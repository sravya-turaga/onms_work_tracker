package com.tcs.onms.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tcs.onms.bean.Onms;
import com.tcs.onms.util.DbTransactions;
public class OnmsDao {


	public ArrayList<Onms> getAllUsers() {
		
			ArrayList<Onms> empList=new ArrayList<Onms>();
			try {
				Connection cn=DbTransactions.createConnection();
				PreparedStatement ps=cn.prepareStatement("select * from Users");
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					Onms omns=new Onms(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getDate(8));
					empList.add(omns);
									}
				DbTransactions.closeResultSet(rs);
				DbTransactions.closePreparedStatement(ps);
				DbTransactions.closeConnection();
			}
			catch(ClassNotFoundException e) 
			{
				System.out.println(e.getMessage());
			}
			catch(SQLException e) 
			{
				System.out.println(e.getMessage());
			}
			return empList;
		
	}
	public boolean addUsers(Onms user) {
		boolean result=false;
		try {
			Connection cn=DbTransactions.createConnection();
			PreparedStatement ps=cn.prepareStatement("INSERT INTO Users(EmpId,UserName,TempPwd,RoleId,EmailId,MobNo,TeamLeadId) VALUES(?,?,?,?,?,?,?)");
			ps.setLong(1,user.getEmployeeId());
			ps.setString(2,user.getUserName());
			ps.setString(3,user.getPassword());
			ps.setLong(4,user.getRoleId());
			ps.setString(5,user.getEmailId());
			ps.setString(6,user.getMobileNumber());
			ps.setString(7,user.getTeamLeadName());
			int t=ps.executeUpdate();
			if(t>0) {
				result=true;
			}
			DbTransactions.closePreparedStatement(ps);
			DbTransactions.closeConnection();
		} catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

}
