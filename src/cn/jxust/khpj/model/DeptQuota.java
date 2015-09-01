package cn.jxust.khpj.model;

/**
 * 部门指标对应表
 * @author chenle 2013-6-5 下午2:50:13
 */
public class DeptQuota
{
	private String deptQuotaId;//Id
	private String deptType;//单位类型
	private Quota quota;//指标
	private Integer inused;
	
	public String getDeptQuotaId()
	{
		return deptQuotaId;
	}
	public void setDeptQuotaId(String deptQuotaId)
	{
		this.deptQuotaId = deptQuotaId;
	}
	public String getDeptType()
	{
		return deptType;
	}
	public void setDeptType(String deptType)
	{
		this.deptType = deptType;
	}
	public Quota getQuota()
	{
		return quota;
	}
	public void setQuota(Quota quota)
	{
		this.quota = quota;
	}
	public Integer getInused()
	{
		return inused;
	}
	public void setInused(Integer inused)
	{
		this.inused = inused;
	}
}
