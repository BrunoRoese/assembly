CREATE TABLE IF NOT EXISTS issue (
    id SERIAL,
    title VARCHAR(100),
    creation_time TIMESTAMP DEFAULT now(),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS voting_session (
    id SERIAL,
    expiration_time TIMESTAMP,
    issue_id INTEGER,
    PRIMARY KEY (id),
    CONSTRAINT fk_issue FOREIGN KEY(issue_id) REFERENCES issue(id)
);

CREATE TABLE IF NOT EXISTS vote (
    id SERIAL,
    user_id INTEGER,
    voting_session_id INTEGER,
    PRIMARY KEY (id),
    CONSTRAINT fk_voting_session FOREIGN KEY(voting_session_id) REFERENCES voting_session(id)
);

INSERT INTO issue (title) VALUES ('Issue 1');
INSERT INTO issue (title) VALUES ('Issue 2');
INSERT INTO issue (title) VALUES ('Issue 3');

INSERT INTO voting_session (expiration_time, issue_id) VALUES ('2024-05-01 12:00:00', 1);
INSERT INTO voting_session (expiration_time, issue_id) VALUES ('2024-05-02 12:00:00', 2);
INSERT INTO voting_session (expiration_time, issue_id) VALUES ('2024-05-03 12:00:00', 3);

INSERT INTO vote (user_id, voting_session_id) VALUES (1, 1);
INSERT INTO vote (user_id, voting_session_id) VALUES (2, 1);
INSERT INTO vote (user_id, voting_session_id) VALUES (3, 2);