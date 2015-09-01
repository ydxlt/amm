/**
 * 
 */
package cn.jxust.khpj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.khpj.model.RegisterReport;
import cn.jxust.orm.hibernate.BaseDao;

/**
 * @author Jephirus
 *
 */
@Repository
public class RegisterReportDao extends BaseDao<RegisterReport> {

	public List<RegisterReport> findByDeptAndYear(String departmentId, String year){
		return findList("from RegisterReport where department.departmentId=? and reportYear=?",departmentId,year);
	}
}
