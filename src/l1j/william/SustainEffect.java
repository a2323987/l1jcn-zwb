package l1j.william;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SkillSound;

public class SustainEffect extends TimerTask {
        private static Logger _log = Logger.getLogger(SustainEffect.class.getName());

        private final L1PcInstance Active_pc;
        private final int effect_id;

        public SustainEffect(L1PcInstance pc ,int effect) {
                Active_pc = pc;
                effect_id = effect;
        }

	@Override
	public void run() {
		try {
			if (Active_pc.isDead()) {
				return;
			}
			if (l1j.william.L1WilliamSystemMessage.ShowMessage(137).equals("true")) {
				Active_pc.sendPackets(new S_SkillSound(Active_pc.getId(), effect_id));
				if (l1j.william.L1WilliamSystemMessage.ShowMessage(138).equals("true")) {
					Active_pc.broadcastPacket(new S_SkillSound(Active_pc.getId(), effect_id)); // 是否加入，请自行考量
				}
			}
			// Active_pc.broadcastPacketExceptTargetSight(new
			// S_SkillSound(Active_pc.getId(), effect_id),Active_pc); //
			// 是否加入，请自行考量
		} catch (Throwable e) {
			_log.log(Level.WARNING, e.getLocalizedMessage(), e);
		}
	}
}