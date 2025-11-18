FROM openjdk:17
COPY "./target/Equipo_Futbol-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8090
ENTRYPOINT [ "java", "-jar", "app.jar" ]