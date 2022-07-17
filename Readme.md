## Message storage application

### Описание

Приложение состоит из двух сервисов auth-service и message-storage, а так же базы данных MySql.

- Auth-service - для аутентификации пользователя. Реализован HTTP POST эндпоинт http://localhost:8083/authenticate,
  который получает данные в json вида:
  <br/>`{ name: "имя отправителя", password: "пароль" }` <br> и в ответ отправлят токен при упешной проверке
  пользователя
  <br/>`{ name: "имя отправителя", password: "пароль" }`
  <br/> Ссылка на docker image https://hub.docker.com/r/sml235/docker-message-storage-jwt_auth-service
- Message-storage - предназначен для работы с сообщениями. Реализован эндпойнт  http://localhost:8082/messages,
  при отправке на него данных происходит проверка токена из заголовка запроса Authorization. Токен с префиксом Bearer.
  <br/>Если пришло сообщение вида:
  <br/>`{
  name:       "имя отправителя",
  message:    "текст сообщение"
  }`  то сообщение сохраняется в БД.
  <br/>
  Если :
  <br/>`{
  name:       "имя отправителя",
  message:    "history Х"
  }` то в ответ выводятся последние Х сообщений.
  <br/> Ссылка на docker image https://hub.docker.com/r/sml235/docker-message-storage-jwt_message-storage

Примеры curl запросов указаны в файле curl в корневом каталоге проекта.

### Запуск приложения

Приложение запускал в Docker с помощью команды `docker compose up -d`. При запуске скачиваются образы 
прилолжений и базы данных, создается volume для БД, БД инициализируется и заполняется пользавателями и 
примерами сообщений из файла data.sql, так же передаются properties для подключения к БД.
<br/>В файле .env прописаны порты из контейнеров для приложений:
<br/> &emsp; auth-service : 8083
<br/> &emsp; message-storage : 8082
<br/> и пользователь БД