/**
 * 
 */
package cn.jxust.khpj.model;

import cn.jxust.base.model.Department;

/**
 * 矛盾纠纷报表
 * 
 * @author Jephirus
 * 
 */
public class CDReport {
	String cDReportId; //主键
	Department department;  		 // 填报单位
	String reportDate;      		 // 日期
	int conDisClass;     		 // 类别项目，包括：因土地、山林、水利等问题引发的矛盾纠纷等
	int countyCount;      		 // 层级：县区排查数
	int townCount;      		     // 层级：乡镇排查数
	int villageCount;      		 // 层级：村居排查数
	int couDisCount;       		 // 县区调处数
	int townDisCount;       		 // 乡镇调处数
	int vilDisCount;       		 // 村居调处数
	String informant;       		 // 填报人
	String audit;           		 // 审核人
	String timeFillReport;  		 // 填表时间

	/**
	 * @return the cDReportId
	 */
	public String getcDReportId() {
		return cDReportId;
	}
	/**
	 * @param cDReportId the cDReportId to set
	 */
	public void setcDReportId(String cDReportId) {
		this.cDReportId = cDReportId;
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
	 * @return the conDisClass
	 */
	public int getConDisClass() {
		return conDisClass;
	}
	/**
	 * @param conDisClass the conDisClass to set
	 */
	public void setConDisClass(int conDisClass) {
		this.conDisClass = conDisClass;
	}

	/**
	 * @return the informant
	 */
	public String getInformant() {
		return informant;
	}
	/**
	 * @param informant the informant to set
	 */
	public void setInformant(String informant) {
		this.informant = informant;
	}
	/**
	 * @return the audit
	 */
	public String getAudit() {
		return audit;
	}
	/**
	 * @param audit the audit to set
	 */
	public void setAudit(String audit) {
		this.audit = audit;
	}
	/**
	 * @return the timeFillReport
	 */
	public String getTimeFillReport() {
		return timeFillReport;
	}

	/**
	 * @param timeFillReport the timeFillReport to set
	 */
	public void setTimeFillReport(String timeFillReport) {
		this.timeFillReport = timeFillReport;
	}
	/**
	 * @return the countyCount
	 */
	public int getCountyCount() {
		return countyCount;
	}
	/**
	 * @param countyCount the countyCount to set
	 */
	public void setCountyCount(int countyCount) {
		this.countyCount = countyCount;
	}
	/**
	 * @return the townCount
	 */
	public int getTownCount() {
		return townCount;
	}
	/**
	 * @param townCount the townCount to set
	 */
	public void setTownCount(int townCount) {
		this.townCount = townCount;
	}
	/**
	 * @return the villageCount
	 */
	public int getVillageCount() {
		return villageCount;
	}
	/**
	 * @param villageCount the villageCount to set
	 */
	public void setVillageCount(int villageCount) {
		this.villageCount = villageCount;
	}
	/**
	 * @return the couDisCount
	 */
	public int getCouDisCount() {
		return couDisCount;
	}
	/**
	 * @param couDisCount the couDisCount to set
	 */
	public void setCouDisCount(int couDisCount) {
		this.couDisCount = couDisCount;
	}
	/**
	 * @return the townDisCount
	 */
	public int getTownDisCount() {
		return townDisCount;
	}
	/**
	 * @param townDisCount the townDisCount to set
	 */
	public void setTownDisCount(int townDisCount) {
		this.townDisCount = townDisCount;
	}
	/**
	 * @return the vilDisCount
	 */
	public int getVilDisCount() {
		return vilDisCount;
	}
	/**
	 * @param vilDisCount the vilDisCount to set
	 */
	public void setVilDisCount(int vilDisCount) {
		this.vilDisCount = vilDisCount;
	}

}
