<%@ include file="common/header.jspf"%>
<%@ include file="common/navigationForUser.jspf"%>

<script>
    function see_more(id, playlistId) {
        window.location = '/seeMore?id='+id+'&page=see-playlist?id='+playlistId;
    }
</script>

<div class="container">
<h1>${name}</h1>
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of music Tracks</h3>
  </div>
  <div class="panel-body">
   <table class="table table-hover">
    <thead>
     <tr>
      <th width="30%">Name</th>
      <th width="30%">Author</th>
      <th width="10%">Year</th>
      <th width="20%">Category</th>
      <th width="10%"></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${musicTracks}" var="musicTrack">
      <tr onclick="see_more(${musicTrack.id}, ${id})">
       <td>${musicTrack.name}</td>
       <td>${musicTrack.author}</td>
       <td>${musicTrack.year}</td>
       <td>${musicTrack.category}</td>
       <td>
            <form action="${pageContext.request.contextPath}/delete-from-playlist" method="post">
                <input type="hidden" name="musicTrackId" value="${musicTrack.id}">
                <input type="hidden" name="playlistId" value="${id}">
                <button type="submit" class="btn btn-primary btn-md">Delete from playlist</a>
            </form>
        </td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>
<a href="${pageContext.request.contextPath}/all-playlists">Back To All Playlists</a>
</div>

<%@ include file="common/footer.jspf"%>