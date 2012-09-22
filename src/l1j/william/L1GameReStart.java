package l1j.william;


//  elfooxx 重启系统

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.Server;
import l1j.server.server.GameServer;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;
//  elfooxx 重启系统
/*
*
* @author Administrator
*/
public class L1GameReStart {
	public  int _remnant ;  /* elfooxx */
	private static L1GameReStart _instance; /* elfooxx */

	private L1GameReStart() {
		GeneralThreadPool.getInstance().execute(new ReStart());
	}

	public class ReStart implements Runnable /*elfooxx*/ { 
		public void run() {
			while ( true ) {
				int remnant = GetRestartTime()* 60 ;
				// System.out.println( "伺服器设定： "+ GetRestartTime()+" 分钟后重新启动。" );
				while ( remnant > 0 ) {
					for ( int i = remnant ; i >= 0 ; i -- ) {
						SetRemnant(i) ;        
						if ( i % 60 == 0 && i <= 300 && i != 0 )  {
							BroadCastToAll( "伺服器将于 " + i/60 +" 分钟后重新启动，请至安全区域准备登出。" );
							System.out.println( "伺服器将于 " + i/60 +" 分钟后重新启动" );     
						} // if (五分钟内)
						else if ( i <= 30 && i != 0 )  {
							BroadCastToAll( "伺服器将于 " + i +"秒后重新启动，请至安全区域准备登出。" );
							System.out.println( "伺服器将于 " + i +" 秒后重新启动" );     
						} // if (30秒内)
						else if ( i == 0) {
							BroadCastToAll("伺服器重新启动。");
							System.out.println( "伺服器重新启动。");  
								System.exit(1);
						} // if 1秒
						try {
							Thread.sleep( 1000 );
						} catch ( InterruptedException e ) {
							;
						} // try and catch
					} // for
				} // while ( remnant > 0 ) 
			} // while true         
		} // run()
	} // class ReStart /*elfooxx*/

	private int GetRestartTime()  {
		Properties properties = new Properties();
		InputStream input = getClass().getResourceAsStream( "/config/altsettings.properties" );

		try {
			properties.load( input );
		}catch( IOException e ) {
			e.printStackTrace();
		} // try catch
		return Integer.parseInt( properties.getProperty( "RestartTime" ) );
	} // GetRestartTime() 

	private void BroadCastToAll(String string) {
		Collection <L1PcInstance> allpc = L1World.getInstance().getAllPlayers();
		for ( L1PcInstance pc : allpc )
			pc.sendPackets( new S_SystemMessage( string ) );
	} // BroadcastToAll()

	public void SetRemnant ( int remnant ) {
		_remnant = remnant ;
	}

	public int GetRemnant ( ) {
		return _remnant ;
	}

	public static void init() {
		_instance = new L1GameReStart();
	}

	public static L1GameReStart getInstance() {
		return _instance;
	}

} // class L1GameReStart() /*elfooxx 重启系统*/