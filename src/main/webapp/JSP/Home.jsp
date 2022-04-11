<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="gestion_annonces.model.bo.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core_1_1" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="../CSS/bootstrap.min.css" rel="stylesheet">
<link href="../fonts/font-awesome-4.7.0/css/font-awesome.css"
	rel="stylesheet">
<title>Home</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container px-5">
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
					<%
					if (request.getSession().getAttribute("userNOW") == null) {
					%>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="connecter.jsp">se connecter</a></li>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="inscrire.jsp">s'inscrire</a></li>
					<%
					} else {
					%>
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="index.do"> <i style="font-size:24px;color:white"
							class="fa fa-user"></i>
						<c:out value="${userNOW}"></c:out></a></li>
					<%
					}
					%>


				</ul>
			</div>
		</div>
	</nav>
	<section class="py-5 border-bottom">
		<div class="container">
			<div class="text-center mb-5">
				<h2 class="fw-bolder">Les offres de recrutement</h2>
				<!--  <p class="lead mb-0">Our customers love working with us</p> -->
			</div>
			<div class="row gx-5 justify-content-center">
				<div class="col-lg-6">
					<!-- Testimonial 1-->
		
					<c:forEach items="${annonces.offres }" var="of">

						<div class="card mb-4">
							<div class="card-body p-4">
								<div class="d-flex">
									<div class="flex-shrink-0">
										<i class="bi bi-chat-right-quote-fill text-primary fs-1"></i>
									</div>
									<div class="ms-4">
										<h5>${of.titre }</h5>
										<p class="mb-1">${of.description }</p>
										<div class="small text-muted">${of.profile }</div>
									</div>
									
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</section>
</body>
</html>