-- user
create table if not exists user(
    username varchar(255) not null unique,
    nickname varchar(255) not null default '',
    password varchar(255) not null,
    signature varchar(255) not null default '',
    salt varchar(255) not null,
    avatar varchar(2083) not null default '',
    userid varchar(255) not null unique,
    gender varchar(10) not null default '',
    age int(3) not null default 0,
    id int(11) auto_increment primary key
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

-- story
create table if not exists story(
    id int(11) auto_increment primary key,
    story_id varchar(255) not null unique,
    author_id varchar(255) not null,
    title varchar(255) not null,
    content text not null,
    release_time int(11) not null,
    release_location varchar(255) not null,
    pictures mediumtext  not null,
    attraction_id varchar(255) not null
);

-- story_likes
create table if not exists story_likes(
    id int(11) auto_increment primary key,
    userid varchar(255) not null,
    story_id varchar(255)
);

-- comment_likes
create table if not exists comment_likes(
    id int(11) auto_increment primary key,
    userid varchar(255) not null,
    comment_id varchar(255)
);

-- follow
create table if not exists follow(
    id int(11) auto_increment primary key,
    userid varchar(255) not null,
    follower_id varchar(255) not null
);

-- comment
create table if not exists comment(
    id int(11) auto_increment primary key,
    story_id varchar(255) not null,
    author_id varchar(255) not null,
    comment_id varchar(255) not null,
    pid varchar(255) not null default '',
    content mediumtext not null
);
