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
package com.lineage.server.model.map;

import com.lineage.server.types.Point;

/**
 * L1Map 保存地图信息提供各种接口。
 */
public abstract class L1Map {

    private static L1NullMap _nullMap = new L1NullMap();

    public static L1Map newNull() {
        return _nullMap;
    }

    protected L1Map() {
    }

    /**
     * 取得地图高度 (终点Y - 起点Y)
     * 
     * @return
     */
    public abstract int getHeight();

    /**
     * 取得地图ID。
     * 
     * @return 地图ID
     */
    public abstract int getId();

    /**
     * 传回指定的坐标值。 不推荐。这种方法是为兼容现有的代码。 L1Map用户通常需要知道什么是地图存储在不值。
     * 此外，编写代码，就值这不是存储而定。只有在特殊情况下，如调试，您可以使用此方法。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 坐标指定值 0 : 无法通过 15: 一般区域 31: 安全区域 47: 战斗区域
     */
    public abstract int getOriginalTile(int x, int y);

    /**
     * 传回指定的坐标值。 不推荐。这种方法是为兼容现有的代码。 L1Map用户通常需要知道什么是地图存储在不值。
     * 此外，编写代码，就值这不是存储而定。只有在特殊情况下，如调试，您可以使用此方法。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 坐标指定值
     */
    public abstract int getTile(int x, int y);

    /**
     * 取得地图宽度 (终点X - 起点X)
     * 
     * @return
     */
    public abstract int getWidth();

    // TODO JavaDoc
    /**
     * 取得起点X
     * 
     * @return
     */
    public abstract int getX();

    /**
     * 取得起点Y
     * 
     * @return
     */
    public abstract int getY();

    /**
     * 传回指定的坐标远程攻击能否通过。 (魔法箭)
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 远程攻击可以通过返回 true
     */
    public abstract boolean isArrowPassable(int x, int y);

    /**
     * 传回指定的坐标远程攻击能否通过。 (一般箭)
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @param heading
     *            方向
     * @return 远程攻击可以通过返回 true
     */
    public abstract boolean isArrowPassable(int x, int y, int heading);

    /**
     * 传回指定的坐标远程攻击能否通过。 (魔法箭)
     * 
     * @param pt
     *            资料
     * @return 远程攻击可以通过返回 true
     */
    public abstract boolean isArrowPassable(Point pt);

    /**
     * 传回指定的坐标远程攻击能否通过。 (一般箭)
     * 
     * @param pt
     *            坐标 (Point)，其中包含的坐标
     * @param heading
     *            方向
     * @return 远程攻击可以通过返回 true
     */
    public abstract boolean isArrowPassable(Point pt, int heading);

    /**
     * 传回指定坐标是战斗区域。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 战斗区域返回 true
     */
    public abstract boolean isCombatZone(int x, int y);

    /**
     * 传回指定坐标是战斗区域。
     * 
     * @param pt
     *            坐标资料
     * @return 战斗区域返回 true
     */
    public abstract boolean isCombatZone(Point pt);

    /**
     * 传回这张地图是否有死亡惩罚。
     * 
     * @return 如果有死亡惩罚、true
     */
    public abstract boolean isEnabledDeathPenalty();

    /**
     * 传回这张地图是否可使用回家卷。
     * 
     * @return 可使用回家卷、true
     */
    public abstract boolean isEscapable();

    /**
     * 传回指定坐标是否有一个门。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 如果有门true
     */
    public abstract boolean isExistDoor(int x, int y);

    /**
     * 传回指定坐标是否可钓鱼。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 如果可钓鱼、true
     */
    public abstract boolean isFishingZone(int x, int y);

    /**
     * 传回坐标是否在地图指定的可用位置。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 范围内可用 true
     */
    public abstract boolean isInMap(int x, int y);

    /**
     * 传回坐标是否在地图指定的可用位置。
     * 
     * @param pt
     *            坐标 (Point)，其中包含的坐标
     * @return 范围内可用 true
     */
    public abstract boolean isInMap(Point pt);

    /**
     * 传回这张地图是否有可记忆坐标。
     * 
     * @return 如果可记忆坐标、true
     */
    public abstract boolean isMarkable();

    /**
     * 传回指定坐标是一般区域。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 一般区域返回 true
     */
    public abstract boolean isNormalZone(int x, int y);

    /**
     * 传回指定坐标是一般区域。
     * 
     * @param pt
     *            坐标资料
     * @return 一般区域返回 true
     */
    public abstract boolean isNormalZone(Point pt);

    /**
     * 传回此地图是否为空 (null)。
     * 
     * @return 如果空 (null)、true
     */
    public boolean isNull() {
        return false;
    }

    /**
     * 传回指定的坐标能否通过。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 可以通过 true 不能通过 false
     */
    public abstract boolean isPassable(int x, int y);

    /**
     * 传回指定的坐标heading方向能否通过。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 可以通过 true 不能通过 false
     */
    public abstract boolean isPassable(int x, int y, int heading);

    /**
     * 传回指定的坐标能否通过。
     * 
     * @param pt
     *            坐标 (Point)，其中包含的坐标
     * @return 可以通过 true 不能通过 false
     */
    public abstract boolean isPassable(Point pt);

