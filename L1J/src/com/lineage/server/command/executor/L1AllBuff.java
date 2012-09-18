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
package com.lineage.server.command.executor;

import static com.lineage.server.model.skill.L1SkillId.ADDITIONAL_FIRE;
import static com.lineage.server.model.skill.L1SkillId.ADVANCE_SPIRIT;
import static com.lineage.server.model.skill.L1SkillId.AQUA_PROTECTER;
import static com.lineage.server.model.skill.L1SkillId.BERSERKERS;
import static com.lineage.server.model.skill.L1SkillId.BLESS_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.BOUNCE_ATTACK;
import static com.lineage.server.model.skill.L1SkillId.BRAVE_AURA;
import static com.lineage.server.model.skill.L1SkillId.BURNING_SPIRIT;
import static com.lineage.server.model.skill.L1SkillId.BURNING_WEAPON;
import static com.lineage.server.model.skill.L1SkillId.CLEAR_MIND;
import static com.lineage.server.model.skill.L1SkillId.DECREASE_WEIGHT;
import static com.lineage.server.model.skill.L1SkillId.DOUBLE_BRAKE;
import static com.lineage.server.model.skill.L1SkillId.DRESS_EVASION;
import static com.lineage.server.model.skill.L1SkillId.ELEMENTAL_FIRE;
import static com.lineage.server.model.skill.L1SkillId.ELEMENTAL_PROTECTION;
import static com.lineage.server.model.skill.L1SkillId.ENCHANT_VENOM;
import static com.lineage.server.model.skill.L1SkillId.EXOTIC_VITALIZE;
import static com.lineage.server.model.skill.L1SkillId.GLOWING_AURA;
import static com.lineage.server.model.skill.L1SkillId.IMMUNE_TO_HARM;
import static com.lineage.server.model.skill.L1SkillId.IRON_SKIN;
import static com.lineage.server.model.skill.L1SkillId.LIGHT;
import static com.lineage.server.model.skill.L1SkillId.MEDITATION;
import static com.lineage.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_DEX;
import static com.lineage.server.model.skill.L1SkillId.PHYSICAL_ENCHANT_STR;
import static com.lineage.server.model.skill.L1SkillId.REDUCTION_ARMOR;
import static com.lineage.server.model.skill.L1SkillId.RESIST_MAGIC;
import static com.lineage.server.model.skill.L1SkillId.SOLID_CARRIAGE;
import static com.lineage.server.model.skill.L1SkillId.SOUL_OF_FLAME;
import static com.lineage.server.model.skill.L1SkillId.UNCANNY_DODGE;
import static com.lineage.server.model.skill.L1SkillId.VENOM_RESIST;
import static com.lineage.server.model.skill.L1SkillId.WATER_LIFE;

import java.util.StringTokenizer;

import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_1;
import com.lineage.server.model.item.etcitem.UseSpeedPotion_2_Brave;
import com.lineage.server.model.skill.L1SkillUse;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SystemMessage;
import com.lineage.server.templates.L1Skills;

/**
 * GM指令：给对象所有魔法
 */
public class L1AllBuff implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1AllBuff();
    }

    private L1AllBuff() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        final int[] allBuffSkill = { LIGHT, DECREASE_WEIGHT,
                PHYSICAL_ENCHANT_DEX, MEDITATION, PHYSICAL_ENCHANT_STR,
                BLESS_WEAPON, BERSERKERS, IMMUNE_TO_HARM, ADVANCE_SPIRIT,
                REDUCTION_ARMOR, BOUNCE_ATTACK, SOLID_CARRIAGE, ENCHANT_VENOM,
                BURNING_SPIRIT, VENOM_RESIST, DOUBLE_BRAKE, UNCANNY_DODGE,
                DRESS_EVASION, GLOWING_AURA, BRAVE_AURA, RESIST_MAGIC,
                CLEAR_MIND, ELEMENTAL_PROTECTION, AQUA_PROTECTER,
                BURNING_WEAPON, IRON_SKIN, EXOTIC_VITALIZE, WATER_LIFE,
                ELEMENTAL_FIRE, SOUL_OF_FLAME, ADDITIONAL_FIRE };
        try {
            final StringTokenizer st = new StringTokenizer(arg);
            final String name = st.nextToken();
            final L1PcInstance target = L1World.getInstance().getPlayer(name);
            if (target == null) {
                pc.sendPackets(new S_ServerMessage(73, name)); // \f1%0%d 不在线上。
                return;
            }

            UseSpeedPotion_1.get().useItem(target, null, 0, 0, 3600, 191);
            UseSpeedPotion_2_Brave.get().useItem(target, null, 0, 0, 3600, 751);
            L1PolyMorph.doPoly(target, 5641, 7200, L1PolyMorph.MORPH_BY_GM);
            for (final int element : allBuffSkill) {
                final L1Skills skill = SkillsTable.getInstance().getTemplate(
                        element);
                new L1SkillUse().handleCommands(target, element,
                        target.getId(), target.getX(), target.getY(), null,
                        skill.getBuffDuration() * 1000, L1SkillUse.TYPE_GMBUFF);
            }
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入 .allBuff 玩家名称。"));
        }
    }
}
