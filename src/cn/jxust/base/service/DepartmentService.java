package cn.jxust.base.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxust.base.dao.DepartmentDao;
import cn.jxust.base.model.Department;
import cn.jxust.orm.PageData;
import cn.jxust.orm.hibernate.BaseDao;
import cn.jxust.orm.hibernate.BaseService;
import cn.jxust.utils.JsonUtils;

@Service
@Transactional
public class DepartmentService extends BaseService<Department>
{
	@Override
	@Resource(name="departmentDao")
	public void setBaseDao(BaseDao<Department> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	public DepartmentDao getDepartmentDao()
	{
		return (DepartmentDao)baseDao;
	}
	
	public List<Department> getAll()
	{
		return getDepartmentDao().findList("from Department order by departmentType,ordNum");
	}
	
	public PageData<Department> getAllByPage(int pageNum, Department entity)
	{
		return getDepartmentDao().findAllByPage(pageNum, entity);
	}
	
	public List<Department> getByType(String type)
	{
		return getDepartmentDao().findByType(type);
	}
	
	public Department getById(String id)
	{
		return getDepartmentDao().find(id);
	}
	
	public List<Department> getByFixedQuota()
	{
		return getDepartmentDao().findByFixedQuota();
	}
	
	/**
	 * 获取单位累计排名
	 * @param deptType
	 * @return
	 */
	public Integer getTotalScoreByDept(Department dept)
	{
		if(null == dept.getTotalScore() || dept.getTotalScore() == 0)
		{
			return 1;
		}
		List<Department> depts = getDepartmentDao().findTotalScore(dept.getDepartmentType());
		int index = 0;
		double score = 0d;
		for(Department d : depts)
		{
			if(d.getTotalScore() != score)
			{
				score = d.getTotalScore();
				index++;
			}
			if(d.getDepartmentId().equals(dept.getDepartmentId()))
			{
				break;
			}
		}
		return index;
	}
	
	/**
	 * 获取单位累计排名指数
	 * @param dept
	 * @return
	 */
	public Double getTotalScoreRankByDept(Department dept)
	{
		if(null == dept.getTotalScore() || dept.getTotalScore() == 0)
		{
			return 1d;
		}
		List<Department> depts = getDepartmentDao().findTotalScore(dept.getDepartmentType());
		int size = depts.size();
		int index = 0;
		double score = 0d;
		for(Department d : depts)
		{
			if(d.getTotalScore() != score)
			{
				score = d.getTotalScore();
				index++;
			}
			if(d.getDepartmentId().equals(dept.getDepartmentId()))
			{
				break;
			}
		}
		double rank = new Double(size - index + 1) / size;
		return rank;
	}
	
	public List<Department> getTotalScore(String deptType)
	{
		return getDepartmentDao().findTotalScore(deptType);
	}
	
	public List<Department> getTotalScore(String deptType, int num)
	{
		List<Department> depts = getDepartmentDao().findTotalScore(deptType);
		if(depts.size() > num)
		{
			return depts.subList(0, num);
		}
		else
		{
			return depts;
		}
	}
	
	/**
	 * 将deptDesc拆分成Map对象
	 * @param deptDesc
	 * @return
	 */
	public Map<String, Object> convenDescToMap(String deptDesc)
	{
		if(null != deptDesc && !"".equals(deptDesc.trim()))
		{
			return JsonUtils.string2map(deptDesc);
		}
		return null;
	}
	
	public void updateDetails(String deptId, Map<String, Object> value)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(null==value.get("3") || "".equals(value.get("3")))
		{
			sb.append("辖区总人口数:");
			sb.append("'");
			if(null != value.get("1") && !"".equals(value.get("1")))
			{
				sb.append(value.get("1"));
			}
			sb.append("'");
			sb.append(",");
			sb.append("村数:");
			sb.append("'");
			if(null != value.get("2") && !"".equals(value.get("2")))
			{
				sb.append(value.get("2"));
			}
			sb.append("'");
			sb.append(",");
		}
		else
		{
			sb.append("单位职工数:");
			sb.append("'");
			if(null != value.get("3") && !"".equals(value.get("3")))
			{
				
				sb.append(value.get("3"));
			}
			sb.append("'");
			sb.append(",");
		}
		
		sb.append("主要领导:");
		sb.append("'");
		if(null != value.get("4") && !"".equals(value.get("4")))
		{
			sb.append(value.get("4"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("分管领导:");
		sb.append("'");
		if(null != value.get("5") && !"".equals(value.get("5")))
		{
			sb.append(value.get("5"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("综治工作人员1:");
		sb.append("'");
		if(null != value.get("6") && !"".equals(value.get("6")))
		{
			sb.append(value.get("6"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("职务1:");
		sb.append("'");
		if(null != value.get("7") && !"".equals(value.get("7")))
		{
			sb.append(value.get("7"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("办公地址:");
		sb.append("'");
		if(null != value.get("8") && !"".equals(value.get("8")))
		{
			sb.append(value.get("8"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("主要领导职务:");
		sb.append("'");
		if(null != value.get("9") && !"".equals(value.get("9")))
		{
			sb.append(value.get("9"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("主要领导联系电话:");
		sb.append("'");
		if(null != value.get("10") && !"".equals(value.get("10")))
		{
			sb.append(value.get("10"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("分管领导职务:");
		sb.append("'");
		if(null != value.get("11") && !"".equals(value.get("11")))
		{
			sb.append(value.get("11"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("分管领导联系电话:");
		sb.append("'");
		if(null != value.get("12") && !"".equals(value.get("12")))
		{
			sb.append(value.get("12"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("联系电话1:");
		sb.append("'");
		if(null != value.get("13") && !"".equals(value.get("13")))
		{
			sb.append(value.get("13"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("综治工作人员2:");
		sb.append("'");
		if(null != value.get("14") && !"".equals(value.get("14")))
		{
			sb.append(value.get("14"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("综治工作人员3:");
		sb.append("'");
		if(null != value.get("15") && !"".equals(value.get("15")))
		{
			sb.append(value.get("15"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("综治工作人员4:");
		sb.append("'");
		if(null != value.get("16") && !"".equals(value.get("16")))
		{
			sb.append(value.get("16"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("综治工作人员5:");
		sb.append("'");
		if(null != value.get("17") && !"".equals(value.get("17")))
		{
			sb.append(value.get("17"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("职务2:");
		sb.append("'");
		if(null != value.get("18") && !"".equals(value.get("18")))
		{
			sb.append(value.get("18"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("职务3:");
		sb.append("'");
		if(null != value.get("19") && !"".equals(value.get("19")))
		{
			sb.append(value.get("19"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("职务4:");
		sb.append("'");
		if(null != value.get("20") && !"".equals(value.get("20")))
		{
			sb.append(value.get("20"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("职务5:");
		sb.append("'");
		if(null != value.get("21") && !"".equals(value.get("21")))
		{
			sb.append(value.get("21"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("联系电话2:");
		sb.append("'");
		if(null != value.get("22") && !"".equals(value.get("22")))
		{
			sb.append(value.get("22"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("联系电话3:");
		sb.append("'");
		if(null != value.get("23") && !"".equals(value.get("23")))
		{
			sb.append(value.get("23"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("联系电话4:");
		sb.append("'");
		if(null != value.get("24") && !"".equals(value.get("24")))
		{
			sb.append(value.get("24"));
		}
		sb.append("'");
		sb.append(",");
		
		sb.append("联系电话5:");
		sb.append("'");
		if(null != value.get("25") && !"".equals(value.get("25")))
		{
			sb.append(value.get("25"));
		}
		sb.append("'");
		
		sb.append("}");
		
		Department dept = find(deptId);
		dept.setDepartmentDesc(sb.toString());
		update(dept);
	}
	
	/**
	 * 显示dept details
	 * @param deptId
	 * @return
	 */
	public String viewDetails(String deptId)
	{
		StringBuilder sb = new StringBuilder();
		Department dept = find(deptId);
		Map<String, Object> desc = JsonUtils.string2map(dept.getDepartmentDesc());
//		Iterator<String> it = desc.keySet().iterator();
//		while(it.hasNext())
//		{
//			String key = it.next();
//			sb.append("<strong>");
//			sb.append(key);
//			sb.append("</strong>");
//			sb.append(":");
//			sb.append(desc.get(key));
//			sb.append("<br/>");
//		}
		if(!desc.isEmpty())
		{
			if(desc.containsKey("辖区总人口数"))
			{
				sb.append("<div class=\"row-fluid\">");
				sb.append("辖区总人口数");
				sb.append(desc.get("辖区总人口数"));
				
				sb.append("&nbsp;&nbsp;村（居）数");
				sb.append(desc.get("村数"));
				sb.append("</div>");
			}
			else
			{
				sb.append("<div class=\"row-fluid\">单位职工数");
				sb.append(desc.get("单位职工数"));
				sb.append("</div>");
			}
			sb.append("<br/>");
			sb.append("<div>");
			sb.append("<strong>主要领导：</strong>");
			sb.append(desc.get("主要领导"));
			sb.append("&nbsp;&nbsp;[");
			sb.append(desc.get("主要领导职务"));
			sb.append("]<br/><strong>联系电话：</strong>");
			sb.append(desc.get("主要领导联系电话"));
			sb.append("</div>");
			sb.append("<div>");
			sb.append("<strong>分管领导：</strong>");
			sb.append(desc.get("分管领导"));
			sb.append("&nbsp;&nbsp;[");
			sb.append(desc.get("分管领导职务"));
			sb.append("]<br/><strong>联系电话：</strong>");
			sb.append(desc.get("分管领导联系电话"));
			sb.append("</div>");
			sb.append("<div>");
			sb.append("<strong>综治工作人员：</strong>");
			sb.append("<br/>");
			if(!"".equals(desc.get("综治工作人员1")))
			{
				sb.append((null == desc.get("综治工作人员1"))?"":desc.get("综治工作人员1"));
				sb.append("&nbsp;&nbsp;[");
				sb.append((null == desc.get("职务1"))?"":desc.get("职务1"));
				sb.append("]&nbsp;&nbsp;");
				sb.append((null == desc.get("联系电话1"))?"":desc.get("联系电话1"));
				sb.append("<br/>");
			}
			if(!"".equals(desc.get("综治工作人员2")))
			{
				sb.append((null == desc.get("综治工作人员2"))?"":desc.get("综治工作人员2"));
				sb.append("&nbsp;&nbsp;[");
				sb.append((null == desc.get("职务2"))?"":desc.get("职务2"));
				sb.append("]&nbsp;&nbsp;");
				sb.append((null == desc.get("联系电话2"))?"":desc.get("联系电话2"));
				sb.append("<br/>");
			}
			if(!"".equals(desc.get("综治工作人员3")))
			{
				sb.append((null == desc.get("综治工作人员3"))?"":desc.get("综治工作人员3"));
				sb.append("&nbsp;&nbsp;[");
				sb.append((null == desc.get("职务3"))?"":desc.get("职务3"));
				sb.append("]&nbsp;&nbsp;");
				sb.append((null == desc.get("联系电话3"))?"":desc.get("联系电话3"));
				sb.append("<br/>");
			}
			if(!"".equals(desc.get("综治工作人员4")))
			{
				sb.append((null == desc.get("综治工作人员4"))?"":desc.get("综治工作人员4"));
				sb.append("&nbsp;&nbsp;[");
				sb.append((null == desc.get("职务4"))?"":desc.get("职务4"));
				sb.append("]&nbsp;&nbsp;");
				sb.append((null == desc.get("联系电话4"))?"":desc.get("联系电话4"));
				sb.append("<br/>");
			}
			if(!"".equals(desc.get("综治工作人员5")))
			{
				sb.append((null == desc.get("综治工作人员5"))?"":desc.get("综治工作人员5"));
				sb.append("&nbsp;&nbsp;[");
				sb.append((null == desc.get("职务5"))?"":desc.get("职务5"));
				sb.append("]&nbsp;&nbsp;");
				sb.append((null == desc.get("联系电话5"))?"":desc.get("联系电话5"));
				sb.append("<br/>");
			}
			sb.append("</div>");
			sb.append("<div>");
			sb.append("<strong>办公地址：</strong>");
			sb.append(desc.get("办公地址"));
			sb.append("</div>");
		}
		return sb.toString();
	}
}
