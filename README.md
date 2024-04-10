## springboot3新特性
### jdk最低版本提升到17

这个改动比较基础，直接修改pom.xml中java.version为17即可

### 第三方库版本更新
* [Spring Framework 6](https://github.com/spring-projects/spring-framework/wiki/What's-New-in-Spring-Framework-6.x)
* Jakarta EE 10
* 其他升级，比如jetty 11，log4j 2.18，tomcat 10，Netty 4.1.77.Final，Undertow 2.2.20.Final等等

### 提高应用可观察性

支持集成 Micrometer 1.10+，引入了的全新的可观察 API 并自动配置 Micrometer 追踪，包括对 Brave、OpenTelemetry、Zipkin 和 Wavefront 组件的支持。

* 参考com.tongweb.springboot3.micrometer中的代码和application.yaml的配置
* 需要先启动zipkin链路追踪系统，参考[zipkin](https://zipkin.io/)

### 自动配置

自动配置从org.springframework.boot:spring-boot-autoconfigure的META-INF/spring.factories改为META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports，2.7版本改为二者共存，写在其中任意一个文件都生效，3.0之后就必须写在imports文件里面了，正常情况下不用专门测试这个功能，感兴趣的话自己写一个启动类即可

### 支持 GraalVM 原生镜像

参考springboot3GraalVM项目

### log4j2扩展支持
* 按照spring的文档，下面的内容仅适用于log4j2，但理论上也支持logback，我的例子只依照官方文档来写，感兴趣的可以试试logback
#### 依赖
参考本项目pom，springboot默认使用logback，所以需要先排除logback的支持，然后添加log4j2的支持，具体看pom.xml里面的注释
#### 配置文件
* 支持springboot的log4j扩展建议使用文件名log4j2-spring.xml，其他文件名需要在application.yaml中声明
* 本项目的log4j2-spring.xml都有简单的使用示例
* 如果需要使用logback扩展，文件名改成logback-spring.xml即可
#### Profile-specific Configuration(有人翻译成配置文件增强，仅供参考)
* 参考本项目中的log4j2-spring.xml，相比于标准的log4j2.xml配置文件，多了一层`SpringProfile`，它的作用是针对不同的环境使用不同的配置文件
* name标签即为环境名称，启动时添加JVM参数`-Dspring.profiles.active={name}`即可激活对应的日志配置项
* 不支持pom.xml中的profiles配置，目前只能通过JVM参数启动生效
* `SpringProfile`可以放在任意位置，示例中放的是最顶层，比如说放在`Loggers`中的`Root`标签上，即可指定不同环境打印不同等级的日志：
```xml
    <Loggers>
      <SpringProfile name="test1">
        <Root level="info">
          <AppenderRef ref="RollingFileInfo"/>
        </Root>
      </SpringProfile>
      <SpringProfile name="test2">
        <Root level="trace">
          <AppenderRef ref="RollingFileInfo"/>
        </Root>
      </SpringProfile>
      <SpringProfile name="test3">
        <Root level="debug">
          <AppenderRef ref="RollingFileInfo"/>
        </Root>
      </SpringProfile>
    </Loggers>
```
* 也可以通过下面这种方式，激活stg1或stg2配置文件都能使配置生效
```xml
<SpringProfile name="stg1 | stg2">
  // 其他配置
</SpringProfile>
```
#### Environment Properties Lookup(有人翻译成环境属性增强，虽然确实算增强，但总感觉怪怪的)
* 这项功能是指log4j2扩展能在日志配置文件中直接使用spring配置文件里面的内容
* yaml和properties都支持，也支持通过切换配置文件使用不同配置文件里面相同属性的(可能不同的)值
* 使用方法与普通property大致一样，只需要在spring的配置项前面加spring:前缀，类似于这样就是获取应用名称，使用的时候直接调用${application.name}即可
```xml
<property name="application.name" >${spring:spring.application.name}</property>
```
#### Log4j2 System Properties
* 这个是直接用的log4j2自身的[lookups](https://logging.apache.org/log4j/2.x/manual/lookups.html)功能
* 跟Environment Properties Lookup功能差不多的用法，只是把`spring:`前缀改成其他的，比如说java
```xml
<PatternLayout pattern="${LOG_PATTERN}" header="${java:runtime} - ${java:vm} - ${java:os}"/>
```
* 需要注意区分一下就是，Windows环境如果需要用到环境变量，需要使用`sys:`前缀，比如`${sys:os.arch}`，而Linux环境则需要使用`env:`前缀，比如`${env:USER}`
### Improved @ConstructorBinding Detection
* 这项改动的原文是这样的
>When using constructor bound `@ConfigurationProperties` the `@ConstructorBinding` annotation is no longer required if the class has a single parameterized constructor. If you have more than one constructor, you’ll still need to use `@ConstructorBinding` to tell Spring Boot which one to use.
>
>For most users, this updated logic will allow for simpler `@ConfigurationProperties` classes. If, however, you have a `@ConfigurationProperties` and you want to inject beans into the constructor rather than binding it, you’ll now need to add an `@Autowired` annotation.
* 具体的改动就是@ConstructorBinding不再放在class上，而是改成了构造方法上
### Prometheus Support
* springboot一直支持Prometheus监控，这个新特性没明白是什么意思，先跳过
* 这项改动的原文是这样的
>#### Auto-Configuration for Prometheus Exemplars
>When there is a Micrometer Tracing Tracer bean and Prometheus is on the classpath, a SpanContextSupplier is now auto-configured. This supplier links metrics to traces by making the current trace ID and span ID available to Prometheus.
>
>#### Making a PUT to Prometheus Push Gateway on Shutdown
>The Push Gateway can be configured to perform a PUT on shutdown. To do so, set management.prometheus.metrics.export.pushgateway.shutdown-operation to put. Additionally, the existing push setting has been deprecated and post should now be used instead.
### More Flexible Auto-configuration for Spring Data JDBC
* 这个也有点没看懂，看标题是跟autoconfiguration有关，但是后面提到了替换bean类，有点搞不懂，但大概率不会在我们的测试应用里面出现
* 仍然贴一下原文
> The auto-configuration for Spring Data JDBC is now more flexible. Several auto-configured beans that are required by Spring Data JDBC are now conditional and can be replaced by defining a bean of the same type. The types of the beans that can now be replaced are the following:
> 
> - org.springframework.data.jdbc.core.JdbcAggregateTemplate
> 
> - org.springframework.data.jdbc.core.convert.DataAccessStrategy
> 
> - org.springframework.data.jdbc.core.convert.JdbcConverter
> 
> - org.springframework.data.jdbc.core.convert.JdbcCustomConversions
> 
> - org.springframework.data.jdbc.core.mapping.JdbcMappingContext
> 
> - org.springframework.data.relational.RelationalManagedTypes
> 
> - org.springframework.data.relational.core.dialect.Dialect
### Enabling Async Acks with Apache Kafka
* 支持kafka的异步ACK，文档说需要添加两个配置项
```yaml
spring.kafka.listener.async-acks=true
spring.kafka.listener.async-mode=manual/manual-immediate
```
* 这个也没搞明白，网上没有这两个配置项的使用方法，差了spring-kafka的源码，async-acks配置唯一使用到的地方是设置错误处理，没搞懂，或许是在3.0之后的版本改了什么，先记录
### Elasticsearch Java Client
* 适用于新版本的elasticsearch客户端(8.3以上版本)，在application.yaml中配置`spring.elasticsearch.*`以自动装配
* 该项优化不再需要`@Configuration`注解的Config类，参考demo中的[Student](src/main/java/com/tongweb/springboot3/elastic/Student.java)，[ElasticRepository](src/main/java/com/tongweb/springboot3/elastic/ElasticRepository.java)和[ElasticSearchTest](src/test/java/com/tongweb/springboot3/elastic/ElasticSearchTest.java)
* 测试前记得先添加索引和分析器
### Auto-configuration of JdkClientHttpConnector
* 用WebClient请求接口不用再配置Reactor Netty, Jetty’s reactive client, 和Apache HTTP client，`JdkClientHttpConnector`现在可以自动配置了
* demo参考[HttpClientController](src/main/java/com/tongweb/springboot3/controller/HttpClientController.java)，需要添加`spring-boot-starter-webflux`依赖
### @SpringBootTest with Main Methods
* 官方的说法是使用了`@SpringBootConfiguration`注解的配置类的`main`方法可以在`@SpringBootTest`执行
* 实际上的使用方法是，如果在application.yaml中定义了一个配置项，在单元测试中是无法直接使用的，现在给`@SpringBootTest`注解添加了新的属性`useMainMethod`，值为`UseMainMethod.ALWAYS`或`UseMainMethod.WHEN_AVAILABLE`，添加了这个属性就可以在单元测试中使用任意配置了，参考[demo](src/test/java/com/tongweb/springboot3/Springboot3ApplicationTests.java)
### 其他改动
* 感知不强，大多数要么用不到，要么就是一些优化，直接放原文地址
* [优化项](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Release-Notes#miscellaneous)
* [弃用功能](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Release-Notes#deprecations-in-spring-boot-30)
## springboot 3.1新特性
### Dependency Management for Apache HttpClient 4
* 基于springframework6的更新，Apache Http Client4也升级到了5，继续使用httpclient4可能会导致[报错](https://github.com/spring-projects/spring-boot/issues/33515)
* 与其说是移除依赖，倒不如说是一个中间版本，HC4可以用，但更鼓励使用HC5，这个就没有demo了，大家都会用
### Servlet and Filter Registrations
* 也是一个鼓励性改动，在之前的版本`ServletRegistrationBean`和`FilterRegistrationBean`类会报警告，现在直接抛异常，除非调用`setIgnoreRegistrationFailure(true)`方法，总之就是不要用
### Git Commit ID Maven Plugin Version Property
* 一个名字和版本号的改动，如果在之前的版本用到了`git-commit-id-plugin.version`属性，现在应该改为`git-commit-id-maven-plugin.version`然后重新导入
### Spring Kafka Retry Topic Auto-configuration
* 这个的意思是启用了重试配置`spring.kafka.retry.topic.enabled`后，重试延迟到`maxDelay`就不再新建topic，而是所有的重试都发送到最后一个新建的topic
* 搞不懂这个怎么测试，条件是能连上kafka服务器，但无法创建topic，或者创建的topic无法发送消息，跳过
### Dependency Management for Testcontainers
* 在pom中testcontainers的版本现在跟随springboot的版本，无需额外配置版本号，如果需要其他版本，可以尝试使用`testcontainers.version`属性覆盖它
* 对我们的测试应该没太大用处，不过还是写了个demo供参考，包括[一个接口](src/main/java/com/tongweb/springboot3/testcontainer)和[测试类](src/test/java/com/tongweb/springboot3/testcontainer/MongoContainerTest.java)，该测试用例会通过本地docker环境启动一个社区版MongoDB并写入一条数据
* 执行前记得把注释放开
### 依赖版本更新
* 列表如下
> [Hibernate 6.2](https://docs.jboss.org/hibernate/orm/6.2/migration-guide/migration-guide.html)
>
> [Jackson 2.15](https://github.com/FasterXML/jackson/wiki/Jackson-Release-2.15)
>
> [Mockito 5](https://github.com/mockito/mockito/releases/tag/v5.0.0)
### Health Group Membership Validation
* 看描述是把management.endpoint.health.group.custom.include里面配置的所有indicator都在服务启动之前校验是否存在而不是是否UP
* 如果include或者exclude中有不存在的indicator就会启动失败
* 如果不需要这个检查，可以通过设置`management.endpoint.health.validate-group-membership=false`来跳过检查
* demo参考[这里面](src/main/java/com/tongweb/springboot3/health)的两个自定义indicator，去掉application.yaml中的`validate-group-membership=false`会启动失败，因为health3不存在
* 感觉没啥用，本来我想的是提前检查就检查是否UP，结果检查的是是否存在
### Switch to maven.compiler.release
* 在pom文件中`spring-boot-starter-parent`现在使用`maven.compiler.release`来指定Java版本，以前使用的`maven.compiler.source`和`maven.compiler.target`被移除
* 这个是版本迁移需要注意的内容，对功能没影响
* 现在创建springboot3应用，默认会使用`java.version`来指定Java版本，实际上`spring-boot-starter-parent`里面的`maven.compiler.release`就使用了这个属性
### Service Connections
