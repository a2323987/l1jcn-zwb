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
package com.lineage.server.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class IterableElementList implements Iterable<Element> {
    private class MyIterator implements Iterator<Element> {
        private final Iterator<Node> _itr;
        private Element _next = null;

        public MyIterator(final Iterator<Node> itr) {
            this._itr = itr;
            this.updateNextElement();
        }

        @Override
        public boolean hasNext() {
            return this._next != null;
        }

        @Override
        public Element next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            final Element result = this._next;
            this.updateNextElement();
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void updateNextElement() {
            while (this._itr.hasNext()) {
                final Node node = this._itr.next();
                if (node instanceof Element) {
                    this._next = (Element) node;
                    return;
                }
            }
            this._next = null;
        }
    }

    IterableNodeList _list;

    public IterableElementList(final NodeList list) {
        this._list = new IterableNodeList(list);
    }

    @Override
    public Iterator<Element> iterator() {
        return new MyIterator(this._list.iterator());
    }

}
