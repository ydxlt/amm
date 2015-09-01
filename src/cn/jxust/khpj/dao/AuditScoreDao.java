package cn.jxust.khpj.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.enums.AuditScoreState;
import cn.jxust.khpj.model.AuditScore;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class AuditScoreDao extends BaseDao<AuditScore>
{
	/**
	 * 通过部门和分值类型检索分值
	 * @param deptId
	 * @param scoreType
	 * @return
	 */
	public List<AuditScore> findAuditScoreByDept(String deptId, String scoreType)
	{
		return findList("from AuditScore where scoreType = ? and department.id = ?", scoreType, deptId);
	}
	
	public Double findSumScoreByYear(String year, String departmentId)
	{
		Double value = find("select sum(scoreValue) from AuditScore where scoreYear = ? and department.departmentId = ?  and publishFlag = '1' and auditStat = ?", year, departmentId, AuditScoreState.PASS.getIndex());
		if(null == value)
		{
			return 0.0;
		}
		return value;
	}
	
	/**
	 * 书记审核后将用户排名统一发布。
	 * @param month
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void publishAuditScore(String scoreType, String year)
	{
		String hql = "update AuditScore s set s.publishFlag = '1' where s.scoreYear = ? and s.scoreType =? and s.auditStat = '4'";
		Query query = getSession().createQuery(hql);
		query.setString(0, year);
		query.setString(1, scoreType);
		query.executeUpdate();
		
	}
	
	/**
	 * 批量审核通过
	 * @param deptType
	 * @param month
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void auditScoreBatch(String newStat, String oldStat, String deptType, String scoreType, String year)
	{
		String hql = "update AuditScore s set s.auditStat = ? where exists(select 1 from Department as d where d = s.department and d.departmentType= ?) and s.scoreYear = ? and s.scoreType =? and s.auditStat = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, newStat);
		query.setString(1, deptType);
		query.setString(2, year);
		query.setString(3, scoreType);
		query.setString(4, oldStat);
		query.executeUpdate();
	}
	
}
