<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>


<script>
    function see_more(id) {
        window.location = '/seeMore?id='+id+'&page=list-allMusicTracks';
    }
</script>

<div class="container">
<a href="${pageContext.request.contextPath}/userPageAll">Work as user</a>
<br/>
<br/>
    <div class = "row">
        <div class = "col-xs-2">
          <a type="button" class="btn btn-primary btn-md" href="/add-musicTrack">Add Music Track</a>
        </div>
        <div class = "col-xs-2">
          <form action="${pageContext.request.contextPath}/refresh" method="post">
              <input type="hidden" name="page" value="adminPage">
              <button type="submit" class="btn btn-primary btn-md">Refresh</a>
          </form>
        </div>
        <div class = "col-xs-4">
           <form action="${pageContext.request.contextPath}/sort" method="post">
              <input type="hidden" name="page" value="adminPage">
              <select name="option" onchange="this.form.submit();" class="form-control">
                 <option value="sort by">Sort by: </option>
                 <option value="name">name</option>
                 <option value="author">author</option>
                 <option value="year">year</option>
                 <option value="category">category</option>
              </select>
           </form>
        </div>
        <div class = "col-xs-4">
            <form action="${pageContext.request.contextPath}/search" method="post">
                <input type="hidden" name="page" value="adminPage">
                <input type="text" name="search" class="form-control"
                    placeholder="Enter the name, author or category"/>
            </form>
        </div>
    </div>
 <br>
 <div = "row">
      <form action="${pageContext.request.contextPath}/search-by-substring" method="post">
          <input type="hidden" name="page" value="adminPage">
          <input type="text" name="search" class="form-control"
                placeholder="Enter the part of text"/>
      </form>
 </div>
 <div class = "row">
 <div class = "col-md-12">
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of music Tracks</h3>
  </div>
  <div class="panel-body">
   <table class="table table-hover">
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
      <tr onclick="see_more(${musicTrack.id})">
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
 </div>

</div>

 <%@ include file="common/footer.jspf"%>
