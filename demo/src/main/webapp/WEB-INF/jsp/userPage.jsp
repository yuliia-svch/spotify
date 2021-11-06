<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>

<h3>User Page</h3>

<script>
    function run(page) {
        var select = document.getElementById('sort_params');
        var option = select.options[select.selectedIndex].text;
        $.ajax({
            type: "GET",
            url: "/sort/"+option+"/"+page,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function () {
                alert("sorted");
            }
        });
    }

    run();

</script>

<div class="container">
    <div>
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
 <br>
 <div class="panel panel-primary">
  <div class="panel-heading">
   <h3>List of music Tracks</h3>
  </div>
  <div class="panel-body">
   <table class="table table-striped">
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
      <tr>
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