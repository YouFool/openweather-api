# Desafio - OpenWeather API 
[![Build Status](https://travis-ci.org/YouFool/openweather-api.svg?branch=master)](https://travis-ci.org/YouFool/openweather-api)

Desenvolver uma aplicação WEB que permite cadastrar cidades e exibir a previsão do
tempo.

### Especificação da demanda

A aplicação possui 2 telas:
* Tela de cadastro de cidade e listagem das cidades cadastradas: Permitir o usuário
cadastrar somente cidades válidas na API.
* Na listagem de cidades deve ter um link para visualizar os detalhes da previsão (Tela de
detalhe das previsões)
* Tela de detalhe das previsões: Exibe um “forecast” de 5 dias para a cidade
Selecionada.


### Especificação Técnica:
* Permitido usar bibliotecas externas
* Utilizar a API publica do [OpenWeather](http://openweathermap.org/api)
* Gravar cidades em arquivo físico/banco de dados.
* A arquitetura e design da implementação devem ser explicados em um
README.md passando uma visão geral e também conter os passos necessários
para importar/rodar o projeto

### Requerimentos explícitos
* Usar Java no backend
* Desenvolver telas em HTML (pode ser usado frameworks)

---

### Stack
* Java 8 - Maior compatibilidade com dependências externas
*  [Spring Framework](https://spring.io/) - Inicio rápido do projeto, vários módulos que aceleram o desenvolvimento da aplicação
    * [Spring Boot](https://spring.io/projects/spring-boot)
    * [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
    * [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)
* [Maven](https://maven.apache.org/)
* [Lombok](https://github.com/rzwitserloot/lombok) - Previne a geração de *boilerplate code* 
* [JUnit](https://github.com/junit-team/junit5)
* [Mockito](https://github.com/mockito/mockito) - Mock de dependências externas com facilidade
* [AssertJ](https://github.com/joel-costigliola/assertj-core) - Assertions fluidas
* [H2 | In-Memory Database](https://www.h2database.com/html/main.html) - Banco de dados em memória sem necessidade de configuração
* [Flyway | Database Migrations Made Easy](https://github.com/flyway/flyway) - Migração de banco sem dificuldade
* [Embedded MongoDB](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo) - Possibilidade de importar o arquivo `city.list.json` via `mongoimport` no start da  aplicação
* [TravisCI](https://travis-ci.org/) - CI fácil de configurar, integrado diretamente com GitHub

### Get Started
Você deve possuir o [Java Development Kit 8](http://openjdk.java.net/install/) e, opcionalmente, o Apache Maven. Além disso, será necessário cadastrar-se no OpenWeather para obter uma **API Key**.

* Clone este repositório: `git clone https://github.com/YouFool/openweather-api.git`
* Entre na raiz do projeto: `cd openweather-api/`
* Gera o artefato com comando: `mvn package`
   * Alternativamente, caso não possua o Maven instalado: `mvnw package -DskipTests=true`
* Instale o [Docker](https://www.docker.com/) 
* Assim que instalado, basta rodar a aplicação com o comando: `docker-compose up --build` usando o arquivo `docker-compose.yaml` do repositório como base 
* O backend será servido no endereço `localhost:8086` por padrão
    * É possível alterar a porta da aplicação no arquivo `application.yml`
    * Altere a propriedade da linha 14 no arquivo `application.yml` com o valor da sua *API Key*
    * Após ao inicializar, realize o import do arquivo `city.list.json` contendo todas as cidades do OpenWeather no MongoDB com os seguintes comandos:
        * `docker cp static/city.list.json mongo:/tmp/city.list.json`
        * `docker exec -it mongoimport -d test -c city --jsonArray --file /tmp/city.list.json` 
* Importe o arquivo `OpenWeather.postman_collection.json` no seu REST Client favorito
    * Cadastre uma cidade com nome e código do país via `HTTP POST` no endpoint `/city`
    * Use o ID da cidade cadastrada para realizar as seguintes requisições nas APIs `/weather` e `/forecast`

