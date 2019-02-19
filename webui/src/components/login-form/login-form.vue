<template>
  <Form ref="loginForm" :model="form" :rules="rules" @keydown.enter.native="handleSubmit" v-if="isLogin">
    <FormItem prop="userName">
      <Input v-model="form.userName" placeholder="请输入用户名">
        <span slot="prepend">
          <Icon :size="16" type="ios-person"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem prop="password">
      <Input type="password" v-model="form.password" placeholder="请输入密码">
        <span slot="prepend">
          <Icon :size="14" type="md-lock"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem>
      <Button @click="handleSubmit" type="primary" long>登录</Button>
    </FormItem>
  </Form>
  <Form ref="loginForm" :model="form" :rules="rules" @keydown.enter.native="handleSubmit" v-else>
    <FormItem prop="userName">
      <Input v-model="form.userName" placeholder="请输入用户名">
      <span slot="prepend">
          <Icon :size="16" type="ios-person"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem prop="password">
      <Input type="password" v-model="form.password" placeholder="请输入密码">
      <span slot="prepend">
          <Icon :size="14" type="md-lock"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem prop="confPass">
      <Input type="password" v-model="form.confPass" placeholder="请再次输入密码">
      <span slot="prepend">
          <Icon :size="14" type="md-lock"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem prop="email">
      <Input type="email" v-model="form.email" placeholder="请输入邮箱">
      <span slot="prepend">
          <Icon :size="14" type="md-lock"></Icon>
        </span>
      </Input>
    </FormItem>
    <FormItem>
      <Button @click="handleSubmit" type="primary" long>注册</Button>
    </FormItem>
  </Form>
</template>
<script>
import { getMD5 } from '../../libs/validate'

export default {
  name: 'LoginForm',
  props: {
    userNameRules: {
      type: Array,
      default: () => {
        return [
          { required: true, message: '账号不能为空', trigger: 'blur' }
        ]
      }
    },
    passwordRules: {
      type: Array,
      default: () => {
        return [
          { required: true, message: '密码不能为空', trigger: 'blur' }
        ]
      }
    },
    isLogin: {
      type: Boolean,
      default: () => {
        return true
      }
    }
  },
  data () {
    return {
      form: {
        userName: '',
        password: '',
        confPass: '',
        email: ''
      }
    }
  },
  computed: {
    rules () {
      const validateCP = (rule, value, callback) => {
        if (value !== this.form.password) {
          return callback(new Error('两次密码输入不一致！'))
        } else {
          callback()
        }
      }
      return {
        userName: this.userNameRules,
        password: this.passwordRules,
        confPass: [
          this.passwordRules[0],
          { validator: validateCP, trigger: 'blur' }
        ]
      }
    }
  },
  watch: {
    isLogin (val, oldVal) {
      if (!val) {
        this.form.userName = ''
        this.form.password = ''
      }
    }
  },
  methods: {
    handleSubmit () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          let data = this.isLogin ? {
            userName: this.form.userName,
            password: getMD5(this.form.password)
          } : {
            userName: this.form.userName,
            password: getMD5(this.form.password),
            confPass: getMD5(this.form.password),
            email: this.form.email
          }
          this.$emit('on-success-valid', data)
        }
      })
    }
  }
}
</script>
