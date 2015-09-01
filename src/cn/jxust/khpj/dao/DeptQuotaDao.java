package cn.jxust.khpj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.khpj.model.DeptQuota;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class DeptQuotaDao extends BaseDao<DeptQuota>
{
	public List<DeptQuota> getByType(String deptType, String quotaType)
	{
		return findListCache("from DeptQuota where deptType = ? and quota.quotaType = ? and inused = 1", deptType, quotaType);
	}
	
	public PageData<DeptQuota> findPage(PageData<DeptQuota> pageData)
	{
		return findPage(pageData, "from DeptQuota order by deptType asc, quota.quotaType asc,inused desc", new Object[]{});
	}
}
