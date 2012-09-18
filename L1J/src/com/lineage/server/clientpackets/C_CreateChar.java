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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.Account;
import com.lineage.server.BadNamesList;
import com.lineage.server.ClientThread;
import com.lineage.server.IdFactory;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.datatables.SkillsTable;
import com.lineage.server.model.Beginner;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_AddSkill;
import com.lineage.server.serverpackets.S_CharCreateStatus;
import com.lineage.server.serverpackets.S_NewCharPacket;
import com.lineage.server.templates.L1Skills;
import com.lineage.server.utils.CalcInitHpMp;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来建立角色的封包
 */
public class C_CreateChar extends ClientBasePacket {

    private static Logger _log = Logger.getLogger(C_CreateChar.class.getName());

    private static final String C_CREATE_CHAR = "[C] C_CreateChar";

    private static final int[] ORIGINAL_STR = new int[] { 13, 16, 11, 8, 12,
            13, 11 };

    private static final int[] ORIGINAL_DEX = new int[] { 10, 12, 12, 7, 15,
            11, 10 };

    private static final int[] ORIGINAL_CON = new int[] { 10, 14, 12, 12, 8,
            14, 12 };

    private static final int[] ORIGINAL_WIS = new int[] { 11, 9, 12, 12, 10,
            12, 12 };

    private static final int[] ORIGINAL_CHA = new int[] { 13, 12, 9, 8, 9, 8, 8 };

    private static final int[] ORIGINAL_INT = new int[] { 10, 8, 12, 12, 11,
            11, 12 };

    private static final int[] ORIGINAL_AMOUNT = new int[] { 8, 4, 7, 16, 10,
            6, 10 };

    private static final String CLIENT_LANGUAGE_CODE = Config.CLIENT_LANGUAGE_CODE;

    /** 男classId */
    private static final int[] MALE_LIST = new int[] { 0, 61, 138, 734, 2786,
            6658, 6671 };

    /** 女classId */
    private static final int[] FEMALE_LIST = new int[] { 1, 48, 37, 1186, 2796,
            6661, 6650 };
    // 台版 3.3C
    private static final int[] LOCX_LIST = new int[] { 32691, 32691, 32691,
            32691, 32691, 32691, 32691 };

    /*
     * private static final int[] LOCX_LIST = new int[] { 32734, 32734, 32734,
     * 32734, 32734, 32734, 32734 }; private static final int[] LOCY_LIST = new
     * int[] { 32798, 32798, 32798, 32798, 32798, 32798, 32798 }; private static
     * final short[] MAPID_LIST = new short[] { 8013, 8013, 8013, 8013, 8013,
     * 8013, 8013 };
     */
    /*
     * private static final int[] LOCX_LIST = new int[] { 32780, 32714, 32714,
     * 32780, 32714, 32714, 32714 }; private static final int[] LOCY_LIST = new
     * int[] { 32781, 32877, 32877, 32781, 32877, 32877, 32877 }; private static
     * final short[] MAPID_LIST = new short[] { 68, 69, 69, 68, 69, 69, 69 };
     */

    private static final int[] LOCY_LIST = new int[] { 32864, 32864, 32864,
            32864, 32864, 32864, 32864 };

    private static final short[] MAPID_LIST = new short[] { 2005, 2005, 2005,
            2005, 2005, 2005, 2005 };

