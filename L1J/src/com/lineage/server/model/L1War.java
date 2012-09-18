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
package com.lineage.server.model;

import java.util.Calendar;
import java.util.List;

import com.lineage.Config;
import com.lineage.server.GeneralThreadPool;
import com.lineage.server.WarTimeController;
import com.lineage.server.datatables.CastleTable;
import com.lineage.server.model.Instance.L1PcInstance;
import com.lineage.server.serverpackets.S_ServerMessage;
import com.lineage.server.serverpackets.S_War;
import com.lineage.server.templates.L1Castle;
import com.lineage.server.utils.collections.Lists;

// Referenced classes of package com.lineage.server.model:
// L1War

/**
 * 战争
 */
public class L1War {

    class CastleWarTimer implements Runnable {
        public CastleWarTimer() {
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    Thread.sleep(1000);
                    if (L1War.this._warEndTime.before(WarTimeController
                            .getInstance().getRealTime())) {
                        break;
                    }
                } catch (final Exception exception) {
                    break;
                }
                if (L1War.this._isWarTimerDelete) { // 如果定时器结束，结束战争
                    return;
                }
            }
            L1War.this.CeaseCastleWar(); // 攻城战终结处理
            L1War.this.delete();
        }
    }

    class SimWarTimer implements Runnable {
        public SimWarTimer() {
        }

        @Override
        public void run() {
            for (int loop = 0; loop < 240; loop++) { // 240分
                try {
                    Thread.sleep(60000);
                } catch (final Exception exception) {
                    break;
                }
                if (L1War.this._isWarTimerDelete) { // 如果定时器结束，结束战争
                    return;
                }
            }
            L1War.this.CeaseWar(L1War.this._param1, L1War.this._param2); // 终结
            L1War.this.delete();
        }
    }

    /**  */
    String _param1 = null;
    /**  */
    String _param2 = null;
    /** 攻击方血盟名称 */
    private final List<String> _attackClanList = Lists.newList();
    /** 防守方血盟名称 */
    private String _defenceClanName = null;
    /** 战争类型 */
    private int _warType = 0;
    /** 城堡 */
    private L1Castle _castle = null;

    /** 战争结束时间 */
    Calendar _warEndTime;

    /** 删除战争定时器中 */
    boolean _isWarTimerDelete = false;

    public L1War() {
    }

    public void AddAttackClan(final String attack_clan_name) {
        if (!this._attackClanList.contains(attack_clan_name)) {
            this._attackClanList.add(attack_clan_name);
        }
    }

    /** 停止攻城战 */
    public void CeaseCastleWar() { // 到战争结束时间、防守方胜利
        final String defence_clan_name = this.GetDefenceClanName();
        final String clanList[] = this.GetAttackClanList();
        if (defence_clan_name != null) {
            L1World.getInstance().broadcastPacketToAll(new S_ServerMessage( // %0
                                                                            // 血盟赢了对
                                                                            // %1
                                                                            // 血盟的战争。
                    231, defence_clan_name, clanList[0]));
        }

        final L1Clan defence_clan = L1World.getInstance().getClan(
                defence_clan_name);
        if (defence_clan != null) {
            final L1PcInstance defence_clan_member[] = defence_clan
                    .getOnlineClanMember();
            for (final L1PcInstance element : defence_clan_member) {
                element.sendPackets(new S_War(4, defence_clan_name, clanList[0]));
            }
        }

        for (final String element : clanList) {
            if (element != null) {
                L1World.getInstance().broadcastPacketToAll(new S_ServerMessage( // %0
                                                                                // 血盟与
                                                                                // %1血盟之间的战争结束了。
                        227, defence_clan_name, element));
                final L1Clan clan = L1World.getInstance().getClan(element);
                if (clan != null) {
                    final L1PcInstance clan_member[] = clan
                            .getOnlineClanMember();
                    for (final L1PcInstance element2 : clan_member) {
                        element2.sendPackets(new S_War(3, element,
                                defence_clan_name));
                    }
                }
            }
        }

        this._isWarTimerDelete = true;
        this.delete();
    }

    /** 停止战争 */
    public void CeaseWar(final String clan1_name, final String clan2_name) { // _血盟と_血盟との战争が终结しました。
        if (this.GetWarType() == 1) {
            this.RequestCastleWar(3, clan1_name, clan2_name);
        } else {
            this.RequestSimWar(3, clan1_name, clan2_name);
        }
    }

    public boolean CheckAttackClan(final String attack_clan_name) {
        if (this._attackClanList.contains(attack_clan_name)) {
            return true;
        }
        return false;
    }

    /** 检查在同一场战争的血盟 */
    public boolean CheckClanInSameWar(final String player_clan_name,
            final String target_clan_name) { // 自クランと相手クランが同じ战争に参加しているかチェックする（同じクランの场合も含む）
        boolean player_clan_flag;
        boolean target_clan_flag;

        if (this.GetDefenceClanName().toLowerCase()
                .equals(player_clan_name.toLowerCase())) { // 自クランに对して防卫侧クランをチェック
            player_clan_flag = true;
        } else {
            player_clan_flag = this.CheckAttackClan(player_clan_name); // 自クランに对して攻击侧クランをチェック
        }

        if (this.GetDefenceClanName().toLowerCase()
                .equals(target_clan_name.toLowerCase())) { // 相手クランに对して防卫侧クランをチェック
            target_clan_flag = true;
        } else {
            target_clan_flag = this.CheckAttackClan(target_clan_name); // 相手クランに对して攻击侧クランをチェック
        }

        if ((player_clan_flag == true) && (target_clan_flag == true)) {
            return true;
        }
        return false;
    }

    /** 检查在战争的血盟 */
    public boolean CheckClanInWar(final String clan_name) { // 检查是否已加入血盟战争
        boolean ret;
        if (this.GetDefenceClanName().toLowerCase()
                .equals(clan_name.toLowerCase())) { // 防卫侧クランをチェック
            ret = true;
        } else {
            ret = this.CheckAttackClan(clan_name); // 攻击侧クランをチェック
        }
        return ret;
    }

    /** 宣战 */
    public void DeclareWar(final String clan1_name, final String clan2_name) { // _血盟が_血盟に宣战布告しました。
        if (this.GetWarType() == 1) { // 攻城战
            this.RequestCastleWar(1, clan1_name, clan2_name);
        } else { // 模拟战
            this.RequestSimWar(1, clan1_name, clan2_name);
        }
    }

    public void delete() {
        L1World.getInstance().removeWar(this); // 从名单中删除战争
    }

    public String[] GetAttackClanList() {
        return this._attackClanList.toArray(new String[this._attackClanList
                .size()]);
    }

    public int GetAttackClanListSize() {
        return this._attackClanList.size();
    }

    public L1Castle GetCastle() {
        L1Castle l1castle = null;
        if (this.GetWarType() == 1) { // 攻城战
            final L1Clan clan = L1World.getInstance().getClan(
                    this.GetDefenceClanName());
            if (clan != null) {
                final int castle_id = clan.getCastleId();
                l1castle = CastleTable.getInstance().getCastleTable(castle_id);
            }
        }
        return l1castle;
    }

    public int GetCastleId() {
        int castle_id = 0;
        if (this.GetWarType() == 1) { // 攻城战
            final L1Clan clan = L1World.getInstance().getClan(
                    this.GetDefenceClanName());
            if (clan != null) {
                castle_id = clan.getCastleId();
            }
        }
        return castle_id;
    }

    public String GetDefenceClanName() {
        return this._defenceClanName;
    }

    /** 取得敌对方血盟名称 */
    public String GetEnemyClanName(final String player_clan_name) { // 相手のクラン名を取得する
        String enemy_clan_name = null;
        if (this.GetDefenceClanName().toLowerCase()
                .equals(player_clan_name.toLowerCase())) { // 自クランが防卫侧
            final String clanList[] = this.GetAttackClanList();
            for (final String element : clanList) {
                if (element != null) {
                    enemy_clan_name = element;
                    return enemy_clan_name; // リストの先头のクラン名を返す
                }
            }
        } else { // 自クランが攻击侧
            enemy_clan_name = this.GetDefenceClanName();
            return enemy_clan_name;
        }
        return enemy_clan_name;
    }

    public int GetWarType() {
        return this._warType;
    }

    /** 处理命令 */
    public void handleCommands(final int war_type,
            final String attack_clan_name, final String defence_clan_name) {
        // war_type - 1:攻城战 2:模拟战
        // attack_clan_name - 公告的血盟名称
        // defence_clan_name - 公告的血盟名称（攻城战时、城主）

        this.SetWarType(war_type);

        this.DeclareWar(attack_clan_name, defence_clan_name);

        this._param1 = attack_clan_name;
        this._param2 = defence_clan_name;
        this.InitAttackClan();
        this.AddAttackClan(attack_clan_name);
        this.SetDefenceClanName(defence_clan_name);

        if (war_type == 1) { // 攻城战
            this.GetCastleId();
            this._castle = this.GetCastle();
            if (this._castle != null) {
                final Calendar cal = (Calendar) this._castle.getWarTime()
                        .clone();
                cal.add(Config.ALT_WAR_TIME_UNIT, Config.ALT_WAR_TIME);
                this._warEndTime = cal;
            }

            final CastleWarTimer castle_war_timer = new CastleWarTimer();
            GeneralThreadPool.getInstance().execute(castle_war_timer); // 定时器启动
        } else if (war_type == 2) { // 模拟战
            final SimWarTimer sim_war_timer = new SimWarTimer();
            GeneralThreadPool.getInstance().execute(sim_war_timer); // 定时器启动
        }
        L1World.getInstance().addWar(this); // 添加到战争列表
    }

    public void InitAttackClan() {
        this._attackClanList.clear();
    }

    public void RemoveAttackClan(final String attack_clan_name) {
        if (this._attackClanList.contains(attack_clan_name)) {
            this._attackClanList.remove(attack_clan_name);
        }
    }

    /** 请求攻城战 */
    private void RequestCastleWar(final int type, final String clan1_name,
            final String clan2_name) {
        if ((clan1_name == null) || (clan2_name == null)) {
            return;
        }

        final L1Clan clan1 = L1World.getInstance().getClan(clan1_name);
        if (clan1 != null) {
            final L1PcInstance clan1_member[] = clan1.getOnlineClanMember();
            for (final L1PcInstance element : clan1_member) {
                element.sendPackets(new S_War(type, clan1_name, clan2_name));
            }
        }

        final int attack_clan_num = this.GetAttackClanListSize();

        if ((type == 1) || (type == 2) || (type == 3)) { // 宣战布告、降伏、终结
            final L1Clan clan2 = L1World.getInstance().getClan(clan2_name);
            if (clan2 != null) {
                final L1PcInstance clan2_member[] = clan2.getOnlineClanMember();
                for (final L1PcInstance element : clan2_member) {
                    if (type == 1) { // 宣战布告
                        element.sendPackets(new S_War(type, clan1_name,
                                clan2_name));
                    } else if (type == 2) { // 降伏
                        element.sendPackets(new S_War(type, clan1_name,
                                clan2_name));
                        if (attack_clan_num == 1) { // 攻击侧クランが一つ
                            element.sendPackets(new S_War(4, clan2_name,
                                    clan1_name));
                        } else {
                            element.sendPackets(new S_ServerMessage( // %0 血盟向
                                                                     // %1
                                                                     // 血盟投降了。
                                    228, clan1_name, clan2_name));
                            this.RemoveAttackClan(clan1_name);
                        }
                    } else if (type == 3) { // 终结
                        element.sendPackets(new S_War(type, clan1_name,
                                clan2_name));
                        if (attack_clan_num == 1) { // 攻击侧クランが一つ
                            element.sendPackets(new S_War(4, clan2_name,
                                    clan1_name));
                        } else {
                            element.sendPackets(new S_ServerMessage( // %0 血盟与
                                                                     // %1血盟之间的战争结束了。
                                    227, clan1_name, clan2_name));
                            this.RemoveAttackClan(clan1_name);
                        }
                    }
                }
            }
        }

        if (((type == 2) || (type == 3)) && (attack_clan_num >= 1)) { // 投降、终止后攻击方大于或等于一
            this._isWarTimerDelete = true;
            this.delete();
        }
    }

    /** 请求Sim战争 */
    private void RequestSimWar(final int type, final String clan1_name,
            final String clan2_name) {
        if ((clan1_name == null) || (clan2_name == null)) {
            return;
        }

        final L1Clan clan1 = L1World.getInstance().getClan(clan1_name);
        if (clan1 != null) {
            final L1PcInstance clan1_member[] = clan1.getOnlineClanMember();
            for (final L1PcInstance element : clan1_member) {
                element.sendPackets(new S_War(type, clan1_name, clan2_name));
            }
        }

        if ((type == 1) || (type == 2) || (type == 3)) { // 宣战布告、降伏、终结
            final L1Clan clan2 = L1World.getInstance().getClan(clan2_name);
            if (clan2 != null) {
                final L1PcInstance clan2_member[] = clan2.getOnlineClanMember();
                for (final L1PcInstance element : clan2_member) {
                    if (type == 1) { // 宣战布告
                        element.sendPackets(new S_War(type, clan1_name,
                                clan2_name));
                    } else if ((type == 2) || (type == 3)) { // 降伏、终结
                        element.sendPackets(new S_War(type, clan1_name,
                                clan2_name));
                        element.sendPackets(new S_War(4, clan2_name, clan1_name));
                    }
                }
            }
        }

        if ((type == 2) || (type == 3)) { // 降伏、终结
            this._isWarTimerDelete = true;
            this.delete();
        }
    }

    public void SetDefenceClanName(final String defence_clan_name) {
        this._defenceClanName = defence_clan_name;
    }

    public void SetWarType(final int war_type) {
        this._warType = war_type;
    }

    /** 投降 */
    public void SurrenderWar(final String clan1_name, final String clan2_name) { // _血盟が_血盟に降伏しました。
        if (this.GetWarType() == 1) {
            this.RequestCastleWar(2, clan1_name, clan2_name);
        } else {
            this.RequestSimWar(2, clan1_name, clan2_name);
        }
    }

    /** 赢得胜利 */
    public void WinCastleWar(final String clan_name) { // 取得王冠、攻击方胜利
        final String defence_clan_name = this.GetDefenceClanName();
        L1World.getInstance().broadcastPacketToAll(new S_ServerMessage( // %0
                                                                        // 血盟赢了对
                                                                        // %1
                                                                        // 血盟的战争。
                231, clan_name, defence_clan_name));

        final L1Clan defence_clan = L1World.getInstance().getClan(
                defence_clan_name);
        if (defence_clan != null) {
            final L1PcInstance defence_clan_member[] = defence_clan
                    .getOnlineClanMember();
            for (final L1PcInstance element : defence_clan_member) {
                for (final String clanName : this.GetAttackClanList()) {
                    element.sendPackets(new S_War(3, defence_clan_name,
                            clanName));
                }
            }
        }

        final String clanList[] = this.GetAttackClanList();
        for (final String element : clanList) {
            if (element != null) {
                L1World.getInstance().broadcastPacketToAll(new S_ServerMessage( // %0
                                                                                // 血盟与
                                                                                // %1血盟之间的战争结束了。
                        227, defence_clan_name, element));
                final L1Clan clan = L1World.getInstance().getClan(element);
                if (clan != null) {
                    final L1PcInstance clan_member[] = clan
                            .getOnlineClanMember();
                    for (final L1PcInstance element2 : clan_member) {
                        element2.sendPackets(new S_War(3, element,
                                defence_clan_name));
                    }
                }
            }
        }

        this._isWarTimerDelete = true;
        this.delete();
    }

    /** 赢得战争 */
    public void WinWar(final String clan1_name, final String clan2_name) { // _血盟が_血盟との战争で胜利しました。
        if (this.GetWarType() == 1) {
            this.RequestCastleWar(4, clan1_name, clan2_name);
        } else {
            this.RequestSimWar(4, clan1_name, clan2_name);
        }
    }
}
