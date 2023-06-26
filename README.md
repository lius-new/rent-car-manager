# 租车系统

本项目于课程结束后完成的。技术栈采用的是前后端分离的形式。后端采用基础的ssm(spring+mybatis+spring-mvc)配合jwt做权限的认证和校验,前端的内容放在[front分支](https://github.com/lius-new/rent-car-manager/tree/front)

### 目录结构
```
|source # sql文件
|src/
├─main
|  ├─webapp
|  |   ├─WEB-INF
|  ├─resources
|  |     ├─application.xml
|  |     ├─db.properties
|  |     ├─mybatis-config.xml
|  |     ├─com/lius/dao
|  ├─java
|  |  ├─com
|  |  |  ├─lius
|  |  |  |  ├─service
|  |  |  |  ├─interceptors
|  |  |  |  ├─entity
|  |  |  |  ├─dao
|  |  |  |  ├─controller
|  |  |  |  ├─common   # 项目中公共的类的包
```

### 项目运行

1. 拉取代码
```bash
git clone https://github.com/lius-new/rent-car-manager.git
```
2. 执行`source/db.sql`
3. 刷新maven,并运行tomcat
![](https://s2.loli.net/2023/06/26/QW6gZDyJo5wtq14.png)

**这只是后端部分,需要拉取前端的内容一同运行**