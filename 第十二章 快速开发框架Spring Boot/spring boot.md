**快速开发框架** **Spring Boot**

**课程讲义**

**主讲：\****Reythor** **雷**

**2019**

**快速开发框架** **Spring Boot**

# 第1章 Spring Boot 基础

## 1.1 Spring Boot 简介

Spring Boot 是由 Pivotal 团队提供的全新框架，其设计目的是用来简化 Spring 应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。通过这种方式，Spring Boot 致力于在蓬勃发展的快速应用开发领域(rapid application development)成为领导者。

简单来说，SpringBoot 可以简化 Spring 应用程序的开发，使我们不再需要 Spring 配置文件及 web.xml 文件。

2003 年 Spring 开源项目启动

2004 年 Spring 诞生，由 Rod Johnson 创建。

2007 年Rod Johnson 成立了 Spring Source 公司，并更名为 Spring

2009 年 Spring Source 被VMware 收购，Rod Johnson 成为 VMware 公司 Spring Source 团队的负责人

2012 年Rod Johnson 离开 VMware 公司，即离开了 Spring Source 团队

2013 年由 VMware 公司及多家其它公司联合出资，成立了 Pivotal 公司，将 Spring 技术的研发交由 Pivotal

2013 年 Pivotal 开始研发 Spring Boot 2014 年发布 Spring Boot 第一个版本

Rod Johnson

不会音乐的金融顾问不是好程序员

## 1.2 Spring Boot 工程的创建

- **在** **Idea** **中创建**
- **工程创建**

这里要创建一个 Spring Boot 的 Web 工程。首先新建 Spring Initializr。

## （2） 工程编辑

系统会在前面设置的包中自动生成一个启动类。

在启动类所在的包下再创建一个子包，在其中编写 SpringMVC 的处理器类。注意，要求代码所在的包必须是启动类所在包的子孙包，不能是同级包。对于本例而言，要求代码必须出现在 com.abc 包的子孙包中。

## （3） 工程运行

对于 SpringBoot 程序的运行，若是在 Idea 环境下运行，比较简单，直接运行main 类即可；若是没有 Idea 环境，则可打包后直接通过 java 命令运行。

### A、 Idea 下的运行

**a\****、 运行**

打开启动类并直接运行即可启动 SpringBoot 框架。在控制台查看启动信息可知：

- Tomcat 已启动，且为内置 Tomcat，端口号为 8080。
  - 项目的上下文路径 Context Path，即访问该项目时的项目路径为空，即浏览器访问时无需项目名称。

### b、 访问

在浏览器地址栏中直接输入“主机 + 端口 + URI”即可访问该项目，无需项目名称。

### B、 打包后运行

将 SpringBoot 工程打包后即可脱离 Idea 环境运行。

### a、 打包

使用 package 命令将工程打为 Jar 包。

### b、 运行

将打好的 Jar 包移动到任意目录，当然，也可在原来的 target 目录，在命令行即可通过

java 命令直接运行。例如，将 jar 包移动到 D:/course 目录中。

当看到如下提示时，表示应用启动成功。

### c、 访问

在浏览器地址栏中直接输入“主机 + 端口 + URI”即可访问该项目，无需项目名称。

## 1.2.2 官网创建

在点击了Generate Project 按钮后，即可打开一个下载对话框。官网将配置好的 Spring Boot 工程生成了一个zip 压缩文件，只要我们将其下载到本地即可。

下载后，将其解压到 idea-workspace 中，在 idea 中即可马上看到该工程。注意，此时该工程是作为一个 Module 出现的。然后，再通过“导入外部 Moduel 方式”将该工程导入为 Maven 工程即可。

## 1.3 基于 war 的Spring Boot 工程

前面创建的 Spring Boot 工程最终被打为了 Jar 包，是以可执行文件的形式出现的，其使用了 Spring Boot 内嵌的 Tomcat 作为 Web 服务器来运行 web 应用的。新版 Dubbo 的监控中心工程就是典型的应用。

但在实际生产环境下，对于访问量不大的应用，直接以 Jar 包的形式出现，使用起来是非常方便的，不用部署了。但对于访问量较大的 Web 工程，我们不能使用 Tomcat，而要使用更为高效的商业 web 容器，例如 JBOSS、WebLogic 等，此时我们需要的是 war 包而非 jar 包。

下面我们来看一下如何使用 Spring Boot 将工程打为 war 包。

## 1.3.1 工程创建

其创建过程与前面打为 jar 包的相似，只以下面的窗口中选择 Packaging 为 War 即可。

## 1.3.2 工程编辑

将第一个工程中的处理器复制到当前工程中即可

## 1.3.3 工程运行

- **Idea** **下运行与访问**

打开启动类并直接运行即可启动 SpringBoot 工程，访问方式与之前的也相同。

## （2） 打包部署

### A、 打包

运行 package 命令，其会打为 war 包。

### B、 部署

找到该 war 包，将其部署到 Tomcat 的 webapps 目录中，启动 Tomcat。

### C、 访问

在浏览器中可以访问到该工程。注意，由于工程是部署到了 Tomcat 的 webapps 中，不是部署到 webapps/ROOT 中，所以在访问时需要指定工程名。

## 1.4 SpringBoot 的主配置文件

- **编辑器**

Spring Boot 的主配置文件是 src/main/resources 中默认创建的 spring.properties 文件。该文件打开后是没有自动提示功能的。此时可以打开Project Structure 窗口，在 Modules 中选中没有自动提示的工程，点击+号，找到 Spring，将其添加可以。此时的配置文件就有了自动提示功能，包括后面的 yml 文件也有了自动提示。

## 1.4.2 简单尝试

在 Eclipse 中运行工程后，查看日志文件可以看到端口号与应用的根的确发生的变化。

在地址栏中需要键入新的端口号与应用的根名称。

不过需要注意，这里指定的 Tomcat 的端口号及应用的根路径，仅仅是针对于内置 Tomcat 的，是测试时使用的。将工程打为 war 包后部署到真正的 Tomcat，这些配置是不起作用的， 即 Tomcat 的端口号为真正 Tomcat 的端口号，而项目的根路径为 war 包名称。

## 1.4.3 yml 文件

Spring Boot 的主配置文件也可使用 application.yml 文件。yml，也可写为 yaml。

在开发之初 YAML 的本意是 Yet Another Markup Language（仍是一种标记语言）。后来为了强调这种语言是以数据为中心，而不是以标记为中心，所以将 YAML 解释为 Yaml Ain't Markup Language（Yaml 不是一种标记语言）。它是一种直观的能够被电脑识别的数据序列化格式，是一个可读性高并且容易被人阅读，容易和脚本语言交互，用来表达多级资源序列的编程语言。

yml 与 properties 文件的主要区别是对于多级属性，即 key 的显示方式不同。yml 文件在输入时，只需按照点(.)的方式输入 key 即可，输入完毕后回车即出现了如下形式。该形式要求冒号后与值之间要有一个空格。不同级别的属性间要有两个空格的缩进。

需要注意，很多脚本中的空格都是作为无效字符出现的，但 yml 脚本则是作为有效字符出现的，必须要保证空格的数量。

在演示时需要注意，application.properties 与 application.yml 这两个文件只能有一个。要求文件名必须为 application。所以，此时可以将 application.properties 文件重命名为其它名字即可。

## 1.5 Actuator 监控器

Actuator 是 Spring Boot 提供的一个可插拔模块，用于对工程进行监控。其通过不同的监控终端实现不同的监控功能。Spring Boot 的Actuator 可以部署在每个工程中，实现对每个工程的监控。

## 1.5.1 基本环境搭建

随便一个 Spring Boot 工程中都可以使用 Actuator 对其进行监控。本例使用 01-primary

工程作为被监控对象，复制该工程并重命名为 03-actuatorTest，在该工程中进行修改。

## （1） 导入依赖

#### 

