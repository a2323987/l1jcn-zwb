/**
 *                            License
 * THE WORK (AS DEFINED BELOW) IS PROVIDED UNDER THE TERMS OF THIS  
 * CREATIVE COMMONS PUBLIC LICENSE ("CCPL" OR "LICENSE"). 
 * THE WORK IS PROTECTED BY COPYRIGHT AND/OR OTHER APPLICABLE LAW.  
 * ANY USE OF THE WORK OTHER THAN AS AUTHORIZED UNDER THIS LICENSE OR  
 * COPYRIGHT LAW IS PROHIBITED.
 * 
 * BY EXERCISING ANY RIGHTS TO THE WORK PROVIDED HERE, YOU ACCEPT AND  
 * AGREE TO BE BOUND BY THE TERMS OF THIS LICENSE. TO THE EXTENT THIS LICENSE  
 * MAY BE CONSIDERED TO BE A CONTRACT, THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigInteger;

import com.lineage.Config;
import com.lineage.server.datatables.CharBuffTable;
import com.lineage.server.model.Getback;
import com.lineage.server.model.L1DragonSlayer;
import com.lineage.server.model.L1Trade;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1FollowerInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_Disconnect;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_SummonPack;
import com.lineage.server.serverpackets.ServerBasePacket;
import com.lineage.server.utils.StreamUtil;
import com.lineage.server.utils.SystemUtil;

/**
 * 客户端线程
 */
public class ClientThread implements Runnable, PacketOutput {

    // 定时监控客户端
    class ClientThreadObserver extends TimerTask {
        private int _checkct = 1;

        private final int _disconnectTimeMillis;

        public ClientThreadObserver(final int disconnectTimeMillis) {
            this._disconnectTimeMillis = disconnectTimeMillis;
        }

        /** 接收的数据包 */
        public void packetReceived() {
            this._checkct++;
        }

        @Override
        public void run() {
            try {
                if (ClientThread.this._csocket == null) {
                    this.cancel();
                    return;
                }

                if (this._checkct > 0) {
                    this._checkct = 0;
                    return;
                }

                if ((ClientThread.this._activeChar == null // 选角色之前
                        )
                        || ((ClientThread.this._activeChar != null) && !ClientThread.this._activeChar
                                .isPrivateShop())) { // 正在个人商店
                    ClientThread.this.kick();
                    _log.warning("一定时间没有收到封包回应，所以强制切断 ("
                            + ClientThread.this._hostname + ") 的连线。");
                    Account.online(ClientThread.this.getAccount(), false);
                    this.cancel();
                    return;
                }
            } catch (final Exception e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                this.cancel();
            }
        }

        public void start() {
            _observerTimer.scheduleAtFixedRate(ClientThreadObserver.this, 0,
                    this._disconnectTimeMillis);
        }
    }

    // 帐号处理的程序
    class HcPacket implements Runnable {
        private final Queue<byte[]> _queue;

        private final PacketHandler _handler;

        public HcPacket() {
            this._queue = new ConcurrentLinkedQueue<byte[]>();
            this._handler = new PacketHandler(ClientThread.this);
        }

        public HcPacket(final int capacity) {
            this._queue = new LinkedBlockingQueue<byte[]>(capacity);
            this._handler = new PacketHandler(ClientThread.this);
        }

        public void requestWork(final byte data[]) {
            this._queue.offer(data);
        }

        @Override
        public void run() {
            byte[] data;
            while (ClientThread.this._csocket != null) {
                data = this._queue.poll();
                if (data != null) {
                    try {
                        this._handler.handlePacket(data,
                                ClientThread.this._activeChar);
                    } catch (final Exception e) {
                    }
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (final Exception e) {
                    }
                }
            }
            return;
        }
    }

    /** 提示信息 */
    static Logger _log = Logger.getLogger(ClientThread.class.getName());

