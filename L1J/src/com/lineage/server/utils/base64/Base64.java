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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * BASE64 encode & decode for Binary & Text
 */
public class Base64 {

    class Shared {

        static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

        static final char pad = '=';

    }

    private static void copy(final InputStream inputStream,
            final OutputStream outputStream) throws IOException {
        // 1KB buffer
        final byte[] b = new byte[1024];
        int len;
        while ((len = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, len);
        }
    }

    /**
     * <b> Decodes Binary
     * 
     * @param bytes
     *            来源位元组
     * @return 解码后的位元组
     * @throws RuntimeException
     */
    public static byte[] decode(final byte[] bytes) throws RuntimeException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            decode(inputStream, outputStream);
        } catch (final IOException e) {
            throw new RuntimeException("Unexpected I/O error", e);
        } finally {
            try {
                inputStream.close();
            } catch (final Throwable t) {

            }
            try {
                outputStream.close();
            } catch (final Throwable t) {

            }
        }
        return outputStream.toByteArray();
    }

    /**
     * <b> 将来源串流解码后，输出到目标串流
     * 
     * @param inputStream
     *            来源串流
     * @param outputStream
     *            目标串流
     * @throws IOException
     */
    public static void decode(final File source, final File target)
            throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(target);
            decode(inputStream, outputStream);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (final Throwable t) {

                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final Throwable t) {

                }
            }
        }
    }

    public static void decode(final InputStream inputStream,
            final OutputStream outputStream) throws IOException {
        copy(new Base64InputStream(inputStream), outputStream);
    }

    /**
     * <b> Decodes String
     * 
     * @param str
     *            来源字串
     * @return 解码后的字串
     * @throws RuntimeException
     */
    public static String decode(final String str) throws RuntimeException {
        byte[] bytes;
        try {
            bytes = str.getBytes("ASCII");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII is not supported!", e);
        }
        final byte[] decoded = decode(bytes);
        return new String(decoded);
    }

    /**
     * <b> Decodes String
     * 
     * @param str
     *            来源字串
     * @param charset
     *            字串编码
     * @return 解码后的字串
     * @throws RuntimeException
     */
    public static String decode(final String str, final String charset)
            throws RuntimeException {
        byte[] bytes;
        try {
            bytes = str.getBytes("ASCII");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII is not supported!", e);
        }
        final byte[] decoded = decode(bytes);
        try {
            return new String(decoded, charset);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported charset: " + charset, e);
        }
    }

    /**
     * <b> Encodes Binary
     * 
     * @param bytes
     *            来源位元组
     * @return 编码后的位元组
     * @throws RuntimeException
     */
    public static byte[] encode(final byte[] bytes) throws RuntimeException {
        return encode(bytes, 0);
    }

    /**
     * <b> Encodes Binary
     * 
     * @param bytes
     *            来源位元组
     * @param wrapAt
     * @return 编码后的位元组
     * @throws RuntimeException
     */
    public static byte[] encode(final byte[] bytes, final int wrapAt)
            throws RuntimeException {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            encode(inputStream, outputStream, wrapAt);
        } catch (final IOException e) {
            throw new RuntimeException("Unexpected I/O error", e);
        } finally {
            try {
                inputStream.close();
            } catch (final Throwable t) {

            }
            try {
                outputStream.close();
            } catch (final Throwable t) {

            }
        }
        return outputStream.toByteArray();
    }

    public static void encode(final File source, final File target)
            throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(target);
            Base64.encode(inputStream, outputStream);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (final Throwable t) {

                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final Throwable t) {

                }
            }
        }
    }

    public static void encode(final File source, final File target,
            final int wrapAt) throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(target);
            Base64.encode(inputStream, outputStream, wrapAt);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (final Throwable t) {

                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (final Throwable t) {

                }
            }
        }
    }

    /**
     * <b> 将来源串流编码后，输出到目标串流
     * 
     * @param inputStream
     *            来源串流
     * @param outputStream
     *            目标串流
     * @throws IOException
     */
    public static void encode(final InputStream inputStream,
            final OutputStream outputStream) throws IOException {
        encode(inputStream, outputStream, 0);
    }

    public static void encode(final InputStream inputStream,
            final OutputStream outputStream, final int wrapAt)
            throws IOException {
        final Base64OutputStream aux = new Base64OutputStream(outputStream,
                wrapAt);
        copy(inputStream, aux);
        aux.commit();
    }

    /**
     * <b> Encodes String
     * 
     * @param str
     *            来源字串
     * @return 编码后的字串
     * @throws RuntimeException
     */
    public static String encode(final String str) throws RuntimeException {
        final byte[] bytes = str.getBytes();
        final byte[] encoded = encode(bytes);
        try {
            return new String(encoded, "ASCII");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII is not supported!", e);
        }
    }

    /**
     * <b> Encodes String
     * 
     * @param str
     *            来源字串
     * @param charset
     *            字串的编码
     * @return 编码后的字串
     * @throws RuntimeException
     */
    public static String encode(final String str, final String charset)
            throws RuntimeException {
        byte[] bytes;
        try {
            bytes = str.getBytes(charset);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("Unsupported charset: " + charset, e);
        }
        final byte[] encoded = encode(bytes);
        try {
            return new String(encoded, "ASCII");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII is not supported!", e);
        }
    }
}
