FROM adoptopenjdk
RUN mkdir /opt/app
COPY target/bikessharing-0.0.1-SNAPSHOT.jar /opt/app/bikeshare.jar
CMD ["java", "-jar", "/opt/app/bikeshare.jar"]
