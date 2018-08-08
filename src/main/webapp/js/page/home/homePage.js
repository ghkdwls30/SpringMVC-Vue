import Vue from 'vue'
import HomeView from '../../view/home/HomeView.vue'
import HeaderView from '../../view/common/HeaderView.vue'
import FooterView from '../../view/common/FooterView.vue'
import ViewHepler from '../../common/viewHelper.js'

function initalize(initData){
  // 공통 헤더 생성
  ViewHepler.createView("#header", HeaderView, initData);

  // 공통 하단 생성  
  ViewHepler.createView("#footer", FooterView, initData);

  // 모듈 뷰 생성
  ViewHepler.createView("#content", HomeView, initData);
}

export { initalize }

