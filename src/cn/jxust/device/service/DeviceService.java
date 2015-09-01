package cn.jxust.device.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.device.dao.DeviceDao;
import cn.jxust.device.model.Device;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class DeviceService extends BaseService<Device>
{
	
	public DeviceDao getDeviceDao(){
		return (DeviceDao) baseDao;
	}

	@Override
	@Resource(name="DeviceDao")
	public void setBaseDao(BaseDao<Device> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public PageData<Device> getAll(PageData<Device> pageData)
	{
		return getDeviceDao().findPage(pageData, "from Device");
	}
}
