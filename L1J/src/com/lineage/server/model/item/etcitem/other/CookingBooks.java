package com.lineage.server.model.item.etcitem.other;

import static com.lineage.server.model.skill.L1SkillId.COOKING_NOW;

import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1EffectInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.utils.Random;

/**
 * 各阶段料理书 - (41255 - 41259)
 * 
 * @author jrwz
 */
public class CookingBooks implements ItemExecutor {

    public static ItemExecutor get() {
        return new CookingBooks();
    }

    private CookingBooks() {
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

        final int itemId = item.getItemId();
        final int cookStatus = data[0];
        final int cookNo = data[1];

        if (cookStatus == 0) {
            pc.sendPackets(new S_PacketBox(S_PacketBox.COOK_WINDOW,
                    (itemId - 41255)));
        } else {
            this.makeCooking(pc, cookNo);
        }
    }

    // 料理
    private void makeCooking(final L1PcInstance pc, final int cookNo) {

        boolean isNearFire = false;
        for (final L1Object obj : L1World.getInstance()
                .getVisibleObjects(pc, 3)) {
            if (obj instanceof L1EffectInstance) {
                final L1EffectInstance effect = (L1EffectInstance) obj;
                if (effect.getGfxId() == 5943) {
                    isNearFire = true;
                    break;
                }
            }
        }

        if (!isNearFire) {
            pc.sendPackets(new S_ServerMessage(1160)); // 为了料理所以需要柴火。
            return;
        }

        if (pc.getMaxWeight() <= pc.getInventory().getWeight()) {
            pc.sendPackets(new S_ServerMessage(1103)); // 身上无法再携带烹饪的物品
            return;
        }

        if (pc.hasSkillEffect(COOKING_NOW)) {
            return;
        }

        pc.setSkillEffect(COOKING_NOW, 3 * 1000); // 料理制作中

        final int chance = Random.nextInt(100) + 1;

        switch (cookNo) {
            case 0: // 漂浮之眼肉排
                if (pc.getInventory().checkItem(40057, 1)) {
                    pc.getInventory().consumeItem(40057, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 41277, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 41285, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 1: // 烤熊肉
                if (pc.getInventory().checkItem(41275, 1)) {
                    pc.getInventory().consumeItem(41275, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 41278, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 41286, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 2: // 煎饼
                if (pc.getInventory().checkItem(41263, 1)
                        && pc.getInventory().checkItem(41265, 1)) {
                    pc.getInventory().consumeItem(41263, 1);
                    pc.getInventory().consumeItem(41265, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 41279, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 41287, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 3: // 烤蚂蚁腿起司
                if (pc.getInventory().checkItem(41274, 1)
                        && pc.getInventory().checkItem(41267, 1)) {
                    pc.getInventory().consumeItem(41274, 1);
                    pc.getInventory().consumeItem(41267, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 41280, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 41288, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 4: // 水果沙拉
                if (pc.getInventory().checkItem(40062, 1)
                        && pc.getInventory().checkItem(40069, 1)
                        && pc.getInventory().checkItem(40064, 1)) {
                    pc.getInventory().consumeItem(40062, 1);
                    pc.getInventory().consumeItem(40069, 1);
                    pc.getInventory().consumeItem(40064, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 41281, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 41289, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 5: // 水果糖醋肉
                if (pc.getInventory().checkItem(40056, 1)
                        && pc.getInventory().checkItem(40060, 1)
                        && pc.getInventory().checkItem(40061, 1)) {
                    pc.getInventory().consumeItem(40056, 1);
                    pc.getInventory().consumeItem(40060, 1);
                    pc.getInventory().consumeItem(40061, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 41282, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 41290, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 6: // 烤山猪肉串
                if (pc.getInventory().checkItem(41276, 1)) {
                    pc.getInventory().consumeItem(41276, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 41283, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 41291, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 7: // 蘑菇汤
                if (pc.getInventory().checkItem(40499, 1)
                        && pc.getInventory().checkItem(40060, 1)) {
                    pc.getInventory().consumeItem(40499, 1);
                    pc.getInventory().consumeItem(40060, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 41284, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 41292, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 8: // 鱼子酱
                if (pc.getInventory().checkItem(49040, 1)
                        && pc.getInventory().checkItem(49048, 1)) {
                    pc.getInventory().consumeItem(49040, 1);
                    pc.getInventory().consumeItem(49048, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49049, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49057, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 9: // 鳄鱼肉排
                if (pc.getInventory().checkItem(49041, 1)
                        && pc.getInventory().checkItem(49048, 1)) {
                    pc.getInventory().consumeItem(49041, 1);
                    pc.getInventory().consumeItem(49048, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49050, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49058, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 10: // 龙龟蛋饼干
                if (pc.getInventory().checkItem(49042, 1)
                        && pc.getInventory().checkItem(41265, 1)
                        && pc.getInventory().checkItem(49048, 1)) {
                    pc.getInventory().consumeItem(49042, 1);
                    pc.getInventory().consumeItem(41265, 1);
                    pc.getInventory().consumeItem(49048, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49051, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49059, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 11: // 烤奇异鹦鹉
                if (pc.getInventory().checkItem(49043, 1)
                        && pc.getInventory().checkItem(49048, 1)) {
                    pc.getInventory().consumeItem(49043, 1);
                    pc.getInventory().consumeItem(49048, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49052, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49060, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 12: // 毒蝎串烧
                if (pc.getInventory().checkItem(49044, 1)
                        && pc.getInventory().checkItem(49048, 1)) {
                    pc.getInventory().consumeItem(49044, 1);
                    pc.getInventory().consumeItem(49048, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49053, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49061, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 13: // 炖伊莱克顿
                if (pc.getInventory().checkItem(49045, 1)
                        && pc.getInventory().checkItem(49048, 1)) {
                    pc.getInventory().consumeItem(49045, 1);
                    pc.getInventory().consumeItem(49048, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49054, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49062, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 14: // 蜘蛛腿串烧
                if (pc.getInventory().checkItem(49046, 1)
                        && pc.getInventory().checkItem(49048, 1)) {
                    pc.getInventory().consumeItem(49046, 1);
                    pc.getInventory().consumeItem(49048, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49055, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49063, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 15: // 蟹肉汤
                if (pc.getInventory().checkItem(49047, 1)
                        && pc.getInventory().checkItem(40499, 1)
                        && pc.getInventory().checkItem(49048, 1)) {
                    pc.getInventory().consumeItem(49047, 1); // 蟹肉
                    pc.getInventory().consumeItem(40499, 1); // 蘑菇汁
                    pc.getInventory().consumeItem(49048, 1); // 综合烤肉酱
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49056, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49064, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 16: // 烤奎斯坦修的螯
                if (pc.getInventory().checkItem(49048, 1)
                        && pc.getInventory().checkItem(49243, 1)
                        && pc.getInventory().checkItem(49260, 1)) {
                    pc.getInventory().consumeItem(49048, 1);
                    pc.getInventory().consumeItem(49243, 1);
                    pc.getInventory().consumeItem(49260, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49244, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49252, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 17: // 烤格利芬肉
                if (pc.getInventory().checkItem(49048, 1)
                        && pc.getInventory().checkItem(49243, 1)
                        && pc.getInventory().checkItem(49261, 1)) {
                    pc.getInventory().consumeItem(49048, 1);
                    pc.getInventory().consumeItem(49243, 1);
                    pc.getInventory().consumeItem(49261, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49245, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49253, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 18: // 亚力安的尾巴肉排
                if (pc.getInventory().checkItem(49048, 1)
                        && pc.getInventory().checkItem(49243, 1)
                        && pc.getInventory().checkItem(49262, 1)) {
                    pc.getInventory().consumeItem(49048, 1);
                    pc.getInventory().consumeItem(49243, 1);
                    pc.getInventory().consumeItem(49262, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49246, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49254, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 19: // 烤巨王龟肉
                if (pc.getInventory().checkItem(49048, 1)
                        && pc.getInventory().checkItem(49243, 1)
                        && pc.getInventory().checkItem(49263, 1)) {
                    pc.getInventory().consumeItem(49048, 1);
                    pc.getInventory().consumeItem(49243, 1);
                    pc.getInventory().consumeItem(49263, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49247, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49255, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 20: // 幼龙翅膀串烧
                if (pc.getInventory().checkItem(49048, 1)
                        && pc.getInventory().checkItem(49243, 1)
                        && pc.getInventory().checkItem(49264, 1)) {
                    pc.getInventory().consumeItem(49048, 1);
                    pc.getInventory().consumeItem(49243, 1);
                    pc.getInventory().consumeItem(49264, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49248, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49256, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 21: // 烤飞龙肉
                if (pc.getInventory().checkItem(49048, 1)
                        && pc.getInventory().checkItem(49243, 1)
                        && pc.getInventory().checkItem(49265, 1)) {
                    pc.getInventory().consumeItem(49048, 1);
                    pc.getInventory().consumeItem(49243, 1);
                    pc.getInventory().consumeItem(49265, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49249, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49257, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 22: // 炖深海鱼肉
                if (pc.getInventory().checkItem(49048, 1)
                        && pc.getInventory().checkItem(49243, 1)
                        && pc.getInventory().checkItem(49266, 1)) {
                    pc.getInventory().consumeItem(49048, 1);
                    pc.getInventory().consumeItem(49243, 1);
                    pc.getInventory().consumeItem(49266, 1);
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49250, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49258, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;

            case 23: // 邪恶蜥蜴蛋汤
                if (pc.getInventory().checkItem(49048, 1)
                        && pc.getInventory().checkItem(49243, 1)
                        && pc.getInventory().checkItem(49267, 1)
                        && pc.getInventory().checkItem(40499, 1)) {
                    pc.getInventory().consumeItem(49048, 1); // 综合烤肉酱
                    pc.getInventory().consumeItem(49243, 1); // 香菜
                    pc.getInventory().consumeItem(49267, 1); // 邪恶蜥蜴蛋
                    pc.getInventory().consumeItem(40499, 1); // 蘑菇汁
                    if ((chance >= 1) && (chance <= 90)) {
                        pc.createNewItem(pc, 49251, 1);
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6392));
                    } else if ((chance >= 91) && (chance <= 95)) {
                        pc.createNewItem(pc, 49259, 1);
                        pc.sendPackets(new S_SkillSound(pc.getId(), 6390));
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6390));
                    } else if ((chance >= 96) && (chance <= 100)) {
                        pc.sendPackets(new S_ServerMessage(1101)); // 执行失败...
                        pc.broadcastPacket(new S_SkillSound(pc.getId(), 6394));
                    }
                } else {
                    pc.sendPackets(new S_ServerMessage(1102)); // 不够材料可以烹饪...
                }
                break;
        }
    }
}
