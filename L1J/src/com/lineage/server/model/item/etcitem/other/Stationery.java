package com.lineage.server.model.item.etcitem.other;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.clientpackets.C_ItemUSe;
import com.lineage.server.datatables.CharacterTable;
import com.lineage.server.datatables.ItemTable;
import com.lineage.server.datatables.LetterTable;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1Inventory;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.ItemExecutor;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SkillSound;
import com.lineage.server.storage.CharactersItemStorage;

/**
 * (未使用) <br>
 * 信纸 - 40310 <br>
 * 血盟信纸 - 40311 <br>
 * 圣诞卡片 - 40730 <br>
 * 情人节 卡片 - 40731 <br>
 * 白色情人节 卡片 - 40732 <br>
 * 
 * @author jrwz
 */
public class Stationery implements ItemExecutor {

    private static Logger _log = Logger.getLogger(C_ItemUSe.class.getName());

    public static ItemExecutor get() {
        return new Stationery();
    }

    private Stationery() {
    }

    /**
     * 道具执行
     * 
     * @param data
     *            参数
     * @param pc
     *            对象
     * @param item
     *            道具
     */
    @Override
    public void execute(final int[] data, final L1PcInstance pc,
            final L1ItemInstance item) {

        final int itemId = item.getItemId();
        final int letterCode = data[0];
        final String letterReceiver = pc.getText();
        final byte[] letterText = pc.getTextByte();

        if (this.writeLetter(itemId, pc, letterCode, letterReceiver, letterText)) {
            pc.getInventory().removeItem(item, 1);
        }

        // 血盟
        else if (this.writeClanLetter(itemId, pc, letterCode, letterReceiver,
                letterText)) {
            pc.getInventory().removeItem(item, 1);
        }
    }

    // 保存信件
    private void saveLetter(final int itemObjectId, final int code,
            final String sender, final String receiver, final byte[] text) {
        // 取得日期
        final SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
        final TimeZone tz = TimeZone.getTimeZone(Config.TIME_ZONE);
        final String date = sdf.format(Calendar.getInstance(tz).getTime());

        int spacePosition1 = 0;
        int spacePosition2 = 0;
        for (int i = 0; i < text.length; i += 2) {
            if ((text[i] == 0) && (text[i + 1] == 0)) {
                if (spacePosition1 == 0) {
                    spacePosition1 = i;
                } else if ((spacePosition1 != 0) && (spacePosition2 == 0)) {
                    spacePosition2 = i;
                    break;
                }
            }
        }

        final int subjectLength = spacePosition1 + 2;
        int contentLength = spacePosition2 - spacePosition1;
        if (contentLength <= 0) {
            contentLength = 1;
        }
        final byte[] subject = new byte[subjectLength];
        final byte[] content = new byte[contentLength];
        System.arraycopy(text, 0, subject, 0, subjectLength);
        System.arraycopy(text, subjectLength, content, 0, contentLength);
        LetterTable.getInstance().writeLetter(itemObjectId, code, sender,
                receiver, date, 0, subject, content);
    }

    // 信件 (收信)
    private boolean sendLetter(final L1PcInstance pc, final String name,
            final L1ItemInstance item, final boolean isFailureMessage) {
        final L1PcInstance target = L1World.getInstance().getPlayer(name);
        if (target != null) {
            if (target.getInventory().checkAddItem(item, 1) == L1Inventory.OK) {
                target.getInventory().storeItem(item);
                target.sendPackets(new S_SkillSound(target.getId(), 1091));
                target.sendPackets(new S_ServerMessage(428)); // 您收到鸽子信差给你的信件。
            } else {
                if (isFailureMessage) {
                    // 对方的负重太重，无法再给予。
                    pc.sendPackets(new S_ServerMessage(942));
                }
                return false;
            }
        } else {
            if (CharacterTable.doesCharNameExist(name)) {
                try {
                    final int targetId = CharacterTable.getInstance()
                            .restoreCharacter(name).getId();
                    final CharactersItemStorage storage = CharactersItemStorage
                            .create();
                    if (storage.getItemCount(targetId) < 180) {
                        storage.storeItem(targetId, item);
                    } else {
                        if (isFailureMessage) {
                            // 对方的负重太重，无法再给予。
                            pc.sendPackets(new S_ServerMessage(942));
                        }
                        return false;
                    }
                } catch (final Exception e) {
                    _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
                }
            } else {
                if (isFailureMessage) {
                    pc.sendPackets(new S_ServerMessage(109, name)); // 没有叫%0的人。
                }
                return false;
            }
        }
        return true;
    }

    // 信件 (写信)
    private boolean writeClanLetter(final int itemId, final L1PcInstance pc,
            final int letterCode, final String letterReceiver,
            final byte[] letterText) {
        L1Clan targetClan = null;
        for (final L1Clan clan : L1World.getInstance().getAllClans()) {
            if (clan.getClanName().toLowerCase()
                    .equals(letterReceiver.toLowerCase())) {
                targetClan = clan;
                break;
            }
        }
        if (targetClan == null) {
            pc.sendPackets(new S_ServerMessage(434)); // 没有收信者。
            return false;
        }

        final String memberName[] = targetClan.getAllMembers();
        for (final String element : memberName) {
            final L1ItemInstance item = ItemTable.getInstance().createItem(
                    49016);
            if (item == null) {
                return false;
            }
            item.setCount(1);
            if (this.sendLetter(pc, element, item, false)) {
                this.saveLetter(item.getId(), letterCode, pc.getName(),
                        element, letterText);
            }
        }
        return true;
    }

    // 信纸
    private boolean writeLetter(final int itemId, final L1PcInstance pc,
            final int letterCode, final String letterReceiver,
            final byte[] letterText) {

        int newItemId = 0;

        switch (itemId) {
            case 40310: // 信纸
                newItemId = 49016; // 信纸
                break;

            case 40730: // 圣诞卡片
                newItemId = 49020; // 圣诞卡片
                break;

            case 40731: // 情人节 卡片
                newItemId = 49022; // 情人节 卡片
                break;

            case 40732: // 白色情人节 卡片
                newItemId = 49024; // 白色情人节 卡片
                break;
        }

        final L1ItemInstance item = ItemTable.getInstance().createItem(
                newItemId);
        if (item == null) {
            return false;
        }
        item.setCount(1);

        if (this.sendLetter(pc, letterReceiver, item, true)) {
            this.saveLetter(item.getId(), letterCode, pc.getName(),
                    letterReceiver, letterText);
        } else {
            return false;
        }
        return true;
    }
}
