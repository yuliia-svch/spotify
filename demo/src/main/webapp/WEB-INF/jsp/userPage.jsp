<%@ include file="common/header.jspf"%>
<%@ include file="common/navigationForUser.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
    function see_more(id) {
        window.location = '/seeMore?id='+id+'&page=userPageAll';
    }
</script>

<h3>User Page</h3>

<div class="container">
<div class = "row">
    <div class = "col-xs-4">
          <form action="${pageContext.request.contextPath}/refresh" method="post">
              <input type="hidden" name="page" value="userPage">
              <button type="submit" class="btn btn-primary btn-md">Refresh</a>
          </form>
    </div>
    <div class = "col-xs-4">
        <form action="${pageContext.request.contextPath}/sort" method="post">
            <input type="hidden" name="page" value="userPage">
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
                <input type="hidden" name="page" value="userPage">
                <input type="text" name="search" class="form-control"
                        onkeydown="this.form.submit();" placeholder="Enter the name or author"/>
            </form>
    </div>
</div>
<div = "row">
      <form action="${pageContext.request.contextPath}/search-by-substring" method="post">
          <input type="hidden" name="page" value="userPage">
          <input type="text" name="search" class="form-control"
                placeholder="Enter the part of text"/>
      </form>
 </div>
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
      <tr onclick="see_more(${musicTrack.id})">
       <td>${musicTrack.name}</td>
       <td>${musicTrack.author}</td>
       <td>${musicTrack.year}</td>
       <td>${musicTrack.category}</td>
       <td><a type="button" class="btn btn-success"
        href="/add-to-playlist?id=${musicTrack.id}">Add to my playlist</a>
      </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </div>

</div>

<%@ include file="common/footer.jspf"%>