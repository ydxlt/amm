/**
 * 
 */
package cn.jxust.khpj.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.khpj.dao.AppraisalReportDao;
import cn.jxust.khpj.model.AppraisalReport;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

/**
 * @author Jephirus
 *
 */
@Service
@Transactional
public class AppraisalReportService extends BaseService<AppraisalReport>{

	@Override
	@Resource(name="appraisalReportDao")
	public void setBaseDao(BaseDao<AppraisalReport> baseDao) {
		this.baseDao = baseDao;
		
	}
	
	public AppraisalReportDao getAppraisalReportDao()
	{
		return (AppraisalReportDao)baseDao;
	}
	
	public PageData<AppraisalReport> getReport(PageData<AppraisalReport> pageData)
	{
		return getAppraisalReportDao().findPage(pageData, "from AppraisalReport order by appraisalReportId desc");
	}
	
	public PageData<AppraisalReport> getReport(PageData<AppraisalReport> pageData, String deptId)
	{
		return getAppraisalReportDao().findPage(pageData, "from AppraisalReport where department.departmentId = ?", deptId);
	}
	
	public AppraisalReport getReportById(String id)
	{
		return getAppraisalReportDao().find(id);
	}
	
	public List<AppraisalReport> getByDeptAndYear(String departmentId, String year){
		return getAppraisalReportDao().findByDeptandYear(departmentId, year);
	}

}
