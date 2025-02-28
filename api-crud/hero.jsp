<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %> 
<%@ page import="modelo.Celular" %> 
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hero Section</title>
    <script src="https://cdn.tailwindcss.com"></script> 
</head>
<body class="bg-gray-100">
    <section class="relative bg-cover bg-center h-screen" style="background-image: url('https://via.placeholder.com/1920x1080');">
        <div class="absolute inset-0 bg-black opacity-50"></div> 
        <div class="relative z-10 flex flex-col justify-center items-center h-full text-center text-white px-6">
            <h1 class="text-4xl sm:text-5xl lg:text-6xl font-bold mb-4">Bienvenidos a Nuestra Página</h1>
            <p class="text-lg sm:text-xl mb-6">Descubre productos increíbles y disfruta de experiencias únicas. ¡Explora ahora!</p>
            <a href="#explorar" class="bg-blue-600 text-white px-8 py-3 text-lg font-semibold rounded-full shadow-md transition transform hover:bg-blue-500 hover:scale-105">Explorar Ahora</a>
        </div>
    </section>
    <section id="explorar" class="mt-6">
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">

            <%  
                List<Celular> celulares = (List<Celular>) request.getAttribute("celulares");
                if (celulares != null && !celulares.isEmpty()) {
                    for (Celular celular : celulares) { 
            %>
        
                <div class="bg-white shadow-md rounded-lg overflow-hidden">
                  <div class="p-6">
                    <h2 class="text-xl font-bold mb-2"><%= celular.getNombre() %></h2>
                    <p class="text-gray-600"><strong>IMEI:</strong> <%= celular.getImei() %></p>
                    <p class="text-gray-600"><strong>Marca:</strong> <%= celular.getMarca() %></p>
                    <p class="text-gray-600"><strong>Año de Lanzamiento:</strong> <%= celular.getAnoLanzamiento() %></p>
                  </div>           
                </div>
              <% } 
                } else { 
            %>
              <p>No se encontraron celulares.</p>
            <% } %>
            </div>
    </section>

</body>
</html>
