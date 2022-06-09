USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

CREATE TABLE module_deployments
(
    id                			INT AUTO_INCREMENT PRIMARY KEY,
    customer_id					INT,
    deployment_location			VARCHAR(255),
    module_id					INT,
    version_no 					VARCHAR(255),
    applied_date				DATE,
    applied_by					VARCHAR(255),
    comments					TEXT,
    previous_mod_deployment_id	INT,			
    created_date				DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time			DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;