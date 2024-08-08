create table k4r.tb_files
(
    id        bigint       not null,
    filename  varchar(255) null,
    extension varchar(10)  null,
    save_path varchar(256) null
)
    comment '文件表';

