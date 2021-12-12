var store = {
    data: {
      type: {
        isLogin: false,
        isModule: false,
        isRed: false,
      },
      userInfo: {}
    },
    get state () {
      return this.data
    },
    set state (val) {
      window.localStorage.setItem('store', JSON.stringify(this.data))
    },
    amendType (obj) {
      let state = this.data
      switch (obj.type) {
        case 'isLogin':
          state.type.isLogin = obj.blo
          break
        case 'isModule':
          state.type.isModule = obj.blo
          break
        case 'isRed':
            state.type.isRed = obj.blo
            break
        default:
          break
      }
      this.state = state
    },
    setUserInfo (obj) {
      if (obj.user_pic) {
        let http = new RegExp('http').test(obj.user_pic)
        // if(!wx) {
        if (!http) {
           obj.user_pic = window.location.origin + obj.user_pic
          // }
        }
      }
      let state = this.data
      state.userInfo = obj
      this.state = state
    },
    synchronousData (obj) {
      this.data = obj
    }
  }
  
  if (window.localStorage.getItem('store')) {
    store.synchronousData(JSON.parse(window.localStorage.getItem('store')))
  }
  export default store
