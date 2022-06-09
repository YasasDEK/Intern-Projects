USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

ALTER TABLE release_note CHANGE COLUMN dependant_version dependant_version JSON NULL DEFAULT NULL ;