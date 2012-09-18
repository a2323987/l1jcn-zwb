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

import static com.lineage.server.model.skill.L1SkillId.ADDITIONAL_FIRE;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_2_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_1_2_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_4_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_2_4_S;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_5_N;
import static com.lineage.server.model.skill.L1SkillId.COOKING_3_5_S;
import static com.lineage.server.model.skill.L1SkillId.EXOTIC_VITALIZE;
import static com.lineage.server.model.skill.L1SkillId.STATUS_BLUE_POTION;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.types.Point;

/**
 * MP再生(恢复)
 */
public class MpRegeneration extends TimerTask {

    private static Logger _log = Logger.getLogger(MpRegeneration.class
            .getName());

    private final L1PcInstance _pc;

    private int _regenPoint = 0;

    private int _curPoint = 4;

    public MpRegeneration(final L1PcInstance pc) {
        this._pc = pc;
    }

    private boolean isOverWeight(final L1PcInstance pc) {
        // 体能激发状态、能量激发状态、
        // 不被视为过重。
        if (pc.hasSkillEffect(EXOTIC_VITALIZE)
                || pc.hasSkillEffect(ADDITIONAL_FIRE)) {
            return false;
        }

        return (120 <= pc.getInventory().getWeight242()) ? true : false;
    }

    public void regenMp() {
        int baseMpr = 1;
        int wis = this._pc.getWis();
        if ((wis == 15) || (wis == 16)) {
            baseMpr = 2;
        } else if (wis >= 17) {
            baseMpr = 3;
        }

        if (this._pc.hasSkillEffect(STATUS_BLUE_POTION)) { // 在使用蓝色药水
            if (wis < 11) { // WIS11未满MPR+1
                wis = 11;
            }
            baseMpr += wis - 10;
        }
        if (L1HouseLocation.isInHouse(this._pc.getX(), this._pc.getY(),
                this._pc.getMapId())) {
            baseMpr += 3;
        }
        if ((this._pc.getMapId() == 16384) || (this._pc.getMapId() == 16896)
                || (this._pc.getMapId() == 17408)
                || (this._pc.getMapId() == 17920)
                || (this._pc.getMapId() == 18432)
                || (this._pc.getMapId() == 18944)
                || (this._pc.getMapId() == 19968)
                || (this._pc.getMapId() == 19456)
                || (this._pc.getMapId() == 20480)
                || (this._pc.getMapId() == 20992)
                || (this._pc.getMapId() == 21504)
                || (this._pc.getMapId() == 22016)
                || (this._pc.getMapId() == 22528)
                || (this._pc.getMapId() == 23040)
                || (this._pc.getMapId() == 23552)
                || (this._pc.getMapId() == 24064)
                || (this._pc.getMapId() == 24576)
                || (this._pc.getMapId() == 25088)) { // 旅馆
            baseMpr += 3;
        }
        if ((this._pc.getLocation().isInScreen(new Point(33055, 32336))
                && (this._pc.getMapId() == 4) && this._pc.isElf())) {
            baseMpr += 3;
        }
        if (this._pc.hasSkillEffect(COOKING_1_2_N)
                || this._pc.hasSkillEffect(COOKING_1_2_S)) {
            baseMpr += 3;
        }
        if (this._pc.hasSkillEffect(COOKING_2_4_N)
                || this._pc.hasSkillEffect(COOKING_2_4_S)
                || this._pc.hasSkillEffect(COOKING_3_5_N)
                || this._pc.hasSkillEffect(COOKING_3_5_S)) {
            baseMpr += 2;
        }
        if (this._pc.getOriginalMpr() > 0) { // 原始的WIS MPR補正
            baseMpr += this._pc.getOriginalMpr();
        }

        int itemMpr = this._pc.getInventory().mpRegenPerTick();
        itemMpr += this._pc.getMpr();

        if ((this._pc.get_food() < 3) || this.isOverWeight(this._pc)) {
            baseMpr = 0;
            if (itemMpr > 0) {
                itemMpr = 0;
            }
        }
        final int mpr = baseMpr + itemMpr;
        int newMp = this._pc.getCurrentMp() + mpr;
        if (newMp < 0) {
            newMp = 0;
        }
        this._pc.setCurrentMp(newMp);
    }

    @Override
    public void run() {
        try {
            if (this._pc.isDead()) {
                return;
            }

            this._regenPoint += this._curPoint;
            this._curPoint = 4;

            if (64 <= this._regenPoint) {
                this._regenPoint = 0;
                this.regenMp();
            }
        } catch (final Throwable e) {
            _log.log(Level.WARNING, e.getLocalizedMessage(), e);
        }
    }

    public void setState(final int state) {
        if (this._curPoint < state) {
            return;
        }

        this._curPoint = state;
    }
}
