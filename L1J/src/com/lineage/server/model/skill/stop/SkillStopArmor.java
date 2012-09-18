package com.lineage.server.model.skill.stop;

import static com.lineage.server.model.skill.L1SkillId.EFFECT_ITEM_OF_ARMOR_SETS;
import static com.lineage.server.model.skill.L1SkillId.EFFECT_OF_ARMOR_SETS;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 技能停止:装备.
 * 
 * @author jrwz
 */
public class SkillStopArmor implements L1SkillStop {

    @Override
    public final void stopSkill(final L1Character cha, final int skillId) {

        switch (skillId) {
            case EFFECT_OF_ARMOR_SETS: // 套装效果:动画
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    final int effect = pc.getGfxEffect();
                    if ((!pc.isDead()) && (effect > 0)) {
                        final S_SkillSound sound = new S_SkillSound(pc.getId(),
                                effect);
                        pc.sendPackets(sound);
                        if (!pc.isInvisble()) {
                            pc.broadcastPacket(sound);
                        }
                    }
                }
                break;

            case EFFECT_ITEM_OF_ARMOR_SETS: // 套装效果:获得特定道具
                if (cha instanceof L1PcInstance) {
                    final L1PcInstance pc = (L1PcInstance) cha;
                    final int obtainProps = pc.getObtainProps(); // 要获得的道具
                    if (!pc.isDead() && (obtainProps > 0)) {
                        final L1ItemInstance item = ItemTable.getInstance()
                                .createItem(obtainProps);
                        item.setCount(1); // 一次给予的数量
                        if (item != null) {
                            if (pc.getInventory().checkAddItem(item, 1) == L1Inventory.OK) {
                                pc.getInventory().storeItem(item);
                            } else { // 如果身上道具满则掉落地面上
                                L1World.getInstance()
                                        .getInventory(pc.getX(), pc.getY(),
                                                pc.getMapId()).storeItem(item);
                            }
                            pc.sendPackets(new S_ServerMessage(403, item
                                    .getLogName())); // 获得%0%o 。
                        }
                    }
                }
                break;
            default:
                break;
        }
    }

}
