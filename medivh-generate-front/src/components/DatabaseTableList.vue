<template>
  <div class="table-list">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>数据库表</span>
        </div>
      </template>
      <el-table
          :data="tables"
          style="width: 100%"
          @selection-change="handleSelectionChange"
      >
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column
            prop="tableName"
            label="表名"
            width="180">
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
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import {defineEmits, onMounted, ref} from 'vue'
import {getTables} from '@/api/db.js'

const tables = ref([])

const emit = defineEmits(['selectionChange'])

onMounted(() => {
  getTables().then((data) => {
    tables.value = data
  })
})

const handleSelectionChange = (selection) => {
  emit('selectionChange', selection)
}

const formatDate = (date) => {
  if (!date) return ''
  return new Date(date).toLocaleString()
}
</script>

<style scoped>
.table-list {
  margin-bottom: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>
