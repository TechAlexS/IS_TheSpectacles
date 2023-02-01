$(document).ready(function(){
	var err= 0;
	var err2= 0;
	var err3= 0;
	$('#submit').click(function(){
	var email= $('#email1').val();
	var password= $('#password1').val();	
	var expEm= /^([a-z1-9.-])*@([a-z])+(.com)$/;
	var expEm2= /^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{7,}$/;
	
	if (email.match(expEm) ){
		$('#erroreEmail').remove();
		if(password.match(expEm2)){
				$('#errorePassword').remove();
		$.ajax({
			type: 'POST',
			data: {email: email, password: password},
			url: 'Login',
			success: function(result){
				if(result=="Nulla" && err3 < 1){
				err3= err3 + 1;
					$('#email1').after("<b><p id=erroreUtente style='color: red;'>Errore Utente non trovato</p></b> <br>");		
				}
				
				if (result == "Admin"){
					window.location.href="ControlloAdmin";
				}
				if (result == "Utente"){
					window.location.href="shop.jsp";
				}
			}
		})
		}
	
	}
	})
	
})