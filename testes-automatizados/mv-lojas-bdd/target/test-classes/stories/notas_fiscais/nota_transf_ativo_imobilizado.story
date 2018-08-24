Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo


Scenario: Emite Nota Fiscal Avulsa Tipo 110 TRANSF. DE ATIVO IMOBILIZADO (COM REDUCAO BASE DE ICMS)
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-28
@nf-28

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 110
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros

And Na tela de nota avulsa eu seleciono a empresa 5 - RN COMERCIO VAREJISTA S.A RELOH
And Na tela de nota avulsa eu seleciono a loja destino 764 - CD_0221_MG
And Na tela de nota avulsa eu seleciono o grupo estoque destino 1 - Loja
And Na tela de parametros da nota avulsa eu clico no botao Concluir

And Na tela de nota avulsa eu clico na aba Itens da nota fiscal
And Na tela de nota avulsa eu clico no botao Incluir Produto geral
And Na tela de nota avulsa eu informo a descricao AUTOMACAO do item da nota
And Na tela de nota avulsa eu informo o valor 5000 do item da nota
And Na tela de nota avulsa eu informo a aliquota ICMS 100 do item da nota
And Na tela de nota avulsa eu informo a base calculo ICMS 500 do item da nota
And Na tela de nota avulsa eu informo o ICMS 100 do item da nota


And Eu clico no botao Concluir emissao nf avulsa tipo 110
Then Eu salvo a nota fiscal do teste @nf-28 no relatorio