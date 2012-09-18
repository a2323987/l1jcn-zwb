package com.lineage.server.model.item.etcitem.potion;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;

/**
 * 返生药水 - 43000
 * 
 * @author jrwz
 */
public class BackRawPotion implements ItemExecutor {

    private static Logger _log = Logger
            .getLogger(BackRawPotion.class.getName());

    public static ItemExecutor get() {
        return new BackRawPotion();
    }

    private BackRawPotion() {
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

        if (pc.getLevel() < 99) {
            return;
        }

        try {
            pc.setExp(1); // 总经验值归1
            pc.resetLevel(); // 等级重算
            pc.setBonusStats(0); // 清空奖励属性点记录 (升级可以继续点奖励属性点)
            pc.getInventory().takeoffEquip(945); // 用来脱掉全身装备
            pc.sendPackets(new S_OwnCharStatus(pc)); // 更新角色属性与能力值
            pc.sendPackets(new S_ServerMessage(822)); // 你感受到体内深处产生一股不明力量。
            pc.sendPackets(new S_SkillSound(pc.getId(), 2568)); // 风龙降临动画特效。(自己)
            pc.broadcastPacket(new S_SkillSound(pc.getId(), 2568)); // 风龙降临动画特效。(可见范围的其他角色)
            pc.save(); // 将玩家资料储存到资料库中
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            throw new Error("返生药水错误" + " File.");
        }

        // 处理新手保护系统(遭遇的守护)状态资料的变动
        pc.checkNoviceType();

        // 删除道具
        pc.getInventory().removeItem(item, 1);
    }
}
