Narrative:
Como um usuário adm da loja
Eu quero incluir um novo departamento
De modo que o mesmo possa ser associado à um vendedor

Scenario: incluir departamento
Meta:
@tag component:Regressao - RELOH
@context VENDEDOR
@base RELOH
@grupoInstancias AdmCD
@issue ven-01
@ven-01

!-- ----------------------------------------------
!-- TELA DE LOGIN
!-- ----------------------------------------------
Given Eu acesso a instancia padrao
When Eu faco login na loja 78 com usuario 7380 e senha 1

!-- ----------------------------------------------
!-- TELA INICIAL
!-- ----------------------------------------------
And Eu espero 6000 milisegundos
And Eu acesso o menu Administração > Cadastro > Departamentos Associáveis
!-- And Eu acesso o menu Administração > Cadastro > Referência
!-- And Eu acesso o menu Administração > Cadastro > Config. de Sistema TEF Loja
!-- And Eu acesso o menu Administração > Cadastro > Produto Multi CD
!-- And Eu acesso o menu Administração > Cadastro > Zona
!-- And Eu acesso o menu Administração > Cadastro > Referência
!-- And Eu acesso o menu Administração > Cadastro > Meta Global Margem de contribuição
!-- And Eu acesso o menu Administração > Cadastro > Meta Margem de contribuição
!-- And Eu acesso o menu Administração > Cadastro > Motivo de Liberação
!-- And Eu acesso o menu Administração > Cadastro > Loja Config. Confirmação de Estoque
!-- And Eu acesso o menu Administração > Cadastro > Grupo Estoque x Orms
!-- And Eu acesso o menu Administração > Cadastro > Grupo Estoque x Parâmetro de Config
!-- And Eu acesso o menu Administração > Cadastro > Status pedido x Parâmetro de Config
!-- And Eu acesso o menu Administração > Cadastro > Loja x Parâmetro de Config
!-- And Eu acesso o menu Administração > Cadastro > UF x Parâmetro de Config
!-- And Eu acesso o menu Administração > Cadastro > Fornecedor x Parâmetro de Config
!-- And Eu acesso o menu Administração > Cadastro > Config. de Sistema TEF Loja
!-- And Eu acesso o menu Administração > Kit
!-- And Eu acesso o menu Administração > Módulo Atendimento Cliente > Planilha(s) de Devolução
!-- And Eu acesso o menu Fopag > Cadastro > Metas de Vendas > Importação de meta
!-- And Eu acesso o menu Administração > Tipo de Venda > Tipo Venda
!-- And Eu acesso o menu Administração > Tipo de Venda > Forma Pagamento
!-- And Eu acesso o menu Administração > Serviços > Importar Críticas
!-- And Eu acesso o menu Administração > Serviços > Passaporte > Importar Números Sorteio
And Eu espero 10000 milisegundos
And Eu informo e seleciono um novo departamento 34
And Eu clico no botao 'Salvar'
Then Eu valido a mensagem de 'Registro salvo com sucesso!' do tipo 'alert'

