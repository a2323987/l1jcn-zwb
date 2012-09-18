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

import com.lineage.server.Account;
import com.lineage.server.ClientThread;
import com.lineage.server.LoginController;

// Referenced classes of package com.lineage.server.clientpackets:
// ClientBasePacket

/**
 * 处理收到由客户端传来回到登入画面的封包
 * 
 * @author jrwz
 */
public class C_ReturnToLogin extends ClientBasePacket {

    private static Logger _log = Logger.getLogger(C_ReturnToLogin.class
            .getName());

    public C_ReturnToLogin(final byte decrypt[], final ClientThread client)
            throws Exception {
        super(decrypt);

        // 修正 (经常卡在帐号密码或是提示帐号在使用中)
        if (client.getAccount() != null) {
            Account.online(client.getAccount(), false);
            final String account = client.getAccountName();
            final String ip = client.getIp();
            _log.info("回到登入画面--->> 账号：" + account + " IP：" + ip);
            LoginController.getInstance().logout(client);
        }
    }

    @Override
    public String getType() {
        return "[C] C_ReturnToLogin";
    }

}
