DECLARE
  
BEGIN
	
update
(select * from mv_npromocao where nro_promocao in (40580))
set flag_condicional_serv_obr = 1;

delete from MV_NPROMOCAO_X_SERVICO where nro_promocao = 40580;

INSERT INTO MV_NPROMOCAO_X_SERVICO
(NRO_PROMOCAO, NRO_SERVICO, DT_INCLUSAO, DT_ALTERACAO)
VALUES(40580,  153414, TIMESTAMP '2017-10-03 10:50:48.000000', TIMESTAMP '2017-10-03 10:50:48.000000');

END;