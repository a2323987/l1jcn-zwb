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

import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lineage.server.model.L1Quest;
import com.lineage.server.utils.IterableElementList;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * NpcXml分析器
 */
public class L1NpcXmlParser {

    private final static Map<String, Integer> _questIds = Maps.newMap();

    static {
        _questIds.put("level15", L1Quest.QUEST_LEVEL15);
        _questIds.put("level30", L1Quest.QUEST_LEVEL30);
        _questIds.put("level45", L1Quest.QUEST_LEVEL45);
        _questIds.put("level50", L1Quest.QUEST_LEVEL50);
        _questIds.put("lyra", L1Quest.QUEST_LYRA);
        _questIds.put("oilskinmant", L1Quest.QUEST_OILSKINMANT);
        _questIds.put("doromond", L1Quest.QUEST_DOROMOND);
        _questIds.put("ruba", L1Quest.QUEST_RUBA);
        _questIds.put("lukein", L1Quest.QUEST_LUKEIN1);
        _questIds.put("tbox1", L1Quest.QUEST_TBOX1);
        _questIds.put("tbox2", L1Quest.QUEST_TBOX2);
        _questIds.put("tbox3", L1Quest.QUEST_TBOX3);
        _questIds.put("cadmus", L1Quest.QUEST_CADMUS);
        _questIds.put("resta", L1Quest.QUEST_RESTA);
        _questIds.put("kamyla", L1Quest.QUEST_KAMYLA);
        _questIds.put("lizard", L1Quest.QUEST_LIZARD);
        _questIds.put("desire", L1Quest.QUEST_DESIRE);
        _questIds.put("shadows", L1Quest.QUEST_SHADOWS);
        _questIds.put("toscroll", L1Quest.QUEST_TOSCROLL);
        _questIds.put("moonoflongbow", L1Quest.QUEST_MOONOFLONGBOW);
        _questIds.put("Generalhamelofresentment",
                L1Quest.QUEST_GENERALHAMELOFRESENTMENT);
    }

    public static boolean getBoolAttribute(final Element element,
            final String name, final boolean defaultValue) {
        boolean result = defaultValue;
        final String value = element.getAttribute(name);
        if (!value.equals("")) {
            result = Boolean.valueOf(value);
        }
        return result;
    }

    public static Element getFirstChildElementByTagName(final Element element,
            final String tagName) {
        final IterableElementList list = new IterableElementList(
                element.getElementsByTagName(tagName));
        for (final Element elem : list) {
            return elem;
        }
        return null;
    }

    public static int getIntAttribute(final Element element, final String name,
            final int defaultValue) {
        int result = defaultValue;
        try {
            result = Integer.valueOf(element.getAttribute(name));
        } catch (final NumberFormatException e) {
        }
        return result;
    }

    public static List<L1NpcAction> listActions(final Element element) {
        final List<L1NpcAction> result = Lists.newList();
        final NodeList list = element.getChildNodes();
        for (final Element elem : new IterableElementList(list)) {
            final L1NpcAction action = L1NpcActionFactory.newAction(elem);
            if (action != null) {
                result.add(action);
            }
        }
        return result;
    }

    public static int parseQuestId(final String questId) {
        if (questId.equals("")) {
            return -1;
        }
        final Integer result = _questIds.get(questId.toLowerCase());
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public static int parseQuestStep(final String questStep) {
        if (questStep.equals("")) {
            return -1;
        }
        if (questStep.equalsIgnoreCase("End")) {
            return L1Quest.QUEST_END;
        }
        return Integer.parseInt(questStep);
    }
}