<**groupId**>org.springframework.boot</**groupId**>

<**artifactId**>spring-boot-starter-actuator</**artifactId**>

" src="data:image/png;base64,R0lGODlhzQI6AHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AiwAhQAAAAAAAAsLDQAANQAAYAAAgAAAlAAApwA1hgA1uQBgqgBgzDUAADUAYDUAgDVgpzWGzjWGzDWG3WAANWAAgGCGuWCq74Y1AIY1gIbO76pgAKpggKqGlKrOuarv3arv786GNc6GlM7v3c7v7++qYO+qp+/Ohu/Oue/vqu/v3e/vzO/vzu/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwb/QIBwSCwaj8ikcslsOp/QqHRKrVqv2Kx2y+16v+CwMgAgm8voszrNXrvb8Lc8Tp/b6/i7Ps/f+/uAf4KBhIOGhYiHiomMi46NkH4Bk2KVlpeYmZqbnJ2en6ChbKKkpaanqKmqq6yblK2wsbKztLW2t0lnRSy8vb6/wMHCw8TFxsfDuMrLzM3Onq+7yNPU1dbXz9na29zdSLpE1+Lj5Nbe5+jp6qvR4cYaEMAbBx/lx/P19sbr/P3+/1/ADTFmokE+X/j0FUuokBjAhxAjSjTSbmAvFBMy/AIRTx69hsMYgmRBAsGIXhNTqlzpTaCQXiYYdLyYsVcKDAVyfmSxQWc9/xUUIuAsYIFXzwIfgQrNWZTFzZxI6x1NGnRoUxYloC4IQZTXCQcSeGkgkI+l2bNoaVV8STLA1V4gFNjE8DEhvpv0gBYIu8FAhrt0P+iVgPdDYZ70AOelsPcw17BeHSww6hfuAI1pM2veHMolABYgLgNbceFqVsj4vk5mwdUC0AQns1aQzKv165P4Ths90IE2a6K3ERvGABvh4tW9SLjlzLy584BkjJAW/avkydpdhX+FyjR41gcOuAOnUBxf6929xbsmj3tx8V5ZLcT/FROBgOf48+t34pmX8rcswOPLfNqB9UtwXM0GWS/B5ZbdPL0tyEuD9Nz03oQULLDBhaCJtv/fhyCCuNZnvMQ0U0EHsaDabxUG5sttr4ngIoPsFTgZVwd4MCOG5X302C9cZScWWbyEaOSRzPVHk0agyfVLVgUkwMFHT+UEm15WnlRllCNQWA+UUrYI1ZU1MhRkAat9tRNJJqGE5JtwrjSiMBgxeU1wI43zlYTAxOnnnwApmSeeeV4jUjCAJqroOXMWSmih03BVmUOLVmopM4JCqimkl3bq6SwjfirqqKQ+4VmpqKaaaqiqtuqqpae+KuuscLJK66245hdrrrz2mpmtvgYrrES7DmvssesAi+yyzGZTbLPQRmuLstJWa60qz16r7bagUMvtt+Bakm245Ja7hbfmpqtPbhTjruvuu99EB++89C7Rbr34motuvvyCe2+/AF+7b8AER2vGJAgnrPDCDDfs8MMQRyzxxBRXbPHFGGes8cYcd+zxxyCHLPLIJJdscsNBAAA7" v:shapes="_x0000_s1029">

## （2） 修改配置文件

- **访问测试\****—health** **监控终端**

启动该工程后在地址栏访问，health 是一种监控终端，用于查看当前应用程序（微服务）的健康状况。

## 1.5.2 添加 Info 信息

- **修改配置文件**

在配置文件中添加如下 Info 信息，则可以通过 info 监控终端查看到。

## （2） 访问测试

这些信息都是以 JSON 的格式显示在浏览器的。

## 1.5.3 开放其它监控终端

默认情况下，Actuator 仅开放了 health 与 info 两个监控终端，但其还有很多终端可用， 不过，需要手工开放。

## （1） 修改配置文件

在配置文件中添加如下内容。

## （2） 访问测试

### A、 mappings 终端

通过 mappings 终端，可以看到当前工程中所有的 URI 与处理器的映射关系，及详细的处理器方法及其映射规则。很实用。

### B、 beans 终端

可以查看到当前应用中所有的对象信息。

### C、 env 终端

可以看到当前应用程序运行主机的所有软硬件环境信息。

## 1.5.4 单独关闭某些监控终端

在开放了所有监控终端的情况下，有些终端显示的信息并不想公开，此时可以单独关闭这些终端。

## （1） 修改配置文件

在配置文件中添加如下内容。

## （2） 访问测试

在关闭这些终端后，其它终端仍可继续使用。

## 1.5.5 常用的监控终端

在百度搜索“springboot actuator”即可找到如下表格。

**HTTP**

**方法**

**监控终端**

**功能描述**

GET

/autoconfig

提供了一份自动配置报告，记录哪些自动配置条件通过了， 哪些没通过

GET

/configprops

描述配置属性(包含默认值)如何注入 Bean

GET

/beans

描述应用程序上下文里全部的Bean，以及它们的关系

GET

/dump

获取线程活动的快照

GET

/env

获取全部环境属性

GET

/env/{name}

根据名称获取特定的环境属性值

GET

/health

报告应用程序的健康指标，这些值由 HealthIndicator 的实现类提供

GET

/info

获取应用程序的定制信息，这些信息由 info 打头的属性提供

GET

/mappings

描述全部的 URI 路径，以及它们和控制器(包含Actuator

端点)的映射关系

GET

/metrics

报告各种应用程序度量信息，比如内存用量和 HTTP 请求计数

GET

/metrics/{name}

报告指定名称的应用程序度量值

POST

/shutdown

关闭应用程序，要求 endpoints.shutdown.enabled 设置为

true

GET

/trace

提供基本的 HTTP 请求跟踪信息(时间戳、HTTP 头等)

# 第2章 Spring Boot 工程应用

## 2.1 自定义异常页面

对于 404、405、500 等异常状态，服务器会给出默认的异常页面，而这些异常页面一般都是英文的，且非常不友好。我们可以通过简单的方式使用自定义异常页面，并将默认状态码页面进行替换。

直接在前面程序上修改即可，无需创建新的工程。

## 2.1.1 定义目录

在 src/main/resources 目录下再定义新的目录 public/error，必须是这个目录名称。

## 2.1.2 定义异常页面

在error 目录中定义异常页面。这些异常页面的名称必须为相应的状态码，扩展名为 html。

## 2.1.3 修改处理器模拟 500 错误

- **访问效果**
- **单元测试**
- **定义工程**

直接在前面工程中修改即可。

## 2.2.2 定义 Service 接口

- **定义** **Service** **两个实现类**

注意，实现类上要添加@Service 注解，以交给 Spring 容器来管理。

## 2.2.4 修改测试类

打开 src/test/java 中的测试类，在其中直接添加测试方法即可。

## 2.3 多环境选择

- **什么是多环境选择**

以下两种场景下需要进行“多环境选择”。

## （1） 相同代码运行在不同环境

在开发应用时，通常同一套程序会被运行在多个不同的环境，例如，开发、测试、生产环境等。每个环境的数据库地址、服务器端口号等配置都会不同。若在不同环境下运行时将配置文件修改为不同内容，那么，这种做法不仅非常繁琐，而且很容易发生错误。

此时就需要定义出不同的配置信息，在不同的环境中自动选择不同的配置。

## （2） 不同环境执行不同实现类

在开发应用时，有时不同的环境，需要运行的接口的实现类也是不同的。例如，若要开 发一个具有短信发送功能的应用，开发环境中要执行的 send()方法仅需调用短信模拟器即可， 而生产环境中要执行的 send()则需要调用短信运营商所提供的短信发送接口。

此时就需要开发两个相关接口的实现类去实现 send()方法，然后在不同的环境中自动选择不同的实现类去执行。

## 2.3.2 需求

下面将实现如下功能：存在开发与生产两种环境，不同环境使用不同配置文件，不同环境调用不同接口实现类。

## 2.3.3 多配置文件实现方式

- **定义工程**

复制前面打为 Jar 包的工程，并重命名为 03-multiEnv。

## （2） 定义配置文件

### A、 定义多个配置文件

在 src/main/resources 中再定义两个配置文件，分别对应开发环境与生产环境。

### B、 说明

在 Spring Boot 中多环境配置文件名需要满足 application-{profile}.properties 的格式，其中{profile}为对应的环境标识，例如，

- application-dev.properties：开发环境
- application-test.properties：测试环境
- application-prod.properties：生产环境

至于哪个配置文件会被加载，则需要在 application.properties 文件中通过spring.profiles.active 属性来设置，其值对应{profile}值。例如，spring.profiles.active=test 就会加载 application-test.properties 配置文件内容。

在生产环境下，application.properties 中一般配置通用内容，并设置 spring.profiles.active 属性的值为 dev，即，直接指定要使用的配置文件为开发时的配置文件，而对于其它环境的选择，一般是通过命令行方式去激活。配置文件 application-{profile}.properties 中则配置各个环境的不同内容。

## （3） 定义业务代码

### A、 定义业务接口

**B\****、 定义两个实现类**

**C\****、 说明**

在实现类上添加@Profile 注解，并在注解参数中指定前述配置文件中的{profile}值，用于指定该实现类所适用的环境。

## （4） 定义处理器

- **Idea** **下运行与访问**

### A、 运行

打开主类在 Idea 中直接运行，即可在控制台看到默认使用的是开发环境，即端口号使用的是 8888，而工程的根路径为/ddd。

### B、 访问

从页面显示内容可知，其执行的业务接口实现类为DevelopServiceImpl，即开发阶段应使用的实现类。

### C、 修改配置文件

**D\****、再运行**

将上次运行停掉后再次运行主类，即可在控制台看到使用的是生产环境，即端口号使用的是 9999，而工程的根路径为/ppp。

### E、 再访问

从页面显示内容可知，此次执行的业务接口实现类为 ProduceServiceImpl，即生产环境下应使用的实现类。

## （6） 在命令行下选择环境

将工程打为 Jar 包后，在命令行运行。若要想切换运行环境，必须要修改主配置文件吗？ 答案是否定的。只需添加一个命令参数即可动态指定。

### A、 在命令行下运行 Jar 包

例如，现在的主配置文件中指定的是 dev 环境。

将当前工程打为 Jar 包后，在命令行运行时添加如下参数。

此时执行的就是生产环境，调用的就是 ProduceServiceImpl 类。

### B、 说明

在命令行中添加的参数可以是写在配置文件中的任意属性。其原理是命令行设置的属性值的优选级高于配置文件的。

## 2.3.4 单配置文件实现方式

这种实现方式只能使用 application.yml 文件，使用 application.properties 文件好像文件本身就会出错。

## （1） 定义工程

复制前面的 3-multiEnv 工程，并重命名为 03-multiEnv2。

## （2） 修改配置文件

将原有的配置文件全部删除，然后定义 application.yml 文件。需要注意的是，这三部分之间是由三个减号（-）分隔的，必须是三个。而这三部分充当着之前的三个配置文件。

## （3） 运行与访问

运行与访问方式与前面的多配置文件的完全相同。

## 2.4 读取自定义配置

自定义配置，可以是定义在主配置文件中的自定义属性，也可以是自定义配置文件中的属性。

## 2.4.1 读取主配置文件中的属性

- **定义工程**

复制前面打为 Jar 包的工程，并重命名为 04-readConfig。

## （2） 修改SomeController 类

读取监听的端口号。

在@Value 注解中通过${ }符号可以读取指定的属性值。

## 2.4.2 读取主配置文件中的自定义配置

- **修改主配置文件**

在配置文件中添加如下自定义配置。

## （2） 修改SomeController 类

在处理器类中添加如下内容。

## 2.4.3 读取自定义配置文件中的属性

- **自定义配置文件**

该配置文件为 properties 文件，文件名随意，存放在 src/main/resources 目录中。

## （2） 定义配置属性类

- **修改\****SomeController** **类**

在处理器类中添加如下内容。

## 2.4.4 读取 List属性

- **修改自定义配置文件**

在配置文件中添加如下配置。

## （2） 定义配置属性类

- **修改\****SomeController** **类**

在处理器类中添加如下内容。

## 2.4.5 读取 List属性

- **修改自定义配置文件**
- **定义配置属性类**
- **修改\****SomeController** **类**

在处理器类中添加如下内容。

## 2.5 Spring Boot 下使用 JSP 页面

在 Spring Boot 下直接使用 JSP 文件，其是无法解析的，需要做专门的配置。

## 2.5.1 直接添加JSP 文件

- **定义工程**

复制第一个工程，并重命名为 06-jsp。

## （2） 创建 webapp 目录

在 src/main 下创建 webapp 目录，用于存放 jsp 文件。这就是一个普通的目录，无需执行 Mark Directory As。

## （3） 创建index.jsp

### A、 指定 web 资源目录

在 spring boot 工程中若要创建 jsp 文件，一般是需要在 src/main 下创建 webapp 目录， 然后在该目录下创建 jsp 文件。但通过 Alt + Insert 发现没有创建 jsp 文件的选项。此时，需要打开 Project Structrue 窗口，将 webapp 目录指定为 web 资源目录，然后才可以创建 jsp

文件。

指定后便可看到下面的窗口情况。

此时，便可在 webapp 中找到 jsp 的创建选项了。

### B、 创建 index 页面

在 src/main/webapp 下创建一个 html 文件，并命名为 index.html，创建完毕后再将其重命名为 index.jsp。因为 Idea 中是没有 JSP 页面模板的，不能直接创建 JSP 文件。

## （4） 启动后运行

此时启动工程后在浏览器直接访问，发现其并没有显示 index 页面。因为当前工程不能识别 jsp 文件。

## 2.5.2 使用物理视图

- **添加** **jasper** **依赖**

在pom 中添加一个 Tomcat 内嵌的 jsp 引擎 jasper 依赖。jsp 引擎是用于解析 jsp 文件的， 即将 jsp 文件解析为 Servlet 是由 jsp 引擎完成的。embed，嵌入。

org.apache.tomcat.embedtomcat-embed-jasper " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAwA6AgMAgAAAAAAAAAJARI6py+0Po5y02ouz3rz7D4biSJamA6TqyrbuC8fyTNf2jef6zvf+DwwKh8Si8YhsBQzJpvMJjUqn1Kr1is0aCgA7" v:shapes="_x0000_s1408">

## （2） 注册资源目录

在 pom 文件中将 webapp 目录注册为资源目录。

src/main/webapp META-INF/resources **/*.* " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJVhI+py+0Po5y02ouz3rz7D4biSJZOAKBqyq5uC79yTM92jd96zu9+D/wJg8ShsYg8KpPMpbMJfUqjVGHgaspqt9yu9wsOi8fkMsyMTqvX7Lb7Df8GCgA7" v:shapes="_x0000_s1028">

