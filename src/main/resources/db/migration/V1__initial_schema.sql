CREATE TABLE IF NOT EXISTS customer (
    accountNumber varchar(6) not null,
    firstName varchar(200) not null,
    surname varchar(200) not null,
    balance decimal not null,

    primary key (accountNumber)
);