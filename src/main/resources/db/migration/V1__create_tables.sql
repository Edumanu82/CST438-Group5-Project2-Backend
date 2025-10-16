-- ================================
-- Flyway Migration: Initial Schema
-- ================================

-- Drop tables if re-running locally (optional; remove in production)
DROP TABLE IF EXISTS user_words;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS words;

-- ================================
-- USERS TABLE
-- ================================
CREATE TABLE users (
    userid BIGINT AUTO_INCREMENT PRIMARY KEY,
    oauth_provider VARCHAR(255),
    oauth_prov_id VARCHAR(255),
    username VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ================================
-- WORDS TABLE
-- ================================
CREATE TABLE words (
    word_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    word VARCHAR(255) NOT NULL UNIQUE,
    definition TEXT,
    part_of_speech VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ================================
-- USER_WORDS TABLE (JOIN TABLE)
-- ================================
CREATE TABLE user_words (
    user_word_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    userid BIGINT NOT NULL,
    word_id BIGINT NOT NULL,
    status VARCHAR(50),
    times_reviewed INT DEFAULT 0,
    last_reviewed DATETIME,

    CONSTRAINT fk_userword_user FOREIGN KEY (userid)
        REFERENCES users(userid)
        ON DELETE CASCADE,

    CONSTRAINT fk_userword_word FOREIGN KEY (word_id)
        REFERENCES words(word_id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
