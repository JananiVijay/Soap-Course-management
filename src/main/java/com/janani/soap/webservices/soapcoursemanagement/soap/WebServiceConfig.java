package com.janani.soap.webservices.soapcoursemanagement.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

//Enable Spring Web Services
@EnableWs
//Spring Configuration
@Configuration
public class WebServiceConfig {
	//MessageDispatcherServlet
		//ApplicationContext
	//url -> /ws/*
	
	@Bean
	ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		//map the messageDispatcherServlet to the url -> "/ws/*"
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}
	
	// /ws/courses.wsdl
		
	//course-details.xsd
	
	@Bean(name="courses")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("CoursePort");   //PortType - CoursePort
		definition.setTargetNamespace("http://janani.com/courses");    //Namespace - http://janani.com/courses
		definition.setLocationUri("/ws");   // /ws
		definition.setSchema(coursesSchema);  //schema
		return definition;
	}
	
	@Bean
	XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
	}
}
