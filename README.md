- Run a PostgreSQL container with a password and port forwarding
 
```docker run -e POSTGRES_PASSWORD=postgres -p 8200:5432 -d postgres```
 
- Access the PostgreSQL container
```docker exec -it $CONTAINER_ID bash```

- Access PostgreSQL
```psql -h localhost -U postgres```

- Create a database named "memory_analyzer" in PostgreSQL
```create database memory_analyzer;```

- Connect to the "memory_analyzer" database
```\c memory_analyzer;```

- Create a "data" table with the necessary columns
```CREATE TABLE data (
id SERIAL PRIMARY KEY,
data TEXT,
modification_data TIMESTAMP
);```


Analyze memory:
 ```java -XX:+HeapDumpOnOutOfMemoryError -Xmx250m -jar target/memory-analyzer-0.0.1-SNAPSHOT.jar -XX:HeapDumpPath=.```