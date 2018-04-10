# 框架开发中 欢迎PR
eureka:1000
config:1001
gateway:1002
oauth:1003
base:1004

# 分支说明
- master: Spring Cloud Edgware.SR3 / Spring Boot 1.5.10.RELEASE
- Finchley: Spring Cloud Finchley.M9 / Spring Boot 2.0.0.RELEASE

# 开发注意
- 若配置文件出现乱码 请修改编译器文件编码格式为UTF-8
- 使用Lombok简化开发 开发时记得安装Lombok插件避免编译器报错
- 配置文件加密 Jasyp加密 可到xcloud-common服务中找到JasypUtil工具类生成加密结果 使用到的模块配置文件中记得加上jasypt.encryptor.password