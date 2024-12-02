<template>
  <div class="javabean-config">
    <h2>JavaBean配置</h2>
    <div class="config-content">
      <el-form 
        :model="formData"
        :rules="rules"
        ref="formRef"
        label-width="120px" 
        class="config-form"
      >
        <el-form-item label="输入方式">
          <el-radio-group v-model="inputType" class="input-type-radio">
            <el-radio label="text">直接输入</el-radio>
            <el-radio label="file">文件上传</el-radio>
          </el-radio-group>
        </el-form-item>

        <template v-if="inputType === 'text'">
          <el-form-item label="Java类" prop="javaCode">
            <el-input
              v-model="formData.javaCode"
              type="textarea"
              :rows="10"
              placeholder="请输入Java类代码"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="validateCode">验证代码</el-button>
            <el-button type="success" @click="processCode">处理代码</el-button>
          </el-form-item>
        </template>

        <template v-else>
          <el-form-item prop="file">
            <el-upload
              class="java-uploader"
              action="#"
              :auto-upload="false"
              :on-change="handleFileChange"
              accept=".java"
              :limit="1"
            >
              <template #trigger>
                <el-button type="primary">选择文件</el-button>
              </template>
              <template #tip>
                <div class="el-upload__tip">只能上传 Java 文件</div>
              </template>
            </el-upload>
          </el-form-item>
        </template>
      </el-form>
    </div>
    
    <not-supported-overlay />
  </div>
</template>

<script setup>
import { ref, reactive, watch } from 'vue';
import { ElMessage } from 'element-plus';
import NotSupportedOverlay from './NotSupportedOverlay.vue'

const formRef = ref(null);
const inputType = ref('text');
const formData = reactive({
  javaCode: '',
  file: null
});

const rules = reactive({
  javaCode: [
    {
      required: true,
      message: '请输入Java类代码',
      trigger: 'blur',
      validator: (rule, value, callback) => {
        if (inputType.value === 'text' && !formData.javaCode) {
          callback(new Error('请输入Java类代码'));
        } else {
          callback();
        }
      }
    }
  ],
  file: [
    {
      required: true,
      message: '请上传Java文件',
      trigger: 'change',
      validator: (rule, value, callback) => {
        if (inputType.value === 'file' && !formData.file) {
          callback(new Error('请上传Java文件'));
        } else {
          callback();
        }
      }
    }
  ]
});

// 监听输入类型变化，重置表单
watch(inputType, () => {
  formData.javaCode = '';
  formData.file = null;
  if (formRef.value) {
    formRef.value.clearValidate();
  }
});

const validateCode = async () => {
  if (!formRef.value) return;
  await formRef.value.validate((valid, fields) => {
    if (valid) {
      ElMessage.success('代码格式正确！');
    }
  });
};

const processCode = async () => {
  if (!formRef.value) return;
  await formRef.value.validate((valid, fields) => {
    if (valid) {
      try {
        ElMessage.success('代码处理成功！');
      } catch (error) {
        ElMessage.error('处理失败：' + error.message);
      }
    }
  });
};

const handleFileChange = (file) => {
  formData.file = file;
  const reader = new FileReader();
  reader.onload = (e) => {
    try {
      const content = e.target.result;
      formData.javaCode = content;
      ElMessage.success('文件读取成功！');
    } catch (error) {
      ElMessage.error('文件读取失败：' + error.message);
    }
  };
  reader.readAsText(file.raw);
};
</script>

<style scoped>
.javabean-config {
  position: relative;
  max-width: 800px;
  margin: 20px auto;
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
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.input-type-radio {
  margin-bottom: 20px;
}

.java-uploader {
  text-align: center;
}
</style>