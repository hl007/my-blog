create table blog (
	 id INT(20) auto_increment PRIMARY KEY,
	 name VARCHAR(100) NOT NULL,
	 title VARCHAR(100) NOT NULL,
	 summary VARCHAR(1000) NOT NULL,
	 content MEDIUMTEXT NOT NULL,
   tag1 VARCHAR(100) NOT NULL,
   tag2 VARCHAR(100),
   tag3 VARCHAR(100),
   tag4 VARCHAR(100),
   date VARCHAR(100) NOT NULL,
   pathname VARCHAR(100) NOT NULL
);
