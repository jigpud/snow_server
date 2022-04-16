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
    age int not null default 0,
    id int auto_increment primary key,
    background varchar(2083) not null default ''
);

-- role
create table if not exists role(
    id int auto_increment primary key,
    userid varchar(255) not null unique,
    roles varchar(255) not null default 'user'
);

-- permission
create table if not exists permission(
    id int auto_increment primary key,
    userid varchar(255) not null unique,
    permissions varchar(255) not null default 'read'
);

-- story
create table if not exists story(
    id int auto_increment primary key,
    story_id varchar(255) not null unique,
    author_id varchar(255) not null,
    title varchar(255) not null,
    content text not null,
    release_time int not null,
    release_location varchar(255) not null,
    pictures mediumtext  not null,
    attraction_id varchar(255) not null
);

-- story_likes
create table if not exists story_like(
    id int auto_increment primary key,
    userid varchar(255) not null,
    story_id varchar(255) not null,
    unique key story_like(userid, story_id)
);

-- comment_likes
create table if not exists comment_like(
    id int auto_increment primary key,
    userid varchar(255) not null,
    comment_id varchar(255) not null,
    unique key comment_like(userid, comment_id)
);

-- follow_user
create table if not exists user_follow(
    id int auto_increment primary key,
    userid varchar(255) not null,
    follower_id varchar(255) not null,
    unique key user_follow(userid, follower_id)
);

-- follow_attraction
create table if not exists attraction_follow(
    id int auto_increment primary key,
    userid varchar(255) not null,
    attraction_id varchar(255) not null,
    unique key attraction_follow(userid, attraction_id)
);

-- comment
create table if not exists comment(
    id int auto_increment primary key,
    story_id varchar(255) not null,
    author_id varchar(255) not null,
    comment_id varchar(255) not null,
    pid varchar(255) not null default '',
    content mediumtext not null
);

-- favorite
create table if not exists favorite(
    id int auto_increment primary key,
    userid varchar(255) not null,
    story_id varchar(255) not null,
    unique key favorite(userid, story_id)
);
