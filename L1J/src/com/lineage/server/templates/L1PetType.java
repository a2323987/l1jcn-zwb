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
package com.lineage.server.templates;

import com.lineage.server.datatables.NpcTable;
import com.lineage.server.utils.IntRange;

public class L1PetType {
    public static int getMessageNumber(final int level) {
        if (50 <= level) {
            return 5;
        }
        if (48 <= level) {
            return 4;
        }
        if (36 <= level) {
            return 3;
        }
        if (24 <= level) {
            return 2;
        }
        if (12 <= level) {
            return 1;
        }
        return 0;
    }

    private final int _baseNpcId;

    private final L1Npc _baseNpcTemplate;

    private final String _name;

    private final int _itemIdForTaming;

    private final IntRange _hpUpRange;

    private final IntRange _mpUpRange;

    private final int _npcIdForEvolving;

    private final int _msgIds[];

    private final int _defyMsgId;

    private final int _evolvItemId;

    private final boolean _canUseEquipment;

    public L1PetType(final int baseNpcId, final String name,
            final int itemIdForTaming, final IntRange hpUpRange,
            final IntRange mpUpRange, final int evolvItemId,
            final int npcIdForEvolving, final int msgIds[],
            final int defyMsgId, final boolean canUseEquipment) {
        this._baseNpcId = baseNpcId;
        this._baseNpcTemplate = NpcTable.getInstance().getTemplate(baseNpcId);
        this._name = name;
        this._itemIdForTaming = itemIdForTaming;
        this._hpUpRange = hpUpRange;
        this._mpUpRange = mpUpRange;
        this._evolvItemId = evolvItemId;
        this._npcIdForEvolving = npcIdForEvolving;
        this._msgIds = msgIds;
        this._defyMsgId = defyMsgId;
        this._canUseEquipment = canUseEquipment;

    }

    public boolean canEvolve() {
        return this._npcIdForEvolving != 0;
    }

    public boolean canTame() {
        return this._itemIdForTaming != 0;
    }

    // 可使用宠物装备
    public boolean canUseEquipment() {
        return this._canUseEquipment;
    }

    public int getBaseNpcId() {
        return this._baseNpcId;
    }

    public L1Npc getBaseNpcTemplate() {
        return this._baseNpcTemplate;
    }

    public int getDefyMessageId() {
        return this._defyMsgId;
    }

    // 进化道具
    public int getEvolvItemId() {
        return this._evolvItemId;
    }

    public IntRange getHpUpRange() {
        return this._hpUpRange;
    }

    public int getItemIdForTaming() {
        return this._itemIdForTaming;
    }

    public int getMessageId(final int num) {
        if (num == 0) {
            return 0;
        }
        return this._msgIds[num - 1];
    }

    public IntRange getMpUpRange() {
        return this._mpUpRange;
    }

    public String getName() {
        return this._name;
    }

    public int getNpcIdForEvolving() {
        return this._npcIdForEvolving;
    }

}
