<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<h2>${name} - ${author}</h2>
<h5>${year} ${category}</h5>
<p>${text}</p>

<a href="${pageContext.request.contextPath}/list-allMusicTracks">Back</a>

<%@ include file="common/footer.jspf"%>