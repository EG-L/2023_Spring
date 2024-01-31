package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
/*
 *  <resultMap>
 *  	<result property="dvo.dname" column="dname">
 *  </resultMap>
 *  
 *  dvo.setDname(rs.getString("dname"))
 * */
import org.apache.ibatis.annotations.Select;
public interface EmpMapper {
	@Results({
		@Result(property = "dvo.dname",column = "dname"),
		@Result(property = "dvo.loc",column = "loc")
	})
	@Select("SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal,dname,loc FROM "
			+ "emp JOIN dept ON emp.deptno = dept.deptno")
	public List<EmpVO> empDeptJoinData();
}
