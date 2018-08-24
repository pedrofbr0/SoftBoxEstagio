# Testes Automatizados

 - Baixar o projeto para a pasta **C:\testes-mv**:

```
$ git clone ssh://git@gitlab.softbox.com.br:1912/mv-lojas/testes-automatizados.git
```


# Executando via Docker

 - Executar o comando para criação de dockers **ansible\ansible-playbook init.yml**:

```
Descomentar a linha no arquivo serenity.properties 
'#webdriver.remote.url=http://localhost:4446/wd/hub

Executar via gradle

gradle clean build test aggregate -Dmetafilter="+prd-smk-02" -Dmvlojas.urlProducao="http://10.30.232.18:8080" --stacktrace
```

## Importação de projetos

 - Testes (Casperjs):
	- File > Import > General > Existing Projects in Workspace;
	- Buscar a pasta C:\testes-mv\testes-automatizados\Testes, selecionar projeto e Finish;

 - TestesSelenium:
	- File > Import > Gradle > Gradle Project;
	- Buscar a pasta C:\testes-mv\testes-automatizados\TestesSelenium , clicar em Build Model, selecionar projeto e Finish;

 - mv-lojas-bdd:
	- File > Import > Maven > Existing Maven Projects;
	- Buscar a pasta C:\testes-mv\testes-automatizados\mv-lojas-bdd, selecionar projeto e Finish;

 - analytics-bdd:
	- File > Import > Maven > Existing Maven Projects;
	- Buscar a pasta C:\testes-mv\testes-automatizados\analytics-bdd, selecionar projeto e Finish;
	
### MV Lojas BDD:

