document.addEventListener("DOMContentLoaded", () => {
    
    let patternImei = new RegExp("^\\d{15}$");
    let patternNombre = new RegExp("^[a-zA-Z0-9\\s]{5,20}$"); 
    let patternMarca = new RegExp("^[A-Z][a-zA-Z\s]{4,19}$");
    let patternLanzamiento = new RegExp("^(20\\d{2})$");

    function mostrarMensajeError(idElemento, idMensaje, mensaje, regex) {
        const elemento = document.getElementById(idElemento);
        const mensajeElemento = document.getElementById(idMensaje);
        
        if (elemento && mensajeElemento) {
            elemento.addEventListener("blur", () => {
                if (!regex.test(elemento.value.trim())) {
                    mensajeElemento.textContent = mensaje;
                    mensajeElemento.style.color = "red";
                } else {
                    mensajeElemento.textContent = "";
                }
            });
        }
    }

    function validarFormulario(event) {
        let esValido = true;

        const validaciones = [
            { id: "imei", mensajeId: "imei-message", mensaje: "El IMEI debe ser de 15 dígitos", regex: patternImei },
            { id: "nombre", mensajeId: "nombre-message", mensaje: "El nombre debe tener entre 5 y 50 caracteres", regex: patternNombre },
            { id: "marca", mensajeId: "marca-message", mensaje: "La marca debe tener entre 5 y 30 caracteres y solo letras", regex: patternMarca },
            { id: "anoLanzamiento", mensajeId: "anoLanzamiento-message", mensaje: "El año debe ser posterior al 2000", regex: patternLanzamiento }
        ];

        validaciones.forEach(({ id, mensajeId, mensaje, regex }) => {
            const elemento = document.getElementById(id);
            const mensajeElemento = document.getElementById(mensajeId);
            
            if (elemento && mensajeElemento) {
                if (!regex.test(elemento.value.trim())) {
                    mensajeElemento.textContent = mensaje;
                    mensajeElemento.style.color = "red";
                    esValido = false;
                } else {
                    mensajeElemento.textContent = "";
                }
            }
        });

        if (!esValido) {
            event.preventDefault();
        }
    }

    const formulario = document.getElementById("insert-form");
    if (formulario) {
        formulario.addEventListener("submit", validarFormulario);
    }


    mostrarMensajeError("imei", "imei-message", "El IMEI debe ser una cadena de 15 números", patternImei);
    mostrarMensajeError("nombre", "nombre-message", "El nombre debe tener entre 5 y 50 caracteres", patternNombre);
    mostrarMensajeError("marca", "marca-message", "La marca debe tener entre 5 y 30 caracteres y solo letras y empezar con mayusucla", patternMarca);
    mostrarMensajeError("anoLanzamiento", "anoLanzamiento-message", "El año de lanzamiento debe ser posterior al 2000", patternLanzamiento);
});

