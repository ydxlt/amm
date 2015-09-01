<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>中共定南县政法委平安建设网</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container">
		<div class="container mainbody">
			<%@ include file="/WEB-INF/pages/common/head.jsp"%>
			<!--Body content-->
			<div class="row-fluid">
				<div class="span9">
					<ul class="breadcrumb">
						<li><span class="divider">|</span>
						</li>
						<li class="active"><a href="${ctx}/${news.newscolumns.columnType}/${news.newscolumns.columnCode}.html">${news.newscolumns.columnName}</a>
						<span class="divider">|</span>
						</li>
						<li>查看详情</li>
					</ul>
					
					<h4 align="center"><strong>${news.title}</strong></h4>
					<hr />
					<div class="row-fluid">
						<p class="text-center">
							<c:if test="${not empty news.cdate}">
								日期：${news.cdate}
							</c:if>
							<c:if test="${not empty news.newsdetails.from}">
								来源：${news.newsdetails.from} 
							</c:if>
							<c:if test="${not empty news.newsdetails.author}">
								作者：${news.newsdetails.author}
							</c:if>
						</p>
					</div>
					<br/>
					<div class="row-fluid">
						${news.newsdetails.content}
					</div>
				</div>
				<div class="span3">
					<!-- 今日天气 -->
					<div>
						<p>
						今天是
						<script type="text/javascript" src="${ctx}/resource/utils/calendar.js"></script>
						</p>
						<iframe src="http://m.weather.com.cn/m/pn7/weather.htm?id=101240715T " width="195" height="20" allowtransparency="true" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no"></iframe>
						<div>
							<!-- 通知公告 -->
							<div>
								<h5 class="right_title">通知公告</h5>
								<marquee height="150" direction="up" scrollamount="2" scrolldelay="100" onmouseover=stop() onmouseout=start()>
									<ul>
										<jsp:include page="/list/notice/5.html"></jsp:include>
									</ul>
								</marquee>
							</div>
							
							<!-- 指标发布 -->
							<div>
								<h5 class="right_title">动态指标发布</h5>
								<ul>
									<jsp:include page="/list/zbfb/5.html"></jsp:include>
								</ul>
							</div>
							
							<div>
								<h5 class="right_title">友情链接</h5>
								<select onchange="javascript:window.open(this.options[this.selectedIndex].value)" target="_blank">
									<option>=======友情链接=======</option>
									<option value="http://www.chinapeace.org.cn/">中国长安网</option>
									<option value="http://www.jxzfw.gov.cn/">江西政法网</option>
									<option value="http://gz.jxzfw.gov.cn/Index.html">赣州政法网</option>
									<option value="http://www.gzdw.gov.cn/n289/n429/">中共赣州市委政法委</option>
									<option value="http://www.dingnan.gov.cn/">定南县人民政府网</option>
								</select>
							</div>
							<!-- /友情链接 -->
						</div>
						<!-- /span3 -->
					</div>
					<!-- /Body content -->
				</div>
			</div>
			<!-- /body content -->
		</div>
		<!-- /container-fluid -->

		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<!-- /container -->
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
</body>
</html>