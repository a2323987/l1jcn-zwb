package l1j.plugin;

import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;

public class ShowMeaagae {
	public void broadcastToAll(String s) {
		// Collection<L1PcInstance> al1pcinstance =
		// L1World.getInstance().getAllPlayers();
		S_SystemMessage s_systemmessage = new S_SystemMessage(s);
		for (L1PcInstance listner : L1World.getInstance().getAllPlayers()) {
			listner.sendPackets(s_systemmessage);
		}
	}
}
