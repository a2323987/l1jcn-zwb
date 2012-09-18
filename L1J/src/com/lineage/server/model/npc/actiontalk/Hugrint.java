package com.lineage.server.model.npc.actiontalk;

import com.lineage.server.datatables.ItemTable;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.npc.NpcExecutor;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * 3.5C副本：修格林特 - 81375
 * 
 * @author jrwz
 */
public class Hugrint implements NpcExecutor {

    public static NpcExecutor get() {
        return new Hugrint();
    }

    private Hugrint() {
    }

    @Override
    public void attack(final L1PcInstance pc, final L1NpcInstance npc) {
        // TODO Auto-generated method stub

    }

    @Override
    public L1PcInstance death(final L1Character lastAttacker,
            final L1NpcInstance npc) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void npcTalkAction(final L1PcInstance pc, final L1NpcInstance npc,
            final String s, final int objid) {
        String htmlid = null;
        if (s.equalsIgnoreCase("0")) { // 购买魔力的气息
            if (pc.getInventory().consumeItem(40308, 1000)) { // 检查身上金币是否足够
                final L1ItemInstance item = ItemTable.getInstance().createItem(
                        50001); // 最后的气息
                item.setCount(1); // 一次给予的数量
                if (item != null) {
                    if (pc.getInventory().checkAddItem(item, 1) == L1Inventory.OK) {
                        pc.getInventory().storeItem(item);
                        final L1Object obj = L1World.getInstance().findObject(
                                objid);
                        final String npc_name = ((L1NpcInstance) obj)
                                .getNpcTemplate().get_nameid();
                        pc.sendPackets(new S_ServerMessage(143, npc_name, item
                                .getLogName())); // \f1%0%s 给你%1%o 。
                    } else { // 如果身上道具满则掉落地面上
                        L1World.getInstance()
                                .getInventory(pc.getX(), pc.getY(),
                                        pc.getMapId()).storeItem(item);
                    }
                }
                htmlid = "hugrint2"; // 购买成功显示
            } else {
                htmlid = "hugrint3"; // 钱不够显示
            }
        }
        if (htmlid != null) {
            pc.sendPackets(new S_NPCTalkReturn(npc.getId(), htmlid));
        }
    }

    @Override
    public void npcTalkReturn(final L1PcInstance pc, final L1NpcInstance npc) {
        // pc.sendPackets(new S_NPCTalkReturn(npc.getId(), "hugrint1"));

        String htmlid = null;
        if (pc.getInventory().checkItem(50001)) {
            htmlid = "hugrint4"; // 你已经有魔力的气息了。过多的贪心会招惹麻烦的。
        } else {
            htmlid = "hugrint1"; // 购买
        }
        if (htmlid != null) {
            pc.sendPackets(new S_NPCTalkReturn(npc.getId(), htmlid));
        }
    }

    @Override
    public void spawn(final L1NpcInstance npc) {
        // TODO Auto-generated method stub

    }

    @Override
    public int type() {
        return 3;
    }

    @Override
    public void work(final L1NpcInstance npc) {
        // TODO Auto-generated method stub

    }

    @Override
    public int workTime() {
        // TODO Auto-generated method stub
        return 0;
    }

}
