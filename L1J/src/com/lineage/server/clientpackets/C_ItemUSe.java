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
package com.lineage.server.clientpackets;

import static com.lineage.server.model.skill.L1SkillId.DECAY_POTION;
import static com.lineage.server.model.skill.L1SkillId.SOLID_CARRIAGE;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lineage.server.ClientThread;
import com.lineage.server.model.L1ItemDelay;
import com.lineage.server.model.L1PcInventory;
import com.lineage.server.model.L1PolyMorph;
import com.lineage.server.model.Instance.L1ItemInstance;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.model.item.CiteItemClass;
import com.lineage.server.model.item.L1TreasureBox;
import com.lineage.server.model.item.etcitem.MagicStone;
import com.lineage.server.serverpackets.S_OwnCharAttrDef;
import com.lineage.server.serverpackets.S_OwnCharStatus;
import com.lineage.server.serverpackets.S_PacketBox;
import com.lineage.server.serverpackets.S_SPMR;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1EtcItem;

/**
 * 处理由客户端传来使用道具的封包
 * 
 * @author jrwz
 */
public class C_ItemUSe extends ClientBasePacket {

    private static final String C_ITEM_USE = "[C] C_ItemUSe";
    private static final Log _log = LogFactory.getLog(C_ItemUSe.class);

