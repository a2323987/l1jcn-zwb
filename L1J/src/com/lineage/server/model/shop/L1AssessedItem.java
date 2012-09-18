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
package com.lineage.server.model.shop;

import com.lineage.server.datatables.RaceTicketTable;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.templates.L1RaceTicket;

/**
 * 评估道具
 */
public class L1AssessedItem {
    /** 目标ID */
    private final int _targetId;
    /** 评估价格 */
    private int _assessedPrice;

    L1AssessedItem(final int targetId, final int assessedPrice) {
        this._targetId = targetId;
        final L1ItemInstance item = (L1ItemInstance) L1World.getInstance()
                .findObject(this.getTargetId());

        if (item.getItemId() == 40309) { // Race Tickets(食人妖精竞赛票)
            final L1RaceTicket ticket = RaceTicketTable.getInstance()
                    .getTemplate(this._targetId);

            int price = 0;
            if (ticket != null) {
                price = (int) (assessedPrice
                        * ticket.get_allotment_percentage() * ticket
                        .get_victory());
            }
            this._assessedPrice = price;
        } else {
            this._assessedPrice = assessedPrice;
        }
    }

    /** 获得评估价格 */
    public int getAssessedPrice() {
        return this._assessedPrice;
    }

    /** 获得目标ID */
    public int getTargetId() {
        return this._targetId;
    }
}
