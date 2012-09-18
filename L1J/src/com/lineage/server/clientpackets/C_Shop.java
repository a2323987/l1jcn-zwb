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

import java.util.List;

import com.lineage.server.ActionCodes;
import com.lineage.server.ClientThread;
import com.lineage.server.model.Instance.L1DollInstance;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_DoActionShop;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1PrivateShopBuyList;
import com.lineage.server.templates.L1PrivateShopSellList;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来商店的封包
 */
public class C_Shop extends ClientBasePacket {

    private static final String C_SHOP = "[C] C_Shop";

    public C_Shop(final byte abyte0[], final ClientThread clientthread) {
        super(abyte0);

        final L1PcInstance pc = clientthread.getActiveChar();
        if (pc.isGhost()) {
            return;
        }

        final int mapId = pc.getMapId();
        // 可以开设个人商店的地图
        if ((mapId != 340 // 古鲁丁商店村
                )
                && (mapId != 350 // 奇岩商店村
                ) && (mapId != 360 // 欧瑞商店村
                ) && (mapId != 370 // 银骑士商店村
                )) {
            pc.sendPackets(new S_ServerMessage(876)); // 无法在此开设个人商店。
            return;
        }

        final List<L1PrivateShopSellList> sellList = pc.getSellList();
        final List<L1PrivateShopBuyList> buyList = pc.getBuyList();
        L1ItemInstance checkItem;
        boolean tradable = true;

        final int type = this.readC();
        if (type == 0) { // 开始
            final int sellTotalCount = this.readH();
            int sellObjectId;
            int sellPrice;
            int sellCount;
            for (int i = 0; i < sellTotalCount; i++) {
                sellObjectId = this.readD();
                sellPrice = this.readD();
                sellCount = this.readD();
                // 检查交易项目
                checkItem = pc.getInventory().getItem(sellObjectId);
                if (!checkItem.getItem().isTradable()) {
                    tradable = false;
                    pc.sendPackets(new S_ServerMessage(166, // \f1%0%s %4%1%3
                                                            // %2。
                            checkItem.getItem().getName(), "这是不可能处理。"));
                }
                for (final L1NpcInstance petNpc : pc.getPetList().values()) {
                    if (petNpc instanceof L1PetInstance) {
                        final L1PetInstance pet = (L1PetInstance) petNpc;
                        if (checkItem.getId() == pet.getItemObjId()) {
                            tradable = false;
                            pc.sendPackets(new S_ServerMessage(166, // \f1%0%s
                                                                    // %4%1%3
                                                                    // %2。
                                    checkItem.getItem().getName(), "这是不可能处理。"));
                            break;
                        }
                    }
                }
                final L1PrivateShopSellList pssl = new L1PrivateShopSellList();
                pssl.setItemObjectId(sellObjectId);
                pssl.setSellPrice(sellPrice);
                pssl.setSellTotalCount(sellCount);
                sellList.add(pssl);
            }
            final int buyTotalCount = this.readH();
            int buyObjectId;
            int buyPrice;
            int buyCount;
            for (int i = 0; i < buyTotalCount; i++) {
                buyObjectId = this.readD();
                buyPrice = this.readD();
                buyCount = this.readD();
                // 检查交易项目
                checkItem = pc.getInventory().getItem(buyObjectId);
                if (!checkItem.getItem().isTradable()) {
                    tradable = false;
                    pc.sendPackets(new S_ServerMessage(166, // \f1%0%s %4%1%3
                                                            // %2。
                            checkItem.getItem().getName(), "这是不可能处理。"));
                }

                // 封印的装备
                if (checkItem.getBless() >= 128) { // 封印的装备
                    // \f1%0%d是不可转移的…
                    pc.sendPackets(new S_ServerMessage(210, checkItem.getItem()
                            .getName()));
                    return;
                }

                // 防止异常堆叠交易
                if ((checkItem.getCount() > 1)
                        && (!checkItem.getItem().isStackable())) {
                    pc.sendPackets(new S_SystemMessage("此物品非堆叠，但异常堆叠无法交易。"));
                    return;
                }

                // 使用中的宠物项链 - 无法贩卖
                for (final L1NpcInstance petNpc : pc.getPetList().values()) {
                    if (petNpc instanceof L1PetInstance) {
                        final L1PetInstance pet = (L1PetInstance) petNpc;
                        if (checkItem.getId() == pet.getItemObjId()) {
                            tradable = false;
                            pc.sendPackets(new S_ServerMessage(1187)); // 宠物项链正在使用中。
                            break;
                        }
                    }
                }

                // 使用中的魔法娃娃 - 无法贩卖
                for (final L1DollInstance doll : pc.getDollList().values()) {
                    if (doll.getItemObjId() == checkItem.getId()) {
                        tradable = false;
                        pc.sendPackets(new S_ServerMessage(1181)); // 这个魔法娃娃目前正在使用中。
                        break;
                    }
                }
                final L1PrivateShopBuyList psbl = new L1PrivateShopBuyList();
                psbl.setItemObjectId(buyObjectId);
                psbl.setBuyPrice(buyPrice);
                psbl.setBuyTotalCount(buyCount);
                buyList.add(psbl);
            }
            if (!tradable) { // 如果项目不包括在交易结束零售商
                sellList.clear();
                buyList.clear();
                pc.setPrivateShop(false);
                pc.sendPackets(new S_DoActionGFX(pc.getId(),
                        ActionCodes.ACTION_Idle));
                pc.broadcastPacket(new S_DoActionGFX(pc.getId(),
                        ActionCodes.ACTION_Idle));
                return;
            }
            final byte[] chat = this.readByte();
            pc.setShopChat(chat);
            pc.setPrivateShop(true);
            pc.sendPackets(new S_DoActionShop(pc.getId(),
                    ActionCodes.ACTION_Shop, chat));
            pc.broadcastPacket(new S_DoActionShop(pc.getId(),
                    ActionCodes.ACTION_Shop, chat));
        } else if (type == 1) { // 终了
            sellList.clear();
            buyList.clear();
            pc.setPrivateShop(false);
            pc.sendPackets(new S_DoActionGFX(pc.getId(),
                    ActionCodes.ACTION_Idle));
            pc.broadcastPacket(new S_DoActionGFX(pc.getId(),
                    ActionCodes.ACTION_Idle));
        }
    }

    @Override
    public String getType() {
        return C_SHOP;
    }

}
