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
package com.lineage.server.model;

import static com.lineage.server.model.skill.L1SkillId.IMMUNE_TO_HARM;

import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.ActionCodes;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_EffectLocation;
import com.lineage.server.utils.Random;

/**
 * 
 */
public class L1Chaser extends TimerTask {

    private static Logger _log = Logger.getLogger(L1Chaser.class.getName());

    private ScheduledFuture<?> _future = null;
    private int _timeCounter = 0;
    private final int _attr;
    private final int _gfxid;
    private final L1PcInstance _pc;
    private final L1Character _cha;

    public L1Chaser(final L1PcInstance pc, final L1Character cha,
            final int attr, final int gfxid) {
        this._cha = cha;
        this._pc = pc;
        this._attr = attr;
        this._gfxid = gfxid;
    }

    public void attack() {
        double damage = this.getDamage(this._pc, this._cha);
        if ((this._cha.getCurrentHp() - (int) damage <= 0)
                && (this._cha.getCurrentHp() != 1)) {
            damage = this._cha.getCurrentHp() - 1;
        } else if (this._cha.getCurrentHp() == 1) {
            damage = 0;
        }
        S_EffectLocation packet = new S_EffectLocation(this._cha.getX(),
                this._cha.getY(), this._gfxid);
        if (this._pc.getWeapon() == null) { // 修正空手会出错的问题
            damage = 0;
        } else if ((this._pc.getWeapon().getItem().getItemId() == 265)
                || (this._pc.getWeapon().getItem().getItemId() == 266)
                || (this._pc.getWeapon().getItem().getItemId() == 267)
                || (this._pc.getWeapon().getItem().getItemId() == 268)) {
            packet = new S_EffectLocation(this._cha.getX(), this._cha.getY(),
                    7025);
        } else if ((this._pc.getWeapon().getItem().getItemId() == 276)
                || (this._pc.getWeapon().getItem().getItemId() == 277)) {
            packet = new S_EffectLocation(this._cha.getX(), this._cha.getY(),
                    7224);
        } else if ((this._pc.getWeapon().getItem().getItemId() == 304)
                || (this._pc.getWeapon().getItem().getItemId() == 307)
                || (this._pc.getWeapon().getItem().getItemId() == 308)) {
            packet = new S_EffectLocation(this._cha.getX(), this._cha.getY(),
                    8150);
        } else if ((this._pc.getWeapon().getItem().getItemId() == 305)
                || (this._pc.getWeapon().getItem().getItemId() == 306)
                || (this._pc.getWeapon().getItem().getItemId() == 309)) {
            packet = new S_EffectLocation(this._cha.getX(), this._cha.getY(),
                    8152);
        } else { // 更换为其他武器 附加特效伤害归零
            damage = 0;
        }
        this._pc.sendPackets(packet);
        this._pc.broadcastPacket(packet);
        if (this._cha instanceof L1PcInstance) {
            final L1PcInstance pc = (L1PcInstance) this._cha;
            pc.sendPackets(new S_DoActionGFX(pc.getId(),
                    ActionCodes.ACTION_Damage));
            pc.broadcastPacket(new S_DoActionGFX(pc.getId(),
                    ActionCodes.ACTION_Damage));
            pc.receiveDamage(this._pc, damage, false);
        } else if (this._cha instanceof L1NpcInstance) {
            final L1NpcInstance npc = (L1NpcInstance) this._cha;
            npc.broadcastPacket(new S_DoActionGFX(npc.getId(),
                    ActionCodes.ACTION_Damage));
            npc.receiveDamage(this._pc, (int) damage);
        }
    }

    public void begin() {
        // 效果时间持续8秒、事实上，在考虑每4秒技能效果处理时间只出现一次
        // 设置0.9秒的启动时间
        this._future = GeneralThreadPool.getInstance().scheduleAtFixedRate(
                this, 0, 1000);
    }

    public double getDamage(final L1PcInstance pc, final L1Character cha) {
        double dmg = 0;
        final int spByItem = pc.getSp() - pc.getTrueSp();
        final int intel = pc.getInt();
        final int charaIntelligence = pc.getInt() + spByItem - 12;
        double coefficientA = 1.0 + 3.0 / 32.0 * charaIntelligence;
        if (coefficientA < 1) {
            coefficientA = 1.0;
        }
        double coefficientB = 0;
        if (intel > 18) {
            coefficientB = (intel + 2.0) / intel;
        } else if (intel <= 12) {
            coefficientB = 12.0 * 0.065;
        } else {
            coefficientB = intel * 0.065;
        }
        double coefficientC = 0;
        if (intel <= 12) {
            coefficientC = 12;
        } else {
            coefficientC = intel;
        }
        dmg = (Random.nextInt(6) + 1 + 7) * coefficientA * coefficientB / 10.5
                * coefficientC * 2.0;
        dmg = L1WeaponSkill.calcDamageReduction(pc, cha, dmg, this._attr);
        if (cha.hasSkillEffect(IMMUNE_TO_HARM)) {
            dmg /= 2.0;
        }
        return dmg;
    }

    @Override
    public void run() {
        try {
            if ((this._cha == null) || this._cha.isDead()) {
                this.stop();
                return;
            }
            this.attack();
            this._timeCounter++;
            if (this._timeCounter >= 3) {
                this.stop();
                return;
            }
        } catch (final Throwable e) {
            _log.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }

    public void stop() {
        if (this._future != null) {
            this._future.cancel(false);
        }
    }

}
