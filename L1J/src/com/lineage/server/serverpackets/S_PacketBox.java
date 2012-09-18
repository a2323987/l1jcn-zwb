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
package com.lineage.server.serverpackets;

import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;

import com.lineage.Config;
import com.lineage.server.Account;
import com.lineage.server.Opcodes;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * スキルアイコンや遮断リストの表示など复数の用途に使われるパケットのクラス
 */
public class S_PacketBox extends ServerBasePacket {
    private static final String S_PACKETBOX = "[S] S_PacketBox";

    private byte[] _byte = null;

    // *** S_107 sub code list ***
    /** Updating */
    public static final int UPDATE_OLD_PART_MEMBER = 104;

    /** 3.3 组队系统(更新新加入的队员信息) */
    public static final int PATRY_UPDATE_MEMBER = 105;

    /** 3.3组队系统(委任新队长) */
    public static final int PATRY_SET_MASTER = 106;

    /** 3.3 组队系统(更新队伍信息,所有队员) */
    public static final int PATRY_MEMBERS = 110;

    // 1:Kent 2:Orc 3:WW 4:Giran 5:Heine 6:Dwarf 7:Aden 8:Diad 9:城名9 ...
    /** C(id) H(?): %s的攻城战开始。 */
    public static final int MSG_WAR_BEGIN = 0;

    /** C(id) H(?): %s的攻城战结束。 */
    public static final int MSG_WAR_END = 1;

    /** C(id) H(?): %s的攻城战正在进行中。 */
    public static final int MSG_WAR_GOING = 2;

    /** -: 已经掌握了城堡的主导权。 (音乐变化) */
    public static final int MSG_WAR_INITIATIVE = 3;

    /** -: 已占领城堡。 */
    public static final int MSG_WAR_OCCUPY = 4;

    /** ?: 结束决斗。 (音乐变化) */
    public static final int MSG_DUEL = 5;

    /** C(count): 您没有发送出任何简讯。 / 全部で%d件送信されました。 */
    public static final int MSG_SMS_SENT = 6;

    /** -: 俩人的结婚在所有人的祝福下完成。 (音乐变化) */
    public static final int MSG_MARRIED = 9;

    /** C(weight): 重量(30段阶) */
    public static final int WEIGHT = 10;

    /** C(food): 饱食度(30段阶) */
    public static final int FOOD = 11;

    /** C(0) C(level): 等级%d以下才能使用此道具。 (0~49以外不显示) */
    public static final int MSG_LEVEL_OVER = 12;

    /** UB情报HTML */
    public static final int HTML_UB = 14;

    /**
     * C(id)<br>
     * 1:感觉到在身上有的精灵力量被空气中融化。<br>
     * 2:忽然全身充满了%s的灵力。火。<br>
     * 3:忽然全身充满了%s的灵力。水。<br>
     * 4:忽然全身充满了%s的灵力。风。<br>
     * 5:忽然全身充满了%s的灵力。地。<br>
     */
    public static final int MSG_ELF = 15;

    /** C(count) S(name)...: 开启拒绝名单 */
    public static final int ADD_EXCLUDE2 = 17;

    /** S(name): 增加到拒绝名单 */
    public static final int ADD_EXCLUDE = 18;

    /** S(name): 移除出拒绝名单 */
    public static final int REM_EXCLUDE = 19;

    /** 技能图标1 */
    public static final int ICONS1 = 20;

    /** 技能图标2 */
    public static final int ICONS2 = 21;

    /** 光系技能图标 */
    public static final int ICON_AURA = 22;

    /** S(name): 新村长由%s选出 */
    public static final int MSG_TOWN_LEADER = 23;

    /**
     * C(id): 你的阶级变更为%s。<br>
     * id - 1:见习 2:一般 3:守护骑士
     */
    public static final int MSG_RANK_CHANGED = 27;

    /** D(?) S(name) S(clanname): %s 血盟的 %s打败了反王 */
    public static final int MSG_WIN_LASTAVARD = 30;

    /** -: \f1你觉得舒服多了。 */
    public static final int MSG_FEEL_GOOD = 31;

    /** 不明。C_30客户端会传回一个封包 */
    public static final int SOMETHING1 = 33;

