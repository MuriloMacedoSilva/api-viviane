# Estágio de Build
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# 1. Copia apenas o pom.xml e o mvnw, o essencial para a *primeira camada* de cache.
COPY mvnw pom.xml ./

# 2. Torna o wrapper Maven executável.
RUN chmod +x mvnw

# 3. Copia a estrutura do Maven (.mvn) e o código fonte (src/)
# Se o seu projeto tem o diretório .mvn, ele precisa ser copiado.
COPY src ./src
COPY .mvn ./.mvn

# 4. Construa a aplicação.
# O build do Maven usa o pom.xml e os arquivos em src.
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -DskipTests clean dependency:list install

# --- Estágio de Execução (Runtime) ---
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copia apenas o JAR final do estágio de build
COPY --from=builder /app/target/quarkus-app/ /app/

# Execute a aplicação quarkus
CMD ["java", "-jar", "quarkus-run.jar"]
