/**
 * 
 */
package cn.jxust.khpj.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.khpj.dao.BpReportDao;
import cn.jxust.khpj.model.BPReport;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

/**
 * @author Jephirus
 *
 */
@Service
@Transactional
public class BPReportService extends BaseService<BPReport>{

	@Override
	@Resource(name="bpReportDao")
	public void setBaseDao(BaseDao<BPReport> baseDao) {
		this.baseDao = baseDao;
	}
	
	public BpReportDao getBPReportDao()
	{
		return (BpReportDao)baseDao;
	}
	
	public PageData<BPReport> getReport(PageData<BPReport> pageData)
	{
		return getBPReportDao().findPage(pageData, "from BPReport order by bPReportId desc");
	}
	
	public PageData<BPReport> getReport(PageData<BPReport> pageData, String deptId)
	{
		return getBPReportDao().findPage(pageData, "from BPReport where department.departmentId = ?", deptId);
	}

	public BPReport getReportById(String id){
		return getBPReportDao().find(id);
	}
	
	public List<BPReport> getBydeptAndYear(String departmentId, String year){
		return getBPReportDao().findByDeptAndYear(departmentId, year);
	}
}
