package cn.jxust.base.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.jxust.base.model.User;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;

@Repository
public class UserDao extends BaseDao<User> 
{
	public User findUserByName(String name)
	{
		return find("from User where userName = ?", name);
	}
	
	/**
	 * 通过用户名获取用户列表
	 * @param userType
	 * @return
	 */
	public List<User> findUserByType(String userType){
		return find("from User where userType = ?", userType);
	}
	
	public PageData<User> findAllByPage(int pageNum, User entity)
	{
		PageData<User> pageData = new PageData<User>(pageNum);
		pageData = this.findPage(pageData, "from User");
		return pageData;
	}
}
