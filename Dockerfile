# Etapa 1: Construção do projeto
FROM maven:3.8.5-openjdk-17-slim AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto (pom.xml e código-fonte)
COPY pom.xml .
COPY src ./src

# Executa a construção do projeto
RUN mvn clean package -DskipTests

# Etapa 2: Execução do aplicativo
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=builder /app/target/devbank-cadastro-pessoa-1.0.0.jar app.jar

# Expondo a porta que a aplicação irá usar
EXPOSE 8080

# Comando para iniciar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]