CREATE DATABASE  test_task_chat;

USE test_task_chat;
CREATE TABLE message (
	id int NOT NULL AUTO_INCREMENT,
	senderId int,
    recipientId int,
	content varchar(1500),
	dateMessage date,
    statusId varchar(1),
    PRIMARY KEY (id)
    
) ;