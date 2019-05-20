package cn.tedu.cloud_note.aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component  //ɨ�赽spring����
@Aspect		//��������Ϊ�������
public class ExceptionBean {
	//e��Ŀ������׳������쳣����
	@AfterThrowing(throwing="e",pointcut="within(cn.tedu.cloud_note.service..*)")
	public void execute(Exception e){
		//���쳣��Ϣ�����ļ�
		System.out.println(0);
		try {
			System.out.println(1);
			FileWriter fw=
					new FileWriter("D:\\note_error.txt");
			PrintWriter pw=new PrintWriter(fw);
			System.out.println(2);
			//����pw����д���쳣��Ϣ
			Date time=new Date();
			SimpleDateFormat sdf=
					new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
			String timeStr=sdf.format(time);
			pw.println("************************");
			pw.println("*�쳣���ͣ�"+ e);
			pw.println("�쳣ʱ�䣺"+timeStr);
			pw.println("***********************�쳣��ϸ��Ϣ*******************");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (IOException ex) {
			System.out.println("��¼�쳣ʧ��");
		}
	}
}
