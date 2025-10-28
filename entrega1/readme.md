### PROGRAMA : GENERAR UNA API UTILIZANDO LO VISTO 
-- --
## Requerimientos:

* Utilizar BD - MySql
* Spring Boot utilizando dependencias (JPA)
* Generar clases y intercomunicarlas

-- --
## Objetivo: 
Generaremos una aplicación que pueda gestionar usuarios
 quienes pueden acceder a una reserva de turnos.
 Estos turnos ser manipulados por un administrador.
 Resumen:

    * Generar entidad: usuario
    * generar entidad: administrador
    * generar entidad: turno
    * usuario -> turno <-> administrador -> usuario

-- -- 
## Vistas a generar:
De haber posibilidad, generar interactividad con vistas
que puedan acceder al perfil de usuario permitiendo modificar
sus datos, poder gestionar el rol del usuario y gestionar el turno.

Desde administrador, gestionar los turnos, visualizar su fecha, su id
y tambien visualizar que usuario solicitó dicho turno.
Además, permitir eliminar usuario, eliminar turno o mismo actualizarlos.

    * Vista usuario - Dashboard para solicitud de turno
    * Vista administrador - Dashboard para administrar Usuarios y Turnos
