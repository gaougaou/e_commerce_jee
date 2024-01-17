<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

  <title>Inscription</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>


<head>
  
</head>
<body>
<div class="container mt-5">
     <form action="inscription" method="post">
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="name">Nom</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="form-group col-md-6">
                <label for="prenom">Prenom</label>
                <input type="text" class="form-control" id="prenom" name="prenom" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="age">Age</label>
                <input type="number" class="form-control" id="age" name="age" required>
            </div>
            <div class="form-group col-md-6">
                <label for="login">Login</label>
                <input type="text" class="form-control" id="login" name="login" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group col-md-6">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <div class="form-group col-md-6">
                <label for="confpassword">Confirme Password</label>
                <input type="password" class="form-control" id="confpassword" name="confpassword" required>
            </div>
        </div>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="tel">Telephone</label>
                <input type="tel" class="form-control" id="tel" name="tel" required>
            </div>
        </div>
        <div id="message"></div>
        <button type="submit" class="btn btn-primary">S'inscrire</button>
    </form>
</div>
</body>
</html>

