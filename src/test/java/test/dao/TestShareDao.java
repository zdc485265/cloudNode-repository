package test.dao;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Share;
import test.TestBase;

public class TestShareDao extends TestBase{
	private ShareDao shareDao;
	@Before
	public void init(){
		shareDao=super.getContext().getBean("shareDao",ShareDao.class);
	}
	
	@Test
	public void test(){
		Share share=new Share();
		share.setCn_note_id("12");
		share.setCn_share_body("34");
		share.setCn_share_id("56");
		share.setCn_share_title("78");
		int n=shareDao.share(share);
		System.out.println(n);
	}
}
