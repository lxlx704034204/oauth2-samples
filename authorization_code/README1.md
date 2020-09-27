 
#原文： http://www.javaboy.org/2020/0414/oauth2_authorization_code.html
# 补充：
    OAuth2简易实战（一）-四种模式           https://www.cnblogs.com/sky-chen/archive/2019/03/13/10523882.html
    SpringSecurityOAuth2配置认证服务器策略  https://blog.csdn.net/qq_41853447/article/details/106598361
    spring-oauth-server 数据库表说明       https://www.andaily.com/spring-oauth-server/db_table_description.html



# 授权码模式 页面流程解析：
    //【授权码】是用来获取令牌的(使用一次就失效)；【令牌】则是用来获取资源的
    4. 选择Approve(后台方法：5.配置 【客户端】 的详细信息)，点击Authorize，会调用回调地址 返回code（授权码）； 拿着这个授权码，我们就可以去请求 access_token (获取到token令牌)，授权码使用一次就会失效。
    5. 在获得授权码后，接下去获取访问令牌，访问

#客户端id(javaboy5) 关联点 :
    [授权服务器]AuthorizationServer.java :  clients.inMemory().withClient("javaboy5")
    [资源服务器]ResourceServerConfig.java :  services.setClientId("javaboy5");
    [第三方服务器]HelloController.java :  map.add("client_id", "javaboy5");
    [第三方服务器]index.html :            "http://localhost:8080/oauth/authorize?client_id=javaboy5&......
    


### 跟着松哥学 Spring Cloud Security OAuth2
