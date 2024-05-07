<h1>Пинчук Роман</h1>
<h2>Сократитель ссылок</h2>

---
<h3>Запуск приложения</h3>
Для корректного запуска приложения необходимо: <br>
1. Запустить docker-compose при помощи команды "**docker compose up**". <br>
2. Запустить приложение. <br>

---
<h3>Ответы на вопросы</h3>
**1. Подумать, что если нужно будет хранить 1M ссылок, 10M, 100M?**
   *   Можно столкнутся с коллизией при добавлении большого количества ссылок,
       чтобы вероятность их возникновения была минимальной, то нужно использовать двойное хеширование и хеширование кукушки.
   *   Необходимо кешировать часто используемые ссылки для быстрого ответа пользователю
       и уменьшения нагрузки на базу дынных.
   *   Необходимо реплицировать базу данных для повышения доступности данных,
       отказоустойчивости и распределения нагрузки.

**2. Куда должно развиваться приложение?** <br>
   * Создать на UI форму, на которой можно будет ввести url и отправлять эту форму при помощи post запроса на сервер.
   * Добавить новый функционал: 
     * генерация QR-кодов; 
     * массовое сокращение ссылок;
     * чтобы пользователь видел все ссылки, которые он добавил;
   * Интеграция с почтой, мессенджерами и соцсетями для быстрой отправки сокращенной ссылки другому пользователю.