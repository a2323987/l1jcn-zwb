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
package com.lineage.server.utils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javolution.util.FastTable;

public class Lists {
    public static class SerializableArrayList<E extends Object> extends
            FastTable<E> {
        private static final long serialVersionUID = 1L;

        public SerializableArrayList() {
            super();
        }

        public SerializableArrayList(final int capacity) {
            super(capacity);
        }
    }

    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<E>();
    }

    public static <E> ArrayList<E> newArrayList(final Collection<? extends E> c) {
        return new ArrayList<E>(c);
    }

    public static <E> List<E> newConcurrentList() {
        return new CopyOnWriteArrayList<E>();
    }

    public static <E> List<E> newConcurrentList(final List<E> from) {
        return new CopyOnWriteArrayList<E>(from);
    }

    public static <E> List<E> newList() {
        return new FastTable<E>();
    }

    public static <E> List<E> newList(final Collection<E> from) {
        return new FastTable<E>(from);
    }

    public static <E> List<E> newList(final int n) {
        return new FastTable<E>(n);
    }

    public static <E> List<E> newList(final Set<E> from) {
        return new FastTable<E>(from);
    }

    public static <E> List<E> newSerializableList() {
        return new SerializableArrayList<E>();
    }

    public static <E> List<E> newSerializableList(final int n) {
        return new SerializableArrayList<E>(n);
    }
}
