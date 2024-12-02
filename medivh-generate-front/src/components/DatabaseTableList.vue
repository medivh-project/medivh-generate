<template>
  <div class="database-table-list">
    <div class="custom-header">
      <h2>数据库表列表</h2>
      <el-button
        type="primary"
        @click="handleGenerateAllCode"
      >
        全部导出
      </el-button>
    </div>
    <el-table
      :data="tables"
      style="width: 100%"
      @selection-change="handleSelectionChange"
      border
      stripe
      highlight-current-row
      class="custom-table"
    >
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="tableName"
          label="表名">
      </el-table-column>
      <el-table-column
          prop="comment"
          label="注释"
          width="180">
      </el-table-column>
      <el-table-column
          prop="engine"
          label="引擎">
      </el-table-column>
      <el-table-column
          prop="db"
          label="数据库">
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="创建时间">
        <template #default="scope">
          {{ formatDate(scope.row.createTime) }}
        </template>
      </el-table-column>
      <el-table-column
          label="操作"
          width="120">
        <template #default="scope">
          <el-button
              type="primary"
              size="small"
              @click="handleGenerateCode(scope.row)">
            生成代码
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import {defineEmits, onMounted, ref} from 'vue'
import {getTables} from '@/api/db.js'

const tables = ref([])

const emit = defineEmits(['selectionChange', 'generateCode', 'generateAllCode'])
const selectedTables = ref([])

onMounted(() => {
  getTables().then((data) => {
    tables.value = data
  })
})

const handleSelectionChange = (selection) => {
  selectedTables.value = selection
  emit('selectionChange', selection)
}

const formatDate = (date) => {
  if (!date) return ''
  return new Date(date).toLocaleString()
}

const handleGenerateCode = (row) => {
  emit('generateCode', row)
}

const handleGenerateAllCode = () => {
  if (selectedTables.value.length === 0) {
    ElMessage.warning('请先选择要生成代码的表')
    return
  }
  emit('generateAllCode', selectedTables.value)
}
</script>

<style scoped>
.database-table-list {
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.custom-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  padding: 0 10px;
}

.custom-header h2 {
  margin: 0;
  color: #303133;
  font-size: 20px;
  font-weight: 600;
}

.custom-table {
  margin-top: 15px;
}

:deep(.el-table) {
  border-radius: 4px;
  overflow: hidden;
}

:deep(.el-table th) {
  background-color: #f5f7fa !important;
  color: #606266;
  font-weight: 600;
  height: 50px;
}

:deep(.el-table td) {
  padding: 12px 0;
}

:deep(.el-button--small) {
  padding: 8px 15px;
}
</style>
