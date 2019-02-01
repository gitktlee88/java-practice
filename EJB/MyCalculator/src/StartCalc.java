
import java.util.*;
import javax.naming.*;
//import com.logic.*;
import com.mylogic.Addition;
import com.mylogic.AdditionRemote;

import javax.ejb.*;
 
public class StartCalc {
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
 
			System.out.println("Hello From Java!");
		    Properties props = new Properties();
	        props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
	        props.put("java.naming.factory.initial","org.jboss.naming.remote.client.InitialContextFactory");
	        props.put("java.naming.provider.url","remote://127.0.0.1:4447");
	        props.put("jboss.naming.client.ejb.context","true");
	        props.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT","false");
	        InitialContext context = new InitialContext(props);
 
	        String appName = "";        	 
	        String moduleName = "MyAdditionEJB";
	        String distinctName = "";        	 
	        String beanName = Addition.class.getSimpleName();        	 
	        String interfaceName = AdditionRemote.class.getName();
	        String name = "ejb:" + appName + "/" + moduleName + "/" +  distinctName    + "/" + beanName + "!" + interfaceName;
	        System.out.println(name);
	        AdditionRemote bean = (AdditionRemote)context.lookup(name);
	        int result=bean.add(4,6);
	        System.out.println("Result computed by EJB is :"+result); 	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}