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

import com.lineage.server.ActionCodes;
import com.lineage.server.datatables.DoorTable;
import com.lineage.server.model.L1Location;
import com.lineage.server.model.Instance.L1DoorInstance;
import com.lineage.server.types.Heading;
import com.lineage.server.types.Point;

/**
 * V1地图
 */
public class L1V1Map extends L1Map {
    private int _mapId;

    private int _worldTopLeftX;

    private int _worldTopLeftY;

    private int _worldBottomRightX;

    private int _worldBottomRightY;

    private byte _map[][];

    private boolean _isUnderwater;

    private boolean _isMarkable;

    private boolean _isTeleportable;

    private boolean _isEscapable;

    private boolean _isUseResurrection;

    private boolean _isUsePainwand;

    private boolean _isEnabledDeathPenalty;

    private boolean _isTakePets;

    private boolean _isRecallPets;

    private boolean _isUsableItem;

    private boolean _isUsableSkill;

    /**
     * Mob不能通过 表示该对象目前不能通过的位置
     */
    private static final byte BITFLAG_IS_IMPASSABLE = (byte) 128; // 1000 0000

    protected L1V1Map() {

    }

    public L1V1Map(final int mapId, final byte map[][],
            final int worldTopLeftX, final int worldTopLeftY,
            final boolean underwater, final boolean markable,
            final boolean teleportable, final boolean escapable,
            final boolean useResurrection, final boolean usePainwand,
            final boolean enabledDeathPenalty, final boolean takePets,
            final boolean recallPets, final boolean usableItem,
            final boolean usableSkill) {
        this._mapId = mapId;
        this._map = map;
        this._worldTopLeftX = worldTopLeftX;
        this._worldTopLeftY = worldTopLeftY;

        this._worldBottomRightX = worldTopLeftX + map.length - 1;
        this._worldBottomRightY = worldTopLeftY + map[0].length - 1;

        this._isUnderwater = underwater;
        this._isMarkable = markable;
        this._isTeleportable = teleportable;
        this._isEscapable = escapable;
        this._isUseResurrection = useResurrection;
        this._isUsePainwand = usePainwand;
        this._isEnabledDeathPenalty = enabledDeathPenalty;
        this._isTakePets = takePets;
        this._isRecallPets = recallPets;
        this._isUsableItem = usableItem;
        this._isUsableSkill = usableSkill;
    }

    public L1V1Map(final L1V1Map map) {
        this._mapId = map._mapId;

        // _map复制
        this._map = new byte[map._map.length][];
        for (int i = 0; i < map._map.length; i++) {
            this._map[i] = map._map[i].clone();
        }

        this._worldTopLeftX = map._worldTopLeftX;
        this._worldTopLeftY = map._worldTopLeftY;
        this._worldBottomRightX = map._worldBottomRightX;
        this._worldBottomRightY = map._worldBottomRightY;

    }

    private int accessOriginalTile(final int x, final int y) {
        return this.accessTile(x, y) & (~BITFLAG_IS_IMPASSABLE);
    }

    private int accessTile(final int x, final int y) {
        if (!this.isInMap(x, y)) { // XXX 无论怎样请检查。这是不好的。
            return 0;
        }
        return this._map[x - this._worldTopLeftX][y - this._worldTopLeftY];
    }

    // 地图高度
    @Override
    public int getHeight() {
        return this._worldBottomRightY - this._worldTopLeftY + 1;
    }

    // 取得地图编号
    @Override
    public int getId() {
        return this._mapId;
    }

    // 传回指定的坐标值
    @Override
    public int getOriginalTile(final int x, final int y) {
        return this.accessOriginalTile(x, y);
    }

    /*
     * 恐怕不是很好
     */
    public byte[][] getRawTiles() {
        return this._map;
    }

    // 传回指定的坐标值
    @Override
    public int getTile(final int x, final int y) {
        final short tile = this._map[x - this._worldTopLeftX][y
                - this._worldTopLeftY];
        if (0 != (tile & BITFLAG_IS_IMPASSABLE)) {
            return 300;
        }
        return this.accessOriginalTile(x, y);
    }

    // 地图宽度
    @Override
    public int getWidth() {
        return this._worldBottomRightX - this._worldTopLeftX + 1;
    }

    // 起点坐标 X
    @Override
    public int getX() {
        return this._worldTopLeftX;
    }

    // 起点坐标 Y
    @Override
    public int getY() {
        return this._worldTopLeftY;
    }

    // 远程攻击能否通过 - 魔法箭
    @Override
    public boolean isArrowPassable(final int x, final int y) {
        return (this.accessOriginalTile(x, y) & 0x0e) != 0;
    }

