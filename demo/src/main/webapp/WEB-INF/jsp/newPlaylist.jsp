<%@ include file="common/header.jspf"%>
<%@ include file="common/navigationForUser.jspf"%>
<div class="container">
 <div class="row">
  <div class="col-md-6 col-md-offset-3 ">
   <div class="panel panel-primary">
    <div class="panel-heading">Add playlist</div>
    <div class="panel-body">
     <form:form method="post" modelAttribute="playlist">
      <form:hidden path="id" />
      <input type="hidden" name="page" value=${page}>
      <fieldset class="form-group ${status.error ? 'has-error' : ''}">
       <form:label path="name">Name</form:label>
       <form:input path="name" type="text" class="form-control"
        required="required" />
       <form:errors path="name" cssClass="text-warning" />
      </fieldset>

      <button type="submit" class="btn btn-success">Save</button>
     </form:form>
    </div>
   </div>
  </div>
 </div>
</div>
<%@ include file="common/footer.jspf"%>