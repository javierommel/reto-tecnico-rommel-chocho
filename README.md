# reto-tecnico-rommel-chocho

**Consideraciones Despliegue

    # 1. Clona el repositorio
    git clone https://github.com/javierommel/reto-tecnico-rommel-chocho.git
    cd reto-tecnico-rommel-chocho

    # 2. Compilar microservicio ms-clientes-personas
    - cd ms-clientes-personas
    - mvn clean package -DskipTests

    # 3. Compilar microservicio ms-cuentas.movimientos
    - cd ms-cuentas-movimientos
    - mvn clean package

    # 4. Configura tus variables de entorno en el archivo .env con los valores de tus configuraciones
    - cp .env.example .env

    # 5. Desplegar solución completa
    - docker composer up --build

**Consideraciones Test (Pruebas Unitarias y de Integración)

    Seguir los siguientes pasos para realizar las pruebas Unitarias y de Integración

    - cd ms-clientes-personas
    - mvn test

    (Se tiene una prueba para el repositorio y la prueba de Integración para una petición completa como prueba de Integración. )

**Consideraciones Pruebas Aplicación

    Se tiene un archivo prueba_tecnica.postman_collection.json que se debe abrir con Postman
    Dentro de Postman ejecutar en el siguiente orden para cumplir con los casos de uso.
    Cliente
    - create cliente 1
    - create cliente 2
    - create cliente 3
    Realizar un findAll al microservicio de clientes para obtener los clienteId y poder reemplazar en las creaciones de cuenta
    - create cuenta 1
    - create cuenta 2
    - create cuenta 3
    - create cuenta 4
    - create cuenta 5
    - create movimiento 1
    - create movimiento 2
    - create movimiento 3
    - create movimiento 4
    - reportemovimientos

    Para realizar otras pruebas tomar en cuenta el campo clientId cuando se crea un cliente que es nuestro identificador para los clientes.

