<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="" prop="peopleNum">
      <el-input v-model="dataForm.peopleNum" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="seatName">
      <el-input v-model="dataForm.seatName" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="" prop="seatStatus">
      <el-input v-model="dataForm.seatStatus" placeholder=""></el-input>
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
          seatId: 0,
          peopleNum: '',
          seatName: '',
          seatStatus: ''
        },
        dataRule: {
          peopleNum: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          seatName: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          seatStatus: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.seatId = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.seatId) {
            this.$http({
              url: this.$http.adornUrl(`/generator/seat/info/${this.dataForm.seatId}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.peopleNum = data.seat.peopleNum
                this.dataForm.seatName = data.seat.seatName
                this.dataForm.seatStatus = data.seat.seatStatus
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
              url: this.$http.adornUrl(`/generator/seat/${!this.dataForm.seatId ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'seatId': this.dataForm.seatId || undefined,
                'peopleNum': this.dataForm.peopleNum,
                'seatName': this.dataForm.seatName,
                'seatStatus': this.dataForm.seatStatus
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
