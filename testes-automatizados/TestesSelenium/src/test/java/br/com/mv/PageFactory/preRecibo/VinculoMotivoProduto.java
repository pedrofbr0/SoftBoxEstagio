package br.com.mv.PageFactory.preRecibo;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import br.com.mv.dao.pedido.MotivoAprovacaoTrocadevol;

public class VinculoMotivoProduto {

	WebDriver driver;
	
	@FindBy(id = "motivo-devolucao")
	private WebElement cbxMotivoDevolucao;
	
	//@FindBy(xpath = "html/body/div[2]/div/div[9]/form/div[2]/fieldset/textarea")
	@FindBy(css = "textarea[ng-model='vinculoMotivo.resp.observacao']")
	private WebElement observacao;
	
	@FindBy(id = "btn-salvar")
	private WebElement btnSalvar;
	
	@FindBy(id = "btn-voltar")
	private WebElement btnVoltar;
	
	public VinculoMotivoProduto(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public VinculoMotivoProduto setMotivo(String motivo) {
		new Select(this.cbxMotivoDevolucao).selectByVisibleText(motivo);
		return this;
	}
	
	public VinculoMotivoProduto setObservacao(String observacao) {
		this.observacao.clear();
		this.observacao.sendKeys(observacao);
		return this;
	}
	
	public VinculoMotivoProduto clickSalvar() {
		this.btnSalvar.click();
		return this;
	}
	
	public VinculoMotivoProduto clickVoltar() {
		this.btnVoltar.click();
		return this;
	}
	
	/**
	 * Recebe todos os motivos para troca/devolução de um produto e seleciona um motivo aleatório, que não seja jurídico
	 * @param motivos, Lista do tipo Motivo, com todos os motivos para Troca/Devolução
	 * @return motivo, String com o motivo selecionado aleatoriamente 'nro_motivo' - 'desc_motivo'. Ex.: '1 - Serviço Inativo'
	 */
	public String selecionarMotivo(List<MotivoAprovacaoTrocadevol> motivos, int nroMotivoTrocaDevol) {
		
		int nroMotivo = 0;
		String descMotivo = "";
		
		if (nroMotivoTrocaDevol == 0) {
			Random random = new Random();
			boolean motivoEncontrado = false;
			int n = 0;
			
			while (!motivoEncontrado) {
				n = random.nextInt(motivos.size());
				descMotivo = motivos.get(n).getDescMotivoFluxoAprovacao().trim();
				if (!descMotivo.startsWith("JURIDICO") && !descMotivo.startsWith("CRC")) {
					motivoEncontrado = true;
				}
			}
			
			nroMotivo = motivos.get(n).getNroMotivoFluxoAprovacao();
			
		} else {
			for (MotivoAprovacaoTrocadevol motivo : motivos) {
				nroMotivo = motivo.getNroMotivoFluxoAprovacao();
				
				if (nroMotivo == nroMotivoTrocaDevol) {
					descMotivo = motivo.getDescMotivoFluxoAprovacao();
					
					break;
				}
				
			}
		}
		
		// Integer.toString(nroMotivo) + " - " + 
		return descMotivo;
		
	}
}
