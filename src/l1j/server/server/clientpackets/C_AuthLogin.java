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
package l1j.server.server.clientpackets;

import java.util.logging.Logger;

import l1j.server.Config;
import l1j.server.server.Account;
import l1j.server.server.AccountAlreadyLoginException;
import l1j.server.server.ClientThread;
import l1j.server.server.GameServerFullException;
import l1j.server.server.LoginController;
import l1j.server.server.serverpackets.S_CommonNews;
import l1j.server.server.serverpackets.S_LoginResult;
import l1j.server.server.utils.Random; // 设定登入游戏是否给予延迟

// Referenced classes of package l1j.server.server.clientpackets:
// ClientBasePacket

/**
 * 处理要求登入的封包
 */
public class C_AuthLogin extends ClientBasePacket {

	private static final String C_AUTH_LOGIN = "[C] C_AuthLogin";
	private static Logger _log = Logger.getLogger(C_AuthLogin.class.getName());

	public C_AuthLogin(byte[] decrypt, ClientThread client) {
		super(decrypt);
		String accountName = readS().toLowerCase();
		String password = readS();

		String ip = client.getIp();
		String host = client.getHostname();

		_log.finest("Request AuthLogin from user : " + accountName);

		if (!Config.ALLOW_2PC) {
			for (ClientThread tempClient : LoginController.getInstance()
					.getAllAccounts()) {
				if (ip.equalsIgnoreCase(tempClient.getIp())) {
					_log.info("拒绝 2P 登入。account="
							+ accountName + " host=" + host);
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
				_log.warning("account missing for user " + accountName);
			}
		}
		if (account == null || !account.validatePassword(password)) {
			client.sendPacket(new S_LoginResult(
					S_LoginResult.REASON_USER_OR_PASS_WRONG));
			return;
		}
		if (account.isOnlined()) {
			client.sendPacket(new S_LoginResult(S_LoginResult.REASON_ACCOUNT_ALREADY_EXISTS));//原码 REASON_ACCOUNT_IN_USE
			return;
		}
		if (account.isBanned()) { // BANアカウント
			_log.info("禁止登入的帐号尝试登入。account=" + accountName + " host="
					+ host);
			client.sendPacket(new S_LoginResult(
					S_LoginResult.REASON_USER_OR_PASS_WRONG));
			return;
		}

		try {
			LoginController.getInstance().login(client, account);
			// 设定登入游戏是否给予延迟
			if (Config.LOGIN_GAME_DELAY) {
				delay(500,1000);
			}
			// end
			Account.updateLastActive(account, ip); // 更新最后一次登入的时间与IP
			client.setAccount(account);
			client.sendPacket(new S_LoginResult(S_LoginResult.REASON_LOGIN_OK));
			client.sendPacket(new S_CommonNews());
			Account.online(account, true);
		} catch (GameServerFullException e) {
			client.kick();
			_log.info("线上人数已经饱和，切断 (" + client.getHostname()
					+ ") 的连线。");
			return;
		} catch (AccountAlreadyLoginException e) {
			client.kick();
			_log.info("同个帐号已经登入，切断 (" + client.getHostname()
					+ ") 的连线。");
			return;
		}
	}

	@Override
	public String getType() {
		return C_AUTH_LOGIN;
	}

	// 设定登入游戏是否给予延迟
	public void delay(int i,int j) {
		try {
			int rnd = Random.nextInt(i) + j;
			Thread.sleep(rnd);
		} catch (Exception e) {
			_log.info("角色延迟登入系统出错!");
		}
	}
	// end
}