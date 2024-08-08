alter table k4r.tb_users
    modify username varchar(255) not null comment '用户名';

alter table k4r.tb_users
    add nickname varchar(50) not null comment '昵称' after username;

alter table k4r.tb_users
    modify password varchar(255) not null comment '密码';

alter table k4r.tb_users
    modify email varchar(255) not null comment '电子邮件';

