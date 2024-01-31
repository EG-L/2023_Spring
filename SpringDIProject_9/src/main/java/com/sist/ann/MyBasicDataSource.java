package com.sist.ann;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;
@Component("ds")
//<bean id="ds"
public class MyBasicDataSource extends BasicDataSource{
	
	public MyBasicDataSource() {
		setDriverClassName("oracle.jdbc.driver.OracleDriver");
		setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		setUsername("hr");
		setPassword("happy");
		setMaxActive(10);
		setMaxIdle(10);
		setMaxWait(-1);
	}
}
