# Usa uma imagem Java como base
FROM openjdk:22-jdk

COPY build/libs/*.jar app.jar
# Define o comando padrão para executar a aplicação Spring Boot
CMD ["java", "-jar", "app.jar"]