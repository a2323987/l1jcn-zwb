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
package l1j.server.server.templates;

import l1j.server.server.datatables.ItemTable;
import l1j.server.server.datatables.MagicDollTable;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.Instance.L1DollInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.utils.Random;
import l1j.server.server.serverpackets.S_ServerMessage; // 经验加倍魔法娃娃讯息

public class L1MagicDoll {

	public static int getHitAddByDoll(L1Character _master) { // 近距离的命中率增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getHit();
			}
		}
		return s;
	}

	public static int getDamageAddByDoll(L1Character _master) { // 近距离的攻击力增加
		int s = 0;
		int chance = Random.nextInt(100) + 1;
		boolean isAdd = false;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (doll.getDmgChance() > 0 && !isAdd) { // 额外伤害发动机率
					if (doll.getDmgChance() >= chance) {
						s += doll.getDmg();
						isAdd = true;
					}
				} else if (doll.getDmg() != 0) { // 额外伤害
					s += doll.getDmg();
				}
			}
		}
		if (isAdd) {
			if (_master instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) _master;
				pc.sendPackets(new S_SkillSound(_master.getId(), 6319));
			}
			_master.broadcastPacket(new S_SkillSound(_master.getId(), 6319));
		}
		return s;
	}

	public static int getDamageReductionByDoll(L1Character _master) { // 伤害减免
		int s = 0;
		int chance = Random.nextInt(100) + 1;
		boolean isReduction = false;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (doll.getDmgReductionChance() > 0 && !isReduction) { // 伤害减免发动机率
					if (doll.getDmgReductionChance() >= chance) {
						s += doll.getDmgReduction();
						isReduction = true;
					}
				} else if (doll.getDmgReduction() != 0) { // 伤害减免
					s += doll.getDmgReduction();
				}
			}
		}
		if (isReduction ) {
			if (_master instanceof L1PcInstance) {
				L1PcInstance pc = (L1PcInstance) _master;
				pc.sendPackets(new S_SkillSound(_master.getId(), 6320));
			}
			_master.broadcastPacket(new S_SkillSound(_master.getId(), 6320));
		}
		return s;
	}

	public static int getDamageEvasionByDoll(L1Character _master) { // 伤害回避
		int chance = Random.nextInt(100) + 1;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (doll.getDmgEvasionChance() >= chance) { // 伤害回避发动机率
					if (_master instanceof L1PcInstance) {
						L1PcInstance pc = (L1PcInstance) _master;
						pc.sendPackets(new S_SkillSound(_master.getId(), 6320));
					}
					_master.broadcastPacket(new S_SkillSound(_master.getId(), 6320));
					return 1;
				}
			}
		}
		return 0;
	}

	public static int getBowHitAddByDoll(L1Character _master) { // 弓的命中率增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getBowHit();
			}
		}
		return s;
	}

	public static int getBowDamageByDoll(L1Character _master) { // 弓的攻击力增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getBowDmg();
			}
		}
		return s;
	}

	public static int getAcByDoll(L1Character _master) { // 防御力增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getAc();
			}
		}
		return s;
	}

	public static int getRegistStoneByDoll(L1Character _master) { // 石化耐性增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getRegistStone();
			}
		}
		return s;
	}

	public static int getRegistStunByDoll(L1Character _master) { // 昏迷耐性增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getRegistStun();
			}
		}
		return s;
	}

	public static int getRegistSustainByDoll(L1Character _master) { // 支撑耐性增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getRegistSustain();
			}
		}
		return s;
	}

	public static int getRegistBlindByDoll(L1Character _master) { // 闇黑耐性增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getRegistBlind();
			}
		}
		return s;
	}

	public static int getRegistFreezeByDoll(L1Character _master) { // 寒冰耐性增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getRegistFreeze();
			}
		}
		return s;
	}

	public static int getRegistSleepByDoll(L1Character _master) { // 睡眠耐性增加
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getRegistSleep();
			}
		}
		return s;
	}

	public static int getWeightReductionByDoll(L1Character _master) { // 负重减轻
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getWeightReduction();
			}
		}
		return s;
	}

	public static int getHprByDoll(L1Character _master) { // 体力回覆量
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (!doll.getHprTime() && doll.getHpr() != 0) {
					s += doll.getHpr();
				}
			}
		}
		return s;
	}

	public static int getMprByDoll(L1Character _master) { // 魔力回覆量
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (!doll.getMprTime() && doll.getMpr() != 0) {
					s += doll.getMpr();
				}
			}
		}
		return s;
	}

	public static boolean isItemMake(L1Character _master) {
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				L1Item item = ItemTable.getInstance().getTemplate((doll.getMakeItemId()));
				if (item != null) {
					return true;
				}
			}
		}
		return false;
	}

	public static int getMakeItemId(L1Character _master) { // 获得道具
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				L1Item item = ItemTable.getInstance().getTemplate((doll.getMakeItemId()));
				if (item != null) {
					return item.getItemId();
				}
			}
		}
		return 0;
	}

	public static boolean isHpRegeneration(L1Character _master) { // 回血判断 (时间固定性)
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (doll.getHprTime() && doll.getHpr() != 0) {
					return true;
				}
			}
		}
		return false;
	}

	public static int getHpByDoll(L1Character _master) { // 体力回覆量 (时间固定性)
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (doll.getHprTime() && doll.getHpr() != 0) {
					s += doll.getHpr();
				}
			}
		}
		return s;
	}

	public static boolean isMpRegeneration(L1Character _master) { // 回魔判断 (时间固定性)
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (doll.getMprTime() && doll.getMpr() != 0) {
					return true;
				}
			}
		}
		return false;
	}

	public static int getMpByDoll(L1Character _master) { // 魔力回覆量 (时间固定性)
		int s = 0;

		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (doll.getMprTime() && doll.getMpr() != 0) {
					s += doll.getMpr();
				}
			}
		}
		return s;
	}

	public static int getEffectByDoll(L1Character _master, byte type) { // 效果
		int chance = Random.nextInt(100) + 1;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll.getEffectChance() > chance) {
				if (doll != null) {
					if (doll.getEffect() == type) {
					return type;
					}
				}
			}
		}
		return 0;
	}

	// TODO 经验加倍魔法娃娃
	public static double getDoubleExpByDoll(L1Character _master) {
		int DoubleExp = 100;
		int chance = Random.nextInt(100) + 1;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if(doll.getExpRateChance() > 0 && doll.getExpRate() > 100){
					if (doll.getExpRateChance() >= chance) {
						DoubleExp = doll.getExpRate();
						if (_master instanceof L1PcInstance) {
							L1PcInstance pc = (L1PcInstance) _master;
							pc.sendPackets(new S_ServerMessage(166, "经验值加成 " + ((double)DoubleExp / 100) + " 倍")); 
							pc.sendPackets(new S_SkillSound(_master.getId(),4399));
						}
					}
				}
			}
		}
		return ((double)DoubleExp / 100);
	}
	
	// TODO 加速状态魔法娃娃
	public static boolean isHasteByDoll(L1Character _master) {
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				if (doll.getIsHaste() == 1) {
					return true;
				}
			}
		}
		return false;
	}
	
	// TODO 增加HP
	public static int getAddHpByDoll(L1Character _master) {
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getAddHp();
			}
		}
		return s;
	}
	
	// TODO 增加MP
	public static int getAddMpByDoll(L1Character _master) {
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getAddMp();
			}
		}
		return s;
	}
	
	// TODO 攻击成功
	public static int getHitModifierByDoll(L1Character _master) {
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getHitModifier();
			}
		}
		return s;
	}
	
	// TODO 额外攻击点数
	public static int getDmgModifierByDoll(L1Character _master) {
		int s = 0;
		for ( L1DollInstance dollIns : _master.getDollList().values()) {
			L1MagicDoll doll = MagicDollTable.getInstance().getTemplate(dollIns.getItemId());
			if (doll != null) {
				s += doll.getDmgModifier();
			}
		}
		return s;
	}
	
	private int _itemId;
	private int _dollId;
	private int _ac;
	private int _hpr;
	private int _mpr;
	private boolean _hprTime;
	private boolean _mprTime;
	private int _dmg;
	private int _bowDmg;
	private int _dmgChance;
	private int _hit;
	private int _bowHit;
	private int _dmgReduction;
	private int _dmgReductionChance;
	private int _dmgEvasionChance;
	private int _weightReduction;
	private int _registStun;
	private int _registStone;
	private int _registSleep;
	private int _registFreeze;
	private int _registSustain;
	private int _registBlind;
	private int _makeItemId;
	private byte _effect;
	private int _EffectChance;
	private int _exprateChance; // TODO 加倍机率
	private int _exprate; // TODO 经验值倍率
	private byte _isHaste; // TODO 加速状态
	private short _addHP; // TODO 增加HP
	private short _addMP; // TODO 增加MP
	private int _hitModifier; // TODO 攻击成功
	private int _dmgModifier; // TODO 额外攻击点数
	
	public int getItemId() {
		return _itemId;
	}

	public void setItemId(int i) {
		_itemId = i;
	}

	public int getDollId() {
		return _dollId;
	}

	public void setDollId(int i) {
		_dollId = i;
	}

	public int getAc() {
		return _ac;
	}

	public void setAc(int i) {
		_ac = i;
	}

	public int getHpr() {
		return _hpr;
	}

	public void setHpr(int i) {
		_hpr = i;
	}

	public int getMpr() {
		return _mpr;
	}

	public void setMpr(int i) {
		_mpr = i;
	}

	public boolean getHprTime() {
		return _hprTime;
	}

	public void setHprTime(boolean i) {
		_hprTime = i;
	}

	public boolean getMprTime() {
		return _mprTime;
	}

	public void setMprTime(boolean i) {
		_mprTime = i;
	}

	public int getDmg() {
		return _dmg;
	}

	public void setDmg(int i) {
		_dmg = i;
	}

	public int getBowDmg() {
		return _bowDmg;
	}

	public void setBowDmg(int i) {
		_bowDmg = i;
	}

	public int getDmgChance() {
		return _dmgChance;
	}

	public void setDmgChance(int i) {
		_dmgChance = i;
	}

	public int getHit() {
		return _hit;
	}

	public void setHit(int i) {
		_hit = i;
	}

	public int getBowHit() {
		return _bowHit;
	}

	public void setBowHit(int i) {
		_bowHit = i;
	}

	public int getDmgReduction() {
		return _dmgReduction;
	}

	public void setDmgReduction(int i) {
		_dmgReduction = i;
	}

	public int getDmgReductionChance() {
		return _dmgReductionChance;
	}

	public void setDmgReductionChance(int i) {
		_dmgReductionChance = i;
	}

	public int getDmgEvasionChance() {
		return _dmgEvasionChance;
	}

	public void setDmgEvasionChance(int i) {
		_dmgEvasionChance = i;
	}

	public int getWeightReduction() {
		return _weightReduction;
	}

	public void setWeightReduction(int i) {
		_weightReduction = i;
	}

	public int getRegistStun() {
		return _registStun;
	}

	public void setRegistStun(int i) {
		_registStun = i;
	}

	public int getRegistStone() {
		return _registStone;
	}

	public void setRegistStone(int i) {
		_registStone = i;
	}

	public int getRegistSleep() {
		return _registSleep;
	}

	public void setRegistSleep(int i) {
		_registSleep = i;
	}

	public int getRegistFreeze() {
		return _registFreeze;
	}

	public void setRegistFreeze(int i) {
		_registFreeze = i;
	}

	public int getRegistSustain() {
		return _registSustain;
	}

	public void setRegistSustain(int i) {
		_registSustain = i;
	}

	public int getRegistBlind() {
		return _registBlind;
	}

	public void setRegistBlind(int i) {
		_registBlind = i;
	}

	public int getMakeItemId() {
		return _makeItemId;
	}

	public void setMakeItemId(int i) {
		_makeItemId = i;
	}

	public byte getEffect() {
		return _effect;
	}

	public void setEffect(byte i) {
		_effect = i;
	}
	public int getEffectChance() {
		return _EffectChance;
	}

	public void setEffectChance(int i) {
		_EffectChance = i;
	}

	// TODO 加倍机率
	public void setExpRateChance(int i) {
		_exprateChance = i;
	}

	public int getExpRateChance() {
		return _exprateChance;
	}
	
	// TODO 经验值倍率
	public int getExpRate() {
		return _exprate;
	}

	public void setExpRate(int i) {
		_exprate = i;
	}
	
	// TODO 加速状态
	public byte getIsHaste() {
		return _isHaste;
	}

	public void setIsHaste(byte i) {
		_isHaste = i;
	}
	
	// TODO 增加HP
	public short getAddHp() {
		return _addHP;
	}

	public void setAddHp(short i) {
		_addHP = i;
	}
	
	// TODO 增加MP
	public short getAddMp() {
		return _addMP;
	}

	public void setAddMp(short i) {
		_addMP = i;
	}
	
	// TODO 攻击成功
	public int getHitModifier() {
		return _hitModifier;
	}

	public void setHitModifier(int i) {
		_hitModifier = i;
	}
	
	// TODO 额外攻击点数
	public int getDmgModifier() {
		return _dmgModifier;
	}

	public void setDmgModifier(int i) {
		_dmgModifier = i;
	}
	
}
