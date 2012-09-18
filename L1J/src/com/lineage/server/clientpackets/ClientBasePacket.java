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

import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;
import com.lineage.server.ClientThread;

/**
 * 解析客户端封包
 */
public abstract class ClientBasePacket {

    /** 提示信息 */
    private static Logger _log = Logger.getLogger(ClientBasePacket.class
            .getName());

    /** 客户端编码 */
    private static final String CLIENT_LANGUAGE_CODE = Config.CLIENT_LANGUAGE_CODE;

    private byte _decrypt[];

    private int _off;

    /**
     * 载入 byte 阵列
     * 
     * @param abyte0
     */
    public ClientBasePacket(final byte abyte0[]) {
        _log.finest("type=" + this.getType() + ", len=" + abyte0.length);
        this._decrypt = abyte0;
        this._off = 1;
    }

    public ClientBasePacket(final ByteBuffer bytebuffer,
            final ClientThread clientthread) {
    }

    /**
     * 返回一个字符串，表示客户端的数据包类型。("[C] C_DropItem" 等)
     */
    public String getType() {
        return "[C] " + this.getClass().getSimpleName();
    }

    /**
     * 由byte[]中取回一组byte[]
     * 
     * @return
     */
    public byte[] readByte() {
        final byte[] result = new byte[this._decrypt.length - this._off];
        try {
            System.arraycopy(this._decrypt, this._off, result, 0,
                    this._decrypt.length - this._off);
            this._off = this._decrypt.length;
        } catch (final Exception e) {
            _log.log(Level.SEVERE, "OpCode=" + (this._decrypt[0] & 0xff), e);
        }
        return result;
    }

    /**
     * 由byte[]中取回一个 byte
     * 
     * @return
     */
    public int readC() {
        final int i = this._decrypt[this._off++] & 0xff;
        return i;
    }

    /**
     * 由byte[]中取回一个 short
     * 
     * @return
     */
    public int readCH() {
        int i = this._decrypt[this._off++] & 0xff;
        i |= this._decrypt[this._off++] << 8 & 0xff00;
        i |= this._decrypt[this._off++] << 16 & 0xff0000;
        return i;
    }

    /**
     * 由byte[]中取回一个 int
     * 
     * @return
     */
    public int readD() {
        int i = this._decrypt[this._off++] & 0xff;
        i |= this._decrypt[this._off++] << 8 & 0xff00;
        i |= this._decrypt[this._off++] << 16 & 0xff0000;
        i |= this._decrypt[this._off++] << 24 & 0xff000000;
        return i;
    }

    /**
     * 由byte[]中取回一个 double
     * 
     * @return
     */
    public double readF() {
        long l = this._decrypt[this._off++] & 0xff;
        l |= this._decrypt[this._off++] << 8 & 0xff00;
        l |= this._decrypt[this._off++] << 16 & 0xff0000;
        l |= this._decrypt[this._off++] << 24 & 0xff000000;
        l |= (long) this._decrypt[this._off++] << 32 & 0xff00000000L;
        l |= (long) this._decrypt[this._off++] << 40 & 0xff0000000000L;
        l |= (long) this._decrypt[this._off++] << 48 & 0xff000000000000L;
        l |= (long) this._decrypt[this._off++] << 56 & 0xff00000000000000L;
        return Double.longBitsToDouble(l);
    }

    /**
     * 由byte[]中取回一个 short
     * 
     * @return
     */
    public int readH() {
        int i = this._decrypt[this._off++] & 0xff;
        i |= this._decrypt[this._off++] << 8 & 0xff00;
        return i;
    }

    /**
     * 由byte[]中取回一个 String
     * 
     * @return
     */
    public String readS() {
        String s = null;
        try {
            s = new String(this._decrypt, this._off, this._decrypt.length
                    - this._off, CLIENT_LANGUAGE_CODE);
            s = s.substring(0, s.indexOf('\0'));
            this._off += s.getBytes(CLIENT_LANGUAGE_CODE).length + 1;
        } catch (final Exception e) {
            _log.log(Level.SEVERE, "OpCode=" + (this._decrypt[0] & 0xff), e);
        }
        return s;
    }
}