    /**
     * 要求使用道具
     * 
     * @param
     */
    public C_ItemUSe(final byte[] abyte0, final ClientThread client)
            throws Exception {

        // 载入资料
        super(abyte0);

        // 取得使用者
        final L1PcInstance pc = client.getActiveChar();

        // 角色为空
        if (pc == null) {
            return;
        }

        // 幽灵状态
        if (pc.isGhost()) {
            return;
        }

        // 死亡状态
        if (pc.isDead()) {
            return;
        }

        // 传送状态
        if (pc.isTeleport()) {
            return;
        }

        // 开个人商店中
        if (pc.isPrivateShop()) {
            return;
        }

        // 不能使用道具的地图
        if (!pc.getMap().isUsableItem()) {
            pc.sendPackets(new S_ServerMessage(563)); // \f1你无法在这个地方使用。
            return;
        }

        // 什么都不能做的状态
        if (pc.CanNotDoAnything()) {
            return;
        }

        // 使用物件的OBJID
        final int itemObjid = this.readD();

        // 取得使用道具
        final L1ItemInstance useItem = pc.getInventory().getItem(itemObjid);

        // 例外:空道具
        if (useItem == null) {
            return;
        }

        // 例外:数量异常
        if (useItem.getCount() <= 0) {
            pc.sendPackets(new S_ServerMessage(329, useItem.getLogName())); // \f1没有具有
                                                                            // %0%o。
            return;
        }

        // 是否具有Class
        boolean isClass = false;
        final String className = useItem.getItem().getClassName(); // 独立执行项位置

        // Class不为0
        if (!className.equals("0")) {
            isClass = true;
        }

        // PC当前血量大于0
        if (pc.getCurrentHp() > 0) {
            int delay_id = 0; // 延迟ID
            if (useItem.getItem().getType2() == 0) { // 种类：道具
                delay_id = ((L1EtcItem) useItem.getItem()).get_delayid(); // 取得延迟ID
            }
            if (delay_id != 0) { // 延迟设定
                if (pc.hasItemDelay(delay_id) == true) {
                    return;
                }
            }

            final int min = useItem.getItem().getMinLevel(); // 取得可以使用道具的最低等级
            final int max = useItem.getItem().getMaxLevel(); // 取得可以使用道具的最高等级

            if ((min != 0) && (min > pc.getLevel())) {
                pc.sendPackets(new S_ServerMessage(318, String.valueOf(min))); // 等级
                                                                               // %0以上才可使用此道具。
                return;
            } else if ((max != 0) && (max < pc.getLevel())) {
                pc.sendPackets(new S_PacketBox(S_PacketBox.MSG_LEVEL_OVER, max)); // 等级
                                                                                  // %d以下才能使用此道具。
                return;
            }

            // 是否具有延迟时间
            boolean isDelayEffect = false;
            if (useItem.getItem().getType2() == 0) { // 使用类型为道具 (etcitem)
                final int delayEffect = ((L1EtcItem) useItem.getItem())
                        .get_delayEffect(); // 延迟时间
                if (delayEffect > 0) {
                    isDelayEffect = true;
                    final Timestamp lastUsed = useItem.getLastUsed();
                    if (lastUsed != null) {
                        final Calendar cal = Calendar.getInstance();
                        long UseTime = (cal.getTimeInMillis() - lastUsed
                                .getTime()) / 1000;
                        if (UseTime <= delayEffect) {
                            // 转换为须等待时间
                            UseTime = (delayEffect - UseTime) / 60;
                            // 取得等待时间 (时间数字 转换为字串)
                            final String UseTimeSurplus = useItem.getLogName()
                                    + " " + String.valueOf(UseTime);
                            pc.sendPackets(new S_ServerMessage(1139,
                                    UseTimeSurplus)); // %0 分钟之内无法使用。
                            return;
                        }
                    }
                }
            }

            // 取得物件触发事件
            final int use_type = useItem.getItem().getUseType();
            switch (use_type) {

                case -14: // treasure_box (宝箱类)
                    // 取得道具ID
                    final int item_Id = useItem.getItem().getItemId();
                    final L1TreasureBox box = L1TreasureBox.get(item_Id);
                    if (box != null) {
                        if (box.open(pc)) {
                            final L1EtcItem temp = (L1EtcItem) useItem
                                    .getItem();
                            if (temp.get_delayEffect() > 0) {
                                // 有限制再次使用的时间且可堆叠的道具
                                if (useItem.isStackable()) {
                                    if (useItem.getCount() > 1) {
                                        isDelayEffect = true;
                                    }
                                    pc.getInventory().removeItem(
                                            useItem.getId(), 1);
                                } else {
                                    isDelayEffect = true;
                                }
                            } else {
                                pc.getInventory()
                                        .removeItem(useItem.getId(), 1);
                            }
                        }
                    }
                    break;

                case -13: // 箭
                    pc.getInventory().setArrow(useItem.getItem().getItemId());
                    pc.sendPackets(new S_ServerMessage(452, useItem
                            .getLogName())); // %0%s 被选择了。
                    break;

                case -12: // 飞刀
                    pc.getInventory().setSting(useItem.getItem().getItemId());
                    pc.sendPackets(new S_ServerMessage(452, useItem
                            .getLogName())); // %0%s 被选择了。
                    break;

                case -11: // 9阶附魔石(近战)(远攻)(恢复)(防御)
                    if (pc.getInventory().consumeItem(41246, 300)) {
                        final int itemId = useItem.getItem().getItemId();
                        MagicStone.get().useItem(pc, useItem, itemId, 0, 0, 0);
                    } else {
                        isDelayEffect = false;
                        pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
                    }
                    break;

                case -10: // 1 ~ 4 阶附魔石(近战)(远攻)(恢复)(防御)
                    if (pc.getInventory().consumeItem(41246, 30)) {
                        final int itemId = useItem.getItem().getItemId();
                        MagicStone.get().useItem(pc, useItem, itemId, 0, 0, 0);
                    } else {
                        isDelayEffect = false;
                        pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
                    }
                    break;

                case -9: // 5 ~ 6阶附魔石(近战)(远攻)(恢复)(防御)
                    if (pc.getInventory().consumeItem(41246, 60)) {
                        final int itemId = useItem.getItem().getItemId();
                        MagicStone.get().useItem(pc, useItem, itemId, 0, 0, 0);
                    } else {
                        isDelayEffect = false;
                        pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
                    }
                    break;

                case -8: // 7阶附魔石(近战)(远攻)(恢复)(防御)
                    if (pc.getInventory().consumeItem(41246, 100)) {
                        final int itemId = useItem.getItem().getItemId();
                        MagicStone.get().useItem(pc, useItem, itemId, 0, 0, 0);
                    } else {
                        isDelayEffect = false;
                        pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
                    }
                    break;

                case -7: // 8阶附魔石(近战)(远攻)(恢复)(防御)
                    if (pc.getInventory().consumeItem(41246, 200)) {
                        final int itemId = useItem.getItem().getItemId();
                        MagicStone.get().useItem(pc, useItem, itemId, 0, 0, 0);
                    } else {
                        isDelayEffect = false;
                        pc.sendPackets(new S_ServerMessage(337, "$5240")); // \f1%0不足%s。
                    }
                    break;

                case -6: // 料理书
                    if (isClass) {
                        final int[] newData = new int[2];
                        newData[0] = this.readC();
                        newData[1] = this.readC();
                        CiteItemClass.getInstance().item(newData, pc, useItem);
                    }
                    break;

                case -5: // 药水类道具
                    if (pc.hasSkillEffect(DECAY_POTION)) {
                        pc.sendPackets(new S_ServerMessage(698)); // 喉咙灼热，无法喝东西。
                        return;
                    }
                    if (isClass) {
                        CiteItemClass.getInstance().item(null, pc, useItem);
                    }
                    break;

                case -4: // 无法使用 (材料等)
                    pc.sendPackets(new S_ServerMessage(74, useItem.getLogName())); // \f1%0%o
                                                                                   // 无法使用。
                    break;

                case -3: // 料理
                case -2: // 技能书
                case -1: // 其他类道具
                case 0: // 一般类道具
                case 15: // 哨子
                case 13: // 信纸(打开)
                case 32: // 圣诞卡片(打开)
                case 34: // 情人节卡片(打开)
                case 36: // 白色情人节卡片(打开)
                    if (isClass) {
                        CiteItemClass.getInstance().item(null, pc, useItem);
                    }
                    break;

                case 1: // 武器
                        // 武器禁止使用
                    if (pc.hasItemDelay(L1ItemDelay.WEAPON) == true) {
                        return;
                    }
                    if (this.checkEquipped(pc, useItem)) {
                        this.setEquippedWeapon(pc, useItem);
                    }
                    break;

                case 2: // 盔甲
                case 18: // T恤
                case 19: // 斗篷
                case 20: // 手套
                case 21: // 长靴
                case 22: // 头盔
                case 23: // 戒指
                case 24: // 项链
                case 25: // 盾牌
                case 37: // 腰带
                case 40: // 耳环
                case 43: // 辅助装备 (右)
                case 44: // 辅助装备 (左)
                case 45: // 辅助装备 (中)
                    // 防具禁止使用
                    if (pc.hasItemDelay(L1ItemDelay.ARMOR) == true) {
                        return;
                    }
                    if (this.checkEquipped(pc, useItem)) {
                        this.setEquippedArmor(pc, useItem);
                    }
                    break;

                case 5: // 魔杖类型 (须选取目标/坐标)地面 / 选择对象(远距离)
                case 17: // 选取目标 地面 (近距离)
                    if (isClass) {
                        final int[] newData = new int[3];
                        newData[0] = this.readD(); // 选取目标的OBJID
                        newData[1] = this.readH(); // X坐标
                        newData[2] = this.readH(); // Y坐标
                        CiteItemClass.getInstance().item(newData, pc, useItem);
                    }
                    break;

                case 6: // 瞬间移动卷轴
                case 29: // 瞬间移动卷轴(祝福)
                    if (isClass) {
                        final int[] newData = new int[2];
                        newData[1] = this.readH(); // 所在地图编号 /必须在上方
                        newData[0] = this.readD(); // 选取目标的OBJID
                        CiteItemClass.getInstance().item(newData, pc, useItem);
                    }
                    break;

                case 7: // 鉴定卷轴
                case 8: // 复活卷轴
                case 14: // 请选择一个物品 (道具栏位) 灯油/磨刀石/胶水/龙之魔眼等
                case 26: // 对武器施法的卷轴
                case 27: // 对盔甲施法的卷轴
                case 30: // 选取目标 (对NPC需要Ctrl 远距离 无XY坐标传回) / 魔法卷轴 (已写)
                case 46: // 饰品强化卷轴
                    if (isClass) {
                        final int[] newData = new int[1];
                        newData[0] = this.readD(); // 选取物件的OBJID
                        CiteItemClass.getInstance().item(newData, pc, useItem);
                    }
                    break;

                case 12: // 信纸
                case 31: // 圣诞卡片
                case 33: // 情人节卡片
                case 35: // 白色情人节卡片
                    if (isClass) {
                        final int[] newData = new int[1];
                        newData[0] = this.readH();
                        final String s = this.readS();
                        pc.setText(s);
                        pc.setTextByte(this.readByte());
                        CiteItemClass.getInstance().item(newData, pc, useItem);
                    }
                    break;

                case 16: // 变形卷轴
                    if (isClass) {
                        final String cmd = this.readS();
                        pc.setText(cmd); // 选取的变身命令
                        CiteItemClass.getInstance().item(null, pc, useItem);
                    }
                    break;

                case 28: // 空的魔法卷轴
                    if (isClass) {
                        final int[] newData = new int[1];
                        newData[0] = this.readC();
                        CiteItemClass.getInstance().item(newData, pc, useItem);
                    }
                    break;

                case 42: // 钓鱼杆 (魔法钓竿)
                    if (isClass) {
                        final int[] newData = new int[2];
                        newData[0] = this.readH(); // X坐标
                        newData[1] = this.readH(); // Y坐标
                        CiteItemClass.getInstance().item(newData, pc, useItem);
                    }
                    break;

                default: // 检测
                    _log.info("未处理的道具类型: " + use_type);
                    break;
            }

            // 物件使用延迟设置
            if (isDelayEffect) {
                final Timestamp ts = new Timestamp(System.currentTimeMillis());
                useItem.setLastUsed(ts); // 设置使用时间
                pc.getInventory().updateItem(useItem,
                        L1PcInventory.COL_DELAY_EFFECT);
                pc.getInventory().saveItem(useItem,
                        L1PcInventory.COL_DELAY_EFFECT);
            }
            try {
                L1ItemDelay.onItemUse(client, useItem); // 项目开始延迟
            } catch (final Exception e) {
                _log.error("道具使用延迟异常:" + useItem.getItemId(), e);
            }
        }
    }

