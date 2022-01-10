FROM  java:8-jdk-alpine
ADD . ./bookshop
WORKDIR /bookshop
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

FROM java:8-jdk-alpine
ENV TZ=Europe/Istanbul

COPY --from=build-project ./bookshop/build/libs/bookshop-*.jar ./bookshop.jar
CMD ["java", "-Dfile.encoding=UTF-8", "-jar", "./bookshop.jar"]