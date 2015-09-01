package cn.jxust.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
import cn.jxust.utils.DateUtils;
import cn.jxust.web.dao.MessagesDao;
import cn.jxust.web.model.Messages;

@Service
@Transactional
public class MessagesService extends BaseService<Messages>
{
	@Override
	@Resource(name="messagesDao")
	public void setBaseDao(BaseDao<Messages> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public MessagesDao getMessagesDao()
	{
		return (MessagesDao)baseDao;
	}
	
	/**
	 * 后台回复时，查询所有留言
	 * @param pageData
	 * @return
	 */
	public PageData<Messages> getAll(PageData<Messages> pageData)
	{
		return getMessagesDao().findPage(pageData, "from Messages order by replyFlag, ltime desc");
	}
	
	public void save(Messages message)
	{
		message.setLtime(DateUtils.getCurrentTime());
		message.setReplyFlag("0");
		getMessagesDao().save(message);
	}
	
	public void update(Messages message)
	{
		Messages m = getMessagesDao().find(message.getMessageId());
		m.setRecontent(message.getRecontent());
		m.setReplyor(message.getReplyor());
		m.setReplyFlag("1");
		getMessagesDao().update(m);
	}
	
	public void delete(String[] messageIds)
	{
		getMessagesDao().delete(messageIds);
	}
	
	public PageData<Messages> getReplys(PageData<Messages> pageData)
	{
		return getMessagesDao().findPage(pageData, "from Messages where replyFlag='1' order by ltime desc");
	}
	
	
}
