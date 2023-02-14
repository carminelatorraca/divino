create table if not exists accounts
(
    account_id int auto_increment
        primary key,
    email      varchar(255) not null,
    password   varchar(255) not null,
    role       varchar(255) null
);

create table if not exists orders
(
    order_id               int auto_increment
        primary key,
    order_status           varchar(255) null,
    order_total_amount     double       null,
    order_shipping_address varchar(255) null,
    created_at             varchar(255) null,
    is_deleted             varchar(255) null,
    order_account          int          not null,
    order_payment          int          null,

    foreign key (order_account) references accounts (account_id) on delete cascade
);

create table if not exists payments
(
    payment_id          int auto_increment
        primary key,
    paid_amount         double       not null,
    payment_description varchar(255) null,
    payment_status      varchar(255) not null,
    payment_method      varchar(255) null,
    order_id            int          not null,

    foreign key (order_id) references orders (order_id) on delete cascade
);

create table if not exists products
(
    product_id           int auto_increment
        primary key,
    product_brand        varchar(255) null,
    product_description  varchar(255) null,
    product_format       varchar(255) null,
    product_price        double       null,
    product_availability int          null,
    is_sales             tinyint(1)   null,
    sales_price          double       null,
    product_vat          int          null,
    is_visible           tinyint(1)   null,
    image_path           varchar(255) null
);

create table if not exists order_items
(
    order_item_id    int auto_increment
        primary key,
    item_price       double       null,
    item_description varchar(255) null,
    order_id         int          not null,
    item_quantity    int          not null,
    item_vat         int          null,
    product_id       int          null,

    foreign key (order_id) references orders (order_id) on delete cascade,
    foreign key (product_id) references products (product_id) on delete cascade
);

create table if not exists users
(
    user_id    int auto_increment
        primary key,
    account_id int          null,
    firstName  varchar(255) null,
    lastName   varchar(255) null,
    fiscalCode varchar(255) null,

    foreign key (account_id) references accounts (account_id) on delete cascade
);

create table if not exists addresses
(
    address_id int auto_increment
        primary key,
    street     varchar(255) null,
    number     varchar(255) null,
    postalCode varchar(255) null,
    country    varchar(255) null,
    favourite  int          null,
    city       varchar(255) null,
    user_id    int          null,

    foreign key (user_id) references users (user_id) on delete cascade
);
