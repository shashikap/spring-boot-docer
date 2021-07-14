FROM adoptopenjdk/openjdk11:latest

WORKDIR /opt/app


COPY event-crud/target/app.jar /opt/app/app.jar

CMD ls -lrt /opt/app/

# EXPOSE 8081

ENTRYPOINT ["java","-jar","/opt/app/app.jar"]

