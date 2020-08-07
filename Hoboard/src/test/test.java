package test;

import db.DBConnection;

public class test {
	public static void main(String[] args) {
		System.out.println("DB Connection Test");
		DBConnection.initConnection();
	}
}
