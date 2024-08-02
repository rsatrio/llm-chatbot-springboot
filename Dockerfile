FROM maven:3.9.6-ibm-semeru-17-focal as build
COPY ./src /buildDir/src
COPY ./pom.xml /buildDir
WORKDIR /buildDir
RUN mvn package

FROM eclipse-temurin:17-jre-jammy
COPY --from=build /buildDir/target/LLMCpp-Chat-SpringBooto.jar /app/LLMCpp-Chat-SpringBoot.jar
ENTRYPOINT java -Dllamacpp.model=/app/llm-model.gguf -jar /app/LLMCpp-Chat-SpringBoot.jar 
