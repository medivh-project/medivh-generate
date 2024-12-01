import { createRouter, createWebHistory } from 'vue-router'

// 导入页面组件
const routes = [
    {
        path: '/Home',
        name: 'Home',
        component: () => import('./view/HomePage.vue')
    },
    {
        path: '/showTest',
        name: 'showTest',
        component: () => import('./view/showTest.vue')
    },
    {
        path: '/',
        redirect: 'showTest'
    }
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

export default router
