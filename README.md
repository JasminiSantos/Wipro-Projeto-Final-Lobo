<div style="display: inline_block" align="center">
  <br>
  <img align="center" alt="Logo Wipro" title="Wipro" height="200"  width="300" src=".github/wipro_logo.png">
  <img align="center" alt="Logo Gama Academy" title="Gama Academy" height="70" width="300" src=".github/gama_academy_logo.png">
</div>

<br>

<h1 align="center"> Wipro Bootcamp - Desafio Final</h1>

<br>

A **Wipro** está revolucionando e trazendo uma novidade para os seus clientes. O desafio será elaborar uma aplicação que faça o **gerenciamento de conta bancária**, permitindo que sejam feitas as operações de criação, consulta e remoção, além das operações de saque, depósito e essa conta também deverá ter um cartão de crédito.

O projeto será desenvolvido em três sprints.

## Grupo 9 - Squad Lobo 🐺‍

Integrantes:

🐺‍👧 [Jasmini Santos](https://github.com/JasminiSantos)

🐺‍🧑‍ [Paulo Henrique](https://github.com/paulohenriquepaulo)

🐺‍🧑‍ [Roberto Brito](https://github.com/RobertoBrito)

🐺‍🧑‍ [Thiago Araujo](https://github.com/thiagoaraujodev)

## 📰 Organização do Projeto

Na etapa inicial definimos todos os entregáveis previstos definidos na primeira sprint. Já na segunda sprint, desenvolvemos uma API para acesso via Web substituindo a interface console utilizada na primeira etapa com todas as operações anteriores incluídas nesta API. Na terceira sprint, inserimos os testes unitários com o Junit.

Entre as principais etapas planejadas a partir da primeira sprint, optamos por:

- Utilizar o Trello para elaboração do Kanban
- Criação do backlog
- Detalhamento descritivo das tarefas da squad
- Formatação do kanban padrão "to do, doing, done"
- Priorização dos cards com cores identificando os níveis de prioridade

## 💻 Quadro Kanban

![Quadro Kanban - Squad Lobo](.github/kanban.png "Quadro Kanban - Squad Lobo")

<br>

## 💻 Caso de Uso

![Caso de Uso - Squad Lobo](.github/caso_uso.png "Caso de Uso - Squad Lobo")

## 💻 Diagrama Entidade Relacionamento

![Diagrama Entidade Relacionamento - Squad Lobo](.github/diagrama.png "Diagrama Entidade Relacionamento - Squad Lobo")

## 🚀 Plano de execução - Sprint 1

Todo plano de execução foi demosntrado no repositório da primeira sprint. Caso tenha interesse de visualisar tudo que foi definido para sa execução poderá ser visualizado em:

[Link Projeto Final - Sprint 1](https://github.com/thiagoaraujodev/Wipro-Projeto-Final)

## 🚀🚀 Plano de execução - Sprint 2

Foram aproveitados boa parte do código e classes desenvolvidas na primeira sprint, alguns métodos precisaram ser remodelados e outros criados do zero e todos os apontamentos que ficaram no backlog para serem desenvolvidos nesta segunda sprint foram concluídos com sucesso.

- Desenvolver o método Transferência entre Contas
- Desenvolver o método Histórico de Transações
- Desenvolver a API
- Persistir os dados no MySQL

## 📣 Endpoints disponíveis na API

Para um melhor entendimento de como utilizar a API desenvolvida neste projeto, disponibilizamos todas as rotas com seus respectivos exemplos de como utilizá-las.


📭 **Cadastra um Cliente e Cria uma Conta** 

**Tipo:** POST

**Rota:** http://localhost:8080/conta

- Este endpoint é responsável por fazer a criação de uma conta a partir das informações pessoais do cliente informada nos parâmetros requeridos. Se todos os dados informados forem validos, o cliente é inserido no sistema e atribuímos uma conta. Caso a renda mensal informada pelo cliente seja menor que R$: 2.000,00 será atribuida uma conta do tipo conta corrente e caso seja maior ou igual a este valor uma conta especial será atribuída com um valor de limite especial de 10% sobre a renda informada.

![Cria o Cliente e uma Conta](.github/conta_cliente.gif "Cria o Cliente e uma Conta")

---

📭 **Exibe Todas as Contas Corrente** 

**Tipo:** GET

**Rota:** http://localhost:8080/conta/corrente

- Este endpoint é respónsvel por exibir todas as contas com suas informações definidas pelo tipo conta corrente. Caso não tenha nehuma conta definida no sistema ele retorna um array vazio.

![Exibe Todas as Contas Corrente](.github/contas_corrente.gif "Exibe Todas as Contas Corrente")

---

📭 **Exibe Todas as Contas Especiais** 

**Tipo:** GET

**Rota:** http://localhost:8080/conta/especial


- Este endpoint é respónsvel por exibir todas as contas com suas informações definidas pelo tipo conta especial. Caso não tenha nehuma conta definida no sistema ele retorna um array vazio.

![Exibe Todas as Contas Especiais](.github/conta_especial.gif "Exibe Todas as Contas Especiais")

---

📭 **Exibe uma Conta Pelo Número da Conta** 

**Tipo:** GET

**Rota:** http://localhost:8080/conta/{numeroConta}

- Este endpoint é respónsvel por exibir as informações de uma conta definida em sua rota pelo número da conta. Caso a conta solicitada não exista no sistema, será devolvido um erro personalizado com status 404 Not Found, com uma mensagem de "Conta não encontrada!".

![Exibe Dados da Conta](.github/conta_numero_conta.gif "Exibe Dados da Conta")

---

📭 **Exibe as Movimentações da Conta** 

**Tipo:** GET

**Rota:** http://localhost:8080/conta/extrato/{numeroConta}

- Este endpoint é respónsvel por exibir as informações de movimentações efetuadas em uma conta, definida em sua rota pelo número da conta. Caso a conta solicitada não exista no sistema, será devolvido um erro personalizado com status 404 Not Found, com uma mensagem de "Está conta não possui movimentações!".

![Exibe as Movimentações da Conta](.github/extrato.gif "Exibe as Movimentações da Conta")

---

📭 **Efetuar Depósito** 

**Tipo:** PATCH

**Rota:** http://localhost:8080/conta/deposito/{numeroConta}

- Este endpoint é respónsvel por efetuar um depósto em uma conta definida em sua rota pelo número da conta, atribuindo como parâmetro o valor do depósito atualizando o saldo da conta informada. Caso a conta solicitada não exista no sistema, será devolvido um erro personalizado com status 404 Not Found, com uma mensagem de "Conta não encontrada!".

![Efetua Depósito](.github/deposito.gif "Efetua Depósito")

---

📭 **Efetua Saque** 

**Tipo:** PATCH

**Rota:** http://localhost:8080/conta/saque/{numeroConta}

- Este endpoint é respónsvel por efetuar um saque em uma conta definida em sua rota pelo número da conta, atribuindo como parâmetro o valor do saque atualizando o saldo da conta informada desde que contenha saldo disponível na conta. Caso a conta solicitada não exista no sistema, será devolvido um erro personalizado com status 404 Not Found, com uma mensagem de "Conta não encontrada!".

![Efetua Saque](.github/saque.gif "Efetua Saque")

---

📭 **Efetua Transferência Entre Contas** 

**Tipo:** PATCH

**Rota:** http://localhost:8080/tranferencia

- Este endpoint é respónsvel por efetuar um transferência entre contas definindo seus atributos como parâmetro o número da conta de origem, o número da conta de destino e o valor da transferência, atualizando o saldo da conta de origem desde que contenha saldo disponível na conta para concluir a operação e atualizando o saldo da conta de destino. Caso a conta solicitada não exista no sistema, será devolvido um erro personalizado com status 404 Not Found, com uma mensagem de "Conta não encontrada!".

![Efetua Transferência Entre Contas](.github/transferencia.gif "Efetua Transferência Entre Contas")

---


📭 **Deleta Conta / Desativa** 

**Tipo:** DELETE

**Rota:** http://localhost:8080/conta/{numeroConta}

- Este endpoint é respónsvel por deletar "desativar" uma conta, definida em sua rota pelo número da conta. Caso a conta solicitada não exista no sistema, será devolvido um erro personalizado com status 404 Not Found, com uma mensagem de "Conta não encontrada!".

![Deleta Conta / Desativa](.github/deletar_conta.gif "Deleta Conta / Desativa")

---

📭 **Exibe Todos os Clientes** 

**Tipo:** GET

**Rota:** http://localhost:8080/cliente

- Este endpoint é respónsvel por exibir todos os clientes com suas informações. Caso não tenha nehuma conta definida no sistema ele retorna um array vazio.

![Exibe Todos os Clientes](.github/clientes.gif "Exibe Todos os Clientes")

---

📭 **Exibe Cliente Pelo CPF** 

**Tipo:** GET

**Rota:** http://localhost:8080/cliente/{CPF}

- Este endpoint é respónsvel por exibir as informações de um cliente definida em sua rota pelo número do CPF. Caso o CPF informado não exista no sistema, será devolvido um erro personalizado com status 404 Not Found, com uma mensagem de "CPF não encontado!".

![Exibe Cliente Pelo CPF](.github/cliente_cpf.gif "Exibe Cliente Pelo CPF")

---

📭 **Atualizar Cliente** 

**Tipo:** PUT

**Rota:** http://localhost:8080/cliente/{CPF}

- Este endpoint é respónsvel por atualizar as informações de um cliente definida em sua rota pelo número do CPF, repassando seus atributos como nome, data de nascimento, telefone, renda mensal, e o cpf também deve ser informado como parâmetro e consequentemente tem que ser o mesmo cpf definido na rota. Caso o CPF informado não exista no sistema, será devolvido um erro personalizado com status 404 Not Found, com uma mensagem de "CPF não encontado!".

![Atualizar Cliente](.github/cliente_atualizar.gif "Atualizar Cliente")

---

## 🚀🚀🚀 Plano de execução - Sprint 3

Nesta etapa inserimos alguns testes unitários utilizando o Junit5 e o Mockito. Estabelecemos como meta inicial a cobertura de 50% dos métodos e conseguimos alcançar 69,4%. Todos sabemos que é necessário testar, e com a ajuda do Mockito temos uma ótima ferramenta para isso, pois, temos a possibilidade de isolar comportamentos e testar pequenas partes que se torna uma grande estratégia para garantir a qualidade do código.

![Testes Unitários](.github/teste_unitario.png "Testes Unitários")


## 📣 Slide apresentação final

🐺‍‍ [Slide Apresentação](https://www.canva.com/design/DAE_HEmPBC8/_5x6G1VSrd6KrIHKY0HQdg/view?utm_content=DAE_HEmPBC8&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

## ⚙️ Como inicializar a aplicação

```ps
# Clone o repositorio utilizando o git
$ git clone https://github.com/thiagoaraujodev/Wipro-Projeto-Final-Lobo.git

# Acesse a pasta do projeto
$ cd Wipro-Projeto-Final-Lobo
```
<br>

Para inicializar é muito simples:

1. Faça um clone do repositório como no exemplo.
2. Em seguida abra a IDE Java de sua preferência.
3. Importe o projeto do tipo Maven.
4. Para executar a aplicação você precisa do MySql instalado em sua maquina.
5. Localize o arquivo application.properties dentro do pacote src/main/resources
6. Altere as informações correspondente com os seu dados de acesso ao MySql:

```
// Altere o nome SuaPorta para o número da sua porta de acesso do Tomcat. Ex.:8080
server.port=${DB_PORT:SuaPorta}

// Altere o nome SeuUsuario pelo nome de usuário do seu MySql
spring.datasource.username=${DB_USERNAME:SeuUsuario}

// Altere o nome SuaSenha pela sua senha do seu MySql
spring.datasource.password=${DB_PASSWORD:SuaSenha}
```

7. Coloque a aplicação para rodar em sua IDE.
8. Agora acesse as rotas mostradas anteriormente, não esquecendo de alterar a porta caso a sua não seja: 8080

Neste projeto foi utilizado:

- [Java 11](https://www.oracle.com/java/)
- [Eclipse](https://www.eclipse.org/)
- [Mysql](https://www.mysql.com/)
- [spring](https://start.spring.io/)


### 📝 License 

Este projeto está sob a licença do MIT. Consulte a [LICENSE](LICENSE) para obter mais informações.
