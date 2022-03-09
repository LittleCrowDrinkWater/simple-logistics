use logistics;
insert into user_group (description, group_name)
VALUES ('对职员用户的增删改查', '管理组');
insert into user_group (description, group_name)
VALUES ('司机组', '司机组');
insert into user_group (description, group_name)
VALUES ('填写货运单', '客户组');
insert into user_group (description, group_name)
VALUES ('接货管理：填写一份货运单合同', '票据组');
insert into user_group (description, group_name)
VALUES ('结算管理', '财务组');
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (1, 'admin', '12345678910', 'a@1.com', 0, 0, 0, '21232f297a57a5a743894a0e4a801fc3', '110101195303153019',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (2, 'operator1', '11111111111', 'b@1.com', 1640970061000, 1640970061000, 1640970061000,
        '4b583376b2767b923c3e1da60d10de59', '110101195303153021',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (2, 'operator2', '22222222222', 'c@1.com', 1640970061000, 1640970061000, 1640970061000,
        '4b583376b2767b923c3e1da60d10de59', '110101195303153022',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (2, 'operator3', '33333333333', 'd@1.com', 1640970061000, 1640970061000, 1640970061000,
        '4b583376b2767b923c3e1da60d10de59', '110101195303153023',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (2, 'operator4', '44444444444', 'e@1.com', 1640970061000, 1640970061000, 1640970061000,
        '4b583376b2767b923c3e1da60d10de59', '110101195303153024',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (2, 'operator5', '55555555555', 'f@1.com', 1640970061000, 1640970061000, 1640970061000,
        '4b583376b2767b923c3e1da60d10de59', '110101195303153025',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (2, 'operator6', '66666666666', 'g@1.com', 1640970061000, 1640970061000, 1640970061000,
        '4b583376b2767b923c3e1da60d10de59', '110101195303153026',
        -530179200);

insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver1', '111', 'a@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153027',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver2', '222', 'b@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153028',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver3', '333', 'c@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153029',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver4', '444', 'd@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153030',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver5', '555', 'e@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153031',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver6', '666', 'f@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153032',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver7', '777', 'g@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153033',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver8', '888', 'h@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153034',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver9', '999', 'i@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153035',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver10', '101010', 'j@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153036',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver11', '111111', 'k@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153037',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (3, 'driver12', '121212', 'l@2.com', 1640970061000, 1640970061000, 1640970061000,
        'e2d45d57c7e2941b65c6ccd64af4223e', '110101195303153038',
        -530179200);

insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (4, 'customer1', '131313', 'a@3.com', 1640970061000, 1640970061000, 1640970061000,
        '91ec1f9324753048c0096d036a694f86', '110101195303153039',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (4, 'customer2', '141414', 'b@3.com', 1640970061000, 1640970061000, 1640970061000,
        '91ec1f9324753048c0096d036a694f86', '110101195303153040',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (4, 'customer3', '151515', 'c@3.com', 1640970061000, 1640970061000, 1640970061000,
        '91ec1f9324753048c0096d036a694f86', '110101195303153041',
        -530179200);
insert into user (type_id, name, tel, email, gmt_modified, gmt_create, gmt_last_login, password, id_card, birthday)
VALUES (4, 'customer4', '161616', 'd@3.com', 1640970061000, 1640970061000, 1640970061000,
        '91ec1f9324753048c0096d036a694f86', '110101195303153042',
        -530179200);

insert into location (province, city, detail_address, gmt_create, gmt_modified)
VALUES ('A省', 'A市', 'XX街道YY仓库', 1640970061000, 1640970061000);
insert into location (province, city, detail_address, gmt_create, gmt_modified)
VALUES ('B省', 'B市', 'XX街道YY仓库', 1640970061000, 1640970061000);
insert into location (province, city, detail_address, gmt_create, gmt_modified)
VALUES ('C省', 'C市', 'XX街道YY仓库', 1640970061000, 1640970061000);
insert into location (province, city, detail_address, gmt_create, gmt_modified)
VALUES ('D省', 'D市', 'XX街道YY仓库', 1640970061000, 1640970061000);
insert into location (province, city, detail_address, gmt_create, gmt_modified)
VALUES ('E省', 'E市', 'XX街道YY仓库', 1640970061000, 1640970061000);
insert into location (province, city, detail_address, gmt_create, gmt_modified)
VALUES ('F省', 'F市', 'XX街道YY仓库', 1640970061000, 1640970061000);

insert into warehouse (director_user_id, location_id, description, gmt_create, gmt_modified)
VALUES (2, 1, '', 1640970061000, 1640970061000);
insert into warehouse (director_user_id, location_id, description, gmt_create, gmt_modified)
VALUES (3, 1, '', 1640970061000, 1640970061000);
insert into warehouse (director_user_id, location_id, description, gmt_create, gmt_modified)
VALUES (4, 1, '', 1640970061000, 1640970061000);
insert into warehouse (director_user_id, location_id, description, gmt_create, gmt_modified)
VALUES (5, 1, '', 1640970061000, 1640970061000);
insert into warehouse (director_user_id, location_id, description, gmt_create, gmt_modified)
VALUES (6, 1, '', 1640970061000, 1640970061000);
insert into warehouse (director_user_id, location_id, description, gmt_create, gmt_modified)
VALUES (7, 1, '', 1640970061000, 1640970061000);

insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (8, 1, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (9, 1, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (10, 2, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (11, 2, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (12, 3, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (13, 3, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (14, 4, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (15, 4, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (16, 5, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (17, 5, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (18, 6, 1, 1640970061000, 1640970061000);
insert into car (user_id, warehouse_id, state, gmt_create, gmt_modified)
VALUES (19, 6, 1, 1640970061000, 1640970061000);

