FROM maven:3.9.9-sapmachine-17 AS build
WORKDIR /my-forum
# Копируем pom с зависимостями в docker-контейнер
COPY pom.xml /my-forum/pom.xml
# Копируем код из src в docker-контейнер
COPY ./src /my-forum/src
# Компилируем код без тестов
RUN mvn -f /my-forum/pom.xml clean package -Dmaven.test.skip=true

FROM sapmachine:17-jre-alpine
# Переносим jar файл из каталога target в каталог my-forum
COPY --from=build /my-forum/target/*.jar /my-forum/*.jar
# Запускаем jar-файл
ENTRYPOINT ["java", "-jar", "/my-forum/*.jar"]