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
							<li>
								<span class="divider">|</span>
							</li>
							<li class="active">
								意见反馈
							</li>
						</ul>
						<c:if test="${not empty success}">
							<div class="alert alert-success">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<h4>系统提示</h4>
								提交成功！
							</div>
						</c:if>
						<c:if test="${not empty fail}">
							<div class="alert alert-error">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<h4>系统提示</h4>
								提交失败！验证码错误！
							</div>
						</c:if>
						<c:forEach var="message" items="${pageData.result}">
							<table class="table table-hover table-striped table-bordered">
								<thead>
									<tr>
										<th width="50">
											标题
										</th>
										<th>
											${message.title}
										</th>
										<th width="210">
											${message.messName} 发表于 ${message.ltime}
										</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>
											内容
										</td>
										<td colspan="2">
											${message.content}
										</td>
									</tr>
									<tr>
										<td>
											回复
										</td>
										<td colspan="2">
											${message.recontent}
										</td>
									</tr>
								</tbody>
							</table>
							<br/>
						</c:forEach>
						<ul class="pager">
							<li>
								显示 ${pageData.pagination.pageSize} 条每页 共
								${pageData.pagination.totalCount} 条记录
							</li>
							<c:if
								test="${pageData.pagination.totalCount <= pageData.pagination.pageSize}">
								<li class="disabled">
									<a href="javascript:void(0)">上一页</a>
								</li>
								<li>
									当前 ${pageData.pagination.pageNo} 页
								</li>
								<li class="disabled">
									<a href="javascript:void(0)">下一页</a>
								</li>
							</c:if>
							<c:if
								test="${pageData.pagination.totalCount > pageData.pagination.pageSize}">
								<li
									<c:if test="${pageData.pagination.pageNo == 1}">class="disabled"</c:if>>
									<a href="${ctx}/web/messages/replysList.html?pageNumber=${pageData.pagination.pageNo - 1}">上一页</a>
								</li>
								<li>
									当前 ${pageData.pagination.pageNo} 页
								</li>
								<li
									<c:if test="${pageData.pagination.pageNo == totlePage}">class="disabled"</c:if>>
									<a href="${ctx}/web/messages/replysList.html?pageNumber=${pageData.pagination.pageNo + 1}">下一页</a>
								</li>
							</c:if>
						</ul>
						
						<form action="${ctx}/web/messages/save.html" method="post">
							<table class="table table-hover table-striped table-bordered">
								<thead>
									<tr>
										<th colspan="2">发表留言</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td width="50">
											标题
										</td>
										<td>
											<input class="input-block-level" type="text" name="title" />
										</td>
									</tr>
									<tr>
										<td>
											留言人
										</td>
										<td>
											<input type="text" name="messName" />
										</td>
									</tr>
									<tr>
										<td>
											内容
										</td>
										<td>
											<textarea class="input-block-level" rows="5" cols="100" name="content"></textarea>
										</td>
									</tr>
									<tr>
										<td>
											验证码
										</td>
										<td>
											<input class="input-mini" type="text" name="validateCode" /><br/>
											<img src="${ctx}/web/messages/rand.html" id="codeimg" onclick="this.src='${ctx}/web/messages/rand.html?id='+Math.random()*10" title="点击更新验证码" width="75" height="24" />
										</td>
									</tr>
								</tbody>
							</table>
							<input type="submit" value="提交" class="btn btn-primary"/>
						</form>

					</div>
					<div class="span3">
						<!-- 今日天气 -->
						<div>
							<p>
								今天是
								<script type="text/javascript" src="${ctx}/resource/utils/calendar.js"></script>
							</p>
							<iframe
								src="http://m.weather.com.cn/m/pn7/weather.htm?id=101240715T "
								width="195" height="20" allowtransparency="true" marginwidth="0"
								marginheight="0" hspace="0" vspace="0" frameborder="0"
								scrolling="no"></iframe>
							<div>
								<!-- 通知公告 -->
								<div>
									<h5 class="right_title">
										通知公告
									</h5>
									<marquee height="150" direction="up" scrollamount="2" scrolldelay="100" onmouseover=stop() onmouseout=start()>
									<ul>
										<jsp:include page="/list/notice/5.html"></jsp:include>
									</ul>
								</marquee>
								</div>
								<!-- 指标发布 -->
								<div>
									<h5 class="right_title">
										动态指标发布
									</h5>
									<ul>
										<jsp:include page="/list/zbfb/5.html"></jsp:include>
									</ul>
								</div>
								<!-- 友情链接 -->
								<div>
									<h5 class="right_title">
										友情链接
									</h5>
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