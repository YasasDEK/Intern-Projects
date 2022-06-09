USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

CREATE TABLE customer
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(255),
    created_date      DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;

