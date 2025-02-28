var rfc = document.getElementById("rfc");
var password = document.getElementById("password");
var repeatPassword = document.getElementById("repeat-password");
var rfcError = document.getElementById("rfc-message");
var passwordError = document.getElementById("password-message");
var repeatPasswordError = document.getElementById("password-repeat-message");
var form = document.getElementById("user-form");
var inputs = document.querySelectorAll("#user-form input");
var error = document.getElementById("error-message");

var iconPassword = document.getElementById("togglePassword");
var eyeOpen = document.getElementById("eyeOpen");
var eyeClosed = document.getElementById("eyeClosed");

var iconPassword1 = document.getElementById("togglePassword1");
var eyeOpen1 = document.getElementById("eyeOpen1");
var eyeClosed1 = document.getElementById("eyeClosed1");

var parametersPassword = [
    "La contraseña debe llevar 8 caracteres.",
    "Debe llevar al menos una letra mayusucula",
    "Debe llevar al menos una minuscula",
    "Debe llevar al menos un numero",
    "Debe llevar al menos un caracter especial (@$!%)"
];

var rfcRegex = /^[A-ZÑ&]{3,4}\d{6}[A-Z0-9]{3}$/;
var passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#])[A-Za-z\d@$!%*?&]{8,}$/;

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

iconPassword1.addEventListener("click", function () {
    if (repeatPassword.type === "password") {
        repeatPassword.type = "text";
        eyeOpen1.classList.add("hidden");
        eyeClosed1.classList.remove("hidden");
    } else {
        repeatPassword.type = "password";
        eyeOpen1.classList.remove("hidden");
        eyeClosed1.classList.add("hidden");
    }
});

rfc.addEventListener("focus", function () {
    rfcError.innerText = "El RFC está formado por las iniciales de tu nombre, fecha de nacimiento y homoclave";
    rfcError.style.color = "gray";
});

rfc.addEventListener("blur", function () {
    rfcError.innerText = "";
});

rfc.addEventListener("input", function () {
    if (!rfcRegex.test(rfc.value.trim())) {
        rfcError.innerText = "Formato incorrecto del RFC";
        rfcError.style.color = "red";
        rfc.classList.add("border-red-500");
        rfc.classList.remove("border-green-500");
    } else {
        rfcError.innerText = "RFC válido";
        rfcError.style.color = "green";
        rfc.classList.add("border-green-500");
        rfc.classList.remove("border-red-500");
    }
});

function validarPassword(password) {
    var errores = [];
    if (password.length < 8) {
        errores.push(parametersPassword[0]);
    }
    if (!/[A-Z]/.test(password)) {
        errores.push(parametersPassword[1]);
    }
    if (!/[a-z]/.test(password)) {
        errores.push(parametersPassword[2]);
    }
    if (!/\d/.test(password)) {
        errores.push(parametersPassword[3]);
    }
    if (!/[@$!%*?&#]/.test(password)) {
        errores.push(parametersPassword[4]);
    }
    return errores;
}

password.addEventListener("input", function () {
    var errores = validarPassword(password.value);
    passwordError.innerHTML = "";
    if (errores.length > 0) {
        errores.forEach(function (error) {
            var li = document.createElement("li");
            li.textContent = error;
            li.classList.add("text-gray-500", "text-sm");
            passwordError.appendChild(li);
        });
        password.classList.add("border-red-500");
        password.classList.remove("border-green-500");
    } else {
        password.classList.add("border-green-500");
        password.classList.remove("border-red-500");
    }
});

password.addEventListener("blur", function () {
    passwordError.innerHTML = "";
});

repeatPassword.addEventListener("input", function () {
    if (repeatPassword.value === "" || password.value === "") {
        repeatPasswordError.innerText = "";
        repeatPasswordError.classList.remove("text-red-500", "text-green-500");
    } else if (repeatPassword.value !== password.value) {
        repeatPasswordError.innerText = "Las contraseñas no coinciden";
        repeatPasswordError.classList.add("text-red-500");
        repeatPasswordError.classList.remove("text-green-500");
    } else {
        repeatPasswordError.innerText = "Las contraseñas coinciden";
        repeatPasswordError.classList.add("text-green-500");
        repeatPasswordError.classList.remove("text-red-500");
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

    if (!rfcRegex.test(rfc.value.trim())) {
        error.textContent = "El RFC no tiene el formato correcto.";
        return;
    }

    if (validarPassword(password.value).length > 0) {
        error.textContent = "La contraseña no cumple con los requisitos.";
        return;
    }

    if (repeatPassword.value !== password.value) {
        error.textContent = "Las contraseñas no coinciden.";
        return;
    }

    form.submit();
});