    /**
     * 检查武器与防具该职业能否使用.
     * 
     * @param pc
     *            角色
     * @param useItem
     *            使用的装备
     * @return 如果该职业可用、true
     */
    private boolean checkEquipped(final L1PcInstance pc,
            final L1ItemInstance useItem) {

        // 是否能装备
        boolean isEquipped = false;

        if (pc.isCrown()) { // 王族
            if (useItem.getItem().isUseRoyal()) {
                isEquipped = true;
            }
        } else if (pc.isKnight()) { // 骑士
            if (useItem.getItem().isUseKnight()) {
                isEquipped = true;
            }
        } else if (pc.isElf()) { // 精灵
            if (useItem.getItem().isUseElf()) {
                isEquipped = true;
            }
        } else if (pc.isWizard()) { // 魔法师
            if (useItem.getItem().isUseMage()) {
                isEquipped = true;
            }
        } else if (pc.isDarkelf()) { // 黑暗精灵
            if (useItem.getItem().isUseDarkelf()) {
                isEquipped = true;
            }
        } else if (pc.isDragonKnight()) { // 龙骑士
            if (useItem.getItem().isUseDragonknight()) {
                isEquipped = true;
            }
        } else if (pc.isIllusionist()) { // 幻术师
            if (useItem.getItem().isUseIllusionist()) {
                isEquipped = true;
            }
        }

        // 该职业不可用
        if (!isEquipped) {
            pc.sendPackets(new S_ServerMessage(264)); // \f1你的职业无法使用此装备。
        }

        return isEquipped;
    }

