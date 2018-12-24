## Calculator API REST

### How to run locally?
Just run `./mvnw clean spring-boot:run`

## APIs
```
/add/{a}/{b}/{c}
/subtract/{a}/{b}/{c}
/multiply/{a}/{b}/{c}
/divide/{a}/{b}
```

### Swagger URL
Go to `http://localhost:8080/calc/swagger-ui.html`

###Some cURL examples !

#### Addition:
`curl -X GET --header 'Accept: application/json' 'http://localhost:8080/calc/api/calculator/add/9/8/3'`

#### Subtraction
`curl -X GET --header 'Accept: application/json' 'http://localhost:8080/calc/api/calculator/subtract/10/2/20'`

#### Multiplication
`curl -X GET --header 'Accept: application/json' 'http://localhost:8080/calc/api/calculator/multiply/2/2/2'`

#### Division
`curl -X GET --header 'Accept: application/json' 'http://localhost:8080/calc/api/calculator/divide/100/2'`