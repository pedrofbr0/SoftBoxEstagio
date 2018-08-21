Narrative:
Como um usuário vendedor da loja
Eu quero elaborar um pedido a vista com empenho em LOJA/NORMAL, forma de pagamento dinheiro
HERO ESSENCIAL
De modo que o pedido seja criado ao finalizar o processo

Scenario: Elaborar um pedido a vista com LOJA/NORMAL, forma de pagamento dinheiro
Meta:
@tag component:Regressão - RELOH
@context PEDIDOS
@base RELOH
@grupoInstancias Pedidos
@issue rgs-54
@rgs-54

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 69930 e senha 1
And Eu pesquiso o cpf 37778670550

And Eu clico no botao 'Elaborar Pedido'

And Eu seleciono o tipo de venda 1 - A VISTA

And Eu clico no botao adicionar produto
And Eu pesquiso e seleciono o produto 144366
And Eu clico no icone detalhes do produto 144366
And Eu seleciono o local de empenho LOJA
And Eu seleciono a opcao de entrega NORMAL
And Eu informo a flag entrega como NÃO
And Eu informo o numero de serie 0001
And Eu clico no botao Reservar


!-- ******************************************
!--  UTILIZACAO NA ABA DE SERVICO
!-- ******************************************

And Eu clico na aba Serviços
And Eu clico em adicionar servico
And Eu adiciono o servico 302075
And Eu valido se o servico 305038 ja foi adicionado

!-- Ao remover o filho tem q apresentar mensgaem servico vinculado
And Eu removo o servico 305038
And Eu valido mensagem servico vinculado a combo
And Eu clico no botao Ok do modal

!-- Verifica se mantem o servico pai
And Eu valido se o servico 302075 ja foi adicionado

!-- Remove o pai tem q remover o filho
And Eu removo o servico 302075
And Eu valido se o servico 305038 nao existe no grid


!-- **** Repete o processo de adicionar o servico pai ****
And Eu clico em adicionar servico
And Eu verifico se o servico 305038 nao existe no grid de consulta de servicos
And Eu adiciono o servico 302075

And Eu valido se o servico 305038 ja foi adicionado

!-- Ao remover o filho tem q apresentar mensgaem servico vinculado
And Eu removo o servico 305038
And Eu valido mensagem servico vinculado a combo
And Eu clico no botao Ok do modal

!-- Verifica se mantem o servico pai
And Eu valido se o servico 302075 ja foi adicionado


!-- ******************************************
!--  UTILIZACAO NA ABA DE GARANTIA
!-- ******************************************

And Eu clico na aba Produto
And Eu clico no produto 144366
And Eu clico na aba Garantia
And Eu clico no botao Adicionar Garantia
And Eu seleciono a garantia 302075
And Eu valido se a garantia 305038 foi adicionada para o produto 144366

!-- Ao remover o filho tem q apresentar mensgaem servico vinculado
And Eu removo a garantia 305038 do produto 144366
And Eu valido mensagem garantia vinculado a combo
And Eu clico no botao Ok do modal

!-- Verifica se mantem o servico pai
And Eu valido se a garantia 302075 foi adicionada para o produto 144366

!-- Remove o pai tem q remover o filho
And Eu removo a garantia 302075 do produto 144366
And Eu valido se a garantia 305038 nao existe no grid


!-- **** Repete o processo de adicionar o servico pai ****
And Eu clico na aba Produto
And Eu clico no produto 144366
And Eu clico na aba Garantia
And Eu clico no botao Adicionar Garantia
And Eu seleciono a garantia 302075
And Eu valido se a garantia 305038 foi adicionada para o produto 144366

!-- Ao remover o filho tem q apresentar mensagaem servico vinculado
And Eu removo a garantia 305038 do produto 144366
And Eu valido mensagem garantia vinculado a combo
And Eu clico no botao Ok do modal

!-- Verifica se mantem o servico pai
And Eu valido se o servico 302075 ja foi adicionado


And Eu clico na aba Planilha
And Eu clico no botao Adicionar Forma Pagamento
And Eu seleciono o 1 º tipo de pagamento V
And Eu seleciono a 1 ª forma de pagamento Dinheiro

Then Eu clico no botao Salvar
And Eu clico no botao Ok do modal
And Eu clico no botao Fechamento Total
And Eu valido o status do pedido 30 - Ag. Receb. ou em Analise p/Troca Minha Casa Melhor

Scenario: Efetua o recebimento do pedido
Meta:
@tag component:Regressão - RELOH
@context PEDIDOS
@base RELOH
@grupoInstancias Caixa
@issue rgs-54
@rgs-54

Given Eu acesso o pdv web padrao
When Eu faco login no pdv web 110 na loja 291 com usuario 7349 e senha 1
And Eu acesso o pdv menu Pedido
And No pdv eu pesquiso o pedido
And No pdv eu clico no botao receber pedido
And No pdv eu preencho os valores
And No pdv eu clico no botao Emitir Cupom
Then No pdv eu valido se o pedido foi recebido

Scenario: Imprimir termo de adesão do antivirus hero
Meta:
@tag component:Regressão - RELOH
@context PEDIDOS
@base RELOH
@grupoInstancias Pedidos
@issue rgs-54
@rgs-54

Given Eu acesso a instancia padrao
When Eu faco login na loja 291 com usuario 69930 e senha 1
And Eu acesso o menu Consultar > Pedido
And Eu consulto o pedido
And Eu clico no botao Imprimir Documentos
And Eu seleciono o documento TERMO ADESÃO ANTIVÍRUS HERO
And Eu clico no botao Gerar Relatorio
Then Eu valido o Termo de Adesão Antivirus Hero
!-- And Eu salvo o pedido do teste @rgs-54 no relatorio