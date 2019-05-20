package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("shareService")
@Transactional
public class ShareServiceImpl implements ShareService{
	@Resource
	private ShareDao shareDao;

	public NoteResult<Share> share(String title,String body,String noteId) {
		Share share=new Share();
		share.setCn_note_id(noteId);
		String shareId=NoteUtil.createId();
		share.setCn_share_id(shareId);
		share.setCn_share_title(title);
		share.setCn_share_body(body);
		
		shareDao.share(share);
		//ģ���쳣
		//String  str=null;
		//str.length();
		
		NoteResult<Share> result=new NoteResult<Share>();
		result.setStatus(0);
		result.setMsg("����ɹ�");
		result.setData(share);
		return result;
	}

	public NoteResult<List<Share>> searchNote(String keyword,int page) {
		
		String title="%"+keyword+"%";
		int begin=(page-1)*3;  //����ץȡ��¼�����
		Map<String,Object> params=new HashMap();
		params.put("title",title);
		params.put("begin",begin);
		//ģ����ѯ
		List<Share> list=shareDao.findLikeTitle(params);
		//�������ؽ��
		NoteResult<List<Share>> result=new NoteResult<List<Share>>();
		result.setStatus(0);
		result.setMsg("�������");
		result.setData(list);
		return result;
	}
	
}
