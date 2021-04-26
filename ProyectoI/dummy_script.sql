SET GLOBAL time_zone = '-6:00';
SELECT admin_id, username, email, pho_num, pass FROM administrators;
SELECT DATABASE(); 
USE universidad;
select * from information_schema.schemata; 
show databases;
select count(*) as total_admins from administrators;