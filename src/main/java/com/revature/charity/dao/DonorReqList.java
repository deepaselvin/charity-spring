package com.revature.charity.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.charity.exception.DBException;
import com.revature.charity.model.AdminActivity;
import com.revature.charity.util.ConnectionProperties;

public class DonorReqList {
	public List<AdminActivity> findAllRequest() throws DBException {
        Connection con = null;
		con = ConnectionProperties.getconnection();

        PreparedStatement pst = null;
        ResultSet rs = null;
        AdminActivity da = null;
        List<AdminActivity> list = null;
        try {
             con = ConnectionProperties.getconnection();
            String sql = "SELECT id,request_type,(fund_needed -\r\n" +
                    "(SELECT IFNULL(SUM(amount_donated),0) FROM user_activity WHERE request_id  = fr.id))\r\n" +
                    " as fund_needed FROM category fr WHERE \r\n" +
                    
                    " fund_needed > (SELECT IFNULL(SUM(amount_funded),0) as fund_needed FROM activity WHERE request_id  = fr.id)";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                da = toRow1(rs);
                list.add(da);
            }
        } catch (SQLException e) {
            throw new DBException("Unable to list fund", e);
        } finally {
        	ConnectionProperties.close(con, pst, rs);
        }
        return list;
    }
 private AdminActivity toRow1(ResultSet rs) throws DBException {
	 AdminActivity da = null;
        try {
            int id = rs.getInt("id");
            String request = rs.getString("request_type");
            double fund = rs.getDouble("Fund_needed");
            da = new AdminActivity();
            da.setId(id);
            da.setRequest(request);
            da.setFund(fund);
        } catch (SQLException e) {
            throw new DBException("Unable to display", e);
        }
        return da;
    }
}

