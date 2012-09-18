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
package com.lineage.server.serverpackets;

import com.lineage.Config;
import com.lineage.server.Opcodes;
import com.lineage.server.datatables.NPCTalkDataTable;
import com.lineage.server.model.L1NpcTalkData;
import com.lineage.server.model.Instance.L1FieldObjectInstance;
import com.lineage.server.model.Instance.L1NpcInstance;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

public class S_NPCPack extends ServerBasePacket {

    private static final String S_NPC_PACK = "[S] S_NPCPack";

    private static final int STATUS_POISON = 1;

    private static final int STATUS_PC = 4;

    private static final int HIDDEN_STATUS_FLY = 2;

    private byte[] _byte = null;

    public S_NPCPack(final L1NpcInstance npc) {
        this.writeC(Opcodes.S_OPCODE_CHARPACK);
        this.writeH(npc.getX());
        this.writeH(npc.getY());
        this.writeD(npc.getId());
        if (npc.getTempCharGfx() == 0) {
            this.writeH(npc.getGfxId());
        } else {
            this.writeH(npc.getTempCharGfx());
        }
        this.writeC(npc.getStatus());
        this.writeC(npc.getHeading());
        this.writeC(npc.getChaLightSize());
        this.writeC(npc.getMoveSpeed());
        this.writeExp(npc.getExp());
        this.writeH(npc.getTempLawful());
        if (Config.ShowNpcId) {
            this.writeS(npc.getNameId() + "[" + npc.getNpcId() + "]"
                    + " \\f4面向[" + npc.getHeading() + "]" + " \\fA图形["
                    + npc.getGfxId() + "]");
        } else {
            this.writeS(npc.getNameId());
        }
        if (npc instanceof L1FieldObjectInstance) { // SICの壁字、看板など
            final L1NpcTalkData talkdata = NPCTalkDataTable.getInstance()
                    .getTemplate(npc.getNpcTemplate().get_npcId());
            if (talkdata != null) {
                this.writeS(talkdata.getNormalAction()); // タイトルがHTML名として解釈される
            } else {
                this.writeS(null);
            }
        } else {
            this.writeS(npc.getTitle());
        }

        /**
         * シシニテ - 0:mob,item(atk pointer), 1:poisoned(), 2:invisable(), 4:pc,
         * 8:cursed(), 16:brave(), 32:??, 64:??(??), 128:invisable but name
         */
        int status = 0;
        if (npc.getPoison() != null) { // 毒状態
            if (npc.getPoison().getEffectId() == 1) {
                status |= STATUS_POISON;
            }
        }
        if (npc.getNpcTemplate().is_doppel()) {
            // 變形怪需強制攻擊判斷
            if ((npc.getGfxId() != 31)
                    && (npc.getNpcTemplate().get_npcId() != 81069)) {
                status |= STATUS_PC;
            }
        }
        // 二段加速狀態
        status |= npc.getBraveSpeed() * 16;

        this.writeC(status);

        this.writeD(0); // 0以外にするとC_27が飛ぶ
        this.writeS(null);
        this.writeS(null); // マスター名？
        if ((npc.getTempCharGfx() == 1024) || (npc.getTempCharGfx() == 2363)
                || (npc.getTempCharGfx() == 6697)
                || (npc.getTempCharGfx() == 8180)
                || (npc.getTempCharGfx() == 1204)
                || (npc.getTempCharGfx() == 2353)
                || (npc.getTempCharGfx() == 3631)
                || (npc.getTempCharGfx() == 2544)) { // 飛行系怪物
            this.writeC(npc.getHiddenStatus() == HIDDEN_STATUS_FLY ? 2 : 1); // 判斷是否飛天中
        } else {
            this.writeC(0);
        }
        this.writeC(0xFF); // HP
        this.writeC(0);
        this.writeC(npc.getLevel());
        this.writeC(0);
        this.writeC(0xFF);
        this.writeC(0xFF);
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this._bao.toByteArray();
        }

        return this._byte;
    }

    @Override
    public String getType() {
        return S_NPC_PACK;
    }

}
