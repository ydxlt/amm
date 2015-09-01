/**
 * 
 */
package cn.jxust.khpj.model;

import cn.jxust.base.model.Department;

/**
 * 综治维稳政绩档案    鉴定      表格 Maintain Stability Political Achievement Report
 * @author Jephirus
 *
 */
public class AppraisalReport {
	
	private String appraisalReportId;
	private String name;
	private Department department;
	private String duty;   //职务
	private String selfAssessment ;  //自我鉴定
	private String selfAssessmentTime ;  //自我鉴定时间
	private String opinion;          //综治委意见
	private String opinionTime;     //综治委意见时间
	private String createDate;

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
	 * @return the remarks
	 */
	/**
	 * @return the selfAssessment
	 */
	public String getSelfAssessment() {
		return selfAssessment;
	}
	/**
	 * @param selfAssessment the selfAssessment to set
	 */
	public void setSelfAssessment(String selfAssessment) {
		this.selfAssessment = selfAssessment;
	}
	/**
	 * @return the opinion
	 */
	public String getOpinion() {
		return opinion;
	}
	/**
	 * @param opinion the opinion to set
	 */
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	/**
	 * @return the selfAssessmentTime
	 */
	public String getSelfAssessmentTime() {
		return selfAssessmentTime;
	}
	/**
	 * @param selfAssessmentTime the selfAssessmentTime to set
	 */
	public void setSelfAssessmentTime(String selfAssessmentTime) {
		this.selfAssessmentTime = selfAssessmentTime;
	}
	/**
	 * @return the opinionTime
	 */
	public String getOpinionTime() {
		return opinionTime;
	}
	/**
	 * @param opinionTime the opinionTime to set
	 */
	public void setOpinionTime(String opinionTime) {
		this.opinionTime = opinionTime;
	}
	/**
	 * @param mainStaPoAchAppraisalReportId the mainStaPoAchAppraisalReportId to set
	 */
	/**
	 * @return the appraisalReportId
	 */
	public String getAppraisalReportId() {
		return appraisalReportId;
	}
	/**
	 * @param appraisalReportId the appraisalReportId to set
	 */
	public void setAppraisalReportId(String appraisalReportId) {
		this.appraisalReportId = appraisalReportId;
	}
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

	
}
