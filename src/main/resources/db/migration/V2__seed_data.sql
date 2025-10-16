-- ================================
-- Flyway Migration: Seed Data
-- ================================

-- Clear existing data (optional; remove in production)
DELETE FROM user_words;
DELETE FROM users;
DELETE FROM words;

-- ================================
-- USERS
-- ================================
INSERT INTO users (oauth_provider, oauth_prov_id, username)
VALUES
    ('google', 'g123', 'alana_h'),
    ('github', 'gh456', 'dev_dan'),
    ('google', 'g789', 'study_buddy');

-- ================================
-- WORDS
-- ================================
INSERT INTO words (word, definition, part_of_speech)
VALUES
    ('ephemeral', 'Lasting for a very short time.', 'adjective'),
    ('lucid', 'Expressed clearly; easy to understand.', 'adjective'),
    ('resilient', 'Able to withstand or recover quickly from difficult conditions.', 'adjective'),
    ('catalyst', 'A person or thing that precipitates an event or change.', 'noun'),
    ('eloquent', 'Fluent or persuasive in speaking or writing.', 'adjective'),
    ('meticulous', 'Showing great attention to detail; very careful and precise.', 'adjective'),
    ('serendipity', 'The occurrence of events by chance in a happy or beneficial way.', 'noun'),
    ('transient', 'Lasting only for a short time; impermanent.', 'adjective');

-- ================================
-- USER_WORDS
-- ================================
INSERT INTO user_words (userid, word_id, status, times_reviewed, last_reviewed)
VALUES
    -- alana_h
    (1, 1, 'learning', 2, '2025-10-10 15:30:00'),
    (1, 3, 'mastered', 5, '2025-10-12 12:00:00'),
    (1, 7, 'learning', 1, '2025-10-11 10:00:00'),

    -- dev_dan
    (2, 2, 'learning', 3, '2025-10-09 18:45:00'),
    (2, 4, 'review', 2, '2025-10-12 09:15:00'),

    -- study_buddy
    (3, 5, 'mastered', 4, '2025-10-13 08:00:00'),
    (3, 6, 'learning', 1, '2025-10-12 20:30:00'),
    (3, 8, 'review', 2, '2025-10-13 09:45:00');