## （3） 创建 welcome.jsp

在 webapp 目录下再创建一个子目录 jsp，在其中创建 welcome.jsp 文件。

## （4） 修改SomeHandler 类

- **使用逻辑视图**
- **修改主配置文件**
- **修改处理器**
- **Spring Boot** **中使用** **MyBatis**
- **总步骤**
- 在 pom 中导入三个依赖：MyBatis 与 Spring Boot 整合依赖、MySQL 驱动依赖、Druid 依

赖

- 将 dao 目录注册为资源目录
- 在 Dao 接口上添加@Mapper 注解
- 在主配置文件中注册三类信息：映射文件、实体类别名、数据源

## 2.6.2 需求

完成一个简单的注册功能。

## 2.6.3 定义工程

复制 06-jsp 工程，并重命名为 07-mybatis。

## 2.6.4 修改pom 文件

导入三个依赖：mybatis 与 Spring Boot 整合依赖、mysql 驱动依赖与 Druid 数据源依赖。

org.mybatis.spring.bootmybatis-spring-boot-starter1.3.2mysqlmysql-connector-java5.1.47com.alibabadruid1.1.12 " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAwA6AgMAgAAAAAAAAAJLRIynyesNn4x02oqvznz7Dn5iSI5miZ5qyq5uC79yeQD2jef6zvf+DwwKh8Si8YhMKpfMpvMJjUqn1GpuYc1qt9yu9wsOi8fkcqAAADs=" v:shapes="_x0000_s1409">

## 2.6.5 修改 SomeHandler

- **定义** **Service** **接口及实现类**
- **定义\****Service** **接口**
- **定义\****Servivce** **实现类**
- **定义实体类及** **DB** **表**
- **定义实体类**
- **定义** **DB** **表**

在 DB 的 test 数据库中定义 student 表。

## 2.6.8 定义Dao 接口

Dao 接口上要添加@Mapper 注解。

## 2.6.9 定义映射文件

- **注册资源目录**

在 pom 文件中将 dao 目录注册为资源目录。

src/main/java **/*.xml " src="data:image/png;base64,R0lGODlhzQIGAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgA6AgQAgAAAAAAAAAJMjI+py+0Po5y02ouz3rz7D4biSJYNEADqyrbuC8fyTNf2jef6zvf+DwwKh8Si8YhMugzKpvMJjUqn1Kr1is2ittqu9wsOi8fk8jRQAAA7" v:shapes="_x0000_s1027">

## 2.6.11 修改主配置文件

在主配置文件中主要完成以下几件工作：

- 注册映射文件
- 注册实体类别名
- 注册数据源

## 2.7 Spring Boot 的事务支持

若工程直接或间接依赖于 spring-tx，则框架会自动注入 DataSourceTransactionManager

事务管理器；若依赖于 spring-boot-data-jpa，则会自动注入 JpaTransactionManager。

## 2.7.1 定义工程

复制 07-mybatis 工程，并重命名为 08-transaction。

当前工程完成在对用户注册时一次插入到 DB 中两条注册信息。若在插入过程中有发生异常，则已完成插入的记录回滚。

## 2.7.2 修改启动类

- **修改** **Service** **实现类**
- **Spring Boot** **对日志的控制**
- **logback** **日志技术介绍**

Spring Boot 中使用的日志技术为 logback。其与 Log4J 都出自同一人，性能要优于 Log4J， 是 Log4J 的替代者。

在 Spring Boot 中若要使用 logback，则需要具有 spring-boot-starter-logging 依赖，而该依赖被 spring-boot-starter-web 所依赖，即不用直接导入 spring-boot-starter-logging 依赖。

## 2.8.2 spring boot 中使用 logback

在 Spring Boot 中使用 logback 日志，有两种方式。

## （1） 添加配置属性

只需在核心配置文件中添加如下配置即可。

## （2） 添加配置文件

该文件名为 logback.xml，且必须要放在 src/main/resources 类路径下。

## 2.9 Spring Boot 中 SSRM 整合应用

- **实现步骤**
- 在 pom 中添加 Redis 与 Spring Boot 整合依赖
- 在配置文件中注册 Redis 连接信息
- 实体类实现序列化接口
- 在启动类上添加@EnableCaching
- 在查询方法上添加@Cacheble，在增删改方法上添加@CacheEvict
- 若使用 API 方式操作 Redis，则需要注入 RedisTemplate，然后通过 RedisTemplate 获取到

Redis 操作对象后就可以对 Redis 进行操作了。

## 2.9.2 Redis 数据分类

使用 Redis 缓存的数据可以划分为两类：DB 更新后，Redis 缓存中的数据就要马上清除，以保证将来缓存中的数据与 DB 中的数据的绝对一致性，这是一类数据；还有一类，对数据准确性要求不是很高，只要与 DB 中的数据差别不大就可以，所以这类数据一般会为其设置过期时效。

## 2.9.3 定义工程

复制 08-transaction 工程，并重命名为 09-redisCache。

当前工程完成让用户在页面中输入要查询学生的 id，其首先会查看 Redis 缓存中是否存

在，若存在，则直接从 Redis 中读取；若不存在，则先从 DB 中查询出来，然后再存放到 Redis 缓存中。但用户也可以通过页面注册学生，一旦有新的学生注册，则需要将缓存中的学生信息清空。根据id 查询出的学生信息要求必须是实时性的，其适合使用注解方式的Redis 缓存。

同时，通过页面还可以查看到总学生数，但对其要求是差不多就行，无需是实时性的。对于 Spring Boot 工程，其适合使用 API 方式的 Redis 缓存，该方式方便设置缓存的到期时限。

## 2.9.4 修改pom 文件

在 pom 文件中添加 Spring Boot 与 Redis 整合依赖。

## 2.9.5 修改主配置文件

在主配置文件中添加如下内容：

## 2.9.6 修改实体类 Student

由于要将查询的实体类对象缓存到 Redis，Redis 要求实体类必须序列化。所以需要实体类实现序列化接口。

## 2.9.7 修改代码

- **修改\****index** **页面**
- **修改\****SomeHandler** **类**

在其中添加两个处理器方法。

## （3） 修改Service 接口

在 Service 接口中添加一个业务方法。

## （4） 修改Service 接口实现类

- **修改** **Dao** **接口**

在其中添加两个方法。

## （6） 修改映射文件

可以使用简单类名作为别名。

## 2.9.8 启动 Redis

- **Spring Boot** **中** **Dubbo** **的整合应用**
- **定义** **commons** **工程** **11-dubboCommons**
- **创建工程**

这里就创建一个普通的 Maven 的 Java 工程，并命名为 11-dubboCommons。

## （2） 定义pom 文件

<**groupId**>com.abc</**groupId**>

<**artifactId**>11-dubboCommons</**artifactId**>

<**version**>1.0-SNAPSHOT</**version**>

#### 

<**project.build.sourceEncoding**>UTF-8</**project.build.sourceEncoding**>

<**maven.compiler.source**>1.8</**maven.compiler.source**>

<**maven.compiler.target**>1.8</**maven.compiler.target**>

</**properties**>

#### 

<**dependency**>

<**groupId**>org.projectlombok</**groupId**>

<**artifactId**>lombok</**artifactId**>

<**version**>1.18.2</**version**>

<**scope**>provided</**scope**>

</**dependency**>

#### 

- **定义实体类**
- **定义业务接口**
- **将工程安装到本地库**

运行 Maven 的 install 命令，将工程安装到本地版本库，以备其它工程使用。

## 2.10.2 定义提供者 11-provider-springboot

- **创建工程**

创建一个 Spring Boot 工程，并重命名为 11-provider-springboot。

## （2） 定义pom 文件

### A、 添加 dubbo 与 spring boot 整合依赖

**B\****、 添加** **zkClient** **依赖**

**C\****、 其它依赖**

- dubboCommons 依赖
- spring boot 与 redis 整合依赖
- mybatis 与 spring boot 整合依赖
- 数据源 Druid 依赖
- mysql 驱动依赖
- slf4j-log4j12 依赖
- spring-boot-starter-web 依赖

### D、pom 文件

<**groupId**>com.abc</**groupId**>

<**artifactId**>11-provider-springboot</**artifactId**>

<**version**>0.0.1-SNAPSHOT</**version**>

<**parent**>

<**groupId**>org.springframework.boot</**groupId**>

<**artifactId**>spring-boot-starter-parent</**artifactId**>

<**version**>2.0.5.RELEASE</**version**>

<**relativePath**/> _<!-- lookup parent from repository -->_

</**parent**>

<**properties**>

<**project.build.sourceEncoding**>UTF-8</**project.build.sourceEncoding**>

<**project.reporting.outputEncoding**>UTF-8</**project.reporting.outputEncoding**>

<**java.version**>1.8</**java.version**>

</**properties**>

<**dependencies**>

__

<**dependency**>

<**groupId**>com.alibaba.spring.boot</**groupId**>

<**artifactId**>dubbo-spring-boot-starter</**artifactId**>

<**version**>2.0.0</**version**>

" src="data:image/png;base64,R0lGODlhcAAEAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAgBXAAIAgQAAAO7u7u/v7wECAwIXDHSJq83vIpyyUgaO2Lz7D4biSJYfUAAAOw==" v:shapes="_x0000_s1026">

_<!--_ *zk* *客户端依赖：__zkclient -->*

<**dependency**>

<**groupId**>com.101tec</**groupId**>

<**artifactId**>zkclient</**artifactId**>

<**version**>0.10</**version**>

</**dependency**>

<**dependency**>

<**groupId**>com.abc</**groupId**>

<**artifactId**>11-dubboCommons</**artifactId**>

<**version**>1.0-SNAPSHOT</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>org.springframework.boot</**groupId**>

<**artifactId**>spring-boot-starter-data-redis</**artifactId**>

</**dependency**>

__

<**dependency**>

<**groupId**>org.mybatis.spring.boot</**groupId**>

<**artifactId**>mybatis-spring-boot-starter</**artifactId**>

<**version**>1.3.2</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>com.alibaba</**groupId**>

<**artifactId**>druid</**artifactId**>

<**version**>1.1.10</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>mysql</**groupId**>

<**artifactId**>mysql-connector-java</**artifactId**>

<**version**>5.1.47</**version**>

</**dependency**>

<**dependency**>

<**groupId**>org.slf4j</**groupId**>

<**artifactId**>slf4j-log4j12</**artifactId**>

<**version**>1.7.25</**version**>

<**scope**>test</**scope**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework.boot</**groupId**>

<**artifactId**>spring-boot-starter-web</**artifactId**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework.boot</**groupId**>

<**artifactId**>spring-boot-starter-test</**artifactId**>

<**scope**>test</**scope**>

</**dependency**>

</**dependencies**>

<**build**>

<**plugins**>

<**plugin**>

<**groupId**>org.springframework.boot</**groupId**>

<**artifactId**>spring-boot-maven-plugin</**artifactId**>

</**plugin**>

</**plugins**>

<**resources**>

__

<**resource**>

<**directory**>src/main/java</**directory**>

<**includes**>

<**include**>**/\*.xmlinclude**>

