var form=document.getElementById("login-form");
var rfc=document.getElementById("login-rfc");
var password=document.getElementById("login-password");
var iconPassword = document.getElementById("togglePasswordLogin");
var eyeOpen = document.getElementById("eyeOpenLogin");
var eyeClosed = document.getElementById("eyeClosedLogin");
var error = document.getElementById("error-message-login");
var inputs = document.querySelectorAll("#login-form input");


iconPassword.addEventListener("click", function () {
    if (password.type === "password") {
        password.type = "text";
        eyeOpen.classList.add("hidden");
        eyeClosed.classList.remove("hidden");
    } else {
        password.type = "password";
        eyeOpen.classList.remove("hidden");
        eyeClosed.classList.add("hidden");
    }
});


form.addEventListener("submit", function (event) {
    event.preventDefault();
    error.textContent = "";
    
    var allFilled = true;
    inputs.forEach(function (input) {
        if (input.value.trim() === "") {
            allFilled = false;
            input.classList.add("border-red-500");
        } else {
            input.classList.remove("border-red-500");
        }
    });

    if (!allFilled) {
        error.textContent = "Todos los campos son obligatorios.";
        error.classList.add("text-red-500", "mt-2");
        return;
    }


    form.submit();
});