    private static void initNewChar(final ClientThread client,
            final L1PcInstance pc) throws IOException, Exception {

        pc.setId(IdFactory.getInstance().nextId());
        pc.setBirthday();
        if (pc.get_sex() == 0) {
            pc.setClassId(MALE_LIST[pc.getType()]);
        } else {
            pc.setClassId(FEMALE_LIST[pc.getType()]);
        }
        pc.setX(LOCX_LIST[pc.getType()]);
        pc.setY(LOCY_LIST[pc.getType()]);
        pc.setMap(MAPID_LIST[pc.getType()]);
        pc.setHeading(0);
        pc.setLawful(0);

        final int initHp = CalcInitHpMp.calcInitHp(pc);
        final int initMp = CalcInitHpMp.calcInitMp(pc);
        pc.addBaseMaxHp((short) initHp);
        pc.setCurrentHp((short) initHp);
        pc.addBaseMaxMp((short) initMp);
        pc.setCurrentMp((short) initMp);
        pc.resetBaseAc();
        pc.setTitle("");
        pc.setClanid(0);
        pc.setClanRank(0);
        pc.set_food(40);
        if (Config.NewCreateRoleSetGM) {
            pc.setAccessLevel((short) 200);
            pc.setGm(true);
        } else {
            pc.setAccessLevel((short) 0);
            pc.setGm(false);
        }
        pc.setMonitor(false);
        pc.setGmInvis(false);
        pc.setExp(0);
        pc.setHighLevel(0);
        pc.setStatus(0);
        pc.setClanname("");
        pc.setBonusStats(0);
        pc.setElixirStats(0);
        pc.resetBaseMr();
        pc.setElfAttr(0);
        pc.set_PKcount(0);
        pc.setPkCountForElf(0);
        pc.setExpRes(0);
        pc.setPartnerId(0);
        pc.setOnlineStatus(0);
        pc.setHomeTownId(0);
        pc.setContribution(0);
        pc.setBanned(false);
        pc.setKarma(0);
        if (pc.isWizard()) { // WIZ
            pc.sendPackets(new S_AddSkill(3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
            final int object_id = pc.getId();
            final L1Skills l1skills = SkillsTable.getInstance().getTemplate(4); // EB
            final String skill_name = l1skills.getName();
            final int skill_id = l1skills.getSkillId();
            SkillsTable.getInstance().spellMastery(object_id, skill_id,
                    skill_name, 0, 0); // 储存魔法资料到资料库中
        }
        Beginner.getInstance().GiveItem(pc);
        pc.setAccountName(client.getAccountName());
        CharacterTable.getInstance().storeNewCharacter(pc);
        final S_NewCharPacket s_newcharpacket = new S_NewCharPacket(pc);
        client.sendPacket(s_newcharpacket);
        CharacterTable.getInstance();
        CharacterTable.saveCharStatus(pc);
        pc.refresh();
    }

    /** 字母数字 */
    private static boolean isAlphaNumeric(final String s) {
        boolean flag = true;
        final char ac[] = s.toCharArray();
        int i = 0;
        do {
            if (i >= ac.length) {
                break;
            }
            if (!Character.isLetterOrDigit(ac[i])) {
                flag = false;
                break;
            }
            i++;
        } while (true);
        return flag;
    }

    /** 无效的名字 */
    private static boolean isInvalidName(final String name) {
        int numOfNameBytes = 0;

        // TODO:Check the badNameList is working well ?
        if (BadNamesList.getInstance().isBadName(name)) {
            return true;
        }

        try {
            numOfNameBytes = name.getBytes(CLIENT_LANGUAGE_CODE).length;
        } catch (final UnsupportedEncodingException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
            return false;
        }

        if (isAlphaNumeric(name)) {
            return false;
        }

        // XXX - 规则还没确定
        // 双字节字符或5个字符以上或整个超过 12个字节就视为一个无效的名称
        if ((5 < (numOfNameBytes - name.length())) || (12 < numOfNameBytes)) {
            return false;
        }

        if (BadNamesList.getInstance().isBadName(name)) {
            return false;
        }
        return true;
    }

    public C_CreateChar(final byte[] abyte0, final ClientThread client)
            throws Exception {
        super(abyte0);
        final L1PcInstance pc = new L1PcInstance();
        String name = this.readS();

        final Account account = Account.load(client.getAccountName());
        final int characterSlot = account.getCharacterSlot();
        final int maxAmount = Config.DEFAULT_CHARACTER_SLOT + characterSlot;

        name = name.replaceAll("\\s", "");
        name = name.replaceAll("　", "");
        if (name.length() == 0) {
            final S_CharCreateStatus s_charcreatestatus = new S_CharCreateStatus(
                    S_CharCreateStatus.REASON_INVALID_NAME);
            client.sendPacket(s_charcreatestatus);
            return;
        }

        if (isInvalidName(name)) {
            final S_CharCreateStatus s_charcreatestatus = new S_CharCreateStatus(
                    S_CharCreateStatus.REASON_INVALID_NAME);
            client.sendPacket(s_charcreatestatus);
            return;
        }

        if (CharacterTable.doesCharNameExist(name)) {
            _log.fine("角色名称: " + pc.getName() + " 已经存在。创建失败。");
            final S_CharCreateStatus s_charcreatestatus1 = new S_CharCreateStatus(
                    S_CharCreateStatus.REASON_ALREADY_EXSISTS);
            client.sendPacket(s_charcreatestatus1);
            return;
        }

        if (client.getAccount().countCharacters() >= maxAmount) {
            _log.fine("账号: " + client.getAccountName() + " 超过角色上限数目: "
                    + maxAmount + "。");
            final S_CharCreateStatus s_charcreatestatus1 = new S_CharCreateStatus(
                    S_CharCreateStatus.REASON_WRONG_AMOUNT);
            client.sendPacket(s_charcreatestatus1);
            return;
        }

        pc.setName(name);
        pc.setType(this.readC());
        pc.set_sex(this.readC());
        pc.addBaseStr((byte) this.readC());
        pc.addBaseDex((byte) this.readC());
        pc.addBaseCon((byte) this.readC());
        pc.addBaseWis((byte) this.readC());
        pc.addBaseCha((byte) this.readC());
        pc.addBaseInt((byte) this.readC());

        boolean isStatusError = false;
        final int originalStr = ORIGINAL_STR[pc.getType()];
        final int originalDex = ORIGINAL_DEX[pc.getType()];
        final int originalCon = ORIGINAL_CON[pc.getType()];
        final int originalWis = ORIGINAL_WIS[pc.getType()];
        final int originalCha = ORIGINAL_CHA[pc.getType()];
        final int originalInt = ORIGINAL_INT[pc.getType()];
        final int originalAmount = ORIGINAL_AMOUNT[pc.getType()];

        if (((pc.getBaseStr() < originalStr) || (pc.getBaseDex() < originalDex)
                || (pc.getBaseCon() < originalCon)
                || (pc.getBaseWis() < originalWis)
                || (pc.getBaseCha() < originalCha) || (pc.getBaseInt() < originalInt))
                || ((pc.getBaseStr() > originalStr + originalAmount)
                        || (pc.getBaseDex() > originalDex + originalAmount)
                        || (pc.getBaseCon() > originalCon + originalAmount)
                        || (pc.getBaseWis() > originalWis + originalAmount)
                        || (pc.getBaseCha() > originalCha + originalAmount) || (pc
                        .getBaseInt() > originalInt + originalAmount))) {
            isStatusError = true;
        }

        final int statusAmount = pc.getDex() + pc.getCha() + pc.getCon()
                + pc.getInt() + pc.getStr() + pc.getWis();

        if ((statusAmount != 75) || isStatusError) {
            _log.finest("角色有错误的能力值");
            final S_CharCreateStatus s_charcreatestatus3 = new S_CharCreateStatus(
                    S_CharCreateStatus.REASON_WRONG_AMOUNT);
            client.sendPacket(s_charcreatestatus3);
            return;
        }

        _log.fine("角色名称: " + pc.getName() + " classId: " + pc.getClassId());
        final S_CharCreateStatus s_charcreatestatus2 = new S_CharCreateStatus(
                S_CharCreateStatus.REASON_OK);
        client.sendPacket(s_charcreatestatus2);
        initNewChar(client, pc);
    }

    @Override
    public String getType() {
        return C_CREATE_CHAR;
    }
}
