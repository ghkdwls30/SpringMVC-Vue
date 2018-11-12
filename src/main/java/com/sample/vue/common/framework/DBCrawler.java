package com.sample.vue.common.framework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
			
			List<HashMap<String, String>> info = getTableDetailInfo("tb_" + s);
			
			createFile(absoluteJavaPath + packagePath + "/" + s + model + "/" +  Character.toUpperCase(s.charAt(0)) + s.substring(1) + "Entity.java", createJavaEntityFile(s, info), true);
			createFile(absoluteJavaPath + packagePath + "/" + s + repository + "/" +  Character.toUpperCase(s.charAt(0)) + s.substring(1) + "Mapper.java", createJavaMapperFile(s, info), false);
			createFile(absoluteJavaPath + packagePath + "/" + s + repository + "/" +  Character.toUpperCase(s.charAt(0)) + s.substring(1) + "Repository.java", createJavaRepositoryFile(s, info), false);
			createFile(absoluteResourcePath + repository + "/" + s + "/tb" +  Character.toUpperCase(s.charAt(0)) + s.substring(1) + "Entity.xml", createXMLEntityFile(s, info), true);
			createFile(absoluteResourcePath + mapper + "/" + s + "/tb" +  Character.toUpperCase(s.charAt(0)) + s.substring(1) + "Mapper.xml", createXMLMapperFile(s, info), false);
			createFile(absoluteResourcePath + repository + "/" + s + "/tb" +  Character.toUpperCase(s.charAt(0)) + s.substring(1) + "Repository.xml", createXMLRepositoryFile(s, info), false);
		}
	}
	
	public void createFile(String path, String contets, boolean duplicatedDelete) {
		File file = new File(path);
		
		if(file.exists() && !duplicatedDelete) return;
		
		try {
			file.getParentFile().mkdirs();
			BufferedWriter out = new BufferedWriter(new FileWriter(path));
			out.write(contets); 
			out.newLine();
			out.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String createJavaEntityFile(String obj, List<HashMap<String, String>> info) {
		Template template = velocityEngine.getTemplate("/src/main/webapp/resources/templates/JavaEntity.vm"); 
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("obj", obj); 
		velocityContext.put("objCapital", Character.toUpperCase(obj.charAt(0)) + obj.substring(1)); 
		velocityContext.put("info", info); 
		
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);

		return stringWriter.toString();
	}
	
	public String createJavaMapperFile(String obj, List<HashMap<String, String>> info) {
		Template template = velocityEngine.getTemplate("/src/main/webapp/resources/templates/JavaMapper.vm"); 
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("obj", obj); 
		velocityContext.put("objCapital", Character.toUpperCase(obj.charAt(0)) + obj.substring(1)); 
		
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);

		return stringWriter.toString();
	}
	
	public String createJavaRepositoryFile(String obj, List<HashMap<String, String>> info) {
		Template template = velocityEngine.getTemplate("/src/main/webapp/resources/templates/JavaRepository.vm"); 
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("obj", obj); 
		velocityContext.put("objCapital", Character.toUpperCase(obj.charAt(0)) + obj.substring(1)); 
		
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);

		return stringWriter.toString();
	}
	
	public String createXMLEntityFile(String obj, List<HashMap<String, String>> info) {
		Template template = velocityEngine.getTemplate("/src/main/webapp/resources/templates/XMLEntity.vm"); 
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("obj", obj); 
		velocityContext.put("objCapital", Character.toUpperCase(obj.charAt(0)) + obj.substring(1)); 
		velocityContext.put("info", info); 
		
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);

		return stringWriter.toString();
	}
	
	public String createXMLMapperFile(String obj, List<HashMap<String, String>> info) {
		Template template = velocityEngine.getTemplate("/src/main/webapp/resources/templates/XMLMapper.vm"); 
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("obj", obj); 
		velocityContext.put("objCapital", Character.toUpperCase(obj.charAt(0)) + obj.substring(1)); 
		velocityContext.put("info", info); 
		
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);

		return stringWriter.toString();
	}
	
	public String createXMLRepositoryFile(String obj, List<HashMap<String, String>> info) {
		Template template = velocityEngine.getTemplate("/src/main/webapp/resources/templates/XMLRepository.vm"); 
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("obj", obj); 
		velocityContext.put("objCapital", Character.toUpperCase(obj.charAt(0)) + obj.substring(1)); 
		velocityContext.put("info", info); 
		
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);

		return stringWriter.toString();
	}
	
	public boolean checkFileExist(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public void initJDBC() {
		try {
		    Class.forName("com.mysql.jdbc.Driver");	
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=UTC", "root", "1234");
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
