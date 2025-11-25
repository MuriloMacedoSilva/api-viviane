# Use a imagem oficial alpine do Eclipse Temurin
# https://hub.docker.com/_/eclipse-temurin
FROM eclipse-temurin:21-jdk-alpine

# Crie e mude para o diretório da aplicação
WORKDIR /app

# Copie o código local para a imagem do container, APÓS definir o WORKDIR
# O "." (source) é o diretório onde o Dockerfile está (raiz do projeto)
# O "." (destination) é o WORKDIR (/app)
COPY . .

# Torne o wrapper Maven executável. Agora o arquivo existe em /app/mvnw.
RUN chmod +x mvnw

# Construa a aplicação.
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -DskipTests clean dependency:list install

# Execute a aplicação quarkus
CMD ["sh", "-c", "java -jar target/quarkus-app/quarkus-run.jar"]