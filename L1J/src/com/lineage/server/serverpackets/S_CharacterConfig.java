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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lineage.L1DatabaseFactory;
import com.lineage.server.Opcodes;
import com.lineage.server.utils.SQLUtil;

// Referenced classes of package com.lineage.server.serverpackets:
// ServerBasePacket

/**
 * 角色配置
 */
public class S_CharacterConfig extends ServerBasePacket {

    private static Logger _log = Logger.getLogger(S_CharacterConfig.class
            .getName());

    private static final String S_CHARACTER_CONFIG = "[S] S_CharacterConfig";

    private byte[] _byte = null;

    /**
     * 角色配置
     * 
     * @param objId
     */
    public S_CharacterConfig(final int objectId) {
        this.buildPacket(objectId);
    }

    private void buildPacket(final int objectId) {
        int length = 0;
        byte data[] = null;
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = L1DatabaseFactory.getInstance().getConnection();
            pstm = con
                    .prepareStatement("SELECT * FROM character_config WHERE object_id=?");
            pstm.setInt(1, objectId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                length = rs.getInt(2);
                data = rs.getBytes(3);
            }
        } catch (final SQLException e) {
            _log.log(Level.SEVERE, e.getLocalizedMessage(), e);
        } finally {
            SQLUtil.close(rs);
            SQLUtil.close(pstm);
            SQLUtil.close(con);
        }

        if (length != 0) {
            this.writeC(Opcodes.S_OPCODE_SKILLICONGFX);
            this.writeC(41);
            this.writeD(length);
            this.writeByte(data);
        }
    }

    @Override
    public byte[] getContent() {
        if (this._byte == null) {
            this._byte = this.getBytes();
        }
        return this._byte;
    }

    @Override
    public String getType() {
        return S_CHARACTER_CONFIG;
    }
}
