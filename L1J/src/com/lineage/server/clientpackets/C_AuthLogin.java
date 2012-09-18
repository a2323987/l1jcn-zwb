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

import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.Account;
import com.lineage.server.AccountAlreadyLoginException;
import com.lineage.server.ClientThread;
import com.lineage.server.GameServerFullException;
import com.lineage.server.LoginController;
import com.lineage.server.serverpackets.S_CommonNews;
import com.lineage.server.serverpackets.S_LoginResult;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理要求登入的封包
 */
public class C_AuthLogin extends ClientBasePacket {

    private static final String C_AUTH_LOGIN = "[C] C_AuthLogin";

    private static Logger _log = Logger.getLogger(C_AuthLogin.class.getName());

    public C_AuthLogin(final byte[] decrypt, final ClientThread client) {
        super(decrypt);
        final String accountName = this.readS().toLowerCase();
        final String password = this.readS();

        final String ip = client.getIp();
        final String host = client.getHostname();

        _log.finest("请求登陆的账户 : " + accountName);

        if (!Config.ALLOW_2PC) {
            for (final ClientThread tempClient : LoginController.getInstance()
                    .getAllAccounts()) {
                if (ip.equalsIgnoreCase(tempClient.getIp())) {
                    _log.info("拒绝 2P 登入。账号=" + accountName + " IP=" + host);
                    client.sendPacket(new S_LoginResult(
                            S_LoginResult.REASON_USER_OR_PASS_WRONG));
                    return;
                }
            }
        }

        Account account = Account.load(accountName);
        if (account == null) {
            if (Config.AUTO_CREATE_ACCOUNTS) {
                account = Account.create(accountName, password, ip, host);
            } else {
                _log.warning("用户账号丢失 " + accountName);
            }
        }
        if ((account == null) || !account.validatePassword(password)) {
            client.sendPacket(new S_LoginResult(
                    S_LoginResult.REASON_USER_OR_PASS_WRONG));
            return;
        }
        if (account.isOnlined()) {
            client.sendPacket(new S_LoginResult(
                    S_LoginResult.REASON_ACCOUNT_ALREADY_EXISTS));// 原码
                                                                  // REASON_ACCOUNT_IN_USE
            return;
        }
        if (account.isBanned()) { // BAN账户
            _log.info("禁止登入的帐号尝试登入。账号=" + accountName + " IP=" + host);
            client.sendPacket(new S_LoginResult(
                    S_LoginResult.REASON_USER_OR_PASS_WRONG));
            return;
        }

        try {
            LoginController.getInstance().login(client, account);
            Account.updateLastActive(account, ip); // 更新最后一次登入的时间与IP
            client.setAccount(account);
            client.sendPacket(new S_LoginResult(S_LoginResult.REASON_LOGIN_OK));
            client.sendPacket(new S_CommonNews());
            Account.online(account, true);
        } catch (final GameServerFullException e) {
            client.kick();
            _log.info("线上人数已经饱和，切断 (" + client.getHostname() + ") 的连线。");
            return;
        } catch (final AccountAlreadyLoginException e) {
            client.kick();
            _log.info("同个帐号已经登入，切断 (" + client.getHostname() + ") 的连线。");
            return;
        }
    }

    @Override
    public String getType() {
        return C_AUTH_LOGIN;
    }

}
