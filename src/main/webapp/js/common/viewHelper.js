/***
 * 단일 컴포넌트(뷰) 생성
 */
module.exports.createView = function(el, viewPath, initData){
  var view;
  
  if( typeof viewPath === 'string' ){
	  require.ensure([], function(){
	    view = require("../view" + viewPath + ".vue").default;
	    createVeu(el, view, initData);
	  }); 
  }else if( typeof viewPath === 'object'){
	  view = viewPath;
	  createVeu(el, view, initData);
  }
}

/**
 * 복합 컴포넌트(페이지, 모듈) 생성
 */
module.exports.createPage = function(pagePath, initData){
  var page;
  require.ensure([], function(){
	page = require("../page" + pagePath + ".js");
	page.initalize(initData);
  });  
}


/**
 * 뷰 생성
 */
function createVeu(el, view, initData){
  new Vue({
      el: el, 
      render: h => h(view, {
         props : {
           data : initData
         }
      })
  })
}

