Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo


Scenario: Emite Nota Fiscal Avulsa Tipo 475 RET DEV COMPRAS - SIMPLES FAT - REF NF 244
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-24
@nf-24

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Entrada da nota fiscal
And Eu seleciono o tipo de nota fiscal 475
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros

And Eu informo o numero da nota via integracao tipo 244
And Eu informo a empresa da nota via integracao tipo 244
And Eu informo o codigo loja da nota via integracao tipo 244
And Eu informo o numero da serie da nota via integracao tipo 244
And Eu clico no botao Pesquisar nota fiscal tipo 244
And Eu seleciono o nota fiscal tipo 244
And Eu clico no botao Concluir parametros


And Na tela de nota avulsa eu clico na aba Outros da nota fiscal
And Na tela de nota avulsa eu informo a descricao especie AUTOMACAO
And Na tela de nota avulsa eu informo a quantidade especie 1
And Na tela de nota avulsa eu informo o peso bruto 100

And Eu clico no botao Concluir emissao nf avulsa tipo 475
Then Eu salvo a nota fiscal do teste @nf-24 no relatorio