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
import com.lineage.server.model.Instance.L1PcInstance;

/**
 * 使用地图
 */
public class S_UseMap extends ServerBasePacket {

    private static final String S_USE_MAP = "[S] S_UseMap";

    public S_UseMap(final L1PcInstance pc, final int objid, final int itemid) {

        this.writeC(Opcodes.S_OPCODE_USEMAP);
        this.writeD(objid);

        switch (itemid) {
            case 40373:
                this.writeD(16);
                break;
            case 40374:
                this.writeD(1);
                break;
            case 40375:
                this.writeD(2);
                break;
            case 40376:
                this.writeD(3);
                break;
            case 40377:
                this.writeD(4);
                break;
            case 40378:
                this.writeD(5);
                break;
            case 40379:
                this.writeD(6);
                break;
            case 40380:
                this.writeD(7);
                break;
            case 40381:
                this.writeD(8);
                break;
            case 40382:
                this.writeD(9);
                break;
            case 40383:
                this.writeD(10);
                break;
            case 40384:
                this.writeD(11);
                break;
            case 40385:
                this.writeD(12);
                break;
            case 40386:
                this.writeD(13);
                break;
            case 40387:
                this.writeD(14);
                break;
            case 40388:
                this.writeD(15);
                break;
            case 40389:
                this.writeD(17);
                break;
            case 40390:
                this.writeD(18);
                break;
        }
    }

    @Override
    public byte[] getContent() {
        return this.getBytes();
    }

    @Override
    public String getType() {
        return S_USE_MAP;
    }
}
