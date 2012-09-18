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

import java.util.Map;

import com.lineage.server.utils.collections.Maps;

/**
 * Telnet指令结果
 */
public class TelnetCommandResult {
    private final int _code;

    private final String _codeMessage;

    private final String _result;

    private static final Map<Integer, String> _codeMessages = Maps.newMap();

    public static final int CMD_OK = 0;

    public static final int CMD_NOT_FOUND = 1;

    public static final int CMD_INTERNAL_ERROR = 2;

    static {
        _codeMessages.put(CMD_OK, "OK");
        _codeMessages.put(CMD_NOT_FOUND, "Not Found");
        _codeMessages.put(CMD_INTERNAL_ERROR, "Internal Error");
    }

    public TelnetCommandResult(final int code, final String result) {
        this._code = code;
        this._result = result;
        this._codeMessage = _codeMessages.get(code);
    }

    public int getCode() {
        return this._code;
    }

    public String getCodeMessage() {
        return this._codeMessage;
    }

    public String getResult() {
        return this._result;
    }
}
