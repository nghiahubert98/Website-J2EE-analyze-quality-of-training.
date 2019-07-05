Create database java_uit default character set utf8;
CREATE USER 'java_uit'@'localhost' IDENTIFIED BY 'java_uit@123';
GRANT ALL ON java_uit.* TO 'java_uit'@'localhost';