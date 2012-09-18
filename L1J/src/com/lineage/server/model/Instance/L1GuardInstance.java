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
package com.lineage.server.model.Instance;

import static com.lineage.server.model.skill.L1SkillId.FOG_OF_SLEEPING;

import com.lineage.server.ActionCodes;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.datatables.NPCTalkDataTable;
import com.lineage.server.model.L1Attack;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1Character;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_DoActionGFX;
import com.lineage.server.serverpackets.S_NPCTalkReturn;
import com.lineage.server.templates.L1Npc;
import com.lineage.server.types.Point;

/**
 * 城门守卫控制项
 */
public class L1GuardInstance extends L1NpcInstance {

    class Death implements Runnable {
        L1Character _lastAttacker;

        public Death(final L1Character lastAttacker) {
            this._lastAttacker = lastAttacker;
        }

        @Override
        public void run() {
            L1GuardInstance.this.setDeathProcessing(true);
            L1GuardInstance.this.setCurrentHpDirect(0);
            L1GuardInstance.this.setDead(true);
            L1GuardInstance.this.setStatus(ActionCodes.ACTION_Die);

            L1GuardInstance.this.getMap().setPassable(
                    L1GuardInstance.this.getLocation(), true);

            L1GuardInstance.this.broadcastPacket(new S_DoActionGFX(
                    L1GuardInstance.this.getId(), ActionCodes.ACTION_Die));

            L1GuardInstance.this.startChat(CHAT_TIMING_DEAD);

            L1GuardInstance.this.setDeathProcessing(false);

            L1GuardInstance.this.allTargetClear();

            L1GuardInstance.this.startDeleteTimer();
        }
    }

    private static final long serialVersionUID = 1L;

    public L1GuardInstance(final L1Npc template) {
        super(template);
    }

    private boolean checkHasCastle(final L1PcInstance pc, final int castleId) {
        boolean isExistDefenseClan = false;
        for (final L1Clan clan : L1World.getInstance().getAllClans()) {
            if (castleId == clan.getCastleId()) {
                isExistDefenseClan = true;
                break;
            }
        }
        if (!isExistDefenseClan) { // 城主クランが居ない
            return true;
        }

        if (pc.getClanid() != 0) { // クラン所属中
            final L1Clan clan = L1World.getInstance().getClan(pc.getClanname());
            if (clan != null) {
                if (clan.getCastleId() == castleId) {
                    return true;
                }
            }
        }
        return false;
    }

    public void doFinalAction() {

    }

    // 如果没有目标处理
    @Override
    public boolean noTarget() {
        if (this.getLocation().getTileLineDistance(
                new Point(this.getHomeX(), this.getHomeY())) > 0) {
            final int dir = this
                    .moveDirection(this.getHomeX(), this.getHomeY());
            if (dir != -1) {
                this.setDirectionMove(dir);
                this.setSleepTime(this.calcSleepTime(this.getPassispeed(),
                        MOVE_SPEED));
            } else // 遠すぎるor経路が見つからない場合はテレポートして帰る
            {
                this.teleport(this.getHomeX(), this.getHomeY(), 1);
            }
        } else {
            if (L1World.getInstance().getRecognizePlayer(this).size() == 0) {
                return true; // 周りにプレイヤーがいなくなったらＡＩ処理終了
            }
        }
        return false;
    }

    @Override
    public void onAction(final L1PcInstance pc) {
        this.onAction(pc, 0);
    }

    @Override
    public void onAction(final L1PcInstance pc, final int skillId) {
        if (!this.isDead()) {
            if (this.getCurrentHp() > 0) {
                final L1Attack attack = new L1Attack(pc, this, skillId);
                if (attack.calcHit()) {
                    attack.calcDamage();
                    attack.calcStaffOfMana();
                    attack.addPcPoisonAttack(pc, this);
                    attack.addChaserAttack();
                }
                attack.action();
                attack.commit();
            } else {
                final L1Attack attack = new L1Attack(pc, this, skillId);
                attack.calcHit();
                attack.action();
            }
        }
    }

    public void onFinalAction() {

    }

    @Override
    public void onNpcAI() {
        if (this.isAiRunning()) {
            return;
        }
        this.setActived(false);
        this.startAI();
    }

