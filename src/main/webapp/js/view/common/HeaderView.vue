<template>
   <div id="app">
    <h3>헤더영역 입니다.</h3>
  </div>
</template>

<script>
export default {
  name: 'hedaer',
  props : ['data'],
  beforeCreate(){
    //console.log(this.data.msg);
  },
  created(){
    
  }
}
</script>

<style lang="scss">
</style>
