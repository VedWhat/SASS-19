1. --List students of year 1 in rel_factor order 
select * from student where cyear=1 order by (rel_factor) desc ;

2. --List names of students that play Tennis
select s.name from student s natural join plays a, sport where a.sid=sport.sid and sport.name='Tennis';

3. --List names of all students with sec id and year that have 
   --monday mornings free
with free as 
    (select sec_id, cyear from section natural join timeslot where mon='S')
select name, sec_id, cyear from student s 
    where (sec_id, cyear) 
        in (select sec_id, cyear from free);
    
4. --List details of all sports that can be played at Marena

select * from Sport s 
    where sid in 
    (select sid from offered_by natural join arena where location='Marena');

5.  --List student(s) that play(s) maximum number of sports

with list1 as (select name, count(SID) ct 
    from student natural join plays group by name)
select name, ct from list1 where ct=(select max(ct) from list1); 

6. --Find average skill (all sports) per section

    select sec_id, cyear, avg(skill)
        from student s natural join plays p 
        group by (sec_id, cyear);

/?7. --List all sports with the reg_no of the person(s) having highest skill in that sport
    with sportskill as (
        select sid, max(skill) max_skill from plays group by sid order by sid
    )
    select s.name, p.reg_no from sport s, plays p 
    where (p.sid, p.reg_no) in (
        select t.sid, t.reg_no from plays, max_skill where t.skill=max_skill);

8. --List all students who can play tennis with ved on friday
    with vedfree as (
        select FRI vfri from student natural join section natural join timeslot 
        where name='Ved' 
    )
    select name, reg_no 
    from student natural join section natural join timeslot, vedfree 
    where FRI=vfri;

9. --Find the name of the person who plays football best
    with maxskills as(
    select max(skill) max_skill from plays group by sid having sid=2)
    select p.name, max_skill from student p natural join plays, maxskills where skill=max_skill;  

10. --select all studets of section CSE-B that play 'Tennis'

    select p.name, reg_no from student p natural join plays where sid=2 and sec_id='CSE-B'; 



 
        
    (select reg_no, name, cyear, skill
    from student natural join section natural join timeslot natural join plays
    where MON='F' and sid = 2) minus (select reg_no, name, cyear, skill from student natural join section natural join timeslot natural join plays where reg_no = 1)
    
    
    
  12. --Adding to match 
   insert into match values((select to_number(to_char(sysdate,'YYHHMMSS')) from dual),sid,aid,sysdate,result);
