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
    id int(11) auto_increment primary key
);

-- admin
create table if not exists admin(
    id int(11) auto_increment primary key,
    userid varchar(255) not null unique,
    is_admin int(1) not null default 0
);