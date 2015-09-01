package cn.jxust.khpj.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.khpj.dao.DeptQuotaDao;
import cn.jxust.khpj.model.DeptQuota;
import cn.jxust.khpj.model.Quota;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class DeptQuotaService extends BaseService<DeptQuota>
{
	@Override
	@Resource(name="deptQuotaDao")
	public void setBaseDao(BaseDao<DeptQuota> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public DeptQuotaDao getDeptQuotaDao()
	{
		return (DeptQuotaDao)baseDao;
	}
	
	public void save(DeptQuota dq, Quota quota)
	{
		dq.setQuota(quota);
		save(dq);
	}
	
	public void update(DeptQuota dq, Quota quota)
	{
		dq.setQuota(quota);
		update(dq);
	}
	
	public PageData<DeptQuota> findPage(PageData<DeptQuota> pageData)
	{
		return getDeptQuotaDao().findPage(pageData);
	}
	
	public List<DeptQuota> findByType(String deptType, String quotaType)
	{
		return getDeptQuotaDao().getByType(deptType, quotaType);
	}
	
	/**
	 * 启用
	 * @param id
	 */
	public void enable(String id)
	{
		DeptQuota dq = getDeptQuotaDao().find(id);
		dq.setInused(1);
		update(dq);
	}
	
	/**
	 * 禁用
	 * @param id
	 */
	public void disable(String id)
	{
		DeptQuota dq = getDeptQuotaDao().find(id);
		dq.setInused(0);
		update(dq);
	}
}
