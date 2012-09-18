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
 * 移动控制项
 */
public class MoveUtil {

    /** 角色方向-X */
    private static final int[] HEADING_TABLE_X = { 0, 1, 1, 1, 0, -1, -1, -1 };
    /** 角色方向-Y */
    private static final int[] HEADING_TABLE_Y = { -1, -1, 0, 1, 1, 1, 0, -1 };

    /**
     * 移动坐标
     * 
     * @param loc
     */
    public static void MoveLoc(final int[] loc) {
        loc[0] += MoveX(loc[2]);
        loc[1] += MoveY(loc[2]);
    }

    /**
     * 移动面向
     * 
     * @param loc
     * @param heading
     */
    public static void MoveLoc(final int[] loc, final int heading) {
        loc[0] += MoveX(heading);
        loc[1] += MoveY(heading);
    }

    /**
     * 移动坐标X + 面向
     * 
     * @param x
     * @param heading
     * @return X + 面向 X
     */
    public static int MoveLocX(final int x, final int heading) {
        return x + MoveX(heading);
    }

    /**
     * 移动坐标Y + 面向
     * 
     * @param y
     * @param heading
     * @return y + 面向 y
     */
    public static int MoveLocY(final int y, final int heading) {
        return y + MoveY(heading);
    }

    /**
     * 移动坐标X
     * 
     * @param heading
     * @return 面向 X
     */
    public static int MoveX(final int heading) {
        return HEADING_TABLE_X[heading];
    }

    /**
     * 移动坐标Y
     * 
     * @param heading
     * @return 面向 Y
     */
    public static int MoveY(final int heading) {
        return HEADING_TABLE_Y[heading];
    }

}
