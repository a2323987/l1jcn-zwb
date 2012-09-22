package l1j.william;

import java.lang.reflect.*;

public class Reflection {

  /**
   * 可动态取得未知的类别,进而使用其物件. by william 
   * <pre>
   *   if we create a class and want to use it dynamically
   *   ex:
   *     1:  com.fromtw.mybean aObj = new mybean(String,String);
   *     2:
   *     Object obj[] = new Object[2];
   *     obj[0] = "ddd";
   *     obj[1] = "eee";
   *     L1MagicPlugin aObject =
   *     (L1MagicPlugin) getObject("l1j.william.L1MagicPluginSample",obj);
   * </pre>
   * @param sClassName dynamic class name
   * @param objs Object array used as parameter
   * @return Object u want to new dynamically
   */
  public static Object newInstance(String sClassName ,Object[] objs) throws Exception{
      Class aClass = Class.forName(sClassName);
      Class aParameterClasses[] ;

      // Get all Constructors
      java.lang.reflect.Constructor[] aClassConstructors = aClass.getConstructors();
      for(int i=0;i<aClassConstructors.length;i++){
        aParameterClasses = aClassConstructors[i].getParameterTypes();
        if( aParameterClasses.length == 0 && objs==null )
        	return aClassConstructors[i].newInstance(new Object[]{});
        else if( objs!=null && aParameterClasses.length == objs.length ){
          Object aReturnObject = null;
          try{

            aReturnObject = aClassConstructors[i].newInstance( objs);
            return aReturnObject;
          }catch(Exception ee){
          }

        }

      }
      return null;
  }
}