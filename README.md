# Hello Java Web

一个使用 Java 21、Spring Boot 和 Maven 构建的最小 Web 项目。

## 运行

```bash
mvn spring-boot:run
```

启动后在浏览器打开 <http://localhost:8080/hello>，将看到：

```text
Hello Java Web!
```

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
