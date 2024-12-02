<template>
  <div class="json-config">
    <h2>JSON配置</h2>
    <div class="config-content">
      <el-form 
        :model="formData"
        :rules="rules"
        ref="formRef"
        label-width="120px" 
        class="config-form"
      >
        <el-form-item label="JSON输入">
          <el-radio-group v-model="inputType" class="input-type-radio">
            <el-radio label="text">直接输入</el-radio>
            <el-radio label="file">文件上传</el-radio>
          </el-radio-group>
        </el-form-item>

        <template v-if="inputType === 'text'">
          <el-form-item prop="jsonText">
            <el-input
              v-model="formData.jsonText"
              type="textarea"
              :rows="10"
              placeholder="请输入JSON数据"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="validateJson">验证JSON</el-button>
            <el-button type="success" @click="processJson">处理数据</el-button>
          </el-form-item>
        </template>

        <template v-else>
          <el-form-item prop="file">
            <el-upload
              class="json-uploader"
              action="#"
              :auto-upload="false"
              :on-change="handleFileChange"
              accept=".json"
              :limit="1"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>
              <template #tip>
                <div class="el-upload__tip">只能上传 JSON 文件</div>
              </template>
            </el-upload>
          </el-form-item>
        </template>
      </el-form>
    </div>
    
    <!-- 遮罩层 -->
    <not-supported-overlay />
  </div>
</template>

<script setup>
import { ref, reactive, watch } from 'vue'
import { ElMessage } from 'element-plus'
import NotSupportedOverlay from './NotSupportedOverlay.vue'

const formRef = ref(null)
const inputType = ref('text')
const formData = reactive({
  jsonText: '',
  file: null
})

const rules = reactive({
  jsonText: [
    {
      required: true,
      message: '请输入JSON数据',
      trigger: 'blur',
      validator: (rule, value, callback) => {
        if (inputType.value === 'text' && !formData.jsonText) {
          callback(new Error('请输入JSON数据'))
        } else {
          callback()
        }
      }
    }
  ],
  file: [
    {
      required: true,
      message: '请上传JSON文件',
      trigger: 'change',
      validator: (rule, value, callback) => {
        if (inputType.value === 'file' && !formData.file) {
          callback(new Error('请上传JSON文件'))
        } else {
          callback()
        }
      }
    }
  ]
})

// 监听输入类型变化，重置表单
watch(inputType, () => {
  formData.jsonText = ''
  formData.file = null
  if (formRef.value) {
    formRef.value.clearValidate()
  }
})

const validateJson = async () => {
  if (!formRef.value) return
  await formRef.value.validate((valid, fields) => {
    if (valid) {
      try {
        JSON.parse(formData.jsonText)
        ElMessage.success('JSON格式正确！')
      } catch (error) {
        ElMessage.error('JSON格式错误：' + error.message)
      }
    }
  })
}

const processJson = async () => {
  if (!formRef.value) return
  await formRef.value.validate((valid, fields) => {
    if (valid) {
      try {
        const data = JSON.parse(formData.jsonText)
        ElMessage.success('数据处理成功！')
      } catch (error) {
        ElMessage.error('处理失败：' + error.message)
      }
    }
  })
}

const handleFileChange = (file) => {
  formData.file = file
  const reader = new FileReader()
  reader.onload = (e) => {
    try {
      const content = e.target.result
      formData.jsonText = content
      JSON.parse(content) 
      ElMessage.success('文件读取成功！')
    } catch (error) {
      ElMessage.error('文件格式错误：' + error.message)
    }
  }
  reader.readAsText(file.raw)
}
</script>

<style scoped>
.json-config {
  position: relative;
  padding: 20px;
}

.config-content {
  min-height: 400px;
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.config-form {
  max-width: 800px;
  margin: 0 auto;
}

.input-type-radio {
  margin-bottom: 20px;
}

.json-uploader {
  text-align: center;
}
</style>
