package com.lineage.server.model.item.etcitem;

import com.lineage.Config;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.UniversalUseItem;
import com.lineage.server.serverpackets.S_AddSkill;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.templates.L1Skills;

/**
 * 使用技能书 (通用魔法)
 * 
 * @author jrwz
 */
public class UseSpellBook implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UseSpellBook();
        }
        return _instance;
    }

    private void SpellBook(final L1PcInstance pc, final L1ItemInstance item,
            final boolean isLawful) {

        String s = "";
        int i = 0;
        int level1 = 0;
        int level2 = 0;
        int l = 0;
        int i1 = 0;
        int j1 = 0;
        int k1 = 0;
        int l1 = 0;
        int i2 = 0;
        int j2 = 0;
        int k2 = 0;
        int l2 = 0;
        int i3 = 0;
        int j3 = 0;
        int k3 = 0;
        int l3 = 0;
        int i4 = 0;
        int j4 = 0;
        int k4 = 0;
        int l4 = 0;
        int i5 = 0;
        int j5 = 0;
        int k5 = 0;
        int l5 = 0;
        int i6 = 0;
        for (int skillId = 1; skillId < 81; skillId++) {
            final L1Skills l1skills = SkillsTable.getInstance().getTemplate(
                    skillId);
            String s1 = null;
            if (Config.CLIENT_LANGUAGE == 3) {
                s1 = (new StringBuilder()).append("魔法书(")
                        .append(l1skills.getName()).append(")").toString();
            } else if (Config.CLIENT_LANGUAGE == 5) {
                s1 = (new StringBuilder()).append("魔法书(")
                        .append(l1skills.getName()).append(")").toString();
            } else {
                s1 = (new StringBuilder()).append("魔法书(")
                        .append(l1skills.getName()).append(")").toString();
            }
            if (item.getItem().getName().equalsIgnoreCase(s1)) {
                final int skillLevel = l1skills.getSkillLevel();
                final int i7 = l1skills.getId();
                s = l1skills.getName();
                i = l1skills.getSkillId();
                switch (skillLevel) {
                    case 1: // '\001'
                        level1 = i7;
                        break;

                    case 2: // '\002'
                        level2 = i7;
                        break;

                    case 3: // '\003'
                        l = i7;
                        break;

                    case 4: // '\004'
                        i1 = i7;
                        break;

                    case 5: // '\005'
                        j1 = i7;
                        break;

                    case 6: // '\006'
                        k1 = i7;
                        break;

                    case 7: // '\007'
                        l1 = i7;
                        break;

                    case 8: // '\b'
                        i2 = i7;
                        break;

                    case 9: // '\t'
                        j2 = i7;
                        break;

                    case 10: // '\n'
                        k2 = i7;
                        break;

                    case 11: // '\013'
                        l2 = i7;
                        break;

                    case 12: // '\f'
                        i3 = i7;
                        break;

                    case 13: // '\r'
                        j3 = i7;
                        break;

                    case 14: // '\016'
                        k3 = i7;
                        break;

                    case 15: // '\017'
                        l3 = i7;
                        break;

                    case 16: // '\020'
                        i4 = i7;
                        break;

                    case 17: // '\021'
                        j4 = i7;
                        break;

                    case 18: // '\022'
                        k4 = i7;
                        break;

                    case 19: // '\023'
                        l4 = i7;
                        break;

                    case 20: // '\024'
                        i5 = i7;
                        break;

                    case 21: // '\025'
                        j5 = i7;
                        break;

                    case 22: // '\026'
                        k5 = i7;
                        break;

                    case 23: // '\027'
                        l5 = i7;
                        break;

                    case 24: // '\030'
                        i6 = i7;
                        break;
                }
            }
        }

        final int objid = pc.getId();
        pc.sendPackets(new S_AddSkill(level1, level2, l, i1, j1, k1, l1, i2,
                j2, k2, l2, i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6,
                0, 0, 0, 0));
        final S_SkillSound s_skillSound = new S_SkillSound(objid,
                isLawful ? 224 : 231);
        pc.sendPackets(s_skillSound);
        pc.broadcastPacket(s_skillSound);
        SkillsTable.getInstance().spellMastery(objid, i, s, 0, 0);
        pc.getInventory().removeItem(item, 1);
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        int itemAttr = 0;
        int locAttr = 0; // 0:other 1:law 2:chaos
        boolean isLawful = true;
        final int pcX = pc.getX();
        final int pcY = pc.getY();
        final int mapId = pc.getMapId();
        final int level = pc.getLevel();

        switch (itemId) {

            // 正义魔法书
            case 45000:
            case 45008:
            case 45018:
            case 45021:
            case 40171:
            case 40179:
            case 40180:
            case 40182:
            case 40194:
            case 40197:
            case 40202:
            case 40206:
            case 40213:
            case 40220:
            case 40222:
                itemAttr = 1;
                break;

            // 邪恶魔法书
            case 45009:
            case 45010:
            case 45019:
            case 40172:
            case 40173:
            case 40178:
            case 40185:
            case 40186:
            case 40192:
            case 40196:
            case 40201:
            case 40204:
            case 40211:
            case 40221:
            case 40225:
                itemAttr = 2;
                break;
        }

        // 正义神殿
        if (((pcX > 33116) && (pcX < 33128) && (pcY > 32930) && (pcY < 32942) && (mapId == 4))
                || ((pcX > 33135) && (pcX < 33147) && (pcY > 32235)
                        && (pcY < 32247) && (mapId == 4))
                || ((pcX >= 32783) && (pcX <= 32803) && (pcY >= 32831)
                        && (pcY <= 32851) && (mapId == 77))) {
            locAttr = 1;
            isLawful = true;
        }

        // 邪恶神殿
        if (((pcX > 32880) && (pcX < 32892) && (pcY > 32646) && (pcY < 32658) && (mapId == 4))
                || ((pcX > 32662) && (pcX < 32674) && (pcY > 32297)
                        && (pcY < 32309) && (mapId == 4))) {
            locAttr = 2;
            isLawful = false;
        }
        if (pc.isGm()) {
            this.SpellBook(pc, item, isLawful);
        } else if (((itemAttr == locAttr) || (itemAttr == 0)) && (locAttr != 0)) {

            // 骑士
            if (pc.isKnight()) {

                if ((itemId >= 45000) && (itemId <= 45007) && (level >= 50)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 45000) && (itemId <= 45007)) {
                    pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
                } else {
                    pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                }
            }

            // 王族或黑暗精灵
            else if (pc.isCrown() || pc.isDarkelf()) {
                if ((itemId >= 45000) && (itemId <= 45007) && (level >= 10)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 45008) && (itemId <= 45015)
                        && (level >= 20)) {
                    this.SpellBook(pc, item, isLawful);
                } else if (((itemId >= 45008) && (itemId <= 45015))
                        || ((itemId >= 45000) && (itemId <= 45007))) {
                    pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
                } else {
                    pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                }
            }

            // 精灵
            else if (pc.isElf()) {
                if ((itemId >= 45000) && (itemId <= 45007) && (level >= 8)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 45008) && (itemId <= 45015)
                        && (level >= 16)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 45016) && (itemId <= 45022)
                        && (level >= 24)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40170) && (itemId <= 40177)
                        && (level >= 32)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40178) && (itemId <= 40185)
                        && (level >= 40)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40186) && (itemId <= 40193)
                        && (level >= 48)) {
                    this.SpellBook(pc, item, isLawful);
                } else if (((itemId >= 45000) && (itemId <= 45022))
                        || ((itemId >= 40170) && (itemId <= 40193))) {
                    pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
                } else {
                    pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
                }
            }

            // 法师
            else if (pc.isWizard()) {
                if ((itemId >= 45000) && (itemId <= 45007) && (level >= 4)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 45008) && (itemId <= 45015)
                        && (level >= 8)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 45016) && (itemId <= 45022)
                        && (level >= 12)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40170) && (itemId <= 40177)
                        && (level >= 16)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40178) && (itemId <= 40185)
                        && (level >= 20)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40186) && (itemId <= 40193)
                        && (level >= 24)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40194) && (itemId <= 40201)
                        && (level >= 28)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40202) && (itemId <= 40209)
                        && (level >= 32)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40210) && (itemId <= 40217)
                        && (level >= 36)) {
                    this.SpellBook(pc, item, isLawful);
                } else if ((itemId >= 40218) && (itemId <= 40225)
                        && (level >= 40)) {
                    this.SpellBook(pc, item, isLawful);
                } else {
                    pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
                }
            }
        }

        // 学习地点不对扣除技能书并施以惩罚 - 极道落雷
        else if ((itemAttr != locAttr) && (itemAttr != 0) && (locAttr != 0)) {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
            final S_SkillSound gfx_effect = new S_SkillSound(pc.getId(), 10);
            pc.sendPackets(gfx_effect);
            pc.broadcastPacket(gfx_effect);
            // 减血
            pc.setCurrentHp(Math.max(pc.getCurrentHp() - 45, 0));
            if (pc.getCurrentHp() <= 0) {
                pc.death(null);
            }
            pc.getInventory().removeItem(item, 1);
        } else {
            pc.sendPackets(new S_ServerMessage(79)); // \f1没有任何事情发生。
        }
    }

}
