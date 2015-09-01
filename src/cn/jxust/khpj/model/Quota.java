/**
 * 
 */
package cn.jxust.khpj.model;

/**
 * @author Administrator
 * 指标表
 */
public class Quota {

    private String quotaId;
    private String quotaName;
    private Double quotaScore;   //指标值
    private String quotaDesc;
    private String quotaType;    //指标类型，分为固定指标和动态指标
    
    /**
     * @return the quotaId
     */
    public String getQuotaId() {
        return quotaId;
    }
    /**
     * @param quotaId the quotaId to set
     */
    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
    }
    /**
     * @return the quotaName
     */
    public String getQuotaName() {
        return quotaName;
    }
    /**
     * @param quotaName the quotaName to set
     */
    public void setQuotaName(String quotaName) {
        this.quotaName = quotaName;
    }
    /**
     * @return the quotaScore
     */
    public Double getQuotaScore() {
        return quotaScore;
    }
    /**
     * @param quotaScore the quotaScore to set
     */
    public void setQuotaScore(Double quotaScore) {
        this.quotaScore = quotaScore;
    }
    /**
     * @return the quotaDesc
     */
    public String getQuotaDesc() {
        return quotaDesc;
    }
    /**
     * @param quotaDesc the quotaDesc to set
     */
    public void setQuotaDesc(String quotaDesc) {
        this.quotaDesc = quotaDesc;
    }
    /**
     * @return the quotaType
     */
    public String getQuotaType() {
        return quotaType;
    }
    /**
     * @param quotaType the quotaType to set
     */
    public void setQuotaType(String quotaType) {
        this.quotaType = quotaType;
    }
}
