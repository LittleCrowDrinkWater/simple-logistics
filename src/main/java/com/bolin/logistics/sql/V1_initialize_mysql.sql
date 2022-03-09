Create Database If Not Exists logistics;
use logistics;
create table logistics.location
(
    id             int auto_increment,
    province       varchar(50) null,
    city           varchar(50) null,
    detail_address varchar(50) null,
    gmt_create     bigint      null,
    gmt_modified   bigint      null,
    constraint location_id_uindex
        unique (id)
)
    collate = utf8_bin;

alter table logistics.location
    add primary key (id);

create table logistics.pay
(
    id              bigint auto_increment,
    payment_no      varchar(50)   null,
    goods_info_id   bigint        null,
    total_payment   int           null,
    pay_mode        int default 1 null,
    transfer_fee    int           null,
    carry_goods_fee int           null,
    gmt_create      bigint        null,
    gmt_modified    bigint        null,
    constraint pay_id_uindex
        unique (id)
)
    collate = utf8_bin;

create index goods_info_id
    on logistics.pay (goods_info_id);

alter table logistics.pay
    add primary key (id);

create table logistics.transfer_info
(
    id                  bigint auto_increment,
    goods_info_id       bigint      null comment '货物运单id',
    goods_bill_code     varchar(50) null comment '货单',
    status              int         null comment '订单状态',
    delivery_warehouse  int         null comment '发货仓库',
    receiving_warehouse int         null comment '收货仓库',
    car_id              bigint      null comment 'carId',
    carriage            double      null comment '重量',
    operate_user_id     bigint      null comment '操作员uid',
    predelivery_date    bigint      null comment '预计送达日期',
    send_goods_date     bigint      null comment '发货时间',
    fetch_goods_mode    int         null comment '取件方式',
    receive_user_id     bigint      null comment '收货人id',
    send_goods_user_id  bigint      null comment '寄货人id',
    remark              varchar(50) null comment '备注',
    gmt_create          bigint      null,
    gmt_modified        bigint      null,
    constraint transfer_info_id_uindex
        unique (id)
)
    charset = utf8;

create index goods_info_id
    on logistics.transfer_info (goods_info_id);

alter table logistics.transfer_info
    add primary key (id);

create table logistics.user_group
(
    id          int auto_increment
        primary key,
    description varchar(50) null,
    group_name  varchar(50) null
)
    charset = utf8;

create table logistics.user
(
    id             bigint auto_increment,
    type_id        int         null comment '用户类型',
    name           varchar(20) null,
    tel            varchar(20) null,
    email          varchar(64) null,
    gmt_modified   bigint      null comment '最新修改时间',
    gmt_create     bigint      null comment '创建时间',
    gmt_last_login bigint      null comment '最后一次登录时间',
    password       varchar(32) null comment '密码',
    id_card        varchar(18) null comment '身份证',
    birthday       bigint      null,
    constraint user_id_uindex
        unique (id),
    constraint user_ibfk_1
        foreign key (type_id) references logistics.user_group (id)
)
    collate = utf8_bin;

create index type_id
    on logistics.user (type_id);

alter table logistics.user
    add primary key (id);

create table logistics.car
(
    id                 bigint auto_increment,
    user_id            bigint      null comment 'user_id',
    warehouse_id       int         null comment '车辆所属仓库id，在到下一个仓库前属于上一个仓库',
    state              int         null comment '车辆状态',
    drive_licence      varchar(50) null comment '车牌
',
    id_card            varchar(50) null comment '驾照',
    insurance_card     varchar(50) null comment '保险',
    run_licence        varchar(50) null comment '车辆行驶号',
    allow_carry_volume double      null,
    allow_carry_weight double      null,
    car_length         double      null,
    car_width          double      null,
    car_height         double      null,
    car_frame_no       varchar(50) null,
    car_no             varchar(50) null,
    car_type           varchar(50) null,
    remark             varchar(50) null comment '备注信息',
    gmt_create         bigint      null,
    gmt_modified       bigint      null,
    constraint car_id_uindex
        unique (id),
    constraint car_ibfk_1
        foreign key (user_id) references logistics.user (id)
)
    charset = utf8;

create index user_id
    on logistics.car (user_id);

alter table logistics.car
    add primary key (id);

create table logistics.goods_info
(
    id                  bigint auto_increment,
    operate_user_id     bigint      null comment '操作员uid',
    receive_user_id     bigint      null comment '收货人id',
    send_goods_user_id  bigint      null comment '寄货人id',
    goods_bill_code     varchar(50) null comment '货单',
    car_id              bigint      null comment 'carId',
    status              int         null comment '订单状态',
    delivery_warehouse  int         null comment '发货仓库',
    receiving_warehouse int         null comment '收货仓库',
    carriage            double      null comment '重量',
    predelivery_date    bigint      null comment '预计送达日期',
    send_goods_date     bigint      null comment '发货时间',
    fetch_goods_mode    int         null comment '取件方式',
    transfer_status     int         null comment '中转状态',
    gmt_create          bigint      null,
    gmt_modified        bigint      null,
    remark              varchar(50) null comment '备注',
    constraint goods_info_id_uindex
        unique (id),
    constraint goods_info_ibfk_1
        foreign key (operate_user_id) references logistics.user (id),
    constraint goods_info_ibfk_2
        foreign key (receive_user_id) references logistics.user (id),
    constraint goods_info_ibfk_3
        foreign key (send_goods_user_id) references logistics.user (id)
)
    charset = utf8;

create index operate_user_id
    on logistics.goods_info (operate_user_id);

create index receive_user_id
    on logistics.goods_info (receive_user_id);

create index send_goods_user_id
    on logistics.goods_info (send_goods_user_id);

alter table logistics.goods_info
    add primary key (id);

create table logistics.warehouse
(
    id               int auto_increment,
    director_user_id bigint       null,
    location_id      int          null,
    description      varchar(100) null,
    gmt_create       bigint       null,
    gmt_modified     bigint       null,
    constraint warehouse_id_uindex
        unique (id)
)
    collate = utf8_bin;

create index director_user_id
    on logistics.warehouse (director_user_id);

create index location_id
    on logistics.warehouse (location_id);

alter table logistics.warehouse
    add primary key (id);

