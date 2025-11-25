FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia os arquivos essenciais primeiro para garantir o acesso.
COPY mvnw mvnw.cmd pom.xml ./

# Torne o wrapper Maven executável.
RUN chmod +x mvnw

# Copia o restante do código fonte.
COPY . .

# Construa a aplicação.
# O uso de ./mvnw é mais seguro.
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -DskipTests clean dependency:list install

# Execute a aplicação quarkus
CMD ["sh", "-c", "java -jar target/quarkus-app/quarkus-run.jar"]
