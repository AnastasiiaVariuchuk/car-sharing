CREATE TABLE IF NOT EXISTS `payments` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `status` VARCHAR(255) DEFAULT NULL,
                                      `type` VARCHAR(255) DEFAULT NULL,
                                      `session_url` VARCHAR(255) DEFAULT NULL,
                                      `session_id` VARCHAR(255) DEFAULT NULL,
                                      `amount_to_pay` DECIMAL DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
