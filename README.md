# votacoes_cooperativa
Serviço back-end para controlar as votações de uma cooperativa.

Autor: Leonardo Brum Dorneles

Projeto escrito na linguagem Java com o auxílio de pacotes Spring e de outras dependências (ver pom.xml). Os plugins Codota e SonarLint foram adicionados à IDE e auxiliaram no desenvolvimento.

Por questões de performance foi utilizado o JDBC comum para gerenciar o banco. O Spring JPA possui maior facilidade de manutenção, mas sabe-se que não é adequado para aplicações que exijam desempenho.

Pendentes de implementação o serviço de mensageria (será feito por Kafka) e os testes unitários.

Para executar o projet serão necessários:
- Java configurado.
- Uma IDE que provenha bom suporte ao Maven (ex.: IntelliJ IDEA).
- PostgreSQL instalado e configurado.

Para instalar e configurar o PostgreSQL:
- Fazer download da versão 10 ou posterior (disponível em https://www.enterprisedb.com/downloads/postgres-postgresql-downloads).
- Configurar login e senha para banco local (deve ser o mesmo daquele que se encontra no arquivo de propriedades application.yml, em spring.datasource).
- Instalar o PgAdmin mais recente (disponível em https://www.pgadmin.org/download/pgadmin-4-windows/).
- No banco local acessível via PgAdmin, criar nova database com o nome 'cooperativa'.

Feito isso, basta executar a classe VotacaoApplication (ou rodar spring-boot:start) para que a estrutura de banco seja criada automaticamente pelo liquibase e que o serviço seja executado.

Uma vez inicializado o serviço, utilizar o seguinte endereço para interagir com as APIs implementadas: http://localhost:8081/swagger-ui.html#/