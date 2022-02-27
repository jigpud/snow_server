-- user
create table if not exists user(
    username varchar(255) not null unique,
    nickname varchar(255) not null default '',
    password varchar(255) not null,
    signature varchar(255) not null default '',
    salt varchar(255) not null,
    avatar varchar(2083) not null default '',
    userid varchar(255) not null unique,
    likes int(11) not null default 0,
    gender varchar(10) not null default '',
    age int(3) not null default 0,
    id int(11) auto_increment primary key,
    version int(11) not null default 0
);

-- role
create table if not exists role(
    id int(11) auto_increment primary key,
    userid varchar(255) not null unique,
    roles varchar(255) not null default 'user'
);

-- permission
create table if not exists permission(
    id int(11) auto_increment primary key,
    userid varchar(255) not null unique,
    permissions varchar(255) not null default 'read'
);
