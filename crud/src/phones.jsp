<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %> 
<%@ page import="modelo.Celular" %> 

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Lista de Celulares</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
  <header class="bg-blue-600 text-white py-4">
    <div class="container mx-auto text-center">
      <h1 class="text-3xl font-bold">Lista de Celulares agregados</h1>
      <a href="./src/index.jsp" class="hover:underline underline-offset-8">Agregar</a>
      
    </div>
  </header>
  <main class="container mx-auto p-6">
    <form action="${pageContext.request.contextPath}/SelectController" method="get">
        <input type="hidden" class="none cursor-pointer hover:underline underline-offset-8" value="Ver telefonos">
    </form>
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
          <span class="flex justify-center align-center bg-blue-600 gap-6">
            <form action="./src/update.jsp" method="post">
              <input type="hidden" name="imei" value="<%= celular.getImei() %>">
              <input type="hidden" name="nombre" value="<%= celular.getNombre() %>">
              <input type="hidden" name="anoLanzamiento" value="<%= celular.getAnoLanzamiento() %>">
              <input type="hidden" name="marca" value="<%= celular.getMarca() %>">
              <input type="submit" value="✏️" class="cursor-pointer shadow-md shadow-black rounded-lg px-2 py-1 mb-2 mt-2"/>
            </form>
            <button class="text-white px-2 py-1 mb-2 mt-2 shadow-md shadow-black rounded-lg" onclick="openModal('<%= celular.getImei() %>')">
              ❌
            </button>  
          </span>
                    
        </div>
      <% } 
        } else { 
    %>
      <p>No se encontraron celulares.</p>
    <% } %>
    </div>
  </main>

  <div id="modal-bg" class="fixed inset-0 bg-gray-800 bg-opacity-75 hidden flex items-center justify-center z-100">
    <div class="bg-white rounded-lg shadow-lg p-6 max-w-md w-full mx-auto">
        <h2 class="text-lg font-bold mb-4">¿Estás seguro de eliminar este registro?</h2>
        <form action="${pageContext.request.contextPath}/DeleteController" method="post">
            <input type="hidden" id="imei" name="imei">
            <div class="flex justify-end gap-4">
                <input type="submit" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700" value="Eliminar">
                    
                </input>
                <button type="button" class="bg-gray-300 px-4 py-2 rounded hover:bg-gray-400" onclick="closeModal()">
                    Cancelar
                </button>
            </div>
        </form>
    </div>
</div>


<script>
  function openModal(imei) {
    document.getElementById("imei").value = imei;
      document.getElementById('modal-bg').classList.remove('hidden');
  }

  function closeModal() {
      document.getElementById('modal-bg').classList.add('hidden');
  }
</script>
</body>
</html>