</**includes**>

</**resource**>

</**resources**>

</**build**>

## （3） 定义Service 实现类

- **定义** **Dao** **接口**
- **定义映射文件**

' src="data:image/png;base64,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" v:shapes="_x0000_s1187">

insert into employee(name, age) values(#{name}, #{age}) select count(id) from employee select id, name, age from employee where id=#{xxx} ' src="data:image/png;base64,R0lGODlhzQI/AXcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAMAAgA6AvwAhwAAAAAAAAgKDQ0KCAMAAAQIDQ0LCwgDAw0LCgQABAoLDQgDAAAABAoEAAMGCw0IBAADCAsLDQgDBAsGAwAECgMDCAgGAwQAAAoEAwgICAMEBAgEAAMABAQAAwMAAwoIBAoLCgMECgoLCwAAAwgGBAoKCAQDCAsLCwsLCgQEBAsIBAMGCAQICggKCwQDAwgKCgoGBAQGCgsGBAQEAwQGCAgEBAQDBAMDAwAANQAAYAAAgAAAlAAApwAA/AAA/wAA+gA1uQA19wBgqgBgzABg9ACAAACANQCUNQCAYACUYACUhgCnqjUAADUAgDUAlDUA/zUA/DU1hjU1/DVg+jWAADWANTWGuTWG3TWGzjWG8jWG9zWnYDW5hjW5zmAAAGAAYGAAgGAA/2BggGCAAGCUAGCGuWCG92CnYGC5hmCq3WCqzGCq72Cq9GDMqmDMzmDM74Y1AIY1NYY1gIY1/4aUAIaGp4aG+oa5YIbdqobOzIbO3YbO74bd74bO9KpgAKpggKpg/6qnAKqqp6rMYKrdhqrO96rvqqrvzqrv3arvzKrv786GNc6GlM6G/M65Nc7vzs7v7++qYO+qp++q+u/MYO/dhu/Ohu/Oue/O9+/vqu/vzO/vzu/v3e/v7+/v8u/v9AECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAEACDCwIMGDBhMiXKiwIcOHDiNCnCixIsWLFjNi3KixI8ePHkOCHCmyJMmTJlOiXKmyJcuXImHKdElzps2aOG/qzMlzp8+eQH8KDRqUoMCjSJMqXcq0qdOnUKNKnUq1qtWrWLNq3cq1q9evYMOKHUu27NIARs2qXcu2rdu3cOPKnUu3bl20dvPq3cu3r9+/gAP7xSu4sOHDiBMrXszYKuHGkCNLnky5smWnjy9r3sy5s+fPXjN/7US6tOnTqFOrXs26tevXsGPLnk0bNujbuHNrFe21tu/fwIMLH078tO7jyJMr5d119aUmV4rLxvQki/BKVLpI3879tfLv4G8z/+fqHPp2TWCis6Zu/fSkHntSO0JiaIx20tjvF89kn/QmOkUEqAQkr/0X4BvbzVeffqaF5+CDlo23lWl+rNGdaeipR9t78aFGCRKH0MFgd/zd998StWGHoHQfhnifH1iUBuGMNC4moVb+wRFjJ5zIoYMOFnZyiRN1/DhEJ5L8qMORnaD3Y3Q9WgGGDjyIoaQOQBB4midz+OBDkJ1Q5yV8qVGixCN0IGhgEQhWUsUdAaLYCSUBFiEnfwF28R8XYxSBhCKB1FnEmXTISZojW1CRBBlsugmnnfhRseKcdXbhiBF8dFKipn0WcR+ens5J6IqbwCEEaTWmqipgN2alqRc7lv/2XJDP8aCIJDvkkR6Gu87a4w57ZJhha9QFyV4nHIrpZRBaoqZiJ9j9SQmmm5a2KXZu0EFtf9WuCel8hEgayBLRKvLss9BmR2kb/X2oyLWSwjspaovkoMiq+OZ7V1pgWcIEmLI2QavASO6gh49AkpbklWv0yCRpw673RJCN/KAIsmTCdq6kc2K65op0CpqtoaR1W6hpjpBbBR/jPivvoX9y2sW4J1Iq6Bsh1zkvapHgIIC+QAe9VqtYvRorabMiTTCu8fVI5a3AmuYwrxqqVixpFV+c7BNe+sCsahsjOG2mnRgoLaal1WyaySSn7CbL5HL8cifzXVzih4Ss3DHZpI3//Vq99wot+OBfEX1VjkcnLeTSUZPGCLDoPcyjHJJPLXGQk3zJZcavhb03ytsaqnbaJ5dd+qEqw/3spZmim9/eBg7IKcn8kayaH6d2QvjuvF9luFUUWuikkmskjauuxCv9o62Wl7Zwlqh9EkbXX3YCiJdTQNFha6AGiAbHY3e/InYBgnh6aSETWueAbq/cMhU6d492zis6wmZp5Pt5cf4xowajjL0LoACb8ruqXOiACEQgumQTCNkVZ4AQjOBA+DWaBFrwgtfhWGzs58AHSvCDvCsgVTBIwhKa0IMgTKHQRKjCFrrwhX9hIQxnSMMaskWGNsyhDneIFRzy8IdADOJR/3woxCIacYZEPKISlxjBJDLxiVAUnBOjSMUq1miKVsyiFpODxS168Yud6SIYx0jGyIixjGhMY2HOqMY2ujEvbHyjHOfYljjS8Y54BIsd88jHPjqGgn0ZAAEKMBdBEhI3BjjAzw6DgAQogC+JXGSqGvlIK+6xK4YUCyWfkkmmbFItBlhAABhQybA0wAGKeQAEIpCVUI6ylFk55VEMIAEFPACVR2lAAHDplQkEgAJUoaUteQkAXRKzKr4EJlJUycoqXlIwn8xKNNUyTa88QJmKacAhpenIrVwTKcKcAC8HUIFmgiWSUwnnOMu5FXQiRZuWBKReXElKgTTSAmjBpSDzCf8ABFwALYfEQAYIEIBV6hItv1wKPR95T34eFC3K3GcAtpkUfwL0ARogAAQ2MNGG7vIon5QoIQfAgUeGsgAWnag9/6lSgcCznwnA50eLidACYFSjHCVkSin6lIeOlKD83OkyV7kUkpp0AQXwKUi7CVOGOlKoAJAoLl8KAF8itKXkZKVHp1rTm25UpVAVCAYk+RSr1lQgWc3lWSX60bCONSnMdKY89/JJfwIzq+4EgDCbWkyiwrOaStmkXaPKzmgioAM/G4AHyCqQvTbyAwwAwQUcIM7BHnaRgkVsVBd70qiGwLGOBK1TKznYtB6FpJCVLGUdINqqkBQEmv1ma+3J1KT/oNO0US0pbUnbzUaKoJZNzStfT1vOtwrkm/28wF3ZeVoOpHay4pxtMXnqlKwaFwDITcprYwvM2VJ1t3KFJlMF201f6hOoARjBz7473KaQd7TtzS57o4reEZCAApSsbDc7y1f5EvKbY2WregX8s7r2tpsPQCgDPoBfR4qTwFJJMFoiy136IlS9INXsUsRZVVRK+JXg5WsjS1Dfn5l3qbAcwGcVqdcFYPO92FUwg/PrAAjnkqhQUTE6Q4nN4yoYtj/7po37as7LhjcwBoavQJKJW5fyFLAVPTB8o+lfpeD2mjTepDs3WeVGgsAEEWhyk4f7XiM/tsEKeDBzp2JmRx60/55jDnFgTXCCWraZt3iGaQnWjJRkDteVCq4kcsus2TPTOM7TjQqgJyzoFxfazYwmrDlz+eTaQvGZW0lyewmLggvwlL1GdgqMMytJxSZ2sYH19FGw7GAHcLmefDX1ZhfZgBToVNUgxfUskQpeShq5AQtGc2V1veqWBlazwN7zpP1JXUk3RaAh6CeyYV3XaUu22ZLm73FdDWbialXKvw52lol9lOsmGNvY5bY5TRtuZSOF2Uq5Llr5/ERMYyWlBY1AmVmKYYn6lbpW7XGuEQoBFEh5yRD1cXoZe1qgQkAFwnY1S+uJ71VKGMNo1a2F871xHMcVxgddQQJm3OqON1OQx/98J1pEroCH9tvh5pzvMlsa8qeylONWZbm/9c3vRcZ1yQXAbVpBvvKRR9zk5S41AVJ+lAkEfc24rXnLL5xYmIuVsTJfor15B2WocNgqsgwLvKPy87Av5ed5MTtTjCqWvI59Kmw/O47VDk4WrxrHVNz67rreFD9fRbhcObFU8C3wd1vaLoBXijHB4veqzrQqi081QguP8B7zXYl696PmNy+QzHP+83n0POhHL0fRk/70aTQ96lcPRtWz/vVZdD3sZ3/pudL+9p+XPe53H0Td8/73OvQ98IePRNsT//ipNz7yl9965TP/+bF3/uFOKBvoW18ydqR+9a/PfRtJH3jat03/98d/mOzH5jlVsyD51y8Y869mEbFC/3l2lRr2238w3zdgaSq0ti9cDIER0wn/gyr3V4BwlH8jVDY6chrwRxpOkzBDUiRLojBKwiTDcwVRMiW28gdXkiWlkjsGGIJ0kX2ZACunsQlxsD2LYyG1AjXBQn8Q0ytNkAZyADm7EoCkATgiuINwYUf+AjCkEQm5EzAsyDh78IBBsjBKQoOS0yQwWBo9w3A8OIViQYImmDYpeBpJYzyN4zS2wjSl0TwxmH46SIVmSBbmVyqxYglR0CxKU4QWAoal8Tgv+DBiODmSgzsEeIZ8WDgIOBXBQxr8VxrD8yNlYISFODDLgwiUgxrP/wMJA6g7fTiJXOF+psGGbhh+lLiJWWGJ4ccanBiKVeGJn6gaoniKUSF8qLiKfKGKrPiK+wKLsggerjiLtnhDf3iLutgYtbiLvqhHufiLwth+wTiMxtgXvXiMyjgVybiMzogZxfiM0ugWzTiN1jhB15iNrRiN2tiNocGN3hiOnQiO4liOVFGN5iiM6JiOvkiKpfiO8BiP8jiP0nF97kiP+JiP+riP8GiP5LgU+Xgs/DiQBFmQ+KMuKPR89+gaSCgbOJgaAmkaHCIf9NEfBQIg9xMbJrM+magaH7Mda9I/tVEtdFORI7IdXOIlQSI97YEs1FM9xKEgFpkuJ2ktMymTNf8pidBniYMIG4oDGw8ZGxPpIeaTk2CjQbDBNimClMPxHyL5GyTZIiJiQVfTCSzpHpxDHFJplKgRlUUpiEfjj2OBOM5TgTH4JG9IiFOiA+rxgEPAgUoCPVvSJTAZJlzjA1mJPqPSN3WCIqCiHwv0l6YTJ50QKOuDJm2TKIvSKG9CmOkyKTljKWizKYLJKXmyQHx5meCDKY5CmIYpIJBgJmiyM6TBJVowPRZjldPjA+2ROV5CBFczlFdzPV3zNRjTIY1AJtKTPXbwmqQhPV7SkqchmmkymPfTmZDymYMSmnupgEMolmJhNGpZNcOyhQTjhNGRNH8gl9hJLBNDGseyNbX/2ZEkOTfoIi8N5IYbSZjgIi5x8yeeQ5Pr0i5/Ii/wcgZoc5DaQSffIzacSQVnkykk6RqbswcseZXFcpXg+Z2y+Z2qKZy3+ZthYB2T8AOF8AQWk6ATapdmcJde05EH2SYAqgh+M6CqATg6qZD/qBQ/KDUIk4RXAoHXqYQ/sgY4GJSnUZVZE6Gu8ZE5cyCRAjKCUgTsMiJKWRrtozpy0x/oUjcyQzMn86NsIqX4yTclaTdjkCj+yQfPUqIzSaBzQAQS+ZJrkJLVE5sZU5UKWhpDaT1EwCWw+Z0s6ZpdA4Ss4TleypVQiAN7AJ1hIZ0u+jRySIQK0zhjSDXeSTGpKZ7L/wKig4kEg5CfpoEuflMyXyoz/nE+Sfo+CDI3Tno3SJA3mVKpfSOpdDIvn5qlm8mlq4qpsAGnEpmXnZCSFsqgaeqga0oabVqhhaA9VzOnnKMsH7oaeDqZl5oaKOqnYEGWp0GHkaOF1/msYSgHtjKtTYgaVZk5ZToHstoal4IHY2A7j2mpokMHT6k2o0M3qcOpdIM2rqMuYxM7BFI7axOupCOSrzMt4Kon2sKqW2o64roasFoa0iOmqZGbfTChBbqgQTKwbMo5KcksV5ObBhoGBjsbxZop6ZoaeriHKjqW+yc8a5kwi7M8iTCyUfMcy3MxwwM9jxg9q6mS1oM92vMa4v+TLuXzLp1SBO4KP/oDKg6UPmjCkZsaN/Gzs/OjM4eSkTirP01bn50SM/RDN96zMl2an+njqKbhsAvqJRYDnDLbCF5iBtoDtngZH65pm23aCWJrIcJqLHeZmq7RPUXQn59jMx1EIWFpfQtpkAaJmbCRnn5bioDwNVWZj8paQYO7uEEqGxyktYyLQGJrm4eLj4nbG5GbuZq7ufp4uez4uV2xjqBri6I7urJYuqb7iqibuqu4uqx7iq77uqEYu7K7ibRbu5N4u7jLh7q7u2bYu747hcAbvDs4vMQbgsZ7vAWYvMprf8zbvOv3vNA7ftI7vdxXvYrhTp3kSYdnvQM0vAv/RRWX9xXVZHbbi2LHhXcKJUoJRRfh673IuKJ0Mb5RBktlgXbYRXmBVVtZd1z6Kxf0C79vgY5StVIAhb7JdcAulXAP1b5nF2kH5VeoFEmJNFC/ZGMv9b4bB2vYpb5J8XUAIFAERVQihV0Z9VXNtlNelVNbhcAlzHYnBVVhJcDjCBjCNVtaBlyb1AACd3kTgHc87GToREmhREpp9V3TVG0FVltQZlYL1wD/ZmSm9gCRBV0pB1rPtVqWpWGkNmudpWM6HFphbL80PIryGxaCt3ELB14EFmoIrFB252wdtklZBW+yFceb9kmDxsQatmG89F3+xWpplnICdl9Ztl+81l/Y/wRPACYABDZkZfxHheFncUbHfFZNfIdbXxdJR7xcrMRhSWxpeqxMhtW9TffHFBXIR6ddaybI+mVSdsdli6xTCfBlYcZniBbJZmwYdUxuIkZudOfGxyZJcTUBwPRNJ4Zc8BR3jZXIIWZURVxKudxhlIYUsjbFq/xuuubKroZg1NZN14xqxWRryUVRb6fLPXTG5NtzaAVzFRdmVpdS2BRwTHFxAgBopPxPFABPsrRj7LtNH3dzRGVVLMC/6EbNTgZXaIFh3FxUDgdx4xZp72zPzVVJO4d06LzLQER3UIG/dGXKXsd0Gf2NQZR47gXSdhHAfebAI12F6tzSs4i9MI17Mv8907RX0zYNezid06y30zyNej7906QX1EINekRd1Jx31EiteUq91H3U1E4dei8NAAMZ1aU31VVt1W7Ut/2o1W3E1e/o1V+N1azRgK/RkLGBoykq1mTEkwCTCf4nGz/5GgEYiWydfCCrhgwYK0p4JBdIqGfJlpNjJJ0Alz/igabisXfdfGNZgkdTNlnYnYeqOIpTnQKznW74kGW42GPkg//CM0PYkDRKsooz2mrwhJJtGlHI2Z2N1Y5tGiiogoPNA4JgqIA9qA+p2fbC2q2d1wtIGpiYGo+jK9da2WBgh9T6fw7YiPsHgrz9RW4tiACTiCX7NIWYsk2wsi2rJY9o18//7UXuGNxh/d1bBNalSN7lTdb7iN5aBNXsDd5T/d7k597yHX31fbrxfd+eq9+cSN/8XXv/3br5HeDL598EjnkDfuDEZ+AKXkQM3uC9l+AQznsPPuE8VOEWHnwSnuE3veEcrtPqzbkiPuIkPrhGHeIlnuIqvuLnnXsoHo8RyeIyPuOd6+IgSxtoDZSoja3VgRpri6QmCRsfqZEW6S15uxpDThz5EypdubN56zc4eUBmCiYKSqcyG5NBbuSQSzpAKh1RjhonfuMCaKesMdeuodau8eN985WxAbjcU+Tn0+ZMmUEIwjqqQaroMz9sfkCHm6tqHhxbCbBLSZqA/pWRuNZ+/5SGv40kZumEaLmCavnobmnYWNKRU14aytKtxCmkhFmZ42qpeWKcKKKchJKYisIob4CccrJAkWnnlNkpnwLrq/Mnrt4ukpo/89Occ3maPvC1q9maXSOmaBofVUmbjcqjFPugmAAFvekDYgq2EKqXoynoSKqYjIIGjQkpjWszoVLr2iGYlbnpzglASe3aV5ja3WmdQWLZFoLZ05moC0qh8CGstlmv+mGeGoSeebue3xKq7lku8ZmvREqfOqsdKgIv36O0m+Iud5uvksoaBSo9p2kdGiqcw26XVL6hD9shV1mhikAdGfoEbLChxULvHektKNKebxAIRzCi7yqi+8kmC/8PtQZPBUX66chqL4j+1FPdotNaoxTIMJDO6Axzozueow66o3+OGj46pOOjQVJ68/aeqW2zrnHTqUyqQak6M0tQM1KKM0Nqt6damF1fOpXqpHgusGHKpmR66RgfoX2u8brKOYDwpmv/q2EgBWR6kVX/Niy/pPcx61iqHVCKIlTq9KzRM31q49H52oH6hbY99IOK7mjOsFizqPPuofV+ggACqQ+/7aRqokeKOn5/9ZjapDEDqqJ6t6XKNy5Dn6t/t2j/+arhsGpOqx9vq8TuoA8aq9vDqzWL91KQlSbP9Jq6ri2P9YHPMVs/J6GqN6Sa9ieq82EenQr42J3grMcNrev/vv3TWq3Lfa1Ijzmaw62y7Rrfaq+ncZ7qbzrnWjrpWrSr07NPv58eAyCyQ682aShXq7H4ryUAQckIn06dKBV5s4nOwIINHTr0NIdIw09hJj4s2KjHnopZIm7shOnJmoYRLxacBLJgRB9BIIUc2UljH4sYbTpUuMShoyWVqvAJdITKm06OGHaqNBQplS4GGSosouRlpjE6C1K1erOgHyENAXwFG1bsWLJlzZ5Fm1btWrZt3b41GyAA3LQO/ZDUBEbHXpKdLjXZyyOR3r079vgFrIOHok559wJ5Kenxy4cVfVz2QRLQ5SlQDmttSLXIaKJJRyNRJHo0Q9NFUKuWWvBg/9RHdEZHhcTTJ9CeVG6/UV2E4WyEBR0VL9ga9dLThHyTbnicaPDYgUZv+Qm6pESHIi//UGT5ct9Gl81AoYlZ5aTLLjul/Jwxc0Hv42F+Z6y9E9TRUnX/DOq54oIbiDiijEOOOdcUUS6/h/zAwiG6JqSwQgsvxBAuuTIMSz8PPwQxRBFHJFGrpA4MMZDYSmxIRcpYhDHGmwBxD6a+ZATtRBJdxNErDn8EMkghf9wQyB6PRDLJGHUE8bgVWXTyRSWn1K68Gm2k0iEmm8QtySG/BDNMMcUq8scsz0QzTTXXZLNNN2UcM04556SwTDrvxDNPPffks08//wSUTzsDJbRQQ/8PRTRRRRf9c1BGH4U0UkknpbTSOB21NFNNN+W0U08bnetTUUcltVRTTyULU1RXZbVVV1+lU1VYZ6W1VltvJTNUXHfltVdfPZX1V2GHJbZYPIM1Nllll2X2LWSbhTZaaZN9dlprr8UW1mqz5bZbbzfd9ltxxyXX0HDLRTdddS/VdV1334UXzHPjpbdee8ea91599103X37/Bbhbf1cdgIACAva04IMR1rbdSA1YIAAGFGgLgQQoPkthsSzGeFSI5aJAz48nnpNjITXe+GKyDJBAgQccCKuBAGCma4IAQmZYzIH/NJmtnt36WdQHcO4zaDGNDhNpAFhWYAKaARigggj/KjTgAAFyDnNnMAuWi2YELpBrYQB6/jpssGS+GQC0QV45YpKhJkCut9Gy2IKuv1q7gAc0IACCDQI4uGzA2cobbrlmHhvswctyOiwMMogbggi4Hnzvvv8WOyzBD7bc7wBYSMBuxL/6mfICBuCAYogDV5x1s8lq/KsJVgj99c0BeDzyqW8fa+SOKZ8bLJsPXzzqqR8Y4eoJJkZeeQY+SB6A5TvG4Gqsv9R6yKqt/4rpsVX+XvWWw1ebaNLBN8tkBDq4Wumxvg7Z+LBQ/4ABEC5wwGnv3TcLdRDYB8DQljY+pA1PLtFrgOTUxgIADsADAniA/fAXu7Ds72IRvF/+VnCB/5Ctz3pka6AHWrCAgw0gBAOkmMVEQED0hWVoHJsACzgINalZUAEJnFoDCmDDsoCwfQkoQdzkokDjVQ8sAvzKC8GnRIoxMWaZux6Qsjckmz2NcgGIHvmumDwPpqxjPVQZEvmnOZWZ7AGHex4FYOiALXIPLWeU2/+uJsA2Mu5pahMbEvHIRAqCpY18pJ3qSHg+jOlRhwI04hYhd7gsisWEA3BBATDwgjJerI06nJ8QsehGMhYSZ0oz4faWtgDzSW9xsjulzaCoNgVGUYoOC5TN4ie196lMfmAx2hjJJ8YWpq+SCuiixT6gxos57ZY+AyDH0EayY5qlj5hMItEOScym3f/xK80EpMpESb4ASrMAFgOBCaZ2zGaSJWowyEAMDkDJFFqSlmfLXDnJ0jNeBvFwEDhBxNDoybDIYAZ39OfTAioWaLrylYky3tdWqb4LQLEB1uziWUyGOgVA7G1nXCUhw9fFBqRxjQodC0Z7CMCOluCdpGtoWp4pNgderaV8tGbiMpfNJr6tZy2F2gPVloKFgRSlGSUjDU5QAxuA4Jc+xVvmkOpLjFHUol88ogMQIE4/vnNoq4smVq86SLAY0aAHJZTgskg5yQkuAJIj6+4UR7ThlRKl94zA8EDXVALEVKMmQxvthrlGw511anCzK97kEsi1RS+tdnyiCw84R2r20Y//QpQcIBVHMrMqEI5jTd1jhzg5yP5VLBALWQJR8Mu+Sq6g1+zs+xTnV1N+rpeyO91J5RfBFOKPtom7AWXx11VOfvVCU6zVUockXLM8oJUPTZQuEQsrilLNar4lEiyZVUUxUXctZnVrWF+LFlnWSmaBVUt3ocsh4I7XvOetVHnRu172Jkq97YVvfPv0XvnW177sum9+9Yso+u7Xv/+lS38BPGACn0XABUZwgg+cYAYDeMENhnB+HxxhCsd3whXGMHoF/CYRZdjDv9owh0H0YRLvKsQi9lCJVVyrE6NYOyuG8atabJNFRMhDnJADX0KUlyvcJMY/XlWL7+KQTHzBQR760cuNPMTjhkDIR0CG8qhCvAk42LghNW6IZPYyhMYQpscFSXJDHKODHuN4y534w14mQ+WudCLKb/7UhjPhBSsXZBNx+AyTC8LkMCOmL3xuwhr+ABmH6PnKOVAEnBUNLulayBJMUHJBItHmTphZByTRspr70udM70UNYPiymEGNkUjgoLeLRjWj5ExnnOD5IWbmgSAMgxFOz3rPoy40rg+d6FT3GlJzIV6whT1sYhfb2MdGdrKVvWxmN9vZz4Z2tKU9bWpX29rXxna2tb1tbndb2QEBADs=" v:shapes="_x0000_s1166">

- **修改启动类**

在启动类上必须要添加@EnableDubboConfiguration 注解，开启 Dubbo 的自动配置功能。

## （7） 修改主配置文件

**mapper-locations**: classpath:com/abc/dao/*.xml

**spring**:

*# 注册数据源*

**datasource**:

*# 指定数据源类型为* *Druid*

**type**: com.alibaba.druid.pool.DruidDataSource

**driver-class-name**: com.mysql.jdbc.Driver

**url**: jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8

**username**: root

**password**: 111

*# 连接* *Redis 服务器*

**redis**:

**host**: redis5OS

**port**: 6379

**password**: 111

*# 连接* *Redis 高可有集群*

*# redis:*

*# sentinel:*

*# master: mymaster*

*# nodes:*

*# - sentinelOS1:26379*

*# - sentinelOS2:26379*

*# - sentinelOS3:26379*

*# 配置缓存*

**cache**:

**type**: *redis #* *指定缓存类型*

**cache-names**: realTimeCache *#* *指定缓存区域名称*

*# 功能等价于* *spring-dubbo 配置文件中的__dubbo:application/ # 该名称是由服务治理平台使用*

**application**:

**name**: 11-provider-springboot

*# 指定* *zk 注册中心*

**dubbo**:

**registry**: zookeeper://zkOS:2181

*#* *zk 集群作注册中心*

*# registry: zookeeper://zkOS1:2181?backup=zkOS2:2181,zkOS3:2181*

## 2.10.3 定义消费者 11-consumer-springboot

- **创建工程**

创建一个 Spring Boot 工程，并重命名为 11-consumer-springboot。

## （2） 定义pom 文件

l dubbo 与 spring boot 整合依赖

l zkClient 依赖

l dubboCommons 依赖

l JSP 引擎 jasper 依赖

l slf4j-log4j12 依赖

l spring-boot-starter-web 依赖

com.abc 11-consumer-springboot 0.0.1-SNAPSHOT org.springframework.boot spring-boot-starter-parent 2.0.5.RELEASE UTF-8 UTF-8 1.8 com.alibaba.spring.boot dubbo-spring-boot-starter 2.0.0 com.101tec " src="data:image/png;base64,R0lGODlhzQLFAncAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAIAAwA6AjQChwAAAAAAAA0LCgQABAoLDQoEAAAECg0KCAMAAAQIDQ0IBAMEBAADCAgEAAgGAwMGCwQAAwgKDQMAAwgGBAAABAoLCgQAAAsGAwMABAQEBA0LCwgDAAgDAwAAAwMGCAgKCwoEAwgICAsLDQMDCAsLCwMGCggDBAgKCgoKCAoLCwsLCgMDAwoIBAQICgMECgsKCAAECAMDAAQGCAQICwQDAwoGBAQGCggEBAQDBAAANQ0LOQ0LJQAAYAAAgAAAlAAApw0kTA05XwA1uQBgqgBgzDUAADkLDSULDSULOTkLJSULJTUAgDUAlDUApzU1hjU1gDU1lDlMTCVMcDlMcDlegTVgpzWGuTWGzjWG3TWGzEwkDV85DV85OUwkOUw5TExMOUxMX19MTF9ecExMcExegV9wTExwX0xwcExwgV+BX1+BcF+BgWAAAGAAYGAAgHBMJXBMOXBeOXBeX3BMTHBwTHBwcGBggGBglGBgp3CBX3CBcHCBgWCGuWCq3WCqzGCq74Y1AIY1NYY1gIFeOYFeTIFwTIGBX4GBcIGBgYaGlIaGp4aquYbOzIbO3YbO76pgAKpggKrOuarvzKrv3arv786GNc6GlM7Ouc7v3c7vzM7v7++qYO+qp+/Ohu/Oue/vqu/vzO/vzu/v3e/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwj/AAMIHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEMiBCCQpMkAJ1OiXKmyJcuXLmPCnCmzJs2bNnPi3Kmz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" v:shapes="_x0000_s1192">

<**artifactId**>zkclient</**artifactId**>

<**version**>0.10</**version**>

</**dependency**>

<**dependency**>

<**groupId**>com.abc</**groupId**>

<**artifactId**>11-dubboCommons</**artifactId**>

<**version**>1.0-SNAPSHOT</**version**>

</**dependency**>

__

<**dependency**>

<**groupId**>org.apache.tomcat.embed</**groupId**>

<**artifactId**>tomcat-embed-jasper</**artifactId**>

</**dependency**>

<**dependency**>

<**groupId**>org.slf4j</**groupId**>

<**artifactId**>slf4j-log4j12</**artifactId**>

<**version**>1.7.25</**version**>

<**scope**>test</**scope**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework.boot</**groupId**>

<**artifactId**>spring-boot-starter-web</**artifactId**>

</**dependency**>

<**dependency**>

<**groupId**>org.springframework.boot</**groupId**>

<**artifactId**>spring-boot-starter-test</**artifactId**>

<**scope**>test</**scope**>

</**dependency**>

</**dependencies**>

<**build**>

<**plugins**>

<**plugin**>

<**groupId**>org.springframework.boot</**groupId**>

<**artifactId**>spring-boot-maven-plugin</**artifactId**>

</**plugin**>

</**plugins**>

<**resources**>

__

<**resource**>

<**directory**>src/main/webapp</**directory**>

<**targetPath**>META-INF/resources</**targetPath**>

<**includes**>

**/*.* " src="data:image/png;base64,R0lGODlhzQKjAHcAMSH+GlNvZnR3YXJlOiBNaWNyb3NvZnQgT2ZmaWNlACH5BAEAAAAALAMAAwA6An8AhgAAAAAAAA0LCgQDCAsLDQsGAwMGCw0LCwgDAAMGCggGCgoEAAAECgQABAoLDQQIDQgDAwgKDQ0IBA0KCAMDCAAANQAAYAAAgAAAlAAApwA1uQBgqgBgzDUAADUAgDUAlDU1hjVgpzWGuTWG3TWGzmAAAGAAYGAAgGBglGCGuWCq74Y1AIY1NYY1gIaGp4aquYbOzIbO3YbO76pgAKpggKrv786GNc6GlM7v7++qYO+qp+/Ohu/Oue/vqu/v3e/vzu/vzO/v7wECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwECAwf/gAABgoSDhoWIh4qJjIuOjZCPkpGUk5aVmJeamZybnp2gn6KhpKOmpainqqmsq66hgwCys7S1tre4ubq7vL2+v8DBwsPExcbHyMnKy8zNzs/Q0bUB1NLW19jZ2tvc3d7f4OHigrHj5ufo6err7O3u3NXv8vP09fb3+Pm58fr9/v8AAwocKIsfwYMIEypcyHCYwYYQI0qcSNHdw4q6gmjcyLGjx48gQ4rsuG2kyZMoU2JcydLZxZa1UsqcabIkzZs4P8LcyVPYy54heXgYkbMoSW0phRI1arSn06f7ykEFEHRoSh8tLlxQYVIHBhknZQkYQIDW2LK0ChiYdVZXW6oh/22Q2Kj0atatXb/WnMr36U+eHGdwLSp0sEivYD3OmLvxAIIEChYwAOAYsmS2DRxQfhx5sq3KneFqFLyxh4kaRgvnTdxx8ca+sHn+3anxxwrGWLUO5vHBhVYOQXJfAK5RdRDViIVq1QvkhFaitje8BlAgwIO01tOunVX9eq7usmrf5ihXo3CuvH0PN3+X+HEP6OEHQfx+uYzmz8VLDxK7P8vZMAXRQwmM0SXfexnUgBgNGuDQkXHIYfCCVYg1R5RxNliAGmiX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" v:shapes="_x0000_s1165">

