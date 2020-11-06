<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="mallName">
      <el-input v-model="dataForm.mallName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="description">
      <el-input v-model="dataForm.description" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="image">
      <el-input v-model="dataForm.image" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="location">
      <el-input v-model="dataForm.location" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="phone">
      <el-input v-model="dataForm.phone" placeholder=""></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          introduceId: 0,
          mallName: '',
          description: '',
          image: '',
          location: '',
          phone: ''
        },
        dataRule: {
          mallName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          image: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          location: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.introduceId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.introduceId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/introduce/info/${this.dataForm.introduceId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.mallName = data.introduce.mallName
                this.dataForm.description = data.introduce.description
                this.dataForm.image = data.introduce.image
                this.dataForm.location = data.introduce.location
                this.dataForm.phone = data.introduce.phone
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/introduce/${!this.dataForm.introduceId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'introduceId': this.dataForm.introduceId || undefined,
                'mallName': this.dataForm.mallName,
                'description': this.dataForm.description,
                'image': this.dataForm.image,
                'location': this.dataForm.location,
                'phone': this.dataForm.phone
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
