package com.lineage.server.model.item.etcitem.poly;

import static com.lineage.server.model.skill.L1SkillId.SHAPE_CHANGE;

import com.lineage.server.datatables.PolyTable;
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.Random;

/**
 * 福利变形药水 - 49308
 * 
 * @author jrwz
 */
public class PolyScroll_Welfare implements ItemExecutor {

    public static ItemExecutor get() {
        return new PolyScroll_Welfare();
    }

    private PolyScroll_Welfare() {
    }

    /**
     * 道具执行
     * 
     * @param data
     *            参数
     * @param pc
     *            对象
     * @param item
     *            道具
     */
    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        // 取回字符串
        final String text = pc.getText();
        // 清空字符串
        pc.setText(null);
        // 变身时间随机
        int time = 0;
        time = Random.nextInt(2401, 4800);

        final L1PolyMorph poly = PolyTable.getInstance().getTemplate(text);

        if ((poly != null) || text.equals("")) {
            if (text.equals("")) {
                pc.removeSkillEffect(SHAPE_CHANGE);
            } else if ((poly.getMinLevel() <= pc.getLevel()) || pc.isGm()) {
                L1PolyMorph.doPoly(pc, poly.getPolyId(), time,
                        L1PolyMorph.MORPH_BY_ITEMMAGIC);
            }
            pc.getInventory().removeItem(item, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(181)); // \f1无法变成你指定的怪物。
        }
    }
}
