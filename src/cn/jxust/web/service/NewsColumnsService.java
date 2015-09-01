package cn.jxust.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
import cn.jxust.web.dao.NewsColumnsDao;
import cn.jxust.web.model.NewsColumns;

@Service
@Transactional
public class NewsColumnsService extends BaseService<NewsColumns>
{
	@Override
	@Resource(name="newsColumnsDao")
	public void setBaseDao(BaseDao<NewsColumns> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public NewsColumnsDao getNewsColumnsDao()
	{
		return (NewsColumnsDao)baseDao;
	}
	
	/**
	 * 查找所有栏目
	 * @return
	 */
	public List<NewsColumns> findAll()
	{
		return getNewsColumnsDao().findListCache("from NewsColumns order by id");
	}
	
	/**
	 * 查找一级目录为list的栏目
	 * @return
	 */
	public List<NewsColumns> findParentList()
	{
		return getNewsColumnsDao().findListCache("from NewsColumns where columnType='list' and newscolumns is null order by id");
	}
	
	/**
	 * 查找除去Index的一级栏目
	 * @return
	 */
	public List<NewsColumns> findParentExceptIndex()
	{
		return getNewsColumnsDao().findListCache("from NewsColumns where columnCode != 'index' and newscolumns is null order by id");
	}
	
	/**
	 * 查找list的子栏目
	 * @param parentId
	 * @return
	 */
	public List<NewsColumns> findChildren(int parentId)
	{
		return getNewsColumnsDao().findListCache("from NewsColumns where newscolumns.id = ? and columnType='list' order by id desc", parentId);
	}
	
	/**
	 * 查找Index的page子栏目
	 * @return
	 */
	public List<NewsColumns> findPageForIndex()
	{
		return getNewsColumnsDao().findListCache("from NewsColumns where newscolumns.id = (select id from NewsColumns where columnCode = 'index') and columnType='page'");
	}
	
	public NewsColumns getNewsColumnsByCode(String code)
	{
		return getNewsColumnsDao().findCache("from NewsColumns where columnCode = ?", code);
	}
	
	public String getChilderColumnsString(int parentId)
	{
		StringBuilder sb = new StringBuilder();
		List<NewsColumns> list = findChildren(parentId);
		
		sb.append("[");
		if(list.isEmpty())
		{
			NewsColumns column = find(parentId);
			sb.append("[");
			sb.append("\"");
			sb.append(column.getId());
			sb.append("\"");
			sb.append(",");
			sb.append("\"");
			sb.append(column.getColumnName());
			sb.append("\"");
			sb.append("]");
			sb.append(",");
		}
		else
		{
			for(NewsColumns c : list)
			{
				sb.append("[");
				sb.append("\"");
				sb.append(c.getId());
				sb.append("\"");
				sb.append(",");
				sb.append("\"");
				sb.append(c.getColumnName());
				sb.append("\"");
				sb.append("]");
				sb.append(",");
			}
		}
		return sb.substring(0, sb.length() - 1) + "]";
	}
	
	/**
	 * 获得栏目树形结构
	 * @return
	 */
	public List<Map<String, Object>> getTree()
	{
		List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		Map<String, Object> item = null;
		List<NewsColumns> menus = findAll();
		for(NewsColumns c : menus)
		{
			item = new HashMap<String, Object>();
			item.put("id", c.getId());
			if(null == c.getNewscolumns())
			{
				item.put("pId", 0);
			}
			else
			{
				item.put("pId", c.getNewscolumns().getId());
			}
			item.put("name", c.getColumnName());
			item.put("open", "true");
			tree.add(item);
		}
		
		return tree;
	}
	
	/**
	 * 保存栏目
	 * @param column
	 * @param parentId
	 */
	public void save(NewsColumns column, Integer parentId)
	{
		if(null == parentId)
		{
			column.setNewscolumns(null);
		}
		else
		{
			NewsColumns parent = getNewsColumnsDao().find(parentId);
			column.setNewscolumns(parent);
		}
		getNewsColumnsDao().save(column);
	}
	
	/**
	 * 更新栏目
	 * @param column
	 * @param parentId
	 */
	public void update(NewsColumns column, Integer parentId)
	{
		if(null != parentId)
		{
			NewsColumns parent = getNewsColumnsDao().find(parentId);
			column.setNewscolumns(parent);
		}
		getNewsColumnsDao().update(column);
	}
}
