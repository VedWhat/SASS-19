

insert into timeslot values(1,'F','S','N','S','N','S','A');
insert into timeslot values(2,'N','F','S','N','N','F','A');
insert into timeslot values(3,'F','S','N','S','N','F','A');
insert into timeslot values(4,'F','N','F','S','F','N','A');

insert into section values('CSE-A',1,2);
insert into section values('CSE-A',2,1);
insert into section values('CSE-A',3,4);
insert into section values('CSE-A',4,3);
insert into section values('CSE-B',1,3);
insert into section values('CSE-B',2,2);
insert into section values('CSE-B',3,2);
insert into section values('CSE-B',4,1);
insert into section values('CSE-C',1,3);
insert into section values('CSE-C',2,3);
insert into section values('CSE-C',3,2);
insert into section values('CSE-C',4,1);
insert into section values('IT-A',1,4);
insert into section values('IT-A',2,2);
insert into section values('IT-A',3,1);
insert into section values('IT-A',4,3);
insert into section values('IT-B',1,4);
insert into section values('IT-B',2,1);
insert into section values('IT-B',3,2);
insert into section values('IT-B',4,1);

insert into student values(1,'Ved','CSE-C',2,10,'Block 14','M','N');
insert into student values(3,'Chaitanya','CSE-C',2,10,'Block 14','M','N');
insert into student values(2,'Jack','CSE-B',2,2,'Block 11','M','N');
insert into student values(4,'Nandita','IT-A',2,8,'Block 22','F','N');
insert into student values(5,'Grace','IT-B',1,6,'Block 12','F','N');
insert into student values(6,'Pam','CSE-C',2,7,'Scranton','F','N');
insert into student values(7,'Jim','CSE-A',3,6,'Philly','M','N');
insert into student values(8,'Rob','IT-A',4,7,'Birmingham','M','N');
insert into student values(9,'Serena','CSE-A',1,6,'Denver','F','N');
insert into student values(10,'Roger','CSE-C',3,10,'Basel','M','N');

insert into Phone values(1,0987654321,0934534555,null);
insert into Phone values(2,0987654221,0944333567,null);
insert into Phone values(3,0987121211,0557777777,null);
insert into Phone values(4,0987613451,0137666644,null);
insert into Phone values(5,0987543461,1187653555,null);
insert into Phone values(6,0987343600,0387334535,null);
insert into Phone values(7,0944444444,0117334535,null);
insert into Phone values(8,0987343242,0287334535,null);
insert into Phone values(9,0987343244,0227334535,null);
insert into Phone values(10,0987342212,0447334535,null);



insert into Sport values(1,'Tennis',2,4,'Non marking shoes, racquet, ball');
insert into Sport values(2,'Football',6,22,'Shoes, ball');
insert into Sport values(3,'Cricket',6,22,'Bat, ball, kit');
insert into Sport values(4, 'Basketball', 6,22,'Shoes, ball');
insert into Sport values(5, 'Table Tennis', 2,4,'racquet, ball');

insert into plays values(1,1,10);
insert into plays values(1,3,2);
insert into plays values(1,2,6);
insert into plays values(3,1,1);
insert into plays values(4,1,1);
insert into plays values(5,4,2);
insert into plays values(4,2,1);
insert into plays values(2,2,5);
insert into plays values(2,3,6);
insert into plays values(3,3,2);
insert into plays values(4,5,1);
insert into plays values(5,2,3);
insert into plays values(6,3,1);
insert into plays values(7,2,8);
insert into plays values(8,3,5);
insert into plays values(9,5,3);
insert into plays values(10,5,7);
insert into plays values(10,1,10);
insert into plays values(9,3,7);

insert into Arena values(1,'KMC',0987651111,'KMC STUDENT');
insert into Arena values(2,'MIT',0987699321,'MIT STUDENT');
insert into Arena values(3,'End point',0900154321,'OPEN:6AM-8AM|4PM-7PM');
insert into Arena values(4, 'Marena', 09555568571, 'PAID MEMBERSHIP');

insert into offered_by values(1,1);
insert into offered_by values(1,2);
insert into offered_by values(1,4);
insert into offered_by values(2,2);
insert into offered_by values(5,4);
insert into offered_by values(5,2);
insert into offered_by values(4,4);
insert into offered_by values(5,1);
insert into offered_by values(3,2);
insert into offered_by values(3,4);
insert into offered_by values(2,3);
insert into offered_by values(2,4);
insert into offered_by values(3, 3);


insert into Login values(1,'Ved');
insert into Login values(2,'i9koi');
insert into Login values(3,'Jack');
insert into Login values(4,'Nandita');
insert into Login values(5,'Grace');
insert into Login values(6,'Pam');
insert into Login values(7,'Jim');
insert into Login values(8,'Rob');
insert into Login values(9,'Serena');
insert into Login values(10,'Roger');
