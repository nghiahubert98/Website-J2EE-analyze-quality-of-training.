@ECHO OFF
ECHO Step 1: Input the password of root to drop the database if it exited.
mysql -u root -p  < DropDatabase.sql

ECHO Step 2: Input the password of root to create the database.
mysql -u root -p  < CreateDatabaseMySQL5.sql

ECHO Step 3: Create tables for the database java_uit...
mysql -u java_uit -pjava_uit@123 java_uit < CreateTable.sql

ECHO Step 4: Insert data for the database java_uit...
mysql -u java_uit -pjava_uit@123 java_uit < InsertData.sql
