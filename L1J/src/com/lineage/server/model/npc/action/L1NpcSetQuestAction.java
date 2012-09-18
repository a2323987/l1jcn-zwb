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

import com.lineage.server.model.L1Object;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.npc.L1NpcHtml;

/**
 * 
 */
public class L1NpcSetQuestAction extends L1NpcXmlAction {

    private final int _id;
    private final int _step;

    public L1NpcSetQuestAction(final Element element) {
        super(element);

        this._id = L1NpcXmlParser.parseQuestId(element.getAttribute("Id"));
        this._step = L1NpcXmlParser
                .parseQuestStep(element.getAttribute("Step"));

        if ((this._id == -1) || (this._step == -1)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public L1NpcHtml execute(final String actionName, final L1PcInstance pc,
            final L1Object obj, final byte[] args) {
        pc.getQuest().set_step(this._id, this._step);
        return null;
    }

}
