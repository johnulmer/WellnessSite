package com.lmig.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

//@Configuration
//@PropertySource("classpath:application.properties")
public class WellnessSiteDB {
	
	public WellnessSiteDB() {

	}
	
//	@Autowired
//	private Environment env;
//
//	private String dburl = env.getProperty("spring.datasource.url");
//	private String dbpwd = env.getProperty("spring.datasource.password");
//	private String dbuser = env.getProperty("spring.datasource.username");
//	
//	@Value("${spring.datasource.url}")
//	private String dburl;
//	@Value("${spring.datasource.password}")
//	private String dbpwd;
//	@Value("${spring.datasource.username}")
//	private String dbuser;	
//	
	private String dburl = "jdbc:postgresql://localhost/wellnessDB";
	private String dbpwd = "gfinsgtgl70";
	private String dbuser = "postgres";
	
	public String getDburl() {
		return dburl;
	}
	public String getDbpwd() {
		return dbpwd;
	}
	public String getDbuser() {
		return dbuser;
	}
}
