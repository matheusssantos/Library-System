
DROP TABLE books IF EXISTS;
CREATE TABLE books(
  id INT AUTO_INCREMENT,
  title VARCHAR(255),
  author VARCHAR(255),
  publication_year INT,
  PRIMARY KEY(id)
); 