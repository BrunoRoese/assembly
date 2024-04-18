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