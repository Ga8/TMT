module.exports = {
    transpileDependencies: ["vuetify"],
    devServer: {
        proxy: {
          '/api': {
            target: 'http://localhost:9090',
            changeOrigin: true,
          }
        }
      },
   // Change build paths to make them Maven compatible
   // see https://cli.vuejs.org/config/
   outputDir: 'target/dist',
   assetsDir: 'static'
   };
 