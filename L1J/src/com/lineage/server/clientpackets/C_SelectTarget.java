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

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1MonsterInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;
import com.lineage.server.serverpackets.S_ServerMessage;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来选择目标的封包
 */
public class C_SelectTarget extends ClientBasePacket {

    private static final String C_SELECT_TARGET = "[C] C_SelectTarget";

    public C_SelectTarget(final byte abyte0[], final ClientThread clientthread)
            throws Exception {
        super(abyte0);

        final int petId = this.readD();
        this.readC();
        final int targetId = this.readD();

        final L1PetInstance pet = (L1PetInstance) L1World.getInstance()
                .findObject(petId);
        final L1Character target = (L1Character) L1World.getInstance()
                .findObject(targetId);

        if ((pet != null) && (target != null)) {
            // 目标为玩家
            if (target instanceof L1PcInstance) {
                final L1PcInstance pc = (L1PcInstance) target;
                // 目标在安区、攻击者在安区、NOPVP
                if ((pc.getZoneType() == 1) || (pet.getZoneType() == 1)
                        || (pc.checkNonPvP(pc, pet))) {
                    // 宠物主人
                    if (pet.getMaster() instanceof L1PcInstance) {
                        final L1PcInstance petMaster = (L1PcInstance) pet
                                .getMaster();
                        petMaster.sendPackets(new S_ServerMessage(328)); // 请选择正确的对象。
                    }
                    return;
                }
            }
            // 目标为宠物
            else if (target instanceof L1PetInstance) {
                final L1PetInstance targetPet = (L1PetInstance) target;
                // 目标在安区、攻击者在安区
                if ((targetPet.getZoneType() == 1) || (pet.getZoneType() == 1)) {
                    // 宠物主人
                    if (pet.getMaster() instanceof L1PcInstance) {
                        final L1PcInstance petMaster = (L1PcInstance) pet
                                .getMaster();
                        petMaster.sendPackets(new S_ServerMessage(328)); // 请选择正确的对象。
                    }
                    return;
                }
            }
            // 目标为召唤怪
            else if (target instanceof L1SummonInstance) {
                final L1SummonInstance targetSummon = (L1SummonInstance) target;
                // 目标在安区、攻击者在安区
                if ((targetSummon.getZoneType() == 1)
                        || (pet.getZoneType() == 1)) {
                    // 宠物主人
                    if (pet.getMaster() instanceof L1PcInstance) {
                        final L1PcInstance petMaster = (L1PcInstance) pet
                                .getMaster();
                        petMaster.sendPackets(new S_ServerMessage(328)); // 请选择正确的对象。
                    }
                    return;
                }
            }
            // 目标为怪物
            else if (target instanceof L1MonsterInstance) {
                final L1MonsterInstance mob = (L1MonsterInstance) target;
                // 特定状态下才可攻击
                if (pet.getMaster().isAttackMiss(pet.getMaster(),
                        mob.getNpcId())) {
                    if (pet.getMaster() instanceof L1PcInstance) {
                        final L1PcInstance petMaster = (L1PcInstance) pet
                                .getMaster();
                        petMaster.sendPackets(new S_ServerMessage(328)); // 请选择正确的对象。
                    }
                    return;
                }
            }
            pet.setMasterTarget(target);
        }
    }

    @Override
    public String getType() {
        return C_SELECT_TARGET;
    }
}
