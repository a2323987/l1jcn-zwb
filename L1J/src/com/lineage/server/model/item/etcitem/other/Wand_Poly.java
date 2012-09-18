package com.lineage.server.model.item.etcitem.other;

import static com.lineage.server.model.skill.L1SkillId.AWAKEN_ANTHARAS;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_FAFURION;
import static com.lineage.server.model.skill.L1SkillId.AWAKEN_VALAKAS;
import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;

import com.lineage.server.ActionCodes;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_AttackPacket;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_ShowPolyList;
import com.lineage.server.templates.L1Skills;
import com.lineage.server.utils.Random;

/**
 * 枫木魔杖 - 40008 <br>
 * 黑暗安特的树皮 - 40410 <br>
 * 受祝福的 枫木魔杖 - 140008 <br>
 * 
 * @author jrwz
 */
public class Wand_Poly implements ItemExecutor {

    public static ItemExecutor get() {
        return new Wand_Poly();
    }

    private Wand_Poly() {
    }

    /**
     * 道具执行
     * 
     * @param data
     *            参数
     * @param pc
     *            对象
     * @param item
     *            道具
     */
    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        final int spellsc_objid = data[0];
        // final int spellsc_x = data[1];
        // final int spellsc_y = data[2];

        if ((pc.getMapId() == 63) || (pc.getMapId() == 552)
                || (pc.getMapId() == 555) || (pc.getMapId() == 557)
                || (pc.getMapId() == 558) || (pc.getMapId() == 779)) { // 水中无法使用
            pc.sendPackets(new S_ServerMessage(563)); // \f1你无法在这个地方使用。
        } else {
            final S_AttackPacket s_attackPacket = new S_AttackPacket(pc, 0,
                    ActionCodes.ACTION_Wand);
            pc.sendPackets(s_attackPacket);
            pc.broadcastPacket(s_attackPacket);
            final L1Object target = L1World.getInstance().findObject(
                    spellsc_objid);
            if (target != null) {
                final L1Character cha = (L1Character) target;
                this.polyAction(pc, cha);
                final int itemId = item.getItemId();
                if ((itemId == 40008) || (itemId == 140008)) {
                    item.setChargeCount(item.getChargeCount() - 1);
                    pc.getInventory().updateItem(item,
                            L1PcInventory.COL_CHARGE_COUNT);
                    if (item.getChargeCount() <= 0) { // 次数为 0时删除
                        pc.getInventory().removeItem(item, 1);
                    }
                } else {
                    pc.getInventory().removeItem(item, 1);
                }
            } else {
                pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            }
        }
    }

    // 变身
    private void polyAction(final L1PcInstance attacker, final L1Character cha) {
        boolean isSameClan = false;
        if (cha instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) cha;
            if ((pc.getClanid() != 0)
                    && (attacker.getClanid() == pc.getClanid())) { // 目标为盟友
                isSameClan = true;
            }
        }
        if ((attacker.getId() != cha.getId()) && !isSameClan) { // 非自身及盟友
            final int probability = 3 * (attacker.getLevel() - cha.getLevel())
                    + 100 - cha.getMr();
            final int rnd = Random.nextInt(100) + 1;
            if (rnd > probability) {
                attacker.sendPackets(new S_ServerMessage(79));
                return;
            }
        }

        final int[] polyArray = { 29, 945, 947, 979, 1037, 1039, 3860, 3861,
                3862, 3863, 3864, 3865, 3904, 3906, 95, 146, 2374, 2376, 2377,
                2378, 3866, 3867, 3868, 3869, 3870, 3871, 3872, 3873, 3874,
                3875, 3876 };

        final int pid = Random.nextInt(polyArray.length);
        final int polyId = polyArray[pid];

        if (cha instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) cha;
            final int awakeSkillId = pc.getAwakeSkillId();
            if ((awakeSkillId == AWAKEN_ANTHARAS)
                    || (awakeSkillId == AWAKEN_FAFURION)
                    || (awakeSkillId == AWAKEN_VALAKAS)) {
                if (attacker.getId() == pc.getId()) {
                    attacker.sendPackets(new S_ServerMessage(1384)); // 目前状态中无法变身。
                } else {
                    attacker.sendPackets(new S_ServerMessage(79));
                }
                return;
            }

            if (pc.getInventory().checkEquipped(20281)) { // 装备变形控制戒指
                pc.sendPackets(new S_ShowPolyList(pc.getId()));
                if (!pc.isShapeChange()) {
                    pc.setShapeChange(true);
                }
            } else {
                if (attacker.getId() != pc.getId()) {
                    pc.sendPackets(new S_ServerMessage(241, attacker.getName())); // %0%s
                                                                                  // 把你变身。
                }
                final L1Skills skillTemp = SkillsTable.getInstance()
                        .getTemplate(SHAPE_CHANGE);
                L1PolyMorph.doPoly(pc, polyId, skillTemp.getBuffDuration(),
                        L1PolyMorph.MORPH_BY_ITEMMAGIC, false);
            }
        } else if (cha instanceof L1MonsterInstance) {
            final L1MonsterInstance mob = (L1MonsterInstance) cha;
            if (mob.getLevel() < 50) {
                final int npcId = mob.getNpcTemplate().get_npcId();
                if ((npcId != 45338)
                        && (npcId != 45370)
                        && (npcId != 45456 // 巨大鳄鱼、强盗头目、魔法师
                        ) && (npcId != 45464)
                        && (npcId != 45473)
                        && (npcId != 45488 // 西玛、巴土瑟、卡士伯
                        ) && (npcId != 45497) && (npcId != 45516)
                        && (npcId != 45529 // 马库尔、伊弗利特、飞龙 (DV)
                        ) && (npcId != 45458)) { // 德雷克 (船长)
                    final L1Skills skillTemp = SkillsTable.getInstance()
                            .getTemplate(SHAPE_CHANGE);
                    L1PolyMorph.doPoly(mob, polyId,
                            skillTemp.getBuffDuration(),
                            L1PolyMorph.MORPH_BY_ITEMMAGIC);
                }
            }
        }
    }
}
