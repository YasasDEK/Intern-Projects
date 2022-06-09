USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

CREATE TABLE module_tag
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    module_id		  INT,
    version_no		  VARCHAR(255),
    branch_name		  VARCHAR(255),	
    created_date      DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;


CREATE TABLE dev_release_note
(
    id                		INT AUTO_INCREMENT PRIMARY KEY,
    tag_id					INT,
    customer_id				INT,
    release_by				VARCHAR(255),
    release_date			DATE,
    apply_on_top_of_version VARCHAR(255),
    dependant_version		JSON,
    status					VARCHAR(255),
    feature_summary 		TEXT,
    build_instructions		TEXT,
    fixed_bugs				TEXT,
    known_issues			TEXT,
    deployment_details		TEXT,
    areas_to_be_tested		TEXT,
    reference_docs			TEXT,
    created_date			DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time		DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (tag_id) REFERENCES module_tag(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;