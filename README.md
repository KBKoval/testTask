# testTask
Spring boot+Spring batch Async, Hibernate, JDBCTemplate, FlyWay, Apache POI
Работа не завершена.
Цели:
 1. Обеспечить пользователю просмотр ранее загруженных данных (JSON).
 2. Обеспечить загрузку/выгрузку (import/export) данных (MC Excel)
Граничные условия:
 1. Import/Export данных должен осуществляться асинхронно с возможностью мониторинга состояния задачи.

Решения:
  Первая цель тривиальна и не представляет особого интереса (описана в любом мануале по спрингу),
то для достижения второй цели:
  1. Использовать ThreadPoolTaskExecutor (аннотация @Async) 
  2. Использовать очередь сообщений (Redis+Kafka), разбив задачу нп два микросервиса
  3. Использовать Spring Batch (пакетная обработка данных)
Выбран первый и третий вариант. В идеале - разбить задачу на два микросервиса:
  сервис загрузки/выгрузки данных и мониторинг статуса выполнения задачи
  сервис прсмотра редактирования данных. 
Стек: Spring WebFlux+Kafka (Rabbit MQ, IBM MQ...) и Spring Batch
(возможная проблема - два приложения работают с одним DataSource)

Общие принципы.
  При загрузке данных выполняется асинхронный запрос (ThreadPoolTaskExecutor), при выполнении которого 
запускается JobLauncher. Тем самым Job выполняется в пуле задач, обеспечивая мониторинг состояния задачи.
                                                                                                                                                                                                      
