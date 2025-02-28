<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
        <script src="https://cdn.tailwindcss.com"></script>

</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
    <div>
        <div class="w-full h-full flex justify-center items-center">
            <div class=" bg-white shadow-lg rounded-lg p-6 w-96">
                <h2 class="text-2xl font-bold text-center text-gray-800">Login</h2>
                <h5 class="text-center" id="error-message-login"></h5></span>
                <form action="${pageContext.request.contextPath}/InsertLoginController" method="POST" class="space-y-4" id="login-form">
                    <div>
                        <label for="login-rfc" class="block text-sm font-medium text-gray-700">RFC</label>
                        <input type="text" id="login-rfc" name="rfc" placeholder="Escribe tu RFC" 
                            class="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                    </div>
                    <div class="relative">
                        <label for="login-password" class="block text-sm font-medium text-gray-700">Contraseña</label>
                        <input type="password" id="login-password" name="password" placeholder="Escribe tu contraseña" 
                            class="w-full p-3 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500">
                            <button type="button" id="togglePasswordLogin" class="absolute bottom-3 right-3 text-gray-500">
                                <svg id="eyeOpenLogin" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"> 
                                    <path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8S1 12 1 12z"></path> 
                                    <circle cx="12" cy="12" r="3"></circle>
                                </svg>
                                <svg id="eyeClosedLogin" xmlns="http://www.w3.org/2000/svg" class="w-6 h-6 hidden" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"> 
                                    <path d="M17.94 17.94A10.94 10.94 0 0 1 12 20C5 20 1 12 1 12S3.64 7.05 8 5m4-1c7 0 11 8 11 8a14.28 14.28 0 0 1-1.36 2.91"></path> 
                                    <line x1="1" y1="1" x2="23" y2="23"></line>
                                </svg>                    
                            </button>
                    </div>
                    <button type="submit" class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 transition duration-200">
                        Iniciar Sesión
                    </button>
                </form>
                <p class="text-center">¿No tienes una cuenta? <a href="register.jsp" class="mt-4 text-blue-500 underline">Registrate</a></p>
            </div>
        </div>
    </div>
    <script src="validationLogin.js"></script>

</body>
</html>