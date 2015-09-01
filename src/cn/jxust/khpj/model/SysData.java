/**
 * 
 */
package cn.jxust.khpj.model;

import cn.jxust.base.model.Department;
import cn.jxust.base.model.User;

/**
 * @author Administrator 数据文件表
 */
public class SysData
{
	private String sysDataId;
	private String scoreMonth;               // 月份
	private String title;                    // 数据标题
	private String sysDataMaterial;          // 综合材料
	private User user;                       // 数据维护者 多对一 单向关联
	private String createDate;               // 数据文件创建时间
	private Department department;           // 部门
	private String content;              // 数据内容，包括：部门文件、活动图片、综合材料及固定指标的内容
	private String contentType;          // 数据内容类型，包括：部门文件、活动图片、综合材料及固定指标的类型

	/**
	 * @return the sysDataMaterial
	 */
	public String getSysDataMaterial() {
		return sysDataMaterial;
	}

	/**
	 * @param sysDataMaterial the sysDataMaterial to set
	 */
	public void setSysDataMaterial(String sysDataMaterial) {
		this.sysDataMaterial = sysDataMaterial;
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
	 * @return the sysDataId
	 */
	public String getSysDataId()
	{
		return sysDataId;
	}

	/**
	 * @param sysDataId
	 *            the sysDataId to set
	 */
	public void setSysDataId(String sysDataId)
	{
		this.sysDataId = sysDataId;
	}

	/**
	 * @return the scoreMonth
	 */
	public String getScoreMonth()
	{
		return scoreMonth;
	}

	/**
	 * @param scoreMonth
	 *            the scoreMonth to set
	 */
	public void setScoreMonth(String scoreMonth)
	{
		this.scoreMonth = scoreMonth;
	}

	/**
	 * @return the createDate
	 */
	public String getCreateDate()
	{
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user)
	{
		this.user = user;
	}

	/**
	 * @param contentType the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

}