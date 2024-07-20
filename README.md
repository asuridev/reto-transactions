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

En caso que los datos de entrada no pasen las reglas de validación el servidor cancelará la solicitud y responderá con un status **400**, Bad-Request.

### Conexión Con la base de datos
Para la capa de persistencia se utilizó el patrón repository sobre el ORM de Panache Reactive
para controlar la base de datos de Mongo-DB.

La interfaz Reactiva de Panache permite escribir pipeline reactivos mediante la libreria de mutiny, lo que incrementa de 
forma considerable la eficiencia del servidor en el consumo de recursos.

Permitiendo de esta manera poder antender un mayor número de solicitudes.

!["pipeline"](/assets/pipeline-panache.png)

### Broker de mensajeria
Se Utilizó el broker apache kafka, dado que tiene una muy buena integración con Quarkus, y por defecto implementa una 
mensajería reactiva.
El servidor registra sobre el topic **transaction-out** cada una de las transacciones recibidas a través de su endpoint
luego de ser persistida en la base de datos.
 
## Deployment del proyecto.
Para el despliegue del proyecto seguir los siguientes pasos:

1. Clonar el repositorio.

```shell script
git clone https://github.com/asuridev/reto-transactions.git
```
2. Ingresar a la raíz del proyecto.

```shell script
cd reto-transactions
```
3. Ejecutar docker-compose

```shell script
docker-compose up --build
```
Este ultimo comando construirá un cluster de docker-compose con cinco contenedores

!["docker-desktop"](/assets/compose.png)

- zookeper-1:  contenedor para el funcionaminto del broker de mensajería kafka.
- kafka-1: corresponde al broker de mensajeria.
- sofka-db: contenedor con la base de datos de Mongo-DB
- kafka-ui: Herraminta para gestionar el broker de mensajería (kafka) mediante una interfaz gráfica.
- api-1: corresponde a la aplicación, la API-REST 

## Verificando el funcionaminto del proyecto.

### Validación del endpoint
La API-REST expone el endpoint en el puerto 4000, en la url:

```
localhost:4000/api/v1/transaction
```

Mediante una solicitud POST con los campos requeridos registrará la transacción en la base de datos y el broker de mensajería.
```
{
    "transactionId": "3G4gurtrt4514",
    "timestamp": 1616575685591,
    "deviceNumber": 452,
    "userId": 72258456,
    "geoPosition":{ 
        "latitude":25.46,
        "longitude": 45.35
    },
    "amount": 3400.25
}
```

> **_NOTA:_**  Ver la documentación publicada en el siguinte enlace: https://documenter.getpostman.com/view/19057359/2sA3kUGNFj

### Consulta de la Base de datos.
Luego de ingresar varios registros de forma exitosa a traves del endpoint, se puede realizar la conexión a la base de datos para verificar la persistencia de los mismos.

El cluster de docker-compose expone el puerto **27017** para permitir la conexión, mediante un cliente gráfico (MongoDB Compass).

URL de conexión a la base de datos:

```
mongodb://localhost:27017
```
!["docker-desktop"](/assets/compass.png)

> **_NOTA:_** El nombre de la base de datos es _transactionDb_ y la collección tiene como nombre _transactions_.

### Validando el registro de los Mensajes en el broker de kafka.
El cluster de docker-compose expone un servicio web en el puerto **80** que permite administrar de forma visual el broker de kafka.

URL del servicio: http://localhost/ui/clusters/sofka/all-topics/transaction-out

!["kafka-ui"](/assets/kafka-ui.png)

> **_NOTA:_**  Ingresar al tab messages para visluazar en detalle cada uno de los mensajes registrados en el broker.

## Prueba de Rendimiento
Para probar el rendimiento del sistema se utilizo la herramienta apache jMeter https://jmeter.apache.org/ .

Jmeter es una aplicación open source muy utilizada por la comunidad para testear servicios web.

Se configuró la herraminta con 10.000 Threads(usuarios) en un lapso de 60 segundos.

!["kafka-ui"](/assets/test2.png)

Dando como resultado una prueba 100% exitosa, donde  todas las solicitudes fueron atendidas de forma satisfactoria por el sistema.

Demostrando el consumo eficiente de los recursos de parte del servidor.

!["kafka-ui"](/assets/test1.png)

> **_NOTA:_** En el repositorio se encuentra el archivo **test.jmx** con las configuraciones realizadas en Jmeter para la prueba, con el objetivo de poder replicarla.