Para fazer o build do projeto siga os passos abaixo:
  - Botão direito no projeto "mv-lojas-bdd", Maven > Run As > Maven build...
  - No campo "Goals" informe: clean verify
    - **OBS.:** Caso queira filtrar por categoria/tags (SMOKE_TESTING / REGRESSAO), seguem abaixo alguns exemplos;
	
		Executa os testes que contém as tags: **@issue smk-05, smk-03, smk-09** e a tag. **@base RELOH**
		```
		clean verify -Dmetafilter="groovy: (issue == 'smk-05' || issue == 'smk-03' || issue == 'smk-09') && (base != 'MVSH')"
		```
		Executa todos os testes que tenham a tag **@context SMOKE_TESTING** e a tag. **@base RELOH**
		```
		clean verify -Dmetafilter="groovy: (context == 'SMOKE_TESTING' && base != 'MVSH')"
		```
		Executa todos os testes que tenham a tag **@context REGRESSAO**
		```
		clean verify -Dmetafilter="+context REGRESSAO"
		```
		Executa somente o teste que contém a tag @rgs-20
		```
		clean verify -Dmetafilter="+rgs-20"
		```
		Utilizar o parâmetro -Dmaven.clean.failOnError=false, caso aconteça falha ao executar o passo "clean".
		[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ mv-lojas-bdd ---
		[INFO] Deleting C:\testes-mv\testes-automatizados\mv-lojas-bdd\target
		```
		clean verify -Dmetafilter="+rgs-20" -Dmaven.clean.failOnError=false
		```
		Executa os cenários de reversão:
		```
		clean verify -Dmetafilter="groovy: (issue == 'rgs-59' || issue == 'rgs-60' || issue == 'rgs-61' || issue == 'rgs-62' || issue == 'rgs-63' || issue == 'rgs-64' || issue == 'rgs-65' || issue == 'rgs-73') && (base != 'MVSH')"
		```
		Executa cenários avulsos, 4 pedidos a vista/dinheiro, 4 pedidos cartao de crédito:
		```
		clean verify -Dmetafilter="groovy: (issue == 'avl-03' || issue == 'avl-04' || issue == 'avl-05' || issue == 'avl-06' || issue == 'avl-02' || issue == 'avl-07' || issue == 'avl-08' || issue == 'avl-09') && (base != 'MVSH')"
		```
		Executa cenários de reversão em **AMBIENTE DE VALIDAÇÃO**
		```
		clean verify -Dmetafilter="groovy: (issue == 'rgs-66' || issue == 'rgs-67' || issue == 'rgs-68' || issue == 'rgs-69' || issue == 'rgs-70' || issue == 'rgs-71' || issue == 'rgs-72' || issue == 'rgs-82') && (base != 'MVSH')"
		```
		
  - Aba JRE, marque a opção "Alternate JRE": escolha um jdk, Exemplo: jdk1.8.0_101
  
Configuração para rodar no Jenkins:
  - Acessar: Jenkins > Gerenciar Jenkins > Configurar o sistema:
    - Procurar a sessão **JDK** instalações: Verificar se está apontando para o jdk 1.8 instalado. Ex.: Nome: Java 1.8.0 e JAVA_HOME: C:\Program Files\Java\jdk1.8.0_101
	- Procurar a sessão **Maven** instalações: Verificar se está apontando para o maven instalado. Ex.: Nome: Maven e MAVEN_HOME: C:\apache-maven-3.3.9
	

#### Passo a passo para criação de novos cenários BDD:

 - Executar os seguintes comandos para preparar seu ambiente:
 
	Atualize a branch master:
	```
	git pull origin master
	```
	Crie sua branch para o desenvolvimento do seu cenário:
	```
	git checkout nome-da-sua-branch
	```
 - Para cada alteração/desenvolvimento no projeto deve ser executados os seguintes comandos:
  
	Para adicionar todos os arquivos:
	```
	git add .
	```
	ou para cada arquivo:
	```
	git add caminho-do-arquivo
	```
	Após executar um dos comandos acima deve ser feito o commit e adicionar um comentário:
	```
	git commit -m 'Mensagem relacionada à alteração'
	```
	Realizar o envio dos arquivos para o repositório:
	```
	git push origin nome-da-sua-branch
	```
	  
 - Após a finalização de todo o desenvolvimento do cenário em questão, executar os seguintes comandos para realizar o merge da sua branch com a master:
  
	```
	git checkout master
	```
	```
	git pull origin master
	```
	```
	git merge nome-da-sua-branch
	```
  
### Analytics BDD:

Sistema desenvolvido para a infra validar os status de algumas aplicações que serão configuradas no arquivo (src/test/resources/stories/aplicacao/valida_aplicacao.story)

Para fazer o build do projeto siga os passos abaixo:
  - Botão direito no projeto "analytics-bdd", Maven > Run As > Maven build...
  - No campo "Goals" informe: clean verify
    - **OBS.:** Caso queira filtrar por categoria/tags, seguem abaixo alguns exemplos;
	
		Executa os testes que contém as tags: **@aplicacao Efetividade**
		```
		clean verify -Dmetafilter="+aplicacao Efetividade"
		```
		
  - Aba JRE, marque a opção "Alternate JRE": escolha um jdk, Exemplo: jdk1.8.0_101
	
## Procedimentos para a execução

#### Geralmente esses passos são executados uma vez/dia antes de começar as execuções.

 - **Executar os scripts de reset, base "RELOH":**
	- scripts_sql_reset_base_testes/RESET_PRODUTOS.sql
	- scripts_sql_reset_base_testes/RESET_PLANO_DE_PAGAMENTO.sql

	
 - **Fechar/Abrir caixa e PDV; Loja: 291, PDV: 110, Base: "RELOH":**
	- Pois é utilizado nos testes, caso o dado "receber_pedido" esteja setado como true em conexao.json;
	- **Obs.:** Se ao tentar fechar o pdv der o erro: "PDV / ECF possui histórico de uso de NFC-e" devemos executar o sql:

```
delete from (select * from MV_CONTROLE_NFCE where NRO_PDV = 110 and nro_loja_pdv = 1101);
```

 - **Fechar/Abrir caixa e PDV; Loja: 4010, PDV: 110, Base: "RELOH"**
	- Pois é utilizado no testes de nota fiscal de acompanhamento (NotaFiscalDeAcompanhamento) executar via Jenkins job "NOTA FISCAL DE ACOMPANHAMENTO";

 - **Ao executar os testes do selenium, observar a instância que está configurada nos arquivos, variável IP;**
