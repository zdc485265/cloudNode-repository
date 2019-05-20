package cn.tedu.cloud_note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;


public class NoteUtil {
	/*
	 * ����UUID�㷨��������
	 */
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		String id=uuid.toString();
		
		return id.replace("-","");
	}
	public static String md5(String msg) throws NoSuchAlgorithmException{
		//����md5��msg����
		MessageDigest md;
		
			md = MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();
			byte[] output = md.digest(input);//���ֽ���Ϣ����
			//��md5�����output���ת���ַ���
			String ret = 
				Base64.encodeBase64String(output);
			return ret;

	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(createId());
		System.out.println(md5(createId()));
	}
}



