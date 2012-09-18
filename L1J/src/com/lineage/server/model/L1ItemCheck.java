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
package com.lineage.server.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.utils.SQLUtil;

/**
 * 负责物品状态检查是否作弊
 */
public class L1ItemCheck {

    private int itemId;

    private boolean isStackable = false;

    private boolean findArmor() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean inArmor = false;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM armor WHERE item_id = ?");
            pstm.setInt(1, this.itemId);
            rs = pstm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    inArmor = true;
                }
            }
        } catch (final Exception e) {
        } finally {
            SQLUtil.close(rs, pstm, con);
        }
        return inArmor;
    }

    private boolean findEtcItem() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean inEtcitem = false;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM etcitem WHERE item_id = ?");
            pstm.setInt(1, this.itemId);
            rs = pstm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    inEtcitem = true;
                    this.isStackable = rs.getInt("stackable") == 1 ? true
                            : false;
                }
            }
        } catch (final Exception e) {
        } finally {
            SQLUtil.close(rs, pstm, con);
        }
        return inEtcitem;
    }

    private boolean findWeapon() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean inWeapon = false;

        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM weapon WHERE item_id = ?");
            pstm.setInt(1, this.itemId);
            rs = pstm.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    inWeapon = true;
                }
            }
        } catch (final Exception e) {
        } finally {
            SQLUtil.close(rs, pstm, con);
        }
        return inWeapon;
    }

    public boolean ItemCheck(final L1ItemInstance item, final L1PcInstance pc) {
        this.itemId = item.getItem().getItemId();
        final int itemCount = item.getCount();
        boolean isCheat = false;

        if ((this.findWeapon() || this.findArmor()) && (itemCount != 1)) {
            isCheat = true;
        } else if (this.findEtcItem()) {
            // 不可堆叠的道具却堆叠，就视为作弊
            if (!this.isStackable && (itemCount != 1)) {
                isCheat = true;
                // 金币大于20亿以及金币负值则为作弊
            } else if ((this.itemId == 40308)
                    && ((itemCount > 2000000000) || (itemCount < 0))) {
                isCheat = true;
                // 可堆叠道具(金币除外)堆叠超过十万个以及堆叠负值设定为作弊
            } else if (this.isStackable && (this.itemId != 40308)
                    && ((itemCount > 100000) || (itemCount < 0))) {
                isCheat = true;
            }
        }
        if (isCheat) {
            // 作弊直接删除物品
            pc.getInventory().removeItem(item, itemCount);
        }
        return isCheat;
    }
}
