package cn.tedu.cloud_note.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * #Spring AOP
##AOP概念
aspect oriented programming
面向切面编程
##是什么？
将共同的业务处理从传统业务处理中抽离出来，单独封装，
然后以配置的形式进行关联

##为什么？
可以在不修改原有逻辑代码的情况下，给系统追加功能。

##AOP典型案例
追加事务控制
异常日志记录

案例：
-要求：在每个controller方法执行前输出打桩信息

##切入点
用于指定目标组件的方法
-方法限定表达式
	可以给某个组件中部分方法追加共同功能
	execution(修饰符 返回类型 方法名称(参数) 异常抛出)
	//匹配到add开头的所有方法
	execution(* add*(..))
	//匹配UserService包下的所有方法
	execution(* cn.tedu.UserService.*(..))
	//匹配service包下所有类的所有方法
 	execution(* cn.tedu.service.*.*(..))
	//匹配service包及子包下的所有方法
	execution(* cn.tedu.service..*(..))

-类型限定表达式
可以给某个组件的所有方法追加功能
within(类型)
//匹配UserService组件下所有方法
within(cn.tedu.service.UserService)
//匹配某个包下所有类的所有方法
within(cn.tedu.service.*)
//匹配service包及子包下的所有方法
within(cn.tedu.service..*)

-bean名称限定表达式
	可以给某个组件中所有的方法追加功能
	bean(id名)
	//匹配id等于userService的组件的所有方法
	bean(userService)
	//匹配以Service结尾的所有组件的所有方法
	bean(*Service)
	
##通知
用于指定切入的时机
spring提供了五种通知类型
try{
	使用前置通知<aop:before>
//执行的目标方法
	后置通知<aop:after-returning>
}catch{
	异常通知<aop:after-throwing>
}finally{
	最终通知<aop:after>
}
@around=前置加后置通知
切面：追加啥功能？单独封装的代码
切入点：切谁？所有Controller
通知： 啥适合切？前置/后置/环绕

##动态代理
AOP原理：使用动态代理技术
可以创建一个新的类型，然后实现注入

spring有两种动态接口
基于目标接口
基于目标类


##AOP注解配置
##注解标记
@Component  起到应以<bean>的作用
@Aspect		<aop:aspect ref="loggerBean">
@Before		<aop:Before pointcut=within()>

案例：
-要求：当系统发生service异常，将异常信息写入日志文件
切面：将异常信息写入文件
切入点：afterthrowing

#Mybatis关联映射
##什么是关联映射？
将数据库中的有关联关系的的表，以实体对象引用的方式体现出来

##关联关系：
- 关联单个对象
- 关联多个对象
class User{
	private List<Book> books;
}
class Book{
	private User user;
}

##什么时候用？
业务要对数据库进行关联查询的时候
可以通过一条SQL语句完成关联查询，也可以通过两条sql语句进行关联查询

##案例：通过userId查询用户信息和关联的笔记本信息
1.User 实体类
2.定义Dao接口，配置Mapper文件
3.定义测试类验证查询结果

2个sql语句：语句简单，但配置繁琐，与DB两次交互
1个sql语句：语句复杂，但配置简单，与数据库一次交互

##案例:通过查询笔记信息，关联用户信息
用一条查询语句实现

#主键的字段处理
在数据库使用自增列或者序列作为主键值时，如何在insert执行后，立即获得ID值
-mysql
	create table t_emp(id int primary key auto_increment,name varchar(20),age int)


Spring 事务管理
什么时事务？
程序为了保护业务的完整性，而执行的一个或多个sql语句
管理？
对事务中的sql语句进行提交或回滚，叫做事务管理
什么时候用？
作用于service的方法上

事务回顾：
-oracle：commit/rollback（DML操作）
-JDBC：自动commit

通过Spring对事务进行管理
怎么用？
1.配置spring文件
2.使用@Transactional标记

##可读可写
select操作时，可以采用只读事务
@Transaction（readOnly=true）
##回滚特性
默认RuntimeException回滚，其他异常不回滚
@Transaction（rollbackFor=IOException.class）

##传播特性
默认类型：REQUIRED--支持当前事务，如果当前没有事务，就新建一个事务。这是最常见
的选择。
SUPPORTS--支持当前事务。如果当前没有事务，就以非事务方式执行
MANDATORY--支持当前事务，如果当前尚没有事务，就抛出异常
REQUIRES_NEW--新建事务，如果当前存在事务，把当前事务挂起
NOT_SUPPORTED--以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
##隔离特性
默认属性：READ_COMMITED
针对事务并发进行处理
脏读/幻读
 * @author 邹
 *
 */
@Component
@Aspect
public class LoggerBean {
	@Before("within(cn.tedu.cloud_note.controller..*)")
	public void logController(){
		System.out.println("AOP注入Controller！");
	}
	@Before("within(cn.tedu.cloud_note.service..*)")
	public void logService(){
		System.out.println("AOP注入Service！");
	}
}
