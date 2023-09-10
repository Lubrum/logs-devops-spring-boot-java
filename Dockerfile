FROM eclipse-temurin:20.0.2_9-jre

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /usr/src/myapp/api-cursos.jar 

WORKDIR /usr/src/myapp

RUN rm -f /etc/localtime && \
    ln -s /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime

CMD ["java","-Duser.timezone=UTC-3", "-Xms128M","-Xmx128M","-XX:MaxMetaspaceSize=128m","-jar","api-cursos.jar"]
