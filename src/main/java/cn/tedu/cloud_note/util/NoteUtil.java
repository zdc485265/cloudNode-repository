package cn.tedu.cloud_note.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;


public class NoteUtil {
	/*
	 * 利用UUID算法生成主键
	 */
	public static String createId(){
		UUID uuid=UUID.randomUUID();
		String id=uuid.toString();
		
		return id.replace("-","");
	}
	public static String md5(String msg) throws NoSuchAlgorithmException{
		//利用md5对msg处理
		MessageDigest md;
		
			md = MessageDigest.getInstance("MD5");
			byte[] input = msg.getBytes();
			byte[] output = md.digest(input);//将字节信息处理
			//将md5处理的output结果转成字符串
			String ret = 
				Base64.encodeBase64String(output);
			return ret;

	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(createId());
		System.out.println(md5(createId()));
	}
}



