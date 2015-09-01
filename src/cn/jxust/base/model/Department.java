/**
 * 
 */
package cn.jxust.base.model;

/**
 * @author Administrator
 * 单位部门表
 */
public class Department {
    
    private String departmentId;
    private String departmentName;
    private String departmentDesc;  //数据格式为json，包括描述、人口数(单位职工数)、村（居）数、主要领导、
                                    //分管领导、综治工作人员、联系电话、办公地址等。
    private String departmentType;
    private Integer ordNum;      // 部门排名
    private String transFixedQuota;  // 是否可上传固定数据包
    private String transFixedQuotaTemplate;  // 是否可上传固定数据包
    private String speciallyWork;//是否可上传专门工作

    private Double lastMonthScore;//上月累计得分
	private Double totalScore;//单位累计得分

    public Double getTotalScore()
	{
		return totalScore;
	}
	public void setTotalScore(Double totalScore)
	{
		this.totalScore = totalScore;
	}
	/**
     * @return the departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }
    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    /**
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }
    /**
     * @param departmentName the departmentName to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    /**
     * @return the departmentDesc
     */
    public String getDepartmentDesc() {
        return departmentDesc;
    }
    /**
     * @param departmentDesc the departmentDesc to set
     */
    public void setDepartmentDesc(String departmentDesc) {
        this.departmentDesc = departmentDesc;
    }
	public String getDepartmentType()
	{
		return departmentType;
	}
	public void setDepartmentType(String departmentType)
	{
		this.departmentType = departmentType;
	}
	/**
	 * @return the ordNum
	 */
	public Integer getOrdNum() {
		return ordNum;
	}
	/**
	 * @param ordNum the ordNum to set
	 */
	public void setOrdNum(Integer ordNum) {
		this.ordNum = ordNum;
	}
	
	/**
	 * @return the transFixedQuota
	 */
	public String getTransFixedQuota() {
		return transFixedQuota;
	}
	
	/**
	 * @param transFixedQuota the transFixedQuota to set
	 */
	public void setTransFixedQuota(String transFixedQuota) {
		this.transFixedQuota = transFixedQuota;
	}
	
	public String getSpeciallyWork()
	{
		return speciallyWork;
	}
	public void setSpeciallyWork(String speciallyWork)
	{
		this.speciallyWork = speciallyWork;
	}
	public String getTransFixedQuotaTemplate()
	{
		return transFixedQuotaTemplate;
	}
	
	public void setTransFixedQuotaTemplate(String transFixedQuotaTemplate)
	{
		this.transFixedQuotaTemplate = transFixedQuotaTemplate;
	}
	public Double getLastMonthScore()
	{
		return lastMonthScore;
	}
	public void setLastMonthScore(Double lastMonthScore)
	{
		this.lastMonthScore = lastMonthScore;
	}

}
