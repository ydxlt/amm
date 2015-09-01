package com.dabizi.point.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dabizi.point.dao.PointInfoDao;
import com.dabizi.point.model.PointInfo;

import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class PointInfoService extends BaseService<PointInfo> {

	@Override
	@Resource(name="pointInfoDao")
	public void setBaseDao(BaseDao<PointInfo> baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 后台回复时，查询所有留言
	 * @param pageData
	 * @return
	 */
	public PageData<PointInfo> getAll(PageData<PointInfo> pageData)
	{
		return getPointInfoDao().findPage(pageData, "from PointInfo");
	}
	
	public PointInfoDao getPointInfoDao(){
		return (PointInfoDao)baseDao;
	}
	
	public void save(PointInfo pointInfo){
		getPointInfoDao().save(pointInfo);
	}
	
	public void update(PointInfo pointInfo)
	{
		PointInfo info = getPointInfoDao().find(pointInfo.getId());
		getPointInfoDao().update(info);
	}
	
	public void delete(String[] pointInfoIds)
	{
		getPointInfoDao().delete(pointInfoIds);
	}

}
