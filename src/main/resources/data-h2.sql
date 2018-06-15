INSERT INTO Tag VALUES (7,'Hibernate');
INSERT INTO Tag VALUES	(1,'Java');
INSERT INTO Tag VALUES	(6,'JPA');
INSERT INTO Tag VALUES	(2,'Spring');
INSERT INTO Tag VALUES	(3,'Spring Boot');
INSERT INTO Tag VALUES	(4,'Spring MVC');

INSERT INTO Course VALUES	(1,'John Thompson','2017-08-01','Java Spring Boot','Spring Framework 5: Beginner to Guru','https://www.udemy.com/spring-framework-5-beginner-to-guru/');
INSERT INTO Course VALUES	(2,'Esteban Herrera','2018-04-01','Spring WebFlux','Spring WebFlux: Getting Started','https://app.pluralsight.com/library/courses/getting-started-spring-webflux');
INSERT INTO Course VALUES	(3,'Bryan Hansen','2013-04-10','Spring JDBC with JPA/Hibernate','Spring with JPA and Hibernate','https://app.pluralsight.com/library/courses/spring-jpa-hibernate/');

INSERT INTO Course_tags VALUES (1,1);
INSERT INTO Course_tags VALUES (2,1);
INSERT INTO Course_tags VALUES (3,1);
INSERT INTO Course_tags VALUES (1,2);
INSERT INTO Course_tags VALUES (2,2);
INSERT INTO Course_tags VALUES (3,2);
INSERT INTO Course_tags VALUES (1,3);
INSERT INTO Course_tags VALUES (1,4);
INSERT INTO Course_tags VALUES (3,6);
INSERT INTO Course_tags VALUES (3,7);

INSERT INTO My_Course VALUES (1,'0','old course, 5h long',3);
INSERT INTO My_Course VALUES (2,'0','All MVC content uses Spring Boot, intro''s into: Docker & MongoDB are nice.',1);