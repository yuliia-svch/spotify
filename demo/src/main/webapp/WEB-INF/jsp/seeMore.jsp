<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
crossorigin="anonymous">

<div class="px-3 mt-3">
<h2>${name} - ${author}</h2>
<h5>${year}, ${category}</h5>
<p><pre>${text}</pre></p>
<br/>
<a href="${pageContext.request.contextPath}/${page}">Back</a>
</div>
<%@ include file="common/footer.jspf"%>