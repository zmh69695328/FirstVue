<template>
  <div class="search">
    <a-auto-complete
      :dataSource="title"
      style="width: 200px"
      placeholder="  搜索"
      :filterOption="filterOption"
      @select="show"
    >
      <a-input>
        <a-icon slot="suffix" type="search"/>
      </a-input>
    </a-auto-complete>
  </div>
</template>
<script>
export default {
  name: "search",
  props: ["title"],
  data() {
    return {
      dataSource: [
        "Burns Bay Road",
        "Downing Street",
        "Wall Street",
        "Wall Street"
      ]
    };
  },
  methods: {
    filterOption(input, option) {
      return (
        option.componentOptions.children[0].text
          .toUpperCase()
          .indexOf(input.toUpperCase()) >= 0
      );
    },
    show(value, open) {
      //this.$message.success(value + open);
      this.$emit("show", value);
    }
  }
};
</script>
<style >
.search {
  display: inline;
}
.search .ant-select-selection__placeholder {
  padding-left: 12px;
}
</style>
