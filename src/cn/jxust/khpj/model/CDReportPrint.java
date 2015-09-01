package cn.jxust.khpj.model;

import java.util.Map;

public class CDReportPrint {
	private Map<Integer, CDReport> cdMap;
	private String month;
	
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the cdMap
	 */
	public Map<Integer, CDReport> getCdMap() {
		return cdMap;
	}
	/**
	 * @param cdMap the cdMap to set
	 */
	public void setCdMap(Map<Integer, CDReport> cdMap) {
		this.cdMap = cdMap;
	}
}
