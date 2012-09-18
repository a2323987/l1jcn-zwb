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
import java.io.OutputStream;

/**
 * Base64 OutputStream
 */
public class Base64OutputStream extends OutputStream {

    private OutputStream outputStream = null;

    private int buffer = 0;

    private int bytecounter = 0;

    private int linecounter = 0;

    private int linelength = 0;

    public Base64OutputStream(final OutputStream outputStream) {
        this(outputStream, 76);// 每 76个Byte换行
    }

    public Base64OutputStream(final OutputStream outputStream, final int wrapAt) {
        this.outputStream = outputStream;
        this.linelength = wrapAt;
    }

    @Override
    public void close() throws IOException {
        this.commit();
        this.outputStream.close();
    }

    protected void commit() throws IOException {
        if (this.bytecounter > 0) {
            if ((this.linelength > 0) && (this.linecounter == this.linelength)) {
                this.outputStream.write("\r\n".getBytes());
                this.linecounter = 0;
            }
            final char b1 = Base64.Shared.chars
                    .charAt((this.buffer << 8) >>> 26);
            final char b2 = Base64.Shared.chars
                    .charAt((this.buffer << 14) >>> 26);
            final char b3 = (this.bytecounter < 2) ? Base64.Shared.pad
                    : Base64.Shared.chars.charAt((this.buffer << 20) >>> 26);
            final char b4 = (this.bytecounter < 3) ? Base64.Shared.pad
                    : Base64.Shared.chars.charAt((this.buffer << 26) >>> 26);
            this.outputStream.write(b1);
            this.outputStream.write(b2);
            this.outputStream.write(b3);
            this.outputStream.write(b4);
            this.linecounter += 4;
            this.bytecounter = 0;
            this.buffer = 0;
        }
    }

    @Override
    public void write(final int b) throws IOException {
        final int value = (b & 0xFF) << (16 - (this.bytecounter * 8));
        this.buffer = this.buffer | value;
        this.bytecounter++;
        if (this.bytecounter == 3) {
            this.commit();
        }
    }

}
