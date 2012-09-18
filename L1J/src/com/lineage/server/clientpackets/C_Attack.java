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
package com.lineage.server.clientpackets;

import static com.lineage.server.model.Instance.L1PcInstance.REGENSTATE_ATTACK;

import com.lineage.Config;
import com.lineage.server.ClientThread;
import com.lineage.server.model.AcceleratorChecker;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理客戶端传来攻击的封包
 */
public class C_Attack extends ClientBasePacket {

    public C_Attack(final byte[] decrypt, final ClientThread client) {

        // 载入资料
        super(decrypt);
        final int targetId = this.readD(); // 目标ID
        final int x = this.readH(); // X坐标
        final int y = this.readH(); // Y坐标

        // 取得在线角色
        final L1PcInstance pc = client.getActiveChar();

        // 角色为空
        if (pc == null) {
            return;
        }

        if (pc.isGhost() || pc.isDead() || pc.isTeleport() || pc.isParalyzed()
                || pc.isSleeped()) {
            return;
        }

        // 取得目标
        final L1Object target = L1World.getInstance().findObject(targetId);

        // 确认是否可以攻击
        // 是否超重
        if (pc.getInventory().getWeight242() >= 197) {
            pc.sendPackets(new S_ServerMessage(110)); // \f1当负重过重的时候，无法战斗。
            return;
        }

        /*
         * // 什么都不能做的状态 if (CheckUtil.check(client)) { return; }
         */

        // 什么都不能做的状态
        if (pc.CanNotDoAnything()) {
            return;
        }

        // 开个人商店中
        if (pc.isPrivateShop()) {
            return;
        }

        // 是否隐形
        if (pc.isInvisble()) {
            return;
        }

        // 是否在隐形解除的延迟中
        if (pc.isInvisDelay()) {
            return;
        }

        // PC
        if (target instanceof L1Character) {
            // 如果目标距离玩家太远(外挂)
            if ((target.getMapId() != pc.getMapId())
                    || (pc.getLocation().getLineDistance(target.getLocation()) > 20D)) {
                return;
            }
        }

        // NPC
        if (target instanceof L1NpcInstance) {
            final int hiddenStatus = ((L1NpcInstance) target).getHiddenStatus();
            // 如果目标躲到土里面，或是飞起來了
            if ((hiddenStatus == L1NpcInstance.HIDDEN_STATUS_SINK)
                    || (hiddenStatus == L1NpcInstance.HIDDEN_STATUS_FLY)) {
                return;
            }
        }

        // 是否要检查攻击的间隔
        if (Config.CHECK_ATTACK_INTERVAL) {
            final int result;
            result = pc.getAcceleratorChecker().checkInterval(
                    AcceleratorChecker.ACT_TYPE.ATTACK);
            if (result == AcceleratorChecker.R_DISPOSED) {
                return;
            }
        }

        pc.delAbsoluteBarrier(); // 取消绝对屏障
        pc.delMeditation(); // 取消冥想效果
        pc.delInvis(); // 解除隐形状态
        pc.setRegenState(REGENSTATE_ATTACK); // 设置恢复状态

        if ((target != null) && !((L1Character) target).isDead()) {
            target.onAction(pc);
        } else { // 目标为空或死亡
            final L1Character cha = new L1Character();
            cha.setId(targetId);
            cha.setX(x);
            cha.setY(y);
            final L1Attack atk = new L1Attack(pc, cha);
            atk.actionPc();
        }
    }
}
