FROM maven:3.9.6-ibm-semeru-17-focal as build
COPY ./src /buildDir/src
COPY ./pom.xml /buildDir
WORKDIR /buildDir
RUN mvn package

FROM eclipse-temurin:17-jre-jammy
RUN apt-get update
RUN apt-get install libgomp1
COPY --from=build /buildDir/target/LLMCpp-Chat-SpringBoot.jar /app/LLMCpp-Chat-SpringBoot.jar
ENTRYPOINT java -Dllamacpp.model=/app/model/llm-model.gguf -jar /app/LLMCpp-Chat-SpringBoot.jar
