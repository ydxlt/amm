package cn.jxust.sms.dao;

import org.springframework.stereotype.Repository;

import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.sms.model.SMS;

@Repository(value="smsDao")
public class SMSDao extends BaseDao<SMS>
{
	
}
