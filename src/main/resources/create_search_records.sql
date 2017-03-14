DROP TABLE IF EXISTS `search_records`;
CREATE TABLE `search_records` (
  `record_index` decimal(10,0) NOT NULL,
  `record_title` varchar(1000) NOT NULL,
  `record_price` varchar(20) NOT NULL,
  PRIMARY KEY (`record_index`)
)  DEFAULT CHARSET=utf8;
