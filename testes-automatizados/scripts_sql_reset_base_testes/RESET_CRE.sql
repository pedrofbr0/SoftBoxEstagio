declare
  numero_cre number := 187229;
  numero_loja number := 1101;
begin

  update mv_cre set flag_processamento_cre = 0, nro_contagem = 0 where nro_cre = numero_cre and nro_loja = numero_loja;

  delete from mv_item_cre where nro_cre = numero_cre and nro_loja = numero_loja and nro_depto not in (36, 44, 37);

  update mv_item_cre set QTDE_ESTOQUE_INFORMADA = null, FLAG_PROCESSAMENTO_ITEM_CRE = 0 where nro_cre = numero_cre and nro_loja = numero_loja and nro_depto in (36, 44, 37);

end;