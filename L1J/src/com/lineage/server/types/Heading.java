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
 * MAY BE CONSIDERED TO BE A CONTRACT,
 * THE LICENSOR GRANTS YOU THE RIGHTS CONTAINED 
 * HERE IN CONSIDERATION OF YOUR ACCEPTANCE OF SUCH TERMS AND CONDITIONS.
 * 
 */
package com.lineage.server.types;

import com.lineage.server.utils.IntRange;

/**
 * Class <code>Heading</code> 面向.
 * 
 * @author jrwz
 * @version 2012-5-6上午11:49:26
 * @see com.lineage.server.types
 * @since JDK1.7
 */
public final class Heading {

    /**
     * 坐标方向:上(X, Y-1).
     */
    public static final int UP = 0;
    /**
     * 坐标方向:右上(X+1, Y-1).
     */
    public static final int UP_RIGHT = 1;
    /**
     * 坐标方向:右(X+1, Y).
     */
    public static final int RIGHT = 2;
    /**
     * 坐标方向:右下(X+1, Y+1).
     */
    public static final int DOWN_RIGHT = 3;
    /**
     * 坐标方向:下(X+1, Y+1).
     */
    public static final int DOWN = 4;
    /**
     * 坐标方向:左下(X-1, Y+1).
     */
    public static final int DOWN_LEFT = 5;
    /**
     * 坐标方向:左(X-1, Y).
     */
    public static final int LEFT = 6;
    /**
     * 坐标方向:坐上(X-1, Y-1).
     */
    public static final int UP_LEFT = 7;

    /**
     * 确认面向.
     * 
     * @param heading
     *            面向
     */
    private static void ensureHeading(final int heading) {
        if (!IntRange.includes(heading, 0, 7)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 左转.
     * 
     * @param heading
     *            面向
     * @return 新面向
     */
    public static Integer rotateLeft(int heading) {
        ensureHeading(heading);
        heading--;
        return heading == -1 ? 7 : heading;
    }

    /**
     * 右转.
     * 
     * @param heading
     *            面向
     * @return 新面向
     */
    public static Integer rotateRight(int heading) {
        ensureHeading(heading);
        heading++;
        return heading == 8 ? 0 : heading;
    }

    /** 面向. */
    private Heading() {
    }
}
