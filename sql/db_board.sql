drop user 'boardmaster'@'localhost';
drop database db_board;
create user 'boardmaster'@'localhost' identified by 'dotorimuk';
create database db_board;
grant all privileges on db_board.* to 'boardmaster'@'%' identified by 'dotorimuk';
grant all privileges on db_board.* to 'boardmaster'@'localhost' identified by 'dotorimuk';
FLUSH PRIVILEGES;

USE db_board;

drop table if exists reply;
drop table if exists board;
drop table if exists member;

CREATE TABLE member(
    id varchar(20) not null,
    password varchar(255) not null,
    name varchar(100) not null,
    mail varchar(100) not null,
    date datetime not null,
    permit tinyint(3) unsigned,
    primary key (id)
);

CREATE TABLE board(
    number int not null auto_increment primary key,
    title varchar(150) not null,
    content text not null,
    id varchar(20) not null,
    password varchar(255) not null,
    date datetime not null,
    hit int not null default 0,
    constraint board_fk_id foreign key (id) references member (id)
);

CREATE TABLE reply(
    idx int not null auto_increment primary key,
    con_num int not null,
    id varchar(20) not null, 
    password varchar(255) not null, 
    content text not null, 
    date datetime not null,
    constraint reply_fk_con_num foreign key (con_num) references board (number),
    constraint reply_fk_id foreign key (id) references member (id)
);
