<template>
  <div id="app">
    <el-menu :style="menuStyle" :default-active="activeIndex" class="menu-bar" mode="horizontal" @select="handleSelect">
      <div class="logo-container" @click="goHome">
        <img src="../public/images/medivh-logo.jpg" alt="Logo" class="logo">
        <el-menu-item index="1">Medivh-Generate</el-menu-item>
      </div>
      <el-sub-menu index="2">
        <template #title>Workspace</template>
        <el-menu-item index="2-1">插件管理</el-menu-item>
        <el-menu-item index="2-2">item two</el-menu-item>
        <el-menu-item index="2-3">item three</el-menu-item>
        <el-sub-menu index="2-4">
          <template #title>item four</template>
          <el-menu-item index="2-4-1">item one</el-menu-item>
          <el-menu-item index="2-4-2">item two</el-menu-item>
          <el-menu-item index="2-4-3">item three</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-menu-item index="3" disabled>Info</el-menu-item>
    </el-menu>
    <div>
      <router-view />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
const activeIndex = ref(null);
const router = useRouter();

const menuStyle = ref({
  position: 'fixed',
  top: '0',
  left: '0',
  width: '100%',
  zIndex: '1000', // 确保导航菜单在顶部
  backgroundColor: '#65639f', // 默认背景透明
  transition: 'background-color 0.3s ease', // 添加平滑过渡
});

const handleSelect = (index) => {
  console.log('选中的菜单项:', index);
};

const goHome = () => {
  router.push('/');
};
</script>

<style lang="less" scoped>
::v-deep .el-menu--horizontal>.logo-container {
  margin-right: auto;
  display: flex;
  align-items: center;
  cursor: pointer;
}

.logo {
  height: 40px; // 调整 logo 大小
  margin: 0 10px;
  vertical-align: middle;
}

/* 确保页面内容不会被导航遮挡 */
#app {
  padding-top: 60px;
}

/* 菜单项的样式 */
.el-menu-item {
  font-size: 16px;
  color: #fff;
}

.el-menu-item:hover {
  background-color: #66b1ff;
}

.el-submenu .el-menu {
  background-color: #1f2d3d;
}
</style>
