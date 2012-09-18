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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.Config;

/**
 * 服务器基础封包
 */
public abstract class ServerBasePacket {

    private static Logger _log = Logger.getLogger(ServerBasePacket.class
            .getName());

    private static final String CLIENT_LANGUAGE_CODE = Config.CLIENT_LANGUAGE_CODE;

    ByteArrayOutputStream _bao = new ByteArrayOutputStream();

    protected ServerBasePacket() {
    }

    public byte[] getBytes() {
        final int padding = this._bao.size() % 4;

        if (padding != 0) {
            for (int i = padding; i < 4; i++) {
                this.writeC(0x00);
            }
        }

        return this._bao.toByteArray();
    }

    public abstract byte[] getContent() throws IOException;

    public int getLength() {
        return this._bao.size() + 2;
    }

    /**
     * 返回服务器数据包类型的文字信息。("[S] S_WhoAmount" 等)
     */
    public String getType() {
        return "[S] " + this.getClass().getSimpleName();
    }

    protected void writeByte(final byte[] text) {
        try {
            if (text != null) {
                this._bao.write(text);
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }
    }

    protected void writeC(final int value) {
        this._bao.write(value & 0xff);
    }

    protected void writeD(final int value) {
        this._bao.write(value & 0xff);
        this._bao.write(value >> 8 & 0xff);
        this._bao.write(value >> 16 & 0xff);
        this._bao.write(value >> 24 & 0xff);
    }

    protected void writeExp(final long value) {
        this._bao.write((int) (value & 0xff));
        this._bao.write((int) (value >> 8 & 0xff));
        this._bao.write((int) (value >> 16 & 0xff));
        this._bao.write((int) (value >> 24 & 0xff));
    }

    protected void writeF(final double org) {
        final long value = Double.doubleToRawLongBits(org);
        this._bao.write((int) (value & 0xff));
        this._bao.write((int) (value >> 8 & 0xff));
        this._bao.write((int) (value >> 16 & 0xff));
        this._bao.write((int) (value >> 24 & 0xff));
        this._bao.write((int) (value >> 32 & 0xff));
        this._bao.write((int) (value >> 40 & 0xff));
        this._bao.write((int) (value >> 48 & 0xff));
        this._bao.write((int) (value >> 56 & 0xff));
    }

    protected void writeH(final int value) {
        this._bao.write(value & 0xff);
        this._bao.write(value >> 8 & 0xff);
    }

    protected void writeL(final long value) {
        this._bao.write((int) (value & 0xff));
    }

    protected void writeP(final int value) {
        this._bao.write(value);
    }

    protected void writeS(final String text) {
        try {
            if (text != null) {
                this._bao.write(text.getBytes(CLIENT_LANGUAGE_CODE));
            }
        } catch (final Exception e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        }

        this._bao.write(0);
    }
}
