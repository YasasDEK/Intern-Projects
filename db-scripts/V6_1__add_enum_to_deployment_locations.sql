USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

ALTER TABLE deployment_location MODIFY COLUMN location_code enum('da','testbed','staging','production');

Alter table module_deployments add column status enum("Planned","Active","Rejected","Retierd");