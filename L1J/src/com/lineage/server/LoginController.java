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
package com.lineage.server;

import java.util.Map;

import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.utils.collections.Maps;

/**
 * 登陆控制器
 */
public class LoginController {

    private static LoginController _instance;

    public static LoginController getInstance() {
        if (_instance == null) {
            _instance = new LoginController();
        }
        return _instance;
    }

    private final Map<String, ClientThread> _accounts = Maps.newConcurrentMap();

    /** 最大允许在线玩家 */
    private int _maxAllowedOnlinePlayers;

    private LoginController() {
    }

    /** 取得所有账户 */
    public ClientThread[] getAllAccounts() {
        return this._accounts.values().toArray(
                new ClientThread[this._accounts.size()]);
    }

    /** 取得最大允许在线玩家 */
    public int getMaxAllowedOnlinePlayers() {
        return this._maxAllowedOnlinePlayers;
    }

    /** 取得在线玩家数量 */
    public int getOnlinePlayerCount() {
        return this._accounts.size();
    }

    /** 踢人 */
    private void kickClient(final ClientThread client) {
        if (client == null) {
            return;
        }

        GeneralThreadPool.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                if (client.getActiveChar() != null) {
                    client.getActiveChar()
                            .sendPackets(new S_ServerMessage(357)); // 有人以同样的帐号登入，请注意，您的密码可能已经外泄
                }

                try {
                    Thread.sleep(1000);
                } catch (final Exception e) {
                }
                client.kick();
            }
        });
    }

    /** 登陆 */
    public synchronized void login(final ClientThread client,
            final Account account) throws GameServerFullException,
            AccountAlreadyLoginException {
        if (!account.isValid()) {
            // 密码验证未指定或不验证账户。
            // 此代码只存在的错误检测。
            throw new IllegalArgumentException("账户沒有通过验证");
        }
        if ((this.getMaxAllowedOnlinePlayers() <= this.getOnlinePlayerCount())
                && !account.isGameMaster()) {
            throw new GameServerFullException();
        }
        if (this._accounts.containsKey(account.getName())) {
            this.kickClient(this._accounts.remove(account.getName()));
            throw new AccountAlreadyLoginException();
        }

        this._accounts.put(account.getName(), client);
    }

    /** 登出 */
    public synchronized boolean logout(final ClientThread client) {
        if (client.getAccountName() == null) {
            return false;
        }
        return this._accounts.remove(client.getAccountName()) != null;
    }

    /** 设置最大允许在线玩家 */
    public void setMaxAllowedOnlinePlayers(final int maxAllowedOnlinePlayers) {
        this._maxAllowedOnlinePlayers = maxAllowedOnlinePlayers;
    }
}
