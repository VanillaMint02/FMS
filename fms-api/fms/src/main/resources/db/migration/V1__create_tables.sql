

CREATE TABLE IF NOT EXISTS users(
id UUID NOT NULL PRIMARY KEY,
username VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS files(
id UUID NOT NULL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
path VARCHAR(255) NOT NULL,
type VARCHAR(255)NOT NULL,
user_id UUID NOT NULL,
CONSTRAINT user_id FOREIGN KEY(user_id) REFERENCES users(id)
);
