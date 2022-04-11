<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
if(request.getSession().getAttribute("userNOW")==null)
{
response.sendRedirect("connecter.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="../CSS/bootstrap.min.css"
	rel="stylesheet">
<title>Ajouter offre</title>
</head>
<body>
	<div class="card text-center">
		<div class="card-header" style="margin-top: 50px">
			<h5 class="card-title">Ajouter un nouveau offre</h5>
		</div>
		<div class="card-body">
			<form method="post" action="insertoffre.do">
				<div class="form-group"
					style="margin-left: 20%; margin-right: 20%; margin-top: 20px; margin-bottom: 20px">
					<input type="text" class="form-control" name="titre"
						placeholder="Entrer le titre de l'offre"> <input
						type="text" class="form-control" name="description"
						placeholder="Entrer la description de l'offre"> <input
						type="text" class="form-control" name="profile"
						placeholder="Entrer le profile recherché"> <select
						class="form-control" name="type">
						<option value="1">CDD</option>
						<option value="2">CDI</option>
						<option value="3">ANAPEC</option>
					</select>
				</div>
				<a href="index.do" class="btn btn-secondary">Revenir</a>
				<button type="submit" class="btn btn-primary">Ajouter offre</button>
			</form>

		</div>
	</div>
</body>
</html>