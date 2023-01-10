-- -----------------------------------------------------
-- Table `list_of_cities`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS profession_list (
  id SERIAL NOT NULL UNIQUE,
  class VARCHAR(45) NULL,
  strength INT NULL,
  dexterity INT NULL,
  stamina INT NULL,
  magic INT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS user_list (
  id SERIAL NOT NULL UNIQUE,
  health INT NULL,
  damage INT NULL,
  name VARCHAR(45) NULL,
  profession_id INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_profession_list_user_list
    FOREIGN KEY (profession_id)
    REFERENCES profession_list(id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


--
---- -----------------------------------------------------
---- Table `route_list`
---- -----------------------------------------------------
--CREATE TABLE IF NOT EXISTS route_list (
--  id SERIAL NOT NULL UNIQUE ,
--  city_output INT NOT NULL,
--  city_input INT NOT NULL,
--  departure_time DATE NULL,
--  value INT NULL,
--  count INT NULL,
--  PRIMARY KEY (id),
--  CONSTRAINT fk_route_list_list_of_cities
--    FOREIGN KEY (city_output)
--    REFERENCES list_of_cities(id)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION,
--  CONSTRAINT fk_route_list_list_of_cities1
--    FOREIGN KEY (city_input)
--    REFERENCES list_of_cities(id)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION);
--
---- -----------------------------------------------------
---- Table `billetservice`.`payment_status`
---- -----------------------------------------------------
--CREATE TABLE IF NOT EXISTS payment_status (
--  id SERIAL NOT NULL UNIQUE ,
--  status VARCHAR(45) NOT NULL UNIQUE,
--  PRIMARY KEY (id));
--
---- -----------------------------------------------------
---- Table `billetservice`.`bilet_list`
---- -----------------------------------------------------
--CREATE TABLE IF NOT EXISTS bilet_list (
--  id SERIAL NOT NULL UNIQUE ,
--  route_list_id INT NOT NULL,
--  firstname VARCHAR(45) NOT NULL,
--  surname VARCHAR(45) NOT NULL,
--  patronomic VARCHAR(45) NOT NULL,
--  PRIMARY KEY (id),
--  CONSTRAINT fk_bilet_list_route_list1
--    FOREIGN KEY (route_list_id)
--    REFERENCES route_list(id)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION);
--
---- -----------------------------------------------------
---- Table `billetservice`.`payment`
---- -----------------------------------------------------
--CREATE TABLE IF NOT EXISTS payment (
--  id SERIAL NOT NULL UNIQUE ,
--  payment_status_id INT NOT NULL,
--  bilet_list_id INT NOT NULL,
--  PRIMARY KEY (id),
--  CONSTRAINT fk_payment_payment_status1
--    FOREIGN KEY (payment_status_id)
--    REFERENCES payment_status (id)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION,
--  CONSTRAINT fk_payment_bilet_list1
--    FOREIGN KEY (bilet_list_id)
--    REFERENCES bilet_list (id)
--    ON DELETE NO ACTION
--    ON UPDATE NO ACTION);
--
