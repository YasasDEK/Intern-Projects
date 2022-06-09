USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

CREATE TABLE release_note
(
    id                		INT,
    module_id				INT,
    version_no				VARCHAR(255),
    release_by				VARCHAR(255),
    branch					VARCHAR(255),
    release_date			DATE,
    apply_on_top_of_version VARCHAR(255),
    dependant_version		VARCHAR(255),
    status					VARCHAR(255),
    build_instructions		TEXT,
    fixed_bugs				TEXT,
    known_issues			TEXT,
    deployment_details		TEXT,
    areas_to_be_tested		TEXT,
    reference_docs			TEXT,
    created_date			DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time		DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(module_id,version_no)
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;





