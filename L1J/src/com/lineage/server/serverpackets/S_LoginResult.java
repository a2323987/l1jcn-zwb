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
package com.lineage.server.serverpackets;

import com.lineage.server.Opcodes;

/**
 * 登入状态
 */
public class S_LoginResult extends ServerBasePacket {

    public static final String S_LOGIN_RESULT = "[S] S_LoginResult";
    /** 登入伺服器成功 (无讯息 ) */
    public static final int REASON_LOGIN_OK = 0x00;
    /** 账号已经使用中 */
    public static final int REASON_ACCOUNT_IN_USE = 0x16;
    /** 已经有同样的账号 请重新输入 */
    public static final int REASON_ACCOUNT_ALREADY_EXISTS = 0x07;
    /** 无法登入的原因如下： 1.您的帐号密码输入错误。 2.帐号受PlaySafe保护但未用PlaySafe登入。 */
    public static final int REASON_ACCESS_FAILED = 0x08;
    /** 3.帐号受GAMAOTP保护但未用GAMAOTP登入。 4.帐号已升级为GASH+，但未用beanfun! 登入。 */
    public static final int REASON_USER_OR_PASS_WRONG = 0x08;
    /** 5.使用GAMAOTP或PlaySafe或beanfun!登入逾时，请重新登入。 若仍有疑问请洽客服中心02-8024-2002 */
    public static final int REASON_PASS_WRONG = 0x08;

    // public static int REASON_SYSTEM_ERROR = 0x01;

    private byte[] _byte = null;

    public S_LoginResult(final int reason) {
        this.buildPacket(reason);
    }

    private void buildPacket(final int reason) {
        this.writeC(Opcodes.S_OPCODE_LOGINRESULT);
        this.writeC(reason);
        this.writeD(0x00000000);
        this.writeD(0x00000000);
        this.writeD(0x00000000);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return S_LOGIN_RESULT;
    }
}
