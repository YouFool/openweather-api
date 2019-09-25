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
* [TravisCI](https://travis-ci.org/) - Ci fácil de configurar, integrado diretamente com GitHub

### Get Started
Você deve possuir o [Java Development Kit 8](http://openjdk.java.net/install/) e, opcionalmente, o Apache Maven. Além disso, será necessário cadastrar-se no OpenWeather para obter uma **API Key**.

1. Clone este repositório: `git clone https://github.com/YouFool/openweather-api.git`
2. Entre na raiz do projeto: `cd openweather-api/`
3. Gera o artefato com comando: `mvn package`
    3. Alternativamente, caso não possua o Maven instalado: `mvnw package -DskipTests=true`
4. Rode a aplicação executando: `java -jar -Dspring.profiles.active=prod target/openweather-0.0.1-SNAPSHOT.jar`
    4. Outra opção, caso possua [Docker](https://www.docker.com/) instalado, é rodar a aplicação via: `docker build -t openweather . && docker run --name openweather-api openweather` 
5. A aplicação será servida por padrão no endereço `localhost:8086` por padrão
    5. É possível alterar a porta da aplicação no arquivo `application.yml`
    5. Altere a propriedade da linha 14 no arquivo `application.yml` com o valor da sua *API Key*
    5. Logo ao inicializar, o app realizará o import do arquivo `city.list.json` contendo todas as cidades do OpenWeather no MongoDB
    5. Ao printar seguinte linha no console: `OpenWeather cities are now saved!`, a aplicação estará pronta para receber requisições 
6. Importe o arquivo `OpenWeather.postman_collection.json` no seu REST Client favorito
    6. Cadastre uma cidade com nome e código do país via `HTTP POST` no endpoint `/city`
    6. Use o ID da cidade cadastrada para realizar as seguintes requisições nas APIs `/weather` e `/forecast`

