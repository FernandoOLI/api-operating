# API-Operating

### Requisitos e Instalação 
#### Requisitos:
```cdm
Java 11
Apache Maven 3.8.4 
```
#### Instalação 
Idle recomendada Intellij.

No terminal, executar 
```cmd
mvn clean install 
```
Executar o arquivo Main que se encontra no caminho:
```cmd
src/main/java/com/operating/api/Main.java
```

### H2 Database
Para consultar os valores salvos no banco de dados, consultar:
```cmd
http://localhost:8080/h2-console
```
Url jdbc:h2:mem:operatingDB
#####
Driver org.h2.Driver
#####
Usuário é "sa" e a senha é vazia

#### Próximos Passos

Criar arquivo Docker para o projeto;

