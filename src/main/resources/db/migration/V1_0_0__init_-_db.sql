CREATE TABLE news (
    id BIGSERIAL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    thumbnail VARCHAR(255),
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    PRIMARY KEY (id)
);

CREATE INDEX news_idx_status ON news (status);

CREATE TABLE topic (
    id BIGSERIAL,
    name VARCHAR(50),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE topic_news (
    id BIGSERIAL,
    news_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_topic_news_idx_news_id
                      foreign key (news_id)
                      references news (id),
    CONSTRAINT fk_topic_news_idx_topic_id
                      foreign key (topic_id)
                      references topic (id)
);

CREATE INDEX topic_news_idx_news_topic ON topic_news (news_id, topic_id);

