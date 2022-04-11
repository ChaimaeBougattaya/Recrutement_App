<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core_1_1" prefix="c"%>
<%
long id = Long.parseLong(request.getParameter("id"));
if (request.getSession().getAttribute("userNOW") == null)
	response.sendRedirect("connecter.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>update offre</title>
<link href="../CSS/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="card text-center">
		<div class="card-header" style="margin-top: 50px">
			<h5 class="card-title">Ajouter un nouveau offre</h5>
		</div>
		<div class="card-body">

			<form action="updateoffre.do">

				<div class="form-group"
					style="margin-left: 20%; margin-right: 20%; margin-top: 20px; margin-bottom: 20px">
					<input type="text" class="form-control" name="titre"
						value="${of.titre }"> <input type="text"
						class="form-control" name="description" value="${of.description }">
					<input type="text" class="form-control" name="profile"
						value="${of.profile}"> <input type="hidden"
						class="form-control" name="idoffre" value="${of.id }"> <select
						class="form-control" name="type">
						<option value="1">CDD</option>
						<option value="2">CDI</option>
						<option value="3">ANAPEC</option>
					</select>
				</div>

				<a href="index.do" class="btn btn-secondary">Revenir</a>
				<Button type="submit" class="btn btn-primary">modifier
					l'offre</Button>
			</form>


		</div>
	</div>
</body>
</html>