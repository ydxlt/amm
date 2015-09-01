package cn.jxust.khpj.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.khpj.dao.MachineAccountDao;
import cn.jxust.khpj.model.MachineAccount;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class MachineAccountService extends BaseService<MachineAccount>{

	@Override
	@Resource(name="machineAccountDao")
	public void setBaseDao(BaseDao<MachineAccount> baseDao) {
		this.baseDao = baseDao;
	}
	
	public MachineAccountDao getMachineAccountDao(){
		return (MachineAccountDao)baseDao;
	}

	public PageData<MachineAccount> getReport(PageData<MachineAccount> pageData, MachineAccount entity, String deptId, String month)
	{
		if(null == deptId || "".equals(deptId.trim()))
		{
			return getMachineAccountDao().findPage(pageData, "from MachineAccount where reportDate=? order by reportDate desc, department.ordNum", month);
		}
		else
		{
			return getMachineAccountDao().findPage(pageData, "from MachineAccount where reportDate=? and department.departmentId = ? order by reportDate desc, department.ordNum, department.departmentName", month, deptId);
		}
	}
	
	public PageData<MachineAccount> getReport(PageData<MachineAccount> pageData, String deptId, String month)
	{
		return getMachineAccountDao().findPage(pageData, "from MachineAccount where department.departmentId = ? and reportDate = ?", deptId, month);
	}
	
	public List<MachineAccount> getReport(String deptId, String month)
	{
		return getMachineAccountDao().findList("from MachineAccount where department.departmentId = ? and reportDate = ?", deptId, month);
	}
	
	public MachineAccount getReportById(String id){
		return getMachineAccountDao().find(id);
	}
	
	public List<Object> getYears(String deptId)
	{
		return getMachineAccountDao().findYears(deptId);
	}
	
	public List<Object> getYears()
	{
		return getMachineAccountDao().findYears();
	}
	
	public List<MachineAccount> findMachineAccountByMonth(String month){
		return getMachineAccountDao().findMachineAccountByMonth(month);
	}

}
