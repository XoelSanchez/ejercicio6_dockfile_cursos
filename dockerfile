FROM openjdk:11
ADD ejercicio6_microservicio_cursos.jar ejercicio6_microservicio_cursos.jar
ENTRYPOINT ["java","-jar","/ejercicio6_microservicio_cursos.jar"]
