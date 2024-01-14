

CREATE TABLE service_provider (
                                 id INT AUTO_INCREMENT PRIMARY KEY,
                                 name VARCHAR(255) NOT NULL,
                                 address VARCHAR(255) NOT NULL
);


CREATE TABLE service_provider_coverage (
                                         id INT PRIMARY KEY AUTO_INCREMENT,
                                         service_provider_id INT,
                                         postal_code_start INT,
                                         postal_code_end INT,
                                         FOREIGN KEY (service_provider_id) REFERENCES service_provider(id)
);


CREATE TABLE waste_stream (id INT  AUTO_INCREMENT PRIMARY KEY,
                          label VARCHAR(255) NOT NULL,
                          category VARCHAR(50) NOT NULL,
                          service_provider_cov_id INT,
                          FOREIGN KEY (service_provider_cov_id) REFERENCES service_provider_coverage(id)
);



CREATE TABLE register_stream_pickup (
                                      id INT PRIMARY KEY AUTO_INCREMENT,
                                      waste_stream_id INT NOT NULL,
                                      service_provider_id INT NOT NULL,
                                      pickup_date DATE NOT NULL
);


CREATE TABLE customer (
                          id INT auto_increment PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          address VARCHAR(255) NOT NULL,
                          postal_code VARCHAR(20) NOT NULL,
                        reg_stream_pickup_id INT,
                        FOREIGN KEY  (reg_stream_pickup_id) REFERENCES register_stream_pickup(id)
);


CREATE TABLE week_days (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          weekday VARCHAR(255),
			              service_provider_cov_id INT,
                          FOREIGN KEY (service_provider_cov_id) REFERENCES service_provider_coverage(id)
);






