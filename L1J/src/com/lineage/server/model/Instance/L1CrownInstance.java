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

import java.util.List;

import com.lineage.server.datatables.ClanTable;
import com.lineage.server.datatables.DoorTable;
import com.lineage.server.model.L1CastleLocation;
import com.lineage.server.model.L1Clan;
import com.lineage.server.model.L1Object;
import com.lineage.server.model.L1Teleport;
import com.lineage.server.model.L1War;
import com.lineage.server.model.L1WarSpawn;
import com.lineage.server.model.L1World;
import com.lineage.server.serverpackets.S_CastleMaster;
import com.lineage.server.serverpackets.S_RemoveObject;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.templates.L1Npc;

/**
 * 王冠控制项
 */
public class L1CrownInstance extends L1NpcInstance {

    private static final long serialVersionUID = 1L;

    public L1CrownInstance(final L1Npc template) {
        super(template);
    }

    private boolean checkRange(final L1PcInstance pc) {
        return ((this.getX() - 1 <= pc.getX())
                && (pc.getX() <= this.getX() + 1)
                && (this.getY() - 1 <= pc.getY()) && (pc.getY() <= this.getY() + 1));
    }

    @Override
    public void deleteMe() {
        this._destroyed = true;
        if (this.getInventory() != null) {
            this.getInventory().clearItems();
        }
        this.allTargetClear();
        this._master = null;
        L1World.getInstance().removeVisibleObject(this);
        L1World.getInstance().removeObject(this);
        for (final L1PcInstance pc : L1World.getInstance().getRecognizePlayer(
                this)) {
            pc.removeKnownObject(this);
            pc.sendPackets(new S_RemoveObject(this));
        }
        this.removeAllKnownObjects();
    }

    @Override
    public void onAction(final L1PcInstance player) {
        boolean in_war = false;
        if (player.getClanid() == 0) { // 没有血盟
            return;
        }
        final String playerClanName = player.getClanname();
        final L1Clan clan = L1World.getInstance().getClan(playerClanName);
        if (clan == null) {
            return;
        }
        if (!player.isCrown()) { // 不是王族
            return;
        }
        if ((player.getTempCharGfx() != 0) && // 变身中
                (player.getTempCharGfx() != 1)) {
            return;
        }
        if (player.getId() != clan.getLeaderId()) { // 血盟主以外
            return;
        }
        if (!this.checkRange(player)) { // クラウンの1セル以内
            return;
        }

        // 有城的血盟王族
        if (clan.getCastleId() != 0) {
            player.sendPackets(new S_ServerMessage(474)); // 你已经拥有城堡，无法再拥有其他城堡。
            return;
        }

        // クラウンの座標からcastle_idを取得
        final int castle_id = L1CastleLocation.getCastleId(this.getX(),
                this.getY(), this.getMapId());

        // 布告しているかチェック。但し、城主が居ない場合は布告不要
        boolean existDefenseClan = false;
        L1Clan defence_clan = null;
        for (final L1Clan defClan : L1World.getInstance().getAllClans()) {
            if (castle_id == defClan.getCastleId()) {
                // 元の城主クラン
                defence_clan = L1World.getInstance().getClan(
                        defClan.getClanName());
                existDefenseClan = true;
                break;
            }
        }
        final List<L1War> wars = L1World.getInstance().getWarList(); // 获得所有战争列表
        for (final L1War war : wars) {
            if (castle_id == war.GetCastleId()) { // 今天的攻城战
                in_war = war.CheckClanInWar(playerClanName);
                break;
            }
        }
        if (existDefenseClan && (in_war == false)) { // 城主が居て、布告していない場合
            return;
        }

        // clan_dataのhascastleを更新し、キャラクターにクラウンを付ける
        if (existDefenseClan && (defence_clan != null)) { // 元の城主クランが居る
            defence_clan.setCastleId(0);
            ClanTable.getInstance().updateClan(defence_clan);
            final L1PcInstance defence_clan_member[] = defence_clan
                    .getOnlineClanMember();
            for (final L1PcInstance element : defence_clan_member) {
                if (element.getId() == defence_clan.getLeaderId()) { // 元の城主クランの君主
                    element.sendPackets(new S_CastleMaster(0, element.getId()));
                    element.broadcastPacket(new S_CastleMaster(0, element
                            .getId()));
                    break;
                }
            }
        }
        clan.setCastleId(castle_id);
        ClanTable.getInstance().updateClan(clan);
        player.sendPackets(new S_CastleMaster(castle_id, player.getId()));
        player.broadcastPacket(new S_CastleMaster(castle_id, player.getId()));

        // 强制传送出其他血盟的成员
        int[] loc = new int[3];
        for (final L1PcInstance pc : L1World.getInstance().getAllPlayers()) {
            if ((pc.getClanid() != player.getClanid()) && !pc.isGm()) {

                if (L1CastleLocation.checkInWarArea(castle_id, pc)) {
                    // 旗内に居る
                    loc = L1CastleLocation.getGetBackLoc(castle_id);
                    final int locx = loc[0];
                    final int locy = loc[1];
                    final short mapid = (short) loc[2];
                    L1Teleport.teleport(pc, locx, locy, mapid, 5, true);
                }
            }
        }

        // メッセージ表示
        for (final L1War war : wars) {
            if (war.CheckClanInWar(playerClanName) && existDefenseClan) {
                // 自クランが参加中で、城主が交代
                war.WinCastleWar(playerClanName);
                break;
            }
        }
        final L1PcInstance[] clanMember = clan.getOnlineClanMember();

        if (clanMember.length > 0) {
            final S_ServerMessage s_serverMessage = new S_ServerMessage(643); // 已占领城堡。
            for (final L1PcInstance pc : clanMember) {
                pc.sendPackets(s_serverMessage);
            }
        }

        // 删除王冠
        this.deleteMe();

        // 守护塔重新出现
        for (final L1Object l1object : L1World.getInstance().getObject()) {
            if (l1object instanceof L1TowerInstance) {
                final L1TowerInstance tower = (L1TowerInstance) l1object;
                if (L1CastleLocation.checkInWarArea(castle_id, tower)) {
                    tower.deleteMe();
                }
            }
        }
        final L1WarSpawn warspawn = new L1WarSpawn();
        warspawn.SpawnTower(castle_id);

        // 撤销城门
        for (final L1DoorInstance door : DoorTable.getInstance().getDoorList()) {
            if (L1CastleLocation.checkInWarArea(castle_id, door)) {
                door.repairGate();
            }
        }
    }
}
