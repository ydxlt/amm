/**
 * 
 */
package cn.jxust.khpj.model;

import cn.jxust.base.model.Department;

/**
 * @author Administrator 领导审核得分表，即书记、副书记进行半年和全年审核得分。
 */
public class AuditScore
{

	private String auditScoreId;
	private Double scoreValue;        // 得分
	private String scoreYear;         // 年份
	private Department department;    // 多个数据文件对应一个部门 多对一 单向关联
	private String auditor;           // 审核人
	private String scoreType;         // 分值类型，分半年和全年两类
	private String auditStat;           // 审核标志：未审核为0；通过为1；未通过为2 二级审核 通过为3；未通过为4
	private String refuseInfo;          // 被拒意见
	private String publishFlag;         // 排名发布标志位，0为未发布该分值参与排名，1为发布。

	/**
	 * @return the scoreId
	 */
	public String getAuditScoreId()
	{
		return auditScoreId;
	}

	/**
	 * @param scoreId
	 *            the scoreId to set
	 */
	public void setAuditScoreId(String auditScoreId)
	{
		this.auditScoreId = auditScoreId;
	}

	public Double getScoreValue()
	{
		return scoreValue;
	}

	public void setScoreValue(Double scoreValue)
	{
		this.scoreValue = scoreValue;
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
	 * @return the scoreYear
	 */
	public String getScoreYear() {
		return scoreYear;
	}

	/**
	 * @param scoreYear the scoreYear to set
	 */
	public void setScoreYear(String scoreYear) {
		this.scoreYear = scoreYear;
	}

	/**
	 * @return the scoreType
	 */
	public String getScoreType() {
		return scoreType;
	}

	/**
	 * @param scoreType the scoreType to set
	 */
	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	/**
	 * @param auditStat the auditStat to set
	 */
	public void setAuditStat(String auditStat) {
		this.auditStat = auditStat;
	}

	/**
	 * @return the auditStat
	 */
	public String getAuditStat() {
		return auditStat;
	}

	/**
	 * @param refuseInfo the refuseInfo to set
	 */
	public void setRefuseInfo(String refuseInfo) {
		this.refuseInfo = refuseInfo;
	}

	/**
	 * @return the refuseInfo
	 */
	public String getRefuseInfo() {
		return refuseInfo;
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

}