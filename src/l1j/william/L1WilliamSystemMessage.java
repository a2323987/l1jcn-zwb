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
package l1j.william;

import l1j.william.SystemMessage;

public class L1WilliamSystemMessage {

	private int _id;

	private String _message;

	public L1WilliamSystemMessage(int id, String message) {

		_id = id;
		_message = message;
	}

	public int getId() {
		return _id;
	}

	public String getMessage() {
		return _message;
	}

	public static String ShowMessage(int id) {
		L1WilliamSystemMessage System_Message = SystemMessage.getInstance().getTemplate(id);

		if (System_Message == null) {
			return "";
		}

		String Message = System_Message.getMessage();
		return Message;
	}
}
