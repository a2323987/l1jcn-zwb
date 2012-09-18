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
package com.lineage.server.utils.base64;

import java.io.IOException;
import java.io.InputStream;

/**
 * Base64 InputStream
 */
public class Base64InputStream extends InputStream {

    private final InputStream inputStream;

    private int[] buffer;

    private int bufferCounter = 0;

    private boolean eof = false; // 结束旗标

    public Base64InputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private void acquire() throws IOException {
        final char[] four = new char[4];
        int i = 0;
        do {
            final int b = this.inputStream.read();
            if (b == -1) {
                if (i != 0) {
                    throw new IOException("Bad base64 stream");
                }
                this.buffer = new int[0];
                this.eof = true;
                return;
            }
            final char c = (char) b;
            if ((Base64.Shared.chars.indexOf(c) != -1)
                    || (c == Base64.Shared.pad)) {
                four[i++] = c;
            } else if ((c != '\r') && (c != '\n')) {
                throw new IOException("Bad base64 stream");
            }
        } while (i < 4);
        boolean padded = false;
        for (i = 0; i < 4; i++) {
            if (four[i] != Base64.Shared.pad) {
                if (padded) {
                    throw new IOException("Bad base64 stream");
                }
            } else {
                if (!padded) {
                    padded = true;
                }
            }
        }
        int l;
        if (four[3] == Base64.Shared.pad) {
            if (this.inputStream.read() != -1) {
                throw new IOException("Bad base64 stream");
            }
            this.eof = true;
            if (four[2] == Base64.Shared.pad) {
                l = 1;
            } else {
                l = 2;
            }
        } else {
            l = 3;
        }
        int aux = 0;
        for (i = 0; i < 4; i++) {
            if (four[i] != Base64.Shared.pad) {
                aux = aux
                        | (Base64.Shared.chars.indexOf(four[i]) << (6 * (3 - i)));
            }
        }
        this.buffer = new int[l];
        for (i = 0; i < l; i++) {
            this.buffer[i] = (aux >>> (8 * (2 - i))) & 0xFF;
        }
    }

    @Override
    public void close() throws IOException {
        this.inputStream.close();
    }

    @Override
    public int read() throws IOException {
        if ((this.buffer == null) || (this.bufferCounter == this.buffer.length)) {
            if (this.eof) {
                return -1;
            }
            this.acquire();
            if (this.buffer.length == 0) {
                this.buffer = null;
                return -1;
            }
            this.bufferCounter = 0;
        }
        return this.buffer[this.bufferCounter++];
    }
}
