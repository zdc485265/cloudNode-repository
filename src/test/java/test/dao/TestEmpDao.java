package test.dao;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.EmpDao;
import cn.tedu.cloud_note.entity.Emp;
import test.TestBase;

public class TestEmpDao extends TestBase{
	private EmpDao empDao;
	
	@Before
	public void init(){
		empDao=getContext().getBean("empDao",EmpDao.class);
	}
	
	@Test
	public void Test(){
		Emp emp=new Emp();
		emp.setAge(99);
		emp.setName("liubei");
		empDao.save(emp);
		int id=emp.getId();
		System.out.println("emp:"+emp+"id:"+id);
	}
}
