package cn.jxust.khpj.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.enums.SysDataType;
import cn.jxust.khpj.model.SysData;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class SysDataDao extends BaseDao<SysData>
{
	/**
	 * 普通用户用于检索当前月份已提交的单个数据
	 * @param departmentId
	 * @param scoreMonth
	 * @param type 不同的数据类型：部门文件、活动图片、综合文件和固定指标文件
	 * @return
	 */
	public SysData findByDeptMonthType(String departmentId, String scoreMonth, String type)
	{
		return find("from SysData where department.departmentId=? and scoreMonth=? and contentType = ?", departmentId, scoreMonth, type);
	}
	
	/**
	 * 普通用户用于检索当前月份已提交的数据
	 * @param departmentId
	 * @param scoreMonth
	 * @param type 不同的数据类型：部门文件、活动图片、综合文件和固定指标文件
	 * @return
	 */
	public List<SysData> findListByDeptMonthType(String departmentId, String scoreMonth, String type)
	{
		return findList("from SysData where department.departmentId=? and scoreMonth=? and contentType = ? order by department.departmentType, department.ordNum", departmentId, scoreMonth, type);
	}
	
	/**
	 * 普通用户用于检索当前月份专门工作
	 * @param departmentId
	 * @param scoreMonth
	 * @param type 不同的数据类型：工作部署、工作开展、工作效果
	 * @return
	 */
	public List<SysData> findByDeptMonthWork(String scoreMonth, String departmentId, String type)
	{
		List<String> para = new ArrayList<String>();
		String sql = "from SysData where scoreMonth=? ";
		para.add(scoreMonth);
		
		if(null != departmentId)
		{
			sql += " and department.departmentId=? ";
			para.add(departmentId);
		}
		else
		{
			sql += " and department.speciallyWork = '1'";
		}
		
		if(null != type)
		{
			sql += " and contentType=? ";
			para.add(type);
		}
		sql += " order by department.ordNum, department.departmentType";
		return findList(sql, para.toArray());
	}
	
	public List<SysData> findFixedQuotaByMonth(String month)
	{
		return findList("from SysData where scoreMonth=? and contentType = ? order by department.departmentType, department.ordNum", month, SysDataType.FIXEDQUOTADATA.getIndex());
	}
	/**
	 * 普通用户用于检索当前月份已提交的数据
	 * @param departmentId
	 * @param scoreMonth
	 * @return
	 */
	public List<SysData> findByDeptMonth(String departmentId, String scoreMonth){
		return findList("from SysData where department.departmentId=? and scoreMonth=?", departmentId, scoreMonth);
	}
	
	/**
	 * 查询已有数据中的年份信息
	 * @param deptId
	 * @return
	 */
	public List<Object> findYearsByDeptId(String deptId)
	{
		return findList("SELECT distinct(scoreMonth) from SysData where department.id = ? order by scoreMonth desc", deptId);
	}
	
	/**
	 * 查询已有数据中的年份信息
	 * @param deptId
	 * @return
	 */
	public List<Object> findYearsByDeptType(String deptType)
	{
		return findList("SELECT distinct(scoreMonth) from SysData where department.departmentType = ? order by scoreMonth desc", deptType);
	}
}