    @Override
    public String getType() {
        return C_ITEM_USE;
    }

    /**
     * 设置防具的装备.
     * 
     * @param pc
     *            角色
     * @param armor
     *            装备的防具
     */
    private void setEquippedArmor(final L1PcInstance pc,
            final L1ItemInstance armor) {

        // 取回防具类型
        final int type = armor.getItem().getType();

        // 取回角色背包道具
        final L1PcInventory pcInventory = pc.getInventory();

        // 装备栏是否有空位
        boolean equipeSpace;

        // 戒指可戴2个,其他都只能戴1个
        if (type == 9) {
            equipeSpace = pcInventory.getTypeEquipped(2, 9) <= 1;
        } else {
            equipeSpace = pcInventory.getTypeEquipped(2, type) <= 0;
        }

        // 防具穿戴
        if (equipeSpace && !armor.isEquipped()) { // 要安装的装备栏尚未安装物品

            // 取回变身编号
            final int polyid = pc.getTempCharGfx();

            // 在此变身状态不能装备的装备
            if (!L1PolyMorph.isEquipableArmor(polyid, type)) {
                return;
            }

            // 已经装备其他东西。
            if (((type == 13) && (pcInventory.getTypeEquipped(2, 7) >= 1))
                    || ((type == 7) && (pcInventory.getTypeEquipped(2, 13) >= 1))) {
                pc.sendPackets(new S_ServerMessage(124)); // 已经装备其他东西。
                return;
            }

            // 使用双手武器时无法装备盾牌
            if ((type == 7) && (pc.getWeapon() != null)) {
                if (pc.getWeapon().getItem().isTwohandedWeapon()) { // 双手武器
                    pc.sendPackets(new S_ServerMessage(129)); // \f1当你使用双手武器时，无法装备盾牌。
                    return;
                }
            }

            // 穿着斗篷时不可穿内衣
            if ((type == 3) && (pcInventory.getTypeEquipped(2, 4) >= 1)) {
                pc.sendPackets(new S_ServerMessage(126, "$224", "$225")); // \f1穿着%1
                                                                          // 无法装备
                                                                          // %0%o
                                                                          // 。
                return;
            }

            // 穿着盔甲时不可穿内衣
            else if ((type == 3) && (pcInventory.getTypeEquipped(2, 2) >= 1)) {
                pc.sendPackets(new S_ServerMessage(126, "$224", "$226")); // \f1穿着%1
                                                                          // 无法装备
                                                                          // %0%o
                                                                          // 。
                return;
            }

            // 穿着斗篷时不可穿盔甲
            else if ((type == 2) && (pcInventory.getTypeEquipped(2, 4) >= 1)) {
                pc.sendPackets(new S_ServerMessage(126, "$226", "$225")); // \f1穿着%1
                                                                          // 无法装备
                                                                          // %0%o
                                                                          // 。
                return;
            }
            pcInventory.setEquipped(armor, true);
        }

        // 防具脱除
        else if (armor.isEquipped()) { // 所选防具穿戴在身上
            if (armor.getItem().getBless() == 2) { // 被诅咒的装备
                pc.sendPackets(new S_ServerMessage(150)); // \f1你无法这样做。这个物品已经被诅咒了。
                return;

            }
            // 穿着盔甲时不能脱下内衣
            if ((type == 3) && (pcInventory.getTypeEquipped(2, 2) >= 1)) {
                pc.sendPackets(new S_ServerMessage(127)); // \f1你不能够脱掉那个。
                return;

            }

            // 穿着斗篷时不能脱下内衣
            else if (((type == 2) || (type == 3))
                    && (pcInventory.getTypeEquipped(2, 4) >= 1)) {
                pc.sendPackets(new S_ServerMessage(127)); // \f1你不能够脱掉那个。
                return;
            }

            // 解除坚固防御
            if (type == 7) {
                if (pc.hasSkillEffect(SOLID_CARRIAGE)) {
                    pc.removeSkillEffect(SOLID_CARRIAGE);
                }
            }
            pcInventory.setEquipped(armor, false);
        } else {
            if (armor.getItem().getUseType() == 23) {
                pc.sendPackets(new S_ServerMessage(144)); // \f1你已经戴着二个戒指。
                return;
            }
            pc.sendPackets(new S_ServerMessage(124)); // \f1已经装备其他东西。
            return;
        }

        pc.setCurrentHp(pc.getCurrentHp()); // 更新角色HP
        pc.setCurrentMp(pc.getCurrentMp()); // 更新角色MP
        pc.sendPackets(new S_OwnCharAttrDef(pc)); // 更新角色物理防御与四属性防御
        pc.sendPackets(new S_OwnCharStatus(pc)); // 更新角色属性与能力值
        pc.sendPackets(new S_SPMR(pc)); // 更新角色魔法攻击与魔法防御
    }

