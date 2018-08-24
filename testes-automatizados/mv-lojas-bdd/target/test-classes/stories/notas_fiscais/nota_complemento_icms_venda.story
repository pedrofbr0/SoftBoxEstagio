Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido
De modo que o pedido seja criado uma nota fiscal de venda ao final do processo


Scenario: Emite Nota Fiscal Avulsa Tipo 820 COMPLEMENTO ICMS  VENDA
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-51
@nf-51

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 820
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros


And Na tela de parametros da nota avulsa eu clico no radio Cliente
And Na tela de parametros da nota avulsa eu clico botao Pesquisar cliente

And No popup de selecao cliente eu informo o cpf 191
And No popup de selecao cliente eu clico no botao Pesquisar
And No popup de selecao cliente eu seleciono o cliente
And No popup de selecao cliente eu clico no botao Selecionar


And Na tela de parametros da nota avulsa eu seleciono o endereco do cliente
And Eu clico no botao Concluir parametros


And Na tela de nota avulsa eu clico na aba Itens da nota fiscal
And Na tela de nota avulsa eu clico no botao Incluir Produto geral
And Na tela de nota avulsa eu informo a descricao AUTOMACAO do item da nota
And Na tela de nota avulsa eu informo o valor 5000 do item da nota
And Na tela de nota avulsa eu informo a aliquota ICMS 100 do item da nota
And Na tela de nota avulsa eu informo a base calculo ICMS 500 do item da nota
And Na tela de nota avulsa eu informo o ICMS 100 do item da nota

And Eu clico no botao Concluir emissao nf avulsa tipo 820
Then Eu salvo a nota fiscal do teste @nf-51-a no relatorio


Scenario: Emite Nota Fiscal Avulsa Tipo 820 COMPLEMENTO ICMS  VENDA
Meta:
@tag component:Processos - RELOH
@context NOTAS_FISCAIS
@base RELOH
@grupoInstancias AdmCD
@issue nf-51
@nf-51

Given Eu acesso a instancia padrao
When Eu faco login na loja 47 com usuario 7380 e senha 1
!-- Emissão NF Avulsa
And Eu acesso o menu Faturamento > Emissão NF Avulsa
And Eu seleciono a operacao de Saída da nota fiscal
And Eu seleciono o tipo de nota fiscal 820
And Eu seleciono o grupo de estoque origem 1 - Loja
And Eu clico no botao Informar Parametros


And Na tela de parametros da nota avulsa eu clico no radio Fornecedor
And Na tela de parametros da nota avulsa eu clico botao Pesquisar fornecedor

And No popup de selecao fornecedor eu informo o codigo 201194 do fornecedor
And No popup de selecao fornecedor eu clico no botao Pesquisar
And No popup de selecao fornecedor eu seleciono o fornecedor


And Na tela de parametros da nota avulsa eu seleciono o endereco do fornecedor
And Eu clico no botao Concluir parametros


And Na tela de nota avulsa eu clico na aba Itens da nota fiscal
And Na tela de nota avulsa eu clico no botao Incluir Produto geral
And Na tela de nota avulsa eu informo a descricao AUTOMACAO do item da nota
And Na tela de nota avulsa eu informo o valor 5000 do item da nota
And Na tela de nota avulsa eu informo a aliquota ICMS 100 do item da nota
And Na tela de nota avulsa eu informo a base calculo ICMS 500 do item da nota
And Na tela de nota avulsa eu informo o ICMS 100 do item da nota

And Eu clico no botao Concluir emissao nf avulsa tipo 820
Then Eu salvo a nota fiscal do teste @nf-51-b no relatorio