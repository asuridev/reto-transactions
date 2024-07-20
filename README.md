# Prueba Técnica: Sistema de Procesamiento de Transacciones

Para el desarrollo de la prueba se optó por utilizar el framework Quarkus https://quarkus.io/ por todas las ventajas que ofrece para el desarrollo basado en contenedores.

!["arquitectura"](/assets/arquitectura.svg)


## Desarrollo de la API-REST
Para el desarrollo de la API-REST se utilizó una arquitectura de tres capas:
- controller
- persistence
- services

Además se implementó el patrón data-mapper con la finalidad de aislar la capa de servicio de cualquier detalle de implementación en la capas adyacentes.

El patrón data-mapper usa el patrón DTO para pasar los datos entre cada una de las capas.

### Validacion de los datos
Se utilizó la libreria de hibernate-validator para garantizar que no ingresen datos nulos al sistema.
En caso que los datos de entrada no pasen las reglas de validacion el servidor cancelará la solicitud y responderá con un status **400**, Bad-Request.

### Conexión Con la base de datos
Para la capa de persistencia se utilizó el patrón repository, sobre el ORM de Panache Reactive
para controlar la base de datos de Mongo-DB.

La interfaz Reactiva de Panache permite escribir pipeline reactivos mediante la libreria de mutiny, lo que incrementa de 
forma considerable la eficiencia del servidor en el consumo de recursos.
permitiendo de esta manera poder antender un mayor número de solicitudes.

!["pipeline"](/assets/pipeline-panache.png)

### Broker de mensajeria
Se Utilizó el broker de apache kafka, dado que tiene una muy buena integración con Quarkus, y por defecto implementa una 
mensajería reactiva.
El servidor registra sobre el topic **transaction-out** cada una de las transacciones recibidas a través de su endpoint
luego de ser persistida en la base de datos.
 
### 




```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/transaction-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
