<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>${webName}</title>

<link rel="shortcut icon" href="${ctx}/favicon.ico" />
<link href="${ctx}/resource/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${ctx}/resource/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${ctx}/resource/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print" />
<link href="${ctx}/resource/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen" />
<!--[if IE]>
<link href="${ctx}/resource/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${ctx}/resource/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->


<script src="${ctx}/resource/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/xheditor/xheditor-1.1.14-zh-cn.min.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<script src="${ctx}/resource/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx}/resource/dwz/js/dwz.print.js" type="text/javascript"></script>

<script src="${ctx}/resource/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		DWZ.init("${ctx}/resource/dwz/dwz.frag.xml", {
			loginUrl : "login_dialog.php",
			loginTitle : "登录", // 弹出登录对话框
			//		loginUrl:"login.php",	// 跳到登录页面
			statusCode : {
				ok : 200,
				error : 300,
				timeout : 301
			}, //【可选】
			pageInfo : {
				pageNum : "pageNum",
				numPerPage : "numPerPage",
				orderField : "orderField",
				orderDirection : "orderDirection"
			}, //【可选】
			debug : false, // 调试模式 【true|false】
			callback : function() {
				initEnv();
				$("#themeList").theme({
					themeBase : "${ctx}/resource/dwz/themes"
				}); // themeBase 相对于index页面的主题base路径
			}
		});
	});
	
</script>

<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&ak=NQDWZkWn81HuP0vEQMKUYvQf"></script>
<script type="text/javascript" src="${ctx}/resource/baiduMap/BaiduMapInfoBox.js"></script>
<script type="text/javascript" src="${ctx}/resource/baiduMap/MapNode.js"></script>

<style type="text/css">
#allmap{height:100%;width:100%;}
.paiban {
	background: url('${ctx}/resource/paiban.png') no-repeat;
}

.infoBoxContent{font-size:12px;}
.infoBoxContent .title{background:url(title.jpg) no-repeat;height:42px;width:272px;}
.infoBoxContent .title strong{font-size:14px;line-height:42px;padding:0 10px 0 5px;}
.infoBoxContent .title .price{color:#FFFF00;}
.infoBoxContent .list{width:280px; height:216px;background:url(/amm/resource/baiduMap/img/tankuang_bj.png) no-repeat;}
.infoBoxContent .list ul{margin:0;padding:0px;list-style:none;}
.infoBoxContent .list ul li {float:right;width:250px; height:33px; line-height:33px;padding:2px 0; margin-left:20px;}
.infoBoxContent .list ul h2{width:260px;height:33px;line-height:33px;margin:0px auto 0px auto;padding:0px;font-size:15px;font-weight:bold;color:#3399cc;text-align:center;border-bottom:1px solid #f2f2f2;}

.infoBoxContent .list ul .last{border:none;}
.infoBoxContent .list ul img{width:20px;height:20px;margin:5px; margin-right:10px;}
.infoBoxContent .left{float:left; font-size:14px;}
.infoBoxContent .left a{ display:block;float:left;text-align:center;width:90px;height:26px;line-height:26px;border-radius:5px;margin-left:70px; margin-top:5px;background-color:#f27c3b; color:#FFFFFF;}
.infoBoxContent .rmb{float:right;color:#EB6100;font-size:14px;font-weight:bold;}

</style>

</head>

<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<!-- <a class="logo" href="http://j-ui.com">标志</a> -->
				<br /> <span style="font-size: 20px;color: #FFF;">&nbsp;&nbsp;${webName}</span>
				<ul class="nav">
					<li><span style="color: #FFF;">您好！欢迎使用本系统</span></li>
					<li><a href="changePw.php" target="dialog" width="540" height="220" rel="changepwd">修改密码</a></li>
					<li><a href="login.php">退出</a></li>
				</ul>

				<ul class="themeList" id="themeList">
					<li theme="default"><div class="selected">蓝色</div></li>
					<li theme="green"><div>绿色</div></li>
					<!-- <li theme="red"><div>红色</div></li> -->
					<li theme="purple"><div>紫色</div></li>
					<li theme="silver"><div>银色</div></li>
					<li theme="azure"><div>天蓝</div></li>
				</ul>
			</div>

			<!-- navMenu -->

		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>主菜单</h2>
					<div>收缩</div>
				</div>


				<div class="accordion" fillSpace="sidebar">

					<sec:authorize ifAllGranted="LABEL_SYS_ADMIN">

						<div class="accordionHeader">
							<h2>
								<span>Folder</span>基础信息管理
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a href="${ctx}/base/config/list.php" target="navTab" rel="configList">系统设置</a>
								</li>
								<li><a>用户管理</a>
									<ul>
										<li><a href="${ctx}/base/user/list.php" target="navTab" rel="userList">用户维护</a></li>
									</ul>
								</li>

								<li><a>单位信息管理</a>
									<ul>
										<li><a href="${ctx}/base/department/list.php" target="navTab" rel="departmentList">单位信息维护</a></li>
									</ul>
								</li>
								<li><a>短信信息管理</a>
									<ul>
										<li><a href="${ctx}/shortMessage/list.php" target="navTab" rel="mapList">短信维护</a></li>
									</ul>
								</li>
							</ul>
						</div>

						<div class="accordionHeader">
							<h2>
								<span>Folder</span>设备监控管理
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a>设备监控管理</a>
									<ul>
										<li><a href="${ctx}/device/list.php" target="navTab" rel="quotaList1">设备列表</a></li>
									</ul>
								</li>
							</ul>
						</div>

						<div class="accordionHeader">
							<h2>
								<span>Folder</span>设备信息管理
							</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a>设备信息维</a>
									<ul>
										<li><a href="${ctx}/web/newsColumns/list.php" target="navTab" rel="newsColumnsList">设备信息查询</a></li>
									</ul>
								</li>
							</ul>
						</div>

						<div class="accordionHeader">
							<h2>
								<span>Folder</span>记录查询
							</h2> 
						</div>

						<div class="accordionContent">
							<ul class="tree">
								<li><a href="${ctx}/web/messages/replyList.php" target="navTab" rel="replyList">记录查询</a></li>
							</ul>
						</div>
					</sec:authorize>
					
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span> </span> </a></li>
						</ul>
					</div>
					
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox" layoutH="90" >
					<!-- 百度地图start -->
						<div id="allmap"></div>
					<!-- 百度地图end -->
				</div>
			</div>
		</div>
	</div>
	
	<div id="footer">
		Copyright &copy; 2013 <a href="javascript:test();">${webName}</a> Power by ie.jxust.cn
	</div>
	
	

</body>
<script type="text/javascript">
	window.onload = loadScript;   // 加载地图
</script>
</html>
