# 베이스 이미지 - corretto:17
FROM amazoncorretto:17

# jar 복사
COPY ./build/libs/app-0.0.1-SNAPSHOT.jar ./app.jar

# jar 실행
CMD ["java", "-jar", "app.jar"]