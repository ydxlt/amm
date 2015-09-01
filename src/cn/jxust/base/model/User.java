package cn.jxust.base.model;

import java.io.Serializable;
import java.util.List;

import cn.jxust.base.model.Role;

public class User implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6402654083259438236L;
    private Integer userId;
    private String userName = null;
    private String phoneNumber = null;
    private String password = null;
    private Department userDep = null;
    private boolean enabled = true;
    private String userDesc = null;
    private String userType;
	private List<Role> userRole; 	     //用户对应的角色
    
    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserType()
	{
		return userType;
	}

	public void setUserType(String userType)
	{
		this.userType = userType;
	}

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Integer getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    public String getUserName() {
	return userName;
    }

	/**
	 * @return the userDesc
	 */
	public String getUserDesc() {
		return userDesc;
	}

	/**
	 * @param userDesc the userDesc to set
	 */
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	/**
	 * @return the userRole
	 */
	public List<Role> getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(List<Role> userRole) {
		this.userRole = userRole;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userDep
	 */
	public Department getUserDep() {
		return userDep;
	}

	/**
	 * @param userDep the userDep to set
	 */
	public void setUserDep(Department userDep) {
		this.userDep = userDep;
	}

}