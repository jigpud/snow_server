-- user
create table if not exists user(
    username varchar(255) not null unique,
    nickname varchar(255) not null default '',
    password mediumtext not null,
    signature varchar(255) not null default '',
    salt varchar(255) not null,
    avatar varchar(2083) not null default '',
    userid varchar(255) not null unique,
    gender varchar(10) not null default '',
    age int not null default 0,
    id bigint auto_increment primary key,
    background varchar(2083) not null default ''
);

-- role
create table if not exists role(
    id bigint auto_increment primary key,
    userid varchar(255) not null unique,
    roles text not null
);

-- permission
create table if not exists permission(
    id bigint auto_increment primary key,
    userid varchar(255) not null unique,
    permissions text not null
);

-- story
create table if not exists story(
    id bigint auto_increment primary key,
    story_id varchar(255) not null unique,
    author_id varchar(255) not null,
    title tinytext not null,
    content text not null,
    release_time bigint not null,
    pictures mediumtext not null,
    attraction_id varchar(255) not null
);

-- story_like
create table if not exists story_like(
    id bigint auto_increment primary key,
    userid varchar(255) not null,
    story_id varchar(255) not null,
    unique key story_like(userid, story_id)
);

-- comment_like
create table if not exists comment_like(
    id bigint auto_increment primary key,
    userid varchar(255) not null,
    comment_id varchar(255) not null,
    unique key comment_like(userid, comment_id)
);

-- user_follow
create table if not exists user_follow(
    id bigint auto_increment primary key,
    userid varchar(255) not null,
    follower_id varchar(255) not null,
    unique key user_follow(userid, follower_id)
);

-- attraction_follow
create table if not exists attraction_follow(
    id bigint auto_increment primary key,
    userid varchar(255) not null,
    attraction_id varchar(255) not null,
    unique key attraction_follow(userid, attraction_id)
);

-- comment
create table if not exists comment(
    id bigint auto_increment primary key,
    story_id varchar(255) not null,
    author_id varchar(255) not null,
    comment_id varchar(255) not null,
    pid varchar(255) not null default '',
    content mediumtext not null,
    comment_time bigint not null
);

-- story_favorite
create table if not exists story_favorite(
    id bigint auto_increment primary key,
    userid varchar(255) not null,
    story_id varchar(255) not null,
    unique key story_favorite(userid, story_id)
);

-- attraction
create table if not exists attraction(
    id bigint auto_increment primary key,
    attraction_id varchar(255) not null unique,
    name varchar(255) not null,
    description mediumtext not null,
    location tinytext not null,
    tags mediumtext not null
);

-- attraction_photo
create table if not exists attraction_photo(
    id bigint auto_increment primary key,
    uploader_id varchar(255) not null,
    attraction_id varchar(255) not null,
    photo varchar(2083) not null default '',
    photo_md5 varchar(128) not null default '',
    unique key attraction_photo(uploader_id, attraction_id, photo_md5)
);

-- attraction_score
create table if not exists attraction_score(
    id bigint auto_increment primary key,
    userid varchar(255) not null,
    attraction_id varchar(255) not null,
    score int not null default 0,
    unique key attraction_score(userid, attraction_id)
);
