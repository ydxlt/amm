/**
 * 
 */
package cn.jxust.khpj.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.khpj.dao.RegisterReportDao;
import cn.jxust.khpj.model.RegisterReport;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

/**
 * @author Jephirus
 *
 */
@Service
@Transactional
public class RegisterReportService extends BaseService<RegisterReport>{

	@Override
	@Resource(name="registerReportDao")
	public void setBaseDao(BaseDao<RegisterReport> baseDao) {
		this.baseDao = baseDao;
		
	}
	
	public RegisterReportDao getRegisterReportDao()
	{
		return (RegisterReportDao)baseDao;
	}
	
	public PageData<RegisterReport> getReport(PageData<RegisterReport> pageData)
	{
		return getRegisterReportDao().findPage(pageData, "from RegisterReport order by registerReportId");
	}
	
	public PageData<RegisterReport> getReport(PageData<RegisterReport> pageData, String deptId)
	{
		return getRegisterReportDao().findPage(pageData, "from RegisterReport where department.departmentId = ?", deptId);
	}
	
	public RegisterReport getReportById(String id){
		return getRegisterReportDao().find(id);
	}
	
	public List<RegisterReport> getBydeptAndYear(String departmentId, String year){
		return getRegisterReportDao().findByDeptAndYear(departmentId, year);
	}

}
