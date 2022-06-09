USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

ALTER TABLE release_note ADD feature_summary TEXT;