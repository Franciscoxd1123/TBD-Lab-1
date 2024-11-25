import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '../views/HomeView.vue';
import RegisterView from '../views/RegisterView.vue';
import LoginView from '../views/LoginView.vue';
import UserMenuView from '../views/UserMenu.vue';
import CreateTarea from '../components/CreateTarea.vue';
import TareasListEdit from '../components/TareasListEdit.vue';
import EditarTareaView from '../views/EditarTareaView.vue';
import MarkTask from '../components/MarkTask.vue';
import ViewTask from '../components/ViewTask.vue';
import Notifications from '../components/Notifications.vue';
import HistorialCompra from '../components/HistorialCompra.vue';
import ListaOrdenes from '../components/ListaOrdenes.vue';


const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
  },
  {
    path: '/register',
    name: 'Register',
    component: RegisterView,
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginView,
  },
  {
    path: '/user-menu', 
    name: 'UserMenu',
    component: UserMenuView,
  },
  {
    path: '/create-tarea',
    name: 'CreateTarea',
    component: CreateTarea,
  },
  {
    path: '/tareas-list-edit',
    name: 'TareasListEdit',
    component: TareasListEdit,
  },
  {
    path: '/mark-task',
    name: 'MarkTask',
    component: MarkTask,
  },
  {
    path: '/editar-tarea/:id',
    name: 'EditarTarea',
    component: EditarTareaView,
  },
  {
    path: '/view-task',
    name: 'ViewTask',
    component: ViewTask,
  },
  {
    path: '/notifications',
    name: 'Notifications',
    component: Notifications,
  },
  {
    path: '/historial-compras',
    name: 'HistorialCompra',
    component: HistorialCompra,
  },
  {
    path: '/lista-ordenes',
    name: 'ListaOrdenes',
    component: ListaOrdenes,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;