    /**
     * 设置武器的装备.
     * 
     * @param pc
     *            角色
     * @param weapon
     *            装备的武器
     */
    private void setEquippedWeapon(final L1PcInstance pc,
            final L1ItemInstance weapon) {

        // 取回角色背包道具
        final L1PcInventory pcInventory = pc.getInventory();

        // 没有使用武器或使用武器与所选武器不同
        if ((pc.getWeapon() == null) || !pc.getWeapon().equals(weapon)) {

            // 取回武器类型
            final int weapon_type = weapon.getItem().getType();

            // 取回变身编号
            final int polyid = pc.getTempCharGfx();

            // 此变身状态不能使用的武器
            if (!L1PolyMorph.isEquipableWeapon(polyid, weapon_type)) {
                return;
            }

            // 装备盾牌时不可再使用双手武器
            if (weapon.getItem().isTwohandedWeapon()
                    && (pcInventory.getTypeEquipped(2, 7) >= 1)) {
                pc.sendPackets(new S_ServerMessage(128));
                return;
            }
        }

        // 已有装备的状态
        if (pc.getWeapon() != null) {

            // 被诅咒的装备
            if (pc.getWeapon().getItem().getBless() == 2) {
                pc.sendPackets(new S_ServerMessage(150)); // \f1你无法这样做。这个物品已经被诅咒了。
                return;
            }

            // 解除装备
            if (pc.getWeapon().equals(weapon)) {
                pcInventory.setEquipped(pc.getWeapon(), false, false, false);
                return;
            }
            pcInventory.setEquipped(pc.getWeapon(), false, false, true);
        }

        // 被诅咒的装备
        if (weapon.getItem().getBless() == 2) {
            pc.sendPackets(new S_ServerMessage(149, weapon.getLogName())); // \f1%0%s
                                                                           // 主动固定在你的手上！
        }
        pcInventory.setEquipped(weapon, true, false, false);
    }
}
