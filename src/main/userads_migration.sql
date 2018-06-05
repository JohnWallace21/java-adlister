USE adlister_db;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(150) NOT NULL,
  password VARCHAR(150) NOT NULL,
  release_date YEAR(4),
  sales FLOAT(5,2) ,
  genre VARCHAR(50),
  PRIMARY KEY (id)
);
