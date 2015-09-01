/**
 * 
 */
package cn.jxust.khpj.model;

import cn.jxust.base.model.Department;

/**
 * @author Jephirus
 * 
 */
public class MachineAccount {

	private String machineAccountId;
	private Department department;
	private String cdDescription; // 矛盾纠纷简要情况
	String reportDate; // 日期
	private String dutyPerson; // 责任单位责任人
	private String resoleTime; // 化解时限
	private String resoleDescription; // 化解情况
	private String supervision; // 交办督办情况
	private String xiaoHao; // 销号情况

	/**
	 * @return the machineAccountId
	 */
	public String getMachineAccountId() {
		return machineAccountId;
	}

	/**
	 * @param machineAccountId
	 *            the machineAccountId to set
	 */
	public void setMachineAccountId(String machineAccountId) {
		this.machineAccountId = machineAccountId;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return the cdDescription
	 */
	public String getCdDescription() {
		return cdDescription;
	}

	/**
	 * @param cdDescription
	 *            the cdDescription to set
	 */
	public void setCdDescription(String cdDescription) {
		this.cdDescription = cdDescription;
	}

	/**
	 * @return the dutyPerson
	 */
	public String getDutyPerson() {
		return dutyPerson;
	}

	/**
	 * @param dutyPerson
	 *            the dutyPerson to set
	 */
	public void setDutyPerson(String dutyPerson) {
		this.dutyPerson = dutyPerson;
	}

	/**
	 * @return the resoleTime
	 */
	public String getResoleTime() {
		return resoleTime;
	}

	/**
	 * @param resoleTime
	 *            the resoleTime to set
	 */
	public void setResoleTime(String resoleTime) {
		this.resoleTime = resoleTime;
	}

	/**
	 * @return the resoleDescription
	 */
	public String getResoleDescription() {
		return resoleDescription;
	}

	/**
	 * @param resoleDescription
	 *            the resoleDescription to set
	 */
	public void setResoleDescription(String resoleDescription) {
		this.resoleDescription = resoleDescription;
	}

	/**
	 * @return the supervision
	 */
	public String getSupervision() {
		return supervision;
	}

	/**
	 * @param supervision
	 *            the supervision to set
	 */
	public void setSupervision(String supervision) {
		this.supervision = supervision;
	}

	/**
	 * @return the xiaoHao
	 */
	public String getXiaoHao() {
		return xiaoHao;
	}

	/**
	 * @param xiaoHao
	 *            the xiaoHao to set
	 */
	public void setXiaoHao(String xiaoHao) {
		this.xiaoHao = xiaoHao;
	}

	/**
	 * @return the reportDate
	 */
	public String getReportDate() {
		return reportDate;
	}

	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

}
