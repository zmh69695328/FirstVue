<template>
  <a-layout-header style="background: #fff; padding: 0" class="header">
    <a-icon
      class="trigger"
      :type="collapsed ? 'menu-unfold' : 'menu-fold'"
      @click="()=> collapsed = !collapsed"
    />
    <a-icon type="sync" style="font-size:20px;" @click="f" :spin="flag"/>
    <a-avatar :size="62" src="https://s.gravatar.com/avatar/7b3885009be8e0f49e1a8d4f145c5563?s=80"></a-avatar>
  </a-layout-header>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      collapsed: false
    };
  }
};
</script>

<style scoped>
#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}

.ant-modal-header .ant-modal-title {
  font-size: 25px;
  text-align: center;
}
.ant-modal-body .news-modal div p div img {
  height: auto;
  width: 100%;
  vertical-align: bottom;
}
</style>