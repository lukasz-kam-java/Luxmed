# Wybieramy oficjalny obraz Javy 21
FROM openjdk:21-jdk-slim

# Instalujemy PostgreSQL
RUN apt-get update && apt-get install -y postgresql
# Tworzymy bazę danych i użytkownika
RUN echo "127.0.0.1:5432:Luxmed:postgres:" > ~/.pgpass && chmod 600 ~/.pgpass
#RUN service postgresql start && \
 #  psql -h 127.0.0.1 -U postgres  --command "CREATE USER postgres WITH SUPERUSER PASSWORD 'kamkam123';" && \
#    psql -h 127.0.0.1 -U postgres  --command "CREATE DATABASE Luxmed;" && \
#    psql -h 127.0.0.1 -U postgres   --command "GRANT ALL PRIVILEGES ON DATABASE Luxmed TO postgres;"

# Eksponujemy porty, które będą używane przez PostgreSQL i aplikację
EXPOSE 5432

# Ustawiamy domyślną komendę do uruchamiania aplikacji (jeśli posiadasz aplikację Java)
#CMD ["java", "-jar", "dist/luxmed-rest-app.jar"]