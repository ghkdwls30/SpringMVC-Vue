module.exports.createView = function(el, view, initData){
    new Vue({
      el: el, 
      render: h => h(view, {
        props : {
          data : initData
        }
      })
    })
}