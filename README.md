# CST438-Group5-Project2-Backend
This is the backend repository for project 2 with group 5. This will be the new API for the vocab option.

git pull

Local dev: ./gradlew bootRun (H2)

Shared DB: set -a; source .env; set +a && ./gradlew bootRun

DB change: add a new db/migration/V#__*.sql, commit, push

Tests: ./gradlew clean test

Build: ./gradlew clean build

When adding or messing with the database whoever does it first make sure to create these folder under resources folder if it doesn't appear in main under the src/main/resource directory if non- existent create a db folder with a subfolder inside of it called migrations  

Therefore when wanting to add to db the file should follow such format in the name V#__description.sql so flyway detects it. This what supposed to happen theoretically and to my understanding after running the Shared db command written above:

Flyway checks your MariaDB’s flyway_schema_history table (this tracks which migrations have already been applied).

It scans your folder db/migration/ for new versions.

Any new .sql files that haven’t been applied yet are executed in order (V1, V2, V3, …).

After running, Flyway records that migration in the history table so it won’t run it again.

Expected output:
Flyway: Migrating schema `qcw4r0odq4lx4kbt` to version 2 - add_part_of_speech_to_words
Flyway: Successfully applied 1 migration


To run Docker Container run this commands:

1. docker build -t vocabapp:latest .
2. docker run -p 8080:8080 --env-file .env vocabapp:latest



