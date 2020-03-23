INSERT INTO user (id, password, name, admin) VALUES (1, 'admin', 'admin', TRUE);

INSERT INTO user (id, password, name) VALUES (2, 'pass', 'excepteur');
INSERT INTO user (id, password, name) VALUES (3, 'pass', 'occaecat');
INSERT INTO user (id, password, name) VALUES (4, 'pass', 'sint');

INSERT INTO category (id, name) VALUES (1, 'cupidatat');
INSERT INTO category (id, name) VALUES (2, 'non');

INSERT INTO post (id, author_id, text) VALUES (1, 2, 'lorem ipsum dolor sit amet');
INSERT INTO post (id, author_id, text) VALUES (2, 3, 'consectetur adipiscing elit');
INSERT INTO post (id, author_id, text) VALUES (3, 3, 'sed do eiusmod tempor incididunt ut labore et dolore magna aliqua');
INSERT INTO post (id, author_id, text) VALUES (4, 4, 'ut enim ad minim veniam');
INSERT INTO post (id, author_id, text) VALUES (5, 4, 'quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat');
INSERT INTO post (id, author_id, text) VALUES (6, 4, 'duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur');

INSERT INTO post_category (post_id, category_id) VALUES (1, 1);
INSERT INTO post_category (post_id, category_id) VALUES (1, 2);
INSERT INTO post_category (post_id, category_id) VALUES (2, 1);
INSERT INTO post_category (post_id, category_id) VALUES (2, 2);
INSERT INTO post_category (post_id, category_id) VALUES (3, 1);
INSERT INTO post_category (post_id, category_id) VALUES (3, 2);
INSERT INTO post_category (post_id, category_id) VALUES (4, 2);
INSERT INTO post_category (post_id, category_id) VALUES (5, 2);
INSERT INTO post_category (post_id, category_id) VALUES (6, 1);