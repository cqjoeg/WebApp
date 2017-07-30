CREATE TABLE `customer` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `NAME`      VARCHAR(255)        DEFAULT '',
  `contact`   VARCHAR(255)        DEFAULT '',
  `telephone` VARCHAR(255)        DEFAULT '',
  `email`     VARCHAR(255)        DEFAULT '',
  `remark`    TEXT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;