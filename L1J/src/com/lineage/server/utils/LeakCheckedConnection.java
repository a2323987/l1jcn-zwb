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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.utils.collections.Maps;

public class LeakCheckedConnection {
    private class ConnectionHandler implements
            java.lang.reflect.InvocationHandler {
        public ConnectionHandler() {
            // TODO Auto-generated constructor stub
        }

        @Override
        public Object invoke(final Object proxy, final Method method,
                final Object[] args) throws Throwable {
            if (method.getName().equals("close")) {
                LeakCheckedConnection.this.closeAll();
            }
            Object o = LeakCheckedConnection.this.send(
                    LeakCheckedConnection.this._con, method, args);
            if (o instanceof Statement) {
                LeakCheckedConnection.this._openedStatements.put((Statement) o,
                        new Throwable());
                o = new Delegate(o, PreparedStatement.class)._delegateProxy;
            }
            return o;
        }
    }

    private class Delegate implements InvocationHandler {
        final Object _delegateProxy;

        private final Object _original;

        Delegate(final Object o, final Class<?> c) {
            this._original = o;
            this._delegateProxy = Proxy.newProxyInstance(c.getClassLoader(),
                    new Class[] { c }, this);
        }

        @Override
        public Object invoke(final Object proxy, final Method method,
                final Object[] args) throws Throwable {
            if (method.getName().equals("close")) {
                LeakCheckedConnection.this.remove(this._original);
            }
            Object o = LeakCheckedConnection.this.send(this._original, method,
                    args);
            if (o instanceof ResultSet) {
                LeakCheckedConnection.this._openedResultSets.put((ResultSet) o,
                        new Throwable());
                o = new Delegate(o, ResultSet.class)._delegateProxy;
            }
            return o;
        }
    }

    private static final Logger _log = Logger
            .getLogger(LeakCheckedConnection.class.getName());

    public static Connection create(final Connection con) {
        return (Connection) new LeakCheckedConnection(con)._proxy;
    }

    final Connection _con;

    final Map<Statement, Throwable> _openedStatements = Maps.newMap();

    final Map<ResultSet, Throwable> _openedResultSets = Maps.newMap();

    private final Object _proxy;

    private LeakCheckedConnection(final Connection con) {
        this._con = con;
        this._proxy = Proxy.newProxyInstance(Connection.class.getClassLoader(),
                new Class[] { Connection.class }, new ConnectionHandler());
    }

    void closeAll() {
        if (!this._openedResultSets.isEmpty()) {
            for (final Throwable t : this._openedResultSets.values()) {
                _log.log(Level.WARNING, "Leaked ResultSets detected.", t);
            }
        }
        if (!this._openedStatements.isEmpty()) {
            for (final Throwable t : this._openedStatements.values()) {
                _log.log(Level.WARNING, "Leaked Statement detected.", t);
            }
        }
        for (final ResultSet rs : this._openedResultSets.keySet()) {
            SQLUtil.close(rs);
        }
        for (final Statement ps : this._openedStatements.keySet()) {
            SQLUtil.close(ps);
        }
    }

    void remove(final Object o) {
        if (o instanceof ResultSet) {
            this._openedResultSets.remove(o);
        } else if (o instanceof Statement) {
            this._openedStatements.remove(o);
        } else {
            throw new IllegalArgumentException("bad class:" + o);
        }
    }

    Object send(final Object o, final Method m, final Object[] args)
            throws Throwable {
        try {
            return m.invoke(o, args);
        } catch (final InvocationTargetException e) {
            if (e.getCause() != null) {
                throw e.getCause();
            }
            throw e;
        }
    }
}