## （3） 修改主配置文件

application: name: 11-consumer-springboot # 指定 zk 注册中心 dubbo: registry: zookeeper://zkOS:2181 # zk 集群作注册中心 # registry: zookeeper://zkOS1:2181?backup=zkOS2:2181,zkOS3:2181 " src="data:image/png;base64,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" v:shapes="_x0000_s1193">

- **创建\****jsp** **页面**

在 src/main/webapp 目录下定义 index.jsp 文件。

## （5） 定义处理器

- **定义** **jsp** **页面**

在 src/main/webapp 目录下定义 welcome.jsp 文件。

## （7） 修改入口类

- **Spring Boot** **下使用拦截器**

在非Spring Boot 工程中若要使用SpringMVC 的拦截器，在定义好拦截器后，需要在Spring 配置文件中对其进行注册。但 Spring Boot 工程中没有了 Spring 配置文件，那么如何使用拦截器呢？

Spring Boot 对于原来在配置文件配置的内容，现在全部体现在一个类中，该类需要继承自 WebMvcConfigurationSupport 类，并使用@Configuration 进行注解，表示该类为一个JavaConfig 类，其充当配置文件的角色。

## 2.11.1 定义工程

复制 04-customConfig 工程，并重命名为 05-interceptor。

## 2.11.2 定义拦截器

