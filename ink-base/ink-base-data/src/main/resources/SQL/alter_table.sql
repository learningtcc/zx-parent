bannerManager表，新增字段：terminalType（终端类型）	字段类型：bigint	


#####友情链接 friend_link 添加sort和 modifyTime字段
ALTER TABLE `friend_link` ADD sort INT(11) DEFAULT 0 COMMENT '排序字段'; 
ALTER TABLE `friend_link` ADD modifyTime DATETIME DEFAULT NULL COMMENT '修改时间'; 

#####问题管理 question 添加isDisplay和 modifyTime字段
ALTER TABLE `question` ADD isDisplay TINYINT(1) DEFAULT 0 COMMENT '是否显示,0不显示1显示'; 
ALTER TABLE `question` ADD modifyTime DATETIME DEFAULT NULL COMMENT '修改时间';

#####商户登记company_basic_info 添加modifyTime字段
ALTER TABLE `company_basic_info` ADD modifyTime DATETIME DEFAULT NULL COMMENT '修改时间';

#####后台用户表 staff 添加 salt，createName，loginCount，previousLoginTime 字段
ALTER TABLE `staff` ADD salt varchar(30) DEFAULT NULL COMMENT '加盐字段'; 
ALTER TABLE `staff` ADD createName varchar(50) DEFAULT NULL COMMENT '创建人'; 
ALTER TABLE `staff` ADD loginCount INT(11) DEFAULT 0 COMMENT '登录次数'; 
ALTER TABLE `staff` ADD previousLoginTime DATETIME DEFAULT NULL COMMENT '上次登录日期'; 

#####loan_phase 新增字段
ALTER TABLE `loan_phase` ADD isDelete INT(10) DEFAULT 0 COMMENT '是否有效';

#####
ALTER TABLE `loan_investor_phase` ADD loanId INT(11) DEFAULT 0 COMMENT '项目ID';

#####因为不太清楚具体菜单权限与角色数据，即等确认后再修改该文件
#####新增菜单权限表 permission

#####新增角色表 role

#####新增角色与菜单权限关系表 role_permission

#####新增user_account、user_account_record 两个表  



