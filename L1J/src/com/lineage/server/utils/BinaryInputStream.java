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

import java.io.IOException;
import java.io.InputStream;

/**
 * 二进制输入流
 */
public class BinaryInputStream extends InputStream {
    InputStream _in;

    /**
     * 二进制输入流
     * 
     * @param in
     */
    public BinaryInputStream(final InputStream in) {
        this._in = in;
    }

    @Override
    public int available() throws IOException {
        return this._in.available();
    }

    @Override
    public void close() throws IOException {
        this._in.close();
    }

    @Override
    public int read() throws IOException {
        return this._in.read();
    }

    public int readByte() throws IOException {
        return this._in.read();
    }

    public int readInt() throws IOException {
        return this.readShort() | ((this.readShort() << 16) & 0xFFFF0000);
    }

    public int readShort() throws IOException {
        return this._in.read() | ((this._in.read() << 8) & 0xFF00);
    }

    @Override
    public long skip(final long n) throws IOException {
        return this._in.skip(n);
    }
}
