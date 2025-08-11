
CREATE TABLE emails (
    id BIGINT NOT NULL,
    create_at TIMESTAMP(6) NOT NULL,
    email_from VARCHAR(255),
    email_to VARCHAR(255),
    subject VARCHAR(255),
    content TEXT,
    status VARCHAR(255),
    retry_num INT,
    last_try_at TIMESTAMP(6)
) PARTITION BY RANGE (create_at);



