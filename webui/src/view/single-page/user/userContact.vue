<template>
  <Card dis-hover>
    <Row :gutter="16">
      <Col span="10">
        <div class="avator">
          <div class="img_box">
            <img :src="user.avator" alt="用户头像">
          </div>
          <Button type="info" @click="showModel('avator')">修改头像</Button>
        </div>
      </Col>
      <Col span="14">
        <div class="user">
          <Button type="info" class="btn" @click="showModel('info')">修改信息</Button>
          <ul class="user_list">
            <li>
              <span>姓名：</span>
              <p>{{user.name}}</p>
            </li>
            <li>
              <span>邮箱：</span>
              <p>{{user.email}}</p>
            </li>
            <li>
              <span>电话：</span>
              <p>{{user.phone}}</p>
            </li>
            <li>
              <span>备注：</span>
              <p>{{user.remakes}}</p>
            </li>
          </ul>
        </div>
      </Col>
    </Row>
    <Modal
      v-model="modleAvator"
      @on-ok="saveAvator"
      @on-cancel="cancel"
      title="修改用户头像">
      <!--<Cropper-->
        <!--:src="forms.avator"-->
        <!--crop-button-text="确认提交"-->
        <!--@on-crop="saveAvator"-->
      <!--&gt;</Cropper>-->
      <Upload
        :before-upload="handleUpload"
        action="//jsonplaceholder.typicode.com/posts/">
        <Button icon="ios-cloud-upload-outline">Select the file to upload</Button>
      </Upload>
      <div v-if="file !== null">Upload file: {{ file.name }}</div>
    </Modal>
    <Modal
      v-model="modleInfo"
      title="修改用户信息"
      @on-ok="saveInfo"
      @on-cancel="cancel">
        <Form ref="forms" :model="forms" :rules="ruleValidate" :label-width="80">
          <FormItem label="Name" prop="name">
            <Input v-model="forms.name" placeholder="Enter your Name"></Input>
          </FormItem>
          <FormItem label="E-mail" prop="email">
            <Input v-model="forms.email" placeholder="Enter your E-mail"></Input>
          </FormItem>
          <FormItem label="Phone" prop="phone">
            <Input v-model="forms.phone" placeholder="Enter your Phone"></Input>
          </FormItem>
          <FormItem label="Remakes" prop="phone">
            <Input type="textarea" :rows="4" v-model="forms.remakes"  placeholder="Enter your Remakes"></Input>
          </FormItem>
        </Form>
    </Modal>
  </Card>
</template>

<script>
  import { getUserInfo, updataUser, updataImage } from "../../../api/user";
  import Cropper from '@/components/cropper'

  export default {
    name: "userContact",
    components: {
      Cropper
    },
    data() {
      return {
        user: {
          name: '',
          email: '',
          phone: '',
          remarks: '',
          avator: ''
        },
        forms: {
          name: '',
          email: '',
          phone: '',
          remarks: '',
          avator: ''
        },
        file: null,
        modleAvator: false,
        modleInfo: false,
        ruleValidate: {}
      }
    },
    mounted() {
      this.init()
    },
    methods: {
      init() {
        // get user
        getUserInfo(this.$store.state.user.userName).then(res => {
          this.user = res.data.data.name ? res.data.data : {}
        }).catch(res => {
          this.$Notice.error({
            title: '获取用户信息失败！！'
          })
        })
      },
      showModel(type) {
        this.forms = JSON.parse(JSON.stringify(this.user))

        switch (type) {
          case 'info':
            this.modleInfo = true
            break
          case 'avator':
            this.modleAvator = true
            break
        }
      },
      handleUpload(file) {
        this.file = file;
        return false;
      },
      saveAvator(file) {
        const formData = new FormData()
        formData.append('uid', this.user.uid)
        formData.append('imageFiles', this.file)
        updataImage(formData).then(res => {
          console.log(res)
          if(res.data.status) {
            this.$Notice.success({
              title: '修改成功！'
            })
            this.modleInfo = false
            this.init()
          } else {
            this.$Notice.error({
              title: '修改失败！'
            })
          }
        }).catch(err => {
          this.$Notice.error({
            title: '修改失败！'
          })
        })
      },
      saveInfo() {
        this.$refs['forms'].validate((valid) => {
          if (valid) {
            let params = {
              uid: this.forms.uid,
              name: this.forms.name,
              email: this.forms.email,
              phone: this.forms.phone,
              remakes: this.forms.remakes
            }
            updataUser(params).then(res => {
              if (res.data.status) {
                this.$Notice.success({
                  title: '修改成功！'
                })
                this.modleInfo = false
                this.init()
              } else {
                this.$Notice.error({
                  title: '修改失败！'
                })
              }
            }).catch(err => {
              this.$Notice.error({
                title: '修改失败！'
              })
            })
          }
        })
      },
      cancel() {
        this.$refs['forms'].resetFields();
        this.modleInfo = false
      }
    }
  }
</script>

<style lang="less" scoped>
  .avator{
    width: 150px;
    margin: 0px auto;
    text-align: center;

    .img_box{
      width: 150px;
      height: 150px;
      background-color: #ccc;
      border-radius: 5px;
      margin-bottom: 15px;

      img{
        vertical-align: center;
      }
    }
  }
  .user{
    position: relative;

    .btn{
      position: absolute;
      top: 10px;
      right: 50px;
    }

    .user_list{

      li{
        overflow: hidden;
        line-height: 22px;
        margin-bottom: 10px;
        list-style: none;

        span{
          float: left;
        }
        p{
          line-height: 22px;
          margin-left: 44px;
          word-wrap:break-word
        }
        textarea{
          min-width: 300px;
          min-height: 100px;
          resize: none;
        }
      }
    }
  }
</style>
