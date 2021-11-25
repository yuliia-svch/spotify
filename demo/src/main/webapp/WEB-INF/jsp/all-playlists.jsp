<%@ include file="common/header.jspf"%>
<%@ include file="common/navigationForUser.jspf"%>

<div class="container">
    <div class = "row">
        <div class = "col-xs-4">
            <form action="${pageContext.request.contextPath}/add-playlist" method="get">
                <input type="hidden" name="page" value="all-playlists">
                <button type="submit" class="btn btn-primary btn-md">Add Playlist</a>
            </form>
        </div>
        <div class = "col-xs-4">
          <form action="${pageContext.request.contextPath}/refresh-playlists" method="post">
              <input type="hidden" name="page" value="all-playlists">
              <button type="submit" class="btn btn-primary btn-md">Refresh</a>
          </form>
        </div>
        <div class = "col-xs-4">
            <form action="${pageContext.request.contextPath}/search-playlists" method="post">
                <input type="hidden" name="page" value="all-playlists">
                <input type="text" name="search" class="form-control"
                    placeholder="Enter the name of playlist"/>
            </form>
        </div>
    </div>
 <br>
 <div class = "row">
 <div class = "col-md-12">
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>Your Playlists</h3>
  </div>
  <div class="panel-body">
   <table class="table table-hover">
    <thead>
     <tr>
      <th width="70%">Name</th>
      <th width="30%"></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${playlists}" var="playlist">
      <tr>
       <td>${playlist.name}</td>
       <td><a type="button" class="btn btn-success"
            href="/see-playlist?id=${playlist.id}">See more</a>
        <a type="button" class="btn btn-warning"
            href="/delete-playlist?id=${playlist.id}">Delete playlist</a>
       </td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>
 </div>
 </div>

</div>

<%@ include file="common/footer.jspf"%>