    // 远程攻击能否通过 - 一般箭
    @Override
    public boolean isArrowPassable(final int x, final int y, final int heading) {
        final L1Location newLoc = new L1Location(x, y, this._mapId);
        newLoc.forward(heading);
        // 目前的坐标
        final int tile1 = this.accessTile(x, y);
        // 移动后的坐标
        final int tile2 = this.accessTile(newLoc.getX(), newLoc.getY());

        if (heading == Heading.UP) {
            final int dir = DoorTable.getInstance().getDoorDirection(newLoc);
            return ((tile1 & 0x08) == 0x08) && (dir != 0);
        } else if (heading == Heading.UP_RIGHT) {
            return this.isArrowPassableUpThenRight(x, y)
                    || this.isArrowPassableRightThenUp(x, y);
        } else if (heading == Heading.RIGHT) {
            final int dir = DoorTable.getInstance().getDoorDirection(newLoc);
            return ((tile1 & 0x04) == 0x04) && (dir != 1);
        } else if (heading == Heading.DOWN_RIGHT) {
            return this.isArrowPassableRightThenDown(x, y)
                    || this.isArrowPassableDownThenRight(x, y);
        } else if (heading == Heading.DOWN) {
            final int dir = DoorTable.getInstance().getDoorDirection(
                    new L1Location(x, y, this._mapId));
            return ((tile2 & 0x08) == 0x08) && (dir != 0);
        } else if (heading == Heading.DOWN_LEFT) {
            return this.isArrowPassableDownThenLeft(x, y)
                    || this.isArrowPassableLeftThenDown(x, y);
        } else if (heading == Heading.LEFT) {
            final int dir = DoorTable.getInstance().getDoorDirection(
                    new L1Location(x, y, this._mapId));
            return ((tile2 & 0x04) == 0x04) && (dir != 1);
        } else if (heading == Heading.UP_LEFT) {
            return this.isArrowPassableUpThenLeft(x, y)
                    || this.isArrowPassableLeftThenUp(x, y);
        }
        return false;
    }

    // 远程攻击能否通过 - 魔法箭
    @Override
    public boolean isArrowPassable(final Point pt) {
        return this.isArrowPassable(pt.getX(), pt.getY());
    }

    // 远程攻击能否通过 - 一般箭
    @Override
    public boolean isArrowPassable(final Point pt, final int heading) {
        return this.isArrowPassable(pt.getX(), pt.getY(), heading);
    }

    private boolean isArrowPassableDownThenLeft(final int x, final int y) {
        return this.isArrowPassable(x, y, Heading.DOWN)
                && this.isArrowPassable(x, y + 1, Heading.LEFT);
    }

    private boolean isArrowPassableDownThenRight(final int x, final int y) {
        return this.isArrowPassable(x, y, Heading.DOWN)
                && this.isArrowPassable(x, y, Heading.RIGHT);
    }

    private boolean isArrowPassableLeftThenDown(final int x, final int y) {
        return this.isArrowPassable(x, y, Heading.LEFT)
                && this.isArrowPassable(x - 1, y, Heading.DOWN);
    }

    private boolean isArrowPassableLeftThenUp(final int x, final int y) {
        return this.isArrowPassable(x, y, Heading.LEFT)
                && this.isArrowPassable(x - 1, y, Heading.UP);
    }

    private boolean isArrowPassableRightThenDown(final int x, final int y) {
        return (this.isArrowPassable(x, y, Heading.RIGHT) && this
                .isArrowPassable(x + 1, y, Heading.DOWN));
    }

    private boolean isArrowPassableRightThenUp(final int x, final int y) {
        return this.isArrowPassable(x, y, 2)
                && this.isArrowPassable(x + 1, y, 0);
    }

    private boolean isArrowPassableUpThenLeft(final int x, final int y) {
        return this.isArrowPassable(x, y, Heading.UP)
                && this.isArrowPassable(x, y, Heading.LEFT);
    }

    private boolean isArrowPassableUpThenRight(final int x, final int y) {
        return (this.isArrowPassable(x, y, Heading.UP) && this.isArrowPassable(
                x, y - 1, Heading.RIGHT));
    }

    // 指定的坐标为 - 战斗区域
    @Override
    public boolean isCombatZone(final int x, final int y) {
        final int tile = this.accessOriginalTile(x, y);

        return (tile & 0x30) == 0x20;
    }

    // 指定的坐标为 - 战斗区域
    @Override
    public boolean isCombatZone(final Point pt) {
        return this.isCombatZone(pt.getX(), pt.getY());
    }

    // 死亡是否受到惩罚
    @Override
    public boolean isEnabledDeathPenalty() {
        return this._isEnabledDeathPenalty;
    }

