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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**
 * 解压缩程序 using Apache ant.jar
 */
public class UnZipUtil {
    /**
     * Zip档解压缩
     * 
     * @param zipFile
     *            要解压缩的档案
     * @param ToPath
     *            目的路径
     */
    public static void unZip(final String zipFile, final String ToPath) {
        try {
            final ZipFile zipfile = new ZipFile(zipFile);
            final Enumeration<?> zipenum = zipfile.getEntries();
            while (zipenum.hasMoreElements()) {
                final ZipEntry ze = (ZipEntry) zipenum.nextElement();
                final File newFile = new File(ToPath, ze.getName());
                final ReadableByteChannel rc = Channels.newChannel(zipfile
                        .getInputStream(ze));
                if (ze.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    final FileOutputStream fos = new FileOutputStream(newFile);
                    final FileChannel fc = fos.getChannel();
                    fc.transferFrom(rc, 0, ze.getSize());
                    fos.close();
                }
            }
            zipfile.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
