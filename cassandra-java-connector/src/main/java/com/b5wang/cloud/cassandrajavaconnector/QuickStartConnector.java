package com.b5wang.cloud.cassandrajavaconnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class QuickStartConnector {

	private final static Logger LOG = LoggerFactory.getLogger(QuickStartConnector.class);

	public static void main(String[] args) {
		try (CqlSession session = CqlSession.builder().build()) {
			ResultSet rs = session.execute("SELECT username,password,display_name,added_date FROM demo.users");
			Row row = rs.one();
			LOG.info("Result: {}, {}, {}, {}", row.getString("username"), row.getString("password"),
					row.getString("display_name"), row.getLocalDate("added_date"));
		}
	}
}
