package com.sist.ann;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
 * <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
	/>
 * */
@Component("ssf")
//지정하지 않는 경우 => mySqlSessionFactoryBean => 맨 앞자가 소문자로 바뀜
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean{
	@Autowired
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
	}
}
