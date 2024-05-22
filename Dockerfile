FROM openjdk:8-jdk-alpine as build
WORKDIR /workspace/order-management

COPY target target

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:8-jre-slim
MAINTAINER beaconfire

VOLUME /app/order-management
ARG DEPENDENCY=/workspace/order-management/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java", "-Djasypt.encryptor.password=secret", "-cp","app:app/lib/*","com.beaconfire.project.trading.ordermanagement.OrderManagementApplication"]