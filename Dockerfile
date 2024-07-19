  FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-21 AS build
  COPY --chown=quarkus:quarkus mvnw /code/mvnw
  COPY --chown=quarkus:quarkus .mvn /code/.mvn
  COPY --chown=quarkus:quarkus pom.xml /code/
  USER quarkus
  WORKDIR /code
  RUN ./mvnw -B org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline
  COPY src /code/src
  RUN ./mvnw package -DskipTests

  ## Stage 2 : create the docker final image
  FROM registry.access.redhat.com/ubi8/openjdk-17:1.18 AS run
  ENV LANGUAGE='en_US:en'
  # We make four distinct layers so if there are application changes the library layers can be re-used
  COPY --from=build --chown=185 /code/target/quarkus-app/lib/ /deployments/lib/
  COPY --from=build --chown=185 /code/target/quarkus-app/*.jar /deployments/
  COPY --from=build --chown=185 /code/target/quarkus-app/app/ /deployments/app/
  COPY --from=build --chown=185 /code/target/quarkus-app/quarkus/ /deployments/quarkus/

  EXPOSE 8080
  USER 185
  ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
  ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

  #ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]