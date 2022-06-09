USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

CREATE TABLE da_release_note
(
    id                	INT AUTO_INCREMENT PRIMARY KEY,
    tag_id            	INT,
    customer_id		  	INT,
    artifact_location 	VARCHAR(500),
    mdsum			  	TEXT,
    tested_areas	  	TEXT,
    new_features      	TEXT,
    found_bugs        	TEXT,
    fixed_bugs        	TEXT,
    know_bugs         	TEXT,
    limitations       	TEXT,
    modules_released  	TEXT,
    prerequisits	  	TEXT,
    deploy_instructions	TEXT,
    patch_instructions	TEXT,
    areas_to_be_tested	TEXT,
    released_date		DATE,
    released_by			VARCHAR(255),
    tested_by			VARCHAR(255),
    test_cases			TEXT,
    planned_start		DATE,
    planned_end			DATE,
    actual_start		DATE,
    actual_end			DATE,
    created_date      	DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time 	DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;

Alter table dev_release_note add column type VARCHAR(255);  