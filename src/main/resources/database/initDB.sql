-- -----------------------------------------------------
-- Table `list_of_cities`
-- -----------------------------------------------------

create TABLE IF NOT EXISTS profession_list (
  id SERIAL NOT NULL UNIQUE,
  class VARCHAR(45) NULL,
  PRIMARY KEY (id));

create TABLE IF NOT EXISTS man_name_list (
  id SERIAL NOT NULL UNIQUE,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

create TABLE IF NOT EXISTS woman_name_list (
  id SERIAL NOT NULL UNIQUE,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));

create TABLE IF NOT EXISTS surname_list (
  id SERIAL NOT NULL UNIQUE,
  surname VARCHAR(45) NULL,
  PRIMARY KEY (id));

create TABLE IF NOT EXISTS settlement_list (
  id SERIAL NOT NULL UNIQUE,
  name VARCHAR(45) NULL,
  start_time DATE NULL,
  last_time DATE NULL,
  PRIMARY KEY (id));

create TABLE IF NOT EXISTS man_list (
  id SERIAL NOT NULL UNIQUE,
  name_id INT NULL,
  surname_id INT NULL,
  health INT NULL,
  date_born DATE NULL,
  profession_id INT NULL,
  settlement_id INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_profession_list_men_list
    FOREIGN KEY (profession_id)
    REFERENCES profession_list(id)
    ON delete NO ACTION
    ON update NO ACTION,
  CONSTRAINT fk_name_list_man_list
    FOREIGN KEY (name_id)
    REFERENCES man_name_list(id)
    ON delete NO ACTION
    ON update NO ACTION,
  CONSTRAINT fk_surname_list_man_list
    FOREIGN KEY (surname_id)
    REFERENCES surname_list(id)
    ON delete NO ACTION
    ON update NO ACTION,
  CONSTRAINT fk_settlement_list_man_list
    FOREIGN KEY (settlement_id)
    REFERENCES settlement_list(id)
    ON delete NO ACTION
    ON update NO ACTION);

create TABLE IF NOT EXISTS woman_list (
  id SERIAL NOT NULL UNIQUE,
  name_id INT NULL,
  surname_id INT NULL,
  health INT NULL,
  date_born DATE NULL,
  profession_id INT NULL,
  settlement_id INT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_profession_list_woman_list
    FOREIGN KEY (profession_id)
    REFERENCES profession_list(id)
    ON delete NO ACTION
    ON update NO ACTION,
  CONSTRAINT fk_settlement_list_woman_list
    FOREIGN KEY (settlement_id)
    REFERENCES settlement_list(id)
    ON delete NO ACTION
    ON update NO ACTION,
  CONSTRAINT fk_name_list_woman_list
    FOREIGN KEY (name_id)
    REFERENCES woman_name_list(id)
    ON delete NO ACTION
    ON update NO ACTION,
  CONSTRAINT fk_surname_list_woman_list
    FOREIGN KEY (surname_id)
    REFERENCES surname_list(id)
    ON delete NO ACTION
    ON update NO ACTION);



--create TABLE IF NOT EXISTS negative_traits (
--  id SERIAL NOT NULL UNIQUE,
--  trait VARCHAR(45) NULL,
--  description VARCHAR(45) NULL);
--
--create TABLE IF NOT EXISTS positive_traits (
--  id SERIAL NOT NULL UNIQUE,
--  trait VARCHAR(45) NULL,
--  description VARCHAR(45) NULL);

--create TABLE IF NOT EXISTS individual_negative_traits (
--  individual_id INT NOT NULL,
--  negative_traits_id INT NOT NULL,
--  primary key (individual_id, negative_traits_id),
--  FOREIGN KEY (individual_id)
--        REFERENCES man_list(id),
--  FOREIGN KEY (negative_traits_id)
--        REFERENCES negative_traits(id)) ENGINE=InnoDB;
--
--create TABLE IF NOT EXISTS individual_positive_traits (
--  individual_id INT NOT NULL,
--  positive_traits_id INT NOT NULL,
--  primary key (individual_id, positive_traits_id),
--  FOREIGN KEY (individual_id)
--        REFERENCES woman_list(id),
--  FOREIGN KEY (positive_traits_id)
--        REFERENCES positive_traits(id)) ENGINE=InnoDB;

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
