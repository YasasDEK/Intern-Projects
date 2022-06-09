USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

ALTER TABLE customer_module_deployment ADD id INT AUTO_INCREMENT PRIMARY KEY;

ALTER TABLE module_dependancy RENAME TO module_dependency;

ALTER TABLE module_dependency ADD id INT AUTO_INCREMENT PRIMARY KEY;
