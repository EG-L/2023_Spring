package com.sist.di;
import java.util.*;
import java.sql.*;
public class DeptDAO {
	private CommonsDAO comm;
	private Connection conn;
	private PreparedStatement ps;

	public void setComm(CommonsDAO comm) {
		this.comm = comm;
	}
	public void init() {
		System.out.println("========= 부서정보 =========");
	}
	
	public List<DeptVO> deptListData(){
		List<DeptVO> list = new ArrayList<DeptVO>();
		try {
			conn = comm.getConnection();
			String sql = "SELECT deptno,dname,loc "
					+ "FROM dept";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DeptVO vo = new DeptVO();
				vo.setDeptno(rs.getInt(1));
				vo.setDname(rs.getString(2));
				vo.setLoc(rs.getString(3));
				
				list.add(vo);
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			comm.disConnection(conn,ps);
		}
		return list;
	}
}
