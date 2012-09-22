package l1j.william;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.sql.*;

import l1j.server.L1DatabaseFactory;
import l1j.server.Server;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.L1Clan;//判断城主
import l1j.server.server.model.L1Spawn;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_NPCTalkReturn;
import l1j.server.server.templates.L1Npc;








public class NpcSpawn{
	private static ArrayList aData9 = new ArrayList();
	private static boolean NO_MORE_GET_DATA9 = false;

	public static final String TOKEN = ",";

	public static void main(String a[]) {
		while(true) {
			try {
			Server.main(null);
			} catch(Exception ex) {
			}
		}
	}
	
	public static int[] forNpcSpawnTable(Map _spawntable){
        ArrayList aTempData = null;
        int iMiscHID = 0;
        int spawnCount = 0;
        if(!NO_MORE_GET_DATA9){
			NO_MORE_GET_DATA9 = true;
			  getData9();
		}
		
		for(int i=0;i<aData9.size();i++){
			aTempData = (ArrayList) aData9.get(i);
			//System.out.println("spawntable npc id="+((Integer)aTempData.get(0)));
			
			
			L1Npc l1npc = NpcTable.getInstance().getTemplate(
					((Integer)aTempData.get(0)).intValue());
			if( l1npc!=null ) {
				try {
					if (((Integer)aTempData.get(27)).intValue() == 0) {
						continue;
					}
					L1Spawn l1spawn;
					l1spawn = new L1Spawn(l1npc);
					l1spawn.setId(l1npc.get_npcId());
					l1spawn.setAmount(((Integer)aTempData.get(27)).intValue());
					l1spawn.setLocX(((Integer)aTempData.get(23)).intValue());
					l1spawn.setLocY(((Integer)aTempData.get(24)).intValue());
					l1spawn.setRandomx(0);
					l1spawn.setRandomy(0);
					l1spawn.setLocX1(0);
					l1spawn.setLocY1(0);
					l1spawn.setLocX2(0);
					l1spawn.setLocY2(0);
					l1spawn.setHeading(((Integer)aTempData.get(25)).intValue());
					l1spawn.setMinRespawnDelay(0);
					l1spawn.setMapId(((Integer)aTempData.get(26)).shortValue());
					l1spawn.setMovementDistance(0);
					l1spawn.setName(l1npc.get_name());
					l1spawn.init();
					spawnCount += l1spawn.getAmount();

					_spawntable.put(new Integer(l1spawn.getId()), l1spawn);
					if( iMiscHID < l1spawn.getId()) {
						iMiscHID = l1spawn.getId();
					}
				} catch(Exception exx){
					exx.printStackTrace();
				}
			}
		}  
		int[] iReturn = new int[2];
		iReturn[0] = spawnCount;
		iReturn[1] = iMiscHID;
		return iReturn;
	}
	public static void forNpcTable(HashMap _npcs){
        ArrayList aTempData = null;

		if(!NO_MORE_GET_DATA9){
			NO_MORE_GET_DATA9 = true;
			  getData9();
		}
		
		for(int i=0;i<aData9.size();i++){
			aTempData = (ArrayList) aData9.get(i);
		    L1Npc npc = new L1Npc();
			npc.set_npcId(((Integer)aTempData.get(0)).intValue());
			npc.set_name((String)aTempData.get(3));
			npc.set_nameid((String)aTempData.get(3));
			npc.setImpl((String)aTempData.get(1));
			npc.set_gfxid(((Integer)aTempData.get(2)).intValue());
			npc.set_level(0);
			npc.set_hp(0);
			npc.set_mp(0);
			npc.set_ac(0);
			npc.set_str((byte)0);
			npc.set_con((byte)0);
			npc.set_dex((byte)0);
			npc.set_wis((byte)0);
			npc.set_int((byte)0);
			npc.set_mr(0);
			npc.set_exp(0);
			npc.set_lawful(0);
			npc.set_size("small");
//			npc.set_weakwater(0);
//			npc.set_weakwind(0);
//			npc.set_weakfire(0);
//			npc.set_weakearth(0);
			npc.set_ranged(0);
			npc.setTamable(false);
			npc.set_passispeed(0);
			npc.set_atkspeed(0);
			npc.setAtkMagicSpeed(0);
			npc.setSubMagicSpeed(0);
			npc.set_undead(0);
			npc.set_poisonatk(0);
			npc.set_paralysisatk(0);
			npc.set_agro(false);
			npc.set_agrososc(false);
			npc.set_agrocoi(false);
			npc.set_family(0);
			npc.set_agrofamily(0);
			npc.set_agrogfxid1(-1);
			npc.set_agrogfxid2(-1);
			npc.set_picupitem(false);
			npc.set_digestitem(0);
			npc.set_bravespeed(false);
			npc.set_hprinterval(0);
			npc.set_hpr(0);
			npc.set_mprinterval(0);
			npc.set_mpr(0);
			npc.set_teleport(false);
			npc.set_randomlevel(0);
			npc.set_randomhp(0);
			npc.set_randommp(0);
			npc.set_randomac(0);
			npc.set_randomexp(0);
			npc.set_randomlawful(0);
			npc.set_damagereduction(0);
			npc.set_hard(false);
			npc.set_doppel(false);
			npc.set_IsTU(false);
			npc.set_IsErase(false);
			npc.setBowActId(0);
			npc.setKarma(0);
			npc.setTransformId(-1);
			npc.setLightSize(8);
			npc.setAmountFixed(false);
//			npc.set_atkexspeed(0);
//			npc.setAttStatus(0);
//			npc.setBowUseId(0);
//			npc.setHascastle(0);

            _npcs.put(new Integer(npc.get_npcId()), npc);
		}   
	}
//遇见npc可以设定html内容
	public static boolean forFirstTimeTalking(L1PcInstance player,int npcid,int oid){
        ArrayList aTempData = null;
		if(!NO_MORE_GET_DATA9){
			NO_MORE_GET_DATA9 = true;
			  getData9();
		}	
		int castle_id = 0;
		L1Clan clan = L1World.getInstance().getClan(player.getClanname());
		if (clan != null) {
			castle_id = clan.getCastleId();
		}
		
		for(int i=0;i<aData9.size();i++){
			aTempData = (ArrayList) aData9.get(i);
			if(aTempData.get(0)!=null&&((Integer)aTempData.get(0)).intValue()==npcid){
	        	if (((Integer) aTempData.get(15)).intValue() > 0 && 
	        		castle_id == ((Integer) aTempData.get(15)).intValue() && 
	        			player.isCrown()) {//判断是否为该城的城主
	        		player.sendPackets(new S_NPCTalkReturn(oid,(String)aTempData.get(16),(String[])aTempData.get(21)));
				  	return true;
	        	} else if (((Integer) aTempData.get(13)).intValue() > 0 && 
	        		((Integer) aTempData.get(14)).intValue() > 0 && 
	        		player.getInventory().checkItem(((Integer) aTempData.get(13)).intValue(), ((Integer) aTempData.get(14)).intValue())) {//判断身上是否持有特定物品
	        		player.sendPackets(new S_NPCTalkReturn(oid,(String)aTempData.get(20)));
	        	} else if (((Integer) aTempData.get(11)).intValue() > 0 && 
	        		((Integer) aTempData.get(12)).intValue() > 0 && 
	        		player.getInventory().checkItem(((Integer) aTempData.get(11)).intValue(), ((Integer) aTempData.get(12)).intValue())) {//判断身上是否持有特定物品
	        		player.sendPackets(new S_NPCTalkReturn(oid,(String)aTempData.get(19)));
	        	} else if (((Integer) aTempData.get(9)).intValue() > 0 && 
	        		((Integer) aTempData.get(10)).intValue() > 0 && 
	        		player.getInventory().checkItem(((Integer) aTempData.get(9)).intValue(), ((Integer) aTempData.get(10)).intValue())) {//判断身上是否持有特定物品
	        		player.sendPackets(new S_NPCTalkReturn(oid,(String)aTempData.get(18)));
	        	} else if( (String[])aTempData.get(21)!=null && 
	        		((((Integer) aTempData.get(4)).intValue()== 1 && (player.getClassId() == 0 || player.getClassId() == 1)) ||
	        		 (((Integer) aTempData.get(5)).intValue()== 1 && (player.getClassId() == 48 || player.getClassId() == 61)) ||
	        		 (((Integer) aTempData.get(6)).intValue()== 1 && (player.getClassId() == 37 || player.getClassId() == 138)) ||
	        		 (((Integer) aTempData.get(7)).intValue()== 1 && (player.getClassId() == 734 || player.getClassId() == 1186)) ||
	        		 (((Integer) aTempData.get(8)).intValue()== 1 && (player.getClassId() == 2786 || player.getClassId() == 2796)))){//判断可以对话的职业
				  player.sendPackets(new S_NPCTalkReturn(oid,(String)aTempData.get(16),(String[])aTempData.get(21)));
				  return true;
	        	} else {
	        	  player.sendPackets(new S_NPCTalkReturn(oid,(String)aTempData.get(17),(String[])aTempData.get(22)));
				  return true;
	        	}  
	        }
		}    
		return false;
		
		
	}
	private static void getData9(){
        java.sql.Connection con = null;
        try { 
          con = L1DatabaseFactory.getInstance().getConnection(); 
	      Statement stat = con.createStatement();
	      ResultSet rset = stat.executeQuery("SELECT * FROM william_spawn_npc");
	      ArrayList aReturn = null;
	      String sTemp = null;
	      if( rset!=null)
            while (rset.next()){
	    	  aReturn = new ArrayList();
		      aReturn.add(0, new Integer(rset.getInt("npcid")));
	    	  aReturn.add(1, rset.getString("type"));
	    	  aReturn.add(2, new Integer(rset.getInt("gfxid")));
	    	  aReturn.add(3, rset.getString("name"));
	    	  aReturn.add(4, new Integer(rset.getInt("UseRoyal")));
	    	  aReturn.add(5, new Integer(rset.getInt("UseKnight")));
	    	  aReturn.add(6, new Integer(rset.getInt("UseElf")));
	    	  aReturn.add(7, new Integer(rset.getInt("UseMage")));
	    	  aReturn.add(8, new Integer(rset.getInt("UseDarkelf")));
	    	  aReturn.add(9, new Integer(rset.getInt("CheckItem1")));
	    	  aReturn.add(10, new Integer(rset.getInt("Count1")));
	    	  aReturn.add(11, new Integer(rset.getInt("CheckItem2")));
	    	  aReturn.add(12, new Integer(rset.getInt("Count2")));
	    	  aReturn.add(13, new Integer(rset.getInt("CheckItem3")));
	    	  aReturn.add(14, new Integer(rset.getInt("Count3")));
	    	  aReturn.add(15, new Integer(rset.getInt("CheckCastleId")));
	    	  aReturn.add(16, new String(rset.getString("class_htmlid")));
              aReturn.add(17, new String(rset.getString("other_htmlid")));
              aReturn.add(18, new String(rset.getString("Item1_htmlid")));
              aReturn.add(19, new String(rset.getString("Item2_htmlid")));
              aReturn.add(20, new String(rset.getString("Item3_htmlid")));
              aReturn.add(21,getArray(rset.getString("class_htmldata"),TOKEN,2) );
	    	  aReturn.add(22,getArray(rset.getString("other_htmldata"),TOKEN,2) );
	    	  aReturn.add(23, new Integer(rset.getInt("location_x")));
	    	  aReturn.add(24, new Integer(rset.getInt("location_y")));
	    	  aReturn.add(25, new Integer(rset.getInt("heading")));
	    	  aReturn.add(26, new Integer(rset.getInt("map")));
	    	  aReturn.add(27, new Integer(rset.getInt("count")));
	    	  aData9.add(aReturn);
            }
          if(con!=null && !con.isClosed())
        	  con.close();
        }
        catch(Exception ex){
        	
        }
	}
	
	private static Object getArray(String s,String sToken,int iType){
     StringTokenizer st = new StringTokenizer(s,sToken);
     int iSize = st.countTokens();
     String  sTemp = null;
     if(iType==1){ // int
     	int[] iReturn = new int[iSize];
     	for(int i=0;i<iSize;i++){
       	  sTemp = st.nextToken();
     	  iReturn[i] = Integer.parseInt( sTemp );
     	}
     	return iReturn;
      }
     if(iType==2){ // String
      	String[] sReturn = new String[iSize];
      	for(int i=0;i<iSize;i++){
       	  sTemp = st.nextToken();
      	  sReturn[i] = sTemp;
      	}
      	return sReturn;
       }
     if(iType==3){ // String
      	String sReturn = null;
      	for(int i=0;i<iSize;i++){
       	  sTemp = st.nextToken();
      	  sReturn = sTemp;
      	}
      	return sReturn;
       }
     return null;
   }
}