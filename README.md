При первом запуске создаётся база и заполняются первичные данные. После чего нужно закомменить строчку
spring.sql.init.data-locations=classpath*:database/populateDB.sql в application.properties, чтоб не возникала ошибка 
уникальных данных при последующих запусках.


============================================================
Путь к БД

spring.datasource.url=jdbc:postgresql://localhost:5433/postgres
spring.datasource.username=postgres
spring.datasource.password=root

============================================================

Старт 

localhost:8080

