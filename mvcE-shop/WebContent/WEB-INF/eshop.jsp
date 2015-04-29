<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>Shop</title>
</head>
<body>

<h2>Seleziona i prodotti che vuoi acquistare</h2>

<form action="kart-Servlet" method="get">
<input type="checkbox" value="Fender Jazz Bass Geddy Lee Signature" name="aggiungi"/><label>"Fender Jazz Bass Geddy Lee Signature"</label><br>
<input type="checkbox" value="Fender Precision Bass  Steve Harris Signature" name="aggiungi"/><label>"Fender Precision Bass  Steve Harris Signature"</label><br>
<input type="checkbox" value="Dean Metalman DimeBag Darrell Signature" name="aggiungi"/><label>"Dean Metalman DimeBag Darrell Signature"</label><br>
<input type="submit" value="inserisci nel carrello"/><br>
</form>


<h3>Carrello attuale:</h3>

	<h4>kart=</h4>${carrello.carrello}

</body>
</html>