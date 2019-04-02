drop table assigned;
drop table match;
drop table offered_by;
drop table arena;
drop table plays;
drop table phone;
drop table sport;
drop table Student;
drop table section;
drop table timeslot;

create table TimeSlot(
	t_id number(5),
	Mon varchar(20),
	Tue varchar(20),
	Wed varchar(20),
	Thu varchar(20),
	Fri varchar(20),
	Sat varchar(20),
	Sun varchar(20),
	primary key(t_id)
);
create table Section(
	sec_id varchar (5)not null ,
	CYear number(1)not null,
	t_id number(4),
    primary key(sec_id, cyear),
	foreign key (t_id) references Timeslot on delete set null
);

create table Student  (
	reg_no number(10) ,
	name varchar(19) not null,
	Sec_id varchar(5) not null,
	CYear number(1) not null,
	Rel_Factor number(2) check (Rel_factor in(0,1,2,3,4,5,6,7,8,9,10)),
	address varchar(19),
	sex varchar(1) not null check (sex in ('M', 'F')),
	engaged varchar(1) check (engaged in('Y','N')),
	primary key (reg_no),
	foreign key (Sec_id, Cyear) references Section(sec_id, Cyear) on delete cascade
);

create table phone(
	reg_no number(10) primary key,
	Primary_no number(10) not null,
	Whatsapp number(10) not null,
	Secondary number(10),
	foreign key (reg_no) references Student on delete cascade 
);

create table Sport(
	SID number(5) not null,
	Name varchar(40) not null,
	Min_p number(5),
	Max_p number(5),
	S_req varchar(50),
	primary key(SID)
);

create table Plays(
	reg_no number(10),
	SID number(5),
	Skill number(5),
    primary key(reg_no, SID),
	foreign key (reg_no) references Student on delete cascade,
	foreign key (SID) references Sport on delete cascade
);

create table Arena(
 	AID number(5) primary key,
	Location varchar(10),
	APhone varchar(10),
	A_req varchar(20)
	);

create table offered_by(
	SID number(5),
	AID number(5),
	primary key(SID,AID),
	foreign key (AID) references Arena on delete cascade ,
	foreign key (SID) references Sport 
	);

create table match(
  	MID number(5) primary key,
  	SID number(5),
	AID number(5),
  	date_played timestamp,
  	Result varchar(50),
	foreign key (AID) references Arena ,
	foreign key (SID) references Sport
  );

create table assigned(
   	reg_no number(5),
   	MID number(5),
   	primary key(reg_no,MID),
	foreign key (MID) references Match,
	foreign key (reg_no) references Student
 );
 create table Login(
 	reg_no number(5)primary key,
 	pwd varchar(20)unique
 );
 