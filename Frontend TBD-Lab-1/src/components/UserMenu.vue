<script setup>
import { ref, onMounted } from 'vue';
import productoService from '../services/productoService';
import categoriaService from '../services/categoriaService'; 
import { useRouter } from 'vue-router';
import clienteService from '../services/clienteService';

// Filtros
const nombreFilter = ref('');
const categoriaFilter = ref(null);
const productos = ref([]);
const categorias = ref([]);  // Para almacenar las categorías obtenidas del backend

// Router
const router = useRouter();

// Obtener categorías desde el backend
const listCategorias = async () => {
  try {
    const response = await categoriaService.listCategorias();
    categorias.value = response;  // Asignar las categorías a la variable
  } catch (error) {
    console.error('Error al listar categorías:', error);
  }
};

// Función para obtener los productos con filtros
const getProductos = async () => {
  try {
    const response = await productoService.getProductos(nombreFilter.value, categoriaFilter.value);
    productos.value = response;
  } catch (error) {
    console.error('Error al obtener productos:', error);
  }
};

// Obtener categorías y productos al montar el componente
onMounted(() => {
  listCategorias();
  getProductos();
});

// Función para manejar logout
const handleLogout = async () => {
  try {
    const cliente = JSON.parse(localStorage.getItem('cliente'));
    if (cliente?.idCliente) {
      await clienteService.logoutCliente(cliente.idCliente);
    }
    localStorage.removeItem('cliente');
    router.push('/');
  } catch (error) {
    console.error('Error al cerrar sesión:', error);
  }
};
</script>

<template>
  <div class="user-menu">
    <aside class="sidebar">
      <h2>Opciones</h2>
      <ul>
        <li><router-link to="/lista-ordenes">Lista de órdenes</router-link></li>
        <li><router-link to="/devolucion-productos">Devolución de productos</router-link></li>
        <li><router-link to="/historial-compras">Historial de compras</router-link></li>
        <li><router-link to="/notifications">Notificaciones</router-link></li>
        <li class="logout-item">
          <button @click="handleLogout" class="logout-button">
            Cerrar sesión
          </button>
        </li>
      </ul>
    </aside>

    <main class="content">
      <section>
        <h1 class="welcome-msg">Bienvenido a E-commerce</h1>

        <!-- Filtros -->
        <div class="filters">
          <input 
            v-model="nombreFilter" 
            @input="getProductos" 
            type="text" 
            placeholder="Buscar por nombre"
          />
          <select v-model="categoriaFilter" @change="getProductos">
            <option value="">Seleccionar categoría</option>
            <option v-for="categoria in categorias" :key="categoria.id_categoria" :value="categoria.id_categoria">
              {{ categoria.nombre }}
            </option>
          </select>
        </div>

        <!-- Listado de productos -->
        <div class="productos-lista">
          <ul>
            <li v-for="producto in productos" :key="producto.id_producto">
              <h3>{{ producto.nombre }}</h3>
              <p>{{ producto.descripcion }}</p>
              <p>Precio: ${{ producto.precio }}</p>
              <p>Stock: {{ producto.stock }}</p>
              <p>Estado: {{ producto.estado }}</p>
            </li>
          </ul>
        </div>
      </section>
    </main>
  </div>
</template>

<style scoped>
.user-menu {
  display: flex;
  height: 100vh;
}

.sidebar {
  width: 250px;
  background-color: #2c3e50;
  color: orange;
  padding: 1rem;
}

.sidebar h2 {
  margin: 0 0 1rem;
  font-size: 1.5rem;
}

.sidebar ul {
  list-style: none;
  padding: 0;
}

.sidebar li {
  margin: 1rem 0;
}

.sidebar a {
  color: #42b983;
  text-decoration: none;
  transition: color 0.3s;
}

.sidebar a:hover {
  color: #1abc9c;
}

.logout-button {
  background-color: #e74c3c;
  color: white;
  border: none;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  cursor: pointer;
  width: 100%;
  transition: background-color 0.3s;
}

.logout-button:hover {
  background-color: #c0392b;
}

.logout-item {
  margin-top: 2rem;
}

.content {
  flex: 1;
  padding: 1rem;
}

.welcome-msg {
  color: orange;
}

.filters {
  display: flex;
  gap: 1rem;
  margin-bottom: 20px;
}

.filters input,
.filters select {
  padding: 0.5rem;
  font-size: 1rem;
}

.productos-lista ul {
  list-style: none;
  padding: 0;
}

.productos-lista li {
  padding: 1rem;
  background: #f9f9f9;
  margin: 10px 0;
  border-radius: 5px;
}

.productos-lista h3 {
  font-size: 1.5rem;
}
</style>
