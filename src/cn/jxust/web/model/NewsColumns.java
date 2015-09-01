package cn.jxust.web.model;

/** 新闻栏目的持久化类 */
@SuppressWarnings("serial")
public class NewsColumns implements java.io.Serializable
{
	private Integer id;// ID号
	private NewsColumns newscolumns;// 父栏目
	private String columnCode;// 新闻栏目编号
	private String columnName;// 新闻栏目名称
	private String columnType;//栏目类型 page/list
	private String enable;//是否可编辑

	/** 构造方法 */
	public NewsColumns()
	{
	}

	public Integer getId()
	{
		return this.id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public NewsColumns getNewscolumns()
	{
		return this.newscolumns;
	}

	public void setNewscolumns(NewsColumns newscolumns)
	{
		this.newscolumns = newscolumns;
	}

	public String getColumnCode()
	{
		return this.columnCode;
	}

	public void setColumnCode(String columnCode)
	{
		this.columnCode = columnCode;
	}

	public String getColumnName()
	{
		return this.columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public String getColumnType()
	{
		return columnType;
	}

	public void setColumnType(String columnType)
	{
		this.columnType = columnType;
	}

	public String getEnable()
	{
		return enable;
	}

	public void setEnable(String enable)
	{
		this.enable = enable;
	}
}