/**
 * 
 */
package cn.jxust.web.model;

/**
 * @author Jephirus
 * 
 */
public class Messages {
	String messageId;
	String title;
	String content;   // 留言内容
	String ltime;     // 时间
	String messName;      // 留言人姓名
	String recontent; // 回复内容
	String replyFlag;  //回复标记, "0"为未回复，“1”为已回复
	String replyor;   // 回复人

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId
	 *            the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the ltime
	 */
	public String getLtime() {
		return ltime;
	}

	/**
	 * @param ltime
	 *            the ltime to set
	 */
	public void setLtime(String ltime) {
		this.ltime = ltime;
	}

	/**
	 * @return the recontent
	 */
	public String getRecontent() {
		return recontent;
	}

	/**
	 * @param recontent
	 *            the recontent to set
	 */
	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

	/**
	 * @return the replyor
	 */
	public String getReplyor() {
		return replyor;
	}

	/**
	 * @param replyor the replyor to set
	 */
	public void setReplyor(String replyor) {
		this.replyor = replyor;
	}

	/**
	 * @return the messName
	 */
	public String getMessName() {
		return messName;
	}

	/**
	 * @param messName the messName to set
	 */
	public void setMessName(String messName) {
		this.messName = messName;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the replyFlag
	 */
	public String getReplyFlag() {
		return replyFlag;
	}

	/**
	 * @param replyFlag the replyFlag to set
	 */
	public void setReplyFlag(String replyFlag) {
		this.replyFlag = replyFlag;
	}

}
