const EntityTransform = {
  template: `
        <div class="component-container">
            <h2>关系型数据库配置</h2>
            <el-form :model="formData" label-width="120px">
                <el-form-item label="数据库类型">
                    <el-select v-model="formData.dbType" placeholder="请选择数据库类型">
                        <el-option label="MySQL" value="mysql"></el-option>
                        <el-option label="PostgreSQL" value="postgresql"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="主机地址">
                    <el-input v-model="formData.host" placeholder="请输入主机地址"></el-input>
                </el-form-item>
                <el-form-item label="端口">
                    <el-input v-model="formData.port" placeholder="请输入端口号"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleSubmit">提交</el-button>
                </el-form-item>
            </el-form>
        </div>
    `,
  data() {
    return {
      formData: {
        dbType: '',
        host: '',
        port: ''
      }
    }
  },
  methods: {
    handleSubmit() {
      console.log('提交的数据：', this.formData);
      // 处理提交逻辑
    }
  }
}
