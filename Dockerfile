FROM openjdk:11.0.7-slim 
LABEL maintainer="gastonezequieldiaz@outlook.com"
ARG JAR_FILE 
ADD target/${JAR_FILE} dan-ms-reportes.jar
RUN echo ${JAR_FILE} 
ENTRYPOINT ["java","-jar","/dan-ms-reportes.jar"]