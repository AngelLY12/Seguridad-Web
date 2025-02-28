<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login de usuarios</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <main class="bg-white p-6 rounded-lg shadow-lg w-full max-w-md">
        <form action="${pageContext.request.contextPath}/InsertUserController" method="POST" class="space-y-4" id="user-form">
            <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Login</h2>
            
            <h5 class="text-center" id="error-message"></h5></span>

            <div>
                <label for="rfc" class="block text-sm font-medium text-gray-700">RFC</label>
                <input type="text" id="rfc" name="rfc" placeholder="Escribe tu RFC" 
                    class="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <small class="w-full h-10" id="rfc-message"></small>
            </div>
            <div>
                <label for="name" class="block text-sm font-medium text-gray-700">Nombre(s)</label>
                <input type="text" id="name" name="name" placeholder="Escribe tu nombre" 
                    class="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                <small class="w-full h-10" id="name-message"></small>
            </div>
            <div>
                <label for="lastName" class="block text-sm font-medium text-gray-700">Apellido</label>
                <input type="text" id="lastName" name="lastName" placeholder="Escribe tu apellido" 
                    class="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <small class="w-full h-10" id="lastname-message"></small>
            </div>
            <div class="relative">
                <label for="password" class="block text-sm font-medium text-gray-700">Contraseña</label>
                <input type="password" id="password" name="password" placeholder="Escribe tu contraseña" 
                    class="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <button type="button" id="togglePassword" class="absolute bottom-3 right-3 text-gray-500">
                        <svg id="eyeOpen" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"> 
                            <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8S1 12 1 12z"></path> 
                            <circle cx="12" cy="12" r="3"></circle>
                        </svg>
                        <svg id="eyeClosed" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 hidden" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"> 
                            <path d="M17.94 17.94A10.94 10.94 0 0 1 12 20C5 20 1 12 1 12S3.64 7.05 8 5m4-1c7 0 11 8 11 8a14.28 14.28 0 0 1-1.36 2.91"></path> 
                            <line x1="1" y1="1" x2="23" y2="23"></line>
                        </svg>                    
                    </button>
            </div>
            <ul id="password-message"></ul>
            <div class="relative">
                <label for="repeat-password" class="block text-sm font-medium text-gray-700">Repetir Contraseña</label>
                <input type="password" id="repeat-password" name="repeat-password" placeholder="Vuelve a escribir tu contraseña" 
                    class="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                    <button type="button" id="togglePassword1" class="absolute bottom-3 right-3 text-gray-500">
                        <svg id="eyeOpen1" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"> 
                            <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8S1 12 1 12z"></path> 
                            <circle cx="12" cy="12" r="3"></circle>
                        </svg>
                        <svg id="eyeClosed1" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 hidden" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"> 
                            <path d="M17.94 17.94A10.94 10.94 0 0 1 12 20C5 20 1 12 1 12S3.64 7.05 8 5m4-1c7 0 11 8 11 8a14.28 14.28 0 0 1-1.36 2.91"></path> 
                            <line x1="1" y1="1" x2="23" y2="23"></line>
                        </svg>                    
                    </button>
            </div>
            <small class="w-full h-10" id="password-repeat-message"></small>
            <button type="submit" class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 transition duration-200">
                Crear Cuenta
            </button>
        </form>
    </main>
    <script src="./validationUser.js"></script>
</body>
</html>
