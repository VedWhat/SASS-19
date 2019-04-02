

insert into timeslot values(1,'F','S','F','S','F','S','F');
insert into timeslot values(2,'S','F','S','F','S','F','S');
insert into timeslot values(3,'S','S','S','S','S','S','S');
insert into timeslot values(4,'F','F','F','F','F','F','F');

insert into section values('CSE-A',3,1);
insert into section values('CSE-B',2,2);
insert into section values('CSE-C',3,3);
insert into section values('IT-A',4,4);
insert into section values('IT-B',1,3);
insert into section values('CCE-B', 1, 3);
insert into section values('CSE-C', 1, 2);

insert into student values(1,'Ved','CSE-C',1,10,'ABC','M','N');
insert into student values(2,'Jack','CSE-B',2,2,'ABC1','M','N');
insert into student values(3,'Chai','CSE-C',1,5,'ABC2','M','N');
insert into student values(4,'Tanya','CSE-A',3,5,'ABC3','F','N');
insert into student values(5,'Bob','IT-B',1,6,'ABC4','M','N');
insert into student values(6,'Rob','IT-A',4,7,'ABC5','M','N');

insert into Phone values(1,0987654321,0987654321,null);
insert into Phone values(2,0987654321,0987654321,null);
insert into Phone values(3,0987654321,0987654321,null);
insert into Phone values(4,0987654321,0987654321,null);
insert into Phone values(5,0987654321,0987654321,null);
insert into Phone values(6,0987654321,0987654321,null);

insert into Sport values(1,'Tennis',2,4,'Non marking shoes, racquet, ball');
insert into Sport values(2,'Football',6,22,'Shoes');
insert into Sport values(3,'Cricket',6,22,'Bat, ball, kit');
insert into Sport values(4, 'Futsal', 6,22,'Shoes');
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

insert into Arena values(1,'KMC',0987654321,'KMC STUDENT');
insert into Arena values(2,'MIT',0987654321,'MIT STUDENT');
insert into Arena values(3,'TC',0987654321,null);
insert into Arena values(4, 'Marena', 09878968571, 'PAID MEMBERSHIP');

insert into offered_by values(1,1);
insert into offered_by values(1,2);
insert into offered_by values(1,4);
insert into offered_by values(2,2);
insert into offered_by values(4,4);
insert into offered_by values(3,2);
insert into offered_by values(3,4);

insert into Login values(1,'Ved');
insert into Login values(2,'Jack');
insert into Login values(3,'Chai');
insert into Login values(4,'Tanya');
insert into Login values(5,'Bob');
insert into Login values(6,'Rob');
