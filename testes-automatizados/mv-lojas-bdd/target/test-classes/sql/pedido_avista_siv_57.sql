DECLARE
  
BEGIN
	
update
(select * from mv_npromocao where nro_promocao in (40580))
set flag_condicional_serv_obr = 1;

delete from MV_NPROMOCAO_X_SERVICO where nro_promocao = 40580;

END;