<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<a href="${pageContext.request.contextPath}/userPage">JD User</a> | <a href="${pageContext.request.contextPath}/adminPage">JD Admin</a> | <a href="javascript:document.getElementById('logout').submit()">Logout</a>

<h3>Welcome to Spotify</h3>
<div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of music Tracks</h3>
  </div>
  <div class="panel-body">
   <table class="table table-striped">
    <thead>
     <tr>
      <th width="25%">Name</th>
      <th width="25%">Author</th>
      <th width="15%">Year</th>
      <th width="25%">Category</th>
      <th width="10%"></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${musicTracks}" var="musicTrack">
      <tr>
       <td>${musicTrack.name}</td>
       <td><${musicTrack.author}</td>
       <td><${musicTrack.year}</td>
       <td><${musicTrack.category}</td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

<c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>