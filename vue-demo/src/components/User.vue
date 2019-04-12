<template>
  <div>
    <p class="login" @click="showModal">个人中心</p>
    <a-modal title="Basic Modal" v-model="visible" @ok="handleOk" :footer="null" width="1200px">
      <a-divider orientation="left">个人信息</a-divider>
      <v-for></v-for>
      <a-divider orientation="left">订阅</a-divider>
      <a-divider oritentation="left"></a-divider>
      <a-divider orientation="left">分析</a-divider>
    </a-modal>
  </div>
</template>
<script>
export default {
  name: "User",
  data() {
    return {
      visible: false,
      userName: "",
      password: "",
      fava: []
    };
  },
  methods: {
    showModal() {
      this.visible = true;
    },
    handleOk(e) {
      console.log(e);
      this.visible = false;
    },
    getUser() {},
    getSubscription() {},
    getAnalysis() {},
    addSubscription() {}
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

