Proyecto Spring Boot con Microservicios
Este proyecto consiste en dos microservicios desarrollados con Spring Boot: orquestador y dominio. El microservicio orquestador se encarga de recibir las solicitudes, validarlas y comunicarse con el microservicio dominio para guardar los datos en una base de datos. Ambos microservicios están conectados entre sí para lograr la funcionalidad deseada.
Configuración de la Base de Datos PostgreSQL
El script SQL a continuación crea la base de datos PostgreSQL y la tabla necesaria para este proyecto:
-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION postgres;

-- DROP TYPE public.datos;

CREATE TYPE public.datos AS (
	id bigserial,
	nombre varchar(255));

-- DROP TYPE public."_datos";

CREATE TYPE public."_datos" (
	INPUT = array_in,
	OUTPUT = array_out,
	RECEIVE = array_recv,
	SEND = array_send,
	ANALYZE = array_typanalyze,
	ALIGNMENT = 8,
	STORAGE = any,
	CATEGORY = A,
	ELEMENT = public.datos,
	DELIMITER = ',');

-- DROP SEQUENCE public.datos_id_seq;

CREATE SEQUENCE public.datos_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE public.datos_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE public.datos_id_seq TO postgres;
-- public.datos definition

-- Drop table

-- DROP TABLE public.datos;

CREATE TABLE public.datos (
	id bigserial NOT NULL,
	nombre varchar(255) NULL,
	CONSTRAINT datos_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE public.datos OWNER TO postgres;
GRANT ALL ON TABLE public.datos TO postgres;

-- Permissions

GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

Este script crea una tabla llamada datos en el esquema public de la base de datos. Asegúrate de ejecutar este script en tu entorno de base de datos PostgreSQL antes de ejecutar los microservicios.

Detalles del Proyecto

Microservicio Orquestador

Paquete Java: com.microservicios.orquestador

Clase Principal: OrchestratorApplication.java

Configuración: application.properties

Pruebas Unitarias: OrchestratorApplicationTests.java

Microservicio Dominio

Paquete Java: com.microservicios.dominio

Clase Principal: DomainApplication.java

Configuración: application.properties

Pruebas Unitarias: DomainApplicationTests.java

Configuración y Ejecución
Clona este repositorio en tu máquina local.
Abre el proyecto en tu IDE preferido.
Configura tu base de datos PostgreSQL y ejecuta el script proporcionado.
Para cada microservicio (orquestador y dominio), ejecuta la clase principal (OrchestratorApplication.java y DomainApplication.java respectivamente).

Los microservicios estarán disponibles en los puertos configurados en los archivos application.properties.

Pruebas
Para probar los microservicios, puedes usar herramientas como Postman. Aquí hay algunas solicitudes que puedes realizar:

Guardar datos: Realiza una solicitud POST al endpoint /save del microservicio orquestador con los datos que deseas guardar.

Notas Adicionales:

Se recomienda revisar y ajustar la configuración de la base de datos en los archivos application.properties según sea necesario.
Asegúrate de tener las dependencias adecuadas especificadas en los archivos pom.xml de cada microservicio.
