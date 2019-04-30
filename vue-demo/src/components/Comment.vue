<template>
  <div>
    <a-list
      v-if="comments.length"
      :dataSource="comments"
      :header="`${comments.length} ${comments.length > 1 ? '回复' : '回复'}`"
      itemLayout="horizontal"
    >
      <a-list-item slot="renderItem" slot-scope="item">
        <a-comment
          :author="item.username"
          :avatar="item.avatar"
          :content="item.content"
          :datetime="item.datetime"
        ></a-comment>
      </a-list-item>
    </a-list>
    <a-comment>
      <a-avatar slot="avatar" :src="user.avatar" :alt="user.username"/>
      <div slot="content">
        <a-form-item>
          <a-textarea :rows="4" @change="handleChange" :value="value"></a-textarea>
        </a-form-item>
        <a-form-item>
          <a-button
            htmlType="submit"
            :loading="submitting"
            @click="handleSubmit"
            type="primary"
          >添加评论</a-button>
        </a-form-item>
      </div>
    </a-comment>
  </div>
</template>
<script>
import moment from "moment";
export default {
  name: "Comment",
  props: ["user", "id"],
  data() {
    return {
      // _id: this.id,
      // user: this.user,
      comments: [],
      submitting: false,
      value: "",
      moment
    };
  },
  methods: {
    handleSubmit() {
      if (!this.value) {
        return;
      }
      this.submitting = true;
      setTimeout(() => {
        this.postComment();
        this.submitting = false;
      }, 1000);
    },
    handleChange(e) {
      this.value = e.target.value;
    },
    getComment() {
      this.axios
        .post("/get/comment", { id: this.id })
        .then(response => {
          console.log("id--------------------------------");
          console.log(this.id);
          console.log(response.data);
          this.comments = response.data;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    postComment() {
      this.axios
        .post("/post/comment", {
          username: this.user.username,
          avatar: this.user.avatar,
          content: this.value,
          datetime: moment().format("YYYY-MM-DD HH:mm:ss"),
          newsid: this.id
        })
        .then(response => {
          console.log("--------post-----------");
          this.value = "";
          this.getComment();
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    myclick() {
      this.getComment();
    }
  }
};
</script>