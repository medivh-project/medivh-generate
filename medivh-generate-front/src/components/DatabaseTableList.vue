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
      :data="currentPageData"
      style="width: 100%"
      @selection-change="handleSelectionChange"
      border
      stripe
      highlight-current-row
      class="custom-table"
    >
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

    <div class="pagination-container">
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[10, 20, 50, 100]"
        :total="tables.length"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes, prev, pager, next, jumper"
      />
    </div>
  </div>
</template>

<script setup>
import {defineEmits, onMounted, ref, computed} from 'vue'
import {getTables, generateCode, generateAllCode} from '@/api/db.js'
import { ElMessage } from 'element-plus'

const props = defineProps({
  pluginName: {
    type: String,
    required: true
  }
})

const tables = ref([])
const currentPage = ref(1)
const pageSize = ref(10)

// 计算当前页的数据
const currentPageData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value
  const end = start + pageSize.value
  return tables.value.slice(start, end)
})

// 处理页码改变
const handleCurrentChange = (val) => {
  currentPage.value = val
}

// 处理每页显示数量改变
const handleSizeChange = (val) => {
  pageSize.value = val
  currentPage.value = 1 // 重置到第一页
}

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

const downloadFile = (blob, fileName) => {
  console.log(fileName)
  const url = window.URL.createObjectURL(blob)
  const link = document.createElement('a')
  link.href = url
  link.download = fileName || 'generated-code.zip' // 默认文件名
  document.body.appendChild(link)
  link.click()
  document.body.removeChild(link)
  window.URL.revokeObjectURL(url)
}

const handleGenerateCode = async (row) => {
  try {
    const response = await generateCode(props.pluginName, row)
    downloadFile(response, `${row.tableName}-code.zip`)
    ElMessage.success('代码生成成功')
  } catch (error) {
    ElMessage.error('代码生成失败：' + (error.message || '未知错误'))
  }
}

const handleGenerateAllCode = async () => {
  try {
    const response = await generateAllCode(props.pluginName, tables.value)
    downloadFile(response, 'all-tables-code.zip')
    ElMessage.success('批量代码生成成功')
  } catch (error) {
    ElMessage.error('批量代码生成失败：' + (error.message || '未知错误'))
  }
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

.pagination-container {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
}

:deep(.el-pagination) {
  padding: 0;
  margin: 16px 0;
}
</style>
