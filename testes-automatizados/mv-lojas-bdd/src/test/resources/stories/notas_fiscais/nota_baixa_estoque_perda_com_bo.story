Narrative:
Como um usuário da loja
Eu quero elaborar uma nota avulsa
De modo que seja criada uma nota fiscal ao final do processo


Scenario: Emite Nota Fiscal Avulsa Tipo 220 BAIXA DE ESTOQUE POR PERDA COM BOUF2971
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-38
@nf-38

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 220
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros

And Na tela de nota avulsa eu seleciono a empresa 5 - RN COMERCIO VAREJISTA S.A RELOH
And Na tela de nota avulsa eu seleciono a loja destino 764 - CD_0221_MG
And Na tela de nota avulsa eu seleciono o grupo estoque destino 1 - Loja
And Na tela de parametros da nota avulsa eu clico no botao Concluir


And Na tela de nota avulsa eu clico na aba Itens da nota fiscal
And Na tela de nota avulsa eu clico no botao Incluir Produto
And No popup de itens de autorizacao eu pesquiso o produto 711
And Na tela de nota avulsa eu informo a quantidade 1 do item da nota fiscal

And Eu clico no botao Concluir emissao nf avulsa tipo 220
Then Eu salvo a nota fiscal do teste @nf-38 no relatorio