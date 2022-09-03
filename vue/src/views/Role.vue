<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 260px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <!--<el-input class="ml-5" style="width: 260px" placeholder="请输入邮箱" suffix-icon="el-icon-message" v-model="email"></el-input>-->
      <!--<el-input style="width: 260px" placeholder="请输入地址" suffix-icon="el-icon-position" v-model="address"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除所选项吗？"
          @confirm="delBatch">
        <el-button type="danger" class="ml-5" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <!--<el-upload action="http://localhost:8081/user/import" :show-file-list="false" accept="xlsx"-->
      <!--           :on-success="handleExcelImportSuccess" style="display: inline-block">-->
      <!--  <el-button type="primary">导入<i class="el-icon-bottom"></i></el-button>-->
      <!--</el-upload>-->
      <!--  <el-button type="primary" style="margin-left: 10px" @click="exp">导出<i class="el-icon-top"></i></el-button>-->
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection"></el-table-column>
      <el-table-column prop="id" label="ID" width="80"></el-table-column>
      <el-table-column prop="name" label="名称"></el-table-column>
      <el-table-column prop="flag" label="唯一标识"></el-table-column>
      <el-table-column prop="description" label="描述"></el-table-column>
      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <el-button type="info" @click="selectMenu(scope.row)">分配菜单 <i class="el-icon-menu"></i></el-button>
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.id)">
            <el-button type="danger" class="ml-5" slot="reference">删除 <i class="el-icon-warning-outline"></i>
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div class="block" style="padding: 10px 0;text-align: center">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[5, 10, 20, 30]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="80px" size="small">
        <el-form-item label="名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="唯一标识">
          <el-input v-model="form.flag" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="菜单分配" :visible.sync="dialogMenuVisible" width="25%">
      <el-tree
          :data="menuData"
          :props="props"
          node-key="id"
          ref="tree"
          show-checkbox
          :default-expanded-keys="expands"
          :default-checked-keys="checks">
        <span class="custom-tree-node" slot-scope="{ node, data }">
        <span><i :class="data.icon"/>{{ data.name }}</span>
        </span>
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogMenuVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Role",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      description: "",
      form: {},
      multipleSelection: [],
      dialogFormVisible: false,
      dialogMenuVisible: false,
      headerBg: 'headerBg',
      menuData: [],
      expands: [],
      checks: [],
      props: {
        label: 'name'
      },
      roleId: 0,
      roleFlag: ''
    }
  },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  //[{},{},{}]==>[1,2,3]
      this.request.post("/role/del/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功！")
          this.load()
        } else {
          this.$message.error("批量删除失败！")
        }
      })
    },
    del(id) {
      this.request.delete("/role/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功！")
          this.load()
        } else {
          this.$message.error("删除失败！")
        }
      })
    },
    handleEdit(row) {
      this.form = row
      this.dialogFormVisible = true
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    save() {
      this.request.post("/role", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功！")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败！")
        }
      })
    },
    saveRoleMenu() {
      this.request.post("role/roleMenu/" + this.roleId, this.$refs.tree.getCheckedKeys()).then(res => {
        if (res.code === '200') {
          this.$message.success("绑定成功！")
          this.dialogMenuVisible = false
          if (this.roleFlag === 'admin') {
            //操作管理员角色后需要重新等录
            this.$store.commit("logout")
          }
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    load() {
      this.request.get("/role/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      })
          .then(res => {
            this.tableData = res.data.records
            this.total = res.data.total
          })
    },
    reset() {
      this.name = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    selectMenu(role) {
      this.roleId = role.id
      this.roleFlag = role.flag
      //请求菜单数据
      this.request.get("/menu").then(res => {
        this.menuData = res.data
        //把后台返回的菜单数据处理成id数组
        this.expands = this.menuData.map(v => v.id)
      })
      this.request.get("/role/roleMenu/" + this.roleId).then(res => {
        this.checks = res.data

        this.request.get("/menu/ids").then(r => {
          //先渲染弹窗的内容
          this.dialogMenuVisible = true
          const ids = r.data
          ids.forEach(id => {
            if (!this.checks.includes(id)) {
              this.$refs.tree.setChecked(id, false)
            }
          })
        })
      })
    },
  }
}
</script>

<style>

</style>