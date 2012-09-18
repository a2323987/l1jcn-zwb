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
package com.lineage.server;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.lineage.server.model.L1Location;
import com.lineage.server.templates.L1ItemSetItem;
import com.lineage.server.utils.IterableElementList;
import com.lineage.server.utils.collections.Lists;
import com.lineage.server.utils.collections.Maps;

/**
 * GM指令设置
 */
public class GMCommandsConfig {

    private interface ConfigLoader {
        public void load(Element element);
    }

    private class ItemSetLoader extends ListLoaderAdapter {
        public ItemSetLoader() {
            super("ItemSet");
        }

        @Override
        public void loadElement(final Element element) {
            final List<L1ItemSetItem> list = Lists.newList();
            final NodeList nodes = element.getChildNodes();
            for (final Element elem : new IterableElementList(nodes)) {
                if (elem.getNodeName().equalsIgnoreCase("Item")) {
                    list.add(this.loadItem(elem));
                }
            }
            final String name = element.getAttribute("Name");
            ITEM_SETS.put(name.toLowerCase(), list);
        }

        public L1ItemSetItem loadItem(final Element element) {
            final int id = Integer.valueOf(element.getAttribute("Id"));
            final int amount = Integer.valueOf(element.getAttribute("Amount"));
            final int enchant = Integer
                    .valueOf(element.getAttribute("Enchant"));
            return new L1ItemSetItem(id, amount, enchant);
        }
    }

    private abstract class ListLoaderAdapter implements ConfigLoader {
        private final String _listName;

        public ListLoaderAdapter(final String listName) {
            this._listName = listName;
        }

        @Override
        public final void load(final Element element) {
            final NodeList nodes = element.getChildNodes();
            for (final Element elem : new IterableElementList(nodes)) {
                if (elem.getNodeName().equalsIgnoreCase(this._listName)) {
                    this.loadElement(elem);
                }
            }
        }

        public abstract void loadElement(Element element);
    }

    private class RoomLoader extends ListLoaderAdapter {
        public RoomLoader() {
            super("Room");
        }

        @Override
        public void loadElement(final Element element) {
            final String name = element.getAttribute("Name");
            final int locX = Integer.valueOf(element.getAttribute("LocX"));
            final int locY = Integer.valueOf(element.getAttribute("LocY"));
            final int mapId = Integer.valueOf(element.getAttribute("MapId"));
            ROOMS.put(name.toLowerCase(), new L1Location(locX, locY, mapId));
        }
    }

    private static Logger _log = Logger.getLogger(GMCommandsConfig.class
            .getName());

    private static Map<String, ConfigLoader> _loaders = Maps.newMap();
    static {
        final GMCommandsConfig instance = new GMCommandsConfig();
        _loaders.put("roomlist", instance.new RoomLoader());
        _loaders.put("itemsetlist", instance.new ItemSetLoader());
    }

    public static Map<String, L1Location> ROOMS = Maps.newMap();

    public static Map<String, List<L1ItemSetItem>> ITEM_SETS = Maps.newMap();

    public static void load() {
        try {
            final Document doc = loadXml("./data/xml/GmCommands/GMCommands.xml");
            final NodeList nodes = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                final ConfigLoader loader = _loaders.get(nodes.item(i)
                        .getNodeName().toLowerCase());
                if (loader != null) {
                    loader.load((Element) nodes.item(i));
                }
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, "读取 GMCommands.xml 失败", e);
        }
    }

    private static Document loadXml(final String file)
            throws ParserConfigurationException, SAXException, IOException {
        final DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        return builder.parse(file);
    }
}
