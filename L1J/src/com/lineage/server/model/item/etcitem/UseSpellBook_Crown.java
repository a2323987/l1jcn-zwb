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
 * 使用技能书 (王族魔法)
 * 
 * @author jrwz
 */
public class UseSpellBook_Crown implements UniversalUseItem {

    private static UniversalUseItem _instance;

    public static UniversalUseItem get() {
        if (_instance == null) {
            _instance = new UseSpellBook_Crown();
        }
        return _instance;
    }

    private void CrownSpellBook(final L1PcInstance pc, final L1ItemInstance item) {

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
        for (int j6 = 113; j6 < 121; j6++) {
            final L1Skills l1skills = SkillsTable.getInstance().getTemplate(j6);
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

    @Override
    public void useItem(final L1PcInstance pc, final L1ItemInstance item,
            final int itemId, final int effect, final int time, final int gfxid) {

        final int level = pc.getLevel();
        switch (itemId) {

            case 40226: // 魔法书(精准目标)
                if (level >= 15) {
                    this.CrownSpellBook(pc, item);
                }
                break;

            case 40228: // 魔法书(呼唤盟友)
                if (level >= 30) {
                    this.CrownSpellBook(pc, item);
                }
                break;

            case 40227: // 魔法书(激励士气)
                if (level >= 40) {
                    this.CrownSpellBook(pc, item);
                }
                break;

            case 40231: // 魔法书(援护盟友)
                // case 40232: // 精灵水晶(魔法防御)
                if (level >= 45) {
                    this.CrownSpellBook(pc, item);
                }
                break;

            case 40230: // 魔法书(冲击士气)
                if (level >= 50) {
                    this.CrownSpellBook(pc, item);
                }
                break;

            case 40229: // 魔法书(钢铁士气)
                if (level >= 55) {
                    this.CrownSpellBook(pc, item);
                }
                break;

            default:
                pc.sendPackets(new S_ServerMessage(312)); // 你还不能学习法术。
                break;
        }
    }

}
