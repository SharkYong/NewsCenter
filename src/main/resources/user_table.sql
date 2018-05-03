CREATE TABLE 'user_table'(`u_id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `u_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `u_password` varchar(20) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`u_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;