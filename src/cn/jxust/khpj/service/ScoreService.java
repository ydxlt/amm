package cn.jxust.khpj.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.model.Department;
import cn.jxust.base.service.DepartmentService;
import cn.jxust.enums.ScoreState;
import cn.jxust.khpj.dao.ScoreDao;
import cn.jxust.khpj.model.Quota;
import cn.jxust.khpj.model.Score;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
import cn.jxust.utils.DateUtils;

@Service
@Transactional
public class ScoreService extends BaseService<Score>
{
	@Resource
	private DepartmentService departmentService;
	@Resource
	private AuditScoreService auditScoreService;
	
	@Override
	@Resource(name = "scoreDao")
	public void setBaseDao(BaseDao<Score> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public ScoreDao getScoreDao()
	{
		return (ScoreDao)baseDao;
	}

	public List<Score> getScoreByTypeAndMonth(String type, String month)
	{
		return getScoreDao().findScoreByTypeAndMonth(type, month);
	}
	
	/**
	 * 组合以score的deptId和quotaId为键值的map分值
	 * @param scores
	 * @return
	 */
	public Map<String, Score> getScoreMapByTypeAndMonth(String type, String month)
	{
		List<Score> scores = getScoreByTypeAndMonth(type, month);
		Map<String, Score> scoremap = new HashMap<String, Score>();
		for(Score score : scores)
		{
			String key = score.getDepartment().getDepartmentId() + "-" + score.getQuota().getQuotaId();
			scoremap.put(key, score);
		}
		return scoremap;
	}

	public List<Score> getQuotaByDept(String deptId, String month)
	{
		return getScoreDao().findQuotaByDept(deptId, month);
	}
	
	/**
	 * 组合以score的deptId和quotaId为键值的map分值
	 * @param scores
	 * @return
	 */
	public Map<String, Score> getScoreValueByDeptAndMonth(String deptId, String month)
	{
		List<Score> scores = getQuotaByDept(deptId, month);
		Map<String, Score> scoremap = new HashMap<String, Score>();
		for(Score score : scores)
		{
			String key = score.getDepartment().getDepartmentId() + "-" + score.getQuota().getQuotaId();
			scoremap.put(key, score);
		}
		return scoremap;
	}

	/**
	 * 保存分值
	 */
	public void save(String departmentId, String[] quotaIds, String month, Double[] scores)
	{
		List<Score> depMonthScore = getQuotaByDept(departmentId, month);   //如果该list为空，说明为新增数据
		if(null == depMonthScore || depMonthScore.size() == 0)
		{
			Score score = null;
			Department dept = null;
			Quota quota = null;
			for (int i = 0; i < scores.length; i++) 
			{
				score = new Score();
				dept = new Department();
				dept.setDepartmentId(departmentId);
				score.setDepartment(dept);
				score.setScoreMonth(month);
				//标示未提交数据
				if(scores[i] != null)
				{
					score.setScoreValue(scores[i]);
				}
				else
				{
					score.setScoreValue(0d);
				}
				quota = new Quota();
				quota.setQuotaId(quotaIds[i]);
				score.setQuota(quota);
				score.setAuditStat(ScoreState.UNAUDITED.getIndex());
				getScoreDao().save(score);
			}
		}
		else                                     //否则为更新打分值
		{
			for(Score score : depMonthScore)
			{
				for(int i=0 ; i<quotaIds.length ; i++)
				{
					if(score.getQuota().getQuotaId().equals(quotaIds[i]))
					{
						if(!ScoreState.PASS.getIndex().equals(score.getAuditStat()))
						{
							score.setAuditStat(ScoreState.UNAUDITED.getIndex());
							score.setRefuseInfo("");
							if(scores[i] != null)
							{
								score.setScoreValue(scores[i]);
							}
							else
							{
								score.setScoreValue(0d);
							}
							getScoreDao().update(score);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 领导审核分值，通过为1，未通过为2.
	 * @param auditScoreId
	 * @param auditStat
	 */
	public void auditTheScore(String scoreId, String auditStat, String refuseInfo)
	{
		Score score = getScoreDao().find(scoreId);
		score.setAuditStat(auditStat);
		score.setAuditDate(DateUtils.getCurrentTime());
		if(auditStat.equals(ScoreState.REFUSE.getIndex()))
		{
			score.setRefuseInfo(refuseInfo);
		}
		getScoreDao().update(score);
		
//		if(auditStat.equals(ScoreState.PASS.getIndex()))
//		{
//			double value = 0;
//			//更新部门得分
//			Department dept = departmentService.find(score.getDepartment().getDepartmentId());
//			String lastMonth = DateUtils.getLastMonth();// 获取上月时间
//			if(score.getScoreMonth().equals(lastMonth)) // 如当前打分的指标时间是上月
//			{
//				value = getScoreDao().findSumScoreByLastMonth(lastMonth, score.getDepartment().getDepartmentId()); // 更新部门的上得分
//				dept.setLastMonthScore(value);
//			}
//			value = getScoreDao().findSumScoreByYear(DateUtils.getCurrentYear(), score.getDepartment().getDepartmentId());// 获取年度总分值。
//			dept.setTotalScore(value);
//			departmentService.update(dept);
//		}
	}
	
	/**
	 * 副书记批量审核通过
	 * @param deptType
	 */
	public void auditScoreBatch(String deptType, String month)
	{
		getScoreDao().auditScoreBatch(deptType, month);
	}
	
	public Double findSumScoreByYear(String year, String departmentId)
	{
		Double value = getScoreDao().findSumScoreByYear(year, departmentId);
		if(null == value)
		{
			return 0.0;
		}
		return value;
	}
	
	public List<Object> getYearsByType(String deptType)
	{
		return getScoreDao().findYearsByType(deptType);
	}
	
	public List<Object> getYearsById(String deptId)
	{
		return getScoreDao().findYearsByDeptId(deptId);
	}
	
	public void publishScore(String month){
		//修改月份打分标示位
		getScoreDao().publishScore(month);
		
		//更新部门上月得分和当年总分
		List<Department> allDept = departmentService.getAll();
		Double monthValue = 0.0;//每月分值
		Double yearValue = 0.0; //年度分值，包括半年及全年分值，用于得出总分值
		for(Department dept : allDept){
			String lastMonth = DateUtils.getLastMonth();// 获取上月时间
			if(month.equals(lastMonth)) // 如当前打分的指标时间是上月
			{
				monthValue = getScoreDao().findSumScoreByLastMonth(lastMonth, dept.getDepartmentId()); // 更新部门的上得分
				dept.setLastMonthScore(monthValue);
			}
			monthValue = getScoreDao().findSumScoreByYear(month.substring(0, 4), dept.getDepartmentId());// 获取年度总分值。
			yearValue = auditScoreService.findSumScoreByYear(month.substring(0, 4), dept.getDepartmentId());
			dept.setTotalScore(monthValue + yearValue);
			departmentService.update(dept);
		}
	}
	
	/**
	 * 获得已有的年份数据
	 * @return
	 */
	public List<String> getExistYear()
	{
		return getScoreDao().findExistYears();
	}
	
	/**
	 * 获得单位历史年度得分列表
	 * @return
	 */
	public List<Object[]> getTotalScore(String year, String type)
	{
		return getScoreDao().findTotalScoreByTypeAndYear(year, type);
	}
	/**
	 * 获得单位历史年度得分列表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getTotalScoreFromSQL(String year, String type)
	{
		return getScoreDao().findTotalScoreByTypeAndYearFromSQL(year, type);
	}
}
