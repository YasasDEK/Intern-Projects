USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

INSERT INTO customer (name) VALUES ("Robi"),("mSpace"),("ideamart");