/**
 * 
 */
package cn.jxust.khpj.service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.enums.ConDisClassEnum;
import cn.jxust.khpj.dao.CdReportDao;
import cn.jxust.khpj.model.CDReport;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

/**
 * @author Jephirus
 *
 */
@Service
@Transactional
public class CDReportService extends BaseService<CDReport>{

	@Override
	@Resource(name="cdReportDao")
	public void setBaseDao(BaseDao<CDReport> baseDao) {
		this.baseDao = baseDao;
		
	}
	
	public CdReportDao getCdReportDao()
	{
		return (CdReportDao)baseDao;
	}
	
	public Map<Integer, CDReport> getReport(String reportDate)
	{
		List<CDReport> list = getCdReportDao().findList("from CDReport where reportDate = ? order by conDisClass", reportDate);
		Map<Integer, CDReport> maps = new TreeMap<Integer, CDReport>();
		CDReport report = null;
		for(Integer type : ConDisClassEnum.toMap().keySet())
		{
			for(CDReport r : list)
			{
				if(type == r.getConDisClass())
				{
					report = maps.get(type);
					if(null == report)
					{
						report = new CDReport();
					}
					report.setCountyCount(report.getCountyCount() + r.getCountyCount());
					report.setTownCount(report.getTownCount() + r.getTownCount());
					report.setVillageCount(report.getVillageCount() + r.getVillageCount());
					
					report.setCouDisCount(report.getCouDisCount() + r.getCouDisCount());
					report.setTownDisCount(report.getTownDisCount() + r.getTownDisCount());
					report.setVilDisCount(report.getVilDisCount() + r.getVilDisCount());
					
					maps.put(type, report);
				}
			}
		}
		return maps;
	}
	
	public Map<Integer, CDReport> getMyReportMap(String deptId, String reportDate)
	{
		List<CDReport> list = getCdReportDao().findList("from CDReport where department.departmentId = ? and reportDate = ? order by conDisClass", deptId, reportDate);
		Map<Integer, CDReport> maps = new TreeMap<Integer, CDReport>();
		for(CDReport r : list)
		{
			maps.put(r.getConDisClass(), r);
		}
		return maps;
	}
	
	public List<CDReport> getMyReportList(String departmentId, String reportDate)
	{
		return getCdReportDao().findList("from CDReport where department.departmentId = ? and reportDate = ? order by conDisClass", departmentId, reportDate);
	}
	
	public List<Object> getCdReportsYears()
	{
		return getCdReportDao().findList("select distinct reportDate from CDReport order by reportDate desc");
	}
	
	public void saveOrUpdate(List<CDReport> cDReports)
	{
		for(CDReport cDReport : cDReports)
		{
			if(null == cDReport.getcDReportId() || "".equals(cDReport.getcDReportId().trim()))
			{
				getCdReportDao().save(cDReport);
			}
			else
			{
				getCdReportDao().update(cDReport);
			}
		}
	}

}
