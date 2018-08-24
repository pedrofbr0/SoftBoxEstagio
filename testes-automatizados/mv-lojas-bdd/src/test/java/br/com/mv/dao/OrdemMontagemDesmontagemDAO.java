package br.com.mv.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.mv.jdbc.Conexao;
import br.com.mv.model.Carga;
import br.com.mv.model.OrdemMontagemDesmontagem;

public class OrdemMontagemDesmontagemDAO {

	Connection connection;
	
	public OrdemMontagemDesmontagem getProdutoPossuiOrdemMontagem(int nroLoja) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT " + 
				"     PRD.NRO_PRODUTO AS NRO_PRODUTO, " + 
				"     PRD.COD_NRO_PRODUTO AS COD_NRO_PRODUTO, " + 
				"     PRD.DESC_PRODUTO AS PRODUTO, " + 
				"     PRD.DT_INCLUSAO AS DATA_CADASTRO, " + 
				"     EP.NRO_LOJA AS NRO_LOJA, " + 
				"     ( " + 
				"        SELECT " + 
				"           L.DESC_LOJA AS LOJA  " + 
				"        FROM " + 
				"           MV_LOJA L  " + 
				"        WHERE " + 
				"           EP.NRO_LOJA = L.NRO_LOJA " + 
				"     )LOJA, " + 
				"     ( " + 
				"        SELECT " + 
				"           MIN(OM.NRO_ORDEM_MONTAGEM)  " + 
				"        FROM " + 
				"           MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"           INNER JOIN " + 
				"              TAB_ORDEM_MONTAGEM OM  " + 
				"              ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"           INNER JOIN " + 
				"              MV_PRODUTO PRD  " + 
				"              ON PRD.NRO_PRODUTO = IOM.NRO_PRODUTO  " + 
				"              AND OM.NRO_TIPO_ORDEM IN(3,4) " + 
				"        WHERE " + 
				"           IOM.NRO_PRODUTO = EP.NRO_PRODUTO  " + 
				"           AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"           AND OM.NRO_STATUS IN(1,3) " + 
				"           AND(OM.DT_PREVISAO,OM.NRO_ORDEM_MONTAGEM)IN( " + 
				"                                                          SELECT " + 
				"                                                             MAX (OM.DT_PREVISAO), " + 
				"                                                             MAX(OM.NRO_ORDEM_MONTAGEM)  " + 
				"                                                          FROM " + 
				"                                                             MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"                                                             INNER JOIN " + 
				"                                                                TAB_ORDEM_MONTAGEM OM  " + 
				"                                                                ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                                                                AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"                                                          WHERE " + 
				"                                                             IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"                                                             AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"                                                             AND OM.NRO_STATUS NOT IN (2,4) " + 
				"                                                                    " + 
				"                                                       ) " + 
				"      )NRO_ORDEM  " + 
				"  FROM " + 
				"     MV_PRODUTO PRD  " + 
				"     INNER JOIN " + 
				"        MV_GRUPO_AGREGADO GA  " + 
				"        ON GA.NRO_GRUPO_AGREGADO = PRD.NRO_GRUPO_AGREGADO  " + 
				"     INNER JOIN " + 
				"        MV_ITEM_GRUPO_AGREGADO ITGA  " + 
				"        ON ITGA.NRO_GRUPO_AGREGADO = GA.NRO_GRUPO_AGREGADO  " + 
				"     INNER JOIN " + 
				"        MV_SERVICO SV  " + 
				"        ON SV.NRO_SERVICO = ITGA.NRO_SERVICO  " + 
				"     INNER JOIN " + 
				"        MV_TIPO_SERVICO_X_PARAM_CONFIG SVPC  " + 
				"        ON SVPC.NRO_TIPO_SERVICO = SV.NRO_TIPO_SERVICO  " + 
				"     INNER JOIN " + 
				"        MV_PARAMETRO_CONFIG PC  " + 
				"        ON PC.NRO_PARAMETRO = SVPC.NRO_PARAMETRO  " + 
				"        AND PC.CHAVE_PARAMETRO_CONFIG = 'NRO_TIPOS_SERVICOS_MONTAGEM'  " + 
				"     INNER JOIN " + 
				"        MV_ESTOQUE_PRODUTO EP  " + 
				"        ON EP.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"        AND EP.NRO_GRUPO_ESTOQUE = 1  " + 
				"  WHERE " + 
				"     EP.NRO_LOJA = EP.NRO_LOJA  " + 
				"     AND NVL(PRD.COD_SITUACAO_PRODUTO, 0) != 'EX'  " + 
				"     AND( " + 
				"          (SELECT " + 
				"                   NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"                FROM " + 
				"                   MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"                   INNER JOIN " + 
				"                      TAB_ORDEM_MONTAGEM OM  " + 
				"                      ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                      AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"                WHERE " + 
				"                   IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"                   AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"                   AND OM.NRO_STATUS IN (1,3)) = 1  " + 
				"       ) " + 
				"     AND EXISTS  " + 
				"     ( " + 
				"        SELECT " + 
				"           NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"        FROM " + 
				"           MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"           INNER JOIN " + 
				"              TAB_ORDEM_MONTAGEM OM  " + 
				"              ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"              AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"        WHERE " + 
				"           IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"           AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"           AND OM.NRO_STATUS NOT IN(2,4) " + 
				"     ) " + 
				"           AND EXISTS( " + 
				"                  SELECT " + 
				"                     OMT.DT_PREVISAO, " + 
				"                     OMT.NRO_ORDEM_MONTAGEM  " + 
				"             FROM " + 
				"               MONT.TAB_ITEM_ORDEM_MONTAGEM IOMT  " + 
				"               INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OMT  " + 
				"               ON OMT.NRO_ORDEM_MONTAGEM = IOMT.NRO_ORDEM_MONTAGEM  " + 
				"               AND OMT.NRO_TIPO_ORDEM IN (3,4) " + 
				"             WHERE " + 
				"               IOMT.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"               AND OMT.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"               AND OMT.NRO_STATUS NOT IN (2,4) " + 
				"                      " + 
				"          ) " + 
				"                AND EP.NRO_LOJA = " + nroLoja +
				"				 AND rownum < 2";
		
