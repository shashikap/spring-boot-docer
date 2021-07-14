FROM adoptopenjdk/openjdk11:latest

WORKDIR /opt/app


COPY /target/app.jar /opt/app/app.jar

CMD ls -lrt /opt/app/

# EXPOSE 8081

ENTRYPOINT ["java","-jar","/opt/app/app.jar"]

