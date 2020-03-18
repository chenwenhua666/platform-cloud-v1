1. 下载zipkin.jar
```
curl -sSL https://zipkin.io/quickstart.sh | bash -s
```
2. 启动zipkin-server
```bash
java -jar zipkin.jar --server.port=9920 --zipkin.storage.type=mysql --
zipkin.storage.mysql.db=platform_cloud_base --zipkin.storage.mysql.username=root --
zipkin.storage.mysql.password=123456 --zipkin.storage.mysql.host=localhost --
zipkin.storage.mysql.port=3306 --zipkin.collector.rabbitmq.addresses=localhost:5672 --
zipkin.collector.rabbitmq.username=platform  --zipkin.collector.rabbitmq.password=123456a_```
