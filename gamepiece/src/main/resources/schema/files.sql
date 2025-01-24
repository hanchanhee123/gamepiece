CREATE TABLE IF NOT EXISTS `files` (
  `file_idx` varchar(50) NOT NULL COMMENT '파일idx',
  `file_original_name` varchar(150) NOT NULL COMMENT '파일오리지널명',
  `file_new_name` varchar(150) NOT NULL COMMENT '새로운파일명',
  `file_path` varchar(150) NOT NULL COMMENT '파일경로',
  `file_size` int NOT NULL COMMENT '파일사이즈',
  `file_reg_date` datetime NOT NULL COMMENT '파일등록일자',
  PRIMARY KEY (`file_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
