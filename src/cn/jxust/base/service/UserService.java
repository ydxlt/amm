package cn.jxust.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.dao.UserDao;
import cn.jxust.base.model.User;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;

@Service
@Transactional
public class UserService extends BaseService<User>
{
	@Override
	@Resource(name="userDao")
	public void setBaseDao(BaseDao<User> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public UserDao getUserDao()
	{
		return (UserDao)baseDao;
	}
	
	public User getUserByName(String name)
	{
		return getUserDao().findUserByName(name);
	}
	
	public PageData<User> getAllByPage(int pageNum, User entity)
	{
		return getUserDao().findAllByPage(pageNum, entity);
	}
	
	/**
	 * 修改用户密码，判断当前密码是否正确，再进行密码修改
	 */
	public boolean changePassword(User currentUser, String newPassword, String oldPassword)
	{
		if(currentUser.getPassword().equals(oldPassword))
		{
			currentUser.setPassword(newPassword);
			getUserDao().update(currentUser);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 通过用户类型（A、B、C三类）获取用户列表
	 * @param userType 用户类型
	 * @return
	 */
	public List<User> getUserByType(String userType){
		return getUserDao().findUserByType(userType);
	}
	
	/**
	 * 初始化密码
	 * @param user
	 */
	public void initPassword(int userId){
		User user = getUserDao().find(userId);
		user.setPassword("111111");  //密码初始化为6个“1”。
		getUserDao().update(user);		
	}
	
	public void save(User user, String deptId, String RoleId)
	{
		getUserDao().save(user);
	}
}
