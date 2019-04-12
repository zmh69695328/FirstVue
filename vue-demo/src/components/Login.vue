<template>
  <div>
    <div v-if="flag">
      <user></user>
      <p class="login" @click="showModal">注销</p>
    </div>
    <p v-else class="login" @click="showModal">登录</p>

    <a-modal
      :closable="false"
      title="~~~欢迎~~~"
      v-model="visible"
      @ok="handleOk"
      okText="登录"
      cancelText="注册"
      @cancel="changetoregister($event)"
    >
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
          v-for="(i,index) in input_en.slice(3)"
          :key="i"
          :placeholder="input_ch[index+3]"
          v-model="input_val[index+3]"
        >
          <a-icon slot="prefix" type="edit"/>
        </a-input>
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
    changetoregister(event) {
      console.log(
        "-----------------------------------------------------------------"
      );
      //event.defaultPrevented();

      console.log(event);
      this.flag_register = false;
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
      console.log(this.input_val);
      let tmp = {};
      tmp["username"] = this.input_val[0];
      tmp["password"] = this.input_val[1];

      this.axios.post("/login", tmp).then(response => {
        if (response.data.ok == true) {
          this.visible = false;
        } else {
          this.$message.error("登陆失败");
        }
      });
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
</style>

