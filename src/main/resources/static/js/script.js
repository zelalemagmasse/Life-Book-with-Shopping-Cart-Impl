function myFunction(id) {
    var x = document.getElementById(id);
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
        x.previousElementSibling.className += " w3-theme-d1";
    } else {
        x.className = x.className.replace("w3-show", "");
        x.previousElementSibling.className =
        x.previousElementSibling.className.replace(" w3-theme-d1", "");
    }
}

// Used to toggle the menu on smaller screens when clicking on the menu button
function openNav() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}

function myFunction() {
    var x, text;

    // Get the value of the input field with id="numb"
    x = document.getElementById("numb").value;

    // If x is Not a Number or less than one or greater than 10
    if (isNaN(x) || x < 1 || x > 10) {
        text = "Input not valid";
    } else {
        text = "Input OK";
    }
    document.getElementById("demo").innerHTML = text;
}


function myFunction() {
    document.getElementById("demo").innerHTML = "Paragraph changed.";
}

function writeAmount()
{
    if( document.myform.amount.value == ""||isNaN(document.myform.amount.value)||document.myform.amount.value<1) {
        alert("Enter some valid amount!");
        document.myform.amount.value = 0;
        return;
    }
    else if (document.getElementById("numIntheStock").value<document.myform.amount.value){
        alert("Enter a Valid amount!");
    }
    }