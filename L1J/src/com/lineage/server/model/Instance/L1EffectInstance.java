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
package com.lineage.server.model.Instance;

import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_BALANCE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_IGNITION_TO_ALLY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_IGNITION_TO_ENEMY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_QUAKE_TO_ALLY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_QUAKE_TO_ENEMY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_SHOCK_TO_ALLY;
import static com.lineage.server.model.skill.L1SkillId.STATUS_CUBE_SHOCK_TO_ENEMY;

import com.lineage.server.ActionCodes;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.WarTimeController;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Cube;
import com.lineage.server.model.L1Magic;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.poison.L1DamagePoison;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.templates.L1Npc;

/**
 * 效果控制项
 */
public class L1EffectInstance extends L1NpcInstance {

    class CubeTimer implements Runnable {
        private final L1EffectInstance _effect;

        public CubeTimer(final L1EffectInstance effect) {
            this._effect = effect;
        }

        @Override
        public void run() {
            while (!L1EffectInstance.this._destroyed) {
                try {
                    for (final L1Object objects : L1World.getInstance()
                            .getVisibleObjects(this._effect, 3)) {
                        if (objects instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) objects;
                            if (pc.isDead()) {
                                continue;
                            }
                            final L1PcInstance user = L1EffectInstance.this
                                    .getUser(); // Cube使用者
                            if (pc.getId() == user.getId()) {
                                L1EffectInstance.this.cubeToAlly(pc,
                                        this._effect);
                                continue;
                            }
                            if ((pc.getClanid() != 0)
                                    && (user.getClanid() == pc.getClanid())) {
                                L1EffectInstance.this.cubeToAlly(pc,
                                        this._effect);
                                continue;
                            }
                            if (pc.isInParty() && pc.getParty().isMember(user)) {
                                L1EffectInstance.this.cubeToAlly(pc,
                                        this._effect);
                                continue;
                            }
                            if (pc.getZoneType() == 1) { // セーフティーゾーンでは戦争中を除き敵には無効
                                boolean isNowWar = false;
                                final int castleId = L1CastleLocation
                                        .getCastleIdByArea(pc);
                                if (castleId > 0) {
                                    isNowWar = WarTimeController.getInstance()
                                            .isNowWar(castleId);
                                }
                                if (!isNowWar) {
                                    continue;
                                }
                                L1EffectInstance.this.cubeToEnemy(pc,
                                        this._effect);
                            } else {
                                L1EffectInstance.this.cubeToEnemy(pc,
                                        this._effect);
                            }
                        } else if (objects instanceof L1MonsterInstance) {
                            final L1MonsterInstance mob = (L1MonsterInstance) objects;
                            if (mob.isDead()) {
                                continue;
                            }
                            L1EffectInstance.this
                                    .cubeToEnemy(mob, this._effect);
                        }
                    }
                    Thread.sleep(CUBE_INTERVAL);
                } catch (final InterruptedException ignore) {
                    // ignore
                }
            }
        }
    }

    class FwDamageTimer implements Runnable {
        private final L1EffectInstance _effect;

        public FwDamageTimer(final L1EffectInstance effect) {
            this._effect = effect;
        }

        @Override
        public void run() {
            while (!L1EffectInstance.this._destroyed) {
                try {
                    for (final L1Object objects : L1World.getInstance()
                            .getVisibleObjects(this._effect, 0)) {
                        if (objects instanceof L1PcInstance) {
                            final L1PcInstance pc = (L1PcInstance) objects;
                            if (pc.isDead()) {
                                continue;
                            }
                            if (pc.getZoneType() == 1) {
                                boolean isNowWar = false;
                                final int castleId = L1CastleLocation
                                        .getCastleIdByArea(pc);
                                if (castleId > 0) {
                                    isNowWar = WarTimeController.getInstance()
                                            .isNowWar(castleId);
                                }
                                if (!isNowWar) {
                                    continue;
                                }
                            }
                            final L1Magic magic = new L1Magic(this._effect, pc);
                            final int damage = magic.calcPcFireWallDamage();
                            if (damage == 0) {
                                continue;
                            }
                            pc.sendPackets(new S_DoActionGFX(pc.getId(),
                                    ActionCodes.ACTION_Damage));
                            pc.broadcastPacket(new S_DoActionGFX(pc.getId(),
                                    ActionCodes.ACTION_Damage));
                            pc.receiveDamage(this._effect, damage, false);
                        } else if (objects instanceof L1MonsterInstance) {
                            final L1MonsterInstance mob = (L1MonsterInstance) objects;
                            if (mob.isDead()) {
                                continue;
                            }
                            final L1Magic magic = new L1Magic(this._effect, mob);
                            final int damage = magic.calcNpcFireWallDamage();
                            if (damage == 0) {
                                continue;
                            }
                            mob.broadcastPacket(new S_DoActionGFX(mob.getId(),
                                    ActionCodes.ACTION_Damage));
                            mob.receiveDamage(this._effect, damage);
                        }
                    }
                    Thread.sleep(FW_DAMAGE_INTERVAL);
                } catch (final InterruptedException ignore) {
                    // ignore
                }
            }
        }
    }

    class PoisonTimer implements Runnable {
        private final L1EffectInstance _effect;

        public PoisonTimer(final L1EffectInstance effect) {
            this._effect = effect;
        }

        @Override
        public void run() {
            while (!L1EffectInstance.this._destroyed) {
                try {
                    for (final L1Object objects : L1World.getInstance()
                            .getVisibleObjects(this._effect, 0)) {
                        if (!(objects instanceof L1MonsterInstance)) {
                            final L1Character cha = (L1Character) objects;
                            L1DamagePoison.doInfection(this._effect, cha, 3000,
                                    20);
                        }
                    }
                    Thread.sleep(POISON_INTERVAL);
                } catch (final InterruptedException ignore) {
                    // ignore
                }
            }
        }
    }

    private static final long serialVersionUID = 1L;

    private static final int FW_DAMAGE_INTERVAL = 1000;

    private static final int CUBE_INTERVAL = 500; // キューブ範囲内に居るキャラクターをチェックする間隔

    private static final int CUBE_TIME = 8000; // 効果時間8秒?

    private static final int POISON_INTERVAL = 1000;

    private L1PcInstance _pc;

    private int _skillId;

    public L1EffectInstance(final L1Npc template) {
        super(template);

        final int npcId = this.getNpcTemplate().get_npcId();
        if (npcId == 81157) { // FW
            GeneralThreadPool.getInstance()
                    .schedule(new FwDamageTimer(this), 0);
        } else if ((npcId == 80149 // 立方：燃烧
                )
                || (npcId == 80150 // 立方：地裂
                ) || (npcId == 80151 // 立方：冲击
                ) || (npcId == 80152) // 立方：和谐
        ) {
            GeneralThreadPool.getInstance().schedule(new CubeTimer(this), 0);
        } else if (npcId == 93002) { // 毒雾-中毒效果
            GeneralThreadPool.getInstance().schedule(new PoisonTimer(this), 0);
        }
    }

    void cubeToAlly(final L1Character cha, final L1Character effect) {
        final int npcId = this.getNpcTemplate().get_npcId();
        final int castGfx = SkillsTable.getInstance()
                .getTemplate(this.getSkillId()).getCastGfx();
        L1PcInstance pc = null;

        if (npcId == 80149) { // 立方：燃烧
            if (!cha.hasSkillEffect(STATUS_CUBE_IGNITION_TO_ALLY)) {
                cha.addFire(30);
                if (cha instanceof L1PcInstance) {
                    pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                    pc.sendPackets(new S_SkillSound(pc.getId(), castGfx));
                }
                cha.broadcastPacket(new S_SkillSound(cha.getId(), castGfx));
                cha.setSkillEffect(STATUS_CUBE_IGNITION_TO_ALLY, CUBE_TIME);
            }
        } else if (npcId == 80150) { // 立方：地裂
            if (!cha.hasSkillEffect(STATUS_CUBE_QUAKE_TO_ALLY)) {
                cha.addEarth(30);
                if (cha instanceof L1PcInstance) {
                    pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                    pc.sendPackets(new S_SkillSound(pc.getId(), castGfx));
                }
                cha.broadcastPacket(new S_SkillSound(cha.getId(), castGfx));
                cha.setSkillEffect(STATUS_CUBE_QUAKE_TO_ALLY, CUBE_TIME);
            }
        } else if (npcId == 80151) { // 立方：冲击
            if (!cha.hasSkillEffect(STATUS_CUBE_SHOCK_TO_ALLY)) {
                cha.addWind(30);
                if (cha instanceof L1PcInstance) {
                    pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_OwnCharAttrDef(pc));
                    pc.sendPackets(new S_SkillSound(pc.getId(), castGfx));
                }
                cha.broadcastPacket(new S_SkillSound(cha.getId(), castGfx));
                cha.setSkillEffect(STATUS_CUBE_SHOCK_TO_ALLY, CUBE_TIME);
            }
        } else if (npcId == 80152) { // 立方：和谐
            if (!cha.hasSkillEffect(STATUS_CUBE_BALANCE)) {
                if (cha instanceof L1PcInstance) {
                    pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillSound(pc.getId(), castGfx));
                }
                cha.broadcastPacket(new S_SkillSound(cha.getId(), castGfx));
                cha.setSkillEffect(STATUS_CUBE_BALANCE, CUBE_TIME);
                final L1Cube cube = new L1Cube(effect, cha, STATUS_CUBE_BALANCE);
                cube.begin();
            }
        }
    }

    void cubeToEnemy(final L1Character cha, final L1Character effect) {
        final int npcId = this.getNpcTemplate().get_npcId();
        final int castGfx2 = SkillsTable.getInstance()
                .getTemplate(this.getSkillId()).getCastGfx2();
        L1PcInstance pc = null;
        if (npcId == 80149) { // 立方：燃烧
            if (!cha.hasSkillEffect(STATUS_CUBE_IGNITION_TO_ENEMY)) {
                if (cha instanceof L1PcInstance) {
                    pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillSound(pc.getId(), castGfx2));
                }
                cha.broadcastPacket(new S_SkillSound(cha.getId(), castGfx2));
                cha.setSkillEffect(STATUS_CUBE_IGNITION_TO_ENEMY, CUBE_TIME);
                final L1Cube cube = new L1Cube(effect, cha,
                        STATUS_CUBE_IGNITION_TO_ENEMY);
                cube.begin();
            }
        } else if (npcId == 80150) { // 立方：地裂
            if (!cha.hasSkillEffect(STATUS_CUBE_QUAKE_TO_ENEMY)) {
                if (cha instanceof L1PcInstance) {
                    pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillSound(pc.getId(), castGfx2));
                }
                cha.broadcastPacket(new S_SkillSound(cha.getId(), castGfx2));
                cha.setSkillEffect(STATUS_CUBE_QUAKE_TO_ENEMY, CUBE_TIME);
                final L1Cube cube = new L1Cube(effect, cha,
                        STATUS_CUBE_QUAKE_TO_ENEMY);
                cube.begin();
            }
        } else if (npcId == 80151) { // 立方：冲击
            if (!cha.hasSkillEffect(STATUS_CUBE_SHOCK_TO_ENEMY)) {
                if (cha instanceof L1PcInstance) {
                    pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillSound(pc.getId(), castGfx2));
                }
                cha.broadcastPacket(new S_SkillSound(cha.getId(), castGfx2));
                cha.setSkillEffect(STATUS_CUBE_SHOCK_TO_ENEMY, CUBE_TIME);
                final L1Cube cube = new L1Cube(effect, cha,
                        STATUS_CUBE_SHOCK_TO_ENEMY);
                cube.begin();
            }
        } else if (npcId == 80152) { // 立方：和谐
            if (!cha.hasSkillEffect(STATUS_CUBE_BALANCE)) {
                if (cha instanceof L1PcInstance) {
                    pc = (L1PcInstance) cha;
                    pc.sendPackets(new S_SkillSound(pc.getId(), castGfx2));
                }
                cha.broadcastPacket(new S_SkillSound(cha.getId(), castGfx2));
                cha.setSkillEffect(STATUS_CUBE_BALANCE, CUBE_TIME);
                final L1Cube cube = new L1Cube(effect, cha, STATUS_CUBE_BALANCE);
                cube.begin();
            }
        }
    }

    @Override
    public void deleteMe() {
        this._destroyed = true;
        if (this.getInventory() != null) {
            this.getInventory().clearItems();
        }
        this.allTargetClear();
        this._master = null;
        L1World.getInstance().removeVisibleObject(this);
        L1World.getInstance().removeObject(this);
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            pc.removeKnownObject(this);
            pc.sendPackets(new S_RemoveObject(this));
        }
        this.removeAllKnownObjects();
    }

    public int getSkillId() {
        return this._skillId;
    }

    public L1PcInstance getUser() {
        return this._pc;
    }

    @Override
    public void onAction(final L1PcInstance pc) {
    }

    public void setSkillId(final int i) {
        this._skillId = i;
    }

    public void setUser(final L1PcInstance pc) {
        this._pc = pc;
    }

}
