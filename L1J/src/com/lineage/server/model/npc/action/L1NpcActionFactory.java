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

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Element;

import com.lineage.server.utils.collections.Maps;

/**
 * 
 */
public class L1NpcActionFactory {

    private static Logger _log = Logger.getLogger(L1NpcActionFactory.class
            .getName());

    private static Map<String, Constructor<? extends L1NpcXmlAction>> _actions = Maps
            .newMap();

    static {
        try {
            _actions.put("Action", loadConstructor(L1NpcListedAction.class));
            _actions.put("MakeItem", loadConstructor(L1NpcMakeItemAction.class));
            _actions.put("ShowHtml", loadConstructor(L1NpcShowHtmlAction.class));
            _actions.put("SetQuest", loadConstructor(L1NpcSetQuestAction.class));
            _actions.put("Teleport", loadConstructor(L1NpcTeleportAction.class));
        } catch (final NoSuchMethodException e) {
            _log.log(Level.SEVERE, "NpcAction类加载失败", e);
        }
    }

    private static Constructor<? extends L1NpcXmlAction> loadConstructor(
            final Class<? extends L1NpcXmlAction> c)
            throws NoSuchMethodException {
        return c.getConstructor(new Class[] { Element.class });
    }

    public static L1NpcAction newAction(final Element element) {
        try {
            final Constructor<? extends L1NpcXmlAction> con = _actions
                    .get(element.getNodeName());
            return con.newInstance(element);
        } catch (final NullPointerException e) {
            _log.warning(element.getNodeName() + " 未定义的NPC动作.");
        } catch (final Exception e) {
            _log.log(Level.SEVERE, "NpcAction类加载失败", e);
        }
        return null;
    }
}
