package com.lineage.server.model.npc;

import com.lineage.server.model.L1Character;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * NPC接口：执行对话动作 - 功能并未全部实装 待需要时再实装(参考薇薇)
 * 
 * @author jrwz
 */
public interface NpcExecutor {

    /**
     * NPC受到攻击(4)<BR>
     * 任务NPC作为抵达目标检查的方法
     * 
     * @param pc
     * @param npc
     */
    public abstract void attack(final L1PcInstance pc, final L1NpcInstance npc);

    /**
     * NPC死亡(8)<BR>
     * 任务NPC作为给予任务道具的判断
     * 
     * @param lastAttacker
     *            攻击者
     * @param npc
     */
    public abstract L1PcInstance death(final L1Character lastAttacker,
            final L1NpcInstance npc);

    /**
     * NPC对话动作 (执行指令)(2)
     * 
     * @param pc
     *            角色
     * @param npc
     *            NPC
     * @param s
     *            信息
     * @param objid
     *            对象
     */
    public abstract void npcTalkAction(final L1PcInstance pc,
            final L1NpcInstance npc, final String s, final int objid);

    /**
     * NPC对话视窗 (触发条件)(1)
     * 
     * @param pc
     *            角色
     * @param npc
     *            NPC
     */
    public abstract void npcTalkReturn(final L1PcInstance pc,
            final L1NpcInstance npc);

    /**
     * NPC召唤(32)
     * 
     * @param mode
     */
    public abstract void spawn(final L1NpcInstance npc);

    /**
     * 类型 <br>
     * 1：对话视窗 <br>
     * 2：对话动作 <br>
     * 3：包含1-2 <br>
     * <h1>以下未实装<br>
     * <br>
     * 4:NPC受到攻击<BR>
     * 8:NPC死亡<BR>
     * 16:NPC工作时间<BR>
     * 32:NPC召唤<BR>
     */
    public abstract int type();

    /**
     * NPC工作执行
     * 
     * @param mode
     */
    public abstract void work(final L1NpcInstance npc);

    /**
     * NPC工作(16)<BR>
     * 工作重复时间(秒)
     */
    public abstract int workTime();
}
