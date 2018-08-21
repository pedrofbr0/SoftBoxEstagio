Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo


Scenario: Emite Nota Fiscal Avulsa Tipo 60 - OUTRAS SAIDAS REGULARIZACAO ESTOQUE - UF2320
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-11
@nf-11

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 60
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros

And Na tela de nota avulsa eu seleciono a empresa 5 - RN COMERCIO VAREJISTA S.A RELOH
And Na tela de nota avulsa eu seleciono a loja destino 601 - CD 47 - CD CONTAGEM 1 - MG
And Na tela de nota avulsa eu seleciono o grupo estoque destino 1 - Loja
And Na tela de parametros da nota avulsa eu clico no botao Concluir


And Na tela de nota avulsa eu clico na aba Itens da nota fiscal
And Na tela de nota avulsa eu clico no botao Incluir Produto
And No popup de itens de autorizacao eu pesquiso o produto 711
And Na tela de nota avulsa eu informo a quantidade 1 do item da nota fiscal


And Eu clico no botao Concluir emissao nf avulsa tipo 60
Then Eu salvo a nota fiscal do teste @nf-11 no relatorio