/**
 * 
 */
package cn.jxust.khpj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.khpj.model.BPReport;
import cn.jxust.orm.hibernate.BaseDao;

/**
 * @author Jephirus
 *
 */
@Repository
public class BpReportDao extends BaseDao<BPReport> {
	
	public List<BPReport> findByDeptAndYear(String departmentId, String year){
		return findList("from BPReport where department.departmentId=? and createDate=?",departmentId,year);
	}

}
