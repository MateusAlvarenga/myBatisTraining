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
    "AGE"    int NOT NULL,
    CONSTRAINT "CHK_EMPLOYEE_AGE" CHECK ("AGE" >= 18)
);

-- CREATETABLE
CREATE TABLE "JOBTITLE"
(
    "ID"   int auto_increment primary key,
    "NAME" TEXT NOT NULL
);

--ALTER TABLE "EMPLOYEE" ADD FOREIGN KEY ("JOBTITLE") REFERENCES "JOBTITLE" ("ID");