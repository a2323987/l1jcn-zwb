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

/**
 * 邮件
 */
public class L1Mail {
    private int _id;

    private int _type;

    private String _senderName;

    private String _receiverName;

    private String _date = null; // yy/mm/dd

    private int _readStatus = 0;

    private byte[] _subject = null;

    private byte[] _content = null;

    public L1Mail() {
    }

    public byte[] getContent() {
        return this._content;
    }

    public String getDate() {
        return this._date;
    }

    public int getId() {
        return this._id;
    }

    public int getReadStatus() {
        return this._readStatus;
    }

    public String getReceiverName() {
        return this._receiverName;
    }

    public String getSenderName() {
        return this._senderName;
    }

    public byte[] getSubject() {
        return this._subject;
    }

    public int getType() {
        return this._type;
    }

    public void setContent(final byte[] arg) {
        this._content = arg;
    }

    public void setDate(final String s) {
        this._date = s;
    }

    public void setId(final int i) {
        this._id = i;
    }

    public void setReadStatus(final int i) {
        this._readStatus = i;
    }

    public void setReceiverName(final String s) {
        this._receiverName = s;
    }

    public void setSenderName(final String s) {
        this._senderName = s;
    }

    public void setSubject(final byte[] arg) {
        this._subject = arg;
    }

    public void setType(final int i) {
        this._type = i;
    }

}
