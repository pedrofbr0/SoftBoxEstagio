Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido com plano controle e utiliza o privilégio para liberar o pedido sem ativação do plano
De modo que o pedido seja criado ao finalizar o processo

Scenario: Elabora um pedido com plano controle e utiliza o privilégio para liberar o pedido sem ativação do plano
Meta:
@tag component:Regressão - RELOH
@context PEDIDOS
@base RELOH
@grupoInstancias Pedidos
@issue rgs-81
@rgs-81

Given Eu executo o script de reset pedido_avista_siv_sem_privilegio_liberacao_sem_ativacao.sql
And Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 6699 e senha 1
And Eu pesquiso o cpf 37778670550
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 1 - A VISTA

And Eu clico no botao adicionar produto
And Eu pesquiso o produto 219254
And Eu deixo o produto sem promocao
And Eu clico no botao Kit
And Eu seleciono o kit 2305
And Eu seleciono a promocao de valor 400,00
And Eu clico no botao Selecionar Produto

And Eu clico no icone detalhes do produto 219254
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu informo o numero de serie 0001
And Eu informo a flag entrega como NÃO
And Eu clico no botao Reservar

And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento V
And Eu seleciono a 1 ª forma de pagamento Dinheiro

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 26 - Ag. Ativação Plano Controle
And Eu clico no botao Ok do modal

And Eu clico no botao Ativar Plano Controle
And Eu verifico se o botao Liberar pedido sem Ativacao nao esta presente
!-- And Eu salvo o pedido do teste @rgs-81 no relatorio