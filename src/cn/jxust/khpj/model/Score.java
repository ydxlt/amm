/**
 * 
 */
package cn.jxust.khpj.model;

import cn.jxust.base.model.Department;

/**
 * @author Administrator 部门指标得分表
 */
public class Score
{

	private String scoreId;
	private Double scoreValue;          // 得分
	private String scoreMonth;          // 月份
	private Department department;      // 多个数据文件对应一个部门 多对一 单向关联
	private Quota quota;                // 多个数据文件对应一个指标，多对一 单向关联
	// private List<SysData> sysData;   // 指标分值可对应多个数据文件, 一对多 多方单向关联一方
	private String auditStat;           // 审核标志：未审核为0；通过为1；未通过为2
	private String auditDate;           // 审核时间
	private String modifyDate;          // 修改时间
	private String auditor;             // 审核人
	private String refuseInfo;          // 被拒意见
	private String publishFlag;         // 排名发布标志位，0为未发布该分值参与排名，1为发布。

	/**
	 * @return the scoreId
	 */
	public String getScoreId()
	{
		return scoreId;
	}

	/**
	 * @return the publishFlag
	 */
	public String getPublishFlag() {
		return publishFlag;
	}

	/**
	 * @param publishFlag the publishFlag to set
	 */
	public void setPublishFlag(String publishFlag) {
		this.publishFlag = publishFlag;
	}

	/**
	 * @param scoreId
	 *            the scoreId to set
	 */
	public void setScoreId(String scoreId)
	{
		this.scoreId = scoreId;
	}

	public Double getScoreValue()
	{
		return scoreValue;
	}

	public void setScoreValue(Double scoreValue)
	{
		this.scoreValue = scoreValue;
	}

	public String getScoreMonth()
	{
		return scoreMonth;
	}

	public void setScoreMonth(String scoreMonth)
	{
		this.scoreMonth = scoreMonth;
	}

	/**
	 * @return the auditStat
	 */
	public String getAuditStat()
	{
		return auditStat;
	}

	/**
	 * @param auditStat
	 *            the auditStat to set
	 */
	public void setAuditStat(String auditStat)
	{
		this.auditStat = auditStat;
	}

	/**
	 * @return the auditDate
	 */
	public String getAuditDate()
	{
		return auditDate;
	}

	/**
	 * @param auditDate
	 *            the auditDate to set
	 */
	public void setAuditDate(String auditDate)
	{
		this.auditDate = auditDate;
	}

	/**
	 * @return the modifyDate
	 */
	public String getModifyDate()
	{
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(String modifyDate)
	{
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the auditor
	 */
	public String getAuditor()
	{
		return auditor;
	}

	/**
	 * @param auditor
	 *            the auditor to set
	 */
	public void setAuditor(String auditor)
	{
		this.auditor = auditor;
	}

	/**
	 * @return the refuseInfo
	 */
	public String getRefuseInfo()
	{
		return refuseInfo;
	}

	/**
	 * @param refuseInfo
	 *            the refuseInfo to set
	 */
	public void setRefuseInfo(String refuseInfo)
	{
		this.refuseInfo = refuseInfo;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment()
	{
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department)
	{
		this.department = department;
	}

	/**
	 * @return the quota
	 */
	public Quota getQuota()
	{
		return quota;
	}

	/**
	 * @param quota
	 *            the quota to set
	 */
	public void setQuota(Quota quota)
	{
		this.quota = quota;
	}
}