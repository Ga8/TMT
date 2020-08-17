module.exports = {
    transpileDependencies: ["vuetify"],
    devServer: {
        proxy: {
          '/api': {
            target: 'http://192.168.0.10:9090',
            changeOrigin: true,
            pathRewrite: {
              '^/api': ''
            },
          }
        }
      }
,
// Change build paths to make them Maven compatible
// see https://cli.vuejs.org/config/
outputDir: 'target/dist',
assetsDir: 'static'
};