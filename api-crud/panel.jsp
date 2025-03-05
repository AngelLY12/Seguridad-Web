<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.User" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Administración</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

    <!-- Header -->
    <header class="bg-blue-500 text-white py-4 text-center">
        <h1 class="text-2xl font-bold">Panel de Administración</h1>
    </header>

    <!-- Contenedor principal -->
    <div class="max-w-5xl mx-auto bg-white p-6 mt-6 shadow-md rounded-lg">

        <!-- Lista de Usuarios -->
        <h2 class="text-xl font-semibold mb-4">Lista de Usuarios</h2>
        <div class="overflow-x-auto">
            <table class="w-full border-collapse border border-gray-300">
                <thead class="bg-blue-500 text-white">
                    <tr>
                       
                        <th class="p-2 border">RFC</th>
                        <th class="p-2 border">Nombre</th>
                        <th class="p-2 border">Apellido</th>
                        <th class="p-2 border">Perfil</th>
                        <th class="p-2 border">Acciones</th>
                    </tr>
                </thead>
                <tbody class="text-gray-700 text-center">
                    <%
                        List<User> usuarios = (List<User>) request.getAttribute("users");
                        if (usuarios != null) {
                            for (User usuario : usuarios) {
                    %>
                                <tr class="hover:bg-gray-100">
                                
                                    <td class="p-2 border"><%= usuario.getRFC() %></td>
                                    <td class="p-2 border"><%= usuario.getName() %></td>
                                    <td class="p-2 border"><%= usuario.getLastName() %></td>
                                    <td class="p-2 border"><%= usuario.getProfile() %></td>
                                    <td class="p-2 border flex justify-center items-center  gap-2">
					        <form action="updateUser.jsp" method="post">
              					<input type="hidden" name="rfc" value="<%= usuario.getRFC() %>">
              					<input type="hidden" name="name" value="<%= usuario.getName() %>">
             					 <input type="hidden" name="lastName" value="<%= usuario.getLastName() %>">
              					<input type="hidden" name="profile" value="<%= usuario.getProfile() %>">
              					<input type="submit" value="✏️" class="cursor-pointer px-3 py-1"/>
            				</form>
                                        
                                        <button class=" px-3 py-1" onclick="openModal('<%= usuario.getRFC() %>')">❌</button>
                                    </td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                        <tr>
                            <td colspan="6" class="p-4 text-center text-gray-500">No hay usuarios registrados.</td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
	
	<div id="modal-bg" class="fixed inset-0 bg-gray-800 bg-opacity-75 hidden flex items-center justify-center z-100">
    <div class="bg-white rounded-lg shadow-lg p-6 max-w-md w-full mx-auto">
        <h2 class="text-lg font-bold mb-4">¿Estás seguro de eliminar este registro?</h2>
        <form action="${pageContext.request.contextPath}/DeleteUserController" method="post">
            <input type="hidden" id="rfc" name="rfc">
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
  function openModal(rfc) {
    document.getElementById("rfc").value = rfc;
      document.getElementById('modal-bg').classList.remove('hidden');
  }

  function closeModal() {
      document.getElementById('modal-bg').classList.add('hidden');
  }
</script>
</body>
</html>
