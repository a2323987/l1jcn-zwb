package com.lineage.server.command.executor;

import com.lineage.server.datatables.ShopTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_SystemMessage;

/**
 * GM指令：重新加载商店物品贩卖价格与数量
 * 
 * @author jrwz
 */
public class L1ReloadShop implements L1CommandExecutor {

    public static L1CommandExecutor getInstance() {
        return new L1ReloadShop();
    }

    private L1ReloadShop() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {

        ShopTable.getInstance().reloadShops();
        pc.sendPackets(new S_SystemMessage("商店物品贩卖价格与数量已从新加载"));
    }

}