    // 能否使用回家卷轴
    @Override
    public boolean isEscapable() {
        return this._isEscapable;
    }

    // 传回指定的坐标是否有一个门
    @Override
    public boolean isExistDoor(final int x, final int y) {
        for (final L1DoorInstance door : DoorTable.getInstance().getDoorList()) {
            if (this._mapId != door.getMapId()) {
                continue; // 门所在地图不同
            }
            if (door.getOpenStatus() == ActionCodes.ACTION_Open) {
                continue; // 门的状态 (开启)
            }
            if (door.isDead()) {
                continue; // 门的状态 (死亡)
            }
            final int leftEdgeLocation = door.getLeftEdgeLocation();
            final int rightEdgeLocation = door.getRightEdgeLocation();
            final int size = rightEdgeLocation - leftEdgeLocation;
            if (size == 0) { // 门的宽度1
                if ((x == door.getX()) && (y == door.getY())) {
                    return true;
                }
            } else { // 门的宽度超过2以上
                if (door.getDirection() == 0) { // ／方向
                    for (int doorX = leftEdgeLocation; doorX <= rightEdgeLocation; doorX++) {
                        if ((x == doorX) && (y == door.getY())) {
                            return true;
                        }
                    }
                } else { // ＼方向
                    for (int doorY = leftEdgeLocation; doorY <= rightEdgeLocation; doorY++) {
                        if ((x == door.getX()) && (y == doorY)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // 传回指定的坐标能否钓鱼
    @Override
    public boolean isFishingZone(final int x, final int y) {
        return this.accessOriginalTile(x, y) == 28; // 3.3C 钓鱼池可钓鱼区域
    }

    // 传回坐标是否在地图指定的可用位置。
    @Override
    public boolean isInMap(final int x, final int y) {
        // 确定的棕色领域的地区
        if ((this._mapId == 4)
                && ((x < 32520) || (y < 32070) || ((y < 32190) && (x < 33950)))) {
            return false;
        }
        return ((this._worldTopLeftX <= x) && (x <= this._worldBottomRightX)
                && (this._worldTopLeftY <= y) && (y <= this._worldBottomRightY));
    }

    // 传回坐标是否在地图指定的可用位置。
    @Override
    public boolean isInMap(final Point pt) {
        return this.isInMap(pt.getX(), pt.getY());
    }

    // 能否记忆坐标
    @Override
    public boolean isMarkable() {
        return this._isMarkable;
    }

    // 指定的坐标为 - 一般区域
    @Override
    public boolean isNormalZone(final int x, final int y) {
        final int tile = this.accessOriginalTile(x, y);
        return (tile & 0x30) == 0x00;
    }

    // 指定的坐标为 - 一般区域
    @Override
    public boolean isNormalZone(final Point pt) {
        return this.isNormalZone(pt.getX(), pt.getY());
    }

    // 指定的坐标能否通过
    @Override
    public boolean isPassable(final int x, final int y) {
        return this.isPassable(x, y - 1, 4) || this.isPassable(x + 1, y, 6)
                || this.isPassable(x, y + 1, 0) || this.isPassable(x - 1, y, 2);
    }

    // 指定的坐标能否通过
    @Override
    public boolean isPassable(final int x, final int y, final int heading) {
        final L1Location newLoc = new L1Location(x, y, this._mapId);
        newLoc.forward(heading);
        // 目前的坐标
        final int tile1 = this.accessTile(x, y);
        // 移动后的坐标
        final int tile2 = this.accessTile(newLoc.getX(), newLoc.getY());

        if ((tile2 & BITFLAG_IS_IMPASSABLE) == BITFLAG_IS_IMPASSABLE) {
            return false;
        }

        if (heading == Heading.UP) {
            final int dir = DoorTable.getInstance().getDoorDirection(newLoc);
            return ((tile1 & 0x02) == 0x02) && (dir != 0);
        } else if (heading == Heading.UP_RIGHT) {
            return this.isPassableUpThenRight(x, y)
                    || this.isPassableRightThenUp(x, y);
        } else if (heading == Heading.RIGHT) {
            final int dir = DoorTable.getInstance().getDoorDirection(newLoc);
            return ((tile1 & 0x01) == 0x01) && (dir != 1);
        } else if (heading == Heading.DOWN_RIGHT) {
            return this.isPassableRightThenDown(x, y)
                    || this.isPassableDownThenRight(x, y);
        } else if (heading == Heading.DOWN) {
            final int dir = DoorTable.getInstance().getDoorDirection(
                    new L1Location(x, y, this._mapId));
            return ((tile2 & 0x02) == 0x02) && (dir != 0);
        } else if (heading == Heading.DOWN_LEFT) {
            return this.isPassableDownThenLeft(x, y)
                    || this.isPassableLeftThenDown(x, y);
        } else if (heading == Heading.LEFT) {
            final int dir = DoorTable.getInstance().getDoorDirection(
                    new L1Location(x, y, this._mapId));
            return ((tile2 & 0x01) == 0x01) && (dir != 1);
        } else if (heading == Heading.UP_LEFT) {
            return this.isPassableUpThenLeft(x, y)
                    || this.isPassableLeftThenUp(x, y);
        }
        return false;
    }

    // 指定的坐标能否通过
    @Override
    public boolean isPassable(final Point pt) {
        return this.isPassable(pt.getX(), pt.getY());
    }

    // 指定的坐标能否通过
    @Override
    public boolean isPassable(final Point pt, final int heading) {
        return this.isPassable(pt.getX(), pt.getY(), heading);
    }

    private boolean isPassableDownThenLeft(final int x, final int y) {
        return this.isPassable(x, y, Heading.DOWN)
                && this.isPassable(x, y + 1, Heading.LEFT);
    }

    private boolean isPassableDownThenRight(final int x, final int y) {
        return this.isPassable(x, y, Heading.DOWN)
                && this.isPassable(x, y, Heading.RIGHT);
    }

    private boolean isPassableLeftThenDown(final int x, final int y) {
        return this.isPassable(x, y, Heading.LEFT)
                && this.isPassable(x - 1, y, Heading.DOWN);
    }

    private boolean isPassableLeftThenUp(final int x, final int y) {
        return this.isPassable(x, y, Heading.LEFT)
                && this.isPassable(x - 1, y, Heading.UP);
    }

    private boolean isPassableRightThenDown(final int x, final int y) {
        return (this.isPassable(x, y, Heading.RIGHT) && this.isPassable(x + 1,
                y, Heading.DOWN));
    }

    private boolean isPassableRightThenUp(final int x, final int y) {
        return this.isPassable(x, y, 2) && this.isPassable(x + 1, y, 0);
    }

    private boolean isPassableUpThenLeft(final int x, final int y) {
        return this.isPassable(x, y, Heading.UP)
                && this.isPassable(x, y, Heading.LEFT);
    }

    private boolean isPassableUpThenRight(final int x, final int y) {
        return (this.isPassable(x, y, Heading.UP) && this.isPassable(x, y - 1,
                Heading.RIGHT));
    }

    // 能否召唤宠物
    @Override
    public boolean isRecallPets() {
        return this._isRecallPets;
    }

    // 指定的坐标位置 - 安全区域
    @Override
    public boolean isSafetyZone(final int x, final int y) {
        final int tile = this.accessOriginalTile(x, y);

        return (tile & 0x30) == 0x10;
    }

    // 指定的坐标位置 - 安全区域
    @Override
    public boolean isSafetyZone(final Point pt) {
        return this.isSafetyZone(pt.getX(), pt.getY());
    }

    // 能否携带宠物
    @Override
    public boolean isTakePets() {
        return this._isTakePets;
    }

    // 能否随机传送
    @Override
    public boolean isTeleportable() {
        return this._isTeleportable;
    }

    // 是否在水下
    @Override
    public boolean isUnderwater() {
        return this._isUnderwater;
    }

    // 能否使用道具
    @Override
    public boolean isUsableItem() {
        return this._isUsableItem;
    }

    // 能否使用技能
    @Override
    public boolean isUsableSkill() {
        return this._isUsableSkill;
    }

    // 能否使用魔杖
    @Override
    public boolean isUsePainwand() {
        return this._isUsePainwand;
    }

    // 能否复活
    @Override
    public boolean isUseResurrection() {
        return this._isUseResurrection;
    }

    // 设置坐标障碍宣告
    @Override
    public void setPassable(final int x, final int y, final boolean isPassable) {
        if (isPassable) {
            this.setTile(x, y,
                    (short) (this.accessTile(x, y) & (~BITFLAG_IS_IMPASSABLE)));
        } else {
            this.setTile(x, y,
                    (short) (this.accessTile(x, y) | BITFLAG_IS_IMPASSABLE));
        }
    }

    // 设置坐标障碍宣告
    @Override
    public void setPassable(final Point pt, final boolean isPassable) {
        this.setPassable(pt.getX(), pt.getY(), isPassable);
    }

    private void setTile(final int x, final int y, final int tile) {
        if (!this.isInMap(x, y)) { // XXX 无论怎样请检查。这是不好的。
            return;
        }
        this._map[x - this._worldTopLeftX][y - this._worldTopLeftY] = (byte) tile;
    }

    // 传回指定的字串 表示PT
    @Override
    public String toString(final Point pt) {
        return "" + this.getOriginalTile(pt.getX(), pt.getY());
    }
}
