drop table if exists EMPLOYEE;
drop table if exists JOB_TITLE;

-- CREATETABLE
CREATE TABLE "EMPLOYEE"
(
    "ID"     int auto_increment primary key,
    "NAME"   TEXT NOT NULL,
    "PHONE"  TEXT NOT NULL,
    "EMAIL"  TEXT NOT NULL,
    "BRANCH" TEXT NOT NULL,
    "JOB_TITLE" int NULL
);

-- CREATETABLE
CREATE TABLE "JOB_TITLE"
(
    "ID"   int auto_increment primary key,
    "NAME" TEXT NOT NULL
);

ALTER TABLE "EMPLOYEE" ADD FOREIGN KEY ("JOB_TITLE") REFERENCES "JOB_TITLE" ("ID");