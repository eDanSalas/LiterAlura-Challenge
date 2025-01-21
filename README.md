# LiterAlura-Challenge
Challenge Literatura Java BackEnd ORACLE ONE
ALURA LATAM

Para asegurarse de que funcione todo adecuadamente, deberá añadir las siguientes líneas en application.properties

spring.datasource.url=jdbc:postgresql://${DB_HOST}/libros
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.format-sql=true

Así como agregar las siguientes dependencias en el pom.xml
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
			<version>3.4.1</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>

  <dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.18.2</version>
		</dependency>

		<dependency>
			<groupId>jakarta.validation</groupId>
			<artifactId>jakarta.validation-api</artifactId>
			<version>3.1.0</version>
		</dependency>

  Finalmente contar con las variables de entorno adecuadas,
  donde:
  el nombre será: libros
  el usuario será: postgres
  la contraseña la que haya puesto en su instalación de PostgresSQL
