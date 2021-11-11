<%@ include file="common/header.jspf"%>
<%@ include file="common/navigationForUser.jspf"%>

<script>
    function choose_playlist(id) {
        window.location = '/choose-playlist?id='+id;
    }

    function see_playlist(id) {
            window.location = '/see-playlist?id='+id;
    }
</script>

<div class="container">
    <div class = "row">
        <div class = "col-md-3 col-md-offset-3">
            <form action="${pageContext.request.contextPath}/add-playlist" method="get">
                <input type="hidden" name="page" value="list-playlists-to-choose">
                <button type="submit" class="btn btn-success btn-md">New Playlist</a>
            </form>
        </div>
        <div class = "col-md-3 col-md-offset-2">
          <form action="${pageContext.request.contextPath}/refresh-playlists" method="post">
              <input type="hidden" name="page" value="list-playlists-to-choose">
              <button type="submit" class="btn btn-primary btn-md">Refresh</a>
          </form>
        </div>
        <div class = "col-md-6 col-md-offset-3">
            <form action="${pageContext.request.contextPath}/search-playlists" method="post">
                <input type="hidden" name="page" value="list-playlists-to-choose">
                <input type="text" name="search" class="form-control"
                    placeholder="Enter the name of playlist"/>
            </form>
        </div>
    </div>
 <br>
 <div class = "row">
 <div class = "col-md-6 col-md-offset-3">
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>Pick Playlist</h3>
  </div>
  <div class="panel-body">
   <table class="table table-hover">
    <thead>
     <tr>
      <th width="100%" class="text-center">Name</th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${playlists}" var="playlist">
      <tr onclick="choose_playlist(${playlist.id})">
       <td class="text-center">${playlist.name}</td>
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