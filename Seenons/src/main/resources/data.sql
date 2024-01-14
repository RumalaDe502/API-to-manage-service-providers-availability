INSERT INTO service_provider (id, name, address)
values (1, 'Unwasted', 'Stationplein, 1, 1012 AB Amsterdam'),
       (2, 'Bluecollection', 'Prins Hendrikkade, 1, 1012 JD Amsterdam');

INSERT INTO service_provider_coverage
(id, service_provider_id, postal_code_start, postal_code_end)
VALUES
    (1, 1, 1010, 1020),
    (2, 1, 1010, 1020),
    (3, 2, 1000, 9999);

INSERT INTO week_days (weekday, service_provider_cov_id) VALUES
('Monday', 1),
('Tuesday', 1),
('Thursday', 1),
('Monday', 2),
('Wednesday', 2),
('Friday', 2),
('Monday',3),
('Tuesday',3),
('Wednesday', 3),
('Thursday', 3),
('Friday', 3);

INSERT INTO waste_Stream (id, label, category, service_provider_cov_id)
values (1, 'paper','recyclable', 1),
       (2, 'metal', 'recyclable', 2),
       (3, 'glass', 'recyclable', 3);



INSERT INTO register_stream_pickup (id, waste_stream_id, service_provider_id, pickup_date)
values
    (1, 1, 1,'2023-10-02'),
    (2, 2, 1, '2023-10-04'),
    (3, 3, 2, '2023-10-04'),
    (4, 2, 2, '2023-10-06');

INSERT INTO customer (id, name, address,postal_code, reg_stream_pickup_id) values
                            ( 1, 'Seenons', 'Danzigerkade 5B, 1013 AP Amsterdam', '1015', 1 ),
                             (2, 'Mega City One','Prins Hendrikkade, 100, 1012 JD Amsterdam','2000', 2),
                             (3, 'Mega City One','Prins Hendrikkade, 100, 1012 JD Amsterdam','2000', 3),
                            (4, 'Seenons','Danzigerkade 5B, 1013 AP Amsterdam','1015', 4);

