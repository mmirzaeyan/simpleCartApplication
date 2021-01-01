create table sms_queue
(
    id           varchar(255) not null,
    content      varchar(255) not null,
    mobile_phone varchar(255) not null,
    is_success   bit          not null,
    primary key (id)
) engine = InnoDB;
