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
 * 取回随机值
 */
public class Dice {

    /** 基础值 */
    private final int _faces;

    /**
     * 取回随机值
     * 
     * @param faces
     *            基础值
     */
    public Dice(final int faces) {
        this._faces = faces;
    }

    /**
     * 取回基础值
     * 
     * @return faces 基础值
     */
    public int getFaces() {
        return this._faces;
    }

    /**
     * 单次随机值
     * 
     * @return
     */
    public int roll() {
        return Random.nextInt(this._faces) + 1;
    }

    /**
     * 多次随机值
     * 
     * @param count
     *            计算次数
     * @return 多次随机值总和
     */
    public int roll(final int count) {
        int n = 0;
        for (int i = 0; i < count; i++) {
            n += this.roll();
        }
        return n;
    }
}
