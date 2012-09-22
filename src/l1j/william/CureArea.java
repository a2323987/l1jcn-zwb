package l1j.william;

import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillSound;

public class CureArea extends Thread{
	private int iMinX,iMinY,iMaxX,iMaxY,iMapID,iCureHp,iCureMp,iCurePeriod,iTimes,iGfxid;
    private l1j.server.server.model.Instance.L1PcInstance pc;
	private CureArea(){}
	
	public CureArea(int minx,int miny,int maxx,int maxy,int mapid,int mp,int hp,int cure_period,int times,int gfxid){
	   iMinX = minx; // 最小x
	   iMinY = miny; // 最小y
	   iMaxX = maxx; // 最大x
	   iMaxY = maxy; // 最大y
	   iMapID = mapid; // 地图号码
	   iCureHp = hp;// 每回合治疗点数 hp
	   iCureMp = mp;// 每回合治疗点数 mp
	   iTimes = times;//治疗几回合, 0代表无限
	   iGfxid = gfxid;// 魔法技能
	   iCurePeriod = cure_period; // 每回合要等几秒
	}	
	//@Override
	public void run() {
		if( iTimes!=0){
		  for( int i=0;i<iTimes;i++){
			try{
				sleep(iCurePeriod*100);
				for (L1PcInstance pc : L1World.getInstance().getAllPlayers()){
					if (pc != null) {
						if( pc.getX() >= iMinX && pc.getX() <= iMaxX && pc.getY() >= iMinY && pc.getY() <= iMaxY && pc.getMapId() == iMapID )
						  if( !pc.isDead() ){
							  if( pc.getCurrentHp() < pc.getBaseMaxHp()|| pc.getCurrentMp() < pc.getBaseMaxMp() ){
								pc.sendPackets(new S_SkillSound(pc.getId(), iGfxid));
								pc.broadcastPacket(new S_SkillSound(pc.getId(), iGfxid));
								pc.sendPackets(new S_ServerMessage(77, "")); // \f1气分が良くなりました。
								pc.setCurrentHp(pc.getCurrentHp() + iCureHp);
								pc.setCurrentMp(pc.getCurrentMp() + iCureMp);
							  }  
						  }  
					}				
				}
				}catch(Exception ex){}
		  }	
		}
		else{	
		  do{
			try{
			sleep(iCurePeriod * 100);
			for (L1PcInstance pc : L1World.getInstance().getAllPlayers()) {
				if (pc != null) {
					if( pc.getX()>=iMinX && pc.getX() <= iMaxX && pc.getY() >= iMinY && pc.getY() <= iMaxY && pc.getMapId() == iMapID )
					  if( !pc.isDead() ){
						  if( pc.getCurrentHp() < pc.getBaseMaxHp()|| pc.getCurrentMp() < pc.getBaseMaxMp() ){
								pc.sendPackets(new S_SkillSound(pc.getId(), iGfxid));
								pc.broadcastPacket(new S_SkillSound(pc.getId(), iGfxid));
								pc.sendPackets(new S_ServerMessage(77, "")); // \f1气分が良くなりました。
								pc.setCurrentHp(pc.getCurrentHp() + iCureHp);
								pc.setCurrentMp(pc.getCurrentMp() + iCureMp);
						  }  
					  }  
				}				
			}
			}catch(Exception ex){}
		  }while(true);
		}
	}	
}