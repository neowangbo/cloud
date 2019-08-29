package com.b5wang.cloud.cassandrajavaconnector;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

/*
 * 1. application.conf inherits from reference.conf, you only need to redeclare what you override:
 * 
 * */
public class ConfigConnector {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigConnector.class);
	
	private static final File CONF = new File("/Users/wangbo/DevHome/projects/GitHub/cloud/cassandra-java-connector/conf-examples/simple-application.conf");
	
	public static void main(String[] args) {
		
		try (CqlSession session = getCqlSession();) {

			ResultSet rs = session.execute("SELECT username,password,display_name,added_date FROM users");
			Row row = rs.one();

			LOG.info("Result: {}, {}, {}, {}", row.getString("username"), row.getString("password"),
					row.getString("display_name"), row.getLocalDate("added_date"));
		}
		
	}
	
	public static CqlSession getCqlSession() {
		return CqlSession.builder().withConfigLoader(DriverConfigLoader.fromFile(CONF)).build();
	}

}
