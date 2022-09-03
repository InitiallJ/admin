<template>
  <div class="wrapper">
    <div
        style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; color: #4158D0; text-align: center;font-size: 24px"><b>登 录</b></div>
      <el-form :model="user" status-icon :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 8px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 8px 0" prefix-icon="el-icon-lock" show-password
                    v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 8px 0; text-align: center">
          <el-button type="primary" style="margin-right: 15px" size="small" autocomplete="off" @click="login">登录
          </el-button>
          <el-button type="success" size="small" autocomplete="off" @click="$router.push('/register')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

import {setRoutes} from "@/router";

export default {
  name: "Login",
  data() {
    return {
      user: {},
      textTitle:"登录页",
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
      }
    }
  },
  created(){
    document.title = this.textTitle
  },
  methods: {
    login() {
      //提交前进行表单校验，合法才提交表单，不合法不提交
      this.$refs['userForm'].validate((valid) => {
        if (valid) {
          //表单提交
          this.request.post("/user/login", this.user).then(res => {
            if (res.code === "200") {
              localStorage.setItem("user",JSON.stringify(res.data))//浏览器存用户信息
              localStorage.setItem("menus",JSON.stringify(res.data.menus))//浏览器存菜单信息
              //动态设置当前用户的路由
              setRoutes()
              this.$message.success("登录成功！")
              this.$router.push("/")
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      });
    }
  }
}
</script>


<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(43deg, #4158D0 0%, #C850C0 46%, #FFCC70 100%);
  overflow: hidden;
}
</style>