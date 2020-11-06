<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="menuId">
      <el-input v-model="dataForm.menuId" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="couponId">
      <el-input v-model="dataForm.couponId" placeholder=""></el-input>
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
          comboId: 0,
          menuId: '',
          couponId: '',
          couponMoney: '',
          totalMoney: ''
        },
        dataRule: {
          menuId: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          couponId: [
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
        this.dataForm.comboId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.comboId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/combo/info/${this.dataForm.comboId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.menuId = data.combo.menuId
                this.dataForm.couponId = data.combo.couponId
                this.dataForm.couponMoney = data.combo.couponMoney
                this.dataForm.totalMoney = data.combo.totalMoney
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
              url: this.$http.adornUrl(`/generator/combo/${!this.dataForm.comboId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'comboId': this.dataForm.comboId || undefined,
                'menuId': this.dataForm.menuId,
                'couponId': this.dataForm.couponId,
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
