package com.lineage.server.model.item;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.console.DataError;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 道具模组相关
 * 
 * @author jrwz
 */
public class CiteItemClass implements ItemClass {

    /** 提示信息 */
    private static final Log _log = LogFactory.getLog(CiteItemClass.class);

    /** ItemID执行类位置 */
    private static final Map<Integer, ItemExecutor> _classList = new HashMap<Integer, ItemExecutor>();

    private static ItemClass _instance;

    public static ItemClass getInstance() {
        if (_instance == null) {
            _instance = new CiteItemClass();
        }
        return _instance;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lineage.console.ItemClass#addList(int, java.lang.String, int)
     */
    @Override
    public void addList(final int itemid, final String className, final int mode) {

        // Class为0
        if (className.equals("0")) {
            return;
        }

        try {
            final StringBuilder stringBuilder = new StringBuilder();
            switch (mode) {
                case 0: // 道具
                    stringBuilder
                            .append("com.lineage.server.model.item.etcitem.");
                    break;

                case 1: // 武器
                    stringBuilder
                            .append("com.lineage.server.model.item.weapon.");
                    break;

                case 2: // 防具
                    stringBuilder
                            .append("com.lineage.server.model.item.armor.");
                    break;
            }
            stringBuilder.append(className);

            final Class<?> cls = Class.forName(stringBuilder.toString());
            final ItemExecutor exe = (ItemExecutor) cls.getMethod("get")
                    .invoke(null);

            _classList.put(new Integer(itemid), exe);

        } catch (final ClassNotFoundException e) {
            final String error = "道具Class档案 (" + className + ") 错误 (ItemId:"
                    + itemid + ")";
            _log.error(error);
            DataError.isError(_log, error, e);

        } catch (final IllegalArgumentException e) {
            _log.error(e.getLocalizedMessage(), e);

        } catch (final IllegalAccessException e) {
            _log.error(e.getLocalizedMessage(), e);

        } catch (final InvocationTargetException e) {
            _log.error(e.getLocalizedMessage(), e);

        } catch (final SecurityException e) {
            _log.error(e.getLocalizedMessage(), e);

        } catch (final NoSuchMethodException e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lineage.console.ItemClass#item(int[],
     * com.lineage.server.model.Instance.L1PcInstance,
     * com.lineage.server.model.Instance.L1ItemInstance)
     */
    @Override
    public void item(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        // 空角色
        if (pc == null) {
            return;
        }

        // 空道具
        if (item == null) {
            return;
        }

        try {
            // 取得Class执行位置
            final ItemExecutor exe = _classList.get(new Integer(item
                    .getItemId()));
            if (exe != null) {
                exe.execute(data, pc, item);
            }

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lineage.console.ItemClass#item_armor(boolean,
     * com.lineage.server.model.Instance.L1PcInstance,
     * com.lineage.server.model.Instance.L1ItemInstance)
     */
    @Override
    public void item_armor(final boolean equipped, final L1PcInstance pc,
            final L1ItemInstance item) {

        // 空角色
        if (pc == null) {
            return;
        }

        // 空道具
        if (item == null) {
            return;
        }

        try {
            // 取得Class执行位置
            final ItemExecutor exe = _classList.get(new Integer(item
                    .getItemId()));
            if (exe != null) {
                final int[] data = new int[1];
                data[0] = equipped ? 1 : 0;
                exe.execute(data, pc, item);
            }

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.lineage.console.ItemClass#item_weapon(boolean,
     * com.lineage.server.model.Instance.L1PcInstance,
     * com.lineage.server.model.Instance.L1ItemInstance)
     */
    @Override
    public void item_weapon(final boolean equipped, final L1PcInstance pc,
            final L1ItemInstance item) {

        // 空角色
        if (pc == null) {
            return;
        }

        // 空道具
        if (item == null) {
            return;
        }

        try {
            // 取得Class执行位置
            final ItemExecutor exe = _classList.get(new Integer(item
                    .getItemId()));
            if (exe != null) {
                final int[] data = new int[1];
                data[0] = equipped ? 1 : 0;
                exe.execute(data, pc, item);
            }

        } catch (final Exception e) {
            _log.error(e.getLocalizedMessage(), e);
        }
    }
}
