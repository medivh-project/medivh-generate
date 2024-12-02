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
          <el-option label="MySQL" value="mysql"/>
          <el-option label="PostgreSQL" value="postgresql"/>
          <el-option label="Oracle" value="oracle"/>
          <el-option label="SQLServer" value="sqlserver"/>
        </el-select>
      </el-form-item>

      <el-form-item label="主机地址" prop="host">
        <el-input v-model="formData.host" placeholder="请输入主机地址"/>
      </el-form-item>

      <el-form-item label="端口" prop="port">
        <el-input v-model="formData.port" placeholder="请输入端口号"/>
      </el-form-item>

      <el-form-item label="数据库名称" prop="database">
        <el-input v-model="formData.database" placeholder="请输入数据库名称"/>
      </el-form-item>

      <el-form-item label="用户名" prop="username">
        <el-input v-model="formData.username" placeholder="请输入用户名"/>
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
        <el-tooltip
            content="请先测试连接成功后再保存配置"
            placement="top"
            :disabled="isConnectionTested"
        >
          <el-button
              type="success"
              @click="saveConfig"
              :disabled="!isConnectionTested"
          >确定
          </el-button>
        </el-tooltip>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {reactive, ref, watch} from 'vue'
import dbApi from '@/api/db.js'
import {ElMessage} from 'element-plus'
import {useRouter} from 'vue-router'


const formRef = ref(null)
const isConnectionTested = ref(false)
const formData = reactive({
  dbType: 'MySQL',
  host: 'localhost',
  port: 3306,
  database: 'seal_bridge',
  username: 'root',
  password: '123456'
})

// 添加 watch 来监听表单数据变化
watch(formData, () => {
  isConnectionTested.value = false
}, {deep: true})

const rules = reactive({
  dbType: [
    {required: true, message: '请选择数据库类型', trigger: 'change'}
  ],
  host: [
    {required: true, message: '请输入主机地址', trigger: 'blur'},
    {type: 'string', min: 1, message: '主机地址不能为空', trigger: 'blur'}
  ],
  port: [
    {required: true, message: '请输入端口号', trigger: 'blur'},
    {pattern: /^[0-9]+$/, message: '端口号必须为数字', trigger: 'blur'}
  ],
  database: [
    {required: true, message: '请输入数据库名称', trigger: 'blur'}
  ],
  username: [
    {required: true, message: '请输入用户名', trigger: 'blur'}
  ],
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'}
  ]
})

const router = useRouter()

const testConnection = async () => {
  if (!formRef.value) return
  await formRef.value.validate((valid, fields) => {
    if (valid) {
      dbApi.testConnection("MYSQL", formData).then(resp => {
        console.log(resp)
        ElMessage.success('连接成功！')
        isConnectionTested.value = true
      })
    } else {
      ElMessage.error('请填写完整的配置信息')
    }
  })
}

const saveConfig = async () => {
  if (!formRef.value) return
  await formRef.value.validate(async (valid, fields) => {
    if (valid) {
      // todo
      console.log('save config:', formData)
      router.push('/code-generation')
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
