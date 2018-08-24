
select * from mv_produto where cod_nro_produto = 139756;

-- nro_produto : flag_saida_estoque
-- 219758 : 1

select * from mv_info_estoque where nro_produto = 139757 and nro_loja = 1101;

-- nro_produto : flag_saida_estoque no banco atual - no teste
-- 001: A: 215275: 1 - 1 | B: 187575: 0 - 0
-- 002: 187394: 0 - 1 |  95377: 0 - 0
-- 003: 193958: 1 - 1 | 196097: 1 - 1 | 156364: 1 - 1
-- 004: 139551: 1 - 0 | 187575: 0 - 0 | 139551: 1 - 0
-- 005: 208976: 1 - 1 
-- 006: 214974: 1 - 0 | 
-- 007: 187575: 0 - 0 | 219655: 1 - 1
-- 008: 188776: 0 - 0 | 95381: 0 - 0
-- 009: 219655: 1 - 1 | 201793: 1 - 1
-- 010: 139551: 1 - 0 | 187575: 0 - 0 | 139551: 1 - 0
-- 011: 144367: 1 - 1 | 189138: 1 - 1
-- 012: 219255: 0 - 1 | 189616: 1 - 1
-- 013: 219255: 0 - 1 | 189616: 1 - 1
-- 014: 215276: 1 - 1 | 95377: 0 - 0 | 215276: 1 - 1
-- 015: 219655: 1 - 1 | 189138: 1 - 1
-- 016: 139551: 1 - 0 | 187575: 0 - 0 | 
-- 017: 208976: 1 - 1 | 188776: 0 - 0 | 95377: 0 - 0
-- 018: 214974: 1 - 0 | 139551: 1 - 0 | 219758: 1 - 1
-- 019: 139757: 1 - 1 | 219758: 1 - 0 | 219758: 1 - 0

-- 020: 201793: 1 - 1 | 187575: 0 - 0 | 219655: 1 - 1
-- 021: 201793: 1 - 1 | 187575: 0 - 0 | 219655: 1 - 1 

-- 022: 139757: 1 - 1 | 219758: 1 - 0 | 219758: 1 - 0
-- 023: 139757: 1 - 1 | 219758: 1 - 0 | 219758: 1 - 0

-- 024: 201793: 1 - 1 | 187575: 0 - 0 | 219655: 1 - 1
-- 025: 219655: 1 - 1 | 219655: 1 - 1 | 219655: 1 - 0 | 139757: 1 - 1
-- 026: 187575: 0 - 0 | 187575: 0 - 1 | 187575: 0 - 0 | 187575: 0 - 1
-- 027: 139757: 1 - 1 | 219758: 1 - 0 | 139551: 1 - 0 | 219758: 1 - 1
-- 028: 187575: 0 - 0 | 219655: 1 - 1 | 201793: 1 - 1 | 187575: 0 - 0 | 219655: 1 - 0 | 201793: 1 - 0
-- 029: 187575: 0 - 0 | 219655: 1 - 1 | 201793: 1 - 1 | 187575: 0 - 0 | 219655: 1 - 0 | 201793: 1 - 0
-- 034: 51867: 1 - 1 | 
-- 035: 139757: 1 - 0 | 219758: 1 - 0 | 219758: 1 - 0
-- 036: 139757: 1 - 0
-- 037: 139757: 1 - 0

DECLARE 
  v_proximo number;
  id_vigencia_paramentro number;
  id_cliente number;
BEGIN

SELECT MAX(NRO_DATA_ENTREGA) INTO  v_proximo FROM MV_DATA_ENTREGA;
  FOR i IN 1..32 LOOP
    v_proximo := v_proximo+1;
    
    MERGE INTO MV_DATA_ENTREGA
      USING DUAL ON ((to_char(DATA, 'DD/MM/YY') = TRUNC(SYSDATE+i)) AND NRO_GRUPO_FAIXA_CEP = '74' AND ROWNUM = 1)
      WHEN NOT MATCHED THEN
        INSERT (NRO_DATA_ENTREGA, DATA, NRO_TURNO_ENTREGA, NRO_FAIXA_CEP, PESO_TOTAL, PESO_CONSUMIDO, VALOR_TOTAL, VALOR_CONSUMIDO, CUBAGEM_TOTAL, CUBAGEM_CONSUMIDA, QUANTIDADE_TOTAL, QUANTIDADE_CONSUMIDA, NRO_GRUPO_FAIXA_CEP, IND_ATIVO) VALUES (v_proximo, sysdate+i, '2', null, '3000', '0', '350000', '0', '19', '0', '35', '0', '74', '1')
      WHEN MATCHED THEN
        UPDATE SET NRO_TURNO_ENTREGA = '2', NRO_FAIXA_CEP = null, PESO_TOTAL = '3000', PESO_CONSUMIDO = '0', VALOR_TOTAL = '350000', VALOR_CONSUMIDO = '0', CUBAGEM_TOTAL = '19', CUBAGEM_CONSUMIDA = '0', QUANTIDADE_TOTAL = '35', QUANTIDADE_CONSUMIDA = '0', IND_ATIVO = '1';
  END LOOP;
  
END;