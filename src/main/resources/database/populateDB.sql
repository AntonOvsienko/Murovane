 INSERT INTO list_of_cities VALUES
 (1, 'Dnepr'),
 (2, 'Kharkov'),
 (3, 'Kiev'),
 (4, 'Kherson'),
 (5, 'Sumi'),
 (6, 'Lviv');

 INSERT INTO route_list
 VALUES (1, 1, 2, '2022-12-31', 600, 30);

 INSERT INTO route_list
 VALUES (2, 3, 5, '2022-12-29', 500, 20);

INSERT INTO route_list
VALUES (3, 1, 6, '2022-12-17', 550, 0);

INSERT INTO route_list
VALUES (4, 4, 5, '2023-01-5', 555, 10);

INSERT INTO route_list
VALUES (5, 6, 3, '2023-02-5', 555, 1);

INSERT INTO payment_status
VALUES (1, 'NEW');

INSERT INTO payment_status
VALUES (2, 'FAILED');

INSERT INTO payment_status
VALUES (3, 'DONE');