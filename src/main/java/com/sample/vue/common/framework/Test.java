package com.sample.vue.common.framework;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;

public class Test {
	
//	@Autowired 
//	private VelocityEngine velocityEngine; 
//
//	public void setVelocityEngine(VelocityEngine velocityEngine) {
//		this.velocityEngine = velocityEngine; 
//	}

	public Test(){
		
		VelocityEngine velocityEngine  = new VelocityEngine();
		
		Template template = velocityEngine.getTemplate("/src/main/webapp/resources/templates/test.vm"); 
		VelocityContext velocityContext = new VelocityContext(); 
		velocityContext.put("firstName", "Yashwant"); 
		velocityContext.put("lastName", "Chavan"); 
		velocityContext.put("location", "Pune"); 
		
		StringWriter stringWriter = new StringWriter(); 
		template.merge(velocityContext, stringWriter);

		System.out.println(stringWriter.toString());
		
	}


		
		
	public static void main(String[] args) {
		new Test();
	}
}