- **定义处理器**
- **定义配置文件类**
- **Spring Boot** **中使用** **Servlet**

在 Spring Boot 中使用 Servlet，根据 Servlet 注册方式的不同，有两种使用方式。若使用的是 Servlet3.0+版本，则两种方式均可使用；若使用的是 Servlet2.5 版本，则只能使用配置类方式。

## 2.12.1 注解方式

- **创建工程**

创建一个 Spring Boot 工程，并命名为 11-servlet01。

## （2） 创建Servlet

- **修改入口类**

在入口类中添加 Servlet 扫描注解。

## 2.12.2 配置类方式

- **创建工程**

创建 spring boot 工程，并命名为 11-servlet02。

## （2） 定义Servlet

- **定义配置类**
- **Spring Boot** **中使用** **Filter**

在 Spring Boot 中使用 Filter 与前面的使用 Servlet 相似，根据 Filter 注册方式的不同，有两种使用方式。若使用的是 Servlet3.0+版本，则两种方式均可使用；若使用的是 Servlet2.5 版本，则只能使用配置类方式。

## 2.13.1 注解方式

- **使用工程**

直接在 11-servlet01 工程上进行修改，不再创建新的工程。

## （2） 创建 Filter

- **修改入口类**

在@ServletComponentScan 注解中注册 Filter 所在的包，当然，Spring Boot 支持通配符的使用。

