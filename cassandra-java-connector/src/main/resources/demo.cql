CREATE KEYSPACE demo WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

USER KEYSPACES;

CREATE TABLE users (
username     TEXT,
password     TEXT,
display_name TEXT,
added_date   DATE,
PRIMARY KEY((username,password),display_name,added_date)
);

INSERT INTO users(username,password,display_name,added_date) VALUES('Jack','123456','Jack Wang','1992-6-19');
INSERT INTO users(username,password,display_name,added_date) VALUES('Tom','123456','Tom Cruises','1992-6-19');

SELECT username,password,display_name,added_date FROM users WHERE username=? AND password=?;

UPDATE users SET password=? WHERE username=?;

DELETE users WHERE username=?;