FROM openjdk:11
EXPOSE 8040
ADD target/reading-is-good.jar reading-is-good.jar
ENTRYPOINT ["java","-jar","/reading-is-good.jar"]