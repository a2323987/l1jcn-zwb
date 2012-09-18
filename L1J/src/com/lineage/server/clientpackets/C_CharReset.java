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
package com.lineage.server.clientpackets;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ClientThread;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.datatables.ExpTable;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_CharReset;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.utils.CalcInitHpMp;
import com.lineage.server.utils.CalcStat;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到客户端传来角色升级/出生的封包
 */
public class C_CharReset extends ClientBasePacket {

    private static final String C_CHAR_RESET = "[C] C_CharReset";

    private static Logger _log = Logger.getLogger(C_CharReset.class.getName());

    /**
     * //配置完初期点数 按确定 127.0.0.1 Request Work ID : 120 0000: 78 01 0d 0a 0b 0a 12
     * 0d //提升10及 127.0.0.1 Request Work ID : 120 0000: 78 02 07 00 //提升1及
     * 127.0.0.1 Request Work ID : 120 0000: 78 02 00 04 //提升完等级 127.0.0.1
     * Request Work ID : 120 0000: 78 02 08 00 x... //万能药 127.0.0.1 Request Work
     * ID : 120 0000: 78 03 23 0a 0b 17 12 0d
     */

    public C_CharReset(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);
        final L1PcInstance pc = clientthread.getActiveChar();
        final int stage = this.readC();

