package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Share;

public interface ShareDao {
	public int share(Share share);
	public List<Share> findLikeTitle(Map params);
}
