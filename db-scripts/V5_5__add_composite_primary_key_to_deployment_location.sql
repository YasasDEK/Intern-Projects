USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

ALTER TABLE deployment_location DROP PRIMARY KEY, ADD PRIMARY KEY(customer_id, location_code);
