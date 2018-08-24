Narrative:
Como um usuário administrativo da loja
Eu quero importar uma lista com promocoes
De modo que essas promocoes sejam importadas e disponibilizadas na tela de pedido

Scenario: Importar uma lista com promocoes
Meta:
@tag component:Regressão - MVSH
@context PROCESSOS
@base MVSH
@grupoInstancias AdmCD
@issue prcs-27
@prcs-27

!-- Importação de promoção com zonas utilizando arquivos com grande massa de dados
Given Eu acesso a instancia padrao
When Eu faco login na loja 6044 com usuario 7380 e senha 1
And Eu acesso o menu Consultar > Promoção > Importação de Promoção
And Eu clico no botao Nova Importacao de promocao
And Eu informo a descricao PROMOCAO AUTOMACAO da importacao de promocao
And Eu informo TABLOIDE no tipo da promocao
And Eu informo a data de comeco de vigencia da promocao
And Eu informo a data de termino de vigencia da promocao
And Eu seleciono Não variar em permitir variacao de plano
And Eu seleciono Preço partida fixo em configurar preco de partida
And Eu seleciono Sim em visualizar alerta
And Eu informo PC em conceito cliente
And Eu seleciono Zona em tipo da praca
And Eu seleciono Produto em tipo de identificacao de itens
And Eu seleciono Não em copiar promocao para demais produtos
And Eu informo o arquivo arquivo valido 2000.xlsx de importacao de promocao

!-- Validar Inconsistência de dados do arquivo em segundo plano
And Eu clico no botao Validar Arquivo importacao de promocao
And Eu valido mensagem de inconsistencia de dados executando em segundo plano

!-- Consulta importações
And Eu registro o numero da importacao na sessao
And Eu clico no botao Voltar para pesquisa de importacoes de promocoes

!-- Apos a validacao, efetiva a importacao
And Eu aguardo a validacao estar concluida
And Eu informo o nro da promocao na pesquisa
And Eu seleciono o status Validação concluída da importacao na pesquisa
And Eu clico no botao Pesquisar importacao de promocao

!-- Ver detalhes de uma importação com validação concluída
And Eu clico no link para editar importacao de promocao

!-- Efetivar a importação dos dados em segundo plano
And Eu clico no botao efetivar importacao de promocao
And Eu valido mensagem de promocao sendo efetivada em segundo plano
And Eu clico no botao Voltar para pesquisa de importacoes de promocoes

!-- Valida se a importacao foi concluida
And Eu aguardo a conclusao da importacao de promocao
And Eu informo o nro da promocao na pesquisa
And Eu seleciono o status Importação concluída da importacao na pesquisa
And Eu clico no botao Pesquisar importacao de promocao
And Eu clico no link para editar importacao de promocao
Then Eu valido mensagem de sucesso da importacao de promocao


Scenario: Baixar template de arquivo de Importação
Meta:
@tag component:Regressão - MVSH
@context PROCESSOS
@base MVSH
@grupoInstancias AdmCD
@issue prcs-27
@prcs-27

Given Eu acesso a instancia padrao
When Eu faco login na loja 6044 com usuario 7380 e senha 1
And Eu acesso o menu Consultar > Promoção > Importação de Promoção
And Eu clico no botao Nova Importacao de promocao
Then Eu clico no link para baixar o template de importacao


Scenario: Baixar arquivo de log de validação
Meta:
@tag component:Regressão - MVSH
@context PROCESSOS
@base MVSH
@grupoInstancias AdmCD
@issue prcs-27
@prcs-27

Given Eu acesso a instancia padrao
When Eu faco login na loja 6044 com usuario 7380 e senha 1
And Eu acesso o menu Consultar > Promoção > Importação de Promoção
And Eu informo o nro da promocao na pesquisa
And Eu clico no botao Pesquisar importacao de promocao
And Eu clico no link para editar importacao de promocao
Then Eu clico no link para baixar o o log de validacao


Scenario: Associação das promoções importadas a serviços/tipo de serviços
Meta:
@tag component:Regressão - MVSH
@context PROCESSOS
@base MVSH
@grupoInstancias AdmCD
@issue prcs-27
@prcs-27

Given Eu acesso a instancia padrao
When Eu faco login na loja 6044 com usuario 7380 e senha 1
And Eu acesso o menu Consultar > Promoção > Configuração de Promoção
!-- And Eu informo o codigo da promocao em configuracao
And Eu seleciono o status da promocao Em Elaboração em configuracao
And Eu clico no botao Pesquisar promocao em configuracao
And Eu clico no botao Acoes da primeira promocao encontrada
And Eu seleciono o condicional E o tipo servico/servico a promocao
And Eu seleciono o tipo de servico TROCA GARANTIDA para a promocao
And Eu seleciono o servico CLARO CARTAO 30 para a promocao
And Eu clico botao Salvar associacao promocao
Then Eu valido mensagem de sucesso na associacao