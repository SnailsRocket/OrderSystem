<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="name">
      <el-input v-model="dataForm.name" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="money">
      <el-input v-model="dataForm.money" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="image">
      <el-input v-model="dataForm.image" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="sales">
      <el-input v-model="dataForm.sales" placeholder=""></el-input>
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
          menuId: 0,
          name: '',
          money: '',
          image: '',
          sales: ''
        },
        dataRule: {
          name: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          money: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          image: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          sales: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.menuId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.menuId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/menu/info/${this.dataForm.menuId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.menu.name
                this.dataForm.money = data.menu.money
                this.dataForm.image = data.menu.image
                this.dataForm.sales = data.menu.sales
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
              url: this.$http.adornUrl(`/generator/menu/${!this.dataForm.menuId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'menuId': this.dataForm.menuId || undefined,
                'name': this.dataForm.name,
                'money': this.dataForm.money,
                'image': this.dataForm.image,
                'sales': this.dataForm.sales
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
