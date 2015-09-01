/**
 * 
 */
package cn.jxust.khpj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.khpj.model.AppraisalReport;
import cn.jxust.orm.hibernate.BaseDao;

/**
 * @author Jephirus
 *
 */
@Repository
public class AppraisalReportDao extends BaseDao<AppraisalReport> {

	public List<AppraisalReport> findByDeptandYear(String departmentId, String year){
		return findList("from AppraisalReport where department.departmentId=? and createDate=?", departmentId, year);
	}
}
