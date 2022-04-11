<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 <%
if(request.getSession().getAttribute("CandidatNOW")!=null)
{
response.sendRedirect("indexCandidat.do");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Connexion</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="../CSS/style.css">
</head>
<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-7 col-lg-5">
					<div class="login-wrap p-4 p-md-5">
						<div class="icon d-flex align-items-center justify-content-center">
							<span class="fa fa-user-o"></span>
						</div>
						<h3 class="text-center mb-4">Connexion Candidat</h3>
						<form action="connecterCandidat.do" method="post" class="login-form">
							
							<div class="form-group">
								<input type="text" name="username" class="form-control rounded-left"
									placeholder="Username" required>
							</div>
							<div class="form-group d-flex">
								<input type="password" name="password" class="form-control rounded-left"
									placeholder="Password" required>
							</div>
							<div class="form-group">
								<button type="submit"
									class="form-control btn btn-primary rounded submit px-3">se connecter</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="../JS/jquery.min.js"></script>
	<script src="../JS/popper.js"></script>
	<script src="../JS/bootstrap.min.js"></script>
	<script src="../JS/main.js"></script>
</body>
</html>