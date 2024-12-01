<template>
  <div class="database-config">
    <el-form 
      :model="formData" 
      :rules="rules"
      ref="formRef"
      label-width="120px" 
      class="config-form"
    >
      <el-form-item label="连接字符串" prop="uri">
        <el-input 
          v-model="formData.uri" 
          placeholder="mongodb://username:password@host:port/database"
          type="textarea"
          :rows="2"
        />
      </el-form-item>
      
      <el-form-item label="数据库名称" prop="database">
        <el-input v-model="formData.database" placeholder="请输入数据库名称" />
      </el-form-item>
      
      <el-form-item label="集合名称" prop="collection">
        <el-input v-model="formData.collection" placeholder="请输入集合名称" />
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary" @click="testConnection">测试连接</el-button>
        <el-button type="success" @click="saveConfig">保存配置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'

const formRef = ref(null)
const formData = reactive({
  uri: '',
  database: '',
  collection: ''
})

const rules = reactive({
  uri: [
    { required: true, message: '请输入连接字符串', trigger: 'blur' },
    { min: 10, message: '连接字符串长度不能小于10个字符', trigger: 'blur' }
  ],
  database: [
    { required: true, message: '请输入数据库名称', trigger: 'blur' }
  ],
  collection: [
    { required: true, message: '请输入集合名称', trigger: 'blur' }
  ]
})

const testConnection = async () => {
  if (!formRef.value) return
  await formRef.value.validate((valid, fields) => {
    if (valid) {
      // 这里添加测试连接的API调用
      ElMessage.success('连接成功！')
    } else {
      ElMessage.error('请填写完整的配置信息')
    }
  })
}

const saveConfig = async () => {
  if (!formRef.value) return
  await formRef.value.validate((valid, fields) => {
    if (valid) {
      ElMessage.success('配置已保存！')
    } else {
      ElMessage.error('请填写完整的配置信息')
    }
  })
}
</script>

<style scoped>
.database-config {
  max-width: 600px;
  margin: 20px auto;
  padding: 20px;
}

.config-form {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style> 