## 2.13.2 配置方式

- **使用工程**

直接在 11-servlet02 工程上进行修改，不再创建新的工程。

## （2） 定义 Filter

- **修改配置类**

在配置类中添加如下方法。

# 第3章 模板引擎 Thymeleaf

## 3.1 Thymeleaf 简介

Thymeleaf[taɪm lif]，百里香叶，是一个流行的模板引擎，该模板引擎采用 Java 语言开发。 Java 中常见的模板引擎有Velocity、Freemaker、Thymeleaf 等。不同的模板引擎都会具有自己的特定的标签体系，而 Thymeleaf 以 HTML 标签为载体，在 HTML 的标签下实现对数据的展示。

Thymeleaf 本身与 SpringBoot 是没有关系的，但 SpringBoot 官方推荐使用 Thymeleaf 作为前端页面的数据展示技术，SpringBoot 很好地集成了这种模板技术。

Thymeleaf 的官网为： [http://www.thymeleaf.org](http://www.thymeleaf.org/)

## 3.2 Spring Boot 集成 Thymeleaf

- **创建工程**

创建一个 Spring Boot 工程，命名为 thymeleaf，并在创建工程时导入如下依赖。

## 3.2.2 定义配置文件

- **定义处理器**
- **定义\****html** **页面**

在 src/main/resources/templates 目录下定义 index.html 页面。

在页面的标签中需要添加 Thymeleaf 的命名空间属性：

xmlns:th="[http://www.thymeleaf.org"](http://www.thymeleaf.org/)

## 3.3 Thymeleaf 标准表达式

常用的 Thymeleaf 标准表达式有三种。标准表达式都是用于获取代码中存放到 Model

中的属性值的，只不过获取方式不同而已。

以下举例均在前面的 thymeleaf 工程基础上直接修改，无需再创建新的 Module。

## 3.3.1 变量表达式${…}

使用${…}括起来的表达式，称为变量表达式。该表达式的内容会显示在 HTML 标签体文本处。

该表达式一般都是通过 th:text 标签属性进行展示的。

## （1） 修改处理器类

- **创建** **VO** **类**
- **修改\****index** **页面**

直接在页面中添加如下内容：

## （4） 测试效果

- **选择表达式\**\***{***\*…\****}**

选择表达式，也称为星号表达式，其是使用*{…}括起来的表达式。一般用于展示对象的属性。该表达式的内容会显示在HTML 标签体文本处。但其需要与th:object 标签属性联用， 先使用 th:object 标签选择了对象，再使用*{…}选择要展示的对象属性。该表达式可以有效降低页面中代码的冗余。

不过，其也可以不与 th:object 标签联用，在*{…}中直接使用“对象.属性”方式，这种写法与变量表达式相同。

该表达式一般都是通过 th:text 标签属性进行展示的。

## （1） 修改index 页面

直接在页面中添加如下内容：

## （2） 测试效果

- **URL** **表达式\****@{***\*…\****}**

使用@{…}括起来，并且其中只能写一个绝对 URL 或相对 URL 地址的表达式，称为 URL 表达式。这个绝对/相对 URL 地址中一般是包含有动态参数的，需要结合变量表达式${…}进行字符串拼接。

@{…}中的 URL 地址具有三种写法。为了演示这三种写法的区别，先为当前工程添加一个上下文路径，然后直接在 index.html 文件中修改。

## （1） 以 http 协议开头的绝对地址

在进行字符串拼接时使用加号(+)连接，容易出错。但使用双竖线则无需字符串拼接， 简单易读。但是，Idea 会对其中的问号(?)报错，不过其不影响运行。

在页面通过查看源码可以看到其解析结果。当然，对于 and 符(&)Thymeleaf 会将其解析为实体形式(&)，但浏览器会对(&)进行正确解析。

## （2） 以/开头的相对地址

在 URL 表达式中，Thymeleaf 会将开头的斜杠(/)解析为当前工程的上下文路径

ContextPath，而浏览器会自动为其添加“http://主机名:端口号”，即其即为一个绝对路径。

在页面通过查看源码可以看到其解析结果中已经添加了上下文路径。

而在页面则可以看到浏览器对其解析的结果已经添加了 [http://localhost:8080。](http://localhost:8080。/)

## （3） 不以/开头的相对地址

在页面通过查看源码可以看到其解析结果中是未添加任何东西的，即没有上下文路径。也就是说，其是相对于当前请求路径的一个相对地址。

而在页面则可以看到浏览器对其解析的结果已经添加了 http://localhost:8080/xxx/test， 这是相对于当前请求路径的的一个相对地址的转换结果。

## 3.4 Thymeleaf 常见属性

- **逻辑运算相关属性**
- **th:if**

该属性用于逻辑判断，类似于 JSTL 中的<c:if/>。

### A、 修改处理器

在处理器中添加如下语句。

### B、 修改 index 页面

在 index.html 文件中添加如下语句。

### C、 效果

- **th:switch/th:case**

该属性用于多分支判断，类似于 Java 中的 Swith-Case 语句。

### A、 修改处理器

在处理器中添加如下语句。

### B、 修改 index 页面

在 index.html 文件中添加如下语句。

一旦某个 case 与 switch 的值相匹配了，剩余的 case 则不再比较。th:case=”*”表示默表示默认的 case，前面的 case 都不匹配时候执行该 case。

## （3） th:each

该属性用于遍历数组、List、Set、Map，类似于 JSTL 中的<c:forEach/>。

### A、 遍历 List

遍历数组、Set 与遍历 List 方式是相同的。

### a、 修改处理器

在 Controller 中添加如下代码。

### b、 修改 index 页面

前面的 stu 为当前遍历对象，而${students}为遍历的集合。

### 

c、 效果

**B\****、 遍历状态对象**

**a\****、 效果**

**C\****、 遍历** **Map**

需要清楚，Map 的键值对是一个 Map.Entry 对象。

### 

a、 修改处理器

**b\****、 修改** **index** **页面**

Idea 对这个遍历对象的 key、value 属性会报错，但不影响运行。

### 

c、 效果

- **html** **标签相关**
- **th:text/th:utext**

这两个属性均用于在标签体中显示动态文本。但不同的是，th:utext 会解析文本中的

HTML 标签，而 th:text 则是原样显示。

### A、 修改处理器

**B\****、 修改** **index** **页面**

**C\****、 效果**

- **th:name/th:value**

该属性用于获取标签动态 name 属性值，及标签的默认 value 值。

### A、 修改处理器

**B\****、 修改** **index** **页面**

**C\****、 效果**

在页面查看源码可以看到如下效果。

## （3） URL 路径相关

th:action、th:src、th:href，这三个都是与 URL 路径相关的属性。若这些 URL 中包含有动态参数，则它们的值需要URL 表达式@{…}与变量表达式${…}配合使用。下面以![img]()标签中的 th:src 为例演示用法。

### A、 创建目录放图片

在工程的 src/main/resources/static 目录下创建一个 Directory，命名为 images，并在其中存放入一张图片 car.jpg。

### B、 修改处理器

**C\****、 修改** **index**

- **css/js** **相关**
- **th:id/th:name**

这两个属性可以获取标签的动态 id 与 name 属性，以便在 js 中使用。

## （2） th:style

该属性用于获取标签的 css 动态样式。

## （3） th:onclick

该属性用于获取标签的单击事件所触发的动态事件，即单击事件所触发的方法名。这些

js 事件属性很多，都是以 th:on 开头。

## 3.4.4 内联属性th:inline

其应用场景是，在HTML 某标签显示文本内部存在部分数据来自于动态数据，或 JS 内部需要使用动态数据的场景。在该场景中，可以使用[[${…}]]或[(${…})]的方式将动态数据嵌入到指定的文本或脚本内部。

th:inline 的取值有四个：text, javascript、css 和 non。分别表示内联文本、内联脚本、内联 css，与禁止内联，即不解析[[${…}]]。不过，th:inline=”text”可以省略不写。

## （1） 内联文本

运行效果是：

## （2） 内联脚本

- **内联** **CSS**

### A、 修改 index 页面

先在 index 页面中添加一个

，要使用内联 CSS 将其背景色设置为红色。



### B、 修改处理器

**C\****、 再修改** **index** **页面**

瑞在 index 页面中添加如下代码，使用内联 CSS 定义

的样式。此内联的写法 Idea



不识别，但不影响运行。

## 3.4.5 万能属性th:attr

该标签只所以称为万能属性，其主要具有两个应用场景，这两个场景在官方文档中均有详细讲解。

## （1） 为无定义的属性赋予动态参数

很多HTML 标签的属性都有其对应的 Thymeleaf 的 th 命名空间中属性，但并不是所有的都存在对应属性。若没有其对应的 th 命名空属性，但又想让其获取动态值，就需要使用该属性了。

5.1 标题为：为任意属性设置值。

## （2） 一次为多个属性赋予动态参数

若想一次为多个属性赋予动态参数，则可以使用该属性。

## 3.5 Thymeleaf 运算基础

- **Thymeleaf** **字面量**

字面量，即字面常量。Thymeleaf 中有四种字面量：文本、数字、布尔，及 null。

## （1） 文本字面量

由单引号括起来的字符串，称为文本字面量。

## （2） 数字字面量

数字字面量就是数字，可以是整数，也可以是小数。

## （3） 布尔字面量

布尔字面量就是 true、false，也可以写为 TRUE、FALSE、True、False。

### A、 修改处理器

**B\****、 修改** **index** **页面**

- **null** **字面量**

表示空，其一般用于判断。若对象未定义，或对象已定义但其值为 null，则为 null 的判断结果为 true；若集合不为 null，但长度为 0，则为 null 的判断结果为 false。

### A、 修改处理器

**B\****、 修改** **index** **页面**

- **Thymeleaf** **运算符**
- **字符串拼接运算符**

可以使用加号(+)进行字符串拼接，也可以将文本字面量与动态数据直接写入由双竖线括起来的字符串内，此时无需使用加号进行连接。

## （2） 算术运算符

\+ , - , * , / , %，但不支持++与--运算。

## （3） 关系运算符

以下两种写法均支持：

\> , < , >= , <= , == , !=

gt , lt , ge , le , eq , ne

## （4） 逻辑运算符

非：not 或者 !

与：and 或：or

## （5） 条件运行符

? :

## 3.6 Thymeleaf 内置对象

为了方便使用，Thymeleaf 中内置了很多对象，程序员可以直接使用。

## 3.6.1 ServletAPI 对象

通过#request、#session、#servletContext 可以获取到 HttpServletRequest、HttpSession 及

ServletContext 对象，然后就可以调用其相应的方法了。

## （1） 修改处理器

在处理器中再定义一个处理器方法。

## （2） 定义页面

再定义一个页面 index2.html。

## （3） 提交请求及运行效果

- **表达式实用对象**
- **实用对象简介**

这些实用对象都是以#开头，由于是对象，所以首字母是小写，且一般都是复数形式， 即一般都是以 s 结尾。

l #executionInfo：获取当前 Thymeleaf 模板对象的执行信息。

l #messages：获取外部信息。

l #uris/#urls：URI/URL 处理工具。

l #conversions：类型转换工具。

l #dates：日期处理工具。

l #calendars：日历处理工具。

l #numbers：数字处理工具。

l #strings：字符串处理工具。

l #Objects：对象处理工具。

l #booleans：布尔值处理工具。

l #arrays：数组处理工具。

l #lists：List 处理工具。

l #sets：Set 处理工具。

l #maps：Map 处理工具。

l #aggregates：聚合处理工具，例如，对数组、集合进行求和、平均值等。

l #ids：th:id 属性处理工具。

## （2） 应用举例

下面就简单举几个例子，演示一下用法。

### A、 修改处理器

**B\****、 定义页面**

再定义一个页面 index3.html。

### C、 效果

**3.7** **Thymeleaf** **工程实践**

将前面的 SSRM 工程修改为使用 Thymeleaf 引擎作为前端页面的工程。

## 3.8 路径问题基础理论

- **路径的构成**

路径由两部分构成：资源路径与资源名称。即路径 = 资源路径 + 资源名称

资源路径与资源名称的分水岭为：路径中的最后一个斜杠。斜杠的前面部分称为资源路径，后面部分称为资源名称。

例如：

l 请求路径：http://localhost:8080/xxx/test/index

l 资源路径：http://localhost:8080/xxx/test

l 资源名称：index

## 3.8.2 路径的分类

根据是否可以唯一的定位一个资源，可以将路径划分为两类：绝对路径与相对路径。

l 绝对路径：可以唯一的定位一个资源的路径。在 Web 应用中，一般使用 URL 形式表示。

- 相对路径：仅依赖此路径无法唯一定位资源，但若为其指定一个参数路径，则可以将其转换为一个绝对路径，这样的路径称为相对路径。在 Web 应用中，一般使用 URI 形式表示。

l 转换关系： 绝对路径 = 参照路径 + 相对路径

## 3.8.3 绝对路径分类

根据路径作用的不同，可以将绝对路径分为：资源定义路径，与资源请求路径。

l 资源定义路径：用于表示资源位置的路径。

l 资源请求路径：客户端所发出的对指定资源的请求路径。

## 3.8.4 相对路径分类

根据相对路径是否以斜杠开头，可以划分为两类：斜杠路径，与非杠路径。

l 斜杠路径：以斜杠开头的相对路径。

l 非杠路径：不以斜杠开头的相对路径。

## 3.8.5 斜杠路径分类

对于斜杠路径，根据其出现的位置的不同，可以划分为：前台路径与后台路径。

l 前台路径：出现在 HTML、JS、CSS，及 JSP 文件的静态部分的斜杠路径。例如，![img](https://tool.lu/markdown/%E2%80%9D%E2%80%9D/)

[backgroud:img(“”) window.location.href=””。](https://tool.lu/markdown/””/)

l 后台路径：出现在 Java 代码、JSP 文件的动态部分（Java 代码块、JSP 动作等）、XML、

properties 等配置文件中的斜杠路径。

## 3.8.6 路径解析器

相对路径，最终都会经过路径解析器，将其转换为绝对路径，以定义或定位一个资源。不同的相对路径，其路径解析器也是不同的。

l 前台路径：路径解析器为浏览器。

l 后台路径：路径解析器为服务器。

- 非杠路径：若非杠路径出现在前台路径位置，其路径解析器为浏览器；若非杠路径出现在后台路径位置，其路径解析器为服务器。

## 3.8.7 解析规则

不同的路径解析器，对同一个相对路径的解析结果是不同的。所谓解析结果，指的是将相对路径所转换为的绝对路径。由于 绝对路径 = 参照路径 + 相对路径 ，所以，不同的解析器，会为相对路径匹配不同的参照路径。换句话说就是，我们学习的重点是，浏览器、服务器对于不同的相对路径所匹配的参照路径到底是谁。

## （1） 一般规则

l 前台路径：其参照路径为当前 web 服务器的根。

l 后台路径：其参照路径为当前 web 应用的根。

- 非杠路径：其参照路径为当前请求路径的资源路径。例如，

l 请求路径： http://localhost:8080/xxx/test/index

l 当前 web 服务器的根： http://localhost:8080

l 当前 web 应用的根： http://localhost:8080/xxx

l 资源路径： http://localhost:8080/xxx/test

## （2） 规则特例

l 在代码中使用HttpServletResponse 的 sendRedirect()方法使用斜杠路径进行重定向时， 其参照路径按照之前规则，应该是当前 web 应用的根，但实际情况是，当前 web 服务器的根。

l 以上规则对于一次跳转是没有问题的，若跳转次数超过一次，则有可能会存在问题。

# 第4章 Spring Boot 源码解析

## 4.1 自动配置源码解析

使用 Spring Boot 开发较之以前的基于 xml 配置式的开发，要简捷方便快速的多。而这完全得益于 Spring Boot 的自动配置。下面就通过源码阅读方式来分析自动配置的运行原理。

## 4.1.1 解析@SpringBootApplication

打开启动类的@SpringBootApplication 注解源码。

我们发现@SpringBootApplication 注解其实就是一个组合注解。

## （1） 元注解

前四个是专门（即只能）用于对注解进行注解的，称为元注解。

l @Target

l @Retention

l @Documented

l @Inherited：表示注解会被子类自动继承。

## （2） @SpringBootConfiguration

查看该注解的源码注解可知，该注解与@Configuration 注解功能相同，仅表示当前类为一个 JavaConfig 类，其就是为 Spring Boot 专门创建的一个注解。

## （3） @ComponentScan

顾名思义，用于完成组件扫描。不过需要注意，其仅仅用于配置组件扫描指令，并没有真正扫描，更没有装配其中的类，这个真正扫描是由@EnableAutoConfiguration 完成的。

## （4） @EnableXxx

@EnableXxx 注解一般用于开启某一项功能，是为了简化代码的导入，即使用了该类注解，就会自动导入某些类。所以该类注解是组合注解，一般都会组合一个@Import 注解，用于导入指定的多个类，而被导入的类一般分为三种：

### A、 配置类

@Import 中指定的类一般以 Configuration 结尾，且该类会注解@Configuration，表示当前类是一个 JavaConfig 配置类。

### B、 选择器

@Import 中指定的类一般以 Selector 结尾，且该类还实现了 ImportSelector 接口，表示当前类会根据条件选择要导入的类。

### C、 注册器

@Import 中指定的类一般以 Registrar 结尾，且该类实现了 ImportBeanDefinitionRegistrar

接口，用于导入注册器。该类可以在代码运行时动态注册指定类的实例。

## 4.1.2 解析@EnableAutoConfiguration

该注解用于开启自动配置，是 Spring Boot 的核心注解，是一个组合注解。所谓自动配

置是指，其会自动找到其所需要的类，然后交给 Spring 容器完成这些类的装配。

## （1） @Import

用于导入框架本身所包含的自动配置相关的类。其参数 AutoConfigurationImportSelector

类，该类用于导入自动配置的类。

## （2） @AutoConfigurationPackage

用于导入用户自定义类，即自动扫描包中的类。

## 4.2 application.yml 的加载

application.yml 文件对于 Spring Boot 来说是核心配置文件，至关重要，那么，该文件是如何加载到内存的呢？需要从启动类的run()方法开始跟踪。

## 4.3 Spring Boot 与 Redis 的整合

在 spring.factories 中有一个 RedisAutoConfiguration 类，通过前面的分析我们知道，该类一定会被 Spring 容器自动装配。但自动装配了就可以读取到 Spring Boot 配置文件中 Redis 相关的配置信息了？这个类与 Spring Boot 配置文件是怎么建立的联系？

## 4.4 MyBatis 与 Spring Boot 的整合

在 External Libraries 中找到 mybatis-spring-boot-starter 依赖。而该依赖又依赖于mybatis-spring-boot-autoconfigurigure。其 META-INF 中有 spring.factories 文件，打开这个文件我们找到了 Mybatis 的自动配置类。

该类中还包含两个@Bean 方法用于创建相应的 Bean。

## 4.5 自定义 Starter

前面的代码中，无论是 Spring Boot 中使用Web、Test，还是 MyBatis、Dubbo，都是通过导入一个相应的 Starter 依赖，然后由 Spring Boot 自动配置完成的。那么，如果我们自己的某项功能也想通过自动配置的方式应用到 Spring Boot 中，为 Spring Boot 项目提供相应支

持，需要怎样实现呢？同样，我们需要定义自己的Starter。

## 4.5.1 Starter 工程的命名

l 官方定义的 Starter 命名格式：spring-boot-starter-{name}

l 非官方定义的 Starter 命名格式：{name}-spring-boot-starter

## 4.5.2 手写 Starter

- **需求**

下面我们自定义一个我们自己的 Starter，实现的功能是：为用户提供的字符串添加前辍与后辍，而前辍与后辍定义在 yml 或properties 配置文件中。例如，用户输入的字符串为 China， application.yml 配置文件中配置的前辍为$$$，后辍为+++，则最终生成的字符串为

$$$China+++。

## （2） 实现

### A、 创建工程

创建一个 Spring Boot 工程，命名为wrap-spring-boot-starter，并导入 Configuration Processor 与 Lombok 依赖。

### B、 定义一个 Service 类

该 Service 类是当前 Starter 功能的核心类。其核心功能就是在这个类中完成的。

该类中的成员变量可以随意命名，但一般与欲在 Spring Boot 中使用的属性名同名。

### C、 定义配置属性封装类

我们指定当前类用于封装来自于 Spring Boot 核心配置文件中的以 some.service 开头的

beore 与 after 属性值。即用于封装配置文件中的如下属性值：

- service.before
- after

该类的对象是由系统自动创建，所以无需将其将给Spring 容器管理。

### D、定义自动配置类

为了加深大家对“自动配置类与配置文件属性关系”的理解，这里再增加一个功能：为some.service 再增加一个组装开关，一个 boolean 属性 enable，当 enable 属性值为 true 时， 或没有设置 some.service.enable 属性时才进行组装，若 enable 为 false，则不进行组装。

### E、 创建 spring.factories 文件

在 resources/META-INF 目录下创建一个名为 spring.factories 的文件。该配置文件是一个键值对文件，键是固定的，为 EnableAutoConfiguration 类的全限定性类名，而值则为我们自定义的自动配置类。

## 4.5.3 使用自定义的 Starter

在本地要使用我们自定义的 Starter，首先要保证其已经被 Install 到了本地 Maven 库。

## （1） 创建工程

创建一个 Spring Boot 工程，仅需要一个 web 依赖。

## （2） 导入自定义 Starter 依赖

- **定义\****yml**

自定义 Starter 中的属性在配置文件中也是有自动提示功能的。

## （4） 定义 Controller

- ***\*运行访问\****

启动类无需修改，直接启动运行即可。