    /**
     * 传回指定的坐标heading方向能否通过。
     * 
     * @param pt
     *            坐标 (Point)，其中包含的坐标
     * @return 可以通过 true 不能通过 false
     */
    public abstract boolean isPassable(Point pt, int heading);

    /**
     * 传回这张地图是否可召唤宠物。
     * 
     * @return 如果可召唤宠物、true
     */
    public abstract boolean isRecallPets();

    /**
     * 传回指定的坐标位置是安全区域。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @return 安全区域返回 true
     */
    public abstract boolean isSafetyZone(int x, int y);

    /**
     * 传回指定的坐标位置是安全区域。
     * 
     * @param pt
     *            坐标资料
     * @return 安全区域返回 true
     */
    public abstract boolean isSafetyZone(Point pt);

    /**
     * 传回这张地图是否可携带宠物。
     * 
     * @return 如果可携带宠物、true
     */
    public abstract boolean isTakePets();

    /**
     * 传回这张地图是否随机传送。
     * 
     * @return 如果可随机传送、true
     */
    public abstract boolean isTeleportable();

    /**
     * 传回这张地图是否在水下。
     * 
     * @return 如果在水中、true
     */
    public abstract boolean isUnderwater();

    /**
     * 传回这张地图是否可以使用道具。
     * 
     * @return 如果可使用道具、true
     */
    public abstract boolean isUsableItem();

    /**
     * 传回这张地图是否可以使用技能。
     * 
     * @return 如果可使用技能、true
     */
    public abstract boolean isUsableSkill();

    /**
     * 传回这张地图是否可使用魔杖。
     * 
     * @return 如果可使用魔杖、true
     */
    public abstract boolean isUsePainwand();

    /**
     * 传回这张地图是否可复活。
     * 
     * @return 如果能复活、true
     */
    public abstract boolean isUseResurrection();

    /**
     * 设置坐标障碍宣告。
     * 
     * @param x
     *            坐标的X值
     * @param y
     *            坐标的Y值
     * @param isPassable
     *            可以通过 true 不能通过 false
     */
    public abstract void setPassable(int x, int y, boolean isPassable);

    /**
     * 设置坐标障碍宣告。
     * 
     * @param pt
     *            坐标 (Point)，其中包含的坐标
     * @param isPassable
     *            可以通过 true 不能通过 false
     */
    public abstract void setPassable(Point pt, boolean isPassable);

    /**
     * 返回指定的字符串表示PT。
     */
    public abstract String toString(Point pt);
}

/**
 * 什么都不做的地图 (Map)。
 */
class L1NullMap extends L1Map {
    public L1NullMap() {
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public int getOriginalTile(final int x, final int y) {
        return 0;
    }

    @Override
    public int getTile(final int x, final int y) {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public boolean isArrowPassable(final int x, final int y) {
        return false;
    }

    @Override
    public boolean isArrowPassable(final int x, final int y, final int heading) {
        return false;
    }

    @Override
    public boolean isArrowPassable(final Point pt) {
        return false;
    }

    @Override
    public boolean isArrowPassable(final Point pt, final int heading) {
        return false;
    }

    @Override
    public boolean isCombatZone(final int x, final int y) {
        return false;
    }

    @Override
    public boolean isCombatZone(final Point pt) {
        return false;
    }

    @Override
    public boolean isEnabledDeathPenalty() {
        return false;
    }

    @Override
    public boolean isEscapable() {
        return false;
    }

    @Override
    public boolean isExistDoor(final int x, final int y) {
        return false;
    }

    @Override
    public boolean isFishingZone(final int x, final int y) {
        return false;
    }

    @Override
    public boolean isInMap(final int x, final int y) {
        return false;
    }

    @Override
    public boolean isInMap(final Point pt) {
        return false;
    }

    @Override
    public boolean isMarkable() {
        return false;
    }

    @Override
    public boolean isNormalZone(final int x, final int y) {
        return false;
    }

    @Override
    public boolean isNormalZone(final Point pt) {
        return false;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public boolean isPassable(final int x, final int y) {
        return false;
    }

    @Override
    public boolean isPassable(final int x, final int y, final int heading) {
        return false;
    }

    @Override
    public boolean isPassable(final Point pt) {
        return false;
    }

    @Override
    public boolean isPassable(final Point pt, final int heading) {
        return false;
    }

    @Override
    public boolean isRecallPets() {
        return false;
    }

    @Override
    public boolean isSafetyZone(final int x, final int y) {
        return false;
    }

    @Override
    public boolean isSafetyZone(final Point pt) {
        return false;
    }

    @Override
    public boolean isTakePets() {
        return false;
    }

    @Override
    public boolean isTeleportable() {
        return false;
    }

    @Override
    public boolean isUnderwater() {
        return false;
    }

    @Override
    public boolean isUsableItem() {
        return false;
    }

    @Override
    public boolean isUsableSkill() {
        return false;
    }

    @Override
    public boolean isUsePainwand() {
        return false;
    }

    @Override
    public boolean isUseResurrection() {
        return false;
    }

    @Override
    public void setPassable(final int x, final int y, final boolean isPassable) {
    }

    @Override
    public void setPassable(final Point pt, final boolean isPassable) {
    }

    @Override
    public String toString(final Point pt) {
        return "null";
    }
}
