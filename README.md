# Hello Java Web

一个使用 Java 21、Spring Boot、Maven、Spring Data JPA 和 MySQL 构建的最小 CRUD Web 项目。

## 运行

```bash
mvn spring-boot:run
```

启动后在浏览器打开 <http://localhost:8080/hello>，将看到：

```text
Hello Java Web!
```

网页 CRUD 管理页面：

```text
http://localhost:8080/
```

默认登录账号：

```text
用户名：admin
密码：admin123
```

登录后页面会自动携带 JWT 访问 CRUD 接口。

CRUD 接口地址：

```text
GET    http://localhost:8080/products
GET    http://localhost:8080/products/{id}
POST   http://localhost:8080/products
PUT    http://localhost:8080/products/{id}
DELETE http://localhost:8080/products/{id}
```

新增商品示例：

```bash
TOKEN=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}' \
  | sed -E 's/.*"token":"([^"]+)".*/\1/')

curl -X POST http://localhost:8080/products \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"name":"Keyboard","price":199.99,"stock":5}'
```

## MySQL 配置

项目默认连接本机 MySQL：

```text
Host: 127.0.0.1
Port: 3306
Database: hello_java_web
User: root
Password:
```

如果你的 MySQL 用户名或密码不同，可以在启动前设置环境变量：

```bash
export MYSQL_USER=你的用户名
export MYSQL_PASSWORD=你的密码
export MYSQL_DATABASE=hello_java_web
```

JWT 登录账号和密钥也可以通过环境变量修改：

```bash
export APP_AUTH_USERNAME=admin
export APP_AUTH_PASSWORD=admin123
export APP_JWT_SECRET=请换成至少32位的随机字符串
export APP_JWT_EXPIRATION_SECONDS=3600
```

测试环境会自动使用 H2 内存数据库，不需要手动启动 MySQL。

## 在 VS Code 中运行

不要使用 Code Runner 的 `Run Code`，它可能会直接调用 `javac`，从而无法加载 Maven 下载的 Spring Boot 依赖。

推荐使用下面任意一种方式：

1. 打开 `运行和调试` 面板，选择 `Run Hello Java Web`。
2. 打开命令面板，执行 `Tasks: Run Task`，选择 `Run Spring Boot with Maven`。
3. 在 Maven 面板中运行 `spring-boot:run`。

## 测试

```bash
mvn test
```
