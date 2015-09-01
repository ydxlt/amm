/**
 * 
 */
package cn.jxust.khpj.model;

import cn.jxust.base.model.Department;

/**
 * 综治维稳政绩档案    奖惩      表格 Maintain Stability Political Achievement Report
 * @author Jephirus
 *
 */
public class BPReport {
	
	private String bPReportId;
	private String name;
	private Department department;
	private String duty;   //职务
	private String mainAffair;  //主要事迹或主要问题
	private String bonusOrPenalty;     //所受奖励或处罚
	private String type;//0：奖励，1：处罚
	private String createDate;

	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	/**
	 * @return the duty
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * @param duty the duty to set
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}

	/**
	 * @return the bPReportId
	 */
	public String getbPReportId() {
		return bPReportId;
	}

	/**
	 * @param bPReportId the bPReportId to set
	 */
	public void setbPReportId(String bPReportId) {
		this.bPReportId = bPReportId;
	}
	/**
	 * @return the mainAffair
	 */
	public String getMainAffair() {
		return mainAffair;
	}
	/**
	 * @param mainAffair the mainAffair to set
	 */
	public void setMainAffair(String mainAffair) {
		this.mainAffair = mainAffair;
	}
	/**
	 * @return the bonusOrPenalty
	 */
	public String getBonusOrPenalty() {
		return bonusOrPenalty;
	}
	/**
	 * @param bonusOrPenalty the bonusOrPenalty to set
	 */
	public void setBonusOrPenalty(String bonusOrPenalty) {
		this.bonusOrPenalty = bonusOrPenalty;
	}

}
