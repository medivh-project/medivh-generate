<template>
    <div>
        <div class="page-header">
            <h1>选择您的数据源</h1>
            <p>请选择需要处理的数据类型</p>
        </div>
        <div class="card-container">
            <el-row :gutter="20" justify="center">
                <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="card in cards" :key="card.title">
                    <el-card class="menu-card" @click="handleCardClick(card)">
                        <div class="card-bg">
                            <div class="card-text">
                                <h3 class="card-title">{{ card.title }}</h3>
                                <img :src="card.imageUrl" alt="Card Image" />
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>
        <component v-if="selectedCard" :is="currentComponent" />
    </div>
</template>
  
<script setup>
import { ref, markRaw } from 'vue';
import HelloWorld from '../components/HelloWorld.vue';

const selectedCard = ref(null);
const currentComponent = ref(null);

const cards = [
    {
        title: '关系型数据库',
        imageUrl:
            'https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/azuresqldatabase/azuresqldatabase-original.svg',
        component: "HelloWorld",
    },
    {
        title: 'MongoDB',
        imageUrl:
            'https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mongodb/mongodb-plain-wordmark.svg',
        component: "HelloWorld",
    },
    {
        title: 'JSON',
        imageUrl:
            'https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/json/json-original.svg',
        component: "HelloWorld",
    },
    {
        title: '实体转',
        imageUrl:
            'https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original-wordmark.svg',
        component: "HelloWorld",
    },
];

const handleCardClick = (card) => {
    selectedCard.value = card;
    currentComponent.value = markRaw(card.component);
};
</script>
  
<style lang="less">
.page-header {
    text-align: center;
    padding: 40px 0;
    background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
    margin-bottom: 40px;

    h1 {
        font-size: 2.5em;
        color: #2c3e50;
        margin-bottom: 10px;
    }

    p {
        color: #666;
        font-size: 1.2em;
    }
}

.card-container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
    min-height: 60vh;
}

.menu-card {
    height: 300px;
    margin-bottom: 20px;
    border-radius: 12px;
    overflow: hidden;
    transition: all 0.3s ease;
    cursor: pointer;
    border: 2px solid transparent;

    &:hover {
        transform: translateY(-8px);
        box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2) !important;
        border-color: #409eff;

        .card-bg {
            background: linear-gradient(145deg, #ffffff 0%, #f0f9ff 100%);
        }

        .card-title {
            color: #409eff;
        }
    }

    .card-bg {
        height: 100%;
        padding: 30px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-between;
        text-align: center;
        background: linear-gradient(145deg, #ffffff 0%, #f8f9fa 100%);
        transition: all 0.3s ease;
    }

    .card-text {
        margin-bottom: 20px;
        display: flex;
        flex-direction: column;
        align-items: center;
        height: 100%;

        img {
            width: 120px;
            height: 120px;
            object-fit: contain;
            margin-top: auto;
        }
    }

    .card-title {
        font-size: 1.5em;
        color: #2c3e50;
        margin-bottom: 10px;
    }
}
</style>
  