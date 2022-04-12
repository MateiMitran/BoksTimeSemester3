const { createProxyMiddleware } = require('http-proxy-middleware');
    
module.exports = function(app) {

    app.use(
    '/api/login', //this is your api
    createProxyMiddleware({
      target:'http://localhost:8080/api/login', //this is your whole endpoint link
      changeOrigin: true,
    })
  );
  
};