    /** 离开游戏 */
    public static void quitGame(final L1PcInstance pc) {
        // 如果死掉回到城中，设定饱食度
        if (pc.isDead()) {
            try {
                Thread.sleep(2000);// 暂停该执行续，优先权让给expmonitor
                final int[] loc = Getback.GetBack_Location(pc, true);
                pc.setX(loc[0]);
                pc.setY(loc[1]);
                pc.setMap((short) loc[2]);
                pc.setCurrentHp(pc.getLevel());
                pc.set_food(40);
            } catch (final InterruptedException ie) {
                ie.printStackTrace();
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

        // 终止交易
        if (pc.getTradeID() != 0) { // 交易中
            final L1Trade trade = new L1Trade();
            trade.TradeCancel(pc);
        }

        // 终止决斗
        if (pc.getFightId() != 0) {
            final L1PcInstance fightPc = (L1PcInstance) L1World.getInstance()
                    .findObject(pc.getFightId());
            pc.setFightId(0);
            if (fightPc != null) {
                fightPc.setFightId(0);
                fightPc.sendPackets(new S_PacketBox(S_PacketBox.MSG_DUEL, 0, 0));
            }
        }

        // 离开组队
        if (pc.isInParty()) { // 如果有组队
            pc.getParty().leaveMember(pc);
        }

        // TODO: 离开聊天组队(?)
        if (pc.isInChatParty()) { // 如果在聊天组队中(?)
            pc.getChatParty().leaveMember(pc);
        }

        // 移除世界地图上的宠物
        // 变更召唤怪物的名称
        for (final L1NpcInstance petNpc : pc.getPetList().values()) {
            if (petNpc instanceof L1PetInstance) {
                final L1PetInstance pet = (L1PetInstance) petNpc;
                // 停止饱食度计时
                pet.stopFoodTimer(pet);
                pet.dropItem();
                pc.getPetList().remove(pet.getId());
                pet.deleteMe();
            } else if (petNpc instanceof L1SummonInstance) {
                final L1SummonInstance summon = (L1SummonInstance) petNpc;
                for (final L1PcInstance visiblePc : L1World.getInstance()
                        .getVisiblePlayer(summon)) {
                    visiblePc.sendPackets(new S_SummonPack(summon, visiblePc,
                            false));
                }
            }
        }

        // 移除世界地图上的魔法娃娃
        for (final L1DollInstance doll : pc.getDollList().values()) {
            doll.deleteDoll();
        }

        // 重新建立跟随者
        for (final L1FollowerInstance follower : pc.getFollowerList().values()) {
            follower.setParalyzed(true);
            follower.spawn(follower.getNpcTemplate().get_npcId(),
                    follower.getX(), follower.getY(), follower.getHeading(),
                    follower.getMapId());
            follower.deleteMe();
        }

        // 删除屠龙副本此玩家纪录
        if (pc.getPortalNumber() != -1) {
            L1DragonSlayer.getInstance().removePlayer(pc, pc.getPortalNumber());
        }

        // 储存魔法状态
        CharBuffTable.DeleteBuff(pc);
        CharBuffTable.SaveBuff(pc);
        pc.clearSkillEffectTimer();
        com.lineage.server.model.game.L1PolyRace.getInstance().checkLeaveGame(
                pc);

        // 停止玩家的侦测
        pc.stopEtcMonitor();

        // 设定线上状态为下线
        pc.setOnlineStatus(0);

        // 设定帐号为下线
        // Account account = Account.load(pc.getAccountName());
        // Account.online(account, false);

        // 设定帐号的角色为下线
        final Account account = Account.load(pc.getAccountName());
        Account.OnlineStatus(account, false);

        try {
            pc.save();
            pc.saveInventory();
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    private InputStream _in;

    private OutputStream _out;

    /** 处理程序 */
    private PacketHandler _handler;

    /** 账号 */
    private Account _account;

    L1PcInstance _activeChar;

    private String _ip;

    /** 主机名 */
    String _hostname;

    /** CSocket类 */
    Socket _csocket;

    /** 登录状态 */
    private int _loginStatus = 0;
	// TODO 伺服器捆绑
	private int _xorByte = (byte) 0xF0;
	private long _authdata;
	// 伺服器捆绑
    /**
     * [frist] opcode = 18 Lin.ver 12011702 0000: 12 8e a7 bf 58 42 c0 14 3e 62
     * 80 0d 00 3d c2 00 ....XB..>b...=..
     */
    private static final byte[] FIRST_PACKET = { // 3.51C Taiwan Server
            // (byte) 0xf4, (byte) 0x0a, (byte) 0x8d, (byte) 0x23, (byte)
            // 0x6f, (byte) 0x7f, (byte) 0x04, (byte) 0x00, (byte) 0x05,
            // (byte) 0x08, (byte) 0x00 };

            (byte) 0x8e, (byte) 0xa7, (byte) 0xbf, (byte) 0x58, (byte) 0x42,
            (byte) 0xc0, (byte) 0x14, (byte) 0x3e, (byte) 0x62, (byte) 0x80,
            (byte) 0x0d, (byte) 0x00, (byte) 0x3d, (byte) 0xc2, (byte) 0x00 };

    // TODO: 翻译
    // ClientThreadによる一定间隔自动セーブを制限する为のフラグ（true:制限 false:制限无し）
    // 现在はC_LoginToServerが实行された际にfalseとなり、
    // C_NewCharSelectが实行された际にtrueとなる
    private boolean _charRestart = true;

    private long _lastSavedTime = System.currentTimeMillis();

    private long _lastSavedTime_inventory = System.currentTimeMillis();

    private Cipher _cipher;

    private int _kick = 0;

    private static final int M_CAPACITY = 3; // 一边移动的最大封包量

    private static final int H_CAPACITY = 2;// 一方接受的最高限额所需的行动

    static Timer _observerTimer = new Timer();

    /**
     * for Test
     */
    protected ClientThread() {
    }

    public ClientThread(final Socket socket) throws IOException {
        this._csocket = socket;
        this._ip = socket.getInetAddress().getHostAddress();
        if (Config.HOSTNAME_LOOKUPS) {
            this._hostname = socket.getInetAddress().getHostName();
        } else {
            this._hostname = this._ip;
        }
        this._in = socket.getInputStream();
        this._out = new BufferedOutputStream(socket.getOutputStream());
		// TODO 伺服器捆绑
		if (Config.LOGINS_TO_AUTOENTICATION) {
			int randomNumber = (int) (Math.random() * 900000000) + 255;
			_xorByte = randomNumber % 255 + 1;
			_authdata = new BigInteger(Integer.toString(randomNumber)).modPow(
					new BigInteger(Config.RSA_KEY_E),
					new BigInteger(Config.RSA_KEY_N)).longValue();
		}
		// 伺服器捆绑
        // PacketHandler 初始化
        this._handler = new PacketHandler(this);
    }

    /** 角色重新开始 */
    public void CharReStart(final boolean flag) {
        this._charRestart = flag;
    }

    /** 关闭 */
    public void close() throws IOException {
        this._csocket.close();
    }

    /** 做自动保存 */
    private void doAutoSave() throws Exception {
        if ((this._activeChar == null) || this._charRestart) {
            return;
        }
        try {
            // 自动储存角色资料
            if (Config.AUTOSAVE_INTERVAL * 1000 < System.currentTimeMillis()
                    - this._lastSavedTime) {
                this._activeChar.save();
                this._lastSavedTime = System.currentTimeMillis();
            }

            // 自动储存身上道具资料
            if (Config.AUTOSAVE_INTERVAL_INVENTORY * 1000 < System
                    .currentTimeMillis() - this._lastSavedTime_inventory) {
                this._activeChar.saveInventory();
                this._lastSavedTime_inventory = System.currentTimeMillis();
            }
        } catch (final Exception e) {
            _log.warning("客户端自动保存失败。");
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw e;
        }
    }

    /** 取得账号 */
    public Account getAccount() {
        return this._account;
    }

    /** 取得帐号名称 */
    public String getAccountName() {
        if (this._account == null) {
            return null;
        }
        return this._account.getName();
    }

    /** 取得在线角色 */
    public L1PcInstance getActiveChar() {
        return this._activeChar;
    }

    /** 取得主机名 */
    public String getHostname() {
        return this._hostname;
    }

    /** 取得 IP */
    public String getIp() {
        return this._ip;
    }

    public void kick() {
        Account.online(this.getAccount(), false);
        this.sendPacket(new S_Disconnect());
        this._kick = 1;
        StreamUtil.close(this._out, this._in);
    }

    /** 读取数据包 */
    private byte[] readPacket() throws Exception {
        try {
            int hiByte = this._in.read();
            int loByte = this._in.read();
			// TODO 伺服器捆绑
			if (Config.LOGINS_TO_AUTOENTICATION) {
				hiByte ^= _xorByte;
				loByte ^= _xorByte;
			}
			// 伺服器捆绑
            if (loByte < 0) {
                throw new RuntimeException();
            }
            final int dataLength = (loByte * 256 + hiByte) - 2;

            final byte data[] = new byte[dataLength];

            int readSize = 0;

            for (int i = 0; (i != -1) && (readSize < dataLength); readSize += i) {
                i = this._in.read(data, readSize, dataLength - readSize);
            }

            if (readSize != dataLength) {
                _log.warning("不完整的数据包发送到服务器，关闭连接。");
                throw new RuntimeException();
            }
			// TODO 伺服器捆绑
			if (Config.LOGINS_TO_AUTOENTICATION) {
				for (int i = 0; i < dataLength; i++) {
					data[i] = (byte) (data[i] ^ _xorByte);
				}
			}
			// 伺服器捆绑
            return this._cipher.decrypt(data);
        } catch (final IOException e) {
            throw e;
        }
    }

    @Override
    public void run() {
        _log.info("(" + this._hostname + ") 连结到伺服器。");
        System.out.println("使用了 " + SystemUtil.getUsedMemoryMB() + " MB 的记忆体");
        System.out.println("等待客户端连接...");

        /*
         * TODO: 翻译 クライアントからのパケットをある程度制限する。 理由：不正の误检出が多発する恐れがあるため
         * ex1.サーバに过负荷が挂かっている场合、负荷が落ちたときにクライアントパケットを一气に处理し、结果的に不正扱いとなる。
         * ex2.サーバ侧のネットワーク（下り）にラグがある场合、クライアントパケットが一气に流れ迂み、结果的に不正扱いとなる。
         * ex3.クライアント侧のネットワーク（上り）にラグがある场合、以下同样。 无制限にする前に不正检出方法を见直す必要がある。
         */
        final HcPacket movePacket = new HcPacket(M_CAPACITY);
        final HcPacket hcPacket = new HcPacket(H_CAPACITY);
        GeneralThreadPool.getInstance().execute(movePacket);
        GeneralThreadPool.getInstance().execute(hcPacket);

        final ClientThreadObserver observer = new ClientThreadObserver(
                Config.AUTOMATIC_KICK * 60 * 1000); // 自动断线的时间（单位:毫秒）

        // 是否启用自动踢人
        if (Config.AUTOMATIC_KICK > 0) {
            observer.start();
        }

        try {
            final int key = 0x58bfa78e;// Lin.ver 12011702 351C_TW
            /** 采取乱数取seed */
            // final String keyHax = Integer.toHexString((int) (Math.random() *
            // 2147483647) + 1);
            // final int key = Integer.parseInt(keyHax, 16);

            final byte Bogus = (byte) (FIRST_PACKET.length + 7);
			// TODO 伺服器捆绑
			if (Config.LOGINS_TO_AUTOENTICATION) {
				_out.write((int) (_authdata & 0xff));
				_out.write((int) (_authdata >> 8 & 0xff));
				_out.write((int) (_authdata >> 16 & 0xff));
				_out.write((int) (_authdata >> 24 & 0xff));
				_out.flush();
			}
			// 伺服器捆绑
            this._out.write(Bogus & 0xFF);
            this._out.write(Bogus >> 8 & 0xFF);
            this._out.write(Opcodes.S_OPCODE_INITPACKET);// 3.51C Taiwan Server
            this._out.write((byte) (key & 0xFF));
            this._out.write((byte) (key >> 8 & 0xFF));
            this._out.write((byte) (key >> 16 & 0xFF));
            this._out.write((byte) (key >> 24 & 0xFF));

            this._out.write(FIRST_PACKET);
            this._out.flush();

            this._cipher = new Cipher(key);

            while (true) {
                this.doAutoSave();

                byte data[] = null;
                try {
                    data = this.readPacket();
                } catch (final Exception e) {
                    break;
                }
                // _log.finest("[C]\n" + new
                // ByteArrayUtil(data).dumpToString());

                final int opcode = data[0] & 0xFF;

                // 处理多重登入
                if ((opcode == Opcodes.C_OPCODE_COMMONCLICK)
                        || (opcode == Opcodes.C_OPCODE_CHANGECHAR)) {
                    this._loginStatus = 1;
                }
                if (opcode == Opcodes.C_OPCODE_LOGINTOSERVER) {
                    if (this._loginStatus != 1) {
                        continue;
                    }
                }
                if ((opcode == Opcodes.C_OPCODE_LOGINTOSERVEROK)
                        || (opcode == Opcodes.C_OPCODE_RETURNTOLOGIN)) {
                    this._loginStatus = 0;
                }

                if (opcode != Opcodes.C_OPCODE_KEEPALIVE) {
                    // C_OPCODE_KEEPALIVE以外の何かしらのパケットを受け取ったらObserverへ通知
                    observer.packetReceived();
                }
                // TODO: 翻译
                // 如果目前角色为 null はキャラクター选択前なのでOpcodeの取舍选択はせず全て实行
                if (this._activeChar == null) {
                    this._handler.handlePacket(data, this._activeChar);
                    continue;
                }

                // TODO: 翻译
                // 以降、PacketHandlerの处理状况がClientThreadに影响を与えないようにする为の处理
                // 目的はOpcodeの取舍选択とClientThreadとPacketHandlerの切り离し

                // 要处理的 OPCODE
                // 切换角色、丢道具到地上、删除身上道具
                if ((opcode == Opcodes.C_OPCODE_CHANGECHAR)
                        || (opcode == Opcodes.C_OPCODE_DROPITEM)
                        || (opcode == Opcodes.C_OPCODE_DELETEINVENTORYITEM)) {
                    this._handler.handlePacket(data, this._activeChar);
                } else if (opcode == Opcodes.C_OPCODE_MOVECHAR) {
                    // 为了确保即时的移动，将移动的封包独立出来处理
                    movePacket.requestWork(data);
                } else {
                    // 处理其他数据的传递
                    hcPacket.requestWork(data);
                }
            }
        } catch (final Throwable e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            try {
                if (this._activeChar != null) {
                    quitGame(this._activeChar);

                    synchronized (this._activeChar) {
                        // 从线上中登出角色
                        this._activeChar.logout();
                        this.setActiveChar(null);
                    }
                }
                // 玩家离线时, online=0
                if (this.getAccount() != null) {
                    Account.online(this.getAccount(), false);
                }

                // 送出断线的封包
                this.sendPacket(new S_Disconnect());

                StreamUtil.close(this._out, this._in);
            } catch (final Exception e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            } finally {
                LoginController.getInstance().logout(this);
            }
        }
        this._csocket = null;
        _log.fine("Server thread[C] stopped");
        if (this._kick < 1) {
            _log.info("(" + this.getAccountName() + ":" + this._hostname
                    + ")连线终止。");
            System.out.println("使用了 " + SystemUtil.getUsedMemoryMB()
                    + " MB 的记忆体");
            System.out.println("等待客户端连接...");
            if (this.getAccount() != null) {
                Account.online(this.getAccount(), false);
            }
        }
        return;
    }

    @Override
    /** 发送数据包 */
    public void sendPacket(final ServerBasePacket packet) {
        synchronized (this) {
            try {
                final byte content[] = packet.getContent();
                final byte data[] = Arrays.copyOf(content, content.length);
                this._cipher.encrypt(data);
                final int length = data.length + 2;

                this._out.write(length & 0xff);
                this._out.write(length >> 8 & 0xff);
                this._out.write(data);
                this._out.flush();
            } catch (final Exception e) {
            }
        }
    }

    /** 设置账号 */
    public void setAccount(final Account account) {
        this._account = account;
    }

    /** 设置在线角色 */
    public void setActiveChar(final L1PcInstance pc) {
        this._activeChar = pc;
    }
}
