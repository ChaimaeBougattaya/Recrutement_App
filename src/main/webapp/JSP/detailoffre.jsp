<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../CSS/bootstrap.min.css" rel="stylesheet">
<title>Détail offre</title>
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
		<div class="card text-center">
		<div class="card-header">
			<h5 class="card-title">Détail offre</h5>
		</div>
		<div class="card-body" style="margin-top: 20px;">
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col">Nom</th>
						<th scope="col">Prenom</th>
						<th scope="col">Username</th>
						<th scope="col">CV</th>
						<th scope="col">LettreM</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${candidatures.candidats}" var="can">
						<tr scope="row">
							<td>${can.nom}</td>
							<td>${can.prenom}</td>
							<td>${can.username}</td>
							<td><a href="dowloadCV.do?id=${can.id}"
								class="btn btn-primary btn-sm">dowloadCV</a>
							</td>
							<td><a href="dowloadLM.do?id=${can.id}"
								class="btn btn-primary btn-sm">dowloadLM</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<a class="btn btn-secondary" href="index.do">Revenir</a>
		</div>
	</div>
	
</body>
</html>