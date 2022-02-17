function validate() {

    $('.error').fadeOut('slow');

    var name = document.getElementById("name").value;
    var surname = document.getElementById("surname").value;
    var login = document.getElementById("login").value;
    var pass = document.getElementById("pass").value;
    var pass2 = document.getElementById("pass2").value;

    var nameSurnamePattern = /[A-Z][a-z]+/;
    var loginPassPattern = /.{2}.*/

    if(!nameSurnamePattern.test(name)) {
        showError("error in name field");
        return false;
    }

    if(!nameSurnamePattern.test(surname)) {
        showError("error in surname field");
        return false;
    }

    if(!loginPassPattern.test(login)) {
        showError("error in login field");
        return false;
    }

    if(!loginPassPattern.test(pass)) {
        showError("error in password field");
        return false;
    }

    if(pass != pass2) {
        showError("pass 1 is not equal to pass 2");
        return false;
    }

    return true;
}

function showError(text) {

    $('.error').text(text);
    $('.error').fadeIn('slow');
}

