drop table if exists log_admin;

use Syslog;
create table log_admin (
id varchar(20),
password varchar(32),
constraint pk_logad_id primary key(id)
);

desc log_admin;

insert into log_admin values ('admin', md5('0000'));

select * from log_admin;

commit;


