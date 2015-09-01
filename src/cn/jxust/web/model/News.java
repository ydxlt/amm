package cn.jxust.web.model;


@SuppressWarnings("serial")
public class News implements java.io.Serializable
{
	private String id;
	private NewsColumns newscolumns;
	private NewsDetails newsdetails;
	private String title;
	private String keyWords;
	private Integer isPicNews;
	private String picture;
	private String cdate;
	private Integer priority;
	private Integer red;
	private String htmlPath;
	private Integer status;//0:未审核 1：已审核 2：不能删除

	/** default constructor */
	public News()
	{
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
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

	public NewsDetails getNewsdetails()
	{
		return newsdetails;
	}

	public void setNewsdetails(NewsDetails newsdetails)
	{
		this.newsdetails = newsdetails;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}


	public String getKeyWords()
	{
		return this.keyWords;
	}

	public void setKeyWords(String keyWords)
	{
		this.keyWords = keyWords;
	}

	public Integer getIsPicNews()
	{
		return this.isPicNews;
	}

	public void setIsPicNews(Integer isPicNews)
	{
		this.isPicNews = isPicNews;
	}

	public String getPicture()
	{
		return this.picture;
	}

	public void setPicture(String picture)
	{
		this.picture = picture;
	}

	public String getCdate()
	{
		return this.cdate;
	}

	public void setCdate(String cdate)
	{
		this.cdate = cdate;
	}

	public Integer getPriority()
	{
		return this.priority;
	}

	public void setPriority(Integer priority)
	{
		this.priority = priority;
	}

	public Integer getRed()
	{
		return this.red;
	}

	public void setRed(Integer red)
	{
		this.red = red;
	}

	public String getHtmlPath()
	{
		return this.htmlPath;
	}

	public void setHtmlPath(String htmlPath)
	{
		this.htmlPath = htmlPath;
	}

	public Integer getStatus()
	{
		return this.status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}
}