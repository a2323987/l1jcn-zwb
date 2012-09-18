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
import com.lineage.server.types.Point;

/**
 * 使用技能书 (精灵魔法)
 * 
 * @author jrwz
 */
public class UseSpellBook_Elf implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UseSpellBook_Elf();
        }
        return _instance;
    }

    private void ElfSpellBook(final L1PcInstance pc, final L1ItemInstance item) {

        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;
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
        for (int j6 = 129; j6 <= 176; j6++) {
            final L1Skills l1skills = SkillsTable.getInstance().getTemplate(j6);
            String s1 = null;
            if (Config.CLIENT_LANGUAGE == 3) {
                s1 = (new StringBuilder()).append("精灵水晶(")
                        .append(l1skills.getName()).append(")").toString();
            } else if (Config.CLIENT_LANGUAGE == 5) {
                s1 = (new StringBuilder()).append("精灵水晶(")
                        .append(l1skills.getName()).append(")").toString();
            } else {
                s1 = (new StringBuilder()).append("精灵の水晶(")
                        .append(l1skills.getName()).append(")").toString();
            }
            if (item.getItem().getName().equalsIgnoreCase(s1)) {
                if (!pc.isGm() && (l1skills.getAttr() != 0)
                        && (pc.getElfAttr() != l1skills.getAttr())) {
                    if ((pc.getElfAttr() == 0) || (pc.getElfAttr() == 1)
                            || (pc.getElfAttr() == 2) || (pc.getElfAttr() == 4)
                            || (pc.getElfAttr() == 8)) { // 属性值异常
                        pc.sendPackets(new S_ServerMessage(79));
                        return;
                    }
                }
                final int l6 = l1skills.getSkillLevel();
                final int i7 = l1skills.getId();
                s = l1skills.getName();
                i = l1skills.getSkillId();
                switch (l6) {
                    case 1: // '\001'
                        j = i7;
                        break;

                    case 2: // '\002'
                        k = i7;
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

        final int k6 = pc.getId();
        pc.sendPackets(new S_AddSkill(j, k, l, i1, j1, k1, l1, i2, j2, k2, l2,
                i3, j3, k3, l3, i4, j4, k4, l4, i5, j5, k5, l5, i6, 0, 0, 0, 0));
        final S_SkillSound s_skillSound = new S_SkillSound(k6, 224);
        pc.sendPackets(s_skillSound);
        pc.broadcastPacket(s_skillSound);
        SkillsTable.getInstance().spellMastery(k6, i, s, 0, 0);
        pc.getInventory().removeItem(item, 1);
    }

    /**
     * 精灵魔法学习地点
     * 
     * @param pc
     *            对象
     */
    private boolean isLearnElfMagic(final L1PcInstance pc) {
        final int pc_X = pc.getX();
        final int pc_Y = pc.getY();
        final int pcMap_Id = pc.getMapId();
        if (((pc_X >= 32786) && (pc_X <= 32797) && (pc_Y >= 32842)
                && (pc_Y <= 32859) && (pcMap_Id == 75 // 象牙塔
        ))
                || (pc.getLocation().isInScreen(new Point(33055, 32336)) && (pcMap_Id == 4))) { // 妖精森林大树下
            return true;
        }
        return false;
    }

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        final int level = pc.getLevel();
        if (this.isLearnElfMagic(pc) || pc.isGm()) {
            switch (itemId) {
                case 40232:
                case 40233:
                case 40234:
                    if (level >= 10) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40235:
                case 40236:
                    if (level >= 20) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40237:
                case 40238:
                case 40239:
                case 40240:
                    if (level >= 30) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40241:
                case 40242:
                case 40243:
                    if (level >= 40) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40244:
                case 40245:
                case 40246:
                    if (level >= 50) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40247:
                case 40248:
                    if (level >= 30) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40249:
                case 40250:
                    if (level >= 40) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40251:
                case 40252:
                    if (level >= 50) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40253:
                    if (level >= 30) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40254:
                    if (level >= 40) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40255:
                    if (level >= 50) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40256:
                    if (level >= 30) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40257:
                    if (level >= 40) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40258:
                case 40259:
                    if (level >= 50) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40260:
                case 40261:
                    if (level >= 30) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40262:
                    if (level >= 40) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 40263:
                case 40264:
                    if (level >= 50) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 41149:
                case 41150:
                    if (level >= 50) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 41151:
                    if (level >= 40) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                case 41152:
                case 41153:
                    if (level >= 50) {
                        this.ElfSpellBook(pc, item);
                    }
                    break;

                default:
                    // pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
                    break;
            }
        }
    }

}
