package com.b5wang.cloud.cassandrajavaconnector;

import java.net.InetSocketAddress;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class ProgrammaticConnector {

	public static void main(String[] args) {
		CqlSession session = CqlSession.builder()
				.addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
			    .withLocalDatacenter("datacenter1")
			    .withKeyspace("demo")
			    .build();
		
		System.out.println("1---");
		
		ResultSet rs = session.execute("SELECT username,password,display_name,added_date FROM users");
		
		System.out.println("2---");
		
		Row row = rs.one();
		
		System.out.println("username: " + row.getString("username"));
	}
	
}