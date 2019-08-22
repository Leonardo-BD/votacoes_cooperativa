# Serviço gerenciador de votações
Serviço back-end para controlar as votações de uma cooperativa.

Autor: Leonardo Brum Dorneles

Sobre questões técnicas gerais:
- Projeto escrito na linguagem Java 8 com o auxílio de pacotes Spring e de outras dependências (ver pom.xml). Os plugins Codota e SonarLint foram adicionados à IDE e auxiliaram no desenvolvimento.
- Um teste de integração foi implementado. Em se tratando de uma demonstração técnica, apenas um fluxo de uso completo foi abrangido.
- O gerenciador de dependências é o Maven. Gradle poderia ter sido utilizado sem maiores problemas, entretanto.
- O banco de dados utilizado foi o PostgreSQL por questões de maior afinidade.

Uma vez inicializada a aplicação, as chamadas de API podem ser testadas individualmente por meio do Swagger, acessível em http://localhost:8081/swagger-ui.html#/

A validação externa do CPF do associado é feita via API externa.

A mensageria foi implementada via Kafka.

A performance não foi testada diretamente com medições, mas foi uma questão considerada durante o desenvolvimento. Por esta razão o banco é acessado com JDBC e não com o versátil JPA. Sabe-se que o segundo não é adequado para aplicações que exijam desempenho.

O suporte a versionamento de API está no projeto, mas não precisou ser explorado. Sobre a abordagem adotada:
- Utilizei a URL para tal, por questão de ser mais prático para o cliente fazer a transição.
- Ainda assim, duplicação de código não tende a ser um problema. A versão escolhida vai para uma variável que é validada pelo método do serviço, podendo este alterar o comportamento da resposta conforme a escolhida.
- Para evitar complicações em casos que requiram alterar o tipo de retorno do método, pode-se trabalhar exclusivamente com retornos do tipo String, sendo o conteúdo desta um JSON gerado dentro do método. Aqui não foi utilizada esta abordagem por questão de praticidade.


A respeito da execução do projeto, serão necessários:
- Java 8 configurado.
- Uma IDE que provenha bom suporte ao Maven (ex.: IntelliJ IDEA).
- PostgreSQL instalado e configurado.
- Apache Zookeper configurado (para teste de mensageria).
- Apache Kafka configurado (para teste de mensageria).

Para instalar e configurar o PostgreSQL:
- Fazer download da versão 10 ou superior (disponível em https://www.enterprisedb.com/downloads/postgres-postgresql-downloads).
- Configurar login e senha para banco local (deve ser o mesmo daqueles que se encontram nos arquivos de propriedades application.yml e application-test.yml, na seção spring.datasource).
- Instalar o PgAdmin mais recente (disponível em https://www.pgadmin.org/download/pgadmin-4-windows/).
- No banco local acessível via PgAdmin, criar duas novas databases, uma com o nome 'cooperativa' e outra com o nome 'cooperativa-test'.

Para configurar o Kafka e o Zookeper: https://dzone.com/articles/running-apache-kafka-on-windows-os

Feito isso, basta executar a classe VotacaoApplication (ou rodar spring-boot:start) para que a estrutura de banco seja criada automaticamente pelo liquibase na primeira execução.