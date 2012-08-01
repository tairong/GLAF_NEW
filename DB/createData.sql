
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr1', 1, 1, 2, to_date('01-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr2', 2, 2, 3, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr3', 1, 3, 4, to_date('03-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr4', 1, 4, 5, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr5', 1, 5, 6, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr6', 1, 6, 7, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr7', 1, 7, 8, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr8', 1, 8, 9, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr9', 1, 9, 10, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr10', 1, 10, 11, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr11', 1, 11, 12, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr12', 1, 12, 13, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr13', 1, 13, 14, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr14', 1, 14, 15, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr15', 1, 15, 16, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr16', 1, 16, 17, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr17', 1, 17, 18, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr18', 1, 18, 19, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr19', 1, 19, 20, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr20', 1, 20, 21, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr21', 1, 21, 22, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('tr22', 1, 22, 23, to_date('02-03-2012', 'dd-mm-yyyy'));
insert into T_ARRAYGRID (COMPANY, PRICE, CHANGE, CHANGEPER, LASEUPDATED)
values ('中文公司', 1, 24, 24, to_date('02-03-2012', 'dd-mm-yyyy'));


insert into T_BATCHCONFIG (BATNAME, LASTEXEDATE, INTERVAL, WHAT, LOCKFLAG, ENABLE, BATCLASSNAME, BATBUSNAME)
values ('TestMultiFileBatch', to_timestamp('15-05-2012 16:55:00', 'dd-mm-yyyy hh24:mi:ss'), 1, 'd:/tt/TestIscBatch.bat', '0', '1', null, null);
insert into T_BATCHCONFIG (BATNAME, LASTEXEDATE, INTERVAL, WHAT, LOCKFLAG, ENABLE, BATCLASSNAME, BATBUSNAME)
values ('TestIscBatch', to_timestamp('15-05-2012 16:55:00', 'dd-mm-yyyy hh24:mi:ss'), 1, 'd:/tt/TestIscBatch.bat', '0', '1', null, null);
insert into T_BATCHCONFIG (BATNAME, LASTEXEDATE, INTERVAL, WHAT, LOCKFLAG, ENABLE, BATCLASSNAME, BATBUSNAME)
values ('TestFileBatch', to_timestamp('15-05-2012 16:55:00', 'dd-mm-yyyy hh24:mi:ss'), 1, 'd:/tt/TestIscBatch.bat', '0', '1', null, null);


insert into T_ISCSAMPLE (SYSNO, NAME, SEX, AGE, CITY, DEADYEAR, MONEY)
values (4, 'tr        ', '1', '0 ', '028', '1982', null);
insert into T_ISCSAMPLE (SYSNO, NAME, SEX, AGE, CITY, DEADYEAR, MONEY)
values (1, '1         ', '1', '0 ', '028', '2012', 132165465.230056);
insert into T_ISCSAMPLE (SYSNO, NAME, SEX, AGE, CITY, DEADYEAR, MONEY)
values (2, '1         ', '2', '0 ', '000', '2012', 132165465.230056);
insert into T_ISCSAMPLE (SYSNO, NAME, SEX, AGE, CITY, DEADYEAR, MONEY)
values (3, '1         ', '2', '0 ', '000', '2012', 132165465.230056);

insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun034', '选项2', '3', '/GLAF/sys/sysJsp/main.jsp');
insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun020', '旧版DEMO', '2', '/GLAF/demo/demoJsp/demoIndex.jsp');
insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun021', '旧版增删查改例子', '2', '../../iscSample.do');
insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun022', '分页检索grid', '2', '../../arrayGrid.do');
insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun023', '更新grid', '2', '../../cellEditing.do');
insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun025', 'xls导入和导出&PDF生成', '2', '/GLAF/demo/demoJsp/xlsDo.jsp');
insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun024', 'csv测试', '2', '/GLAF/demo/demoJsp/CsvTest.jsp');
insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun041', '用户管理', '4', '../../userManage.do');
insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun042', 'batch管理', '4', '../../manageBatch.do');
insert into T_PRIVILEGE (F_PRIVILEGEID, F_PRIVILEGENAME, F_PRIVILEGETYPE, F_URL)
values ('fun043', '查询batch执行日志', '4', '../../showBatchExeLog.do');

insert into T_ROLE (F_ROLEID, F_ROLENAME, F_REMARK)
values (1, '系统管理员', '系统管理员');
insert into T_ROLE (F_ROLEID, F_ROLENAME, F_REMARK)
values (2, '测试角色', '测试角色');
insert into T_ROLE (F_ROLEID, F_ROLENAME, F_REMARK)
values (3, '测试角色2', '测试角色2');

insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun041', null, null, null, null);
insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun042', null, null, null, null);
insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun043', null, null, null, null);
insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun021', null, null, null, null);
insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun022', null, null, null, null);
insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun023', null, null, null, null);
insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun025', null, null, null, null);
insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun024', null, null, null, null);
insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun020', null, null, null, null);
insert into T_ROLE_PRIVILEGE (F_ROLEID, F_PRIVILEGEID, F_CREATUSERID, F_UPDATEUSERID, F_CREATDATE, F_UPDATEDATE)
values (1, 'fun034', null, null, null, null);

insert into T_USERS (F_USERID, F_NAME, F_PASSWORD, F_EMAIL, F_STATUS, F_CREATUSERID, F_CREATDATE, F_UPDATEUSERID, F_UPDATEDATE, F_GYSBM, F_GYSGQ)
values ('admin', '系统管理员', '1234567', 'tairong@intasect.com.cn', '1', null, null, 'admin', to_timestamp('20-06-2012 11:46:03', 'dd-mm-yyyy hh24:mi:ss'), null, null);
insert into T_USERS (F_USERID, F_NAME, F_PASSWORD, F_EMAIL, F_STATUS, F_CREATUSERID, F_CREATDATE, F_UPDATEUSERID, F_UPDATEDATE, F_GYSBM, F_GYSGQ)
values ('tr', '台蓉', '1234567', 'tairong@intasect.com.cn', '0', 'admin', to_timestamp('17-04-2012 10:39:06', 'dd-mm-yyyy hh24:mi:ss'), 'admin', to_timestamp('16-05-2012 10:10:52', 'dd-mm-yyyy hh24:mi:ss'), null, null);

insert into T_USERS_ROLE (F_USERID, F_ROLEID, F_CREATUSERID, F_CREATDATE, F_UPDATEUSERID, F_UPDATEDATE)
values ('admin', 1, 'admin', to_timestamp('18-06-2012 17:30:18', 'dd-mm-yyyy hh24:mi:ss'), 'admin', to_timestamp('18-06-2012 17:30:18', 'dd-mm-yyyy hh24:mi:ss'));
