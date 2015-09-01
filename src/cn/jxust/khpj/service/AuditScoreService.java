package cn.jxust.khpj.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.model.Department;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.enums.AuditScoreState;
import cn.jxust.khpj.dao.AuditScoreDao;
import cn.jxust.khpj.model.AuditScore;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class AuditScoreService extends BaseService<AuditScore> {
	@Override
	@Resource(name = "auditScoreDao")
	public void setBaseDao(BaseDao<AuditScore> baseDao) {
		this.baseDao = baseDao;
	}

	public AuditScoreDao getAuditScoreDao() {
		return (AuditScoreDao) baseDao;
	}
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private ScoreService scoreService;

	public List<AuditScore> getByYearScoreType(String year, String deptType, String scoreType) 
	{
		return findList("from AuditScore where scoreYear=? and scoreType=? and department.departmentType=?", year, scoreType, deptType);
	}
	
	public Map<String, AuditScore> getMapByYearScoreType(String year, String deptType, String scoreType) 
	{
		List<AuditScore> scores = getByYearScoreType(year, deptType, scoreType);
		Map<String, AuditScore> scoremap = new HashMap<String, AuditScore>();
		for(AuditScore score : scores)
		{
			String key = score.getDepartment().getDepartmentId();
			scoremap.put(key, score);
		}
		return scoremap;
	}

	/**
	 * 通过部门和分值类型检索分值
	 * 
	 * @param deptId
	 * @param month
	 * @return
	 */
	public List<AuditScore> getAuditScoreByDept(String deptId, String scoreType) {
		return getAuditScoreDao().findAuditScoreByDept(deptId, scoreType);
	}

	/**
	 * 保存分值 参数：部门、分值类型和对应分值
	 */
	public void save(String[] departmentIds, String year, String deptType, String scoreType, Double[] auditScoreValues) 
	{
		List<AuditScore> deptYearScoreType = getByYearScoreType(year, deptType, scoreType);
		if(null == deptYearScoreType || deptYearScoreType.size() == 0)
		{
			AuditScore auditScore = null;
			Department dept = null;
			for (int i = 0; i < departmentIds.length; i++) 
			{
				auditScore = new AuditScore();
				dept = new Department();
				dept.setDepartmentId(departmentIds[i]);
				auditScore.setDepartment(dept);
				auditScore.setScoreType(scoreType);
				auditScore.setScoreYear(year);
				auditScore.setPublishFlag("0");
				auditScore.setScoreValue(auditScoreValues[i]);
				auditScore.setAuditStat(AuditScoreState.UNAUDITED.getIndex());
				getAuditScoreDao().save(auditScore);
			}
		}
		else
		{
			for(AuditScore as : deptYearScoreType)
			{
				for(int i=0 ; i<departmentIds.length ; i++)
				{
					if(as.getDepartment().getDepartmentId().equals(departmentIds[i]) && auditScoreValues[i] != 0d)
					{
						as.setScoreValue(auditScoreValues[i]);
						as.setAuditStat(AuditScoreState.UNAUDITED.getIndex());
						as.setRefuseInfo("");
						getAuditScoreDao().update(as);
					}
				}
			}
		}
	}
	
	/**
	 * 领导审核半年及全年分值，通过为1，拒绝为2 通过为3，拒绝为4.
	 * @param auditScoreId
	 * @param auditStat
	 */
	public void auditTheScore( String auditScoreId, String auditStat, String refuseInfo)
	{
		AuditScore auditScore = getAuditScoreDao().find(auditScoreId);
		auditScore.setAuditStat(auditStat);
		if(auditStat.equals(AuditScoreState.REFUSE.getIndex()) || auditStat.equals(AuditScoreState.VICE_REFUSE.getIndex()))
		{
			auditScore.setRefuseInfo(refuseInfo);
		}
		getAuditScoreDao().update(auditScore);
		
//		if(auditStat.equals(AuditScoreState.PASS.getIndex()))
//		{
//			//更新部门得分
//			Department dept = departmentService.find(auditScore.getDepartment().getDepartmentId());
//			double totalScore = dept.getTotalScore();
//			dept.setTotalScore(totalScore + auditScore.getScoreValue());
//			departmentService.update(dept);
//		}
	}
	
	/**
	 * 副书记批量审核通过
	 * @param type
	 */
	public void viceAuditScoreBatch(String deptType, String scoreType ,String year)
	{
		getAuditScoreDao().auditScoreBatch(AuditScoreState.VICE_PASS.getIndex(), AuditScoreState.UNAUDITED.getIndex(), deptType, scoreType, year);
	}
	
	/**
	 * 书记批量审核通过
	 * @param type
	 */
	public void auditScoreBatch(String deptType, String scoreType, String year)
	{
		getAuditScoreDao().auditScoreBatch(AuditScoreState.PASS.getIndex(), AuditScoreState.VICE_PASS.getIndex(), deptType, scoreType, year);
	}
	
	public Double findSumScoreByYear(String year, String departmentId)
	{
		Double total = getAuditScoreDao().findSumScoreByYear(year, departmentId);
		if(null == total)
		{
			return 0.0;
		}
		return total;
	}
	
	public void publishAuditScore(String scoreType, String year)
	{
		getAuditScoreDao().publishAuditScore(scoreType, year);
		
		//更新部门上月得分和当年总分
		List<Department> allDept = departmentService.getAll();
		Double monthValue = 0.0;//每月分值
		Double yearValue = 0.0; //年度分值，包括半年及全年分值，用于得出总分值
		BigDecimal b = null;
		for(Department dept : allDept){
			monthValue = scoreService.findSumScoreByYear(year, dept.getDepartmentId());// 获取年度总分值。
			yearValue = getAuditScoreDao().findSumScoreByYear(year, dept.getDepartmentId());
			//保留两位小数，四舍五入
			b = new BigDecimal(monthValue + yearValue).setScale(2, BigDecimal.ROUND_HALF_UP);
			dept.setTotalScore(b.doubleValue());
			departmentService.update(dept);
		}
	}
	
}
