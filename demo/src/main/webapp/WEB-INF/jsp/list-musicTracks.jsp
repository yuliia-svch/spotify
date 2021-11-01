<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
 <div>
  <a type="button" class="btn btn-primary btn-md" href="/add-musicTrack">Add Music Track</a>
 </div>
 <br>
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
      <th width="10%">Year</th>
      <th width="20%">Category</th>
      <th width="20%"></th>
     </tr>
    </thead>
    <tbody>
     <c:forEach items="${musicTracks}" var="musicTrack">
      <tr>
       <td>${musicTrack.name}</td>
       <td>${musicTrack.author}</td>
       <td>${musicTrack.year}</td>
       <td>${musicTrack.category}</td>
       <td><a type="button" class="btn btn-success"
        href="/update-musicTrack?id=${musicTrack.id}">Update</a>
       <a type="button" class="btn btn-warning"
        href="/delete-musicTrack?id=${musicTrack.id}">Delete</a></td>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

</div>
<%@ include file="common/footer.jspf"%>