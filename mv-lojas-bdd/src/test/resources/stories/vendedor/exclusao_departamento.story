Narrative:
Como um usuário adm da loja
Eu quero excluir um departamento
De modo que o mesmo possa ser desassociado à um vendedor

Scenario: exclusao departamento
Meta:
@tag component:Regressao - RELOH
@context VENDEDOR
@base RELOH
@grupoInstancias AdmCD
@issue ven-02
@ven-02

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
!-- And Eu acesso o menu Administração > Tipo de Venda > Tipo Venda
!-- And Eu acesso o menu Administração > Serviços > Importar Críticas
!-- And Eu acesso o menu Administração > Serviços > Passaporte > Importar Números Sorteio
And Eu espero 10000 milisegundos
And Eu removo um departamento
And Eu informo e seleciono um novo departamento 34
And Eu clico no botao 'Salvar'
And Num sei
Then Eu valido a mensagem de 'Registro salvo com sucesso!' do tipo 'alert'
