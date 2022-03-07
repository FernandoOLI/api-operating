# API-Operating
Api para cadastro de vendedores e regiões de atuação.
####
Existem 4 serviços:
####1 - POST /vendedor
####
```json
{
  "name": "fernando",
  "phone": "34324324",
  "age": "35",
  "city": "pouso alegre",
  "state":"SP",
  "region":"Sudeste"
}
```

####2 - POST /atuacao
####
```json
{
  "states": ["SP","RJ","MG","ES"],
  "region": "sudeste"
}
```

####3 - GET /vendedor
####
```json
[
  {
    "name": "fernando",
    "phone": "34324324",
    "age": 35,
    "city": "pouso alegre",
    "state": "SP",
    "states": [
      "SP",
      "RJ",
      "MG",
      "ES"
    ]
  }
]
```

####4 - GET /vendedor/{id}
####
```json
{
  "name": "fernando",
  "dataInclusao": "2022-03-07T03:00:00.000+0000",
  "states": [
    "SP",
    "RJ",
    "MG",
    "ES"
  ]
}
```


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

### Postman
Arquivo com as requisições get e post:
```cmd
src/main/resources/postaman/Operating.postman_collection.json
```

#### Próximos Passos

Criar arquivo Docker para o projeto;

