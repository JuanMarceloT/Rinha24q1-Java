# Usa uma imagem Java como base
FROM openjdk:22-jdk

COPY build/libs/rinha-0.0.1-SNAPSHOT.jar app.jar
# Define o comando padrão para executar a aplicação Spring Boot
CMD ["java", "-jar", "app.jar"]