CREATE TABLE IF NOT EXISTS contact (
    id                  INT(255) NOT NULL,
    firstName           VARCHAR(128),
    lastName            VARCHAR(128),
    email               VARCHAR(128),
    phone               VARCHAR(128),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS directory (
    id                  INT(255) NOT NULL,
    loginId             VARCHAR(128),
    jobTitle            VARCHAR(128),
    department          VARCHAR(128),
    company             VARCHAR(128),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS location (
    id                  INT(255) NOT NULL,
    address             VARCHAR(128),
    city                VARCHAR(128),
    country             VARCHAR(128),
    timezone            VARCHAR(128),
    language            VARCHAR(128),
    PRIMARY KEY (id)
);