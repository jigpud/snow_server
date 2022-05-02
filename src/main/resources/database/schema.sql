-- user
CREATE TABLE IF NOT EXISTS user(
    username VARCHAR(255) NOT NULL UNIQUE,
    nickname VARCHAR(255) NOT NULL DEFAULT '',
    password MEDIUMTEXT NOT NULL,
    signature VARCHAR(255) NOT NULL DEFAULT '',
    salt VARCHAR(255) NOT NULL,
    avatar VARCHAR(2083) NOT NULL DEFAULT '',
    userid VARCHAR(255) NOT NULL UNIQUE,
    gender VARCHAR(10) NOT NULL DEFAULT '',
    age INT NOT NULL DEFAULT 0,
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    background VARCHAR(2083) NOT NULL DEFAULT ''
);

-- role
CREATE TABLE IF NOT EXISTS role(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL DEFAULT '',
    UNIQUE KEY role(userid, role)
);

-- permission
CREATE TABLE IF NOT EXISTS permission(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    permission VARCHAR(255) NOT NULL DEFAULT '',
    UNIQUE KEY permission(userid, permission)
);

-- story
CREATE TABLE IF NOT EXISTS story(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    story_id VARCHAR(255) NOT NULL UNIQUE,
    author_id VARCHAR(255) NOT NULL,
    title TINYTEXT NOT NULL,
    content text NOT NULL,
    release_time BIGINT NOT NULL,
    attraction_id VARCHAR(255) NOT NULL
);

-- story_picture
CREATE TABLE IF NOT EXISTS story_picture(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uploader_id VARCHAR(255) NOT NULL,
    story_id VARCHAR(255) NOT NULL,
    picture VARCHAR(2083) NOT NULL,
    picture_md5 VARCHAR(128) NOT NULL,
    UNIQUE KEY story_picture(story_id, picture_md5)
);

-- story_like
CREATE TABLE IF NOT EXISTS story_like(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    story_id VARCHAR(255) NOT NULL,
    UNIQUE KEY story_like(userid, story_id)
);

-- comment_like
CREATE TABLE IF NOT EXISTS comment_like(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    comment_id VARCHAR(255) NOT NULL,
    UNIQUE KEY comment_like(userid, comment_id)
);

-- user_follow
CREATE TABLE IF NOT EXISTS user_follow(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    follower_id VARCHAR(255) NOT NULL,
    UNIQUE KEY user_follow(userid, follower_id)
);

-- attraction_follow
CREATE TABLE IF NOT EXISTS attraction_follow(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    attraction_id VARCHAR(255) NOT NULL,
    UNIQUE KEY attraction_follow(userid, attraction_id)
);

-- comment
CREATE TABLE IF NOT EXISTS comment(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    story_id VARCHAR(255) NOT NULL,
    author_id VARCHAR(255) NOT NULL,
    comment_id VARCHAR(255) NOT NULL,
    pid VARCHAR(255) NOT NULL DEFAULT '',
    content MEDIUMTEXT NOT NULL,
    comment_time BIGINT NOT NULL
);

-- story_favorite
CREATE TABLE IF NOT EXISTS story_favorite(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    story_id VARCHAR(255) NOT NULL,
    UNIQUE KEY story_favorite(userid, story_id)
);

-- attraction
CREATE TABLE IF NOT EXISTS attraction(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attraction_id VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description MEDIUMTEXT NOT NULL,
    location TINYTEXT NOT NULL
);

-- attraction_tag
CREATE TABLE IF NOT EXISTS attraction_tag(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attraction_id VARCHAR(255) NOT NULL,
    tag TINYTEXT NOT NULL,
    tag_md5 VARCHAR(128) NOT NULL,
    UNIQUE KEY attraction_tag(attraction_id, tag_md5)
);

-- attraction_picture
CREATE TABLE IF NOT EXISTS attraction_picture(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uploader_id VARCHAR(255) NOT NULL,
    attraction_id VARCHAR(255) NOT NULL,
    picture VARCHAR(2083) NOT NULL DEFAULT '',
    picture_md5 VARCHAR(128) NOT NULL DEFAULT '',
    UNIQUE KEY attraction_picture(attraction_id, picture_md5)
);

-- attraction_score
CREATE TABLE IF NOT EXISTS attraction_score(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid VARCHAR(255) NOT NULL,
    attraction_id VARCHAR(255) NOT NULL,
    score INT NOT NULL DEFAULT 0,
    UNIQUE KEY attraction_score(userid, attraction_id)
);

-- food
CREATE TABLE IF NOT EXISTS food(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    food_id VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    description MEDIUMTEXT NOT NULL
);

-- food_picture
CREATE TABLE IF NOT EXISTS food_picture(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    uploader_id VARCHAR(255) NOT NULL,
    food_id VARCHAR(255) NOT NULL,
    picture VARCHAR(2083) NOT NULL DEFAULT '',
    picture_md5 VARCHAR(128) NOT NULL DEFAULT '',
    UNIQUE KEY food_picture(food_id, picture_md5)
);

-- attraction_food
CREATE TABLE IF NOT EXISTS attraction_food(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    attraction_id VARCHAR(255) NOT NULL,
    food_id VARCHAR(255) NOT NULL,
    UNIQUE KEY attraction_food(attraction_id, food_id)
);
