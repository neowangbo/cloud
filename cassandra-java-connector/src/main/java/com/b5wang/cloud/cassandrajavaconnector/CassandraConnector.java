package com.b5wang.cloud.cassandrajavaconnector;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class CassandraConnector {

	public static void main(String[] args) {
		
		try (CqlSession session = CqlSession.builder().build()) { // 1. config in application.conf
			
			System.out.println("1---");
			
			ResultSet rs = session.execute("SELECT username,password,display_name,added_date FROM demo.users");
			
			System.out.println("2---");
			
			Row row = rs.one();
			
			System.out.println("username: " + row.getString("username"));
		}
	}

}
