<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core_1_1" prefix="c" %>
    <%
if (request.getSession().getAttribute("CandidatNOW") == null) {
	response.sendRedirect("connecterCandidat.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../CSS/bootstrap.min.css" rel="stylesheet">
<title>indexCandidat</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-primary">
		<div class="container px-5">
			<!-- <a class="navbar-brand" href="#!">Annonces de recrutement</a> -->
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="HomeCandidat.do"><h5>Home</h3></a></li>
					<li class="nav-item"><a class="nav-link" href="deconnecterCandidat.do"><h5>Se
							déconnecter</h5></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<center>
		<h1 style="margin-top: 50px; margin-bottom: 50px">Mes candidatures</h1>
	</center>

	<div class="card text-center">
		<div class="card-header">
			<!--<!--<h5 class="card-title">Update cv</h5><!-- <form action="updatecv.do" method="POST" enctype="multipart/form-data">
	
			<div class="form-group d-flex" style="margin-left:500px;margin-right:500px;">
				<input type="file" name="cv" class="form-control rounded-left"
					placeholder="votre cv" required>
			<Button type="submit" class="btn btn-success">update</Button>
			</div>
			</form> -->
		</div>
		<div class="card-body" style="margin-top: 20px;">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Titre</th>
						<th scope="col">Description</th>
						<th scope="col">Profile</th>
						<th scope="col">Date de publication</th>
						<th scope="col">Type de contrat</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${annonces.offres}" var="of">
						<tr scope="row">
							<td>${of.titre}</td>
							<td>${of.description}</td>
							<td>${of.profile}</td> 
							<td>${of.datepub}</td>
							<td>${of.typecontrat.typecontrat}</td>
							
						</tr>

					</c:forEach>
					
				</tbody>
			</table>

		</div>
	</div>


</body>
</html>