package l1j.william;
import java.lang.management.*;
import javax.management.*;


public class Config implements ConfigMBean {
	
  public boolean setParameterValue(String pName, String pValue){
    return l1j.server.Config.setParameterValue(pName, pValue);
  }
  
  public String getParameterValue(String pName){
		if( pName.equals("RateXp") )
			return ""+l1j.server.Config.RATE_XP;
		if( pName.equals("RateDropAdena") )
			return ""+l1j.server.Config.RATE_DROP_ADENA;
		if( pName.equals("RateDropItems") )
			return ""+l1j.server.Config.RATE_DROP_ITEMS;
		if( pName.equals("EnchantChanceWeapon") )
			return ""+l1j.server.Config.ENCHANT_CHANCE_WEAPON;
		if( pName.equals("EnchantChanceArmor") )
			return ""+l1j.server.Config.ENCHANT_CHANCE_ARMOR;
	
	return "not support this value";
  }
  /*
  public boolean loginValid(String account,String password){
	  return (new l1j.server.server.Logins(true)).loginValid(account, password, "127.0.0.1");
  }
  */

}