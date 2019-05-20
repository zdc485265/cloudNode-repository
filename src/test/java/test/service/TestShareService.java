package test.service;

import org.junit.Before;
import org.junit.Test;

import test.TestBase;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.service.ShareService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestShareService extends TestBase{
	ShareService shareService;
	
	@Before
	public void init(){
		shareService=super.getContext().getBean("shareService",ShareService.class);
	}
	@Test
	public void test(){
		String noteId="123";
		String title="456";
		String body="789";
		NoteResult<Share> result=result=shareService.share(title,body,noteId);
		
		
		System.out.println(result);
	}
}
