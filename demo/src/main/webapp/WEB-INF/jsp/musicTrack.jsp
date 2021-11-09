<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
 <div class="row">
  <div class="col-md-6 col-md-offset-3 ">
   <div class="panel panel-primary">
    <div class="panel-heading">Add / Update music track</div>
    <div class="panel-body">
     <form:form method="post" modelAttribute="musicTrack">
      <form:hidden path="id" />
      <fieldset class="form-group">
       <form:label path="name">Name</form:label>
       <form:input path="name" type="text" class="form-control"
        required="required" />
       <form:errors path="name" cssClass="text-warning" />
      </fieldset>

      <fieldset class="form-group">
       <form:label path="author">Author</form:label>
       <form:input path="author" type="text" class="form-control"
        required="required" />
       <form:errors path="author" cssClass="text-warning" />
      </fieldset>

      <fieldset class="form-group">
       <form:label path="year">Year</form:label>
       <form:input path="year" type="number" class="form-control"
        required="required" />
       <form:errors path="year" cssClass="text-warning" />
      </fieldset>

      <fieldset class="form-group">
        <form:label path="category">Category</form:label>
        <form:input path="category" type="text" class="form-control"
        required="required" />
        <form:errors path="category" cssClass="text-warning" />
      </fieldset>

      <fieldset class="form-group">
         <form:input path="text" type="textarea" placeholder="Enter text of the song" class="form-control"/>
         <form:errors path="text" cssClass="text-warning" />
      </fieldset>

      <button type="submit" class="btn btn-success">Save</button>
     </form:form>
    </div>
   </div>
  </div>
 </div>
</div>
<%@ include file="common/footer.jspf"%>