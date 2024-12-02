import { createRouter, createWebHistory } from 'vue-router'

// 导入页面组件
const routes = [
    {
        path: '/Home',
        name: 'Home',
        component: () => import('./view/HomePage.vue')
    },
    {
        path: '/database-config',
        name: 'DatabaseConfig',
        component: () => import('./components/DatabaseConfig.vue')
    },
    {
        path: '/mongodb-config',
        name: 'MongodbConfig',
        component: () => import('./components/MongodbConfig.vue'),
        meta: { notSupported: true }
    },
    {
        path: '/json-config',
        name: 'JsonConfig',
        component: () => import('./components/JsonConfig.vue'),
        meta: { notSupported: true }
    },
    {
        path: '/javabean-config',
        name: 'JavaBeanConfig',
        component: () => import('./components/JavaBeanConfig.vue'),
        meta: { notSupported: true }
    },
    {
        path: '/code-generation',
        name: 'CodeGeneration',
        component: () => import('./components/CodeGeneration.vue')
    },
    {
        path: '/',
        redirect: 'Home'
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
