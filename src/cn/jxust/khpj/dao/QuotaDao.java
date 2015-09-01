package cn.jxust.khpj.dao;

import java.util.List;

import cn.jxust.khpj.model.Quota;
import cn.jxust.orm.hibernate.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class QuotaDao extends BaseDao<Quota>
{
	public List<Quota> findAll()
	{
		return findList("from Quota order by quotaType, quotaId");
	}
}
