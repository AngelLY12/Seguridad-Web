const rfc=document.getElementById("rfc") as HTMLInputElement;
const passwrod=document.getElementById("password") as HTMLInputElement;
const repeatPassword=document.getElementById("repeat-password") as HTMLInputElement
const rfcError=document.getElementById("rfc-message") as HTMLElement;
const nameError=document.getElementById("name-message") as HTMLElement;
const lastNameError=document.getElementById("lastname-message") as HTMLElement;
const passwordError=document.getElementById("password-message") as HTMLUListElement;
const repeatPasswordError=document.getElementById("password-repeat-message") as HTMLElement;
const form=document.getElementById("user-form") as HTMLFormElement
const inputs=document.querySelectorAll("input");
const error=document.getElementById("error-message") as HTMLElement;

let parametersPassword:string[]=[
    "La contraseña debe llevar 8 caracteres.",
    "Debe llevar al menos una letra mayusucula",
    "Debe llevar al menos una minuscula",
    "Debe llevar al menos un numero",
    "Debe llevar al menos un caracter especial (@$!%)"
];


const rfcRegex = /^[A-ZÑ&]{3,4}\d{6}[A-Z0-9]{3}$/;
const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;

rfc.addEventListener("focus",()=>{
    rfcError.innerText="El RFC esta formado por las iniciales de tu nombre, fecha de nacimiento y homoclave";
    rfcError.style.color="gray";
    

});

rfc.addEventListener("input",()=>{
    if(!rfcRegex.test(rfc.value.trim())){
        rfcError.innerText="Formato incorrecto del RFC";
        rfcError.style.color="red";
    }else{
        rfcError.innerText="RFC valido"
        rfcError.style.color="green";

    }

});

function validarPassword(password:string):string[]{
    let errores:string[]=[];
    if(password.length<8){
        errores.push(parametersPassword[0]);
    }
    if(!/[A-Z]/.test(password)){errores.push(parametersPassword[1]);}
    if(!/[a-z]/.test(password)){errores.push(parametersPassword[2]);}
    if(!/\d/.test(password)){errores.push(parametersPassword[3]);}
    if(!/[@$!%*?&]/.test(password)){errores.push(parametersPassword[4]);}



    return errores;
}

passwrod.addEventListener("input",()=>{
    const errores=validarPassword(passwrod.value);
    passwordError.innerHTML="";
    if(errores.length>0){
        errores.forEach(error=>{
            const li=document.createElement("li");
            li.textContent=error;
            li.classList.add("text-red-500","text-sm");
            passwordError.appendChild(li);
        });
        
    }
});

repeatPasswordError.addEventListener("input",()=>{
    if(repeatPassword!=passwrod){
        repeatPasswordError.innerText="La contraseña no coincide";
        repeatPasswordError.style.color="red";
    }else{
        repeatPasswordError.innerText="Contraseña valida";
        repeatPasswordError.style.color="green";
    }
})

form.addEventListener("submit",(event)=>{

    event.preventDefault();
    error.textContent="";

    let allFilled=true;
    inputs.forEach(input=>{
        if(input.value.trim()===""){
            allFilled = false;
            input.classList.add("border-red-500");
        }else{
            input.classList.remove("border-red-500");
        }
    });

    if(!allFilled){
        error.textContent="Todos los campos son obligatorios.";
        error.classList.add("text-red-500","mt-2")
    }else{
        form.submit();
    }

});

