drop table if exists EMPLOYEE;

-- CREATETABLE
CREATE TABLE "EMPLOYEE"
(
    "ID"     int auto_increment primary key,
    "NAME"   TEXT NOT NULL,
    "PHONE"  TEXT NOT NULL,
    "EMAIL"  TEXT NOT NULL,
    "BRANCH" TEXT NOT NULL
);