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

/**
 * NPC说话数据
 */
public class L1NpcTalkData {

    int ID;

    int NpcID;

    String NpcName;

    String normalAction;

    String caoticAction;

    String teleportURL;

    String teleportURLA;

    String noSell;

    /**
     * @return Returns the caoticAction.
     */
    public String getCaoticAction() {
        return this.caoticAction;
    }

    /**
     * @return Returns the iD.
     */
    public int getID() {
        return this.ID;
    }

    /**
     * @return Returns the normalAction.
     */
    public String getNormalAction() {
        return this.normalAction;
    }

    /**
     * @return
     */
    public String getNoSell() {
        return this.noSell;
    }

    /**
     * @return Returns the npcID.
     */
    public int getNpcID() {
        return this.NpcID;
    }

    /**
     * @return Returns the npcName.
     */
    public String getNpcName() {
        return this.NpcName;
    }

    /**
     * @return Returns the teleportURL.
     */
    public String getTeleportURL() {
        return this.teleportURL;
    }

    /**
     * @return Returns the teleportURLA.
     */
    public String getTeleportURLA() {
        return this.teleportURLA;
    }

    /**
     * @param caoticAction
     *            The caoticAction to set.
     */
    public void setCaoticAction(final String caoticAction) {
        this.caoticAction = caoticAction;
    }

    /**
     * @param id
     *            The iD to set.
     */
    public void setID(final int id) {
        this.ID = id;
    }

    /**
     * @param normalAction
     *            The normalAction to set.
     */
    public void setNormalAction(final String normalAction) {
        this.normalAction = normalAction;
    }

    /**
     * @param noSell
     */
    public void setNoSell(final String noSell) {
        this.noSell = noSell;
    }

    /**
     * @param npcID
     *            The npcID to set.
     */
    public void setNpcID(final int npcID) {
        this.NpcID = npcID;
    }

    /**
     * @param npcName
     *            The npcName to set.
     */
    public void setNpcName(final String npcName) {
        this.NpcName = npcName;
    }

    /**
     * @param teleportURL
     *            The teleportURL to set.
     */
    public void setTeleportURL(final String teleportURL) {
        this.teleportURL = teleportURL;
    }

    /**
     * @param teleportURLA
     *            The teleportURLA to set.
     */
    public void setTeleportURLA(final String teleportURLA) {
        this.teleportURLA = teleportURLA;
    }

}
