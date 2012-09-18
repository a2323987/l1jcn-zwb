package com.lineage.server.model.item.etcitem;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_A_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_B_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_C_9;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_1;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_2;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_3;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_4;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_5;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_6;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_7;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_8;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_MAGIC_STONE_D_9;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_HPUpdate;
import com.lineage.server.serverpackets.S_MPUpdate;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_OwnCharStatus2;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 附魔石效果
 * 
 * @author jrwz
 */
public class MagicStone implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new MagicStone();
        }
        return _instance;
    }

    /**
     * 附魔石效果
     * 
     * @param pc
     *            对象
     * @param skillId
     *            技能编号
     * @param time
     *            效果时间
     */
    private static void magicStoneEffect(final L1PcInstance pc,
            final int skillId, final int time) {
        byte type = 0;
        if (!pc.hasSkillEffect(skillId)) {
            switch (skillId) {
                case EFFECT_MAGIC_STONE_A_1:
                    pc.addMaxHp(10);
                    type = 84;
                    break;
                case EFFECT_MAGIC_STONE_A_2:
                    pc.addMaxHp(20);
                    type = 85;
                    break;
                case EFFECT_MAGIC_STONE_A_3:
                    pc.addMaxHp(30);
                    type = 86;
                    break;
                case EFFECT_MAGIC_STONE_A_4:
                    pc.addMaxHp(40);
                    type = 87;
                    break;
                case EFFECT_MAGIC_STONE_A_5:
                    pc.addMaxHp(50);
                    pc.addHpr(1);
                    type = 88;
                    break;
                case EFFECT_MAGIC_STONE_A_6:
                    pc.addMaxHp(60);
                    pc.addHpr(2);
                    type = 89;
                    break;
                case EFFECT_MAGIC_STONE_A_7:
                    pc.addMaxHp(70);
                    pc.addHpr(3);
                    type = 90;
                    break;
                case EFFECT_MAGIC_STONE_A_8:
                    pc.addMaxHp(80);
                    pc.addHpr(4);
                    pc.addHitup(1);
                    type = 91;
                    break;
                case EFFECT_MAGIC_STONE_A_9:
                    pc.addMaxHp(100);
                    pc.addHpr(5);
                    pc.addHitup(2);
                    pc.addDmgup(2);
                    pc.addStr((byte) 1);
                    pc.sendPackets(new S_OwnCharStatus2(pc));
                    type = 92;
                    break;
                case EFFECT_MAGIC_STONE_B_1:
                    pc.addMaxHp(5);
                    pc.addMaxMp(3);
                    type = 93;
                    break;
                case EFFECT_MAGIC_STONE_B_2:
                    pc.addMaxHp(10);
                    pc.addMaxMp(6);
                    type = 94;
                    break;
                case EFFECT_MAGIC_STONE_B_3:
                    pc.addMaxHp(15);
                    pc.addMaxMp(10);
                    type = 95;
                    break;
                case EFFECT_MAGIC_STONE_B_4:
                    pc.addMaxHp(20);
                    pc.addMaxMp(15);
                    type = 96;
                    break;
                case EFFECT_MAGIC_STONE_B_5:
                    pc.addMaxHp(25);
                    pc.addMaxMp(20);
                    type = 97;
                    break;
                case EFFECT_MAGIC_STONE_B_6:
                    pc.addMaxHp(30);
                    pc.addMaxMp(20);
                    pc.addHpr(1);
                    type = 98;
                    break;
                case EFFECT_MAGIC_STONE_B_7:
                    pc.addMaxHp(35);
                    pc.addMaxMp(20);
                    pc.addHpr(1);
                    pc.addMpr(1);
                    type = 99;
                    break;
                case EFFECT_MAGIC_STONE_B_8:
                    pc.addMaxHp(40);
                    pc.addMaxMp(25);
                    pc.addHpr(2);
                    pc.addMpr(1);
                    type = 100;
                    break;
                case EFFECT_MAGIC_STONE_B_9:
                    pc.addMaxHp(50);
                    pc.addMaxMp(30);
                    pc.addHpr(2);
                    pc.addMpr(2);
                    pc.addBowDmgup(2);
                    pc.addBowHitup(2);
                    pc.addDex((byte) 1);
                    pc.sendPackets(new S_OwnCharStatus2(pc));
                    type = 101;
                    break;
                case EFFECT_MAGIC_STONE_C_1:
                    pc.addMaxMp(5);
                    type = 102;
                    break;
                case EFFECT_MAGIC_STONE_C_2:
                    pc.addMaxMp(10);
                    type = 103;
                    break;
                case EFFECT_MAGIC_STONE_C_3:
                    pc.addMaxMp(15);
                    type = 104;
                    break;
                case EFFECT_MAGIC_STONE_C_4:
                    pc.addMaxMp(20);
                    type = 105;
                    break;
                case EFFECT_MAGIC_STONE_C_5:
                    pc.addMaxMp(25);
                    pc.addMpr(1);
                    type = 106;
                    break;
                case EFFECT_MAGIC_STONE_C_6:
                    pc.addMaxMp(30);
                    pc.addMpr(2);
                    type = 107;
                    break;
                case EFFECT_MAGIC_STONE_C_7:
                    pc.addMaxMp(35);
                    pc.addMpr(3);
                    type = 108;
                    break;
                case EFFECT_MAGIC_STONE_C_8:
                    pc.addMaxMp(40);
                    pc.addMpr(4);
                    type = 109;
                    break;
                case EFFECT_MAGIC_STONE_C_9:
                    pc.addMaxMp(50);
                    pc.addMpr(5);
                    pc.addInt((byte) 1);
                    pc.addSp(1);
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_OwnCharStatus2(pc));
                    type = 110;
                    break;
                case EFFECT_MAGIC_STONE_D_1:
                    pc.addMr(2);
                    type = 111;
                    break;
                case EFFECT_MAGIC_STONE_D_2:
                    pc.addMr(4);
                    type = 112;
                    break;
                case EFFECT_MAGIC_STONE_D_3:
                    pc.addMr(6);
                    type = 113;
                    break;
                case EFFECT_MAGIC_STONE_D_4:
                    pc.addMr(8);
                    type = 114;
                    break;
                case EFFECT_MAGIC_STONE_D_5:
                    pc.addMr(10);
                    pc.addAc(-1);
                    type = 115;
                    break;
                case EFFECT_MAGIC_STONE_D_6:
                    pc.addMr(10);
                    pc.addAc(-2);
                    type = 116;
                    break;
                case EFFECT_MAGIC_STONE_D_7:
                    pc.addMr(10);
                    pc.addAc(-3);
                    type = 117;
                    break;
                case EFFECT_MAGIC_STONE_D_8:
                    pc.addMr(15);
                    pc.addAc(-4);
                    pc.addDamageReductionByArmor(1);
                    type = 118;
                    break;
                case EFFECT_MAGIC_STONE_D_9:
                    pc.addMr(20);
                    pc.addAc(-5);
                    pc.addCon((byte) 1);
                    pc.addDamageReductionByArmor(3);
                    type = 119;
                    break;
                default:
                    break;
            }

            switch (type) {
                case 84: // (近战)
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    break;

                case 93: // (远攻)
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                    pc.sendPackets(new S_HPUpdate(pc.getCurrentHp(), pc
                            .getMaxHp()));
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    if (pc.isInParty()) { // 组队中
                        pc.getParty().updateMiniHP(pc);
                    }
                    break;

                case 102: // 恢复
                case 103:
                case 104:
                case 105:
                case 106:
                case 107:
                case 108:
                case 109:
                case 110:
                    pc.sendPackets(new S_MPUpdate(pc.getCurrentMp(), pc
                            .getMaxMp()));
                    break;

                case 111: // 防御
                case 112:
                case 113:
                case 114:
                case 115:
                case 116:
                case 117:
                case 118:
                case 119:
                    pc.sendPackets(new S_SPMR(pc));
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                    pc.sendPackets(new S_OwnCharStatus2(pc));
                    break;

                default:
                    break;
            }
        }
        pc.setMagicStoneLevel(type);
        pc.setSkillEffect(skillId, time * 1000);
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        int skillId = 0;
        int time1 = 0;
        int gfxid1 = 0;
        switch (itemId) {
            case 47064: // 附魔石(近战)
            case 47065:
            case 47066:
            case 47067:
            case 47068:
            case 47069:
            case 47070:
            case 47071:
            case 47072:
                skillId = itemId - 43051;
                gfxid1 = itemId - 38125;
                time1 = 600;
                pc.deleteRepeatedSkills(skillId); // 附魔石不可共存
                break;

            case 47074: // 附魔石(远攻)
            case 47075:
            case 47076:
            case 47077:
            case 47078:
            case 47079:
            case 47080:
            case 47081:
            case 47082:
                skillId = itemId - 43052;
                gfxid1 = itemId - 38126;
                time1 = 600;
                pc.deleteRepeatedSkills(skillId); // 附魔石不可共存
                break;

            case 47084: // 附魔石(恢复)
            case 47085:
            case 47086:
            case 47087:
            case 47088:
            case 47089:
            case 47090:
            case 47091:
            case 47092:
                skillId = itemId - 43053;
                gfxid1 = itemId - 38127;
                time1 = 600;
                pc.deleteRepeatedSkills(skillId); // 附魔石不可共存
                break;

            case 47094: // 附魔石(防御)
            case 47095:
            case 47096:
            case 47097:
            case 47098:
            case 47099:
            case 47100:
            case 47101:
            case 47102:
                skillId = itemId - 43054;
                gfxid1 = itemId - 38128;
                time1 = 600;
                pc.deleteRepeatedSkills(skillId); // 附魔石不可共存
                break;

            default:
                pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生。
                break;
        }

        magicStoneEffect(pc, skillId, time1);

        pc.sendPackets(new S_SkillSound(pc.getId(), gfxid1));
        pc.broadcastPacket(new S_SkillSound(pc.getId(), gfxid1));
    }

}
