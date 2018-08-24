DECLARE
  
BEGIN
	
update     
(select * from mv_npromocao where nro_promocao in (40920))
set flag_condicional_serv_obr = 1;

END;