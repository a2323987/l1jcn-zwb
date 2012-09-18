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
package com.lineage.console;

import java.util.Scanner;

import com.lineage.Config;
import com.lineage.server.GameServer;
import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_SystemMessage;

/**
 * cmd 互动命令 处理程序
 */
public class ConsoleProcess extends Thread {

    /** 使用者输入 */
    private final Scanner UserInput = new Scanner(System.in);

    /** 开机后是否开启此功能 */
    private boolean onStarup = true;

    /** 程序是否继续 */
    private final boolean stillrun = true;

    Runtime rt = Runtime.getRuntime();

    public ConsoleProcess() {
        this.onStarup = Config.CmdActive;
        if (!this.onStarup) {
            return;
        }
        System.out.println("→提示: 互动指令听取中..." + "\n" + ">");
    }

    /**
     * 指令执行
     * 
     * @param cmd
     *            指令名称
     */
    private void execute(final String cmd) {
        if (cmd == null) {
            System.out.println("错误, 请输入CMD指令.");
            return;
        }
        if (cmd.equalsIgnoreCase("lookup")) { // cmd查看游戏内对话功能
            // TODO 开启另一个视窗并显示游戏内对话
        } else {
            System.out.println("错误, 没有指令.");
            return;
        }
    }

    /**
     * 指令执行(有引数)
     * 
     * @param cmd
     *            指令名称
     * @param line
     *            指令引数
     */
    private void execute(final String cmd, final String line) {
        if ((cmd == null) || (line == null)) {
            System.out.println("错误, 请输入CMD指令或ARGS.");
            return;
        }
        if (cmd.equalsIgnoreCase("chat")) { // cmd与游戏内对话功能
            L1World.getInstance().broadcastPacketToAll(
                    new S_SystemMessage("\\f3" + "[系统管理员]" + line));
            System.out.println("[系统管理员]" + line);
        } else if (cmd.equalsIgnoreCase("shutdown")) {
            final int sec = Integer.parseInt(line);
            if (sec > 0) {
                GameServer.getInstance().shutdownWithCountdown(sec);
            }
            if (sec <= 0) {
                GameServer.getInstance().shutdown();
            }
        } else {
            System.out.println("错误, 没有指令.");
            return;
        }

    }

    @Override
    public void run() {
        while (this.onStarup && this.stillrun) {
            final String action = this.UserInput.nextLine();
            final String word[] = action.split(" ");
            if (word.length == 1) {
                this.execute(word[0]);
            }
            if (word.length == 2) {
                this.execute(word[0], word[1]);
            }
        }
        System.out.println("→提示: 互动指令听取中..." + "\n" + ">");
    }
}
