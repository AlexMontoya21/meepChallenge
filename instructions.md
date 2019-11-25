*********************************************************

#INSTRUCCIONES

1. Abre la consola : (windows + R) y escribe "cmd". Pulsa intro.
2. Escribe cd + ruta donde esta el proyecti. Example: cd C:/Users/Alex/git/meep
3. Clean y build del proyecto: gradlew clean build. 
4. Levanta el servidor: gradlew bootRun 
5. Abre tu navegador favorito y escribe http://localhost:8080/vehicles

Se puede ver como se va escribiendo la informacion de los vehiculos disponibles y no disponibles (disponibles con available = true y no disponibles con available = false)

Esto permitiria tener una conexion abierta con el frontal y que se fuera actualizando al usuario los vehiculos que puede coger en su zona.

Hemos optado por una opcion SSE, ya que un polling requiere peticiones constantes del front-back y de esta manera se mantiene una unica conexion abierta y actualizada en tiempo real y para este caso puesto que no ibamos a realizar mas comunicacion con el cliente y por facilidad de configuracion, hemos deshechado la posibilidad de hacerlo con un WebSocket.

En cuanto a la escabilidad, al ser un microservicio podria ser totalmente escalable, el problema que se puede presentar a futuro seria no escalar los servidores de meep al escalar los microservicios que hacen peticiones a este, pudiendolo así saturar. Como soluciones se pueden plantear la instalacion de los servidores en una plataforma en la nube con posibilidad de autoescalado en la cual se crean y se destruyen tantas maquinas como sean necesarias en funcion del numero de peticiones que se realizan al servidor y se paga solo por su uso.

Me he limitado a desarrollar unicamente lo que solicitabais e indicabais que queriaís ver, por eso en este caso no hay tests.


