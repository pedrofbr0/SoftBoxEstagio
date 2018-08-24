Narrative:
Como um usuário vendedor da loja
Eu quero validar a confirmação de saida de estoque
De modo que a confirmação seja realizada ao finalizar o processo


Scenario: Confirmação de Saida de Estoque
Meta:
@tag component:Processos - RELOH
@context PROCESSOS
@base RELOH
@grupoInstancias AdmCD
@issue prcs-26
@prcs-26

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 7380 e senha 1
And Eu acesso o menu Faturamento > Estoque > Confirmação Saída Estoque
!-- And Eu pesquiso o pedido para confirmar saida estoque
And Eu seleciono o pedido para confirmar saida estoque
And Eu clico no botao Confirmação de Pedido
And Eu seleciono os produtos para confirmacao
And Eu informo o cupom para confirmacao
And Eu clico no botao Confirmar saida estoque
Then Eu valido a mensagem de sucesso de confirmacao de saida de estoque