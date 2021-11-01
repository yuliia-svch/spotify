<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<h3>Admin Page</h3>

 <div class="panel panel-primary">
     <div class="panel-heading">Home Page</div>
        <div class="panel-body">
           Welcome!! <a href="${pageContext.request.contextPath}/list-musicTracks">Click here</a> to manage music tracks
        </div>
     </div>
 </div>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
	<a href="javascript:document.getElementById('logout').submit()">Logout</a>
</c:if>