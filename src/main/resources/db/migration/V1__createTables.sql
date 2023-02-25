CREATE TABLE IF NOT EXISTS `human`
(

    `id`      bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`    varchar(50),
    `age`     int,
    `balance` numeric(15, 2)

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS `car`
(

    `id`       bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `model`    varchar(50),
    `cost`     numeric(15, 2),
    `human_id` bigint NOT NULL,
    CONSTRAINT FK_HumanCar FOREIGN KEY (human_id)
        REFERENCES human (id)

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS `deal_history`
(

    `id`        bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `date`      date,
    `status`    varchar(50),
    `seller_id` bigint,
    `buyer_id`  bigint,
    `car_id`    bigint

) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;
