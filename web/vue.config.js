module.export = {
    devServer: {
      proxy: { //配置自动启动浏览器
              "/apis": {
                  target: "http://38617112yi.zicp.vip",
                  changeOrigin: true,
                  // ws: true,//websocket支持
                  //secure: false,
                  pathRewrite: {
                      '^apis': '/'    //代理的路径
                  },
                //   onProxyRes(proxyRes, req, res) {
                //       if(proxyRes.headers['set-cookie']) {
                //           proxyRes.headers['set-cookie'] = proxyRes.headers['set-cookie'].map(v => {
                //              // /coss/app是后端服务设置的上下文跟， 由于是本地所以需要添加一个代理/api（于proxy端口的代理是一样的）
                //               return v.replace('/coss/app', '/api/coss/app')
                //           })
                //       }
                //   }
              },
          }
    }
  }