    /** H(time): 蓝水图示。 */
    public static final int ICON_BLUEPOTION = 34;

    /** H(time): 变身图示。 */
    public static final int ICON_POLYMORPH = 35;

    /** H(time): 禁言图示。 */
    public static final int ICON_CHATBAN = 36;

    /** 不明。C_7パケットが飞ぶ。C_7はペットのメニューを开いたときにも飞ぶ。 */
    public static final int SOMETHING2 = 37;

    /** 血盟成员清单HTML */
    public static final int HTML_CLAN1 = 38;

    /** H(time): 圣结界图示 */
    public static final int ICON_I2H = 40;

    /** 更新角色使用的快捷键 */
    public static final int CHARACTER_CONFIG = 41;

    /** 角色选择视窗 */
    public static final int LOGOUT = 42;

    /** 战斗中无法重新开始。 */
    public static final int MSG_CANT_LOGOUT = 43;

    /**
     * C(count) D(time) S(name) S(info):<br>
     * [CALL] ボタンのついたウィンドウが表示される。これはBOTなどの不正者チェックに
     * 使われる机能らしい。名前をダブルクリックするとC_RequestWhoが飞び、クライアントの
     * フォルダにbot_list.txtが生成される。名前を选択して+キーを押すと新しいウィンドウが开く。
     */
    public static final int CALL_SOMETHING = 45;

    /**
     * C(id): 大圆形竞技场、混沌的大战<br>
     * id - 1:开始 2:取消 3:结束
     */
    public static final int MSG_COLOSSEUM = 49;

    /** 血盟情报HTML */
    public static final int HTML_CLAN2 = 51;

    /** 料理清单 */
    public static final int COOK_WINDOW = 52;

    /** C(type) H(time): 料理アイコンが表示される */
    public static final int ICON_COOKING = 53;

    /** 鱼上钩的图示 */
    public static final int FISHING = 55;

    /** 魔法娃娃状态图示 */
    public static final int ICON_MAGIC_DOLL = 56;

    public S_PacketBox(final int subCode) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(subCode);

