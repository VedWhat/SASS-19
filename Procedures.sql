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
