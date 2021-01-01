create table cart
(
    id          varchar(255)     not null,
    createddate datetime(6)      not null,
    ip          varchar(255)     not null,
    updateddate datetime(6)      not null,
    version     integer          not null,
    balance     double precision not null,
    cart_number varchar(255)     not null,
    cvv2        varchar(255)     not null,
    expire_date varchar(255)     not null,
    pin         varchar(255)     not null,
    pin2        varchar(255),
    user_id     varchar(255)     not null,
    primary key (id)
) engine = InnoDB;
create table transaction_history
(
    id                 varchar(255)     not null,
    createddate        datetime(6)      not null,
    ip                 varchar(255)     not null,
    updateddate        datetime(6)      not null,
    version            integer          not null,
    amount             double precision not null,
    destination        varchar(255)     not null,
    transaction_status smallint         not null,
    source_cart_id     varchar(255)     not null,
    primary key (id)
) engine = InnoDB;
create table user
(
    id          varchar(255) not null,
    createddate datetime(6)  not null,
    ip          varchar(255) not null,
    updateddate datetime(6)  not null,
    version     integer      not null,
    username    varchar(255) not null,
    primary key (id)
) engine = InnoDB;

alter table cart
    add constraint FKl70asp4l4w0jmbm1tqyofho4o foreign key (user_id) references user (id);

alter table transaction_history
    add constraint FKt92tyvy18k41m6y28j4ecmbwc foreign key (source_cart_id) references cart (id);

INSERT INTO `user`(`id`, `createddate`, `ip`, `updateddate`, `username`, `version`)
VALUES ('8613e577-4794-11eb-9d98-0242ac140002', '2020-12-26 19:38:08.000000', '127.0.0.1', '2020-12-26 19:38:14.000000',
        'mohammad','0');
