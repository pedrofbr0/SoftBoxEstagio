update mv_nrosorte set nro_pedido = null, nro_loja = null, nro_servico = null, nro_exporta = null where (nro_pedido, nro_loja) in (select s.nro_pedido, s.nro_loja
  from (SELECT sor.nro_pedido, sor.nro_loja
          FROM MV_NROSORTE SOR                 
         WHERE (SOR.NRO_IMPORTA, SOR.NRO_SERIE) IN (
                 SELECT MNSS.NRO_IMPORTA, MNSS.NRO_SERIE
                   FROM MV_NROSORTE_SERIE MNSS
                        INNER JOIN MV_SERVICO_X_NROSORTE_SERIE MSXNSS ON MSXNSS.NRO_IMPORTA = MNSS.NRO_IMPORTA
                               AND MSXNSS.NRO_SERIE = MNSS.NRO_SERIE
                        INNER JOIN MV_SERVICO SERV ON SERV.NRO_SERVICO = MSXNSS.NRO_SERVICO
                              AND SERV.NRO_servico = 307075
                        INNER JOIN MV_NROSORTE_IMPORTA MNSI ON MNSI.NRO_IMPORTA = MNSS.NRO_IMPORTA
                               AND MNSI.NRO_EMPRESA = 5
                  WHERE TRUNC(SYSDATE) BETWEEN MNSS.DT_VALIDADE_DE AND MNSS.DT_VALIDADE_ATE
               )
           AND SOR.NRO_PEDIDO IS not NULL
           order by sor.dt_alteracao asc
          ) s where rownum <= 50);
