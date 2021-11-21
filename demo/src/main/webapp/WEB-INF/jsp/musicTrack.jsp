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
      <spring:bind path="name">
      <div class="form-group ${status.error ? 'has-error' : ''}">
       <form:label path="name">Name</form:label>
       <form:input path="name" type="text" class="form-control"
        required="required" />
       <form:errors path="name" cssClass="text-warning" />
      </div>
    </spring:bind>

      <spring:bind path="author">
      <div class="form-group ${status.error ? 'has-error' : ''}">
       <form:label path="author">Author</form:label>
       <form:input path="author" type="text" class="form-control"
        required="required" />
       <form:errors path="author" cssClass="text-warning" />
      </div>
    </spring:bind>

        <spring:bind path="year">
      <div class="form-group ${status.error ? 'has-error' : ''}">
       <form:label path="year">Year</form:label>
       <form:input path="year" type="number" class="form-control"
        required="required" min="1600" max="2021"/>
       <form:errors path="year" cssClass="text-warning" />
      </div>
    </spring:bind>

    <spring:bind path="category">
      <div class="form-group ${status.error ? 'has-error' : ''}">
        <form:label path="category">Category</form:label>
        <form:input path="category" type="text" class="form-control"
        required="required" />
        <form:errors path="category" cssClass="text-warning" />
      </div>
    </spring:bind>
      <fieldset class="form-group">
         <form:textarea path="text" cols="30" rows="7"
         style="resize: both;"
         placeholder="Enter text of the song" class="form-control"/>
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