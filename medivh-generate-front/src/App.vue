<template>
  <div id="app">
    <!-- 横向导航菜单 -->
    <el-menu :style="menuStyle" :default-active="activeIndex" class="menu-bar" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">Processing Center</el-menu-item>
      <el-sub-menu index="2">
        <template #title>Workspace</template>
        <el-menu-item index="2-1">item one</el-menu-item>
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
const activeIndex = ref(null);

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
</script>


<style lang="less" scoped>
::v-deep .el-menu--horizontal>.el-menu-item:nth-child(1) {
  margin-right: auto;
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
