### npm 与 yarn 常用命令对比
检查依赖是否可更新
npm outdated [-g]
yarn [global] outdated
更新依赖包到最新版本
npm update [-g]
yarn [global] upgrade
锁定依赖包版本号（生成 npm-shrinkwrap.json 文件）
npm shrinkwrap
yarn generate-lock-entry && yarn install
固定依赖包版本号（修改 package.json 文件）
npm config set save-prefix="~"
npm config set save-exact true
线上环境更新依赖包（仅安装 dependencies 里面的包）
npm install --production
yarn install --production
安装 package.json 中所有依赖
npm install
yarn install
安装生产依赖（指定版本）
npm install --save lodash@4.17.2
yarn add lodash@4.17.2
安装开发依赖（指定版本）
npm install --save-dev lodash@4.17.2
yarn add lodash@4.17.2 --dev
安装全局依赖（指定版本）
npm install --global lodash@4.17.2
yarn global add lodash@4.17.2
卸载生产依赖
npm uninstall --save lodash
yarn remove lodash
卸载开发依赖
npm uninstall --save-dev lodash
yarn remove lodash --dev
更新生产依赖
npm update --save
yarn upgrade
升级全局依赖到最新版本
npm update lodash -g
yarn global upgrade webpack
查看全局依赖包
npm ls -g

### 创建VUE前台项目
