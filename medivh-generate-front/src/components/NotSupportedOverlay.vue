<template>
  <div class="not-supported-overlay">
    <div class="overlay-content">
      <el-icon class="warning-icon"><WarningFilled /></el-icon>
      <h2>功能暂未开放</h2>
      <p>该功能正在开发中，敬请期待...</p>
      <el-button type="primary" @click="goBack" class="back-button">
        返回首页
      </el-button>
    </div>
  </div>
</template>

<script setup>
import { WarningFilled } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const goBack = () => {
  router.push({
    name: 'Home',
    query: { force: Date.now() },
    replace: true
  })
}
</script>

<style scoped>
.not-supported-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(255, 255, 255, 0.9);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.overlay-content {
  text-align: center;
  padding: 2rem;
}

.warning-icon {
  font-size: 48px;
  color: var(--el-color-warning);
  margin-bottom: 1rem;
}

h2 {
  color: var(--el-text-color-primary);
  margin-bottom: 1rem;
}

p {
  color: var(--el-text-color-secondary);
  margin-bottom: 2rem;
}

.back-button {
  min-width: 120px;
}
</style>
