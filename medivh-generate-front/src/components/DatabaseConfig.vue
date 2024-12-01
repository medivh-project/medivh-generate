<template>
  <div class="database-config">
    <el-form 
      :model="formData" 
      :rules="rules"
      ref="formRef"
      label-width="120px" 
      class="config-form"
    >
      <el-form-item label="数据库类型" prop="dbType">
        <el-select v-model="formData.dbType" placeholder="请选择数据库类型">
          <el-option label="MySQL" value="mysql" />
          <el-option label="PostgreSQL" value="postgresql" />
          <el-option label="Oracle" value="oracle" />
          <el-option label="SQLServer" value="sqlserver" />
        </el-select>
      </el-form-item>

      <el-form-item label="主机地址" prop="host">
        <el-input v-model="formData.host" placeholder="请输入主机地址" />
      </el-form-item>

      <el-form-item label="端口" prop="port">
        <el-input v-model="formData.port" placeholder="请输入端口号" />
      </el-form-item>

      <el-form-item label="数据库名称" prop="database">
        <el-input v-model="formData.database" placeholder="请输入数据库名称" />
      </el-form-item>

      <el-form-item label="用户名" prop="username">
        <el-input v-model="formData.username" placeholder="请输入用户名" />
      </el-form-item>

      <el-form-item label="密码" prop="password">
        <el-input 
          v-model="formData.password" 
          type="password" 
          placeholder="请输入密码" 
          show-password
        />
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
  dbType: '',
  host: '',
  port: '',
  database: '',
  username: '',
  password: ''
})

const rules = reactive({
  dbType: [
    { required: true, message: '请选择数据库类型', trigger: 'change' }
  ],
  host: [
    { required: true, message: '请输入主机地址', trigger: 'blur' },
    { type: 'string', min: 1, message: '主机地址不能为空', trigger: 'blur' }
  ],
  port: [
    { required: true, message: '请输入端口号', trigger: 'blur' },
    { pattern: /^[0-9]+$/, message: '端口号必须为数字', trigger: 'blur' }
  ],
  database: [
    { required: true, message: '请输入数据库名称', trigger: 'blur' }
  ],
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' }
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