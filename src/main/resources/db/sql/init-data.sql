CREATE TYPE vote_status AS ENUM ('yes', 'no');

CREATE TABLE IF NOT EXISTS issue (
    id UUID,
    title VARCHAR(100) NOT NULL,
    creation_time TIMESTAMP NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS voting_session (
    id UUID,
    expiration_time TIMESTAMP NOT NULL,
    issue_id UUID,
    PRIMARY KEY (id),
    CONSTRAINT fk_issue FOREIGN KEY(issue_id) REFERENCES issue(id)
);

CREATE TABLE IF NOT EXISTS vote (
    id UUID,
    user_id INTEGER NOT NULL,
    vote_status vote_status NOT NULL,
    voting_session_id UUID,
    PRIMARY KEY (id),
    CONSTRAINT fk_voting_session FOREIGN KEY(voting_session_id) REFERENCES voting_session(id)
);

INSERT INTO issue (id, title, creation_time) VALUES (gen_random_uuid(), 'Issue 1', now());
INSERT INTO issue (id, title, creation_time) VALUES (gen_random_uuid(), 'Issue 2', now());
INSERT INTO issue (id, title, creation_time) VALUES (gen_random_uuid(), 'Issue 3', now());

INSERT INTO voting_session (id, expiration_time, issue_id) VALUES (gen_random_uuid(), '2024-05-01 12:00:00', (SELECT id FROM issue WHERE title = 'Issue 1'));
INSERT INTO voting_session (id, expiration_time, issue_id) VALUES (gen_random_uuid(), '2024-05-02 12:00:00', (SELECT id FROM issue WHERE title = 'Issue 2'));
INSERT INTO voting_session (id, expiration_time, issue_id) VALUES (gen_random_uuid(), '2024-05-03 12:00:00', (SELECT id FROM issue WHERE title = 'Issue 3'));

INSERT INTO vote (id, user_id, vote_status, voting_session_id) VALUES (gen_random_uuid(), 1, 'yes', (SELECT id FROM voting_session WHERE id = (SELECT id FROM voting_session WHERE issue_id = (SELECT id FROM issue WHERE title = 'Issue 1'))));
INSERT INTO vote (id, user_id, vote_status, voting_session_id) VALUES (gen_random_uuid(), 2, 'yes', (SELECT id FROM voting_session WHERE id = (SELECT id FROM voting_session WHERE issue_id = (SELECT id FROM issue WHERE title = 'Issue 1'))));
INSERT INTO vote (id, user_id, vote_status, voting_session_id) VALUES (gen_random_uuid(), 3, 'yes', (SELECT id FROM voting_session WHERE id = (SELECT id FROM voting_session WHERE issue_id = (SELECT id FROM issue WHERE title = 'Issue 2'))));