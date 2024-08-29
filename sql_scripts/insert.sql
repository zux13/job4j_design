INSERT INTO roles (role_name) 
VALUES 
    ('Admin'), 
    ('User'), 
    ('Guest');

INSERT INTO rules (rule_name) 
VALUES 
    ('Create Item'), 
    ('Edit Item'), 
    ('Delete Item'), 
    ('View Item'), 
    ('Manage Users');

INSERT INTO roles_rules (role_id, rule_id) 
VALUES 
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5);

INSERT INTO roles_rules (role_id, rule_id) 
VALUES 
    (2, 1),
    (2, 4);

INSERT INTO roles_rules (role_id, rule_id) 
VALUES 
    (3, 4);

INSERT INTO users (user_name, role_id) 
VALUES 
    ('Alice', 1),
    ('Bob', 2),
    ('Charlie', 3);

INSERT INTO categories (category_name) 
VALUES 
    ('Bug'), 
    ('Feature Request'), 
    ('Support');

INSERT INTO states (state_name) 
VALUES 
    ('Open'), 
    ('In Progress'), 
    ('Closed');

INSERT INTO items (item_name, user_id, category_id, state_id) 
VALUES 
    ('Fix login bug', 1, 1, 1),
    ('Add dark mode', 2, 2, 2),
    ('Support for API integration', 2, 3, 1);

INSERT INTO comments (comment, item_id) 
VALUES 
    ('This bug occurs during login', 1), 
    ('Dark mode is a requested feature for a while', 2), 
    ('We need help with API integration', 3);

INSERT INTO attachs (attachment, item_id) 
VALUES 
    ('screenshot_login_bug.png', 1), 
    ('design_proposal_dark_mode.pdf', 2), 
    ('api_docs.zip', 3);
