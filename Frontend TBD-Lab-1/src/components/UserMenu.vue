<template>
  <div class="user-menu">
    <!-- Sidebar -->
    <aside class="sidebar">
      <h2>Opciones</h2>
      <ul>
        <li><router-link to="/lista-ordenes">Lista de órdenes</router-link></li>
        <li><router-link to="/devolucion-productos">Devolución de productos</router-link></li>
        <li><router-link to="/historial-compras">Historial de compras</router-link></li>
        <li class="logout-item">
          <button @click="handleLogout" class="logout-button">
            Cerrar sesión
          </button>
        </li>
      </ul>
    </aside>

    <!-- Contenido Principal -->
    <main class="content">
      <div class="create-tarea">
        <h1>Bienvenido a E-commerce</h1>

        <!-- Buscador y Filtros -->
        <div class="search-filters">
          <div class="form-field">
            <div class="label">Buscar Producto</div>
            <input 
              v-model="searchTerm"
              type="text"
              placeholder="Buscar por nombre..."
              @input="filterProducts"
            >
          </div>

          <div class="form-field">
            <div class="label">Categoría</div>
            <select 
              v-model="selectedCategory"
              @change="filterProducts"
              class="select-field"
            >
              <option value="">Todas las categorías</option>
              <option 
                v-for="categoria in categorias" 
                :key="categoria.idCategoria"
                :value="categoria.idCategoria"
              >
                {{ categoria.nombre }}
              </option>
            </select>
          </div>
        </div>

        <!-- Lista de Productos -->
        <div class="products-grid">
          <div 
            v-for="producto in filteredProducts" 
            :key="producto.idProducto"
            class="product-card"
          >
            <div class="product-content">
              <h3 class="product-title">{{ producto.nombre }}</h3>
              <p class="product-description">{{ producto.descripcion }}</p>
              <div class="product-details">
                <span class="product-price">${{ producto.precio.toFixed(2) }}</span>
                <span :class="['product-stock', { 'low-stock': producto.stock < 10 }]">
                  Stock: {{ producto.stock }}
                </span>
              </div>
              <button 
                class="add-to-cart-btn"
                @click="addToCart(producto)"
                :disabled="producto.stock === 0"
              >
                {{ producto.stock === 0 ? 'Sin Stock' : 'Agregar a la Orden' }}
              </button>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<style scoped>
.user-menu {
  display: flex;
  min-height: 100vh;
  background: #1a2634;
}

.sidebar {
  width: 250px;
  background-color: #2c3e50;
  color: orange;
  padding: 1rem;
  position: fixed;
  height: 100vh;
  overflow-y: auto;
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
  margin-left: 250px;
  padding: 2rem;
}

.create-tarea {
  background: #2c3e50;
  padding: 2rem;
  border-radius: 0.75rem;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.search-filters {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
  margin-bottom: 2rem;
}

h1 {
  color: orange;
  text-align: center;
  font-size: 1.8rem;
  margin-bottom: 2rem;
}

.search-filters {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
  margin-bottom: 2rem;
}

.form-field {
  margin-bottom: 1rem;
  padding-right: 1rem;
}

.label {
  color: #42b983;
  text-align: center;
  margin-bottom: 0.5rem;
  font-size: 1rem;
}

input,
.select-field {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid orange;
  border-radius: 0.5rem;
  font-size: 0.9rem;
  background: black;
  color: #42b983;
}

.products-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 1.5rem;
  margin-bottom: 2rem;
}

.product-card {
  background: black;
  border: 1px solid orange;
  border-radius: 0.5rem;
  overflow: hidden;
  transition: transform 0.2s;
}

.product-card:hover {
  transform: translateY(-4px);
}

.product-content {
  padding: 1rem;
}

.product-title {
  color: #42b983;
  font-size: 1.1rem;
  margin-bottom: 0.5rem;
}

.product-description {
  color: #8f9ca8;
  font-size: 0.9rem;
  margin-bottom: 1rem;
  min-height: 2.7rem;
}

.product-details {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.product-price {
  color: orange;
  font-size: 1.2rem;
  font-weight: bold;
}

.product-stock {
  color: #42b983;
  font-size: 0.9rem;
}

.low-stock {
  color: #e74c3c;
}

.add-to-cart-btn {
  width: 100%;
  padding: 0.75rem;
  background: #42b983;
  color: black;
  border: none;
  border-radius: 0.5rem;
  font-size: 0.9rem;
  cursor: pointer;
  transition: background-color 0.2s;
}

.add-to-cart-btn:hover:not(:disabled) {
  background: #3aa876;
}

.add-to-cart-btn:disabled {
  background: #767676;
  cursor: not-allowed;
}

.menu-buttons {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1rem;
  margin-top: 2rem;
}

.menu-btn {
  padding: 0.75rem;
  border: none;
  border-radius: 0.5rem;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.2s;
  position: relative;
}

.logout-btn {
  background: #e74c3c;
  color: black;
}

.logout-btn:hover {
  background: #c0392b;
}

/* Ajustes responsive */
@media (max-width: 768px) {
  .user-menu {
    flex-direction: column;
  }

  .sidebar {
    width: 100%;
    height: auto;
    position: static;
  }

  .content {
    margin-left: 0;
  }

  .search-filters {
    grid-template-columns: 1fr;
  }

  .products-grid {
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  }
}
</style>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import productoService from '../services/productoService';
import categoriaService from '../services/categoriaService';
import clienteService from '../services/clienteService';

const router = useRouter();
const productos = ref([]);
const categorias = ref([]);
const searchTerm = ref('');
const selectedCategory = ref('');
const filteredProducts = ref([]);

// Cargar productos y categorías
const loadData = async () => {
  try {
    const [productosResponse, categoriasResponse] = await Promise.all([
      productoService.listProductos(),
      categoriaService.listCategorias()
    ]);
    
    productos.value = productosResponse.data;
    categorias.value = categoriasResponse.data;
    filterProducts();
  } catch (error) {
    console.error('Error al cargar datos:', error);
  }
};

// Filtrar productos
const filterProducts = () => {
  filteredProducts.value = productos.value.filter(producto => {
    const matchesSearch = producto.nombre.toLowerCase().includes(searchTerm.value.toLowerCase()) ||
                         producto.descripcion.toLowerCase().includes(searchTerm.value.toLowerCase());
    const matchesCategory = !selectedCategory.value || producto.idCategoria === selectedCategory.value;
    
    return matchesSearch && matchesCategory;
  });
};

// Función para cerrar sesión
const handleLogout = async () => {
  try {
    const userData = JSON.parse(localStorage.getItem('userData'));
    if (userData?.idCliente) {
      await clienteService.logoutCliente(userData.idCliente);
    }
  } catch (error) {
    console.error('Error al cerrar sesión:', error);
  } finally {
    // Limpiar datos de sesión y redireccionar siempre
    localStorage.clear(); // Limpia todo el localStorage
    router.push('/');
  }
};

// Función para agregar al carrito (opcional, puede eliminarse si ya no tiene uso)
const addToCart = (producto) => {
  console.log(`Producto ${producto.nombre} agregado a la orden.`);
};

onMounted(() => {
  loadData();
});
</script>