<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="java.util.List"%>
<%@page import="gestion_annonces.model.bo.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core_1_1" prefix="c" %>
<%
if (request.getSession().getAttribute("userNOW") == null) {
	response.sendRedirect("connecter.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../CSS/bootstrap.min.css" rel="stylesheet">
<title>Gestion des Offres</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
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
						aria-current="page" href="Home.do">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="deconnecter.do">Se
							déconnecter</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<center>
		<h1 style="margin-top: 50px; margin-bottom: 50px">Les offres de
			recrutement</h1>
	</center>

	<div class="card text-center">
		<div class="card-header">
			<h5 class="card-title">Ajouter un nouveau Offre</h5>
			<a href="ajouterOffre.jsp" class="btn btn-success">ajouter un
				offre</a>
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
						<th scope="col">Action</th>
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
							<td><a href="delete.do?id=${of.id}"
								class="btn btn-danger btn-sm">delete</a> <a
								href="update.do?id=${of.id}"
								class="btn btn-warning btn-sm">update</a><a
								href="detail.do?id=${of.id}"
								class="btn btn-info btn-sm">detail</a>
							</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>


</body>
</html>