/**
 * 
 */
package cn.jxust.khpj.model;

import cn.jxust.base.model.Department;

/**
 * 综治维稳政绩档案     登记     表格 Maintain Stability Political Achievement Report
 * @author Jephirus
 *
 */
public class RegisterReport {
	
	private String registerReportId;
	private String reportDate;     //登记时间：分上、下半年。例“2013-上”；“2013-下”
	private String reportYear;
	private String name;
	private Department department;
	private String duty;   //职务
	private String workingSituation;  //工作情况
	private String auditResult;     //责任人审签
	private String auditResultTime;     //责任人审签日期
	private String remarks;          //备注
	private String filler;          //填写人
	private String fillTime;          //填写时间

	/**
	 * @return the reportYear
	 */
	public String getReportYear() {
		return reportYear;
	}
	/**
	 * @param reportYear the reportYear to set
	 */
	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}
	/**
	 * @return the registerReportId
	 */
	public String getRegisterReportId() {
		return registerReportId;
	}
	/**
	 * @param registerReportId the registerReportId to set
	 */
	public void setRegisterReportId(String registerReportId) {
		this.registerReportId = registerReportId;
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
	 * @return the workingSituation
	 */
	public String getWorkingSituation() {
		return workingSituation;
	}
	/**
	 * @param workingSituation the workingSituation to set
	 */
	public void setWorkingSituation(String workingSituation) {
		this.workingSituation = workingSituation;
	}
	/**
	 * @return the auditResult
	 */
	public String getAuditResult() {
		return auditResult;
	}
	/**
	 * @param auditResult the auditResult to set
	 */
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	/**
	 * @return the filler
	 */
	public String getFiller() {
		return filler;
	}
	/**
	 * @param filler the filler to set
	 */
	public void setFiller(String filler) {
		this.filler = filler;
	}
	/**
	 * @return the fillTime
	 */
	public String getFillTime() {
		return fillTime;
	}
	/**
	 * @param fillTime the fillTime to set
	 */
	public void setFillTime(String fillTime) {
		this.fillTime = fillTime;
	}
	/**
	 * @return the auditResultTime
	 */
	public String getAuditResultTime() {
		return auditResultTime;
	}
	/**
	 * @param auditResultTime the auditResultTime to set
	 */
	public void setAuditResultTime(String auditResultTime) {
		this.auditResultTime = auditResultTime;
	}

}
