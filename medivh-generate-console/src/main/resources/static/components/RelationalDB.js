const RelationalDB = {
  template: `
    <div class="component-container">
      <h2>关系型数据库配置</h2>
      <el-form 
        :model="formData" 
        :rules="rules"
        ref="dbForm"
        label-width="120px"
        class="db-form"
      >
        <el-form-item label="数据库类型" prop="dbType">
          <el-select v-model="formData.dbType" placeholder="请选择数据库类型" style="width: 100%">
            <el-option label="MySQL" value="mysql"></el-option>
            <el-option label="PostgreSQL" value="postgresql"></el-option>
          </el-select>
        </el-form-item>
        
        <el-form-item label="主机地址" prop="host">
          <el-input v-model="formData.host" placeholder="请输入主机地址"></el-input>
        </el-form-item>
        
        <el-form-item label="端口" prop="port">
          <el-input v-model="formData.port" placeholder="请输入端口号"></el-input>
        </el-form-item>
        
        <el-form-item label="数据库名称" prop="dbName">
          <el-input v-model="formData.dbName" placeholder="请输入数据库名称"></el-input>
        </el-form-item>
        
        <el-form-item label="用户名" prop="username">
          <el-input v-model="formData.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        
        <el-form-item label="密码" prop="password">
          <el-input v-model="formData.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('dbForm')">提交</el-button>
          <el-button @click="resetForm('dbForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  `,
  data() {
    return {
      formData: {
        dbType: '',
        host: '',
        port: '',
        dbName: '',
        username: '',
        password: ''
      },
      rules: {
        dbType: [
          { required: true, message: '请选择数据库类型', trigger: 'change' }
        ],
        host: [
          { required: true, message: '请输入主机地址', trigger: 'blur' },
          { pattern: /^[^\s]+$/, message: '主机地址不能包含空格', trigger: 'blur' }
        ],
        port: [
          { required: true, message: '请输入端口号', trigger: 'blur' },
          { pattern: /^\d+$/, message: '端口号必须为数字', trigger: 'blur' },
          { validator: this.validatePort, trigger: 'blur' }
        ],
        dbName: [
          { required: true, message: '请输入数据库名称', trigger: 'blur' }
        ],
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, message: '密码长度不能小于6个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    validatePort(rule, value, callback) {
      if (value && (value < 0 || value > 65535)) {
        callback(new Error('端口号必须在0-65535之间'));
      } else {
        callback();
      }
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log('提交的数据：', this.formData);
          // TODO: 调用API保存数据
        } else {
          console.log('表单验证失败');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
