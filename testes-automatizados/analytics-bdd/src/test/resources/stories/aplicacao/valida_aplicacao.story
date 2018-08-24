Scenario: Valida o status de aplicacao Catman
Meta:
@tag component:Aplicações
@aplicacao Catman

Given Eu acesso a aplicacao https://catman.softbox.com.br/site/login
When Eu preencho o campo #LoginForm_username com o valor catman/monitoria
And Eu preencho o campo #LoginForm_password com o valor monitoria
And Eu clico no botao .sbx-md-button-flat.sbx-md-cor-primaria.sbx-md-bnt-login
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento #container-novidades

Scenario: Valida o status de aplicacao Pine
Meta:
@tag component:Aplicações
@aplicacao Pine

Given Eu acesso a aplicacao https://pine.softbox.com.br/site/login
When Eu preencho o campo #LoginForm_username com o valor pine/monitoria
And Eu preencho o campo #LoginForm_password com o valor S0ftB0x@#$%2017
And Eu clico no botao .button
Then Eu verifico o status da aplicacao


Scenario: Valida o status de aplicacao Catman-Ice
Meta:
@tag component:Aplicações
@aplicacao Catman-Ice

Given Eu acesso a aplicacao http://catmanice.softbox.com.br/site/login
When Eu preencho o campo #LoginForm_username com o valor catmanice/MONITORIA
And Eu preencho o campo #LoginForm_password com o valor S0ftB0x@#$%2017
And Eu clico no botao .sbx-md-button-flat.sbx-md-cor-primaria.sbx-md-bnt-login
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento .material-icons

Scenario: Valida o status de aplicacao Efetividade
Meta:
@tag component:Aplicações
@aplicacao Efetividade

Given Eu acesso a aplicacao https://efetividade.softbox.com.br/site/login
When Eu preencho o campo #LoginForm_username com o valor efetividade/MONITORIA
And Eu preencho o campo #LoginForm_password com o valor softbox!eldoc!123
And Eu clico no botao .button
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento .icon

Scenario: Valida o status de aplicacao Cashlink
Meta:
@tag component:Aplicações
@aplicacao Cashlink

Given Eu acesso a aplicacao http://cashlinkbi.softbox.com.br/site/login
When Eu preencho o campo #LoginForm_username com o valor fibra/monitoria
And Eu preencho o campo #LoginForm_password com o valor monitoria1
And Eu clico no botao .sbx-md-button-flat.sbx-md-cor-primaria.sbx-md-bnt-login
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento .material-icons

Scenario: Valida o status de aplicacao Eldoc_MV
Meta:
@tag component:Aplicações
@aplicacao Eldoc_MV

Given Eu acesso a aplicacao http://10.25.238.15:8080/eldoc/
When Eu preencho o campo #user.ui-widget-content.ui-corner-all com o valor softbox@softbox.com.br
And Eu preencho o campo #password.ui-widget-content.ui-corner-all com o valor softbox!eldoc!123
And Eu clico no botao .ui-button-text
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento #ajuda.help

Scenario: Valida o status de aplicacao Eldoc_MV_Lojas_Fisicas
Meta:
@tag component:Aplicações
@aplicacao Eldoc_MV_Lojas_Fisicas

Given Eu acesso a aplicacao http://eldoc.maquinadevendas.corp/eldoc/
When Eu preencho o campo #user.ui-widget-content.ui-corner-all com o valor softbox@softbox.com.br
And Eu preencho o campo #password.ui-widget-content.ui-corner-all com o valor softbox!eldoc!123
And Eu clico no botao .ui-button-text
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento #ajuda.help

Scenario: Valida o status de aplicacao Kelex
Meta:
@tag component:Aplicações
@aplicacao Kelex

Given Eu acesso a aplicacao http://mv.kelex.com.br/Login
When Eu preencho o campo #login.form-control com o valor monitoria@softbox.com.br
And Eu preencho o campo #senha.form-control com o valor kelexmonitoria
And Eu clico no botao #btLogin.btn.btn-success.bg-blue-ace.btn-block
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento .content-header

Scenario: Valida o status de aplicacao Sistema_GPC
Meta:
@tag component:Aplicações
@aplicacao Sistema_GPC

Given Eu acesso a aplicacao https://sistemagpc.softbox.com.br/eldoc_hpf_view/#/login
When Eu preencho o campo #login_username com o valor suporte@softbox.com.br
And Eu preencho o campo #login_password com o valor NocSoftbox0310
And Eu clico no botao .width-35.pull-right.btn.btn-sm.btn-primary
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento #navbar-container

Scenario: Valida o status de aplicacao SERD
Meta:
@tag component:Aplicações
@aplicacao SERD

Given Eu acesso a aplicacao http://www.serd.com.br/eldoc_unilever/
When Eu preencho o campo #user.ui-widget-content.ui-corner-all com o valor suporte@softbox.com.br
And Eu preencho o campo #password.ui-widget-content.ui-corner-all com o valor softbox!eldoc!123
And Eu clico no botao .ui-button-text
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento #ajuda.help

Scenario: Valida o status de aplicacao Suvinil
Meta:
@tag component:Aplicações
@aplicacao Suvinil

Given Eu acesso a aplicacao http://suvinilon.suvinil.com.br/
When Eu preencho o campo #username.ng-pristine.ng-empty.ng-invalid.ng-invalid-required.ng-touched com o valor qaprodutos@softbox.com.br
And Eu preencho o campo #password.ng-pristine.ng-empty.ng-invalid.ng-invalid-required.ng-touched com o valor Tradelinks@suvinil1
And Eu clico no botao .bottom-pos
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento #main

Scenario: Valida o status de aplicacao CoachingUFS
Meta:
@tag component:Aplicações
@aplicacao CoachingUFS

Given Eu acesso a aplicacao http://www.coachingufs.com.br/Authentication
When Eu preencho o campo #Username com o valor 000381282
And Eu clico no botao .btn.btn-orange.btn-block
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento .navbar-inner

Scenario: Valida o status de aplicacao Vortex
Meta:
@tag component:Aplicações
@aplicacao Vortex

Given Eu acesso a aplicacao https://vortex.softbox.com.br
When Eu preencho o campo #input-usuario com o valor qaprodutos@softbox.com.br
And Eu preencho o campo #input-senha com o valor Qav0rtt3xmonnit0r
And Eu clico no botao #button-entrar
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento #opcoes-menu

Scenario: Valida o status de aplicacao Execução
Meta:
@tag component:Aplicações
@aplicacao Execução

Given Eu acesso a aplicacao https://www.execucaounilever.com.br/#/login
When Eu preencho o campo #input_0 com o valor 1010
And Eu preencho o campo #input_1 com o valor s0ftb0xm0n1t0r3
And Eu clico no botao .login-btn.md-raised.md-primary.submit.md-button.md-ink-ripple.flex-grow
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento .ng-scope

Scenario: Valida o status de aplicacao Tobias
Meta:
@tag component:Aplicações
@aplicacao Tobias

Given Eu acesso a aplicacao https://tobias.softbox.com.br/tobias/
When Eu preencho o campo #login-email com o valor infra@softbox.com.br
And Eu preencho o campo #login-senha com o valor sj28yab8yp
And Eu clico no botao .btn.btn-block.btn-primary.mt-lg.ng-scope
Then Eu verifico o status da aplicacao
And Eu valido a presenca do elemento .img-responsive