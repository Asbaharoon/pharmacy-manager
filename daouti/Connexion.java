package daouti;



import java.sql.DriverManager;
import java.util.HashMap;

import com.sun.jdi.connect.spi.Connection;

import Tools.FileUtil;

public class Connexion {
public static java.sql.Connection getconnection() throws Exception{
	HashMap<String,String>params=FileUtil.LireProperties();
	Class.forName(params.get("pilote"));
	java.sql.Connection c= DriverManager.getConnection(params.get("url"),params.get("utilisateur"),params.get("motepass"));
	 return c;
	
}
}
