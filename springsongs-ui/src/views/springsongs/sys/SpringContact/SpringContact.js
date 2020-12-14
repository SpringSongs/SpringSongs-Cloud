import {
  search,
  get,
  save,
  edit,
  batchDelete
} from '@/api/springsongs/sys/SpringContact/SpringContact'
export default {
  data() {
    return {
      tableData: [],
      total: 1,
      multipleSelection: [],
      searchForm: {
        size: 20,
        page: 0,
        total: 0
      },
      dialogAddVisible: false,
      dialogEditVisible: false,
      // 新增界面数据
      addForm: {
        sortCode: 99
      },
      editForm: {},
      addFormRules: {
        company: [{
          required: true,
          message: '请输入公司',
          trigger: 'blur'
        }],
        title: [{
          required: true,
          message: '请输入职称',
          trigger: 'blur'
        }],
        username: [{
          required: true,
          message: '请输入名称',
          trigger: 'blur'
        }],
        email: [{
          required: true,
          message: '请输入邮箱地址',
          trigger: 'blur'
        },
        {
          type: 'email',
          message: '请输入正确的邮箱地址',
          trigger: ['blur', 'change']
        }
        ],
        mobile: [{
          required: true,
          message: '请输入手机',
          trigger: 'blur'
        }],
        sortCode: [{
          required: true,
          message: '请输入排序',
          trigger: 'blur'
        }]
      },
      editFormRules: {
        company: [{
          required: true,
          message: '请输入公司',
          trigger: 'blur'
        }],
        title: [{
          required: true,
          message: '请输入职称',
          trigger: 'blur'
        }],
        username: [{
          required: true,
          message: '请输入名称',
          trigger: 'blur'
        }],
        email: [{
          required: true,
          message: '请输入邮箱地址',
          trigger: 'blur'
        },
        {
          type: 'email',
          message: '请输入正确的邮箱地址',
          trigger: ['blur', 'change']
        }
        ],
        mobile: [{
          required: true,
          message: '请输入手机',
          trigger: 'blur'
        }],
        sortCode: [{
          required: true,
          message: '请输入排序',
          trigger: 'blur'
        }]
      }
    }
  },
  created() {
    this.handleSearch()
  },
  methods: {
    sizeChangeHandle(val) {
      this.searchForm.size = val
      this.searchForm.page = 0
      this.handleSearch()
    },
    handleSelectionChange: function(val) {
      this.multipleSelection = val
    },
    handleCurrentChange: function(val) {
      this.searchForm.page = val
      this.handleSearch()
    },
    // 显示新增界面
    handleAdd: function() {
      this.dialogAddVisible = true
    },
    // 显示编辑界面
    handleEdit: function() {
      const self = this
      if (self.multipleSelection.length === 0) {
        self.$message.warning('请选择修改项目')
      } else if (self.multipleSelection.length > 1) {
        self.$message.warning('只允许选择一项修改项目')
      } else {
        get(self.multipleSelection[0].id).then(res => {
          self.editForm = res.data
          self.dialogEditVisible = true
        })
      }
    },
    // 显示编辑界面
    handleSingleEdit: function(index, row) {
      const self = this
      get(row.id).then(res => {
        self.editForm = res.data
        self.dialogEditVisible = true
      })
    },
    // 关装对话框
    handleClose: function(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done()
        })
        .catch(_ => {})
    },
    // 重置表单
    resetForm: function(formName) {
      this.$refs[formName].resetFields()
    },
    // 查询
    handleSearch: function() {
      const self = this
      search(self.searchForm).then(
        function(response) {
          self.tableData = response.data
          self.searchForm.total = response.count
          self.loading = false
        }
      )
    },
    // 保存
    handleSave: function(formName) {
      const self = this
      self.$refs[formName].validate((valid) => {
        if (valid) {
          save(this.addForm).then((res) => {
            self.$message.success(res.msg)
            self.handleSearch()
            self.dialogAddVisible = false
            self.resetForm(formName)
          })
        } else {
          self.$message.error('请填写必填项')
        }
      })
    },
    // 更新
    handleUpdate: function(formName) {
      const self = this
      edit(self.editForm).then((res) => {
        self.$message.success(res.msg)
        self.handleSearch()
        self.dialogEditVisible = false
      })
    },
    // 删除
    handleDel: function() {
      const self = this
      const ids = []
      if (self.multipleSelection.length === 0) {
        self.$message.warning('请选择删除项目')
        return
      }
      self.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      }).then(() => {
        self.multipleSelection.map((item) => {
          ids.push(item.id)
        })
        batchDelete(ids).then(res => {
          self.$message.success(res.msg)
          self.handleSearch()
        })
      }).catch(() => {})
    },
    handleSingleDelete: function(index, row) {
      const self = this
      const ids = []
      self.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      }).then(() => {
        ids.push(row.id)
        batchDelete(ids).then(res => {
          self.$message.success(res.msg)
          self.handleSearch()
        })
      }).catch(() => {})
    }
  }
}
