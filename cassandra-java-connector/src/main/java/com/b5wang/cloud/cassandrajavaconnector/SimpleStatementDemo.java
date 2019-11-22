package com.b5wang.cloud.cassandrajavaconnector;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;

public class SimpleStatementDemo {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleStatementDemo.class);
	
	/*
	 * Present CRUD with SimpleStatement
	 * */
	public static void main(String[] args) {
		String username = "user_1";
		String password = "123456";
		String displayName = "Official user";
		String addedDate = "1987-1-5";
		
		insertUser(username, password, displayName, addedDate);
		
		//getUser(username, password);
		
		//String newDisplayName = "Unformal user name";
		//updateDisplayName(username, newDisplayName);
		
		//deleteUser(username, password);
		
		//selectAll();
	}
	
	
	private static void insertUser(String username, String password, String displayName, String addedDate) {
		
		try (CqlSession session = ConfigConnector.getCqlSession();) {
			
			SimpleStatementBuilder statementBuilder = SimpleStatement.builder("INSERT INTO users(username,password,display_name,added_date) VALUES(?,?,?,?)");
			statementBuilder = statementBuilder.addPositionalValue(username);
			statementBuilder = statementBuilder.addPositionalValue(password);
			statementBuilder = statementBuilder.addPositionalValue(displayName);
			statementBuilder = statementBuilder.addPositionalValue(LocalDate.of(1987, 1, 5));
			
			SimpleStatement statement = statementBuilder.build();
			
			ResultSet rs = session.execute(statement);
			for(Row row : rs) {
				LOG.info("Insert a user: {}, {}, {}, {}", row.getObject("username"), row.getObject("password"), row.getObject("display_name"), row.getObject("added_date"));
			}
		}
	}
	
	private static void getUser(String username, String password) {
		
		try (CqlSession session = ConfigConnector.getCqlSession();) {
			
			SimpleStatementBuilder statementBuilder = SimpleStatement.builder("SELECT username,password,display_name,added_date FROM users WHERE username=? AND password=?");
			statementBuilder = statementBuilder.addPositionalValue(username);
			statementBuilder = statementBuilder.addPositionalValue(password);
			
			SimpleStatement statement = statementBuilder.build();
			
			ResultSet rs = session.execute(statement);
			for(Row row : rs) {
				LOG.info("Select a user: {}, {}, {}, {}", row.getObject("username"), row.getObject("password"), row.getObject("display_name"), row.getObject("added_date"));
			}
		}
	}
	
	private static void selectAll() {
		
		try (CqlSession session = ConfigConnector.getCqlSession();) {
			
			SimpleStatementBuilder statementBuilder = SimpleStatement.builder("SELECT username,password,display_name,added_date FROM users");
			SimpleStatement statement = statementBuilder.build();
			
			ResultSet rs = session.execute(statement);
			for(Row row : rs) {
				LOG.info("Select a user: {}, {}, {}, {}", row.getObject("username"), row.getObject("password"), row.getObject("display_name"), row.getObject("added_date"));
			}
		}
	}
	
	/**
	 * Any of primary keys is not able to be changed!!!
	 * */
	private static void updateDisplayName(String username, String displayName) {
		
		try (CqlSession session = ConfigConnector.getCqlSession();) {
			
			SimpleStatementBuilder statementBuilder = SimpleStatement.builder("UPDATE users SET display_name=:displayName WHERE username=:username");
			statementBuilder = statementBuilder.addNamedValue("username", username);
			statementBuilder = statementBuilder.addNamedValue("displayName", displayName);
			
			SimpleStatement statement = statementBuilder.build();
			
			ResultSet rs = session.execute(statement);
			for(Row row : rs) {
				LOG.info("Update a user: {}, {}, {}, {}", row.getObject("username"), row.getObject("password"), row.getObject("display_name"), row.getObject("added_date"));
			}
		}
	}
	
	/**
	 * must have partition key
	 * */
	private static void deleteUser(String username, String password) {
		try (CqlSession session = ConfigConnector.getCqlSession();) {
			
			SimpleStatementBuilder statementBuilder = SimpleStatement.builder("DELETE FROM users WHERE username=? AND password=?");
			statementBuilder = statementBuilder.addPositionalValue(username);
			statementBuilder = statementBuilder.addPositionalValue(password);
			
			SimpleStatement statement = statementBuilder.build();
			
			ResultSet rs = session.execute(statement);
			for(Row row : rs) {
				LOG.info("Delete a user: {}, {}, {}, {}", row.getObject("username"), row.getObject("password"), row.getObject("display_name"), row.getObject("added_date"));
			}
		}
	}
	
}
