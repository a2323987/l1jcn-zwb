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

import com.lineage.server.Opcodes;
import com.lineage.server.model.Instance.L1NpcInstance;
import com.lineage.server.model.Instance.L1PetInstance;
import com.lineage.server.model.Instance.L1SummonInstance;

/**
 * NPC对话视窗
 */
public class S_PetMenuPacket extends ServerBasePacket {

    private byte[] _byte = null;

    /**
     * NPC对话视窗
     * 
     * @param npc
     * @param exppercet
     */
    public S_PetMenuPacket(final L1NpcInstance npc, final int exppercet) {
        this.buildpacket(npc, exppercet);
    }

    private void buildpacket(final L1NpcInstance npc, final int exppercet) {
        this.writeC(Opcodes.S_OPCODE_SHOWHTML);

        if (npc instanceof L1PetInstance) { // 宠物
            final L1PetInstance pet = (L1PetInstance) npc;
            this.writeD(pet.getId());
            this.writeS("anicom");
            this.writeC(0x00);
            this.writeH(10);
            switch (pet.getCurrentPetStatus()) {
                case 1:
                    this.writeS("$469"); // 攻击态势
                    break;
                case 2:
                    this.writeS("$470"); // 防御态势
                    break;
                case 3:
                    this.writeS("$471"); // 休憩
                    break;
                case 5:
                    this.writeS("$472"); // 警戒
                    break;
                default:
                    this.writeS("$471"); // 休憩
                    break;
            }
            this.writeS(Integer.toString(pet.getCurrentHp())); // 现在ＨＰ
            this.writeS(Integer.toString(pet.getMaxHp())); // 最大ＨＰ
            this.writeS(Integer.toString(pet.getCurrentMp())); // 现在ＭＰ
            this.writeS(Integer.toString(pet.getMaxMp())); // 最大ＭＰ
            this.writeS(Integer.toString(pet.getLevel())); // 等级

            // 名前の文字数が8を超えると落ちる
            // なぜか"セント バーナード","ブレイブ ラビット"はOK
            // String pet_name = pet.get_name();
            // if (pet_name.equalsIgnoreCase("ハイ ドーベルマン")) {
            // pet_name = "ハイ ドーベルマ";
            // }
            // else if (pet_name.equalsIgnoreCase("ハイ セントバーナード")) {
            // pet_name = "ハイ セントバー";
            // }
            // writeS(pet_name);
            this.writeS(""); // ペットの名前を表示させると不安定になるので、非表示にする

            String s = "$610";
            if (pet.get_food() > 80) {
                s = "$612"; // 非常饱。
            } else if (pet.get_food() > 60) {
                s = "$611"; // 稍微饱。
            } else if (pet.get_food() > 30) {
                s = "$610"; // 普通。
            } else if (pet.get_food() > 10) {
                s = "$609"; // 稍微饿。
            } else if (pet.get_food() >= 0) {
                s = "$608"; // 非常饿。
            }
            this.writeS(s); // 饱食度
            this.writeS(Integer.toString(exppercet)); // 经验值
            this.writeS(Integer.toString(pet.getLawful())); // 正义值
        } else if (npc instanceof L1SummonInstance) { // 召唤兽
            final L1SummonInstance summon = (L1SummonInstance) npc;
            this.writeD(summon.getId());
            this.writeS("moncom");
            this.writeC(0x00);
            this.writeH(6); // 渡す引数文字の数の模样
            switch (summon.get_currentPetStatus()) {
                case 1:
                    this.writeS("$469"); // 攻击态势
                    break;
                case 2:
                    this.writeS("$470"); // 防御态势
                    break;
                case 3:
                    this.writeS("$471"); // 休憩
                    break;
                case 5:
                    this.writeS("$472"); // 警戒
                    break;
                default:
                    this.writeS("$471"); // 休憩
                    break;
            }
            this.writeS(Integer.toString(summon.getCurrentHp())); // 现在ＨＰ
            this.writeS(Integer.toString(summon.getMaxHp())); // 最大ＨＰ
            this.writeS(Integer.toString(summon.getCurrentMp())); // 现在ＭＰ
            this.writeS(Integer.toString(summon.getMaxMp())); // 最大ＭＰ
            this.writeS(Integer.toString(summon.getLevel())); // 等级
            // writeS(summon.getNpcTemplate().get_nameid());
            // writeS(Integer.toString(0));
            // writeS(Integer.toString(790));
        }
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this._bao.toByteArray();
        }

        return this._byte;
    }
}
