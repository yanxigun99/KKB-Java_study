# 课堂主题

Spring架构分析及手写spring框架之ioc专题

**课堂目标**

搞明白spring的体系结构

搞明白IoC的作用以及通过手写框架实现该功能

**知识要点**

[**课堂主题**](https://wordhtml.com/#_bookmark2)[**课堂目标**](https://wordhtml.com/#_bookmark1)[**知识要点**](https://wordhtml.com/#_bookmark0)

[**Spring\****体系结构**](https://wordhtml.com/#_bookmark4)

1. [核心容器](https://wordhtml.com/#_bookmark3)
2. [AOP和设备支持](https://wordhtml.com/#_bookmark5)
3. [数据访问及集成](https://wordhtml.com/#_bookmark6)
4. [Web](https://wordhtml.com/#_bookmark7)
5. [报文发送](https://wordhtml.com/#_bookmark13)[Test](https://wordhtml.com/#_bookmark12)

[**源码阅读篇**](https://wordhtml.com/#_bookmark11)

[Spring重要接口详解](https://wordhtml.com/#_bookmark10)[BeanFactory继承体系](https://wordhtml.com/#_bookmark9)

[体系结构图](https://wordhtml.com/#_bookmark8)

[BeanFactory](https://wordhtml.com/#_bookmark14) [ListableBeanFactory](https://wordhtml.com/#_bookmark15) [HierarchicalBeanFactory](https://wordhtml.com/#_bookmark17) [AutowireCapableBeanFactory](https://wordhtml.com/#_bookmark16) [ConﬁgurableBeanFactory](https://wordhtml.com/#_bookmark18) [ConﬁgurableListableBeanFactory](https://wordhtml.com/#_bookmark19) [BeanDeﬁnitionRegistry](https://wordhtml.com/#_bookmark22)

[BeanDeﬁnition继承体系](https://wordhtml.com/#_bookmark21)

[体系结构图](https://wordhtml.com/#_bookmark20)

[ApplicationContext继承体系](https://wordhtml.com/#_bookmark24)[体系结构图](https://wordhtml.com/#_bookmark23)

[容器初始化流程源码分析](https://wordhtml.com/#_bookmark27)[主流程源码分析](https://wordhtml.com/#_bookmark26)

[找入口](https://wordhtml.com/#_bookmark25)[流程解析](https://wordhtml.com/#_bookmark28)

[创建BeanFactory流程源码分析](https://wordhtml.com/#_bookmark31)[找入口](https://wordhtml.com/#_bookmark30)

[流程解析](https://wordhtml.com/#_bookmark29)

[加载BeanDeﬁnition流程分析](https://wordhtml.com/#_bookmark33)[找入口](https://wordhtml.com/#_bookmark32)

[流程图](https://wordhtml.com/#_bookmark35)

[流程相关类的说明](https://wordhtml.com/#_bookmark34)[流程解析](https://wordhtml.com/#_bookmark36)

[Bean实例化流程分析](https://wordhtml.com/#_bookmark40)

[找 入 口](https://wordhtml.com/#_bookmark39) [流程解析](https://wordhtml.com/#_bookmark38)

[AOP流程源码分析](https://wordhtml.com/#_bookmark37)

[查找BeanDeﬁnitionParser流程分析](https://wordhtml.com/#_bookmark47)[找入口](https://wordhtml.com/#_bookmark46)

[流程图](https://wordhtml.com/#_bookmark45)

[流程相关类的说明](https://wordhtml.com/#_bookmark44)[流程解析](https://wordhtml.com/#_bookmark43)

[执行BeanDeﬁnitionParser流程分析](https://wordhtml.com/#_bookmark42)[找入口](https://wordhtml.com/#_bookmark41)

[流程图](https://wordhtml.com/#_bookmark52)

[流程相关类的说明](https://wordhtml.com/#_bookmark51)[流程解析](https://wordhtml.com/#_bookmark50)

[产生AOP代理流程分析](https://wordhtml.com/#_bookmark49)[AspectJAwareAdvisorAutoProxyCreator的继承体系](https://wordhtml.com/#_bookmark48)[找入口](https://wordhtml.com/#_bookmark57)

[流 程 图](https://wordhtml.com/#_bookmark56) [事务流程源码分析](https://wordhtml.com/#_bookmark55)

[获取TransactionInterceptor的BeanDeﬁnition](https://wordhtml.com/#_bookmark54) [找入口](https://wordhtml.com/#_bookmark53)

[流程图](https://wordhtml.com/#_bookmark61)[流程解析](https://wordhtml.com/#_bookmark60)

[执行TransactionInterceptor流程分析](https://wordhtml.com/#_bookmark59)[找入口](https://wordhtml.com/#_bookmark58)

[流程图](https://wordhtml.com/#_bookmark62)

[**源码分析\****--***\*参考**](https://wordhtml.com/#_bookmark63)

- Spring核心容器体系结构
  - [(1)BeanFactory](https://wordhtml.com/#_bookmark65)
  - (2)BeanDeﬁnition
    - [IOC容器的初始化IOC容器的初始化包括BeanDeﬁnition的Resource定位、载入和注册这三个基本的过程。](https://wordhtml.com/#_bookmark67)
  - [1、XmlBeanFactory(屌丝IOC)的整个流程](https://wordhtml.com/#_bookmark68)
  - 2、FileSystemXmlApplicationContext的IOC容器流程
    - 基于XML的依赖注入
  - 依赖注入发生的时间
  - 2、AbstractBeanFactory通过getBean向IOC容器获取被管理的Bean，
  - 3、AbstractAutowireCapableBeanFactory创建Bean实例对象：
  - 4、createBeanInstance方法创建Bean的java实例对象：
  - 5、SimpleInstantiationStrategy类使用默认的无参构造方法创建Bean实例化对象：
  - 6、populateBean方法对Bean属性的依赖注入：
  - 7、BeanDeﬁnitionValueResolver解析属性值：
  - 8、BeanWrapperImpl对Bean属性的依赖注入：
    - 基于Annotation的依赖注入
  - AnnotationConﬁgApplicationContext对注解Bean初始化：
  - AnnotationConﬁgApplicationContext注册注解Bean：
  - AnnotationConﬁgApplicationContext扫描指定包及其子包下的注解Bean：
  - AnnotationConﬁgWebApplicationContext载入注解Bean定义：
    - IOC容器中那些鲜为人知的事儿
  - 1、介绍
  - 2、SpringIOC容器的lazy-init属性实现预实例化：
  - 3、FactoryBean的实现：
  - 4、BeanPostProcessor后置处理器的实现：
  - 5、SpringIOC容器autowiring实现原理：

**手写框架篇**

框架分析

# Spring体系结构

Spring总共大约有20个模块，由1300多个不同的文件构成。而这些组件被分别整合在核心容器

（CoreContainer）、AOP（AspectOrientedProgramming）和设备支持（Instrmentation）、数据 访问及集成（DataAccess/Integeration）、Web、报文发送（Messaging）、Test，6个模块集合中。 以下是Spring5的模块结构图：

组成Spring框架的每个模块集合或者模块都可以单独存在，也可以一个或多个模块联合实现。每个模块的组成和功能如下：

1. **核心容器**

**由\****spring-beans***\*、\****spring-core***\*、\****spring-context***\*和\****spring-expression***\*（\****Spring Expression Language,SpEL***\*）\****4***\*个模块组成。**

**spring-beans\****和***\*spring-core\****模块是***\*Spring\****框架的核心模块**，包含了**控制反转**

**（\****InversionofControl,IOC***\*）和依赖注入（\****DependencyInjection,DI***\*）**。BeanFactory接口是Spring 框架中的核心接口，它是工厂模式的具体实现。BeanFactory使用控制反转对应用程序的配置和依赖性规范与实际的应用程序代码进行了分离。但BeanFactory容器实例化后并不会自动实例化Bean，只有当Bean被使用时BeanFactory容器才会对该Bean进行实例化与依赖关系的装配。

**spring-context\****模块构架于核心模块之上，他扩展了***\*BeanFactory\****，为她添加了***\*Bean\****生命周期控制、** **框架事件体系以及资源加载透明化等功能。**此外该模块还提供了许多企业级支持，如邮件访问、远程访 问、任务调度等，ApplicationContext是该模块的核心接口，她是BeanFactory的超类，与BeanFactory不同，ApplicationContext容器实例化后会自动对所有的单实例Bean进行实例化与依赖关系的装配，使之处于待用状态。

**spring-expression\****模块是统一表达式语言（***\*EL\****）的扩展模块，可以查询、管理运行中的对象，同时也 方便的可以调用对象方法、操作数组、集合等。**它的语法类似于传统EL，但提供了额外的功能，最出色 的要数函数调用和简单字符串的模板函数。这种语言的特性是基于Spring产品的需求而设计，他可以非常方便地同SpringIOC进行交互。

1. **AOP\****和设备支持**

##### 由spring-aop、spring-aspects和spring-instrument3个模块组成。

**spring-aop\****是***\*Spring\****的另一个核心模块，是***\*AOP\****主要的实现模块。**作为继OOP后，对程序员影响最大 的编程思想之一，AOP极大地开拓了人们对于编程的思路。在Spring中，他是以JVM的动态代理技术为 基础，然后设计出了一系列的AOP横切实现，比如前置通知、返回通知、异常通知等，同时，Pointcut 接口来匹配切入点，可以使用现有的切入点来设计横切面，也可以扩展相关方法根据需求进行切入。

**spring-aspects\****模块集成自***\*AspectJ\****框架，主要是为***\*SpringAOP\****提供多种***\*AOP\****实现方法。**

**spring-instrument\****模块**是基于JAVASE中的"java.lang.instrument"进行设计的，应该算是AOP的一个 支援模块，主要作用是**在***\*JVM\****启用时，生成一个代理类，程序员通过代理类在运行时修改类的字节，从 而改变一个类的功能，实现***\*AOP\****的功能。**在分类里，我把他分在了AOP模块下，在Spring官方文档里对这个地方也有点含糊不清，这里是纯个人观点。

## 3. 数据访问及集成

##### 由spring-jdbc、spring-tx、spring-orm、spring-jms和spring-oxm5个模块组成。

**spring-jdbc\****模块**是Spring提供的JDBC抽象框架的主要实现模块，用于简化SpringJDBC。主要是提供JDBC模板方式、关系数据库对象化方式、SimpleJdbc方式、事务管理来简化JDBC编程，主要实现类是JdbcTemplate、SimpleJdbcTemplate以及NamedParameterJdbcTemplate。

**spring-tx\****模块**是SpringJDBC事务控制实现模块。使用Spring框架，它对事务做了很好的封装，通过它的AOP配置，可以灵活的配置在任何一层；但是在很多的需求和应用，直接使用JDBC事务控制还是有其优势的。其实，事务是以业务逻辑为基础的；一个完整的业务应该对应业务层里的一个方法；如果业务 操作失败，则整个事务回滚；所以，事务控制是绝对应该放在业务层的；但是，持久层的设计则应该遵 循一个很重要的原则：保证操作的原子性，即持久层里的每个方法都应该是不可以分割的。所以，在使 用SpringJDBC事务控制时，应该注意其特殊性。

**spring-orm\****模块是***\*ORM\****框架支持模块**，主要集成Hibernate,JavaPersistenceAPI( JPA)和

JavaDataObjects( JDO)用于资源管理、数据访问对象(DAO)的实现和事务策略。

spring-jms模块（JavaMessagingService）能够发送和接受信息，自SpringFramework4.1以后，他还 提供了对spring-messaging模块的支撑。

spring-oxm模块主要提供一个抽象层以支撑OXM（OXM是Object-to-XML-Mapping的缩写，它是一个O/M-mapper，将java对象映射成XML数据，或者将XML数据映射成java对象），例如： JAXB,Castor,XMLBeans,JiBX和XStream等。

1. **Web**

**由\****spring-web***\*、\****spring-webmvc***\*、\****spring-websocket***\*和\****spring-webﬂux4***\*个模块组成。**

**spring-web\****模块为***\*Spring\****提供了最基础***\*Web\****支持**，主要建立于核心容器之上，通过Servlet或者

Listeners来初始化IOC容器，也包含一些与Web相关的支持。

**spring-webmvc\****模块众所周知是一个的***\*Web-Servlet\****模块，实现了***\*SpringMVC\****（***\*model-view-**

**Controller\****）的***\*Web\****应用。**

spring-websocket模块主要是与Web前端的全双工通讯的协议。（资料缺乏，这是个人理解）

spring-webﬂux是一个新的非堵塞函数式ReactiveWeb框架，可以用来建立异步的，非阻塞，事件驱动 的服务，并且扩展性非常好。

1. **报文发送**

**即\****spring-messaging***\*模块。**

spring-messaging是从Spring4开始新加入的一个模块，主要职责是为Spring框架集成一些基础的报文 传送应用。

1. **Test**

**即\****spring-test***\*模块。**

spring-test模块主要为测试提供支持的，毕竟在不需要发布（程序）到你的应用服务器或者连接到其他企业设施的情况下能够执行一些集成测试或者其他测试对于任何企业都是非常重要的。

**源码阅读篇**

**Spring\****重要接口详解***\*BeanFactory\****继承体系***\*体系结构图**

这是BeanFactory基本的类体系结构，这里没有包括强大的ApplicationContext体系，

ApplicationContext单独搞一个。

**四级接口继承体系：**

1. BeanFactory 作为一个主接口不继承任何接口，暂且称为**一级接口**。
2. AutowireCapableBeanFactory、HierarchicalBeanFactory、ListableBeanFactory 3个子接口继承了它，进行功能上的增强。这3个子接口称为**二级接口**。
   1. ConfigurableBeanFactory 可以被称为**三级接口**，对二级接口HierarchicalBeanFactory 进行

了再次增强，它还继承了另一个外来的接口SingletonBeanRegistry

1. ConfigurableListableBeanFactory 是一个更强大的接口，继承了上述的所有接口，无所不包，称为**四级接口**。

**总结：**

|-- BeanFactory 是Spring bean容器的**根接口**.

AutowireCapableBeanFactory

|-- --

|-- --

|-- -- --

|-- -- -- --

例.

提供工厂的装配功能。提供父容器的访问功能

如名,提供factory的配置功能,眼花缭乱好多api

集大成者,提供解析,修改bean定义,并初始化单

|-- -- 提供容器内bean实例的枚举功能.这边不会考虑父容器内的实例.

看到这边,我们是不是想起了设计模式原则里的**接口隔离原则**。

##### 下面是继承关系的2个抽象类和2个实现类：

1. AbstractBeanFactory 作为一个抽象类，实现了三级接口ConfigurableBeanFactory 大部分功能。
   1. AbstractAutowireCapableBeanFactory 同样是抽象类，继承自AbstractBeanFactory ，并额

外实现了二级接口AutowireCapableBeanFactory 。

1. DefaultListableBeanFactory 继承自AbstractAutowireCapableBeanFactory ，实现了最强大的四级接口ConfigurableListableBeanFactory ，并实现了一个外来接口BeanDefinitionRegistry ，它并非抽象类。
   1. 最后是最强大的XmlBeanFactory ，继承自DefaultListableBeanFactory ，重写了一些功能， 使自己更强大。

##### 那为何要定义这么多层次的接口呢？

查阅这些接口的源码和说明发现，每个接口都有他使用的场合，它主要是为了区分在Spring内部在操作过程中对象的传递和转化过程中，对对象的数据访问所做的限制。例如ListableBeanFactory接口表示 这些Bean是可列表的，而HierarchicalBeanFactory表示的是这些Bean是有继承关系的，也就是每个Bean有可能有父Bean。AutowireCapableBeanFactory接口定义Bean的自动装配规则。这四个接口共 同定义了Bean的集合、Bean之间的关系、以及Bean行为.

**总结：**

BeanFactory 的类体系结构看似繁杂混乱，实际上由上而下井井有条，非常容易理解。

**BeanFactory**

1 package org.springframework.beans.factory; 2

3 public interface BeanFactory { 4

5 //用来引用一个实例，或把它和工厂产生的Bean区分开

6 //就是说，如果一个FactoryBean的名字为a，那么，&a会得到那个Factory

- String FACTORY_BEAN_PREFIX = "&"; 8

9 /*

10 * 四个不同形式的getBean方法，获取实例

11 */

12 Object getBean(String name) throws BeansException;

- T getBean(String name, Class requiredType) throws BeansException;

14 T getBean(Class requiredType) throws BeansException;

15 Object getBean(String name, Object... args) throws BeansException;

16 // 是否存在

17 boolean containsBean(String name);

18 // 是否为单实例

19 boolean isSingleton(String name) throws NoSuchBeanDefinitionException;

20 // 是否为原型（多实例）

21

22

23

24

25

26

27

28

29

30 }

boolean isPrototype(String name) throws NoSuchBeanDefinitionException;

// 名称、类型是否匹配

boolean isTypeMatch(String name, Class<?> targetType) throws NoSuchBeanDefinitionException;

// 获取类型

Class<?> getType(String name) throws NoSuchBeanDefinitionException;

// 根据实例的名字获取实例的别名

String[] getAliases(String name);

在BeanFactory里只对IOC容器的基本行为作了定义，根本不关心你的Bean是如何定义怎样加载的。正 如我们只关心工厂里得到什么的产品对象，至于工厂是怎么生产这些对象的，这个基本的接口不关心。

**源码说明：**

4个获取实例的方法。getBean的重载方法。

4个判断的方法。判断是否存在，是否为单例、原型，名称类型是否匹配。

1个获取类型的方法、一个获取别名的方法。根据名称获取类型、根据名称获取别名。一目了 然！

**总结：**

这10个方法，很明显，这是一个典型的工厂模式的工厂接口。

**ListableBeanFactory**

**可将\****Bean***\*逐一列出的工厂**

- public interface ListableBeanFactory extends BeanFactory {

2 // 对于给定的名字是否含有

3 boolean containsBeanDefinition(String beanName); BeanDefinition

4 // 返回工厂的BeanDefinition总数

5 int getBeanDefinitionCount();

6 // 返回工厂中所有Bean的名字

7 String[] getBeanDefinitionNames();

8 // 返回对于指定类型Bean（包括子类）的所有名字

- String[] getBeanNamesForType(Class<?> type); 10

11 /*

- \* 返回指定类型的名字
- \* includeNonSingletons为false表示只取单例Bean，true则不是
- \* allowEagerInit为true表示立刻加载，false表示延迟加载。
- \* 注意：FactoryBeans都是立刻加载的。

16 */

- String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons,
  - boolean allowEagerInit);

19 // 根据类型（包括子类）返回指定Bean名和Bean的Map

20 Map<String, T> getBeansOfType(Class type) throws BeansException;

21 Map<String, T> getBeansOfType(Class type,

- boolean includeNonSingletons, boolean allowEagerInit)
- throws BeansException; 24

25 // 根据注解类型，查找所有有这个注解的Bean名和Bean的Map

26 Map<String, Object> getBeansWithAnnotation(

- Class<? extends Annotation> annotationType) throws BeansException;

28

29 // 根据指定Bean名和注解类型查找指定的Bean

30 A findAnnotationOnBean(String beanName,

- Class annotationType); 32

33 }

**源码说明：**

3个跟BeanDeﬁnition有关的总体操作。包括BeanDeﬁnition的总数、名字的集合、指定类型 的名字的集合。

2个getBeanNamesForType重载方法。根据指定类型（包括子类）获取其对应的所有Bean

名字。

2个getBeansOfType重载方法。根据类型（包括子类）返回指定Bean名和Bean的Map。

2个跟注解查找有关的方法。根据注解类型，查找Bean名和Bean的Map。以及根据指定Bean名和注解类型查找指定的Bean。

**总结：**

正如这个工厂接口的名字所示，这个工厂接口最大的特点就是可以列出工厂可以生产的所有实例。当 然，工厂并没有直接提供返回所有实例的方法，也没这个必要。它可以返回指定类型的所有的实例。而 且你可以通过getBeanDeﬁnitionNames()得到工厂所有bean的名字，然后根据这些名字得到所有的 Bean。这个工厂接口扩展了BeanFactory的功能，作为上文指出的BeanFactory二级接口，有9个独有 的方法，扩展了跟BeanDeﬁnition的功能，提供了BeanDeﬁnition、BeanName、注解有关的各种操 作。**它可以根据条件返回\****Bean***\*的信息集合，这就是它名字的由来\****——ListableBeanFactory***\*。**

**HierarchicalBeanFactory**

**分层的\****Bean***\*工厂**

- public interface HierarchicalBeanFactory extends BeanFactory {
  - // 返回本Bean工厂的父工厂
  - BeanFactory getParentBeanFactory();
  - // 本地工厂是否包含这个Bean
- boolean containsLocalBean(String name); 6 }

##### 参数说明：

第一个方法返回本Bean工厂的父工厂。这个方法实现了工厂的分层。

第二个方法判断本地工厂是否包含这个Bean（忽略其他所有父工厂）。这也是分层思想的体现。

**总结：**

这个工厂接口非常简单，实现了Bean工厂的分层。这个工厂接口也是继承自BeanFacotory，也是 一个二级接口，相对于父接口，它只扩展了一个重要的功能——**工厂分层**。

**AutowireCapableBeanFactory**

**自动装配的\****Bean***\*工厂**

- public interface AutowireCapableBeanFactory extends BeanFactory {

2 // 这个常量表明工厂没有自动装配的Bean

3 int AUTOWIRE_NO = 0;

4 // 表明根据名称自动装配

5 int AUTOWIRE_BY_NAME = 1;

6 // 表明根据类型自动装配

7 int AUTOWIRE_BY_TYPE = 2;

8 // 表明根据构造方法快速装配

9 int AUTOWIRE_CONSTRUCTOR = 3;

10 //表明通过Bean的class的内部来自动装配（有没翻译错...）Spring3.0被弃用。

11 @Deprecated

12 int AUTOWIRE_AUTODETECT = 4;

13 // 根据指定Class创建一个全新的Bean实例

14 T createBean(Class beanClass) throws BeansException;

15 // 给定对象，根据注释、后处理器等，进行自动装配

- void autowireBean(Object existingBean) throws BeansException; 17

18 // 根据Bean名的BeanDefinition装配这个未加工的Object，执行回调和各种后处理器。

- Object configureBean(Object existingBean, String beanName) throws BeansException;

20

21 // 分解Bean在工厂中定义的这个指定的依赖descriptor

- Object resolveDependency(DependencyDescriptor descriptor, String beanName) throws BeansException;

23

24 // 根据给定的类型和指定的装配策略，创建一个新的Bean实例

- Object createBean(Class<?> beanClass, int autowireMode, boolean dependencyCheck) throws BeansException;

26

27 // 与上面类似，不过稍有不同。

- Object autowire(Class<?> beanClass, int autowireMode, boolean dependencyCheck) throws BeansException;

29

30 /*

31 * 根据名称或类型自动装配

32 */

- void autowireBeanProperties(Object existingBean, int autowireMode, boolean dependencyCheck)
- throws BeansException; 35

36 /*

37 * 也是自动装配

38 */

39 void applyBeanPropertyValues(Object existingBean, String beanName) throws BeansException;

40

41 /*

42 * 初始化一个Bean...

43 */

44 Object initializeBean(Object existingBean, String beanName) throws BeansException;

45

46 /*

47 * 初始化之前执行BeanPostProcessors 48 */

- Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
- throws BeansException; 51 /*

52 * 初始化之后执行BeanPostProcessors

53 */

- Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
  - throws BeansException; 56

57 /*

58 * 分解指定的依赖

59 */

- Object resolveDependency(DependencyDescriptor descriptor, String beanName,
- Set autowiredBeanNames, TypeConverter typeConverter) throws BeansException;

62

63 }

##### 源码说明：

1. 总共5个静态不可变常量来指明装配策略，其中一个常量被0废弃、一个常量表示没有自动装配，另外3个常量指明不同的装配策略——根据名称、根据类型、根据构造方法。
2. 8个跟自动装配有关的方法，实在是繁杂，具体的意义我们研究类的时候再分辨吧。
3. 2个执行BeanPostProcessors的方法。
4. 2个分解指定依赖的方法

**总结：**

**ConﬁgurableBeanFactory**

**复杂的配置\****Bean***\*工厂**

1 public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

2

3 String SCOPE_SINGLETON = "singleton"; // 单例

4

5 String SCOPE_PROTOTYPE = "prototype"; // 原型

6

7 /*

8 * 搭配HierarchicalBeanFactory接口的getParentBeanFactory方法

9 */

10 void setParentBeanFactory(BeanFactory parentBeanFactory) throws IllegalStateException;

11

12 /*

13 * 设置、返回工厂的类加载器

14 */

15 void setBeanClassLoader(ClassLoader beanClassLoader); 16

17 ClassLoader getBeanClassLoader(); 18

19 /*

20 * 设置、返回一个临时的类加载器

21 */

22 void setTempClassLoader(ClassLoader tempClassLoader); 23

24 ClassLoader getTempClassLoader(); 25

26 /*

27 * 设置、是否缓存元数据，如果false，那么每次请求实例，都会从类加载器重新加载（热加载）

28

29 */

30 void setCacheBeanMetadata(boolean cacheBeanMetadata); 31

32 boolean isCacheBeanMetadata();//是否缓存元数据

33

34 /*

35 * Bean表达式分解器

36 */

37 void setBeanExpressionResolver(BeanExpressionResolver resolver); 38

39 BeanExpressionResolver getBeanExpressionResolver(); 40

41 /*

42 * 设置、返回一个转换服务

43 */

44 void setConversionService(ConversionService conversionService); 45

46 ConversionService getConversionService(); 47

48 /*

49 * 设置属性编辑登记员...

50 */

51 void addPropertyEditorRegistrar(PropertyEditorRegistrar registrar); 52

53 /*

54 * 注册常用属性编辑器

55 */

56 void registerCustomEditor(Class<?> requiredType, Class<? extends

PropertyEditor> propertyEditorClass);

57

58 /*

59 * 用工厂中注册的通用的编辑器初始化指定的属性编辑注册器

60 */

61 void copyRegisteredEditorsTo(PropertyEditorRegistry registry); 62

63 /*

64 * 设置、得到一个类型转换器

65 */

66 void setTypeConverter(TypeConverter typeConverter); 67

68 TypeConverter getTypeConverter(); 69

70 /*

71 * 增加一个嵌入式的StringValueResolver 72 */

73 void addEmbeddedValueResolver(StringValueResolver valueResolver); 74

75 String resolveEmbeddedValue(String value);//分解指定的嵌入式的值

76

77 void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);//设置一个Bean后处理器

78

79 int getBeanPostProcessorCount();//返回Bean后处理器的数量80

81 void registerScope(String scopeName, Scope scope);//注册范围82

83 String[] getRegisteredScopeNames();//返回注册的范围名84

85 Scope getRegisteredScope(String scopeName);//返回指定的范围86

87 AccessControlContext getAccessControlContext();//返回本工厂的一个安全访问上下文

88

89 void copyConfigurationFrom(ConfigurableBeanFactory otherFactory);//从其他的工厂复制相关的所有配置

90

91 /*

92 * 给指定的Bean注册别名

93 */

94 void registerAlias(String beanName, String alias) throws BeanDefinitionStoreException;

95

96 void resolveAliases(StringValueResolver valueResolver);//根据指定的StringValueResolver移除所有的别名

97

98 /*

99 * 返回指定Bean合并后的Bean定义

100 */

101 BeanDefinition getMergedBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

102

103 boolean isFactoryBean(String name) throws NoSuchBeanDefinitionException;//判断指定Bean是否为一个工厂Bean

104

105 void setCurrentlyInCreation(String beanName, boolean inCreation);//设置一个Bean是否正在创建

106

107 boolean isCurrentlyInCreation(String beanName);//返回指定Bean是否已经成功创建

108

109 void registerDependentBean(String beanName, String dependentBeanName);//注册一个依赖于指定bean的Bean

110

111 String[] getDependentBeans(String beanName);//返回依赖于指定Bean的所欲Bean

名

112

113 String[] getDependenciesForBean(String beanName);//返回指定Bean依赖的所有Bean名

114

115 void destroyBean(String beanName, Object beanInstance);//销毁指定的Bean 116

117 void destroyScopedBean(String beanName);//销毁指定的范围Bean 118

/*

\* 判断指定的Bean是否有资格作为自动装配的候选者

*/

boolean isAutowireCandidate(String beanName, DependencyDescriptor descriptor) throws NoSuchBeanDefinitionException;

#### ConﬁgurableListableBeanFactory

3

4

5

6

7

8

9

10

11

##### BeanFactory的集大成者

}

// 返回注册的Bean定义

BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

// 暂时冻结所有的Bean配置

void freezeConfiguration();

// 判断本工厂配置是否被冻结

boolean isConfigurationFrozen();

// 使所有的非延迟加载的单例类都实例化。

void preInstantiateSingletons() throws BeansException;

17

18

19

20

21

22

23

24

25

26

27

**源码说明：**

1、2个忽略自动装配的的方法。

2、1个注册一个可分解依赖的方法。

3、1个判断指定的Bean是否有资格作为自动装配的候选者的方法。

4、1个根据指定bean名，返回注册的Bean定义的方法。

5、2个冻结所有的Bean配置相关的方法。

6、1个使所有的非延迟加载的单例类都实例化的方法。

**总结：**

/*

\* 查找，指定的Bean名是否包含Bean定义

*/

boolean containsBeanDefinition(String beanName);

工厂接口ConfigurableListableBeanFactory 同时继承了3个接口， ListableBeanFactory 、

和 ConfigurableBeanFactory ，扩展之后，加上自有的这8个方法，这个工厂接口总共有83个方法，实在是巨大到不行了。这个工厂接口的自有方法总体上只 是对父类接口功能的补充，包含了BeanFactory 体系目前的所有方法，可以说是接口的集大成者。

#### BeanDeﬁnitionRegistry

##### 额外的接口,这个接口基本用来操作定义在工厂内部的BeanDeﬁnition的。

}

String[] getBeanDefinitionNames();//返回本容器内所有注册的Bean定义名称

int getBeanDefinitionCount();//返回本容器内注册的Bean定义数目boolean isBeanNameInUse(String beanName);//指定Bean名是否被注册过。

14

15

16

17

18

19

20

21

22

23

24

25

26

**BeanDeﬁnition\****继承体系**

**体系结构图**

SpringIOC容器管理了我们定义的各种Bean对象及其相互的关系，Bean对象在Spring实现中是以BeanDeﬁnition来描述的，其继承体系如下：

**ApplicationContext\****继承体系**

**体系结构图**

ApplicationContext允许上下文嵌套，通过保持父上下文可以维持一个上下文体系。对于Bean的查找可 以在这个上下文体系中发生，首先检查当前上下文，其次是父上下文，逐级向上，这样为不同的Spring 应用提供了一个共享的Bean定义环境。

**容器初始化流程源码分析**

**主流程源码分析**

**找入口**

java程序入口

web程序入口

- \* contextConfigLocation * classpath:spring.xml
- 
- \* * springframework.web.context.ContextLoaderListener *
- 

**注意**：不管上面哪种方式，最终都会调AbstractApplicationContext的refresh方法 ，而这个方法才是我们真正的入口。

#### 流程解析

**AbstractApplicationContext**的 refresh 方法

- public void refresh() throws BeansException, IllegalStateException {
- synchronized (this.startupShutdownMonitor) {
  - // Prepare this context for
    - // STEP 1： 刷新预处理
      - prepareRefresh(); 6

7 // Tell the subclass to refresh the internal bean factory. 8 // STEP 2：

- // a） 创建IoC容器（DefaultListableBeanFactory）
- // b） 加载解析XML文件（最终存储到Document对象中）
- // c） 读取Document对象，并完成BeanDefinition的加载和注册工作
- ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

13

- // Prepare the bean factory for use in this
  - // STEP 3： 对IoC容器进行一些预处理（设置一些公共属性）

16 prepareBeanFactory(beanFactory); 17

- try {
- // Allows post-processing of the bean factory in context subclasses.
  - // STEP 4：
    - postProcessBeanFactory(beanFactory); 22
  - // Invoke factory processors registered as beans in the
- // STEP 5： 调用BeanFactoryPostProcessor后置处理器对

BeanDefinition处理

25 invokeBeanFactoryPostProcessors(beanFactory); 26

- // Register bean processors that intercept bean
- // STEP 6： 注册BeanPostProcessor后置处理器
  - registerBeanPostProcessors(beanFactory); 30
- // Initialize message source for this
- // STEP 7： 初始化一些消息源（比如处理国际化的i18n等消息源）
- initMessageSource(); 34
  - // Initialize event multicaster for this
  - // STEP 8： 初始化应用事件广播器
    - initApplicationEventMulticaster(); 38
    - // Initialize other special beans in specific context
  - // STEP 9： 初始化一些特殊的bean
  - onRefresh();

42

- // Check for listener beans and register
- // STEP 10： 注册一些监听器
- registerListeners(); 46
  - // Instantiate all remaining (non-lazy-init)
  - // STEP 11： 实例化剩余的单例bean（非懒加载方式）

49

50

51

52

53

54

55 }

56

// 注意事项：Bean的IoC、DI和AOP都是发生在此步骤

finishBeanFactoryInitialization(beanFactory);

// Last step: publish corresponding event.

// STEP 12： 完成刷新时，需要发布对应的事件

finishRefresh();

- catch (BeansException ex) {
  - if (logger.isWarnEnabled()) {
    - logger.warn("Exception encountered during context initialization - " +
      - "cancelling refresh attempt: " + ex); 61 }

62

- // Destroy already created singletons to avoid dangling resources.
- destroyBeans();

65

- // Reset 'active'
- cancelRefresh(ex); 68
  - // Propagate exception to
  - throw ex;

71 }

72

- finally {
  - // Reset common introspection caches in Spring's core, since we
    - // might not ever need metadata for singleton beans ..
  - resetCommonCaches(); 77 }

78 }

79 }

**创建\****BeanFactory***\*流程源码分析**

#### 找入口

**AbstractApplicationContext**类的 refresh 方法：

1 // Tell the subclass to refresh the internal bean factory. 2 // STEP 2：

- // a） 创建IoC容器（DefaultListableBeanFactory）
- // b） 加载解析XML文件（最终存储到Document对象中）
- // c） 读取Document对象，并完成BeanDefinition的加载和注册工作
- ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

#### 流程解析

进入**AbstractApplication**的 obtainFreshBeanFactory 方法：

用于创建一个新的IoC容器 ，这个IoC容器 就是**DefaultListableBeanFactory**对象。

- protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
  - // 主要是通过该方法完成IoC容器的刷新
  - refreshBeanFactory();
  - ConfigurableListableBeanFactory beanFactory = getBeanFactory();
  - if (logger.isDebugEnabled()) {
    - logger.debug("Bean factory for " + getDisplayName() + ": " + beanFactory);

7 }

8 return beanFactory; 9 }

进入**AbstractRefreshableApplicationContext**的 refreshBeanFactory 方法：

销毁以前的容器创建新的IoC容器

加载BeanDefinition 对象注册到IoC容器中

- protected final void refreshBeanFactory() throws BeansException {
- // 如果之前有IoC容器，则销毁
- if (hasBeanFactory()) {
  - destroyBeans();
    - closeBeanFactory(); 6 }
  - try {
    - // 创建IoC容器，也就是DefaultListableBeanFactory
  - DefaultListableBeanFactory beanFactory = createBeanFactory();
  - beanFactory.setSerializationId(getId());
  - customizeBeanFactory(beanFactory);
    - // 加载BeanDefinition对象，并注册到IoC容器中（重点）
  - loadBeanDefinitions(beanFactory);
  - synchronized (this.beanFactoryMonitor) {
    - this.beanFactory = beanFactory; 16 }

17 }

- catch (IOException ex) {
  - throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);

20 }

21 }

进入**AbstractRefreshableApplicationContext**的 createBeanFactory 方法

### 加载BeanDeﬁnition流程分析

#### 找入口

**AbstractRefreshableApplicationContext**类的 refreshBeanFactory 方法中第13行代码：

- protected final void refreshBeanFactory() throws BeansException {
- // 如果之前有IoC容器，则销毁
- if (hasBeanFactory()) {
  - destroyBeans();
    - closeBeanFactory(); 6 }
  - try {
    - // 创建IoC容器，也就是DefaultListableBeanFactory
  - DefaultListableBeanFactory beanFactory = createBeanFactory();
  - beanFactory.setSerializationId(getId());
  - customizeBeanFactory(beanFactory);
    - // 加载BeanDefinition对象，并注册到IoC容器中（重点）
  - loadBeanDefinitions(beanFactory);
  - synchronized (this.beanFactoryMonitor) {
    - this.beanFactory = beanFactory; 16 }

17 }

- catch (IOException ex) {
  - throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);

20 }

21 }

#### 流程图

loadBeanDefinitions

AbstractRefreshableApplicationContext

AbstractXmlApplicationContext

loadBeanDefinitions

AbstractBeanDefinitionReader

XmlBeanDefinitionReader

DefaultBeanDefinitionDocumentReader

BeanDefinitionParserDelegate

BeanDefinition解析的核心类

AbstractRefreshableApplicationContext

AbstractXmlApplicationContext

AbstractBeanDefinitionReader

XmlBeanDefinitionReader

DefaultBeanDefinitionDocumentReader

BeanDefinitionParserDelegate

BeanDeﬁnition流程

- |——AbstractRefreshableApplicationContext#refreshBeanFactory

2 |--AbstractXmlApplicationContext#loadBeanDefinitions：走了多个重载方法

- |--AbstractBeanDefinitionReader#loadBeanDefinitions：走了多个重载方法
  - |--XmlBeanDefinitionReader#loadBeanDefinitions：走了多个重载方法
    - |--XmlBeanDefinitionReader#doLoadBeanDefinitions
  - |--XmlBeanDefinitionReader#registerBeanDefinitions 7 |--

DefaultBeanDefinitionDocumentReader#registerBeanDefinitions

- |--#doRegisterBeanDefinitions
  - |--#parseBeanDefinitions
    - |--#parseDefaultElement
  - |--#processBeanDefinition

12 |--

BeanDefinitionParserDelegate#parseBeanDefinitionElement 13 |--

\#parseBeanDefinitionElement

14

#### 流程相关类的说明

##### AbstractRefreshableApplicationContext

主要用来对**BeanFactory**提供refresh 功能。包括**BeanFactory**的创建和BeanDefinition 的定义、解析、注册操作。

##### AbstractXmlApplicationContext

主要提供对于XML资源 的加载功能。包括从Resource资源对象和资源路径中加载XML文件。

**AbstractBeanDeﬁnitionReader**

主要提供对于BeanDefinition 对象的读取功能。具体读取工作交给子类实现。

##### XmlBeanDeﬁnitionReader

主要通过DOM4J 对于XML资源 的读取、解析功能，并提供对于BeanDefinition 的注册功能。

##### DefaultBeanDeﬁnitionDocumentReader BeanDeﬁnitionParserDelegate

**流程解析**

进入**AbstractXmlApplicationContext**的loadBeanDeﬁnitions方法：

创建一个**XmlBeanDeﬁnitionReader**，通过阅读XML文件，真正完成BeanDeﬁnition的加载和注册。

配置**XmlBeanDeﬁnitionReader**并进行初始化。

委托给**XmlBeanDeﬁnitionReader**去加载BeanDeﬁnition。

- protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
- // Create a new XmlBeanDefinitionReader for the given
- // 给指定的工厂创建一个BeanDefinition阅读器
- // 作用：通过阅读XML文件，真正完成BeanDefinition的加载和注册
  - XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

6

- // Configure the bean definition reader with this context's
- // resource loading
- beanDefinitionReader.setEnvironment(this.getEnvironment());
- beanDefinitionReader.setResourceLoader(this);
  - beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this));

12

- // Allow a subclass to provide custom initialization of the reader,
- // then proceed with actually loading the bean
  - initBeanDefinitionReader(beanDefinitionReader); 16
- // 委托给BeanDefinition阅读器去加载BeanDefinition
  - loadBeanDefinitions(beanDefinitionReader); 19 }

20

- protected void loadBeanDefinitions(XmlBeanDefinitionReader reader) throws
  - BeansException, IOException {
- // 获取资源的定位
- // 这里getConfigResources是一个空实现，真正实现是调用子类的获取资源定位的方法
- // 比如：ClassPathXmlApplicationContext中进行了实现
- // 而FileSystemXmlApplicationContext没有使用该方法
- Resource[] configResources = getConfigResources();
- if (configResources != null) {

29

源

30

31 }

// XML Bean读取器调用其父类AbstractBeanDefinitionReader读取定位的资reader.loadBeanDefinitions(configResources);

- // 如果子类中获取的资源定位为空，则获取FileSystemXmlApplicationContext构造方法中setConfigLocations方法设置的资源
- String[] configLocations = getConfigLocations();
- if (configLocations != null) {

35 // XML Bean读取器调用其父类AbstractBeanDefinitionReader读取定位的资源

- reader.loadBeanDefinitions(configLocations); 37 }

38 }

loadBeanDefinitions 方法经过一路的兜兜转转，最终来到了**XmlBeanDeﬁnitionReader**的

doLoadBeanDefinitions 方法：

一个是完成BeanDefinition对象的加载与注册 。

- protected int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
- throws BeanDefinitionStoreException {
- try {
  - // 通过DOM4J加载解析XML文件，最终形成Document对象
- Document doc = doLoadDocument(inputSource, resource);
- // 通过对Document对象的操作，完成BeanDefinition的加载和注册工作
  - return registerBeanDefinitions(doc, resource); 8 }

9 //省略一些catch语句

10 catch (Throwable ex) { 11 ......

12 }

13 }

此处我们暂不处理DOM4J加载解析XML的流程，我们重点分析BeanDeﬁnition的加载注册流程 进入**XmlBeanDeﬁnitionReader**的 registerBeanDefinitions 方法:

创建**DefaultBeanDeﬁnitionDocumentReader**用来解析Document对象。 获得容器中已注册的BeanDeﬁnition数量

委托给**DefaultBeanDeﬁnitionDocumentReader**来完成BeanDeﬁnition的加载、注册工 作。

统计新注册的BeanDeﬁnition数量

- public int registerBeanDefinitions(Document doc, Resource resource) throws
- BeanDefinitionStoreException {
- // 创建DefaultBeanDefinitionDocumentReader用来解析Document对象
- BeanDefinitionDocumentReader documentReader =
  - createBeanDefinitionDocumentReader();
- // 获得容器中注册的Bean数量
- int countBefore = getRegistry().getBeanDefinitionCount();
- //解析过程入口，BeanDefinitionDocumentReader只是个接口
- //具体的实现过程在DefaultBeanDefinitionDocumentReader完成
  - documentReader.registerBeanDefinitions(doc, createReaderContext(resource));
- // 统计注册的Bean数量
  - return getRegistry().getBeanDefinitionCount() - countBefore; 13 }

进入**DefaultBeanDeﬁnitionDocumentReader**的 registerBeanDefinitions 方法：

获得Document的根元素标签

真正实现BeanDeﬁnition解析和注册工作

1 public void registerBeanDefinitions(Document doc, XmlReaderContext readerContext

2 {

- this.readerContext = readerContext;
- logger.debug("Loading bean definitions");
- // 获得Document的根元素标签
- Element root = doc.getDocumentElement();
- // 真正实现BeanDefinition解析和注册工作
  - doRegisterBeanDefinitions(root); 9 }

进入**DefaultBeanDeﬁnitionDocumentReader** doRegisterBeanDefinitions 方法： 这里使用了委托模式，将具体的BeanDeﬁnition解析工作交给了

##### BeanDeﬁnitionParserDelegate去完成

在解析Bean定义之前，进行自定义的解析，增强解析过程的可扩展性

委托给**BeanDeﬁnitionParserDelegate**,从Document的根元素开始进行BeanDeﬁnition的解析

在解析Bean定义之后，进行自定义的解析，增加解析过程的可扩展性

1 protected void doRegisterBeanDefinitions(Element root) {

- // Any nested elements will cause recursion in this In
- // order to propagate and preserve default-* attributes

correctly,

- // keep track of the current (parent) delegate, which may be Create
  - // the new (child) delegate with a reference to the parent for fallback purposes,
    - // then ultimately reset delegate back to its original (parent) reference.
  - // this behavior emulates a stack of delegates without actually necessitating

8

- // 这里使用了委托模式，将具体的BeanDefinition解析工作交给了

BeanDefinitionParserDelegate去完成

- BeanDefinitionParserDelegate parent = this.delegate;
  - this.delegate = createDelegate(getReaderContext(), root, parent); 12
- if (this.delegate.isDefaultNamespace(root)) {
  - String profileSpec = root.getAttribute(PROFILE_ATTRIBUTE);
  - if (StringUtils.hasText(profileSpec)) {

16 String[] specifiedProfiles = StringUtils.tokenizeToStringArray(

- profileSpec,

BeanDefinitionParserDelegate.MULTI_VALUE_ATTRIBUTE_DELIMITERS);

- if

(!getReaderContext().getEnvironment().acceptsProfiles(specifiedProfiles)) {

- if (logger.isInfoEnabled()) {
  - logger.info("Skipped XML bean definition file due to specified profiles [" + profileSpec +
    - "] not matching: " +

getReaderContext().getResource()); 22 }

23 return;

24 }

25 }

26 }

- // 在解析Bean定义之前，进行自定义的解析，增强解析过程的可扩展性
- preProcessXml(root);
- // 委托给BeanDefinitionParserDelegate,从Document的根元素开始进行

BeanDefinition的解析

- parseBeanDefinitions(root, this.delegate);
- // 在解析Bean定义之后，进行自定义的解析，增加解析过程的可扩展性
  - postProcessXml(root); 33

34 this.delegate = parent; 35 }

### Bean实例化流程分析

#### 找入口

**AbstractApplicationContext**类的 refresh 方法：

#### 流程解析

**AOP\****流程源码分析**

**查找\****BeanDeﬁnitionParser***\*流程分析**

**找入口**

DefaultBeanDefinitionDocumentReader#parseBeanDefinitions 方法的第_16_行或者_23_行：

- protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
- // 加载的Document对象是否使用了Spring默认的XML命名空间（beans命名空间）
- if (delegate.isDefaultNamespace(root)) {
  - // 获取Document对象根元素的所有子节点（bean标签、import标签、alias标签和其他自定义标签context、aop等）
- NodeList nl = root.getChildNodes();
- for (int i = 0; i < nl.getLength(); i++) {
  - Node node = nl.item(i);
  - if (node instanceof Element) {
    - Element ele = (Element) node;
    - // bean标签、import标签、alias标签，则使用默认解析规则
    - if (delegate.isDefaultNamespace(ele)) {
  - parseDefaultElement(ele, delegate); 13 }
    - //像context标签、aop标签、tx标签，则使用用户自定义的解析规则解析

元素节点

- else {
- delegate.parseCustomElement(ele); 17 }

18 }

19 }

20 }

- else {
  - // 如果不是默认的命名空间，则使用用户自定义的解析规则解析元素节点

23 delegate.parseCustomElement(root); 24 }

25 }

#### 流程图

parseCustomElement

DefaultBeanDefinitionDocumentReader

resolve

BeanDefinitionParserDelegate

init

DefaultNamespaceHandlerResolver

registerBeanDefinitionParser

AopNamespaceHandler

NamespaceHandlerSupport

return AopNamespaceHandler

findParserForElement

DefaultBeanDefinitionDocumentReader

BeanDefinitionParserDelegate

DefaultNamespaceHandlerResolver

AopNamespaceHandler

NamespaceHandlerSupport

#### 流程相关类的说明流程解析

**执行\****BeanDeﬁnitionParser***\*流程分析**

**找入口**

**NamespaceHandlerSupport**类的 parse 方法第*6*行代码：

- public BeanDefinition parse(Element element, ParserContext parserContext)

{

- // NamespaceHandler里面初始化了大量的BeanDefinitionParser来分别处理不同的自

定义标签

3

4

// 从指定的NamespaceHandler中，匹配到指定的BeanDefinitionParser BeanDefinitionParser parser = findParserForElement(element,

parserContext);

- // 调用指定自定义标签的解析器，完成具体解析工作
- return (parser != null ? parser.parse(element, parserContext) : null);

7 }

ConfigBeanDefinitionParser

NamespaceHandlerSupport

#### 流程图

configureAutoProxyCreator

**流程相关类的说明流程解析**

**产生\****AOP***\*代理流程分析**

**AspectJAwareAdvisorAutoProxyCreator\****的继承体系**

- |-BeanPostProcessor

2 postProcessBeforeInitialization---初始化之前调用

- postProcessAfterInitialization---初始化之后调用 4
  - |--InstantiationAwareBeanPostProcessor

6 postProcessBeforeInstantiation---实例化之前调用

7 postProcessAfterInstantiation---实例化之后调用

- postProcessPropertyValues---后置处理属性值 9
  - |---SmartInstantiationAwareBeanPostProcessor

11 predictBeanType

12 determineCandidateConstructors

- getEarlyBeanReference 14
  - |--- AbstractAutoProxyCreator

16 postProcessBeforeInitialization

17 postProcessAfterInitialization--- AOP功能入口

18 postProcessBeforeInstantiation

19 postProcessAfterInstantiation

- postProcessPropertyValues 21 ...
- |----- AbstractAdvisorAutoProxyCreator

23 getAdvicesAndAdvisorsForBean

24 findEligibleAdvisors

25 findCandidateAdvisors

- findAdvisorsThatCanApply 27
  - |------ AspectJAwareAdvisorAutoProxyCreator

29 extendAdvisors

30 sortAdvisors

#### 找入口

**AbstractAutoProxyCreator**类的 postProcessAfterInitialization 方法第*6*行代码：

#### 流程图

**事务流程源码分析**

**获取\****TransactionInterceptor***\*的\****BeanDeﬁnition**

**找入口**

AbstractBeanDefinitionParser#parse 方法：

- public final BeanDefinition parse(Element element, ParserContext parserContext) {
- // 调用子类的parseInternal获取BeanDefinition对象
  - AbstractBeanDefinition definition = parseInternal(element, parserContext);

4

- if (definition != null && !parserContext.isNested()) {
  - try {
    - String id = resolveId(element, definition, parserContext);
    - if (!StringUtils.hasText(id)) {
      - parserContext.getReaderContext().error(
        - "Id is required for element '" + parserContext.getDelegate().getLocalName(element)

11 + "' when used as a top-level tag",

element);

12 }

- String[] aliases = null;
- if (shouldParseNameAsAliases()) {
  - String name = element.getAttribute(NAME_ATTRIBUTE);
  - if (StringUtils.hasLength(name)) {
- aliases =

StringUtils.trimArrayElements(StringUtils.commaDelimitedListToStringArray(n ame));

18 }

19 }

- BeanDefinitionHolder holder = new BeanDefinitionHolder(definition, id, aliases);
- // 将处理tx:advice标签的类BeanDefinition对象，注册到IoC容器中

22 registerBeanDefinition(holder, parserContext.getRegistry());

- if (shouldFireEvents()) {
  - BeanComponentDefinition componentDefinition = new BeanComponentDefinition(holder);
- postProcessComponentDefinition(componentDefinition);
  - parserContext.registerComponent(componentDefinition); 27 }

28 }

- catch (BeanDefinitionStoreException ex) {
  - String msg = ex.getMessage();
    - parserContext.getReaderContext().error((msg != null ? msg : ex.toString()), element);

32

return null;

33

}

34

}

35

return definition;

36

}

doParse

TransactionInterceptor.class

getBeanClass

parseInternal

parse

TxAdviceBeanDefinitionParser

AbstractSingleBeanDefinitionParser

#### 流程图

**流程解析**

**执行\****TransactionInterceptor***\*流程分析**

**找入口**

**TransactionInterceptor**类实现了**MethodInterceptor**接口，所以入口方法是 invoke 方法：

1 public Object invoke(final MethodInvocation invocation) throws Throwable { 2

3 Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);

4

- // 调用TransactionAspectSupport类的invokeWithinTransaction方法去实现事务支持
- return invokeWithinTransaction(invocation.getMethod(), targetClass, invocation::proceed);

7 }

#### 流程图

**源码分析\****--***\*参考**

- **Spring\****核心容器体系结构**
- **(1)BeanFactory**

SpringBean的创建是典型的工厂模式，这一系列的Bean工厂，也即IOC容器为开发者管理对象间的依 赖关系提供了很多便利和基础服务，在Spring中有许多的IOC容器的实现供用户选择和使用，其相互关系如下：

其中BeanFactory作为最顶层的一个接口类，它定义了IOC容器的基本功能规范，BeanFactory有三个子 类：ListableBeanFactory、HierarchicalBeanFactory和AutowireCapableBeanFactory。但是从上图中我们可以发现最终的默认实现类是DefaultListableBeanFactory，他实现了所有的接口。那为何要定 义这么多层次的接口呢？查阅这些接口的源码和说明发现，每个接口都有他使用的场合，它主要是为了 区分在Spring内部在操作过程中对象的传递和转化过程中，对对象的数据访问所做的限制。例如ListableBeanFactory接口表示这些Bean是可列表的，而HierarchicalBeanFactory表示的是这些Bean 是有继承关系的，也就是每个Bean有可能有父Bean。AutowireCapableBeanFactory接口定义Bean的自动装配规则。这四个接口共同定义了Bean的集合、Bean之间的关系、以及Bean行为.

最基本的IOC容器接口BeanFactory

在BeanFactory里只对IOC容器的基本行为作了定义，根本不关心你的Bean是如何定义怎样加载的。正 如我们只关心工厂里得到什么的产品对象，至于工厂是怎么生产这些对象的，这个基本的接口不关心。

而要知道工厂是如何产生对象的，我们需要看具体的IOC容器实现，Spring提供了许多IOC容器的实

现。比如XmlBeanFactory，ClasspathXmlApplicationContext等。其中XmlBeanFactory就是针对最基本的IOC容器的实现，这个IOC容器可以读取XML文件定义的BeanDeﬁnition（XML文件中对bean的描 述）,如果说XmlBeanFactory是容器中的屌丝，ApplicationContext应该算容器中的高帅富.

ApplicationContext是Spring提供的一个高级的IOC容器，它除了能够提供IOC容器的基本功能外，还为用户提供了以下的附加服务。

从ApplicationContext接口的实现，我们看出其特点：

1. 支持信息源，可以实现国际化。（实现MessageSource接口）
2. 访问资源。(实现ResourcePatternResolver接口，后面章节会讲到) 支持应用事件。(实现ApplicationEventPublisher接口)

- **(2)BeanDeﬁnition**

SpringIOC容器管理了我们定义的各种Bean对象及其相互的关系，Bean对象在Spring实现中是以BeanDeﬁnition来描述的，其继承体系如下：

Bean的解析过程非常复杂，功能被分的很细，因为这里需要被扩展的地方很多，必须保证有足够的灵活性，以应对可能的变化。Bean的解析主要就是对Spring配置文件的解析。这个解析过程主要通过下图中的类完成：

- **IOC\****容器的初始化***\*IOC\****容器的初始化包括***\*BeanDeﬁnition\****的***\*Resource\****定位、载入和注册这三个基***\*本的过程。**

我们以ApplicationContext为例讲解，ApplicationContext系列容器也许是我们最熟悉的，因为Web项 目中使用的XmlWebApplicationContext就属于这个继承体系，还有ClasspathXmlApplicationContext 等，其继承体系如下图所示：

ApplicationContext允许上下文嵌套，通过保持父上下文可以维持一个上下文体系。对于Bean的查找可 以在这个上下文体系中发生，首先检查当前上下文，其次是父上下文，逐级向上，这样为不同的Spring 应用提供了一个共享的Bean定义环境。

下面我们分别简单地演示一下两种IOC容器的创建过程

- **1\****、***\*XmlBeanFactory(\****屌丝***\*IOC)\****的整个流程**

通过XmlBeanFactory的源码，我们可以发现:

参照源码，自己演示一遍，理解定位、载入、注册的全过程

通过前面的源码，XmlBeanDeﬁnitionReader reader=new XmlBeanDeﬁnitionReader(this);中其中this传的是factory对象

- 2**

  、

  **FileSystemXmlApplicationContext**

  的

  **IOC**

  容器流程**

  - 、高富帅版IOC解剖

其实际调用的构造函数为：

- 、设置资源加载器和资源定位

通过分析FileSystemXmlApplicationContext的源代码可以知道，在创建

FileSystemXmlApplicationContext容器时，构造方法做以下两项重要工作：

首先，调用父类容器的构造方法(super(parent)方法)为容器设置好Bean资源加载器。 然后，再调用父类AbstractRefreshableConﬁgApplicationContext的

setConﬁgLocations(conﬁgLocations)方法设置Bean定义资源文件的定位路径。

通过追踪FileSystemXmlApplicationContext的继承体系，发现其父类的父类

AbstractApplicationContext中初始化IOC容器所做的主要源码如下：

AbstractApplicationContext构造方法中调用PathMatchingResourcePatternResolver的构造方法创建Spring资源加载器：

在设置容器的资源加载器之后，接下来FileSystemXmlApplicationContext执行setConﬁgLocations方 法通过调用其父类AbstractRefreshableConﬁgApplicationContext的方法进行对Bean定义资源文件的 定位，该方法的源码如下：

通过这两个方法的源码我们可以看出，我们既可以使用一个字符串来配置多个SpringBean定义资源文件，也可以使用字符串数组，即下面两种方式都是可以的：

ClasspathResourceres=newClasspathResource(“a.xml,b.xml,...... ”);多个资源文件路径之间可以是

用”,;\t\n”等分隔。

B.ClasspathResourceres=newClasspathResource(newString[]{“a.xml”,”b.xml”,..... });至此，

SpringIOC容器在初始化时将配置的Bean定义资源文件定位为Spring封装的Resource。

- 、AbstractApplicationContext的refresh函数载入Bean定义过程：

SpringIOC容器对Bean定义资源的载入是从refresh()函数开始的，refresh()是一个模板方法，refresh() 方法的作用是：在创建IOC容器前，如果已经有容器存在，则需要把已有的容器销毁和关闭，以保证在refresh之后使用的是新建立起来的IOC容器。refresh的作用类似于对IOC容器的重启，在新建立好的容 器中对容器进行初始化，对Bean定义资源进行载入FileSystemXmlApplicationContext通过调用其父类AbstractApplicationContext的refresh()函数启动整个IOC容器对Bean定义的载入过程：

refresh()方法主要为IOC容器Bean的生命周期管理提供条件，SpringIOC容器载入Bean定义资源文件从其子类容器的refreshBeanFactory()方法启动，所以整个refresh()

中“ConﬁgurableListableBeanFactorybeanFactory=obtainFreshBeanFactory();”这句以后代码的都是 注册容器的信息源和生命周期事件，载入过程就是从这句代码启动。

refresh()方法的作用是：在创建IOC容器前，如果已经有容器存在，则需要把已有的容器销毁和关闭， 以保证在refresh之后使用的是新建立起来的IOC容器。refresh的作用类似于对IOC容器的重启，在新建 立好的容器中对容器进行初始化，对Bean定义资源进行载入

- 、AbstractApplicationContext的obtainFreshBeanFactory()方法调用子类容器的refreshBeanFactory()方法，启动容器载入Bean定义资源文件的过程，代码如下：

AbstractApplicationContext类中只抽象定义了refreshBeanFactory()方法，容器真正调用的是其子类AbstractRefreshableApplicationContext实现的refreshBeanFactory()方法，方法的源码如下：

在这个方法中，先判断BeanFactory是否存在，如果存在则先销毁beans并关闭beanFactory，接着创 建DefaultListableBeanFactory，并调用loadBeanDeﬁnitions(beanFactory)装载bean定义。

- 、AbstractRefreshableApplicationContext子类的loadBeanDeﬁnitions方法：

AbstractRefreshableApplicationContext中只定义了抽象的loadBeanDeﬁnitions方法，容器真正调用 的是其子类AbstractXmlApplicationContext对该方法的实现，AbstractXmlApplicationContext的主要源码如下：

loadBeanDeﬁnitions方法同样是抽象方法，是由其子类实现的，也即在AbstractXmlApplicationContext中。

XmlBean读取器(XmlBeanDeﬁnitionReader)调用其父类AbstractBeanDeﬁnitionReader的reader.loadBeanDeﬁnitions方法读取Bean定义资源。

由于我们使用FileSystemXmlApplicationContext作为例子分析，因此getConﬁgResources的返回值为

null，因此程序执行reader.loadBeanDeﬁnitions(conﬁgLocations)分支。

1. 、AbstractBeanDeﬁnitionReader读取Bean定义资源,在其抽象父类AbstractBeanDeﬁnitionReader

中定义了载入过程。

AbstractBeanDeﬁnitionReader的loadBeanDeﬁnitions方法源码如下：





loadBeanDeﬁnitions(Resource...resources)方法和上面分析的3个方法类似，同样也是调用XmlBeanDeﬁnitionReader的loadBeanDeﬁnitions方法。

从对AbstractBeanDeﬁnitionReader的loadBeanDeﬁnitions方法源码分析可以看出该方法做了以下两 件事：

首先，调用资源加载器的获取资源方法resourceLoader.getResource(location)，获取到要加载的资源。其次，真正执行加载功能是其子类XmlBeanDeﬁnitionReader的loadBeanDeﬁnitions方法。

看到上面的ResourceLoader与ApplicationContext的继承系图，可以知道其实际调用的是DefaultResourceLoader中的getSource()方法定位Resource，因为FileSystemXmlApplicationContext 本身就是DefaultResourceLoader的实现类，所以此时又回到了FileSystemXmlApplicationContext中 来。

- 、资源加载器获取要读入的资源：

XmlBeanDeﬁnitionReader通过调用其父类DefaultResourceLoader的getResource方法获取要加载的 资源，其源码如下

FileSystemXmlApplicationContext容器提供了getResourceByPath方法的实现，就是为了处理既不 是classpath标识，又不是URL标识的Resource定位这种情况。

这样代码就回到了FileSystemXmlApplicationContext中来，他提供了FileSystemResource来完成从 文件系统得到配置文件的资源定义。

这样，就可以从文件系统路径上对IOC配置文件进行加载，当然我们可以按照这个逻辑从任何地方加载，在Spring中我们看到它提供的各种资源抽象，比如ClassPathResource,URLResource,FileSystemResource等来供我们使用。上面我们看到的是定位Resource的一个过程，而这只是加载过程的一部分.

- 、XmlBeanDeﬁnitionReader加载Bean定义资源：

继续回到XmlBeanDeﬁnitionReader的loadBeanDeﬁnitions(Resource...)方法看到代表bean文件的资 源定义以后的载入过程。

通过源码分析，载入Bean定义资源文件的最后一步是将Bean定义资源转换为Document对象，该过 程由documentLoader实现

- 、DocumentLoader将Bean定义资源转换为Document对象： DocumentLoader将Bean定义资源转换成Document对象的源码如下：

该解析过程调用JavaEE标准的JAXP标准进行处理。

至此SpringIOC容器根据定位的Bean定义资源文件，将其加载读入并转换成为Document对象过程完 成。接下来我们要继续分析SpringIOC容器将载入的Bean定义资源文件转换为Document对象之后，是 如何将其解析为SpringIOC管理的Bean对象并将其注册到容器中的。

- 、XmlBeanDeﬁnitionReader解析载入的Bean定义资源文件：

XmlBeanDeﬁnitionReader类中的doLoadBeanDeﬁnitions方法是从特定XML文件中实际载入Bean定 义资源的方法，该方法在载入Bean定义资源之后将其转换为Document对象，接下来调用registerBeanDeﬁnitions启动SpringIOC容器对Bean定义的解析过程，registerBeanDeﬁnitions方法源 码如下：

Bean定义资源的载入解析分为以下两个过程：

首先，通过调用XML解析器将Bean定义资源文件转换得到Document对象，但是这些Document对象并 没有按照Spring的Bean规则进行解析。这一步是载入的过程

其次，在完成通用的XML解析之后，按照Spring的Bean规则对Document对象进行解析。

按照Spring的Bean规则对Document对象解析的过程是在接口BeanDeﬁnitionDocumentReader的实 现类DefaultBeanDeﬁnitionDocumentReader中实现的。

- 、DefaultBeanDeﬁnitionDocumentReader对Bean定义的Document对象解析：

BeanDeﬁnitionDocumentReader接口通过registerBeanDeﬁnitions方法调用其实现类DefaultBeanDeﬁnitionDocumentReader对Document对象进行解析，解析的代码如下：

通过上述SpringIOC容器对载入的Bean定义Document解析可以看出，我们使用Spring时，在Spring配置文件中可以使用元素来导入IOC容器所需要的其他资源，SpringIOC容器在解析时会首先将指定导入的资源加载进容器中。使用别名时，SpringIOC容器首先将别名元素所定义的别名注册到容器中。

对于既不是元素，又不是元素的元素，即Spring配置文件中普通的元素的解析由

BeanDeﬁnitionParserDelegate类的parseBeanDeﬁnitionElement方法来实现。

- 、BeanDeﬁnitionParserDelegate解析Bean定义资源文件中的元素：

Bean定义资源文件中的和元素解析在DefaultBeanDeﬁnitionDocumentReader中已经完成，对Bean

定义资源文件中使用最多的元素交由BeanDeﬁnitionParserDelegate来解析，其解析实现的源码如下：

只要使用过Spring，对Spring配置文件比较熟悉的人，通过对上述源码的分析，就会明白我们在Spring 配置文件中元素的中配置的属性就是通过该方法解析和设置到Bean中去的。

注意：在解析元素过程中没有创建和实例化Bean对象，只是创建了Bean对象的定义类BeanDeﬁnition，将元素中的配置信息设置到BeanDeﬁnition中作为记录，当依赖注入时才使用这些记 录信息创建和实例化具体的Bean对象。

上面方法中一些对一些配置如元信息(meta)、qualiﬁer等的解析，我们在Spring中配置时使用的也不 多，我们在使用Spring的元素时，配置最多的是属性，因此我们下面继续分析源码，了解Bean的属性在解析时是如何设置的。

- 、BeanDeﬁnitionParserDelegate解析元素：BeanDeﬁnitionParserDelegate在解析调用parsePropertyElements方法解析元素中的属性子元素，解析源码如下：

通过对上述源码的分析，我们可以了解在Spring配置文件中，元素中元素的相关配置是如何处理的：

a.ref被封装为指向依赖对象一个引用。

b.value配置都会封装成一个字符串类型的对象。

c.ref和value都通过“解析的数据类型属性值.setSource(extractSource(ele));”方法将属性值/引用与所引 用的属性关联起来。

在方法的最后对于元素的子元素通过parsePropertySubElement方法解析，我们继续分析该方法的源 码，了解其解析过程。

- 、解析元素的子元素：

在BeanDeﬁnitionParserDelegate类中的parsePropertySubElement方法对中的子元素解析，源码如 下：

通过上述源码分析，我们明白了在Spring配置文件中，对元素中配置的array、list、set、map、prop 等各种集合子元素的都通过上述方法解析，生成对应的数据对象，比如ManagedList、 ManagedArray、ManagedSet等，这些Managed类是Spring对象BeanDeﬁniton的数据封装，对集合 数据类型的具体解析有各自的解析方法实现，解析方法的命名非常规范，一目了然，我们对集合元素的 解析方法进行源码分析，了解其实现过程。

- 、解析子元素：

在BeanDeﬁnitionParserDelegate类中的parseListElement方法就是具体实现解析元素中的集合子元 素，源码如下：

经过对SpringBean定义资源文件转换的Document对象中的元素层层解析，SpringIOC现在已经将XML 形式定义的Bean定义资源文件转换为SpringIOC所识别的数据结构——BeanDeﬁnition，它是Bean定 义资源文件中配置的POJO对象在SpringIOC容器中的映射，我们可以通过AbstractBeanDeﬁnition为入口，看到了IOC容器进行索引、查询和操作。

通过SpringIOC容器对Bean定义资源的解析后，IOC容器大致完成了管理Bean对象的准备工作，即初始 化过程，但是最为重要的依赖注入还没有发生，现在在IOC容器中BeanDeﬁnition存储的只是一些静态信息，接下来需要向容器注册Bean定义信息才能全部完成IOC容器的初始化过程

- 、解析过后的BeanDeﬁnition在IOC容器中的注册：

让我们继续跟踪程序的执行顺序，接下来我们来分析DefaultBeanDeﬁnitionDocumentReader对Bean 定义转换的Document对象解析的流程中，在其parseDefaultElement方法中完成对Document对象的 解析后得到封装BeanDeﬁnition的BeanDeﬁnitionHold对象，然后调用BeanDeﬁnitionReaderUtils的registerBeanDeﬁnition方法向IOC容器注册解析的Bean，BeanDeﬁnitionReaderUtils的注册的源码如 下：

当调用BeanDeﬁnitionReaderUtils向IOC容器注册解析的BeanDeﬁnition时，真正完成注册功能的是

DefaultListableBeanFactory。

- 、DefaultListableBeanFactory向IOC容器注册解析后的BeanDeﬁnition： DefaultListableBeanFactory中使用一个HashMap的集合对象存放IOC容器中注册解析的BeanDeﬁnition，向IOC容器注册的主要源码如下：

至此，Bean定义资源文件中配置的Bean被解析过后，已经注册到IOC容器中，被容器管理起来，真正完成了IOC容器初始化所做的全部工作。现在IOC容器中已经建立了整个Bean的配置信息，这些BeanDeﬁnition信息已经可以使用，并且可以被检索，IOC容器的作用就是对这些注册的Bean定义信息 进行处理和维护。这些的注册的Bean定义信息是IOC容器控制反转的基础，正是有了这些注册的数据， 容器才可以进行依赖注入。

总结：

现在通过上面的代码，总结一下IOC容器初始化的基本步骤：

- 初始化的入口在容器实现中的refresh()调用来完成。
- 对bean定义载入IOC容器使用的方法是loadBeanDeﬁnition,其中的大致过程如下：

通过ResourceLoader来完成资源文件位置的定位，DefaultResourceLoader是默认的实现，同时上下 文本身就给出了ResourceLoader的实现，可以从类路径，文件系统,URL等方式来定为资源位置。如果 是XmlBeanFactory作为IOC容器，那么需要为它指定bean定义的资源，也就是说bean定义文件时通过 抽象成Resource来被IOC容器处理的，容器通过BeanDeﬁnitionReader来完成定义信息的解析和Bean 信息的注册,往往使用的是XmlBeanDeﬁnitionReader来解析bean的xml定义文件-实际的处理过程是委 托给BeanDeﬁnitionParserDelegate来完成的，从而得到bean的定义信息，这些信息在Spring中使用BeanDeﬁnition对象来表示-这个名字可以让我们想到loadBeanDeﬁnition,RegisterBeanDeﬁnition这些 相关方法-他们都是为处理BeanDeﬁnitin服务的，容器解析得到BeanDeﬁnition以后，需要把它在IOC容 器中注册，这由IOC实现BeanDeﬁnitionRegistry接口来实现。注册过程就是在IOC容器内部维护的一个HashMap来保存得到的BeanDeﬁnition的过程。这个HashMap是IOC容器持有Bean信息的场所，以后 对Bean的操作都是围绕这个HashMap来实现的。

然后我们就可以通过BeanFactory和ApplicationContext来享受到SpringIOC的服务了,在使用IOC容器的 时候，我们注意到除了少量粘合代码，绝大多数以正确IOC风格编写的应用程序代码完全不用关心如何 到达工厂，因为容器将把这些对象与容器管理的其他对象钩在一起。基本的策略是把工厂放到已知的地 方，最好是放在对预期使用的上下文有意义的地方，以及代码将实际需要访问工厂的地方。Spring本身 提供了对声明式载入web应用程序用法的应用程序上下文,并将其存储在ServletContext中的框架实现。

以下是容器初始化全过程的时序图：

在使用SpringIOC容器的时候我们还需要区别两个概念:

BeanFactory和FactoryBean，其中BeanFactory指的是IOC容器的编程抽象，比如ApplicationContext，XmlBeanFactory等，这些都是IOC容器的具体表现，需要使用什么样的容器由客户决定,但Spring为我们提供了丰富的选择。FactoryBean只是一个可以在IOC而容器中被管理的一个Bean,是对各种处理过程和资源使用的抽象,FactoryBean在需要时产生另一个对象，而不返回FactoryBean本身,我们可以把它看成是一个抽象工厂，对它的调用返回的是工厂生产的产品。所有的FactoryBean都实现特殊的org.springframework.beans.factory.FactoryBean接口，当使用容器中FactoryBean的时候，该容器不会返回FactoryBean本身,而是返回其生成的对象。Spring包括了大部分 的通用资源和服务访问抽象的FactoryBean的实现，其中包括:对JNDI查询的处理，对代理对象的处理， 对事务性代理的处理，对RMI代理的处理等，这些我们都可以看成是具体的工厂,看成是Spring为我们建立好的工厂。也就是说Spring通过使用抽象工厂模式为我们准备了一系列工厂来生产一些特定的对象,免除我们手工重复的工作，我们要使用时只需要在IOC容器里配置好就能很方便的使用了。

- **基于\****XML***\*的依赖注入**
- **依赖注入发生的时间**

当SpringIOC容器完成了Bean定义资源的定位、载入和解析注册以后，IOC容器中已经管理类Bean定义 的相关数据，但是此时IOC容器还没有对所管理的Bean进行依赖注入，依赖注入在以下两种情况发生：

- 用户第一次通过getBean方法向IOC容索要Bean时，IOC容器触发依赖注入。
- 当用户在Bean定义资源中为元素配置了lazy-init属性，即让容器在解析注册Bean定义时进行预实例 化，触发依赖注入。

BeanFactory接口定义了SpringIOC容器的基本功能规范，是SpringIOC容器所应遵守的最底层和最基本的编程规范。BeanFactory接口中定义了几个getBean方法，就是用户向IOC容器索取管理的Bean的方 法，我们通过分析其子类的具体实现，理解SpringIOC容器在用户索取Bean时如何完成依赖注入。

在BeanFactory中我们看到getBean（String...）函数，它的具体实现在AbstractBeanFactory中。

- **2\****、***\*AbstractBeanFactory\****通过***\*getBean\****向***\*IOC\****容器获取被管***\*理的\****Bean***\*，**

AbstractBeanFactory的getBean相关方法的源码如下：

通过上面对向IOC容器获取Bean方法的分析，我们可以看到在Spring中，如果Bean定义的单例模式(Singleton)，则容器在创建之前先从缓存中查找，以确保整个容器中只存在一个实例对象。如果Bean 定义的是原型模式(Prototype)，则容器每次都会创建一个新的实例对象。除此之外，Bean定义还可以 扩展为指定其生命周期范围。

上面的源码只是定义了根据Bean定义的模式，采取的不同创建Bean实例对象的策略，具体的Bean实例对象的创建过程由实现了ObejctFactory接口的匿名内部类的createBean方法完成，ObejctFactory使用委派模式，具体的Bean实例创建过程交由其实现类AbstractAutowireCapableBeanFactory完成，我们 继续分析AbstractAutowireCapableBeanFactory的createBean方法的源码，理解其创建Bean实例的具 体实现过程。

- **3\****、***\*AbstractAutowireCapableBeanFactory\****创建***\*Bean\****实例***\*对象：**

AbstractAutowireCapableBeanFactory类实现了ObejctFactory接口，创建容器指定的Bean实例对象，同时还对创建的Bean实例对象进行初始化处理。其创建Bean实例对象的方法源码如下：

通过对方法源码的分析，我们看到具体的依赖注入实现在以下两个方法中： (1).createBeanInstance：生成Bean所包含的java对象实例。(2).populateBean：对Bean属性的依赖注入进行处理。

下面继续分析这两个方法的代码实现。

- **4\****、***\*createBeanInstance\****方法创建***\*Bean\****的***\*java\****实例对象：**

在createBeanInstance方法中，根据指定的初始化策略，使用静态工厂、工厂方法或者容器的自动装配 特性生成java实例对象，创建对象的源码如下：

经过对上面的代码分析，我们可以看出，对使用工厂方法和自动装配特性的Bean的实例化相当比较清 楚，调用相应的工厂方法或者参数匹配的构造方法即可完成实例化对象的工作，但是对于我们最常使用 的默认无参构造方法就需要使用相应的初始化策略( JDK的反射机制或者CGLIB)来进行初始化了，在方法getInstantiationStrategy().instantiate()中就具体实现类使用初始策略实例化对象。

- **5\****、***\*SimpleInstantiationStrategy\****类使用默认的无参构造方法***\*创建\****Bean***\*实例化对象：**

在使用默认的无参构造方法创建Bean的实例化对象时，方法getInstantiationStrategy().instantiate()调 用了SimpleInstantiationStrategy类中的实例化Bean的方法，其源码如下：

通过上面的代码分析，我们看到了如果Bean有方法被覆盖了，则使用JDK的反射机制进行实例化，否则，使用CGLIB进行实例化。

instantiateWithMethodInjection方法调用SimpleInstantiationStrategy的子类CglibSubclassingInstantiationStrategy使用CGLIB来进行初始化，其源码如下：

CGLIB是一个常用的字节码生成器的类库，它提供了一系列API实现java字节码的生成和转换功能。我 们在学习JDK的动态代理时都知道，JDK的动态代理只能针对接口，如果一个类没有实现任何接口，要对 其进行动态代理只能使用CGLIB。

- **6\****、***\*populateBean\****方法对***\*Bean\****属性的依赖注入：**

在第3步的分析中我们已经了解到Bean的依赖注入分为以下两个过程： (1).createBeanInstance：生成Bean所包含的Java对象实例。(2).populateBean：对Bean属性的依赖注入进行处理。

上面我们已经分析了容器初始化生成Bean所包含的Java实例对象的过程，现在我们继续分析生成对象后，SpringIOC容器是如何将Bean的属性依赖关系注入Bean实例对象中并设置好的，属性依赖注入的 代码如下：

分析上述代码，我们可以看出，对属性的注入过程分以下两种情况：

- 属性值类型不需要转换时，不需要解析属性值，直接准备进行依赖注入。
- 属性值需要进行类型转换时，如对其他对象的引用等，首先需要解析属性值，然后对解析后的属性 值进行依赖注入。

对属性值的解析是在BeanDeﬁnitionValueResolver类中的resolveValueIfNecessary方法中进行的，对 属性值的依赖注入是通过bw.setPropertyValues方法实现的，在分析属性值的依赖注入之前，我们先分 析一下对属性值的解析过程。

## 1.3.7 7、BeanDeﬁnitionValueResolver解析属性值：

当容器在对属性进行依赖注入时，如果发现属性值需要进行类型转换，如属性值是容器中另一个Bean 实例对象的引用，则容器首先需要根据属性值解析出所引用的对象，然后才能将该引用对象注入到目标 实例对象的属性上去，对属性进行解析的由resolveValueIfNecessary方法实现，其源码如下：

通过上面的代码分析，我们明白了Spring是如何将引用类型，内部类以及集合类型等属性进行解析的， 属性值解析完成后就可以进行依赖注入了，依赖注入的过程就是Bean对象实例设置到它所依赖的Bean 对象属性上去，在第6步中我们已经说过，依赖注入是通过bw.setPropertyValues方法实现的，该方法 也使用了委托模式，在BeanWrapper接口中至少定义了方法声明，依赖注入的具体实现交由其实现类BeanWrapperImpl来完成，下面我们就分析依BeanWrapperImpl中赖注入相关的源码。

## 1.3.8 8、BeanWrapperImpl对Bean属性的依赖注入：

BeanWrapperImpl类主要是对容器中完成初始化的Bean实例对象进行属性的依赖注入，即把Bean对象 设置到它所依赖的另一个Bean的属性中去。然而，BeanWrapperImpl中的注入方法实际上由AbstractNestablePropertyAccessor来实现的，其相关源码如下：

通过对上面注入依赖代码的分析，我们已经明白了SpringIOC容器是如何将属性的值注入到Bean实例对象中去的：

- 对于集合类型的属性，将其属性值解析为目标类型的集合后直接赋值给属性。
- 对于非集合类型的属性，大量使用了JDK的反射和内省机制，通过属性的getter方法(readerMethod) 获取指定属性注入以前的值，同时调用属性的setter方法(writerMethod)为属性设置注入后的值。看到 这里相信很多人都明白了Spring的setter注入原理。

至此SpringIOC容器对Bean定义资源文件的定位，载入、解析和依赖注入已经全部分析完毕，现在SpringIOC容器中管理了一系列靠依赖关系联系起来的Bean，程序不需要应用自己手动创建所需的对象，SpringIOC容器会在我们使用的时候自动为我们创建，并且为我们注入好相关的依赖，这就是Spring核心功能的控制反转和依赖注入的相关功能。

# 1.4 基于Annotation的依赖注入

1.从Spring2.0以后的版本中，Spring也引入了基于注解(Annotation)方式的配置，注解(Annotation)是JDK1.5中引入的一个新特性，用于简化Bean的配置，某些场合可以取代XML配置文件。开发人员对注 解(Annotation)的态度也是萝卜青菜各有所爱，个人认为注解可以大大简化配置，提高开发速度，同时也不能完全取代XML配置方式，XML方式更加灵活，并且发展的相对成熟，这种配置方式为大多数Spring开发者熟悉；注解方式使用起来非常简洁，但是尚处于发展阶段，XML配置文件和注解(Annotation)可以相互配合使用。

SpringIOC容器对于类级别的注解和类内部的注解分以下两种处理策略：

- 类级别的注解：如@Component、@Repository、@Controller、@Service以及JavaEE6的@ManagedBean和@Named注解，都是添加在类上面的类级别注解，Spring容器根据注解的过滤规则 扫描读取注解Bean定义类，并将其注册到SpringIOC容器中。
- 类内部的注解：如@Autowire、@Value、@Resource以及EJB和WebService相关的注解等，都是添加在类内部的字段或者方法上的类内部注解，SpringIOC容器通过Bean后置注解处理器解析Bean内部的注解。

下面将根据这两种处理策略，分别分析Spring处理注解相关的源码。

## 1.4.1 2.AnnotationConﬁgApplicationContext对注解Bean初始 化：

Spring中，管理注解Bean定义的容器有两个：AnnotationConﬁgApplicationContext和AnnotationConﬁgWebApplicationContex。这两个类是专门处理Spring注解方式配置的容器，直接依赖于注解作为容器配置信息来源的IOC容器。AnnotationConﬁgWebApplicationContext是AnnotationConﬁgApplicationContext的web版本，两者的用法以及对注解的处理方式几乎没有什么差别。

现在来看看AnnotationConﬁgApplicationContext的源码：

通过对AnnotationConﬁgApplicationContext的源码分析，我们了解到Spring对注解的处理分为两种方式：

- 直接将注解Bean注册到容器中：

可以在初始化容器时注册；也可以在容器创建之后手动调用注册方法向容器注册，然后通过手动刷新容 器，使得容器对注册的注解Bean进行处理。

- 通过扫描指定的包及其子包下的所有类：

在初始化注解容器时指定要自动扫描的路径，如果容器创建以后向给定路径动态添加了注解Bean，则需要手动调用容器扫描的方法，然后手动刷新容器，使得容器对所注册的Bean进行处理。

接下来，将会对两种处理方式详细分析其实现过程。

## 1.4.2 3.AnnotationConﬁgApplicationContext注册注解Bean：

当创建注解处理容器时，如果传入的初始参数是具体的注解Bean定义类时，注解容器读取并注册。

(1).AnnotationConﬁgApplicationContext通过调用注解Bean定义读取器AnnotatedBeanDeﬁnitionReader的register方法向容器注册指定的注解Bean，注解Bean定义读取器 向容器注册注解Bean的源码如下：

从上面的源码我们可以看出，注册注解Bean定义类的基本步骤：

a，需要使用注解元数据解析器解析注解Bean中关于作用域的配置。

b，使用AnnotationConﬁgUtils的processCommonDeﬁnitionAnnotations方法处理注解Bean定义类 中通用的注解。

c，使用AnnotationConﬁgUtils的applyScopedProxyMode方法创建对于作用域的代理对象。

d，通过BeanDeﬁnitionReaderUtils向容器注册Bean。下面我们继续分析这3步的具体实现过程

(2).AnnotationScopeMetadataResolver解析作用域元数据：

AnnotationScopeMetadataResolver通过processCommonDeﬁnitionAnnotations方法解析注解Bean 定义类的作用域元信息，即判断注册的Bean是原生类型(prototype)还是单态(singleton)类型，其源码 如下：

上述代码中的annDef.getMetadata().getAnnotationAttributes方法就是获取对象中指定类型的注解 的值。

(3).AnnotationConﬁgUtils处理注解Bean定义类中的通用注解：

AnnotationConﬁgUtils类的processCommonDeﬁnitionAnnotations在向容器注册Bean之前，首先对 注解Bean定义类中的通用Spring注解进行处理，源码如下：

(4).AnnotationConﬁgUtils根据注解Bean定义类中配置的作用域为其应用相应的代理策略：

AnnotationConﬁgUtils类的applyScopedProxyMode方法根据注解Bean定义类中配置的作用域@Scope注解的值，为Bean定义应用相应的代理模式，主要是在Spring面向切面编程(AOP)中使用。源 码如下：

这段为Bean引用创建相应模式的代理，这里不做深入的分析。

(5).BeanDeﬁnitionReaderUtils向容器注册Bean：

BeanDeﬁnitionReaderUtils向容器注册载入的Bean我们在第4篇博客中已经分析过，主要是校验Bean

定义，然后将Bean添加到容器中一个管理Bean定义的HashMap中，这里就不做分析。

## 1.4.3 4.AnnotationConﬁgApplicationContext扫描指定包及其子 包下的注解Bean：

当创建注解处理容器时，如果传入的初始参数是注解Bean定义类所在的包时，注解容器将扫描给定的包及其子包，将扫描到的注解Bean定义载入并注册。

(1).ClassPathBeanDeﬁnitionScanner扫描给定的包及其子包：

AnnotationConﬁgApplicationContext通过调用类路径Bean定义扫描器ClassPathBeanDeﬁnitionScanner扫描给定包及其子包下的所有类，主要源码如下：

类路径Bean定义扫描器ClassPathBeanDeﬁnitionScanner主要通过ﬁndCandidateComponents方法调 用其父类ClassPathScanningCandidateComponentProvider类来扫描获取给定包及其子包下的类。

(2).ClassPathScanningCandidateComponentProvider扫描给定包及其子包的类：

ClassPathScanningCandidateComponentProvider类的ﬁndCandidateComponents方法具体实现扫描给定类路径包的功能，主要源码如下：

## 1.4.4 5.AnnotationConﬁgWebApplicationContext载入注解Bean定义：

AnnotationConﬁgWebApplicationContext是AnnotationConﬁgApplicationContext的Web版，它们对 于注解Bean的注册和扫描是基本相同的，但是AnnotationConﬁgWebApplicationContext对注解Bean 定义的载入稍有不同，AnnotationConﬁgWebApplicationContext注入注解Bean定义源码如下：

解析和注入注解配置资源的过程分析

# 1.5 IOC容器中那些鲜为人知的事儿

## 1.5.1 1、介绍

通过前面章节中对SpringIOC容器的源码分析，我们已经基本上了解了SpringIOC容器对Bean定义资源 的定位、读入和解析过程，同时也清楚了当用户通过getBean方法向IOC容器获取被管理的Bean时， IOC容器对Bean进行的初始化和依赖注入过程，这些是SpringIOC容器的基本功能特性。SpringIOC容 器还有一些高级特性，如使用lazy-init属性对Bean预初始化、FactoryBean产生或者修饰Bean对象的生 成、IOC容器初始化Bean过程中使用BeanPostProcessor后置处理器对Bean声明周期事件管理和IOC容器的autowiring自动装配功能等。

## 1.5.2 2、SpringIOC容器的lazy-init属性实现预实例化：

通过前面我们对IOC容器的实现和工作原理分析，我们知道IOC容器的初始化过程就是对Bean定义资源的定位、载入和注册，此时容器对Bean的依赖注入并没有发生，依赖注入主要是在应用程序第一次向 容器索取Bean时，通过getBean方法的调用完成。

当Bean定义资源的元素中配置了lazy-init属性时，容器将会在初始化的时候对所配置的Bean进行预实 例化，Bean的依赖注入在容器初始化的时候就已经完成。这样，当应用程序第一次向容器索取被管理的Bean时，就不用再初始化和对Bean进行依赖注入了，直接从容器中获取已经完成依赖注入的现成Bean，可以提高应用第一次向容器获取Bean的性能。

下面我们通过代码分析容器预实例化的实现过程：

(1).refresh()

先从IOC容器的初始会过程开始，通过前面文章分析，我们知道IOC容器读入已经定位的Bean定义资源是从refresh方法开始的，我们首先从AbstractApplicationContext类的refresh方法入手分析，源码如下：

在refresh()方法中

ConﬁgurableListableBeanFactorybeanFactory=obtainFreshBeanFactory();启动了Bean定义资源的载 入、注册过程，而ﬁnishBeanFactoryInitialization方法是对注册后的Bean定义中的预实例化(lazy- init=false,Spring默认就是预实例化,即为true)的Bean进行处理的地方。

(2).ﬁnishBeanFactoryInitialization处理预实例化Bean:

当Bean定义资源被载入IOC容器之后，容器将Bean定义资源解析为容器内部的数据结构BeanDeﬁnition注册到容器中，AbstractApplicationContext类中的ﬁnishBeanFactoryInitialization方法对配置了预实例化属性的Bean进行预初始化过程，源码如下：

ConﬁgurableListableBeanFactory是一个接口，其preInstantiateSingletons方法由其子类DefaultListableBeanFactory提供。

(3)、DefaultListableBeanFactory对配置lazy-init属性单态Bean的预实例化：

通过对lazy-init处理源码的分析，我们可以看出，如果设置了lazy-init属性，则容器在完成Bean定义的 注册之后，会通过getBean方法，触发对指定Bean的初始化和依赖注入过程，这样当应用第一次向容器索取所需的Bean时，容器不再需要对Bean进行初始化和依赖注入，直接从已经完成实例化和依赖注入的Bean中取一个现成的Bean，这样就提高了第一次获取Bean的性能。

## 1.5.3 3、FactoryBean的实现：

在Spring中，有两个很容易混淆的类：BeanFactory和FactoryBean。

BeanFactory：Bean工厂，是一个工厂(Factory)，我们SpringIOC容器的最顶层接口就是这个BeanFactory，它的作用是管理Bean，即实例化、定位、配置应用程序中的对象及建立这些对象间的依 赖。

FactoryBean：工厂Bean，是一个Bean，作用是产生其他bean实例。通常情况下，这种bean没有什么 特别的要求，仅需要提供一个工厂方法，该方法用来返回其他bean实例。通常情况下，bean无须自己 实现工厂模式，Spring容器担任工厂角色；但少数情况下，容器中的bean本身就是工厂，其作用是产 生其它bean实例。

当用户使用容器本身时，可以使用转义字符”&”来得到FactoryBean本身，以区别通过FactoryBean产生的实例对象和FactoryBean对象本身。在BeanFactory中通过如下代码定义了该转义字符：

StringFACTORY_BEAN_PREFIX="&";

如果myJndiObject是一个FactoryBean，则使用&myJndiObject得到的是myJndiObject对象，而不是

myJndiObject产生出来的对象。

(1).FactoryBean的源码如下：

(2).AbstractBeanFactory的getBean方法调用FactoryBean：

在前面我们分析SpringIOC容器实例化Bean并进行依赖注入过程的源码时，提到在getBean方法触发容 器实例化Bean的时候会调用AbstractBeanFactory的doGetBean方法来进行实例化的过程，源码如下：

在上面获取给定Bean的实例对象的getObjectForBeanInstance方法中，会调用FactoryBeanRegistrySupport类的getObjectFromFactoryBean方法，该方法实现了Bean工厂生产Bean实例对象。

Dereference(解引用)：一个在C/C++中应用比较多的术语，在C++中，”*”是解引用符号，而”&”是引用 符号，解引用是指变量指向的是所引用对象的本身数据，而不是引用对象的内存地址。

(3) 、 AbstractBeanFactory 生 产 Bean 实 例 对 象 ： AbstractBeanFactory类中生产Bean实例对象的主要源码如下：

从上面的源码分析中，我们可以看出，BeanFactory接口调用其实现类的getObject方法来实现创建

Bean实例对象的功能。

(4).工厂Bean的实现类getObject方法创建Bean实例对象：

FactoryBean的实现类有非常多，比如：Proxy、RMI、JNDI、ServletContextFactoryBean等等， FactoryBean接口为Spring容器提供了一个很好的封装机制，具体的getObject有不同的实现类根据不同 的实现策略来具体提供，我们分析一个最简单的AnnotationTestFactoryBean的实现源码：

其他的Proxy，RMI，JNDI等等，都是根据相应的策略提供getObject的实现。这里不做一一分析，这已 经不是Spring的核心功能，有需要的时候再去深入研究。

## 1.5.4 4、BeanPostProcessor后置处理器的实现：

BeanPostProcessor后置处理器是SpringIOC容器经常使用到的一个特性，这个Bean后置处理器是一个监听器，可以监听容器触发的Bean声明周期事件。后置处理器向容器注册以后，容器中管理的Bean就具备了接收IOC容器事件回调的能力。

BeanPostProcessor的使用非常简单，只需要提供一个实现接口BeanPostProcessor的实现类，然后在Bean的配置文件中设置即可。

(1).BeanPostProcessor的源码如下：

这两个回调的入口都是和容器管理的Bean的生命周期事件紧密相关，可以为用户提供在SpringIOC容器初始化Bean过程中自定义的处理操作。

(2).AbstractAutowireCapableBeanFactory类对容器生成的Bean添加后置处理器：

BeanPostProcessor后置处理器的调用发生在SpringIOC容器完成对Bean实例对象的创建和属性的依赖注入完成之后，在对Spring依赖注入的源码分析过程中我们知道，当应用程序第一次调用getBean方法(lazy-init预实例化除外)向SpringIOC容器索取指定Bean时触发SpringIOC容器创建Bean实例对象并进行依赖注入的过程，其中真正实现创建Bean对象并进行依赖注入的方法是AbstractAutowireCapableBeanFactory类的doCreateBean方法，主要源码如下：

从上面的代码中我们知道，为Bean实例对象添加BeanPostProcessor后置处理器的入口的是

initializeBean方法。

(3).initializeBean方法为容器产生的Bean实例对象添加BeanPostProcessor后置处理器：

同样在AbstractAutowireCapableBeanFactory类中，initializeBean方法实现为容器创建的Bean实例对 象添加BeanPostProcessor后置处理器，源码如下：

BeanPostProcessor是一个接口，其初始化前的操作方法和初始化后的操作方法均委托其实现子类来实 现，在Spring中，BeanPostProcessor的实现子类非常的多，分别完成不同的操作，如：AOP面向切面 编程的注册通知适配器、Bean对象的数据校验、Bean继承属性/方法的合并等等，我们以最简单的AOP 切面织入来简单了解其主要的功能。

(4).AdvisorAdapterRegistrationManager在Bean对象初始化后注册通知适配器：

AdvisorAdapterRegistrationManager是BeanPostProcessor的一个实现类，其主要的作用为容器中管 理的Bean注册一个面向切面编程的通知适配器，以便在Spring容器为所管理的Bean进行面向切面编程时提供方便，其源码如下：

其他的BeanPostProcessor接口实现类的也类似，都是对Bean对象使用到的一些特性进行处理，或者 向IOC容器中注册，为创建的Bean实例对象做一些自定义的功能增加，这些操作是容器初始化Bean时自动触发的，不需要认为的干预。

## 1.5.5 5、SpringIOC容器autowiring实现原理：

SpringIOC容器提供了两种管理Bean依赖关系的方式：

a.显式管理：通过BeanDeﬁnition的属性值和构造方法实现Bean依赖关系管理。

b.autowiring：SpringIOC容器的依赖自动装配功能，不需要对Bean属性的依赖关系做显式的声明，只 需要在配置好autowiring属性，IOC容器会自动使用反射查找属性的类型和名称，然后基于属性的类型或者名称来自动匹配容器中管理的Bean，从而自动地完成依赖注入。

通过对autowiring自动装配特性的理解，我们知道容器对Bean的自动装配发生在容器对Bean依赖注入 的过程中。在前面对SpringIOC容器的依赖注入过程源码分析中，我们已经知道了容器对Bean实例对象的属性注入的处理发生在AbstractAutoWireCapableBeanFactory类中的populateBean方法中，我们通 过程序流程分析autowiring的实现原理：

(1).AbstractAutoWireCapableBeanFactory对Bean实例进行属性依赖注入：

应用第一次通过getBean方法(配置了lazy-init预实例化属性的除外)向IOC容器索取Bean时，容器创建Bean实例对象，并且对Bean实例对象进行属性依赖注入，AbstractAutoWireCapableBeanFactory的populateBean方法就是实现Bean属性依赖注入的功能，其主要源码如下：

(2).SpringIOC容器根据Bean名称或者类型进行autowiring自动依赖注入：

通过上面的源码分析，我们可以看出来通过属性名进行自动依赖注入的相对比通过属性类型进行自动依 赖注入要稍微简单一些，但是真正实现属性注入的是DefaultSingletonBeanRegistry类的registerDependentBean方法。

(3).DefaultSingletonBeanRegistry的registerDependentBean方法对属性注入：

通过对autowiring的源码分析，我们可以看出，autowiring的实现过程：

a.对Bean的属性代调用getBean方法，完成依赖Bean的初始化和依赖注入。 b.将依赖Bean的属性引用设置到被依赖的Bean属性上。

c.将依赖Bean的名称和被依赖Bean的名称存储在IOC容器的集合中。

SpringIOC容器的autowiring属性自动依赖注入是一个很方便的特性，可以简化开发时的配置，但是凡 是都有两面性，自动属性依赖注入也有不足，首先，Bean的依赖关系在配置文件中无法很清楚地看出 来，对于维护造成一定困难。其次，由于自动依赖注入是Spring容器自动执行的，容器是不会智能判断的，如果配置不当，将会带来无法预料的后果，所以自动依赖注入特性在使用时还是综合考虑。

**手写框架篇**

# 框架分析

工程名称：spring-custom

工程目的：手写spring框架，实现spring框架核心功能：IoC和DI。分析上述代码，我们可以看出，对属性的注入过程分以下两种情况： (1). 属性值类型不需要转换时，不需要解析属性值，直接准备进行依赖注入。 (2). 属性值需要进行类型转换时，如对其他对象的引用等，首先需要解析属性值，然后对解析后的属性 值进行依赖注入。 对属性值的解析是在 BeanDeﬁnitionValueResolver 类中的 resolveValueIfNecessary 方法中进行的，对 属性值的依赖注入是通过 bw.setPropertyValues 方法实现的，在分析属性值的依赖注入之前，我们先分 析一下对属性值的解析过程。 1.3.7 7、BeanDeﬁnitionValueResolver 解析属性值： 当容器在对属性进行依赖注入时，如果发现属性值需要进行类型转换，如属性值是容器中另一个 Bean 实例对象的引用，则容器首先需要根据属性值解析出所引用的对象，然后才能将该引用对象注入到目标 实例对象的属性上去，对属性进行解析的由 resolveValueIfNecessary 方法实现，其源码如下： 通过上面的代码分析，我们明白了 Spring 是如何将引用类型，内部类以及集合类型等属性进行解析的， 属性值解析完成后就可以进行依赖注入了，依赖注入的过程就是 Bean 对象实例设置到它所依赖的 Bean 对象属性上去，在第 6 步中我们已经说过，依赖注入是通过 bw.setPropertyValues 方法实现的，该方法 也使用了委托模式，在 BeanWrapper 接口中至少定义了方法声明，依赖注入的具体实现交由其实现类 BeanWrapperImpl 来完成，下面我们就分析依 BeanWrapperImpl 中赖注入相关的源码。 1.3.8 8、BeanWrapperImpl 对 Bean 属性的依赖注入： BeanWrapperImpl 类主要是对容器中完成初始化的 Bean 实例对象进行属性的依赖注入，即把 Bean 对象 设置到它所依赖的另一个 Bean 的属性中去。然而，BeanWrapperImpl 中的注入方法实际上由 AbstractNestablePropertyAccessor 来实现的，其相关源码如下： 通过对上面注入依赖代码的分析，我们已经明白了 SpringIOC 容器是如何将属性的值注入到 Bean 实例对象中去的： (1). 对于集合类型的属性，将其属性值解析为目标类型的集合后直接赋值给属性。 (2). 对于非集合类型的属性，大量使用了 JDK 的反射和内省机制，通过属性的 getter 方法(readerMethod) 获取指定属性注入以前的值，同时调用属性的 setter 方法(writerMethod)为属性设置注入后的值。看到 这里相信很多人都明白了 Spring 的 setter 注入原理。 至此 SpringIOC 容器对 Bean 定义资源文件的定位，载入、解析和依赖注入已经全部分析完毕，现在 SpringIOC 容器中管理了一系列靠依赖关系联系起来的 Bean，程序不需要应用自己手动创建所需的对象，SpringIOC 容器会在我们使用的时候自动为我们创建，并且为我们注入好相关的依赖，这就是 Spring 核心功能的控制反转和依赖注入的相关功能。 1.4 基于 Annotation 的依赖注入 1.从 Spring2.0 以后的版本中，Spring 也引入了基于注解(Annotation)方式的配置，注解(Annotation)是 JDK1.5 中引入的一个新特性，用于简化 Bean 的配置，某些场合可以取代 XML 配置文件。开发人员对注 解(Annotation)的态度也是萝卜青菜各有所爱，个人认为注解可以大大简化配置，提高开发速度，同时也不能完全取代 XML 配置方式，XML 方式更加灵活，并且发展的相对成熟，这种配置方式为大多数 Spring 开发者熟悉；注解方式使用起来非常简洁，但是尚处于发展阶段，XML 配置文件和注解(Annotation)可以相互配合使用。 SpringIOC 容器对于类级别的注解和类内部的注解分以下两种处理策略： (1). 类级别的注解：如@Component、@Repository、@Controller、@Service 以及 JavaEE6 的@ManagedBean 和@Named 注解，都是添加在类上面的类级别注解，Spring 容器根据注解的过滤规则 扫描读取注解 Bean 定义类，并将其注册到 SpringIOC 容器中。 (2). 类内部的注解：如@Autowire、@Value、@Resource 以及 EJB 和 WebService 相关的注解等，都是添加在类内部的字段或者方法上的类内部注解，SpringIOC 容器通过 Bean 后置注解处理器解析 Bean 内部的注解。 下面将根据这两种处理策略，分别分析 Spring 处理注解相关的源码。 1.4.1 2.AnnotationConﬁgApplicationContext 对注解 Bean 初始 化： Spring 中，管理注解 Bean 定义的容器有两个：AnnotationConﬁgApplicationContext 和 AnnotationConﬁgWebApplicationContex。这两个类是专门处理 Spring 注解方式配置的容器，直接依赖于注解作为容器配置信息来源的 IOC 容器。AnnotationConﬁgWebApplicationContext 是 AnnotationConﬁgApplicationContext 的 web 版本，两者的用法以及对注解的处理方式几乎没有什么差别。 现在来看看 AnnotationConﬁgApplicationContext 的源码： 通过对 AnnotationConﬁgApplicationContext 的源码分析，我们了解到 Spring 对注解的处理分为两种方式： (1). 直接将注解 Bean 注册到容器中： 可以在初始化容器时注册；也可以在容器创建之后手动调用注册方法向容器注册，然后通过手动刷新容 器，使得容器对注册的注解 Bean 进行处理。 (2). 通过扫描指定的包及其子包下的所有类： 在初始化注解容器时指定要自动扫描的路径，如果容器创建以后向给定路径动态添加了注解 Bean，则需要手动调用容器扫描的方法，然后手动刷新容器，使得容器对所注册的 Bean 进行处理。 接下来，将会对两种处理方式详细分析其实现过程。 1.4.2 3.AnnotationConﬁgApplicationContext 注册注解 Bean： 当创建注解处理容器时，如果传入的初始参数是具体的注解 Bean 定义类时，注解容器读取并注册。 (1).AnnotationConﬁgApplicationContext 通过调用注解 Bean 定义读取器 AnnotatedBeanDeﬁnitionReader 的 register 方法向容器注册指定的注解 Bean，注解 Bean 定义读取器 向容器注册注解 Bean 的源码如下： 从上面的源码我们可以看出，注册注解 Bean 定义类的基本步骤： a，需要使用注解元数据解析器解析注解 Bean 中关于作用域的配置。 b，使用 AnnotationConﬁgUtils 的 processCommonDeﬁnitionAnnotations 方法处理注解 Bean 定义类 中通用的注解。 c，使用 AnnotationConﬁgUtils 的 applyScopedProxyMode 方法创建对于作用域的代理对象。 d，通过 BeanDeﬁnitionReaderUtils 向容器注册 Bean。下面我们继续分析这 3 步的具体实现过程 (2).AnnotationScopeMetadataResolver 解析作用域元数据： AnnotationScopeMetadataResolver 通过 processCommonDeﬁnitionAnnotations 方法解析注解 Bean 定义类的作用域元信息，即判断注册的 Bean 是原生类型(prototype)还是单态(singleton)类型，其源码 如下： 上述代码中的 annDef.getMetadata().getAnnotationAttributes 方法就是获取对象中指定类型的注解 的值。 (3).AnnotationConﬁgUtils 处理注解 Bean 定义类中的通用注解： AnnotationConﬁgUtils 类的 processCommonDeﬁnitionAnnotations 在向容器注册 Bean 之前，首先对 注解 Bean 定义类中的通用 Spring 注解进行处理，源码如下： (4).AnnotationConﬁgUtils 根据注解 Bean 定义类中配置的作用域为其应用相应的代理策略： AnnotationConﬁgUtils 类的 applyScopedProxyMode 方法根据注解 Bean 定义类中配置的作用域@Scope 注解的值，为 Bean 定义应用相应的代理模式，主要是在 Spring 面向切面编程(AOP)中使用。源 码如下： 这段为 Bean 引用创建相应模式的代理，这里不做深入的分析。 (5).BeanDeﬁnitionReaderUtils 向容器注册 Bean： BeanDeﬁnitionReaderUtils 向容器注册载入的 Bean 我们在第 4 篇博客中已经分析过，主要是校验 Bean 定义，然后将 Bean 添加到容器中一个管理 Bean 定义的 HashMap 中，这里就不做分析。 1.4.3 4.AnnotationConﬁgApplicationContext 扫描指定包及其子 包下的注解 Bean： 当创建注解处理容器时，如果传入的初始参数是注解 Bean 定义类所在的包时，注解容器将扫描给定的包及其子包，将扫描到的注解 Bean 定义载入并注册。 (1).ClassPathBeanDeﬁnitionScanner 扫描给定的包及其子包： AnnotationConﬁgApplicationContext 通过调用类路径 Bean 定义扫描器 ClassPathBeanDeﬁnitionScanner 扫描给定包及其子包下的所有类，主要源码如下： 类路径 Bean 定义扫描器 ClassPathBeanDeﬁnitionScanner 主要通过 ﬁndCandidateComponents 方法调 用其父类 ClassPathScanningCandidateComponentProvider 类来扫描获取给定包及其子包下的类。 (2).ClassPathScanningCandidateComponentProvider 扫描给定包及其子包的类： ClassPathScanningCandidateComponentProvider 类的 ﬁndCandidateComponents 方法具体实现扫描给定类路径包的功能，主要源码如下： 1.4.4 5.AnnotationConﬁgWebApplicationContext 载入注解 Bean 定义： AnnotationConﬁgWebApplicationContext 是 AnnotationConﬁgApplicationContext 的 Web 版，它们对 于注解 Bean 的注册和扫描是基本相同的，但是 AnnotationConﬁgWebApplicationContext 对注解 Bean 定义的载入稍有不同，AnnotationConﬁgWebApplicationContext 注入注解 Bean 定义源码如下： 解析和注入注解配置资源的过程分析 1.5 IOC 容器中那些鲜为人知的事儿 1.5.1 1、介绍 通过前面章节中对 SpringIOC 容器的源码分析，我们已经基本上了解了 SpringIOC 容器对 Bean 定义资源 的定位、读入和解析过程，同时也清楚了当用户通过 getBean 方法向 IOC 容器获取被管理的 Bean 时， IOC 容器对 Bean 进行的初始化和依赖注入过程，这些是 SpringIOC 容器的基本功能特性。SpringIOC 容 器还有一些高级特性，如使用 lazy-init 属性对 Bean 预初始化、FactoryBean 产生或者修饰 Bean 对象的生 成、IOC 容器初始化 Bean 过程中使用 BeanPostProcessor 后置处理器对 Bean 声明周期事件管理和 IOC 容器的 autowiring 自动装配功能等。 1.5.2 2、SpringIOC 容器的 lazy-init 属性实现预实例化： 通过前面我们对 IOC 容器的实现和工作原理分析，我们知道 IOC 容器的初始化过程就是对 Bean 定义资源的定位、载入和注册，此时容器对 Bean 的依赖注入并没有发生，依赖注入主要是在应用程序第一次向 容器索取 Bean 时，通过 getBean 方法的调用完成。 当 Bean 定义资源的元素中配置了 lazy-init 属性时，容器将会在初始化的时候对所配置的 Bean 进行预实 例化，Bean 的依赖注入在容器初始化的时候就已经完成。这样，当应用程序第一次向容器索取被管理的 Bean 时，就不用再初始化和对 Bean 进行依赖注入了，直接从容器中获取已经完成依赖注入的现成 Bean，可以提高应用第一次向容器获取 Bean 的性能。 下面我们通过代码分析容器预实例化的实现过程： (1).refresh() 先从 IOC 容器的初始会过程开始，通过前面文章分析，我们知道 IOC 容器读入已经定位的 Bean 定义资源是从 refresh 方法开始的，我们首先从 AbstractApplicationContext 类的 refresh 方法入手分析，源码如下： 在 refresh()方法中 ConﬁgurableListableBeanFactorybeanFactory=obtainFreshBeanFactory();启动了 Bean 定义资源的载 入、注册过程，而 ﬁnishBeanFactoryInitialization 方法是对注册后的 Bean 定义中的预实例化(lazy- init=false,Spring 默认就是预实例化,即为 true)的 Bean 进行处理的地方。 (2).ﬁnishBeanFactoryInitialization 处理预实例化 Bean: 当 Bean 定义资源被载入 IOC 容器之后，容器将 Bean 定义资源解析为容器内部的数据结构 BeanDeﬁnition 注册到容器中，AbstractApplicationContext 类中的 ﬁnishBeanFactoryInitialization 方法对配置了预实例化属性的 Bean 进行预初始化过程，源码如下： ConﬁgurableListableBeanFactory 是一个接口，其 preInstantiateSingletons 方法由其子类 DefaultListableBeanFactory 提供。 (3)、DefaultListableBeanFactory 对配置 lazy-init 属性单态 Bean 的预实例化： 通过对 lazy-init 处理源码的分析，我们可以看出，如果设置了 lazy-init 属性，则容器在完成 Bean 定义的 注册之后，会通过 getBean 方法，触发对指定 Bean 的初始化和依赖注入过程，这样当应用第一次向容器索取所需的 Bean 时，容器不再需要对 Bean 进行初始化和依赖注入，直接从已经完成实例化和依赖注入的 Bean 中取一个现成的 Bean，这样就提高了第一次获取 Bean 的性能。 1.5.3 3、FactoryBean 的实现： 在 Spring 中，有两个很容易混淆的类：BeanFactory 和 FactoryBean。 BeanFactory：Bean 工厂，是一个工厂(Factory)，我们 SpringIOC 容器的最顶层接口就是这个 BeanFactory，它的作用是管理 Bean，即实例化、定位、配置应用程序中的对象及建立这些对象间的依 赖。 FactoryBean：工厂 Bean，是一个 Bean，作用是产生其他 bean 实例。通常情况下，这种 bean 没有什么 特别的要求，仅需要提供一个工厂方法，该方法用来返回其他 bean 实例。通常情况下，bean 无须自己 实现工厂模式，Spring 容器担任工厂角色；但少数情况下，容器中的 bean 本身就是工厂，其作用是产 生其它 bean 实例。 当用户使用容器本身时，可以使用转义字符”&”来得到 FactoryBean 本身，以区别通过 FactoryBean 产生的实例对象和 FactoryBean 对象本身。在 BeanFactory 中通过如下代码定义了该转义字符： StringFACTORY_BEAN_PREFIX="&"; 如果 myJndiObject 是一个 FactoryBean，则使用&myJndiObject 得到的是 myJndiObject 对象，而不是 myJndiObject 产生出来的对象。 (1).FactoryBean 的源码如下： (2).AbstractBeanFactory 的 getBean 方法调用 FactoryBean： 在前面我们分析 SpringIOC 容器实例化 Bean 并进行依赖注入过程的源码时，提到在 getBean 方法触发容 器实例化 Bean 的时候会调用 AbstractBeanFactory 的 doGetBean 方法来进行实例化的过程，源码如下： 在上面获取给定 Bean 的实例对象的 getObjectForBeanInstance 方法中，会调用 FactoryBeanRegistrySupport 类的 getObjectFromFactoryBean 方法，该方法实现了 Bean 工厂生产 Bean 实例对象。 Dereference(解引用)：一个在 C/C++中应用比较多的术语，在 C++中，”*”是解引用符号，而”&”是引用 符号，解引用是指变量指向的是所引用对象的本身数据，而不是引用对象的内存地址。 (3) 、 AbstractBeanFactory 生 产 Bean 实 例 对 象 ： AbstractBeanFactory 类中生产 Bean 实例对象的主要源码如下： 从上面的源码分析中，我们可以看出，BeanFactory 接口调用其实现类的 getObject 方法来实现创建 Bean 实例对象的功能。 (4).工厂 Bean 的实现类 getObject 方法创建 Bean 实例对象： FactoryBean 的实现类有非常多，比如：Proxy、RMI、JNDI、ServletContextFactoryBean 等等， FactoryBean 接口为 Spring 容器提供了一个很好的封装机制，具体的 getObject 有不同的实现类根据不同 的实现策略来具体提供，我们分析一个最简单的 AnnotationTestFactoryBean 的实现源码： 其他的 Proxy，RMI，JNDI 等等，都是根据相应的策略提供 getObject 的实现。这里不做一一分析，这已 经不是 Spring 的核心功能，有需要的时候再去深入研究。 1.5.4 4、BeanPostProcessor 后置处理器的实现： BeanPostProcessor 后置处理器是 SpringIOC 容器经常使用到的一个特性，这个 Bean 后置处理器是一个监听器，可以监听容器触发的 Bean 声明周期事件。后置处理器向容器注册以后，容器中管理的 Bean 就具备了接收 IOC 容器事件回调的能力。 BeanPostProcessor 的使用非常简单，只需要提供一个实现接口 BeanPostProcessor 的实现类，然后在 Bean 的配置文件中设置即可。 (1).BeanPostProcessor 的源码如下： 这两个回调的入口都是和容器管理的 Bean 的生命周期事件紧密相关，可以为用户提供在 SpringIOC 容器初始化 Bean 过程中自定义的处理操作。 (2).AbstractAutowireCapableBeanFactory 类对容器生成的 Bean 添加后置处理器： BeanPostProcessor 后置处理器的调用发生在 SpringIOC 容器完成对 Bean 实例对象的创建和属性的依赖注入完成之后，在对 Spring 依赖注入的源码分析过程中我们知道，当应用程序第一次调用 getBean 方法(lazy-init 预实例化除外)向 SpringIOC 容器索取指定 Bean 时触发 SpringIOC 容器创建 Bean 实例对象并进行依赖注入的过程，其中真正实现创建 Bean 对象并进行依赖注入的方法是 AbstractAutowireCapableBeanFactory 类的 doCreateBean 方法，主要源码如下： 从上面的代码中我们知道，为 Bean 实例对象添加 BeanPostProcessor 后置处理器的入口的是 initializeBean 方法。 (3).initializeBean 方法为容器产生的 Bean 实例对象添加 BeanPostProcessor 后置处理器： 同样在 AbstractAutowireCapableBeanFactory 类中，initializeBean 方法实现为容器创建的 Bean 实例对 象添加 BeanPostProcessor 后置处理器，源码如下： BeanPostProcessor 是一个接口，其初始化前的操作方法和初始化后的操作方法均委托其实现子类来实 现，在 Spring 中，BeanPostProcessor 的实现子类非常的多，分别完成不同的操作，如：AOP 面向切面 编程的注册通知适配器、Bean 对象的数据校验、Bean 继承属性/方法的合并等等，我们以最简单的 AOP 切面织入来简单了解其主要的功能。 (4).AdvisorAdapterRegistrationManager 在 Bean 对象初始化后注册通知适配器： AdvisorAdapterRegistrationManager 是 BeanPostProcessor 的一个实现类，其主要的作用为容器中管 理的 Bean 注册一个面向切面编程的通知适配器，以便在 Spring 容器为所管理的 Bean 进行面向切面编程时提供方便，其源码如下： 其他的 BeanPostProcessor 接口实现类的也类似，都是对 Bean 对象使用到的一些特性进行处理，或者 向 IOC 容器中注册，为创建的 Bean 实例对象做一些自定义的功能增加，这些操作是容器初始化 Bean 时自动触发的，不需要认为的干预。 1.5.5 5、SpringIOC 容器 autowiring 实现原理： SpringIOC 容器提供了两种管理 Bean 依赖关系的方式： a.显式管理：通过 BeanDeﬁnition 的属性值和构造方法实现 Bean 依赖关系管理。 b.autowiring：SpringIOC 容器的依赖自动装配功能，不需要对 Bean 属性的依赖关系做显式的声明，只 需要在配置好 autowiring 属性，IOC 容器会自动使用反射查找属性的类型和名称，然后基于属性的类型或者名称来自动匹配容器中管理的 Bean，从而自动地完成依赖注入。 通过对 autowiring 自动装配特性的理解，我们知道容器对 Bean 的自动装配发生在容器对 Bean 依赖注入 的过程中。在前面对 SpringIOC 容器的依赖注入过程源码分析中，我们已经知道了容器对 Bean 实例对象的属性注入的处理发生在 AbstractAutoWireCapableBeanFactory 类中的 populateBean 方法中，我们通 过程序流程分析 autowiring 的实现原理： (1).AbstractAutoWireCapableBeanFactory 对 Bean 实例进行属性依赖注入： 应用第一次通过 getBean 方法(配置了 lazy-init 预实例化属性的除外)向 IOC 容器索取 Bean 时，容器创建 Bean 实例对象，并且对 Bean 实例对象进行属性依赖注入，AbstractAutoWireCapableBeanFactory 的 populateBean 方法就是实现 Bean 属性依赖注入的功能，其主要源码如下： (2).SpringIOC 容器根据 Bean 名称或者类型进行 autowiring 自动依赖注入： 通过上面的源码分析，我们可以看出来通过属性名进行自动依赖注入的相对比通过属性类型进行自动依 赖注入要稍微简单一些，但是真正实现属性注入的是 DefaultSingletonBeanRegistry 类的 registerDependentBean 方法。 (3).DefaultSingletonBeanRegistry 的 registerDependentBean 方法对属性注入： 通过对 autowiring 的源码分析，我们可以看出，autowiring 的实现过程： a.对 Bean 的属性代调用 getBean 方法，完成依赖 Bean 的初始化和依赖注入。 b.将依赖 Bean 的属性引用设置到被依赖的 Bean 属性上。 c.将依赖 Bean 的名称和被依赖 Bean 的名称存储在 IOC 容器的集合中。 SpringIOC 容器的 autowiring 属性自动依赖注入是一个很方便的特性，可以简化开发时的配置，但是凡 是都有两面性，自动属性依赖注入也有不足，首先，Bean 的依赖关系在配置文件中无法很清楚地看出 来，对于维护造成一定困难。其次，由于自动依赖注入是 Spring 容器自动执行的，容器是不会智能判断的，如果配置不当，将会带来无法预料的后果，所以自动依赖注入特性在使用时还是综合考虑。 手写框架篇 框架分析 工程名称：spring-custom 工程目的：手写 spring 框架，实现 spring 框架核心功能：IoC 和 DI。