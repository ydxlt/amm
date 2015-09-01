package cn.jxust.sms.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
import cn.jxust.sms.dao.SMSDao;
import cn.jxust.sms.model.SMS;

@Service
@Transactional
public class SMSService extends BaseService<SMS>
{
	
	public SMSDao getMessageDao(){
		return (SMSDao) baseDao;
	}

	@Override
	@Resource(name="smsDao")
	public void setBaseDao(BaseDao<SMS> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public PageData<SMS> getAll(PageData<SMS> pageData)
	{
		return getMessageDao().findPage(pageData, "from SMS");
	}
}
