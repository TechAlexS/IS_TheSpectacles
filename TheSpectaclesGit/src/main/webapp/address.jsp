<%@page import="gestioneUtenza.UtenteBean"%>
<%@page import="gestioneIndirizzi.IndirizziBean"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
if(request.getSession().getAttribute("auth") == null) {
response.sendRedirect(getServletContext().getContextPath() +
"/login.jsp"); } 
UtenteBean auth = (UtenteBean) request.getSession().getAttribute("auth");
IndirizziBean attivo= (IndirizziBean) request.getAttribute("attivo");
Collection<?> indirizzi = (Collection<?>) request.getAttribute("indirizzi");
%>


<!DOCTYPE html>
<html lang="it">


<head>

<%@ include file="meta.html"%>

</head>

<body id="body">

<% session.setAttribute("auth",(UtenteBean) request.getSession().getAttribute("auth")); %>

<%@ include file="header.jsp"%>


<section class="page-header">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="content">
					<h1 class="page-name">Rubrica Indirizzi</h1>
			</div>
		</div>
	</div>
</section>
<section class="user-dashboard page-wrapper">
  <div class="container">
    <div class="row">
		<div class="col-md-12">
			<ul class="list-inline dashboard-menu text-center">
				<li><a href="CronologiaOrdini">Ordini</a></li>							
				<li><a  href="Profile">Dettagli Profilo</a></li>
				<li><a class="active" href="Indirizzo?page=ok">Indirizzi</a></li>	
			</ul>
        <div class="dashboard-wrapper user-dashboard">
          <div class="table-responsive">
            <table class="table">
              <thead>
                <tr>
                  <th>Nome</th>
                  <th>Cognome</th>
                  <th>Indirizzo</th>
                  <th>Città</th>
                  <th>CAP</th>
                  <th class="col-md-2 col-sm-3">Telefono</th>
                 
                </tr>
              </thead>
               <tbody>
                  <%if (indirizzi != null && indirizzi.size() != 0) {
						Iterator<?> it = indirizzi.iterator();
						while (it.hasNext()) {
							IndirizziBean bean = (IndirizziBean) it.next();%>      
                <tr>
                  <td><%= bean.getName() %></td>
                  <td><%= bean.getSurname() %></td>
                  <td><%= bean.getAddress() %></td>
                  <td><%= bean.getCity() %>, <%= bean.getProvince() %> </td>
                  <td><%= bean.getCap() %></td>
                  <td><%= bean.getTelefono() %></td>
                  
                </tr>
              
              </tbody>
               <% }						
					 	}%>
            </table>
          </div>
        </div>
      </div>
    </div>
    <a href="aggiungiIndirizzo2.jsp" class="btn btn-main pull-right">Aggiungi Indirizzo</a>
  </div>
</section>



 <%@ include file="footer.html"%>

 <%@ include file="script.html"%>
    


  </body>
  </html>