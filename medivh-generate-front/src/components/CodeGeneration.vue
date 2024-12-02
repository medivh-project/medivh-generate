<template>
  <div class="code-generation">
    <div v-if="!selectedPlugin" class="plugin-selection">
      <h2>选择代码生成插件</h2>
      <div class="plugins-container">
        <el-row :gutter="20" justify="center">
          <el-col :xs="24" :sm="12" :md="8" :lg="6" v-for="plugin in plugins" :key="plugin.name">
            <el-card class="plugin-card" @click="selectPlugin(plugin)" :class="{ disabled: !plugin.supported }">
              <div class="plugin-content">
                <img :src="plugin.image" :alt="plugin.title" class="plugin-image">
                <el-icon class="plugin-icon" :size="48">
                  <component :is="plugin.icon" />
                </el-icon>
                <h3>{{ plugin.title }}</h3>
                <p class="plugin-description">{{ plugin.description }}</p>
                <div class="plugin-status" v-if="!plugin.supported">
                  <el-tag type="warning">即将推出</el-tag>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>

    <div v-else class="plugin-config">
      <div class="config-header">
        <el-button @click="backToSelection" class="back-button">
          <el-icon><ArrowLeft /></el-icon>
          返回插件选择
        </el-button>
        <h2>{{ selectedPlugin.title }} 配置</h2>
      </div>
      <component :is="selectedPlugin.configComponent" />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ArrowLeft, DataLine } from '@element-plus/icons-vue'
import DatabaseTableList from './DatabaseTableList.vue'

const selectedPlugin = ref(null)

const plugins = [
  {
    name: 'mybatis-plus',
    title: 'MyBatis Plus',
    description: '快速生成 MyBatis Plus 相关代码，包括实体类、Mapper、Service 等',
    icon: 'DataLine',
    image: '/images/mybatis-plus-logo.png',
    supported: true,
    configComponent: DatabaseTableList
  },
  {
    name: 'jpa',
    title: 'Spring Data JPA',
    description: '生成 JPA 实体类和仓库接口',
    icon: 'DataLine',
    image: 'https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg',
    supported: false
  },
  {
    name: 'mybatis',
    title: 'MyBatis',
    description: '生成原生 MyBatis 相关代码',
    icon: 'DataLine',
    image: '/images/mybatis-logo.jpg',
    supported: false
  }
]

const selectPlugin = (plugin) => {
  if (plugin.supported) {
    selectedPlugin.value = plugin
  }
}

const backToSelection = () => {
  selectedPlugin.value = null
}
</script>

<style scoped>
.code-generation {
  padding: 20px;
}

.plugin-selection {
  text-align: center;
}

h2 {
  margin-bottom: 2rem;
  color: var(--el-text-color-primary);
}

.plugins-container {
  margin-top: 2rem;
}

.plugin-card {
  margin-bottom: 1.5rem;
  cursor: pointer;
  transition: transform 0.3s ease;

  &:not(.disabled):hover {
    transform: translateY(-5px);
  }

  &.disabled {
    cursor: not-allowed;
    opacity: 0.7;
  }
}

.plugin-content {
  padding: 1.5rem;
  text-align: center;
}

.plugin-icon {
  color: var(--el-color-primary);
  margin-bottom: 1rem;
}

h3 {
  margin: 1rem 0;
  color: var(--el-text-color-primary);
}

.plugin-description {
  color: var(--el-text-color-secondary);
  font-size: 0.9rem;
  margin-bottom: 1rem;
}

.plugin-status {
  margin-top: 1rem;
}

.config-header {
  display: flex;
  align-items: center;
  margin-bottom: 2rem;
  gap: 1rem;

  .back-button {
    display: flex;
    align-items: center;
    gap: 0.5rem;
  }

  h2 {
    margin: 0;
  }
}

.plugin-image {
  width: 120px;
  height: 120px;
  object-fit: contain;
  margin-bottom: 1rem;
}
</style>
