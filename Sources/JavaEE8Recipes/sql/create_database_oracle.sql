/*
 * Create the database for the JavaEERecipes application prior to deploying.
 * 
 * This script should be used to create a new Oracle database schema for the
 * bookstore application.
 *
 * Database Name: acme (may refer to it as acmedb within JNDI connections)
 * Database User: acme
 *
 * Once the database has been created, execute the following script to created
 * the objects and populate the tables.
 */
/**
 * Author:  Juneau
 * Created: Oct 14, 2017
 */
create table author_work(
id          number,
author_id   number,
book_id     number,
PRIMARY KEY (ID));

create table book (
id          number,
title       varchar2(150),
image       varchar2(500),
num_chapters number,
num_pages       number,
description     varchar2(2000),
publish_date    date,
PRIMARY KEY (ID)
);

create table book_author (
id              number,
lastname        varchar2(150),
firstname       varchar2(150),
bio             varchar2(2500),
PRIMARY KEY (ID));

create table book_category (
id              number,
store_id        number,
name            varchar2(150),
PRIMARY KEY (ID)
);

create table book_store (
id              number,
name            varchar2(150),
location_city    varchar2(150),
location_state  varchar2(150),
PRIMARY KEY (ID));

create table chapter (
id              number,
chapter_number  number,
title           varchar2(500),
description     varchar2(2500),
book_id         number,
PRIMARY KEY (ID));

create table contact (
id              number,
firstname       varchar2(150),
lastname        varchar2(150),
password        varchar2(30),
email           varchar2(150),
decription      varchar2(2500),
occupation      varchar2(150),
receivenotifications varchar2(1),
gender          varchar2(1),
PRIMARY KEY (ID));

create table employee (
id              number,
firstname           varchar2(150),
lastname            varchar2(150),
age             number,
job_id          number,
status          number,
PRIMARY KEY (ID)
);

create table id_category (
genre           varchar2(150),
description     varchar2(2500));

create table jobs (
job_id              number,
title           varchar2(150),
division        varchar2(150),
salary          number,
PRIMARY KEY (JOB_ID)
);





ALTER TABLE CHAPTER ADD CONSTRAINT FK_CHAPTER_BOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOK (ID);
ALTER TABLE BOOK_CATEGORY ADD CONSTRAINT FK_BOOK_CATEGORY_STORE_ID FOREIGN KEY (STORE_ID) REFERENCES BOOK_STORE (ID);
ALTER TABLE AUTHOR_WORK ADD CONSTRAINT FK_AUTHOR_WORK_AUTHOR_ID FOREIGN KEY (AUTHOR_ID) REFERENCES BOOK_AUTHOR (ID);
ALTER TABLE AUTHOR_WORK ADD CONSTRAINT FK_AUTHOR_WORK_BOOK_ID FOREIGN KEY (BOOK_ID) REFERENCES BOOK (ID);
create sequence author_work_s
start with 1
increment by 1;

create sequence book_s
start with 1
increment by 1;

create sequence book_author_s
start with 1
increment by 1;

create sequence book_category_s
start with 1
increment by 1;

create sequence book_store_s
start with 1
increment by 1;

create sequence chapter_s
start with 1
increment by 1;

create sequence contact_s
start with 1
increment by 1;

create sequence employee_s
start with 1
increment by 1;

create sequence jobs_s
start with 1
increment by 1;

-- Insert some data
insert into book_author values(
 book_author_s.nextval,
 'Author of Apress Books',
 'Josh',
 'Juneau');

insert into book_author values(
 book_author_s.nextval,
 'JavaFX Guru',
 'Carl',
 'Dea');

insert into book_author values(
 book_author_s.nextval,
 'Apress Editor',
 'Jonathan',
 'Gennick');

insert into book (ID, DESCRIPTION, IMAGE, TITLE, NUM_CHAPTERS, NUM_PAGES, PUBLISH_DATE) values(
 book_s.nextval,
 'Tips and Techniques for Beginner, Intermediate, or Advanced Developers for Java 9.',
 '',
 'Java 9 Recipes',
20,
700,
to_date('2017-01-01', 'YYYY-MM-DD'));

insert into book (ID, DESCRIPTION, IMAGE, TITLE, NUM_CHAPTERS, NUM_PAGES, PUBLISH_DATE) values(
  book_s.nextval,
 'Java EE 8 Recipes takes an example-based approach in showing how to program Enterprise Java applications for many different scenarios, and using the very latest in frameworks and technologies that are available in the Java EE 7 platform.',
 '',
 'Java EE 8 Recipes',
18,
750,
to_date('2018-05-01', 'YYYY-MM-DD'));
