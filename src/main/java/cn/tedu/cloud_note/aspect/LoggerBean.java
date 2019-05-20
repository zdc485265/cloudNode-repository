package cn.tedu.cloud_note.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/**
 * #Spring AOP
##AOP����
aspect oriented programming
����������
##��ʲô��
����ͬ��ҵ����Ӵ�ͳҵ�����г��������������װ��
Ȼ�������õ���ʽ���й���

##Ϊʲô��
�����ڲ��޸�ԭ���߼����������£���ϵͳ׷�ӹ��ܡ�

##AOP���Ͱ���
׷���������
�쳣��־��¼

������
-Ҫ����ÿ��controller����ִ��ǰ�����׮��Ϣ

##�����
����ָ��Ŀ������ķ���
-�����޶����ʽ
	���Ը�ĳ������в��ַ���׷�ӹ�ͬ����
	execution(���η� �������� ��������(����) �쳣�׳�)
	//ƥ�䵽add��ͷ�����з���
	execution(* add*(..))
	//ƥ��UserService���µ����з���
	execution(* cn.tedu.UserService.*(..))
	//ƥ��service��������������з���
 	execution(* cn.tedu.service.*.*(..))
	//ƥ��service�����Ӱ��µ����з���
	execution(* cn.tedu.service..*(..))

-�����޶����ʽ
���Ը�ĳ����������з���׷�ӹ���
within(����)
//ƥ��UserService��������з���
within(cn.tedu.service.UserService)
//ƥ��ĳ����������������з���
within(cn.tedu.service.*)
//ƥ��service�����Ӱ��µ����з���
within(cn.tedu.service..*)

-bean�����޶����ʽ
	���Ը�ĳ����������еķ���׷�ӹ���
	bean(id��)
	//ƥ��id����userService����������з���
	bean(userService)
	//ƥ����Service��β��������������з���
	bean(*Service)
	
##֪ͨ
����ָ�������ʱ��
spring�ṩ������֪ͨ����
try{
	ʹ��ǰ��֪ͨ<aop:before>
//ִ�е�Ŀ�귽��
	����֪ͨ<aop:after-returning>
}catch{
	�쳣֪ͨ<aop:after-throwing>
}finally{
	����֪ͨ<aop:after>
}
@around=ǰ�üӺ���֪ͨ
���棺׷��ɶ���ܣ�������װ�Ĵ���
����㣺��˭������Controller
֪ͨ�� ɶ�ʺ��У�ǰ��/����/����

##��̬����
AOPԭ��ʹ�ö�̬������
���Դ���һ���µ����ͣ�Ȼ��ʵ��ע��

spring�����ֶ�̬�ӿ�
����Ŀ��ӿ�
����Ŀ����


##AOPע������
##ע����
@Component  ��Ӧ��<bean>������
@Aspect		<aop:aspect ref="loggerBean">
@Before		<aop:Before pointcut=within()>

������
-Ҫ�󣺵�ϵͳ����service�쳣�����쳣��Ϣд����־�ļ�
���棺���쳣��Ϣд���ļ�
����㣺afterthrowing

#Mybatis����ӳ��
##ʲô�ǹ���ӳ�䣿
�����ݿ��е��й�����ϵ�ĵı���ʵ��������õķ�ʽ���ֳ���

##������ϵ��
- ������������
- �����������
class User{
	private List<Book> books;
}
class Book{
	private User user;
}

##ʲôʱ���ã�
ҵ��Ҫ�����ݿ���й�����ѯ��ʱ��
����ͨ��һ��SQL�����ɹ�����ѯ��Ҳ����ͨ������sql�����й�����ѯ

##������ͨ��userId��ѯ�û���Ϣ�͹����ıʼǱ���Ϣ
1.User ʵ����
2.����Dao�ӿڣ�����Mapper�ļ�
3.�����������֤��ѯ���

2��sql��䣺���򵥣������÷�������DB���ν���
1��sql��䣺��临�ӣ������ü򵥣������ݿ�һ�ν���

##����:ͨ����ѯ�ʼ���Ϣ�������û���Ϣ
��һ����ѯ���ʵ��

#�������ֶδ���
�����ݿ�ʹ�������л���������Ϊ����ֵʱ�������insertִ�к��������IDֵ
-mysql
	create table t_emp(id int primary key auto_increment,name varchar(20),age int)


Spring �������
ʲôʱ����
����Ϊ�˱���ҵ��������ԣ���ִ�е�һ������sql���
����
�������е�sql�������ύ��ع��������������
ʲôʱ���ã�
������service�ķ�����

����عˣ�
-oracle��commit/rollback��DML������
-JDBC���Զ�commit

ͨ��Spring��������й���
��ô�ã�
1.����spring�ļ�
2.ʹ��@Transactional���

##�ɶ���д
select����ʱ�����Բ���ֻ������
@Transaction��readOnly=true��
##�ع�����
Ĭ��RuntimeException�ع��������쳣���ع�
@Transaction��rollbackFor=IOException.class��

##��������
Ĭ�����ͣ�REQUIRED--֧�ֵ�ǰ���������ǰû�����񣬾��½�һ�������������
��ѡ��
SUPPORTS--֧�ֵ�ǰ���������ǰû�����񣬾��Է�����ʽִ��
MANDATORY--֧�ֵ�ǰ���������ǰ��û�����񣬾��׳��쳣
REQUIRES_NEW--�½����������ǰ�������񣬰ѵ�ǰ�������
NOT_SUPPORTED--�Է�����ʽִ�в����������ǰ�������񣬾Ͱѵ�ǰ�������
##��������
Ĭ�����ԣ�READ_COMMITED
������񲢷����д���
���/�ö�
 * @author ��
 *
 */
@Component
@Aspect
public class LoggerBean {
	@Before("within(cn.tedu.cloud_note.controller..*)")
	public void logController(){
		System.out.println("AOPע��Controller��");
	}
	@Before("within(cn.tedu.cloud_note.service..*)")
	public void logService(){
		System.out.println("AOPע��Service��");
	}
}
