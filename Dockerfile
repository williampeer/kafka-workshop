FROM openjdk:17-slim

RUN addgroup appuser && adduser --disabled-password --gecos '' appuser --ingroup appuser


RUN mkdir /app

COPY --chown=appuser:appuser ./build/libs/kafka-workshop.jar /app/kafka-workshop.jar
WORKDIR /app

USER appuser
EXPOSE 3000
ENV PORT=3000
CMD ["java", "-jar", "kafka-workshop.jar"]
