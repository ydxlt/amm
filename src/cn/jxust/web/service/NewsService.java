package cn.jxust.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.enums.NewsState;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
import cn.jxust.utils.DateUtils;
import cn.jxust.web.dao.NewsDao;
import cn.jxust.web.model.News;
import cn.jxust.web.model.NewsColumns;
import cn.jxust.web.model.NewsDetails;

@Service
@Transactional
public class NewsService extends BaseService<News>
{
	@Override
	@Resource(name="newsDao")
	public void setBaseDao(BaseDao<News> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public NewsDao getNewsDao()
	{
		return (NewsDao)baseDao;
	}
	
	public PageData<News> getAll(PageData<News> pageData)
	{
		return getNewsDao().findPage(pageData, "from News order by cdate desc, id desc");
	}
	
	public List<News> getNewsByPicture(String code, int num)
	{
		PageData<News> pageData = new PageData<News>(1);
		List<News> news = getNewsDao().findPageCache(pageData, "from News where newscolumns.columnCode = ? and status > ? and isPicNews = 1 order by cdate desc", code, NewsState.UNAUDITED.getIndex()).getResult();
		if(news.size() > num)
		{
			return news.subList(0, num);
		}
		else
		{
			return news;
		}
	}
	
	public List<News> getNewsByCode(String code)
	{
		PageData<News> pageData = new PageData<News>(1);
		return getNewsDao().findPageCache(pageData, "from News where newscolumns.columnCode = ? and status > ? order by cdate desc", code, NewsState.UNAUDITED.getIndex()).getResult();
	}
	
	public PageData<News> getNewsByCode(String code, int pageNum)
	{
		PageData<News> pageData = new PageData<News>(pageNum);
		return getNewsDao().findPageCache(pageData, "from News where newscolumns.columnCode = ? and status > ? order by cdate desc", code, NewsState.UNAUDITED.getIndex());
	}
	
	public PageData<News> searchNews(String keyword, int pageNum)
	{
		PageData<News> pageData = new PageData<News>(pageNum);
		return getNewsDao().findPage(pageData, "from News where (title like ? or keyWords like ?) and status > ? order by cdate desc", "%"+keyword+"%", "%"+keyword+"%", NewsState.UNAUDITED.getIndex());
	}
	
	public void save(News news, NewsDetails details, NewsColumns column)
	{
		//当前时间为主键
		String id = String.valueOf(System.currentTimeMillis());
		
		news.setId(id);
		if("".equals(news.getCdate().trim()))
		{
			news.setCdate(DateUtils.getCurrentTime());
		}
		//设置图片新闻
		if(null == news.getPicture())
		{
			news.setIsPicNews(0);
		}
		else
		{
			news.setIsPicNews(1);
		}
		
		news.setStatus(new Integer(NewsState.UNAUDITED.getIndex()));
		
		details.setId(id);
		details.setNews(news);
		details.setNewscolumns(column);
		
		news.setNewsdetails(details);
		news.setNewscolumns(column);
		save(news);
	}
	
	public void update(News news, NewsDetails details, NewsColumns column)
	{
		details.setId(news.getId());
		
		if("".equals(news.getCdate().trim()))
		{
			news.setCdate(DateUtils.getCurrentTime());
		}
		//设置图片新闻
		if(null == news.getPicture())
		{
			news.setIsPicNews(0);
		}
		else
		{
			news.setIsPicNews(1);
		}
		
		news.setStatus(new Integer(NewsState.UNAUDITED.getIndex()));
		
		news.setNewsdetails(details);
		news.setNewscolumns(column);
		update(news);
	}
	
	public void delete(String[] id)
	{
		News news = null;
		for(String item : id)
		{
			news = find(item);
//			if(news.getStatus().intValue() == NewsState.UNAUDITED.getIndex())
//			{
//				
//			}
			delete(news);
		}
	}
	
	public void publish(String[] items)
	{
		News news = null;
		for(String item : items)
		{
			news = find(item);
			if(news.getStatus().intValue() == NewsState.UNAUDITED.getIndex())
			{
				news.setStatus(NewsState.PASS.getIndex());
				update(news);
			}
		}
	}
	
	public void nopublish(String[] items)
	{
		News news = null;
		for(String item : items)
		{
			news = find(item);
			if(news.getStatus().intValue() == NewsState.PASS.getIndex())
			{
				news.setStatus(NewsState.UNAUDITED.getIndex());
				update(news);
			}
		}
	}
}
