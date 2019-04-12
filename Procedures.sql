create or replace function calMatchHalf
return varchar as
half varchar(1);
begin 
	if to_number(to_char(sysdate,'HH24'))<=12 then 
		half:='F';
	else
		half:='S';
	end if;
	return half;
end ;
/
		
------------------------------------------------------------------
SELECT calMatchHalf from dual;
------------------------------------------------------------------


create or replace procedure getDay(date1 in date,day out varchar) as
	begin 
		select to_char(date1,'DY') into day from dual;
	end;
	/
-------------------------------------------------------------------
declare 
day varchar(3);
begin 
getDay(sysdate,day);
dbms_output.put_line(day);
end;
/
--------------------------------------------------------------------S
