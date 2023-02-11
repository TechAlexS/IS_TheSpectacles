$(document).ready(function(){
	var errCardNumber= 0;
	var errCardExpiry= 0;
	var errCardCvv= 0;
	var errIndirizzo= 0;
	$('#submit').click(function(){
	
	var cardnumber = $('#cardnumber').val();
	var expiry= $('#expiry').val();
	var cvv = $('#cvv').val();
	var indirizzo = $('#indirizzo').val();
	
	    
	var expCardNumber= /^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11})$/;
	var expCardExpiry= /^(0[1-9]|1[0-2])\/?([0-9]{4}|[0-9]{2})$/;
	var expCardCvv= /^[0-9]{3, 4}$/;
	
	
		
	console.log(cardnumber);	
	console.log(cardnumber.match(expCardNumber));	
	console.log(expiry);	
	console.log(expiry.match(expCardExpiry));	
	console.log(cvv);		
	console.log(cvv.match(expCardCvv));	
	console.log(indirizzo);	

	
		
	if (!(cardnumber.match(expCardNumber)) && errCardNumber < 1){
		errCardNumber= errCardNumber + 1;
		$('#cardnumber').after("<b><p id=errorecardnumber style='color: red;'>Errore nell'inserimento del cardnumber</p></b> <br>");
	 }
	
	if (!(expiry.match(expCardExpiry)) && errCardExpiry < 1){
		errCardExpiry= errCardExpiry + 1;
		$('#expiry').after("<b><p id=errorecardnumber style='color: red;'>Errore nell'inserimento della scadenza r</p></b> <br>");
	}
		
		
	if (!(cvv.match(expCardCvv)) && errCardCvv < 1){
		errCardCvv= errCardCvv + 1;
		$('#cvv').after("<b><p id=errorecvv style='color: red;'>Errore nell'inserimento del cvv</p></b> <br>");
	}
	
	if ((indirizzo.equals(null)) && errIndirizzo< 1){
		errindirizzo= errindirizzo + 1;
		$('#indirizzo').after("<b><p id=erroreindirizzo style='color: red;'>Errore indirizo non inserito </p></b> <br>");
	}
		
	
		
	
	
	//eliminazione errori
		
	if (cardnumber.match(expCardNumber)){
		errCardNumber= errCardNumber + 1;
		$('#errorecardnumber').remove();
	}
		
	
	if (expiry.match(expCardExpiry)){
		errCardExpiry= errCardExpiry + 1;
		$('#expiry').remove();
	}
		
	if (cvv.match(expCardExpiry)){
		errCardCvv= errCardCvv + 1;
		$('#errorecvv').remove();
	}
		
	if (!(indirizzo.equals("null"))){
		errindirizzo= errindirizzo + 1;
		$('#indirizzo').remove();
	}
		
		
		
	//utilizzo di ajax
		
	if ((cardnumber.match(expCardNumber)) && (expiry.match(expCardExpiry)) && (cvv.match(expCardCvv)) && !(indirizzo.equals("null"))){
			$.ajax({
				type: 'GET',
				data: {cardnumber: cardnumber,expiry: expiry, cvv: cvv, indirizzo: indirizzo},
				url: 'Checkout'
			
				})
			}
		
		})
	})