<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<h2>Hey, ${username}</h2>

<a href="${pageContext.request.contextPath}/userPage">Work as user</a>

 <div class="panel panel-primary">
     <div class="panel-heading">Home Page</div>
        <div class="panel-body">
           Welcome!! <a href="${pageContext.request.contextPath}/list-allMusicTracks">Click here</a> to manage music tracks
        </div>
     </div>
 </div>

 <%@ include file="common/footer.jspf"%>