        switch (subCode) {
            case MSG_WAR_INITIATIVE:
            case MSG_WAR_OCCUPY:
            case MSG_MARRIED:
            case MSG_FEEL_GOOD:
            case MSG_CANT_LOGOUT:
            case LOGOUT:
            case FISHING:
                break;
            case CALL_SOMETHING:
                this.callSomething();
            default:
                break;
        }
    }

    public S_PacketBox(final int subCode, final int value) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(subCode);

        switch (subCode) {
            case ICON_BLUEPOTION:
            case ICON_CHATBAN:
            case ICON_I2H:
            case ICON_POLYMORPH:
                this.writeH(value); // time
                break;
            case MSG_WAR_BEGIN:
            case MSG_WAR_END:
            case MSG_WAR_GOING:
                this.writeC(value); // castle id
                this.writeH(0); // ?
                break;
            case MSG_SMS_SENT:
            case WEIGHT:
            case FOOD:
                this.writeC(value);
                break;
            case MSG_ELF: // 忽然全身充满了%s的灵力。
            case MSG_RANK_CHANGED: // 你的阶级变更为%s
            case MSG_COLOSSEUM: // 大圆形竞技场，混沌的大战开始！结束！取消！
                this.writeC(value); // msg id
                this.writeC(0);
                break;
            case MSG_LEVEL_OVER:
                this.writeC(0); // ?
                this.writeC(value); // 0-49以外不显示
                break;
            case COOK_WINDOW:
                this.writeC(0xdb); // ?
                this.writeC(0x31);
                this.writeC(0xdf);
                this.writeC(0x02);
                this.writeC(0x01);
                this.writeC(value); // level
                break;
            case 88: // + 闪避率
                this.writeC(value);
                this.writeC(0x00);
                break;
            case 101: // - 闪避率
                this.writeC(value);
                break;
            case 21: // 状态图示
                this.writeC(0x00);
                this.writeC(0x00);
                this.writeC(0x00);
                this.writeC(value); // 闪避图示 (幻术:镜像、黑妖:闇影闪避)
                break;
            default:
                break;
        }
    }

    public S_PacketBox(final int subCode, final int type, final int time) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(subCode);

        switch (subCode) {
            case ICON_COOKING:
                if (type == 54) { // 象牙塔妙药
                    this.writeC(0x12);
                    this.writeC(0x0c);
                    this.writeC(0x0c);
                    this.writeC(0x07);
                    this.writeC(0x12);
                    this.writeC(0x08);
                    this.writeH(0x0000); // 饱和度 值:2000，饱和度100%
                    this.writeC(type); // 类型
                    this.writeC(0x2a);
                    this.writeH(time); // 时间
                    this.writeC(0x0); // 负重度 值:242，负重度100%
                } else if (type != 7) {
                    this.writeC(0x12);
                    this.writeC(0x0b);
                    this.writeC(0x0c);
                    this.writeC(0x0b);
                    this.writeC(0x0f);
                    this.writeC(0x08);
                    this.writeH(0x0000); // 饱和度 值:2000，饱和度100%
                    this.writeC(type); // 类型
                    this.writeC(0x24);
                    this.writeH(time); // 时间
                    this.writeC(0x00); // 负重度 值:242，负重度100%
                } else {
                    this.writeC(0x12);
                    this.writeC(0x0b);
                    this.writeC(0x0c);
                    this.writeC(0x0b);
                    this.writeC(0x0f);
                    this.writeC(0x08);
                    this.writeH(0x0000); // 饱和度 值:2000，饱和度100%
                    this.writeC(type); // 类型
                    this.writeC(0x26);
                    this.writeH(time); // 时间
                    this.writeC(0x00); // 负重度 值:240，负重度100%
                }
                break;
            case MSG_DUEL:
                this.writeD(type); // 对方ID
                this.writeD(time); // 自己ID
                break;
            case ICON_MAGIC_DOLL:
                if (type == 32) { // 爱心图示
                    this.writeH(time);
                    this.writeC(type);
                    this.writeC(12);
                } else { // 魔法娃娃图示
                    this.writeH(time);
                    this.writeC(0);
                    this.writeC(0);
                }
                break;
            default:
                break;
        }
    }

    public S_PacketBox(final int subCode, final int id, final String name,
            final String clanName) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(subCode);

        switch (subCode) {
            case MSG_WIN_LASTAVARD:
                this.writeD(id); // 血盟ID或者什么？
                this.writeS(name);
                this.writeS(clanName);
                break;
            default:
                break;
        }
    }

    public S_PacketBox(final int subCode, final Object[] names) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(subCode);

        switch (subCode) {
            case ADD_EXCLUDE2:
                this.writeC(names.length);
                for (final Object name : names) {
                    this.writeS(name.toString());
                }
                break;
            default:
                break;
        }
    }

    public S_PacketBox(final int subCode, final String name) {
        this.writeC(Opcodes.S_OPCODE_PACKETBOX);
        this.writeC(subCode);

        switch (subCode) {
            case ADD_EXCLUDE:
            case REM_EXCLUDE:
            case MSG_TOWN_LEADER:
                this.writeS(name);
                break;
            default:
                break;
        }
    }

    private void callSomething() {
        final Iterator<L1PcInstance> itr = L1World.getInstance()
                .getAllPlayers().iterator();

        this.writeC(L1World.getInstance().getAllPlayers().size());

        while (itr.hasNext()) {
            final L1PcInstance pc = itr.next();
            final Account acc = Account.load(pc.getAccountName());

            // 时间情报 とりあえずログイン时间を入れてみる
            if (acc == null) {
                this.writeD(0);
            } else {
                final Calendar cal = Calendar.getInstance(TimeZone
                        .getTimeZone(Config.TIME_ZONE));
                final long lastactive = acc.getLastActive().getTime();
                cal.setTimeInMillis(lastactive);
                cal.set(Calendar.YEAR, 1970);
                final int time = (int) (cal.getTimeInMillis() / 1000);
                this.writeD(time); // JST 1970 1/1 09:00 が基准
            }

            // 角色信息
            this.writeS(pc.getName()); // 半角最多12字符
            this.writeS(pc.getClanname()); // 出现在[]内的文字信息。半角最多12字符
        }
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }

        return this._byte;
    }

    @Override
    public String getType() {
        return S_PACKETBOX;
    }
}
