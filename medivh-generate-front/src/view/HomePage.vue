<template>
  <div class="page-header">
    <h1>选择您的数据源</h1>
    <p>请选择需要处理的数据类型</p>
    <el-button @click="test">调api测试按钮！！！！</el-button>
  </div>
  <div class="card-container">
    <el-row :gutter="20" justify="center">
      <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="card in cards" :key="card">
        <el-card class="menu-card" @click="handleCardClick(card)">
          <div class="card-bg">
            <div class="card-text">
              <h3 class="card-title">{{ card.component }}</h3>
              <div class="card-content">{{ card.description }}</div>
              <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/mysql/mysql-plain-wordmark.svg" />
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
  
<script setup>
import { ref, getCurrentInstance } from 'vue';
import { useRouter } from 'vue-router'
import api from '../api/user'  // 导入api

const router = useRouter()
const { proxy } = getCurrentInstance()

const cards = ref(
  [
    {
      title: '关系型数据库',
      description: '关系型数据库',
      icon: 'DataLine',
      component: 'HelloWorld',
      imageUrl: 'https://cdn.intuji.com/2023/10/MySQL.png',
      active: false
    },
    {
      title: '非关系型数据库',
      description: '非关系型数据库',
      icon: 'Monitor',
      component: 'HelloWorld',
      imageUrl: 'https://cdn.intuji.com/2023/10/Monitor.png',
      active: false
    },
    {
      title: 'JSON',
      description: 'JSON',
      icon: 'Shield',
      component: 'HelloWorld',
      imageUrl: 'https://cdn.intuji.com/2023/10/Security.png',
      active: false
    },
    {
      title: '实体转',
      description: '集中管理您的API配置',
      icon: 'Setting',
      component: 'HelloWorld',
      imageUrl: 'https://cdn.intuji.com/2023/10/Settings.png',
      active: false
    }
  ]
)

const handleCardClick = (card) => {
  router.push({
    path: `/page/${encodeURIComponent(card.title)}`
  })
}

// 接口测试按钮
const test = async (card) => {
  try {
    let res = await api.getUser(1)

  } catch (error) {
    console.log(error);
  }
}
</script>

<style lang='less' scoped>
// 变量定义
@header-bg-gradient: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
@card-bg-gradient: linear-gradient(145deg, #ffffff 0%, #f8f9fa 100%);
@primary-color: #2c3e50;
@secondary-color: #666;

// 样式定义
.page-header {
  text-align: center;
  padding: 40px 0;
  background: @header-bg-gradient;
  margin-bottom: 40px;

  h1 {
    font-size: 2.5em;
    color: @primary-color;
    margin-bottom: 10px;
  }

  p {
    color: @secondary-color;
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

  &:hover {
    transform: translateY(-5px);
    box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15) !important;
  }

  .card-bg {
    height: 100%;
    padding: 30px;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-between;
    text-align: center;
    background: @card-bg-gradient;

    .card-text {
      margin-bottom: 20px;
    }

    .card-icon {
      width: 100px;
      height: 100px;
      object-fit: contain;
      margin-top: auto;
    }

    .card-title {
      font-size: 1.5em;
      color: @primary-color;
      margin-bottom: 10px;
    }

    .card-content {
      color: @secondary-color;
      font-size: 1.1em;
      line-height: 1.4;
    }
  }
}
</style>
  