<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
    <!-- Tailwind CSS v4 CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        teal: tailwind.colors.teal,
                    }
                }
            }
        }
    </script>
</head>
<body class="bg-teal-50 min-h-screen flex flex-col items-center p-8">

<h2 class="text-3xl font-bold text-teal-700 mb-6">Gestión de Clientes</h2>

<form action="clientes" method="post" class="w-full max-w-md bg-white p-6 rounded-xl shadow-md mb-10">
    <input type="hidden" name="accion" value="agregar">

    <div class="mb-4">
        <label class="block text-teal-700 font-semibold mb-2">Nombre:</label>
        <input type="text" name="nombre" required
               class="w-full px-4 py-2 border border-teal-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-500">
    </div>

    <div class="mb-4">
        <label class="block text-teal-700 font-semibold mb-2">Email:</label>
        <input type="email" name="email" required
               class="w-full px-4 py-2 border border-teal-300 rounded-md focus:outline-none focus:ring-2 focus:ring-teal-500">
    </div>

    <button type="submit"
            class="w-full bg-teal-600 text-white font-semibold py-2 px-4 rounded-md hover:bg-teal-700 transition">
        Agregar Cliente
    </button>
</form>

<h3 class="text-2xl font-semibold text-teal-700 mb-4">Lista de Clientes</h3>

<div class="w-full overflow-x-auto">
    <table class="min-w-full bg-white rounded-xl shadow-md overflow-hidden">
        <thead class="bg-teal-600 text-white">
        <tr>
            <th class="py-3 px-4 text-left">ID</th>
            <th class="py-3 px-4 text-left">Nombre</th>
            <th class="py-3 px-4 text-left">Email</th>
            <th class="py-3 px-4 text-left">Acción</th>
        </tr>
        </thead>
        <tbody class="divide-y divide-teal-200">
        <c:forEach var="cliente" items="${clientes}">
            <tr class="hover:bg-teal-100">
                <td class="py-2 px-4">${cliente.id}</td>
                <td class="py-2 px-4">${cliente.nombre}</td>
                <td class="py-2 px-4">${cliente.email}</td>
                <td class="py-2 px-4">
                    <form action="clientes" method="post" class="inline">
                        <input type="hidden" name="accion" value="eliminar">
                        <input type="hidden" name="id" value="${cliente.id}">
                        <button type="submit"
                                class="bg-red-500 text-white font-semibold py-1 px-3 rounded hover:bg-red-600 transition">
                            Eliminar
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
