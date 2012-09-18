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

// 全部staticにしてもいいかもしれない
public class ByteArrayUtil {
    private final byte[] _byteArray;

    public ByteArrayUtil(final byte[] byteArray) {
        this._byteArray = byteArray;
    }

    public String dumpToString() {
        final StringBuffer stringbuffer = new StringBuffer();
        int j = 0;
        for (int k = 0; k < this._byteArray.length; k++) {
            if (j % 16 == 0) {
                stringbuffer.append((new StringBuilder())
                        .append(this.fillHex(k, 4)).append(": ").toString());
            }
            stringbuffer.append((new StringBuilder())
                    .append(this.fillHex(this._byteArray[k] & 0xff, 2))
                    .append(" ").toString());
            if (++j != 16) {
                continue;
            }
            stringbuffer.append("   ");
            int i1 = k - 15;
            for (int l1 = 0; l1 < 16; l1++) {
                final byte byte0 = this._byteArray[i1++];
                if ((byte0 > 31) && (byte0 < 128)) {
                    stringbuffer.append((char) byte0);
                } else {
                    stringbuffer.append('.');
                }
            }

            stringbuffer.append("\n");
            j = 0;
        }

        final int l = this._byteArray.length % 16;
        if (l > 0) {
            for (int j1 = 0; j1 < 17 - l; j1++) {
                stringbuffer.append("   ");
            }

            int k1 = this._byteArray.length - l;
            for (int i2 = 0; i2 < l; i2++) {
                final byte byte1 = this._byteArray[k1++];
                if ((byte1 > 31) && (byte1 < 128)) {
                    stringbuffer.append((char) byte1);
                } else {
                    stringbuffer.append('.');
                }
            }

            stringbuffer.append("\n");
        }
        return stringbuffer.toString();
    }

    private String fillHex(final int i, final int j) {
        String s = Integer.toHexString(i);
        for (int k = s.length(); k < j; k++) {
            s = (new StringBuilder()).append("0").append(s).toString();
        }

        return s;
    }

    public String getTerminatedString(final int i) {
        final StringBuffer stringbuffer = new StringBuffer();
        for (int j = i; (j < this._byteArray.length)
                && (this._byteArray[j] != 0); j++) {
            stringbuffer.append((char) this._byteArray[j]);
        }

        return stringbuffer.toString();
    }
}
