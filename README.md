# Проект «Облачное хранилище»

BACKEND написан на Java с использованием Spring Boot. 
Доступен по адресу http://localhost:9091.

## Приложение удовлетворяет следующим требованиям:

- Сервис предоставляет REST-интерфейс для интеграции с FRONT 
 FRONT доступен по адресу http://localhost:8080).

- BACKEND реализует авторизацию 

- Информация о пользователях сервиса (логины для авторизации) и данные хранятся в базе данных Postgresql, имя базы дыннх Postgres, схема default, установленной	 локально.

- Приложение разработано с использованием Spring Boot.

- Использован сборщик пакетов maven.

- Для запуска используется docker, docker-compose.

- Есть интеграционный тест с использованием testcontainers.


## Инструкция по запуску фронта 
1. Установите nodejs (версия не ниже 19.7.0) на компьютер, следуя [инструкции](https://nodejs.org/ru/download/current/).
2. Скачайте [FRONT](./netology-diplom-frontend) (JavaScript).
3. Перейдите в папку FRONT приложения и все команды для запуска выполняйте из неё.
4. Следуя описанию README.md FRONT проекта, запустите nodejs-приложение (`npm install`, `npm run serve`).
5. Далее нужно задать url для вызова своего backend-сервиса.
    1. В файле `.env` FRONT (находится в корне проекта) приложения нужно изменить url до backend, например: `VUE_APP_BASE_URL=http://localhost:8080`. 
       1. Нужно указать корневой url вашего backend, к нему frontend будет добавлять все пути согласно спецификации
       2. Для `VUE_APP_BASE_URL=http://localhost:8080` при выполнении логина frontend вызовет `http://localhost:8080/login`
    2. Запустите FRONT снова: `npm run serve`.
    3. Изменённый `url` сохранится для следующих запусков.
6. По умолчанию FRONT запускается на порту 8080 и доступен по url в браузере `http://localhost:8080`. 
   1. Если порт 8080 занят, FRONT займёт следующий доступный (`8081`). После выполнения `npm run serve` в терминале вы увидите, на каком порту он запустился.

## Настройка CORS

Чтобы FRONT смог обратиться к серверу: 
```java
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*");
    }
}
