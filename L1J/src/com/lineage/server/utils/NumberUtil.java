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

/**
 * 编号控制项
 */
public class NumberUtil {

    /**
     * 返回一个整数四舍五入或与几个小数位在第二的概率下降。 例如，1.3被四舍五入为30％的概率、70%的几率四舍五入。
     * 
     * @param number
     *            - 原始的数
     * @return 四舍五入的整数
     */
    public static int randomRound(final double number) {
        final double percentage = (number - Math.floor(number)) * 100;

        if (percentage == 0) {
            return ((int) number);
        }
        final int r = Random.nextInt(100);
        if (r < percentage) {
            return ((int) number + 1);
        }
        return ((int) number);
    }
}
