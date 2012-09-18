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
package com.lineage.server.model.npc.action;

import org.w3c.dom.Element;

import com.lineage.server.model.L1Location;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.identity.L1ItemId;
import com.lineage.server.model.npc.L1NpcHtml;
import com.lineage.server.serverpackets.S_ServerMessage;

/**
 * NPC传送动作
 */
public class L1NpcTeleportAction extends L1NpcXmlAction {

    private final L1Location _loc;
    private final int _heading;
    private final int _price;
    private final boolean _effect;

    public L1NpcTeleportAction(final Element element) {
        super(element);

        final int x = L1NpcXmlParser.getIntAttribute(element, "X", -1);
        final int y = L1NpcXmlParser.getIntAttribute(element, "Y", -1);
        final int mapId = L1NpcXmlParser.getIntAttribute(element, "Map", -1);
        this._loc = new L1Location(x, y, mapId);

        this._heading = L1NpcXmlParser.getIntAttribute(element, "Heading", 5);

        this._price = L1NpcXmlParser.getIntAttribute(element, "Price", 0);
        this._effect = L1NpcXmlParser.getBoolAttribute(element, "Effect", true);
    }

    @Override
    public L1NpcHtml execute(final String actionName, final L1PcInstance pc,
            final L1Object obj, final byte[] args) {
        if (!pc.getInventory().checkItem(L1ItemId.ADENA, this._price)) {
            pc.sendPackets(new S_ServerMessage(337, "$4")); // \f1%0不足%s。
            return L1NpcHtml.HTML_CLOSE;
        }
        pc.getInventory().consumeItem(L1ItemId.ADENA, this._price);
        L1Teleport.teleport(pc, this._loc.getX(), this._loc.getY(),
                (short) this._loc.getMapId(), this._heading, this._effect);
        return null;
    }

}
