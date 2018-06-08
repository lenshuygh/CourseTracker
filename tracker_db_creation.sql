create table Course (id integer not null auto_increment, author varchar(255), publishdate date, subjects varchar(255), title varchar(255), url varchar(255), primary key (id)) engine=InnoDB;
create table Course_tags (Course_id integer not null, tags_id integer not null, primary key (Course_id, tags_id)) engine=InnoDB;
create table MyCourse (id integer not null auto_increment, completed bit, notes varchar(255), course_id integer, primary key (id)) engine=InnoDB;
create table Tag (id integer not null auto_increment, tagName varchar(255), primary key (id)) engine=InnoDB;
alter table Tag add constraint UKmq3hkoplvo1e6sfugpqjb5opf unique (tagName);
alter table Course_tags add constraint FKr09whggd3c8m96n0cp6lrrkkc foreign key (tags_id) references Tag (id);
alter table Course_tags add constraint FK7lktsn73lrn9yx6wt1b7x67jf foreign key (Course_id) references Course (id);
alter table MyCourse add constraint FKkirxmp78qx7jpm6wcl6hc22pn foreign key (course_id) references Course (id);
