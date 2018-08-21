Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido a vista com kit, promoção e que tenha serviço vinculado obrigatorio
De modo que o pedido seja criado ao finalizar o processo

Scenario: Elaborar um pedido a vista com kit, promoção e que tenha serviço vinculado obrigatorio
Meta:
@tag component:Regressão - RELOH
@context PEDIDOS
@base RELOH
@grupoInstancias Pedidos
@issue rgs-56
@rgs-56

Given Eu executo o script de reset pedido_avista_siv_56.sql 
And Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 69930 e senha 1
And Eu pesquiso o cpf 37778670550
And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 1 - A VISTA

!-- 219254	14	0	CEL MOTO G XT1068 2CHIP 8GB PTO
!-- 256403	4	0	CHIP CLARO PRE TRIPLE FLEX BR
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

!-- 153414	PLANO CONTROLE
And Eu clico na aba Garantia
And Eu removo a garantia 153414 do produto 256403

And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento V
And Eu seleciono a 1 ª forma de pagamento Dinheiro

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido a mensagem de obrigatoriedade de pelo menos 1 servico e 1 tipo de servico vinculados na promocao
And Eu clico no botao Ok do modal

When Eu clico na aba Produto
And Eu clico no produto 256403
And Eu clico na aba Garantia
And Eu clico no botao Adicionar Garantia
And Eu seleciono a garantia 153414

And Eu clico na aba Planilha
And Eu removo todas as planilhas
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento V
And Eu seleciono a 1 ª forma de pagamento Dinheiro

Then Eu clico no botao Fechamento Total
And Eu valido o status do pedido 26 - Ag. Ativação Plano Controle
And Eu clico no botao Ok do modal

And Eu valido botao Ativar Plano Controle

!-- And Eu salvo o pedido do teste @rgs-56 no relatorio