    @Override
    public void onTalkAction(final L1PcInstance player) {
        final int objid = this.getId();
        final L1NpcTalkData talking = NPCTalkDataTable.getInstance()
                .getTemplate(this.getNpcTemplate().get_npcId());
        final int npcid = this.getNpcTemplate().get_npcId();
        String htmlid = null;
        String[] htmldata = null;
        boolean hascastle = false;
        String clan_name = "";
        String pri_name = "";

        if (talking != null) {
            // 管理人
            if ((npcid == 70549) || // 肯特城堡的守门员左外门
                    (npcid == 70985)) { // 肯特城堡的守门员右外门(矛)
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.KENT_CASTLE_ID);
                if (hascastle) { // 本城成员(城主)
                    htmlid = "gateokeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if (npcid == 70656) { // 肯特城守门人(内城)
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.KENT_CASTLE_ID);
                if (hascastle) { // 本城成员(城主)
                    htmlid = "gatekeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if ((npcid == 70600) || // オークの森外門キーパー
                    (npcid == 70986)) {
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.OT_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "orckeeper";
                } else {
                    htmlid = "orckeeperop";
                }
            } else if ((npcid == 70687) || // ウィンダウッド城外門キーパー
                    (npcid == 70987)) {
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.WW_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "gateokeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if (npcid == 70778) { // ウィンダウッド城内門キーパー
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.WW_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "gatekeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if ((npcid == 70800)
                    || // ギラン城外門キーパー
                    (npcid == 70988) || (npcid == 70989) || (npcid == 70990)
                    || (npcid == 70991)) {
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.GIRAN_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "gateokeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if (npcid == 70817) { // ギラン城内門キーパー
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.GIRAN_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "gatekeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if ((npcid == 70862) || // ハイネ城外門キーパー
                    (npcid == 70992)) {
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.HEINE_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "gateokeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if (npcid == 70863) { // ハイネ城内門キーパー
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.HEINE_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "gatekeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if ((npcid == 70993) || // ドワーフ城外門キーパー
                    (npcid == 70994)) {
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.DOWA_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "gateokeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if (npcid == 70995) { // ドワーフ城内門キーパー
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.DOWA_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "gatekeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            } else if (npcid == 70996) { // アデン城内門キーパー
                hascastle = this.checkHasCastle(player,
                        L1CastleLocation.ADEN_CASTLE_ID);
                if (hascastle) { // 城主クラン員
                    htmlid = "gatekeeper";
                    htmldata = new String[] { player.getName() };
                } else {
                    htmlid = "gatekeeperop";
                }
            }

            // 近衛兵
            else if (npcid == 60514) { // ケント城近衛兵
                for (final L1Clan clan : L1World.getInstance().getAllClans()) {
                    if (clan.getCastleId() // 城主クラン
                    == L1CastleLocation.KENT_CASTLE_ID) {
                        clan_name = clan.getClanName();
                        pri_name = clan.getLeaderName();
                        break;
                    }
                }
                htmlid = "ktguard6";
                htmldata = new String[] { this.getName(), clan_name, pri_name };
            } else if (npcid == 60560) { // オーク近衛兵
                for (final L1Clan clan : L1World.getInstance().getAllClans()) {
                    if (clan.getCastleId() // 城主クラン
                    == L1CastleLocation.OT_CASTLE_ID) {
                        clan_name = clan.getClanName();
                        pri_name = clan.getLeaderName();
                        break;
                    }
                }
                htmlid = "orcguard6";
                htmldata = new String[] { this.getName(), clan_name, pri_name };
            } else if (npcid == 60552) { // ウィンダウッド城近衛兵
                for (final L1Clan clan : L1World.getInstance().getAllClans()) {
                    if (clan.getCastleId() // 城主クラン
                    == L1CastleLocation.WW_CASTLE_ID) {
                        clan_name = clan.getClanName();
                        pri_name = clan.getLeaderName();
                        break;
                    }
                }
                htmlid = "wdguard6";
                htmldata = new String[] { this.getName(), clan_name, pri_name };
            } else if ((npcid == 60524) || // ギラン街入り口近衛兵(弓)
                    (npcid == 60525) || // ギラン街入り口近衛兵
                    (npcid == 60529)) { // ギラン城近衛兵
                for (final L1Clan clan : L1World.getInstance().getAllClans()) {
                    if (clan.getCastleId() // 城主クラン
                    == L1CastleLocation.GIRAN_CASTLE_ID) {
                        clan_name = clan.getClanName();
                        pri_name = clan.getLeaderName();
                        break;
                    }
                }
                htmlid = "grguard6";
                htmldata = new String[] { this.getName(), clan_name, pri_name };
            } else if (npcid == 70857) { // ハイネ城ハイネ ガード
                for (final L1Clan clan : L1World.getInstance().getAllClans()) {
                    if (clan.getCastleId() // 城主クラン
                    == L1CastleLocation.HEINE_CASTLE_ID) {
                        clan_name = clan.getClanName();
                        pri_name = clan.getLeaderName();
                        break;
                    }
                }
                htmlid = "heguard6";
                htmldata = new String[] { this.getName(), clan_name, pri_name };
            } else if ((npcid == 60530) || // ドワーフ城ドワーフ ガード
                    (npcid == 60531)) {
                for (final L1Clan clan : L1World.getInstance().getAllClans()) {
                    if (clan.getCastleId() // 城主クラン
                    == L1CastleLocation.DOWA_CASTLE_ID) {
                        clan_name = clan.getClanName();
                        pri_name = clan.getLeaderName();
                        break;
                    }
                }
                htmlid = "dcguard6";
                htmldata = new String[] { this.getName(), clan_name, pri_name };
            } else if ((npcid == 60533) || // アデン城 ガード
                    (npcid == 60534)) {
                for (final L1Clan clan : L1World.getInstance().getAllClans()) {
                    if (clan.getCastleId() // 城主クラン
                    == L1CastleLocation.ADEN_CASTLE_ID) {
                        clan_name = clan.getClanName();
                        pri_name = clan.getLeaderName();
                        break;
                    }
                }
                htmlid = "adguard6";
                htmldata = new String[] { this.getName(), clan_name, pri_name };
            } else if (npcid == 81156) { // アデン偵察兵（ディアド要塞）
                for (final L1Clan clan : L1World.getInstance().getAllClans()) {
                    if (clan.getCastleId() // 城主クラン
                    == L1CastleLocation.DIAD_CASTLE_ID) {
                        clan_name = clan.getClanName();
                        pri_name = clan.getLeaderName();
                        break;
                    }
                }
                htmlid = "ktguard6";
                htmldata = new String[] { this.getName(), clan_name, pri_name };
            }

            // html表示パケット送信
            if (htmlid != null) { // htmlidが指定されている場合
                if (htmldata != null) { // html指定がある場合は表示
                    player.sendPackets(new S_NPCTalkReturn(objid, htmlid,
                            htmldata));
                } else {
                    player.sendPackets(new S_NPCTalkReturn(objid, htmlid));
                }
            } else {
                if (player.getLawful() < -1000) { // プレイヤーがカオティック
                    player.sendPackets(new S_NPCTalkReturn(talking, objid, 2));
                } else {
                    player.sendPackets(new S_NPCTalkReturn(talking, objid, 1));
                }
            }
        }
    }

    @Override
    public void receiveDamage(final L1Character attacker, final int damage) { // 攻撃でＨＰを減らすときはここを使用
        if ((this.getCurrentHp() > 0) && !this.isDead()) {
            if (damage >= 0) {
                if (!(attacker instanceof L1EffectInstance)) { // FWはヘイトなし
                    this.setHate(attacker, damage);
                }
            }
            if (damage > 0) {
                this.removeSkillEffect(FOG_OF_SLEEPING);
            }

            this.onNpcAI();

            if ((attacker instanceof L1PcInstance) && (damage > 0)) {
                final L1PcInstance pc = (L1PcInstance) attacker;
                pc.setPetTarget(this);
                this.serchLink(pc, this.getNpcTemplate().get_family());
            }

            final int newHp = this.getCurrentHp() - damage;
            if ((newHp <= 0) && !this.isDead()) {
                this.setCurrentHpDirect(0);
                this.setDead(true);
                this.setStatus(ActionCodes.ACTION_Die);
                final Death death = new Death(attacker);
                GeneralThreadPool.getInstance().execute(death);
            }
            if (newHp > 0) {
                this.setCurrentHp(newHp);
            }
        } else if ((this.getCurrentHp() == 0) && !this.isDead()) {
        } else if (!this.isDead()) { // 念のため
            this.setDead(true);
            this.setStatus(ActionCodes.ACTION_Die);
            final Death death = new Death(attacker);
            GeneralThreadPool.getInstance().execute(death);
        }
    }

    // 寻找目标
    @Override
    public void searchTarget() {
        // 目标搜索
        L1PcInstance targetPlayer = null;
        for (final L1PcInstance pc : L1World.getInstance().getVisiblePlayer(
                this)) {
            if ((pc.getCurrentHp() <= 0) || pc.isDead() || pc.isGm()
                    || pc.isGhost()) {
                continue;
            }
            if (!pc.isInvisble() || this.getNpcTemplate().is_agrocoi()) // 检查隐身状态
            {
                if (pc.isWanted()) { // PKで手配中か
                    targetPlayer = pc;
                    break;
                }
            }
        }
        if (targetPlayer != null) {
            this._hateList.add(targetPlayer, 0);
            this._target = targetPlayer;
        }
    }

    @Override
    public void setCurrentHp(final int i) {
        int currentHp = i;
        if (currentHp >= this.getMaxHp()) {
            currentHp = this.getMaxHp();
        }
        this.setCurrentHpDirect(currentHp);

        if (this.getMaxHp() > this.getCurrentHp()) {
            this.startHpRegeneration();
        }
    }

    @Override
    public void setLink(final L1Character cha) {
        if ((cha != null) && this._hateList.isEmpty()) {
            this._hateList.add(cha, 0);
            this.checkTarget();
        }
    }

    /** 设置目标 */
    public void setTarget(final L1PcInstance targetPlayer) {
        if (targetPlayer != null) {
            this._hateList.add(targetPlayer, 0);
            this._target = targetPlayer;
        }
    }

}
