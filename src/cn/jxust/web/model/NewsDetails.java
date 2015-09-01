package cn.jxust.web.model;

@SuppressWarnings("serial")
public class NewsDetails implements java.io.Serializable
{
	private String id;
	private NewsColumns newscolumns;
	private News news;
	private String content;
	private String abstract_;
	private String from;
	private String author;
	private String editor;
	private Integer clicks;
	
	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	
	public News getNews()
	{
		return this.news;
	}

	public void setNews(News news)
	{
		this.news = news;
	}
	
	public NewsColumns getNewscolumns()
	{
		return this.newscolumns;
	}

	public void setNewscolumns(NewsColumns newscolumns)
	{
		this.newscolumns = newscolumns;
	}
	
	public String getContent()
	{
		return this.content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getAbstract_()
	{
		return this.abstract_;
	}

	public void setAbstract_(String abstract_)
	{
		this.abstract_ = abstract_;
	}
	
	public String getFrom()
	{
		return this.from;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}
	
	public String getAuthor()
	{
		return this.author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getEditor()
	{
		return this.editor;
	}

	public void setEditor(String editor)
	{
		this.editor = editor;
	}

	public Integer getClicks()
	{
		return this.clicks;
	}

	public void setClicks(Integer clicks)
	{
		this.clicks = clicks;
	}
}
