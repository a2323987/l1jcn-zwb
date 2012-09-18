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
package com.lineage.telnet.command;

import static com.lineage.telnet.command.TelnetCommandResult.CMD_INTERNAL_ERROR;
import static com.lineage.telnet.command.TelnetCommandResult.CMD_OK;

import java.util.StringTokenizer;

import com.lineage.server.GameServer;
import com.lineage.server.Opcodes;
import com.lineage.server.datatables.ChatLogTable;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ChatPacket;
import com.lineage.server.storage.mysql.MySqlCharacterStorage;
import com.lineage.server.utils.IntRange;

class CharStatusCommand implements TelnetCommand {
    @Override
    public TelnetCommandResult execute(final String args) {
        final int id = Integer.valueOf(args);
        final L1Object obj = L1World.getInstance().findObject(id);
        if (obj == null) {
            return new TelnetCommandResult(CMD_INTERNAL_ERROR, "ObjectId " + id
                    + " not found");
        }
        if (!(obj instanceof L1Character)) {
            return new TelnetCommandResult(CMD_INTERNAL_ERROR, "ObjectId " + id
                    + " is not a character");
        }
        final L1Character cha = (L1Character) obj;
        final StringBuilder result = new StringBuilder();
        result.append("Name: " + cha.getName() + "\r\n");
        result.append("Level: " + cha.getLevel() + "\r\n");
        result.append("MaxHp: " + cha.getMaxHp() + "\r\n");
        result.append("CurrentHp: " + cha.getCurrentHp() + "\r\n");
        result.append("MaxMp: " + cha.getMaxMp() + "\r\n");
        result.append("CurrentMp: " + cha.getCurrentMp() + "\r\n");
        return new TelnetCommandResult(CMD_OK, result.toString());
    }
}

class EchoCommand implements TelnetCommand {
    @Override
    public TelnetCommandResult execute(final String args) {
        return new TelnetCommandResult(CMD_OK, args);
    }
}

class GlobalChatCommand implements TelnetCommand {
    @Override
    public TelnetCommandResult execute(final String args) {
        final StringTokenizer tok = new StringTokenizer(args, " ");
        final String name = tok.nextToken();
        final String text = args.substring(name.length() + 1);
        final L1PcInstance pc = new MySqlCharacterStorage().loadCharacter(name);
        if (pc == null) {
            return new TelnetCommandResult(CMD_INTERNAL_ERROR, " 没有角色存在。");
        }
        pc.getLocation().set(-1, -1, 0);
        ChatLogTable.getInstance().storeChat(pc, null, text, 3);

        L1World.getInstance().broadcastPacketToAll(
                new S_ChatPacket(pc, text, Opcodes.S_OPCODE_GLOBALCHAT, 3));
        return new TelnetCommandResult(CMD_OK, "");
    }
}

class PlayerIdCommand implements TelnetCommand {
    @Override
    public TelnetCommandResult execute(final String args) {
        final L1PcInstance pc = L1World.getInstance().getPlayer(args);
        final String result = pc == null ? "0" : String.valueOf(pc.getId());
        return new TelnetCommandResult(CMD_OK, result);
    }
}

class ShutDownCommand implements TelnetCommand {
    @Override
    public TelnetCommandResult execute(final String args) {
        int sec = args.isEmpty() ? 0 : Integer.parseInt(args);
        sec = IntRange.ensure(sec, 30, Integer.MAX_VALUE);

        GameServer.getInstance().shutdownWithCountdown(sec);
        return new TelnetCommandResult(CMD_OK, "");
    }
}

/**
 * Telnet指令
 */
public interface TelnetCommand {

    TelnetCommandResult execute(String args);
}
