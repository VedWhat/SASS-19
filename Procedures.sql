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


create or replace procedure getDay(day out varchar) as
	begin 
		select to_char(sysdate,'DY') into day from dual;
	end;
	/
-------------------------------------------------------------------
declare 
day varchar(3);
begin 
getDay(day);
dbms_output.put_line(day);
end;
/
--------------------------------------------------------------------S
			     
			     
create or replace procedure getMatchID(mid out match.mid%type) as
	begin 
		 select (to_number(to_char(sysdate,'YYHHMMSS'))) into mid from dual;
	end;
	/
---------------------------------------------------------------------
declare 
mid number(9);
begin 
getMatchID(mid);
dbms_output.put_line(mid);
end;
/
---------------------------------------------------------------------------
