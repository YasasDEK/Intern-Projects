USE release_note_db;

-- Enable LOAD FILE LOCAL support
SET GLOBAL local_infile = 'ON';

CREATE TABLE module
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(255),
    git_location      VARCHAR(500),
    created_date      DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;

CREATE TABLE deployment_location
(
    customer_id       INT,
    location_code     VARCHAR(255) PRIMARY KEY,
    created_date      DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;

CREATE TABLE customer_module_deployment
(
    customer_id       INT,
    module_id         INT,
    created_date      DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (module_id) REFERENCES module(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;

CREATE TABLE module_dependancy
(
    module_id         INT,
    dependant_module_id  INT,
    created_date      DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_updated_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (module_id) REFERENCES module(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = `UTF8MB4`;