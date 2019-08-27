package com.b5wang.cloud.cassandrajavaconnector;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class ProgrammaticConnector {

	private final static Logger LOG = LoggerFactory.getLogger(ProgrammaticConnector.class);
	
	public static void main(String[] args) {
		CqlSession session = CqlSession.builder()
				.addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
			    .withLocalDatacenter("datacenter1")
			    .withKeyspace("demo")
			    .build();
		
		ResultSet rs = session.execute("SELECT username,password,display_name,added_date FROM users");
		Row row = rs.one();
		
		LOG.info("Result: {}, {}, {}, {}", row.getString("username"),row.getString("password"),row.getString("display_name"),row.getLocalDate("added_date"));
		
		session.close();
	}
	
}