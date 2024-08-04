create table if not exists k4r.tb_dietary_records
(
    id        bigint       not null comment '饮食记录ID'
        primary key,
    record_id int          null comment '记录ID',
    meal_name varchar(255) null comment '餐点名称',
    time      datetime     null comment '时间',
    calories  int          null comment '卡路里'
)
    charset = utf8mb4;

create table if not exists k4r.tb_expenses
(
    id        bigint         not null comment '消费记录ID'
        primary key,
    record_id int            null comment '记录ID',
    item_name varchar(255)   null comment '商品名称',
    amount    decimal(10, 2) null comment '金额',
    date      date           null comment '日期',
    notes     text           null comment '备注'
)
    charset = utf8mb4;

create table if not exists k4r.tb_memos
(
    id            bigint   not null comment '备忘记录ID'
        primary key,
    record_id     int      null comment '记录ID',
    reminder_time datetime null comment '提醒时间'
)
    charset = utf8mb4;

create table if not exists k4r.tb_records
(
    id            bigint       not null comment '记录ID'
        primary key,
    user_id       int          null comment '用户ID',
    record_type   int          null comment '记录类型',
    title         varchar(255) null comment '标题',
    content       text         null comment '内容',
    creation_date datetime     null comment '创建日期'
)
    charset = utf8mb4;

create table if not exists k4r.tb_schedules
(
    id         bigint       not null comment '日程记录ID'
        primary key,
    record_id  int          null comment '记录ID',
    event_name varchar(255) null comment '事件名称',
    date       date         null comment '日期',
    time       time         null comment '时间',
    location   varchar(255) null comment '地点'
)
    charset = utf8mb4;

create table if not exists k4r.tb_users
(
    id       bigint       not null comment '用户ID'
        primary key,
    username varchar(255) null comment '用户名',
    password varchar(255) null comment '密码',
    email    varchar(255) null comment '电子邮件'
)
    charset = utf8mb4;