        if (stage == 0x01) { // 0x01:初始化角色
            final int str = this.readC();
            final int intel = this.readC();
            final int wis = this.readC();
            final int dex = this.readC();
            final int con = this.readC();
            final int cha = this.readC();
            final int hp = CalcInitHpMp.calcInitHp(pc);
            final int mp = CalcInitHpMp.calcInitMp(pc);
            pc.sendPackets(new S_CharReset(pc, 1, hp, mp, 10, str, intel, wis,
                    dex, con, cha));
            this.initCharStatus(pc, hp, mp, str, intel, wis, dex, con, cha);
            CharacterTable.getInstance();
            CharacterTable.saveCharStatus(pc);
        } else if (stage == 0x02) { // 0x02:再分配状态
            final int type2 = this.readC();
            if (type2 == 0x00) { // 0x00:Lv1UP
                this.setLevelUp(pc, 1);
            } else if (type2 == 0x07) { // 0x07:Lv10UP
                if (pc.getTempMaxLevel() - pc.getTempLevel() < 10) {
                    return;
                }
                this.setLevelUp(pc, 10);
            } else if (type2 == 0x01) {
                pc.addBaseStr((byte) 1);
                this.setLevelUp(pc, 1);
            } else if (type2 == 0x02) {
                pc.addBaseInt((byte) 1);
                this.setLevelUp(pc, 1);
            } else if (type2 == 0x03) {
                pc.addBaseWis((byte) 1);
                this.setLevelUp(pc, 1);
            } else if (type2 == 0x04) {
                pc.addBaseDex((byte) 1);
                this.setLevelUp(pc, 1);
            } else if (type2 == 0x05) {
                pc.addBaseCon((byte) 1);
                this.setLevelUp(pc, 1);
            } else if (type2 == 0x06) {
                pc.addBaseCha((byte) 1);
                this.setLevelUp(pc, 1);
            } else if (type2 == 0x08) {
                switch (this.readC()) {
                    case 1:
                        pc.addBaseStr((byte) 1);
                        break;
                    case 2:
                        pc.addBaseInt((byte) 1);
                        break;
                    case 3:
                        pc.addBaseWis((byte) 1);
                        break;
                    case 4:
                        pc.addBaseDex((byte) 1);
                        break;
                    case 5:
                        pc.addBaseCon((byte) 1);
                        break;
                    case 6:
                        pc.addBaseCha((byte) 1);
                        break;
                }
                if (pc.getElixirStats() > 0) {
                    pc.sendPackets(new S_CharReset(pc.getElixirStats()));
                    return;
                }
                this.saveNewCharStatus(pc);
            }
        } else if (stage == 0x03) {
            pc.addBaseStr((byte) (this.readC() - pc.getBaseStr()));
            pc.addBaseInt((byte) (this.readC() - pc.getBaseInt()));
            pc.addBaseWis((byte) (this.readC() - pc.getBaseWis()));
            pc.addBaseDex((byte) (this.readC() - pc.getBaseDex()));
            pc.addBaseCon((byte) (this.readC() - pc.getBaseCon()));
            pc.addBaseCha((byte) (this.readC() - pc.getBaseCha()));
            this.saveNewCharStatus(pc);
        }
    }

    @Override
    public String getType() {
        return C_CHAR_RESET;
    }

    /** 初始化角色的状态 */
    private void initCharStatus(final L1PcInstance pc, final int hp,
            final int mp, final int str, final int intel, final int wis,
            final int dex, final int con, final int cha) {
        pc.addBaseMaxHp((short) (hp - pc.getBaseMaxHp()));
        pc.addBaseMaxMp((short) (mp - pc.getBaseMaxMp()));
        pc.addBaseStr((byte) (str - pc.getBaseStr()));
        pc.addBaseInt((byte) (intel - pc.getBaseInt()));
        pc.addBaseWis((byte) (wis - pc.getBaseWis()));
        pc.addBaseDex((byte) (dex - pc.getBaseDex()));
        pc.addBaseCon((byte) (con - pc.getBaseCon()));
        pc.addBaseCha((byte) (cha - pc.getBaseCha()));
    }

    /** 保存角色新的状态 */
    private void saveNewCharStatus(final L1PcInstance pc) {
        pc.setInCharReset(false);
        if (pc.getOriginalAc() > 0) {
            pc.addAc(pc.getOriginalAc());
        }
        if (pc.getOriginalMr() > 0) {
            pc.addMr(0 - pc.getOriginalMr());
        }
        pc.refresh();
        pc.setCurrentHp(pc.getMaxHp());
        pc.setCurrentMp(pc.getMaxMp());
        if (pc.getTempMaxLevel() != pc.getLevel()) {
            pc.setLevel(pc.getTempMaxLevel());
            pc.setExp(ExpTable.getExpByLevel(pc.getTempMaxLevel()));
        }
        if (pc.getLevel() > 50) {
            pc.setBonusStats(pc.getLevel() - 50);
        } else {
            pc.setBonusStats(0);
        }
        pc.sendPackets(new S_OwnCharStatus(pc));
        final L1ItemInstance item = pc.getInventory().findItemId(49142); // 回忆蜡烛
        if (item != null) {
            try {
                pc.getInventory().removeItem(item, 1);
                pc.save(); // 储存玩家的资料到资料库中
            } catch (final Exception e) {
                _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            }
        }
        L1Teleport.teleport(pc, 32628, 32772, (short) 4, 4, false);
    }

    /** 设定等级提升 */
    private void setLevelUp(final L1PcInstance pc, final int addLv) {
        pc.setTempLevel(pc.getTempLevel() + addLv);
        for (int i = 0; i < addLv; i++) {
            final short randomHp = CalcStat.calcStatHp(pc.getType(),
                    pc.getBaseMaxHp(), pc.getBaseCon(), pc.getOriginalHpup());
            final short randomMp = CalcStat.calcStatMp(pc.getType(),
                    pc.getBaseMaxMp(), pc.getBaseWis(), pc.getOriginalMpup());
            pc.addBaseMaxHp(randomHp);
            pc.addBaseMaxMp(randomMp);
        }
        final int newAc = CalcStat.calcAc(pc.getTempLevel(), pc.getBaseDex());
        pc.sendPackets(new S_CharReset(pc, pc.getTempLevel(),
                pc.getBaseMaxHp(), pc.getBaseMaxMp(), newAc, pc.getBaseStr(),
                pc.getBaseInt(), pc.getBaseWis(), pc.getBaseDex(), pc
                        .getBaseCon(), pc.getBaseCha()));
    }

}
