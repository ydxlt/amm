package cn.jxust.khpj.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.enums.SysDataType;
import cn.jxust.khpj.dao.SysDataDao;
import cn.jxust.khpj.model.SysData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
import cn.jxust.utils.DateUtils;

@Service
@Transactional
public class SysDataService extends BaseService<SysData>
{
	@Override
	@Resource(name="sysDataDao")
	public void setBaseDao(BaseDao<SysData> baseDao)
	{
		this.baseDao = baseDao;
	}
	public SysDataDao getSysDataDao()
	{
		return (SysDataDao)baseDao;
	}
	
	public void save(SysData sysData)
	{
		//业务处理
		if(null == sysData.getScoreMonth() || "".equals(sysData.getScoreMonth().trim()))
		{
			sysData.setScoreMonth(DateUtils.getDaysForSubmit()[0]);
		}
		
		/**
		 * 对于固定指标 和 专门工作 数据， 若数据库中存在对应月份数据，则进行更新操作，否则进行添加操作。
		 */
		boolean flag = false;
		if(sysData.getContentType().equals(SysDataType.FIXEDQUOTADATA.getIndex()))
		{
			sysData.setTitle("固定指标数据");
			SysData sd = getByDeptMonthType(sysData.getDepartment().getDepartmentId(), sysData.getScoreMonth(), SysDataType.FIXEDQUOTADATA.getIndex());
			if(null != sd && !"".equals(sd.getSysDataId()))
			{
				sysData.setSysDataId(sd.getSysDataId());
				flag = true;
			}
		}
		if(sysData.getContentType().equals(SysDataType.PLAN.getIndex()))
		{
			sysData.setTitle("工作部署");
			SysData sd = getByDeptMonthType(sysData.getDepartment().getDepartmentId(), sysData.getScoreMonth(), SysDataType.PLAN.getIndex());
			if(null != sd && !"".equals(sd.getSysDataId()))
			{
				sysData.setSysDataId(sd.getSysDataId());
				flag = true;
			}
		}
		if(sysData.getContentType().equals(SysDataType.DEVELOP.getIndex()))
		{
			sysData.setTitle("工作开展");
			SysData sd = getByDeptMonthType(sysData.getDepartment().getDepartmentId(), sysData.getScoreMonth(), SysDataType.DEVELOP.getIndex());
			if(null != sd && !"".equals(sd.getSysDataId()))
			{
				sysData.setSysDataId(sd.getSysDataId());
				flag = true;
			}
		}
		if(sysData.getContentType().equals(SysDataType.EFFECT.getIndex()))
		{
			sysData.setTitle("工作效果");
			SysData sd = getByDeptMonthType(sysData.getDepartment().getDepartmentId(), sysData.getScoreMonth(), SysDataType.EFFECT.getIndex());
			if(null != sd && !"".equals(sd.getSysDataId()))
			{
				sysData.setSysDataId(sd.getSysDataId());
				flag = true;
			}
		}
		
		if(flag)
		{
			//update
			getSysDataDao().update(sysData);
		}
		else
		{
			//save
			sysData.setCreateDate(DateUtils.getCurrentTime());
			getSysDataDao().save(sysData);
		}
	}
	
	public void update(SysData sysData)
	{
		//业务处理
		if(sysData.getContentType().equals(SysDataType.FIXEDQUOTADATA.getIndex()))
		{
			sysData.setTitle("固定指标数据");
		}
		if(sysData.getContentType().equals(SysDataType.PLAN.getIndex()))
		{
			sysData.setTitle("工作部署");
		}
		if(sysData.getContentType().equals(SysDataType.DEVELOP.getIndex()))
		{
			sysData.setTitle("工作开展");
		}
		if(sysData.getContentType().equals(SysDataType.EFFECT.getIndex()))
		{
			sysData.setTitle("工作效果");
		}
		sysData.setCreateDate(DateUtils.getCurrentTime());
		sysData.setScoreMonth(DateUtils.getDaysForSubmit()[0]);
		getSysDataDao().update(sysData);
	}
	
	/**
	 * 获得当个数据
	 * @param deptId
	 * @param month
	 * @param type
	 * @return
	 */
	public SysData getByDeptMonthType(String deptId, String month, String type)
	{
		return getSysDataDao().findByDeptMonthType(deptId, month, type);
	}
	
	public List<Object> getYearsByDeptId(String deptId)
	{
		/**
		 * 从2013年开始计算月份
		 */
		return DateUtils.getMonthListByCurrentMonth();
		/**
		 * 从数据库中查询已有的月份
		 */
		//return getSysDataDao().findYearsByDeptId(deptId);
	}
	
	public List<Object> getYearsByDeptType(String deptType)
	{
		return getSysDataDao().findYearsByDeptType(deptType);
	}
	
	/**
	 * 管理员查看专门小组工作，scoreMonth参数一定有
	 * @param scoreMonth
	 * @param type
	 * @return
	 */
	public List<SysData> getByDeptMonthWork(String scoreMonth, String departmentId, String type)
	{
		return getSysDataDao().findByDeptMonthWork(scoreMonth, departmentId, type);
	}
	public List<SysData> getByDeptMonthWork(String scoreMonth, String type)
	{
		return getSysDataDao().findByDeptMonthWork(scoreMonth, null, type);
	}
	public List<SysData> getByDeptMonthWork(String scoreMonth)
	{
		return getSysDataDao().findByDeptMonthWork(scoreMonth, null, null);
	}
	
	/**
	 * 管理员打分时，通过该方法浏览用户用的提交数据
	 * @param departmentId
	 * @param scoreMonth
	 * @return
	 */
	public List<SysData> getByDeptMonth(String departmentId, String scoreMonth, String type)
	{
		return getSysDataDao().findListByDeptMonthType(departmentId, scoreMonth, type);
	}
	
	public List<SysData> getFixedQuotaByMonth(String scoreMonth)
	{
		return getSysDataDao().findFixedQuotaByMonth(scoreMonth);
	}
	
	public List<SysData> getListByDept(String departmentId, String scoreMonth){
		return getSysDataDao().findByDeptMonth(departmentId, scoreMonth);
	}
	
}
