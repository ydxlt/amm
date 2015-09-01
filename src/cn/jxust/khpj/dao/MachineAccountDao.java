package cn.jxust.khpj.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.khpj.model.MachineAccount;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class MachineAccountDao extends BaseDao<MachineAccount> {

	/**
	 * 查询已有数据中的年份信息
	 * 
	 * @param deptId
	 * @return
	 */
	public List<Object> findYears(String deptId) {
		return findList("SELECT distinct(reportDate) from MachineAccount where department.id = ? order by reportDate desc",deptId);
	}
	
	/**
	 * 查询已有数据中的年份信息
	 * 
	 * @param deptId
	 * @return
	 */
	public List<Object> findYears() {
		return findList("SELECT distinct(reportDate) from MachineAccount order by reportDate desc");
	}
	
	public List<MachineAccount> findMachineAccountByMonth(String month){
		return findList("from MachineAccount where reportDate=?", month);
	}
}