		try {
			
			OrdemMontagemDesmontagem ordemMontagemDes = new OrdemMontagemDesmontagem();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ordemMontagemDes.setNroProduto(rs.getInt("NRO_PRODUTO"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return ordemMontagemDes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public OrdemMontagemDesmontagem getProdutoSemEstoqueEOrdemMontagem(int nroLoja) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT " + 
				"      PRD.NRO_PRODUTO     AS NRO_PRODUTO, " + 
				"      PRD.COD_NRO_PRODUTO AS COD_NRO_PRODUTO, " + 
				"      PRD.DESC_PRODUTO    AS PRODUTO, " + 
				"      PRD.DT_INCLUSAO     AS DATA_CADASTRO, " + 
				"      EP.NRO_LOJA         AS NRO_LOJA, " + 
				"       " + 
				"      (SELECT " + 
				"            L.DESC_LOJA AS LOJA  " + 
				"         FROM " + 
				"            MV_LOJA L  " + 
				"         WHERE " + 
				"            EP.NRO_LOJA = L.NRO_LOJA)LOJA, " + 
				"       " + 
				"      (SELECT " + 
				"            NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"                   ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                    AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS IN(1,3)) AS TEM_OM, " + 
				"       " + 
				"      (EP.QTDE_ESTOQUE - EP.QTDE_EMPENHADA) as TEM_ESTOQUE, " + 
				"       " + 
				"      (SELECT " + 
				"            MIN(OM.NRO_ORDEM_MONTAGEM)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"               ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"            INNER JOIN " + 
				"               MV_PRODUTO PRD  " + 
				"               ON PRD.NRO_PRODUTO = IOM.NRO_PRODUTO  " + 
				"               AND OM.NRO_TIPO_ORDEM IN(3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = EP.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS IN(1,3) " + 
				"            AND(OM.DT_PREVISAO,OM.NRO_ORDEM_MONTAGEM)IN(SELECT " + 
				"                                                           MAX (OM.DT_PREVISAO), " + 
				"                                                           MAX(OM.NRO_ORDEM_MONTAGEM)  " + 
				"                                                         FROM " + 
				"                                                           MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"                                                           INNER JOIN " + 
				"                                                             TAB_ORDEM_MONTAGEM OM  " + 
				"                                                             ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                                                             AND OM.NRO_TIPO_ORDEM IN(3,4) " + 
				"                                                        WHERE " + 
				"                                                          IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"                                                          AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"                                                          AND OM.NRO_STATUS NOT IN (2,4)))NRO_ORDEM  " + 
				"   FROM " + 
				"      MV_PRODUTO PRD  " + 
				"      INNER JOIN " + 
				"         MV_GRUPO_AGREGADO GA  " + 
				"         ON GA.NRO_GRUPO_AGREGADO = PRD.NRO_GRUPO_AGREGADO  " + 
				"      INNER JOIN " + 
				"         MV_ITEM_GRUPO_AGREGADO ITGA  " + 
				"         ON ITGA.NRO_GRUPO_AGREGADO = GA.NRO_GRUPO_AGREGADO  " + 
				"      INNER JOIN " + 
				"         MV_SERVICO SV  " + 
				"         ON SV.NRO_SERVICO = ITGA.NRO_SERVICO  " + 
				"      INNER JOIN " + 
				"         MV_TIPO_SERVICO_X_PARAM_CONFIG SVPC  " + 
				"         ON SVPC.NRO_TIPO_SERVICO = SV.NRO_TIPO_SERVICO  " + 
				"      INNER JOIN " + 
				"         MV_PARAMETRO_CONFIG PC  " + 
				"         ON PC.NRO_PARAMETRO = SVPC.NRO_PARAMETRO  " + 
				"         AND PC.CHAVE_PARAMETRO_CONFIG = 'NRO_TIPOS_SERVICOS_MONTAGEM'  " + 
				"      INNER JOIN " + 
				"         MV_ESTOQUE_PRODUTO EP  " + 
				"         ON EP.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"         AND EP.NRO_GRUPO_ESTOQUE = 1  " + 
				"   WHERE " + 
				"      EP.NRO_LOJA = EP.NRO_LOJA  " + 
				"      AND EXISTS ( " + 
				"         SELECT " + 
				"            NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"               ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"               AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS NOT IN (2,4) " + 
				"      ) " + 
				"               AND EP.NRO_LOJA = " + nroLoja + 
				"               AND (SELECT " + 
				"            NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"                   ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                    AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS IN(1,3)) = 0 " + 
				"        AND (EP.QTDE_ESTOQUE - EP.QTDE_EMPENHADA) = 0 " + 
				"        AND ROWNUM < 2";
		
		try {
			
			OrdemMontagemDesmontagem ordemMontagemDes = new OrdemMontagemDesmontagem();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ordemMontagemDes.setNroProduto(rs.getInt("NRO_PRODUTO"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return ordemMontagemDes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public OrdemMontagemDesmontagem getProdutoComEstoqueESemOrdemMontagem(int nroLoja) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT " + 
				"      PRD.NRO_PRODUTO     AS NRO_PRODUTO, " + 
				"      PRD.COD_NRO_PRODUTO AS COD_NRO_PRODUTO, " + 
				"      PRD.DESC_PRODUTO    AS PRODUTO, " + 
				"      PRD.DT_INCLUSAO     AS DATA_CADASTRO, " + 
				"      EP.NRO_LOJA         AS NRO_LOJA, " + 
				"       " + 
				"      (SELECT " + 
				"            L.DESC_LOJA AS LOJA  " + 
				"         FROM " + 
				"            MV_LOJA L  " + 
				"         WHERE " + 
				"            EP.NRO_LOJA = L.NRO_LOJA)LOJA, " + 
				"       " + 
				"      (SELECT " + 
				"            NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"                   ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                    AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS IN(1,3)) AS TEM_OM, " + 
				"       " + 
				"      (EP.QTDE_ESTOQUE - EP.QTDE_EMPENHADA) as TEM_ESTOQUE, " + 
				"       " + 
				"      (SELECT " + 
				"            MIN(OM.NRO_ORDEM_MONTAGEM)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"               ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"            INNER JOIN " + 
				"               MV_PRODUTO PRD  " + 
				"               ON PRD.NRO_PRODUTO = IOM.NRO_PRODUTO  " + 
				"               AND OM.NRO_TIPO_ORDEM IN(3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = EP.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS IN(1,3) " + 
				"            AND(OM.DT_PREVISAO,OM.NRO_ORDEM_MONTAGEM)IN(SELECT " + 
				"                                                           MAX (OM.DT_PREVISAO), " + 
				"                                                           MAX(OM.NRO_ORDEM_MONTAGEM)  " + 
				"                                                         FROM " + 
				"                                                           MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"                                                           INNER JOIN " + 
				"                                                             TAB_ORDEM_MONTAGEM OM  " + 
				"                                                             ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                                                             AND OM.NRO_TIPO_ORDEM IN(3,4) " + 
				"                                                        WHERE " + 
				"                                                          IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"                                                          AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"                                                          AND OM.NRO_STATUS NOT IN (2,4)))NRO_ORDEM  " + 
				"   FROM " + 
				"      MV_PRODUTO PRD  " + 
				"      INNER JOIN " + 
				"         MV_GRUPO_AGREGADO GA  " + 
				"         ON GA.NRO_GRUPO_AGREGADO = PRD.NRO_GRUPO_AGREGADO  " + 
				"      INNER JOIN " + 
				"         MV_ITEM_GRUPO_AGREGADO ITGA  " + 
				"         ON ITGA.NRO_GRUPO_AGREGADO = GA.NRO_GRUPO_AGREGADO  " + 
				"      INNER JOIN " + 
				"         MV_SERVICO SV  " + 
				"         ON SV.NRO_SERVICO = ITGA.NRO_SERVICO  " + 
				"      INNER JOIN " + 
				"         MV_TIPO_SERVICO_X_PARAM_CONFIG SVPC  " + 
				"         ON SVPC.NRO_TIPO_SERVICO = SV.NRO_TIPO_SERVICO  " + 
				"      INNER JOIN " + 
				"         MV_PARAMETRO_CONFIG PC  " + 
				"         ON PC.NRO_PARAMETRO = SVPC.NRO_PARAMETRO  " + 
				"         AND PC.CHAVE_PARAMETRO_CONFIG = 'NRO_TIPOS_SERVICOS_MONTAGEM'  " + 
				"      INNER JOIN " + 
				"         MV_ESTOQUE_PRODUTO EP  " + 
				"         ON EP.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"         AND EP.NRO_GRUPO_ESTOQUE = 1  " + 
				"   WHERE " + 
				"      EP.NRO_LOJA = EP.NRO_LOJA  " + 
				"      AND EXISTS ( " + 
				"         SELECT " + 
				"            NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"               ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"               AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS NOT IN (2,4) " + 
				"      ) " + 
				"               AND EP.NRO_LOJA = " + nroLoja + 
				"               AND (SELECT " + 
				"            NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"                   ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                    AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS IN(1,3)) = 0 " + 
				"        AND (EP.QTDE_ESTOQUE - EP.QTDE_EMPENHADA) > 1 " + 
				"        AND ROWNUM < 2";
		
		try {
			
			OrdemMontagemDesmontagem ordemMontagemDes = new OrdemMontagemDesmontagem();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ordemMontagemDes.setNroProduto(rs.getInt("NRO_PRODUTO"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return ordemMontagemDes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
	public OrdemMontagemDesmontagem getProdutoComEstoqueEOrdemMontagem(int nroLoja) {
		
		connection = new Conexao().getConexao();
		
		String sql = "SELECT " + 
				"      PRD.NRO_PRODUTO     AS NRO_PRODUTO, " + 
				"      PRD.COD_NRO_PRODUTO AS COD_NRO_PRODUTO, " + 
				"      PRD.DESC_PRODUTO    AS PRODUTO, " + 
				"      PRD.DT_INCLUSAO     AS DATA_CADASTRO, " + 
				"      EP.NRO_LOJA         AS NRO_LOJA, " + 
				"       " + 
				"      (SELECT " + 
				"            L.DESC_LOJA AS LOJA  " + 
				"         FROM " + 
				"            MV_LOJA L  " + 
				"         WHERE " + 
				"            EP.NRO_LOJA = L.NRO_LOJA)LOJA, " + 
				"       " + 
				"      (SELECT " + 
				"            NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"                   ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                    AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS IN(1,3)) AS TEM_OM, " + 
				"       " + 
				"      (EP.QTDE_ESTOQUE - EP.QTDE_EMPENHADA) as TEM_ESTOQUE, " + 
				"       " + 
				"      (SELECT " + 
				"            MIN(OM.NRO_ORDEM_MONTAGEM)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"               ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"            INNER JOIN " + 
				"               MV_PRODUTO PRD  " + 
				"               ON PRD.NRO_PRODUTO = IOM.NRO_PRODUTO  " + 
				"               AND OM.NRO_TIPO_ORDEM IN(3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = EP.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS IN(1,3) " + 
				"            AND(OM.DT_PREVISAO,OM.NRO_ORDEM_MONTAGEM)IN(SELECT " + 
				"                                                           MAX (OM.DT_PREVISAO), " + 
				"                                                           MAX(OM.NRO_ORDEM_MONTAGEM)  " + 
				"                                                         FROM " + 
				"                                                           MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"                                                           INNER JOIN " + 
				"                                                             TAB_ORDEM_MONTAGEM OM  " + 
				"                                                             ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                                                             AND OM.NRO_TIPO_ORDEM IN(3,4) " + 
				"                                                        WHERE " + 
				"                                                          IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"                                                          AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"                                                          AND OM.NRO_STATUS NOT IN (2,4)))NRO_ORDEM  " + 
				"   FROM " + 
				"      MV_PRODUTO PRD  " + 
				"      INNER JOIN " + 
				"         MV_GRUPO_AGREGADO GA  " + 
				"         ON GA.NRO_GRUPO_AGREGADO = PRD.NRO_GRUPO_AGREGADO  " + 
				"      INNER JOIN " + 
				"         MV_ITEM_GRUPO_AGREGADO ITGA  " + 
				"         ON ITGA.NRO_GRUPO_AGREGADO = GA.NRO_GRUPO_AGREGADO  " + 
				"      INNER JOIN " + 
				"         MV_SERVICO SV  " + 
				"         ON SV.NRO_SERVICO = ITGA.NRO_SERVICO  " + 
				"      INNER JOIN " + 
				"         MV_TIPO_SERVICO_X_PARAM_CONFIG SVPC  " + 
				"         ON SVPC.NRO_TIPO_SERVICO = SV.NRO_TIPO_SERVICO  " + 
				"      INNER JOIN " + 
				"         MV_PARAMETRO_CONFIG PC  " + 
				"         ON PC.NRO_PARAMETRO = SVPC.NRO_PARAMETRO  " + 
				"         AND PC.CHAVE_PARAMETRO_CONFIG = 'NRO_TIPOS_SERVICOS_MONTAGEM'  " + 
				"      INNER JOIN " + 
				"         MV_ESTOQUE_PRODUTO EP  " + 
				"         ON EP.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"         AND EP.NRO_GRUPO_ESTOQUE = 1  " + 
				"   WHERE " + 
				"      EP.NRO_LOJA = EP.NRO_LOJA  " + 
				"      AND EXISTS ( " + 
				"         SELECT " + 
				"            NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"               ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"               AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS NOT IN (2,4) " + 
				"      ) " + 
				"               AND EP.NRO_LOJA = " + nroLoja + 
				"               AND (SELECT " + 
				"            NVL(SUM(DECODE(OM.NRO_TIPO_ORDEM, 3, 1, - 1)), 0)  " + 
				"         FROM " + 
				"            MONT.TAB_ITEM_ORDEM_MONTAGEM IOM  " + 
				"            INNER JOIN " + 
				"               TAB_ORDEM_MONTAGEM OM  " + 
				"                   ON OM.NRO_ORDEM_MONTAGEM = IOM.NRO_ORDEM_MONTAGEM  " + 
				"                    AND OM.NRO_TIPO_ORDEM IN (3,4) " + 
				"         WHERE " + 
				"            IOM.NRO_PRODUTO = PRD.NRO_PRODUTO  " + 
				"            AND OM.NRO_LOJA_MOSTRUARIO = EP.NRO_LOJA  " + 
				"            AND OM.NRO_STATUS IN(1,3)) > 1 " + 
				"        AND (EP.QTDE_ESTOQUE - EP.QTDE_EMPENHADA) > 1 " + 
				"        AND ROWNUM < 2";
		
		try {
			
			OrdemMontagemDesmontagem ordemMontagemDes = new OrdemMontagemDesmontagem();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				ordemMontagemDes.setNroProduto(rs.getInt("NRO_PRODUTO"));
			}
			
			stmt.close();
			rs.close();
			connection.close();
			
			return ordemMontagemDes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	
}