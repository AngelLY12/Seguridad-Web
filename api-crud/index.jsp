<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario agregar Celulares</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <header class="bg-blue-600 text-white py-4">
        <div class="container mx-auto text-center">
            <h1 class="text-3xl font-bold">Formulario de Celulares</h1>
            <a href="/api-crud/SelectController?" class="hover:underline underline-offset-8">Ver celulares</a>
        </div>
    </header>

    <main class="container mx-auto p-6">
        <form id="insert-form" action="${pageContext.request.contextPath}/InsertController" method="POST" class="bg-white shadow-md rounded-lg p-6">
            <div class="space-y-6">
                <h2 class="text-2xl font-semibold text-gray-800 mb-4">Agrega tu celular</h2>
        
                <div>
                    <label for="imei" class="block text-sm font-medium text-gray-700">IMEI</label>
                    <input
                        type="text"
                        id="imei"
                        name="imei"
                        placeholder="Escribe el IMEI de tu celular"
                        class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        maxlength="15"
                    />
                    <small class="w-full h-10" id="imei-message"></small>
                </div>
        
                <div>
                    <label for="nombre" class="block text-sm font-medium text-gray-700">Nombre</label>
                    <input
                        type="text"
                        id="nombre"
                        name="nombre"
                        placeholder="Escribe el nombre de tu celular"
                        class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        maxlength="20"
                    />
                    <small id="nombre-message"></small>

                </div>
        
                <div>
                    <label for="anoLanzamiento" class="block text-sm font-medium text-gray-700">Año de Lanzamiento</label>
                    <input
                        type="text"
                        id="anoLanzamiento"
                        name="anoLanzamiento"
                        placeholder="Escribe el año de lanzamiento"
                        class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        maxlength="4"
                    />
                    <small id="anoLanzamiento-message"></small>

                </div>
        
                <div>
                    <label for="marca" class="block text-sm font-medium text-gray-700">Marca</label>
                    <input
                        type="text"
                        id="marca"
                        name="marca"
                        placeholder="Escribe la marca del celular"
                        class="mt-1 block w-full px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-blue-500 focus:border-blue-500"
                        maxlength="20"
                    />
                    <small id="marca-message"></small>
                </div>
        
                <div class="text-right">
                    <button
                        type="submit"
                        class="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-blue-600 hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500"
                    >
                        Guardar
                    </button>
                </div>
            </div>
        </form>
    </main>
    <script src="/validation.js" defer></script>

</body>
</html>
