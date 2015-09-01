package cn.jxust.khpj.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.enums.ScoreState;
import cn.jxust.khpj.model.Score;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.utils.DateUtils;

@Repository
public class ScoreDao extends BaseDao<Score>
{
	/**
	 * 查询当前type的对应month的分值
	 * @param type
	 * @param month
	 * @return
	 */
	public List<Score> findScoreByTypeAndMonth(String type, String month)
	{
		if (null == month)
		{
			month = DateUtils.getCurrentMonth();
		}
		return findList("from Score where department.departmentType=? and scoreMonth = ? order by department.ordNum, quota.quotaType, quota.quotaId", type, month);
	}
	
	/**
	 * 查询当前dept的对应month的分值
	 * @param type
	 * @param month
	 * @return
	 */
	public List<Score> findScoreByDeptAndMonth(String deptId, String month)
	{
		if (null == month)
		{
			month = DateUtils.getCurrentMonth();
		}
		return findList("from Score where department.departmentId=? and scoreMonth = ? order by department.ordNum, quota.quotaType, quota.quotaId", deptId, month);
	}
	
	/**
	 * 通过部门和月份检索分值
	 * @param deptId
	 * @param month
	 * @return
	 */
	public List<Score> findQuotaByDept(String deptId, String month)
	{
		return findList("from Score where scoreMonth = ? and department.departmentId = ? order by quota.quotaType, quota.quotaId", month, deptId);
	}
	
	/**
	 * 查询已有数据中的年份信息
	 * @param deptId
	 * @return
	 */
	public List<Object> findYearsByType(String deptType)
	{
		return findList("select distinct scoreMonth from Score where department.departmentType = ? order by scoreMonth desc", deptType);
	}
	
	/**
	 * 查询已有数据中的年份信息
	 * @param deptId
	 * @return
	 */
	public List<Object> findYearsByDeptId(String deptId)
	{
		return findList("select distinct scoreMonth from Score where department.departmentId = ? order by scoreMonth desc", deptId);
	}
	
	public Double findSumScoreByLastMonth(String month, String departmentId)
	{
		Double value = find("select sum(scoreValue) from Score where scoreMonth = ? and department.departmentId = ? and publishFlag = '1' and auditStat = ?", month, departmentId, ScoreState.PASS.getIndex());
		if(null == value)
		{
			return 0.0;
		}
		return value;
	}
	
	public Double findSumScoreByYear(String year, String departmentId)
	{
		Double value = find("select sum(scoreValue) from Score where scoreMonth like ? and department.departmentId = ?  and publishFlag = '1' and auditStat = ?", year+"%", departmentId, ScoreState.PASS.getIndex());
		if(null == value)
		{
			return 0.0;
		}
		return value;
	}
	
	/**
	 * 副书记审核后将用户排名统一发布。
	 * @param month
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void publishScore(String month){
		String hql = "update Score s set s.publishFlag = '1' where s.auditStat = '1' and s.scoreMonth =?";
		Query query = getSession().createQuery(hql);
		query.setString(0, month);
		query.executeUpdate();
		
	}
	
	/**
	 * 批量审核通过
	 * @param deptType
	 * @param month
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void auditScoreBatch(String deptType, String month)
	{
		String hql = "update Score s set auditStat = ? where exists(select 1 from Department as d where d = s.department and d.departmentType= ?) and s.scoreMonth =? and s.auditStat = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, ScoreState.PASS.getIndex());
		query.setString(1, deptType);
		query.setString(2, month);
		query.setString(3, ScoreState.UNAUDITED.getIndex());
		query.executeUpdate();
	}
	
	/**
	 * 获得已有的年份数据
	 * @return
	 */
	public List<String> findExistYears()
	{
		List<Object> scoreMonth = findList("select scoreMonth from Score where publishFlag = '1' and auditStat = '1' order by scoreId desc");
		List<String> years = new ArrayList<String>();
		String year = null;
		for(Object s : scoreMonth)
		{
			year = s.toString().substring(0, 4);
			if(!years.contains(year))
			{
				years.add(year);
			}
		}
		return years;
	}
	
	/**
	 * 获得单位历史年度得分列表
	 * @return
	 */
	public List<Object[]> findTotalScoreByTypeAndYear(String year, String type)
	{
		return findList("select department.departmentName, sum(scoreValue) as scoreValue from Score where scoreMonth like ? and department.departmentType = ? and publishFlag = '1' and auditStat = '1' group by department.departmentName" , year + "-__" , type);
	}
	/**
	 * 获得单位历史年度得分列表
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findTotalScoreByTypeAndYearFromSQL(String year, String type)
	{
		//select DEPARTMENT_NAME, round(sum(SCORE_VALUE),2) as scorevalue 
		//from V_SCORE 
		//where month_year like '2013%' 
		//group by DEPARTMENT_NAME 
		//order by SCOREVALUE desc
		return findBySQL("SELECT DEPARTMENT_NAME, ROUND(SUM(SCORE_VALUE),2) as SCOREVALUE FROM V_SCORE WHERE MONTH_YEAR LIKE '"+year+"%' AND DEPARTMENT_TYPE = '"+type+"' GROUP BY DEPARTMENT_NAME ORDER BY SCOREVALUE DESC");
	}
}
