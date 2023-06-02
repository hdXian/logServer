create user 'boardmaster'@'localhost' identified by 'dotorimuk';

create database Board;
grant all privileges on Board.* to 'boardmaster'@'%' identified by 'dotorimuk';

use Board;

drop table if exists comment;
drop table if exists content;
drop table if exists board;
drop table if exists buser;

create table buser (
   id varchar(15),
   pw varchar(40) not null,
   name varchar(100) not null,
   email varchar(100) not null,
   constraint pk_user_id primary key(id)
);

create table board (
   bno int auto_increment,
   id varchar(15) not null,
   title varchar(200) not null,
   bdate date not null,
   hit int not null,
   constraint pk_board_bno primary key(bno),
   constraint fk_board_id foreign key (id) references buser (id)
);

create table content (
   bno int,
   content varchar(4000) not null,
   constraint pk_content_bno primary key(bno),
   constraint fk_content_bno foreign key (bno) references board(bno)
);
 
create table comment (
   bno int not null,
   ctime datetime not null,
   content varchar(1000) not null,
   id varchar(15) not null,
   constraint pk_comment_bno_ctime primary key (bno, ctime),
   constraint fk_comment_bno foreign key (bno) references board (bno),
   constraint fk_comment_id foreign key (id) references buser (id)
);

