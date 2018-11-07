package com.sample.vue.common.framework;

import java.io.File;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class DBCrawler {
	
	Connection connection = null;
	Statement st = null;
	ResultSet rs = null;
	
	
	String absoluteJavaPath = "D:/dev/workspace/SpringMVC-Vue/src/main/java";
	String absoluteResourcePath = "D:/dev/workspace/SpringMVC-Vue/src/main/resource";
	String packagePath = "/com/sample/vue";
	
	String model = "/model";
	String mapper = "/mapper";
	String repository = "/repository";
	
	VelocityEngine velocityEngine = null;
	
	
	public DBCrawler() {
		
		initJDBC();
		List<String> list = getTableList();
		
		velocityEngine  = new VelocityEngine();
		
		for(String s : list) {
			boolean existJavaEntity = checkFileExist(absoluteJavaPath + packagePath + "/" + s + model + "/" + s + "Entity.java");
			boolean existJavaMapper = checkFileExist(absoluteJavaPath + packagePath + "/" + s + repository + "/" + s + "Mapper.java");
			boolean existJavaRepository = checkFileExist(absoluteJavaPath + packagePath + "/" + s + repository + "/" + s + "Repository.java");
			
			boolean existXMLEntity = checkFileExist(absoluteResourcePath + repository + "/" + s + "/tb" + s +  "Entity.xml");
			boolean existXMLMapper = checkFileExist(absoluteResourcePath + mapper + "/" + s + "/tb" + s + "Mapper.xml");
			boolean existXMLRepository = checkFileExist(absoluteResourcePath + repository + "/" + s + "/tb" + s  + "Repository.xml");
			

			System.out.println(existJavaEntity + " : " + s);
			System.out.println(existJavaMapper + " : " + s);
			System.out.println(existJavaRepository + " : " + s);
			
			System.out.println(existXMLEntity + " : " + s);
			System.out.println(existXMLMapper + " : " + s);
			System.out.println(existXMLRepository + " : " + s);
			
			
			List<HashMap<String, String>> info = getTableDetailInfo("tb_"+s);
			createJavaEntityFile(s, info);
			
		}
	}
	
	public void createJavaEntityFile(String obj, List<HashMap<String, String>> info) {
		Template template = velocityEngine.getTemplate("/src/main/webapp/resources/templates/JavaEntity.vm"); 
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("obj", obj); 
		velocityContext.put("objCapital", Character.toUpperCase(obj.charAt(0)) + obj.substring(1)); 
		velocityContext.put("info", info); 
		
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);

		System.out.println(stringWriter.toString());
	}
	
	public boolean checkFileExist(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public void initJDBC() {
		try {
		    Class.forName("com.mysql.jdbc.Driver");	
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "1234");
		}
		catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public void closeDatabase() {
		try
		{
			//if( connection != null ) connection.close();
			
			if( st != null ) st.close();
			
			if( rs != null ) rs.close();
			
		}
		catch (SQLException e)
		{
			System.out.println("[´Ý±â ¿À·ù]\n" +  e.getStackTrace());
		}
	}
	
	public List<String> getTableList(){
		
		List<String> list = new ArrayList();
		
		try {
			st = connection.createStatement();

            String sql;
            sql = "SHOW TABLES;";

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String tbName = rs.getString(1);
                list.add(tbName.replaceAll("tb_", ""));
            }
            closeDatabase();
		}
		catch (Exception e) {
			
		}
		return list;
	}
	
	public List<HashMap<String, String>> getTableDetailInfo(String tbName){
		
		List<HashMap<String, String>> list = new ArrayList();
		
		try {
			st = connection.createStatement();

            String sql;
            sql = "EXPLAIN " + tbName + ";";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String col = getJavaName(rs.getString(1));
                String type = getJavaType(rs.getString(2));
                String pri = rs.getString(4);
                
                HashMap<String, String> map = new HashMap<>();
                
                map.put("col", col);
                map.put("type", type);
                map.put("pri", pri);
                
                list.add(map);
            }
            closeDatabase();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String getJavaType(String type) {
		
		if(type.contains("int")) {
			return "int";
		}
		else if(type.contains("date")) {
			return "Timestamp";
		}
		
		return "String";
	}
	
	public String getJavaName(String col) {
		
		col = col.toLowerCase();
		
		String[] split = col.split("_");
		String result = split[0];

		for(int i=1; i<split.length; i++) {
			result += Character.toUpperCase(split[i].charAt(0)) + split[i].substring(1);
		}
		
		return result;
	}
	
	public static void main(String[] args) {

		new DBCrawler();
	}
}
