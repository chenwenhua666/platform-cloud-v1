platform模块：

服务名称 | 端口 | 描述
---|---|---
platform-Auth| 9902| 微服务认证服务器 
platform-Server-System| 9905 | 微服务子系统（资源服务器）
platform-Server-Foundation|9906 | 微服务子系统（资源服务器）
platform-Gateway|99031|微服务网关
platform-Monitor-Admin|9904|微服务监控子系统

第三方模块：

服务名称 | 端口 | 描述
---|---|---
Nacos| 9901 |注册中心，配置中心 
Zipkin-Server|9920|Zipkin服务器
MySQL| 3306 |MySQL 数据库 
RabbitMQ|5672|RabbitMQ 消息中间件 
Redis| 6379 | K-V 缓存数据库 
mongodb 