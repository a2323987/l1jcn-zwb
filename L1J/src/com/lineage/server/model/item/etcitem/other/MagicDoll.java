package com.lineage.server.model.item.etcitem.other;

import com.lineage.Config;
import com.lineage.server.datatables.MagicDollTable;
import com.lineage.server.datatables.NpcTable;
import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillIconGFX;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.templates.L1MagicDoll;
import com.lineage.server.templates.L1Npc;

/**
 * 魔法娃娃
 * 
 * @author jrwz
 */
public class MagicDoll implements ItemExecutor {

    public static ItemExecutor get() {
        return new MagicDoll();
    }

    /**
     * 使用魔法娃娃
     * 
     * @param pc
     *            使用者
     * @param itemId
     *            道具ID
     * @param itemObjectId
     *            道具OBJID
     */
    private static void useMagicDoll(final L1PcInstance pc, final int itemId,
            final int itemObjectId) {

        final L1MagicDoll magic_doll = MagicDollTable.getInstance()
                .getTemplate((itemId));

        if (magic_doll != null) {
            boolean isAppear = true;
            L1DollInstance doll = null;

            for (final L1DollInstance curdoll : pc.getDollList().values()) {
                doll = curdoll;
                if (doll.getItemObjId() == itemObjectId) {
                    isAppear = false;
                    break;
                }
            }

            if (isAppear) {
                if (!pc.getInventory().checkItem(41246, 50)) {
                    pc.sendPackets(new S_ServerMessage(337, "$5240")); // 魔法结晶体不足
                    return;
                }

                if (pc.getDollList().size() >= Config.MAX_DOLL_COUNT) {
                    pc.sendPackets(new S_ServerMessage(79)); // 没有任何事情发生
                    return;
                }

                final int npcId = magic_doll.getDollId();

                final L1Npc template = NpcTable.getInstance()
                        .getTemplate(npcId);
                doll = new L1DollInstance(template, pc, itemId, itemObjectId);
                pc.sendPackets(new S_SkillSound(doll.getId(), 5935));
                pc.broadcastPacket(new S_SkillSound(doll.getId(), 5935));
                pc.sendPackets(new S_SkillIconGFX(56, 1800));
                pc.sendPackets(new S_OwnCharStatus(pc));
                pc.getInventory().consumeItem(41246, 50);
            } else {
                pc.sendPackets(new S_SkillSound(doll.getId(), 5936));
                pc.broadcastPacket(new S_SkillSound(doll.getId(), 5936));
                doll.deleteDoll();
                pc.sendPackets(new S_SkillIconGFX(56, 0));
                pc.sendPackets(new S_OwnCharStatus(pc));
            }
        }
    }

    private MagicDoll() {
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

        // 取得道具ID
        final int itemId = item.getItemId();
        final int itemObjid = item.getId();

        useMagicDoll(pc, itemId, itemObjid);
    }
}
