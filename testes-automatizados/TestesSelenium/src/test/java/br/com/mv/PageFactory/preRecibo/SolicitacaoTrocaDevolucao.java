package br.com.mv.PageFactory.preRecibo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.com.mv.dao.pedido.MotivoAprovacaoTrocadevol;
import br.com.mv.dao.pedido.MotivoAprovacaoTrocadevolDAO;
import br.com.mv.dao.pedido.Produto;
import br.com.mv.dao.pedido.Servico;

public class SolicitacaoTrocaDevolucao {

	WebDriver driver;
	
	@FindBy(id = "btn-informar-motivo")
	private WebElement btnInformarMotivo;
	
	@FindBy(id = "btn-salvar")
	private WebElement btnSalvar;
	
	public SolicitacaoTrocaDevolucao(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public SolicitacaoTrocaDevolucao clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
	
	public SolicitacaoTrocaDevolucao clickInformarMotivoProdutoReverso(int idProdutoReverso) {
		driver.findElement(By.cssSelector("#btn-informar-motivo-produto-" + idProdutoReverso)).click();
		return this;
	}
	
	public SolicitacaoTrocaDevolucao clickInformarMovitoServicoReverso(int idServicoReverso) {
		driver.findElement(By.cssSelector("#btn-informar-motivo-servico-" + idServicoReverso)).click();
		return this;
	}
	
	public void informarMotivoProduto(List<Produto> produtos, int nroLoja, int nroPedido, int nroLojaPedido, int tipoProcesso) {
		MotivoAprovacaoTrocadevolDAO motivoDAO = new MotivoAprovacaoTrocadevolDAO();
		List<MotivoAprovacaoTrocadevol> motivos = new ArrayList<MotivoAprovacaoTrocadevol>();
		VinculoMotivoProduto vinculoMotivoProduto = new VinculoMotivoProduto(driver);
		
		for (Produto prod : produtos) {
			
			System.out.println(prod.getIdProdutoReverso());
			
			clickInformarMotivoProdutoReverso(prod.getIdProdutoReverso());
			
			motivos = motivoDAO.getMotivosProdutos(nroLoja, nroPedido, nroLojaPedido, prod.getNroProduto(), tipoProcesso); // o está como nro item pedido produto, não será mais utilizado no sql
			String motivo = vinculoMotivoProduto.selecionarMotivo(motivos, 0);
			
			System.out.println(motivo);
			
			// Vinculo motivo produto
			vinculoMotivoProduto.setMotivo(motivo.trim()).setObservacao("Troca/Devolução teste").clickSalvar();
		}
	}
	
	public void informarMotivoServico(List<Servico> servicos, String motivo, String observacao) {
		VinculoMotivoProduto vinculoMotivoProduto = new VinculoMotivoProduto(driver);
		
		for (Servico serv : servicos) {

			//if (serv.getFlagDevolucao().equalsIgnoreCase("N")) { // Deve ser criada uma liberação de devolução como etapa de aprovação
			
				// Solicitacao troca/devolucao
				clickInformarMovitoServicoReverso(serv.getIdServicoReverso());
				
				// Vinculado motivo
				vinculoMotivoProduto.setMotivo(motivo.trim()).setObservacao(observacao).clickSalvar();
			//}
		}
	}
}