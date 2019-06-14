# Host: 127.0.0.1  (Version 5.6.44)
# Date: 2019-06-14 22:27:07
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "r_admin_role"
#

DROP TABLE IF EXISTS `r_admin_role`;
CREATE TABLE `r_admin_role` (
  `admin_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`admin_id`,`role_id`),
  KEY `FKbhdh6dkadub105tjfe17k1124` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "r_admin_role"
#


#
# Structure for table "r_role_permission"
#

DROP TABLE IF EXISTS `r_role_permission`;
CREATE TABLE `r_role_permission` (
  `role_id` varchar(32) NOT NULL,
  `permission_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FKhs6f6sv6hr6ynj08p8lsly79` (`permission_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "r_role_permission"
#


#
# Structure for table "t_admin"
#

DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` varchar(32) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `user_password` varchar(64) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "t_admin"
#


#
# Structure for table "t_permission"
#

DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` varchar(32) NOT NULL,
  `permission_name` varchar(255) DEFAULT NULL,
  `permission_url` varchar(255) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjpo0wq99a03xl6oxm1gcwmbol` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "t_permission"
#

/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES ('1','管理员管理','/admin/index',NULL),('2','添加管理员','/admin/add','1'),('3','浏览管理员','/admin/list','1'),('4','系统管理','/system/index',NULL),('5','系统时间','/system/time','4');
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;

#
# Structure for table "t_role"
#

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "t_role"
#

