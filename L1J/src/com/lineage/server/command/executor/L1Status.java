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

import java.util.StringTokenizer;

import com.lineage.server.model.L1World;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_Lawful;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_SystemMessage;

public class L1Status implements L1CommandExecutor {
    public static L1CommandExecutor getInstance() {
        return new L1Status();
    }

    private L1Status() {
    }

    @Override
    public void execute(final L1PcInstance pc, final String cmdName,
            final String arg) {
        try {
            final StringTokenizer st = new StringTokenizer(arg);
            final String char_name = st.nextToken();
            final String param = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            L1PcInstance target = null;
            if (char_name.equalsIgnoreCase("me")) {
                target = pc;
            } else {
                target = L1World.getInstance().getPlayer(char_name);
            }

            if (target == null) {
                pc.sendPackets(new S_ServerMessage(73, char_name)); // \f1%0%d
                                                                    // 不在线上。
                return;
            }

            // -- not use DB --
            if (param.equalsIgnoreCase("AC")) {
                target.addAc((short) (value - target.getAc())); // byte -->>
                                                                // short
            } else if (param.equalsIgnoreCase("MR")) {
                target.addMr((short) (value - target.getMr()));
            } else if (param.equalsIgnoreCase("HIT")) {
                target.addHitup((short) (value - target.getHitup()));
            } else if (param.equalsIgnoreCase("DMG")) {
                target.addDmgup((short) (value - target.getDmgup()));
                // -- use DB --
            } else {
                if (param.equalsIgnoreCase("HP")) {
                    target.addBaseMaxHp((short) (value - target.getBaseMaxHp()));
                    target.setCurrentHpDirect(target.getMaxHp());
                } else if (param.equalsIgnoreCase("MP")) {
                    target.addBaseMaxMp((short) (value - target.getBaseMaxMp()));
                    target.setCurrentMpDirect(target.getMaxMp());
                } else if (param.equalsIgnoreCase("LAWFUL")) {
                    target.setLawful(value);
                    final S_Lawful s_lawful = new S_Lawful(target.getId(),
                            target.getLawful());
                    target.sendPackets(s_lawful);
                    target.broadcastPacket(s_lawful);
                } else if (param.equalsIgnoreCase("KARMA")) {
                    target.setKarma(value);
                } else if (param.equalsIgnoreCase("GM")) {
                    if (value > 200) {
                        value = 200;
                    }
                    target.setAccessLevel((short) value);
                    target.sendPackets(new S_SystemMessage("您已被提升为GM权限，小退生效。"));
                } else if (param.equalsIgnoreCase("STR")) {
                    if (value > 255) {
                        value = 255;
                    }
                    target.addBaseStr((short) (value - target.getBaseStr()));
                } else if (param.equalsIgnoreCase("CON")) {
                    if (value > 255) {
                        value = 255;
                    }
                    target.addBaseCon((short) (value - target.getBaseCon()));
                } else if (param.equalsIgnoreCase("DEX")) {
                    if (value > 255) {
                        value = 255;
                    }
                    target.addBaseDex((short) (value - target.getBaseDex()));
                } else if (param.equalsIgnoreCase("INT")) {
                    if (value > 255) {
                        value = 255;
                    }
                    target.addBaseInt((short) (value - target.getBaseInt()));
                } else if (param.equalsIgnoreCase("WIS")) {
                    if (value > 255) {
                        value = 255;
                    }
                    target.addBaseWis((short) (value - target.getBaseWis()));
                } else if (param.equalsIgnoreCase("CHA")) {
                    if (value > 255) {
                        value = 255;
                    }
                    target.addBaseCha((short) (value - target.getBaseCha()));
                } else {
                    pc.sendPackets(new S_SystemMessage("状态 " + param + " 不明。"));
                    return;
                }
                target.save(); // DBにキャラクター情报を书き迂む
            }
            target.sendPackets(new S_OwnCharStatus(target));
            pc.sendPackets(new S_SystemMessage(target.getName() + " 的" + param
                    + "值" + value + "被变更了。"));
        } catch (final Exception e) {
            pc.sendPackets(new S_SystemMessage("请输入: " + cmdName
                    + " 玩家名称|me 属性 变更值 。"));
        }
    }
}
