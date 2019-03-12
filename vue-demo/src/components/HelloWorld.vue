<template>
  <div class="hello">
    <h1>{{ msg }}</h1>

    <button @click="getMsg">按钮</button>
  </div>
</template>

<script>
export default {
  name: "HelloWord",
  props: {
    msg: String
  },
  methods: {
    getMsg: function() {
      this.$axios
        .get("http://localhost:8081/hello")
        .then(function(response) {
          console.log(response);
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
