CREATE TABLE IF NOT EXISTS `cars`
(
    `id`        bigint NOT NULL AUTO_INCREMENT,
    `model`     VARCHAR(255) DEFAULT NULL,
    `brand`     VARCHAR(255) DEFAULT NULL,
    `type`      VARCHAR(255) DEFAULT NULL,
    `inventory` INT          DEFAULT NULL,
    `daily_fee` DECIMAL      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
