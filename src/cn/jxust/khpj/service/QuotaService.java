package cn.jxust.khpj.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.khpj.dao.QuotaDao;
import cn.jxust.khpj.model.Quota;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class QuotaService extends BaseService<Quota>
{
	@Override
	@Resource(name="quotaDao")
	public void setBaseDao(BaseDao<Quota> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public QuotaDao getQuotaDao()
	{
		return (QuotaDao)baseDao;
	}
	
	public List<Quota> getAll()
	{
		return getQuotaDao().findAll();
	}
}
