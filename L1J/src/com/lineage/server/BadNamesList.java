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
package com.lineage.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import com.lineage.server.utils.StreamUtil;
import com.lineage.server.utils.collections.Lists;

/**
 * 禁止使用名字清单
 */
public class BadNamesList {

    /** 提示信息 */
    private static Logger _log = Logger.getLogger(BadNamesList.class.getName());

    private static BadNamesList _instance;

    public static BadNamesList getInstance() {
        if (_instance == null) {
            _instance = new BadNamesList();
        }
        return _instance;
    }

    private final List<String> _nameList = Lists.newList();

    private BadNamesList() {
        LineNumberReader lnr = null;

        try {
            final File mobDataFile = new File("data/badnames.txt");
            lnr = new LineNumberReader(new BufferedReader(new FileReader(
                    mobDataFile)));

            String line = null;
            while ((line = lnr.readLine()) != null) {
                if ((line.trim().length() == 0) || line.startsWith("#")) { // 跳过注解
                    continue;
                }
                final StringTokenizer st = new StringTokenizer(line, ";");

                while (st.hasMoreTokens()) {
                    this._nameList.add(st.nextToken());
                }
            }

            _log.config("加载 " + this._nameList.size() + " bad names");
        } catch (final FileNotFoundException e) {
            _log.warning("badnames.txt 数据文件夹丢失.");
        } catch (final Exception e) {
            _log.warning("error while loading bad names list : " + e);
        } finally {
            StreamUtil.close(lnr);
        }
    }

    public String[] getAllBadNames() {
        return this._nameList.toArray(new String[this._nameList.size()]);
    }

    public boolean isBadName(final String name) {
        for (final String badName : this._nameList) {
            if (name.toLowerCase().contains(badName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
