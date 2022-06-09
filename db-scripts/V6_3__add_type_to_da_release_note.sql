USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

Alter table da_release_note add column type VARCHAR(255);

Alter table da_release_note add column comment TEXT;