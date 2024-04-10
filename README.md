# Getting Started

### 业务逻辑

该项目是使用springboot的响应式框架对mongodb中student集合的增删改查操作的示例

### 注意事项

* 项目使用jdk11开发，支持jdk8-21
* springboot版本为2.7.9，支持修改为3.x但同时需要修改jdk版本为17-21

### 准备工作

* 修改application.properties中mongodb的信息，其中database和authenticationdatabase是不一样的，注意区分
* 手动创建student表或执行POST /init接口

### 如何使用

* 直接启动或执行mvn package打包后部署到tongweb8
* 需要jar包的话，修改pom.xml中`<packaging>war</packaging>`为`<packaging>jar</packaging>`
* 启动后访问/index.html，在新生入学中输入学号，姓名和籍贯，选择性别，点击入学即可在数据库中添加一条记录
  ![示例](student.png "示例")
* 点击退学即可删除对应记录

### 可访问的接口

* 查询学生列表：GET /stu
* 根据学号查询学生信息：GET /stu/{no}
* 新增学生：POST /stu ，参数是[Student](src/main/java/com/tongweb/webfluxwithmongodb/model/Student.java)类。
* 获取学生记录总数：GET /stu/count
* 修改学生信息：PUT /stu，参数也是[Student](src/main/java/com/tongweb/webfluxwithmongodb/model/Student.java)类。
* 根据学号删除学生记录：DELETE /stu/{no}
* 初始化数据库：GET /init，创建一个名为student的集合

### 其他

* 我本来还打算加上自选省市作为籍贯的，但是想了一下在功能上没有差异，没必要做重复的工作，就放弃了，但是代码没删，不影响功能
* 前端我打算加上vue框架，但是学习成本有点高，也先放弃，之后有时间再改造
