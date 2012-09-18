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
package com.lineage.server.types;

/**
 * 座標左上の点(left, top)、及び右下の点(right, bottom)によって囲まれる座標の領域を指定するクラス。
 */
public class Rectangle {
    private int _left;
    private int _top;
    private int _right;
    private int _bottom;

    public Rectangle() {
        this(0, 0, 0, 0);
    }

    public Rectangle(final int left, final int top, final int right,
            final int bottom) {
        this.set(left, top, right, bottom);
    }

    public Rectangle(final Rectangle rect) {
        this.set(rect);
    }

    /**
     * 指定された点(x, y)が、このRectangleの範囲内にあるかを判定する。
     * 
     * @param x
     *            判定する点のX座標
     * @param y
     *            判定する点のY座標
     * @return 点(x, y)がこのRectangleの範囲内にある場合、true。
     */
    public boolean contains(final int x, final int y) {
        return ((this._left <= x) && (x <= this._right))
                && ((this._top <= y) && (y <= this._bottom));
    }

    /**
     * 指定されたPointが、このRectangleの範囲内にあるかを判定する。
     * 
     * @param pt
     *            判定するPoint
     * @return ptがこのRectangleの範囲内にある場合、true。
     */
    public boolean contains(final Point pt) {
        return this.contains(pt.getX(), pt.getY());
    }

    public int getBottom() {
        return this._bottom;
    }

    public int getHeight() {
        return this._bottom - this._top;
    }

    public int getLeft() {
        return this._left;
    }

    public int getRight() {
        return this._right;
    }

    public int getTop() {
        return this._top;
    }

    public int getWidth() {
        return this._right - this._left;
    }

    public void set(final int left, final int top, final int right,
            final int bottom) {
        this._left = left;
        this._top = top;
        this._right = right;
        this._bottom = bottom;
    }

    public void set(final Rectangle rect) {
        this.set(rect.getLeft(), rect.getTop(), rect.getWidth(),
                rect.getHeight());
    }
}
