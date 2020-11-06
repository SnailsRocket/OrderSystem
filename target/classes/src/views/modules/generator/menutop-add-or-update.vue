<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="menuId">
      <el-input v-model="dataForm.menuId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="couponMoney">
      <el-input v-model="dataForm.couponMoney" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="totalMoney">
      <el-input v-model="dataForm.totalMoney" placeholder=""></el-input>
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
          menuTopId: 0,
          menuId: '',
          couponMoney: '',
          totalMoney: ''
        },
        dataRule: {
          menuId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          couponMoney: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          totalMoney: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.menuTopId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.menuTopId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/menutop/info/${this.dataForm.menuTopId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.menuId = data.menuTop.menuId
                this.dataForm.couponMoney = data.menuTop.couponMoney
                this.dataForm.totalMoney = data.menuTop.totalMoney
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
              url: this.$http.adornUrl(`/generator/menutop/${!this.dataForm.menuTopId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'menuTopId': this.dataForm.menuTopId || undefined,
                'menuId': this.dataForm.menuId,
                'couponMoney': this.dataForm.couponMoney,
                'totalMoney': this.dataForm.totalMoney
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
