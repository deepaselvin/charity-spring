package com.revature.charity.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.charity.exception.DBException;
import com.revature.charity.util.Logger;

public class ConnectionProperties {


	public static Connection getconnection() {

		String driverClassName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://trainingdb.ck1ayq0lncmp.ap-south-1.rds.amazonaws.com:3306/pradeepa1_db";
		
		String username = "pradeepa";
		String password = "pradeepa";
		Connection con = null;

		try {
			Class.forName(driverClassName);
			con = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			Logger.error(e.getMessage());
		} catch (SQLException e) {

			Logger.error(e.getMessage());
		}
		return con;
	}
	public static void main(String[] args) throws DBException {
        getconnection();
    }
    public static void close(Connection con, PreparedStatement pst) throws DBException {
        try {
            if (pst != null)
                pst.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
        	Logger.error(e.getMessage());
            throw new DBException("Unable to close the connection");
        }
    }
    public static void close(Connection con, PreparedStatement pst, ResultSet result) throws DBException {
        try {
            if (result != null)
            	result.close();
            if (pst != null)
                pst.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
        	Logger.error(e.getMessage());
            throw new DBException("Unable to close the connection");
        }
    }
    public static void close(Connection con, Scanner scn) {
        try {
            if (scn != null)
                scn.close();
            if (con != null)
                con.close();
        } catch (SQLException e) {
        	Logger.error(e.getMessage());
        }

    }



	
}
