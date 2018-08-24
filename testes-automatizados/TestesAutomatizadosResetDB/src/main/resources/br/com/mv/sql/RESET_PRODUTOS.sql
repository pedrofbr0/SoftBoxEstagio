DECLARE
  v_proximo number;
  id_vigencia_paramentro number;
  id_cliente number;
BEGIN

  SELECT MAX(NRO_DATA_ENTREGA) INTO  v_proximo FROM MV_DATA_ENTREGA;
  
END;