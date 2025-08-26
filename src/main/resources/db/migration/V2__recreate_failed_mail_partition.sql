DELETE FROM englearn.flyway_schema_history;
DROP TABLE IF EXISTS failed_mail CASCADE;

CREATE TABLE failed_mail (
     id BIGSERIaAL PRIMARY KEY,
     email_to VARCHAR(255),
     subject VARCHAR(255),
     body TEXT,
     retry_number INT,
     create_at TIMESTAMP NOT NULL,
     last_retry_time TIMESTAMP
) PARTITION BY RANGE (create_at);

CREATE TABLE failed_mail_2025_08 PARTITION OF failed_mail
    FOR VALUES FROM ('2025-08-01') TO ('2025-09-01');

CREATE TABLE failed_mail_2025_09 PARTITION OF failed_mail
    FOR VALUES FROM ('2025-09-01') TO ('2025-10-01');
