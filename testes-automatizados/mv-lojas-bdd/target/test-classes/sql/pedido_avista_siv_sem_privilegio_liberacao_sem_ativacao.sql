DECLARE
  
BEGIN
	
delete from MV_USUARIO_X_PRIVILEGIO where COD_PRIVILEGIO = 'LIBERARPEDIDOSEMATIVACAO' and NRO_USUARIO = 6699;

END;