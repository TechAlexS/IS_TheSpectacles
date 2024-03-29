<%@page import="gestioneOcchiali.OcchialeBean"%>
<%@page import="gestioneUtenza.UtenteBean"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
OcchialeBean bean = (OcchialeBean) request.getAttribute("des");
 %>
 
<!DOCTYPE html>
<html lang="it">
<head>

<%@ include file="meta.html"%>

</head>

<body id="body">
<% session.setAttribute("auth",(UtenteBean) request.getSession().getAttribute("auth")); %>

<%@ include file="headerAmministratore.jsp"%>

<% 
		if (bean != null) {
				
				%>

<section class="single-product">
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<ol class="breadcrumb">
					<li><a href="ControlloAmministratores.jsp">Home</a></li>
					
					<li class="active">Dettagli Prodotto</li>
				</ol>
			</div>	
		</div>
		
		<div class="row mt-20">
		
			<div class="col-md-5">
				<div class="single-product-slider">
					<div id='carousel-custom' class='carousel slide' data-ride='carousel'>
						<div class='carousel-outer'>
							<!-- me art lab slider -->
							<div class='carousel-inner '>
								<div class='item active'>
									<img src='images/shop/products/<%=bean.getImage()%>' alt='' data-zoom-image="images/shop/single-products/product-1.jpg" />
								</div>
								<div class='item'>
									<img src='images/shop/products/<%=bean.getImage2()%>' alt='' data-zoom-image="images/shop/single-products/product-2.jpg" />
								</div>
							</div>
							
							<!-- sag sol -->
							<a class='left carousel-control' href='#carousel-custom' data-slide='prev'>
								<i class="tf-ion-ios-arrow-left"></i>
							</a>
							<a class='right carousel-control' href='#carousel-custom' data-slide='next'>
								<i class="tf-ion-ios-arrow-right"></i>
							</a>
						</div>
						
						
						<ol class='carousel-indicators mCustomScrollbar meartlab'>
							<li data-target='#carousel-custom' data-slide-to='0' class='active'>
								<img src='images/shop/products/<%=bean.getImage()%>' alt='' />
							</li>
							<li data-target='#carousel-custom' data-slide-to='1'>
								<img src='images/shop/products/<%=bean.getImage2()%>' alt='' />
							</li>
							
						</ol>
						
					</div>
				</div>
			</div>
			<div class="col-md-7">
				<div class="single-product-details">
					<h2>Brand: <%=bean.getBrand()%></h2>

					<p class="product-price">Prezzo: <%=bean.getPrice()%> &euro;</p>

					
					<p class="product-description mt-20">
						Nome: <%=bean.getNameGlasses()%>
					</p>
			       <p> Categoria: <%=bean.getCategory()%> </p>
			       <p> Codice: <%=bean.getIdGlasses()  %> </p>

					
				
					<div class="product-quantity">
						<span>Quantità: <%=bean.getAvailability()%></span>
					</div>
					
					
				</div>
			</div>
		</div>
		
		
		<div class="row">
			<div class="col-xs-12">
				<div class="tabCommon mt-20">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#details" aria-expanded="true">Details</a></li>
						
					</ul>
					<div class="tab-content patternbg">
						<div id="details" class="tab-pane fade active in">
							<h4>Breve descrizione</h4>
							<p><%=bean.getDescription()%></p>
							
						</div>
						
						
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

	<%	
			} else {
			out.println("There is no proucts");
			}
	%>

	 <%@ include file="footer.html"%>
     <%@ include file="script.html"%>
	
  </body>
  </html>