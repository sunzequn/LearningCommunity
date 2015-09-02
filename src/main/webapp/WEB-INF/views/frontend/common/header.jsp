<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div id="header">
		<div class="container">
			<div id="logo">
			<a href="/LearningCommunity/index"><img src="/LearningCommunity/resources/images/lstlogo.png" alt="LOGO" width="140" height="60"></a>
				
			</div>
			<div class="header-menu">
				<ul class="nav-item">
					<li><a href="/LearningCommunity/course/courses" <c:if test="${module eq 'course'}">class="active"</c:if> >课程</a></li>
					<li><a href="/LearningCommunity/question/questions" <c:if test="${module eq 'question'}">class="active"</c:if>>讨论</a></li>
					<li><a href="/LearningCommunity/blog/blogs"  <c:if test="${module eq 'blog'}">class="active"</c:if>>文章</a></li>
					<li><a href="/LearningCommunity/editor/index"  <c:if test="${module eq 'editor'}">class="active"</c:if>>在线运行</a></li>
				</ul>
			</div>

			<div id="login-area">
				<c:choose>
					<c:when test="${empty loginUser}">
						<ul class="header-unlogin">
							<li class="header-signin"><a href="javascript:showLoginBox()" id="js-signin-btn">登录</a></li>
							<li class="header-signup"><a href="javascript:showRegisterBox()" id="js-signup-btn">注册</a></li>
						</ul>					
					</c:when>
					<c:otherwise>
						<ul class="header-login">
							<li class="notification"><a href="/LearningCommunity/user/notification/letter"><c:if test="${notify_letters+notify_friends gt 0}"><span class="badge bg-red r">${notify_letters+notify_friends}</span></c:if></a></li>
											<li class="user-avatar-list">
						<a href="" class="user-avatar"><img class="avatar-32" src="/LearningCommunity/read/avatar/${loginUser.userId }" alt="" width="32" height="32"></a>
						<div class="user-card">
							<div class="card-inner clearfix">
								<div class="card-top clearfix">
									<span class="name">${loginUser.userName}</span>
								</div>
								<ul class="card-link">
									<li><a href="/LearningCommunity/user/${loginUser.userId}/course">个人中心</a></li>
									<li><a href="/LearningCommunity/feedback/add">建议反馈</a></li>
									<li><a href="/LearningCommunity/user/logout">退出登录</a></li>
								</ul>
								<i class="card-arr"></i>
							</div>
						</div>	
					</li>
						</ul>					
					</c:otherwise>
				</c:choose>
			</div>
			<div id="search-area">
	            <form action="/LearningCommunity/search/search" name="search-form" method="get">
		            <input class="js-input-keyword search-input" placeholder="搜索课程、问答" type="text" autocomplete="off" name="key" value="">
		            <input type="text" name="type" value="course"  style="display: none;"/>
		            <input type="submit"  value="" class="btn_search js-btn-search">
		            <dl class="search-area-result" style="display: none;"></dl>
	            </form>
	        </div>
        </div>
	</div>