# TestApiRest
Automação de teste de API em Java com RestAssured utilizando o servidor: http://jsonplaceholder.typicode.com/users

## Configurações:
- Dependências: rest-assured 5.3.1, junit 4.13.2
- A estrutura do projeto contém uma interface chamada "Constants.java" onde as contantes do projeto estão armazenadas, na "BaseTest.java" a configuração do projeto está definida na classe "setup()" e os métodos de teste estão no package= "tests".
- Para gerar o relatório de execução em HTML basta executar a suite de testes ou o comando mvn test -DcustomerCode=DEV
- Clonar o repositório através do link: https://gitlab.com/glauberfo/
- Instalar o java 1.8 (jdk1.8.0_271)
- Instalar o Apache Maven 3.9.4
- IDE utilizada: IntelliJ

## Execução:
- A estrutura do projeto contém uma interface chamada "Constants.java" onde as contantes do projeto estão armazenadas, na "BaseTest.java" a configuração do projeto está definida na classe "setup()" e os métodos de teste estão no package= "tests".
- Para executar o projeto basta digitar o comando "mvn test"
- Para gerar o relatório de execução em HTML basta executar a suite de testes ou o comando mvn test -DcustomerCode=DEV
- O Framework já está preparado para executar em mais de 1 ambiente, basta preencher o a variável APP_BASE_URL_HML no arquivo de contantes e alterar o comando de execução para mvn test -DcustomerCode=HML