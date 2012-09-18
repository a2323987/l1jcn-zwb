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
package com.lineage.server.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.lineage.Config;

/**
 * 二进制输出流
 */
public class BinaryOutputStream extends OutputStream {

    /**
     * 客户端编码
     */
    private static final String CLIENT_LANGUAGE_CODE = Config.CLIENT_LANGUAGE_CODE;

    private final ByteArrayOutputStream _bao = new ByteArrayOutputStream();

    public BinaryOutputStream() {
    }

    public byte[] getBytes() {
        return this._bao.toByteArray();
    }

    public int getLength() {
        return this._bao.size() + 2;
    }

    @Override
    public void write(final int b) throws IOException {
        this._bao.write(b);
    }

    public void writeByte(final byte[] text) {
        try {
            if (text != null) {
                this._bao.write(text);
            }
        } catch (final Exception e) {
        }
    }

    public void writeC(final int value) {
        this._bao.write(value & 0xff);
    }

    public void writeD(final int value) {
        this._bao.write(value & 0xff);
        this._bao.write(value >> 8 & 0xff);
        this._bao.write(value >> 16 & 0xff);
        this._bao.write(value >> 24 & 0xff);
    }

    public void writeF(final double org) {
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

    public void writeH(final int value) {
        this._bao.write(value & 0xff);
        this._bao.write(value >> 8 & 0xff);
    }

    public void writeL(final long value) {
        this._bao.write((int) (value & 0xff));
    }

    public void writeP(final int value) {
        this._bao.write(value);
    }

    public void writeS(final String text) {
        try {
            if (text != null) {
                this._bao.write(text.getBytes(CLIENT_LANGUAGE_CODE));
            }
        } catch (final Exception e) {
        }

        this._bao.write(0);
    }
}
