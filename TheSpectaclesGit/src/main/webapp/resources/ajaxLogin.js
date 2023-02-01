$(document).ready(function(){
	var err3= 0;
	$('#submit').click(function(){
	var email= $('#email1').val();
	var password= $('#password1').val();	
	
		$.ajax({
			type: 'POST',
			data: {email: email, password: password},
			url: 'Login',
			success: function(result){
				if(result=="Nulla" && err3 < 1){
				err3= err3 + 1;
					$('#email1').after("<b><p id=erroreEmail style='color: red;'>Errore Utente non trovato</p></b> <br>");		
				}
				
				if (result == "Admin"){
					window.location.href="ControlloAdmin";
				}
				if (result == "Utente"){
					window.location.href="shop.jsp";
				}
			}
		})
	})
})