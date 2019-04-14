<template>
  <div>
    <div v-if="flag">
      <user></user>
      <p class="login" @click="showModal">注销</p>
    </div>
    <p v-else class="login" @click="showModal">登录</p>

    <a-modal :closable="false" title="~~~欢迎~~~" v-model="visible" :footer="null">
      <div v-if="flag_register">
        <a-input placeholder="用户名" v-model="input_val[0]">
          <a-icon slot="prefix" type="user"/>
        </a-input>
        <a-input placeholder="密码" v-model="input_val[1]">
          <a-icon slot="prefix" type="key"/>
        </a-input>
      </div>
      <div v-else>
        <a-input placeholder="用户名" v-model="input_val[0]">
          <a-icon slot="prefix" type="user"/>
        </a-input>
        <a-input placeholder="密码" v-model="input_val[1]">
          <a-icon slot="prefix" type="key"/>
        </a-input>
        <a-input placeholder="确认密码">
          <a-icon slot="prefix" type="key"/>
        </a-input>
        <a-input
          v-for="(i,index) in input_en.slice(2)"
          :key="i"
          :placeholder="input_ch[index+2]"
          v-model="input_val[index+2]"
        >
          <a-icon slot="prefix" type="edit"/>
        </a-input>
      </div>
      <div class="modal_button">
        <a-button @click="changetoregister">注册</a-button>
        <a-button type="primary" @click="handleOk">确定</a-button>
      </div>
    </a-modal>
  </div>
</template>
<script>
import User from "./User";

export default {
  components: {
    User
  },
  name: "Login",
  data() {
    return {
      flag: false,
      flag_register: true,
      visible: false,
      input_en: ["username", "password", "email", "age", "profession", "sex"],
      input_ch: ["用户", "密码", "电子邮件", "年龄", "职业", "性别"],
      input_val: []
    };
  },
  mounted() {
    this.haslogin();
  },
  methods: {
    changetoregister() {
      this.flag_register = !this.flag_register;
      for (let i in this.input_val) this.input_val[i] = "";
    },
    haslogin() {
      if (localStorage.JWT_TOKEN) {
        // 判断是否存在token，如果存在的话，则每个http header都加上token
        this.flag = true;
      } else {
      }
    },
    showModal() {
      this.visible = true;
    },
    handleOk(e) {
      console.log("------");
      if (this.flag_register == true) {
        let tmp = {};
        tmp["username"] = this.input_val[0];
        tmp["password"] = this.input_val[1];

        this.axios.post("/login", tmp).then(response => {
          console.log(response);
          if (response.data.status == true) {
            localStorage.setItem("JWT_TOKEN", response.data.JWT_TOKEN);
            this.visible = false;
            this.flag = true;
            this.$message.success("登录成功");
          } else {
            this.$message.error("登陆失败");
          }
        });
      } else {
        let tmp = {};
        console.log("----------------------------------------------");
        for (let i in this.input_en) {
          tmp[this.input_en[i]] = this.input_val[i];
        }
        console.log(tmp);
        this.axios.post("/register", tmp).then(response => {
          console.log(response);
          this.$message.success("注册成功");
          this.flag_register = true;
        });
      }
    }
  }
};
</script>

<style scoped>
.login {
  cursor: pointer;
}
.ant-input-affix-wrapper {
  margin: 10px;
}
.modal_button .ant-btn {
  margin: 3px;
}
.modal_button {
  text-align: right;
}
</style>

