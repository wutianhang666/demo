package com.icss.etc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.icss.etc.pojo.User;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * jwt token = base64 URL(头部) + "." +base64 URL(负载) + "." + base64 URL(签名)
 * <p>
 * 头部
 * {
 * "alg": "HS256",
 * "typ": "JWT"
 * }
 * alg是签名用的算法，默认为HMAC SHA256（写为HS256），JWT默认也是推荐的算法
 * typ属性表示令牌的类型，JWT令牌统一写为JWT
 * <p>
 *
 * 负载（Payload）
 * JWT的主体内容部分，也是JSON，包含需要传递的数据
 * JWT指定七个默认字段供选择
 * iss：发行人
 * exp：到期时间
 * sub：主题
 * aud：用户
 * nbf：在此之前不可用
 * iat：发布时间
 * jti：JWT ID用于标识该JWT
 * <p>
 *
 * 签名（Signature）
 * 首先，需要指定一个密钥(secretKey)，该密钥只存在服务器中，不能向用户公开公开，使用header中指定的签名算法默认情况下为HMAC SHA256）根据以下公式生成签名。
 * Signature = HS256(Base64(头部) + "." + Base64(负载) ,  secretKey)
 */
public class JWTUtil {

    //测试生成token
    @Test
    public void test() throws UnsupportedEncodingException {
        User user = new User();
        user.setId("123456");
        user.setUserName("张三");
        user.setPassWord("66666");
        String token = createToken(user);
        System.out.println(token);
    }

    //测试获取token
    @Test
    public void get() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzV29yZCI6IjY2NjY2IiwibmFtZSI6IuW8oOS4iSIsImlkIjoiMTIzNDU2IiwiZXhwIjoxNzEzNTA5NDQzLCJpYXQiOjE3MTM1MDk0NDF9.Hgeq8Xu4qEClxz5rJucjIw5udf5x7sjefec-7l0O10k";
        DecodedJWT jwt = verifyToken(token);
        String name = jwt.getClaim("name").asString();
        String password = jwt.getClaim("passWord").asString();
        String id = jwt.getClaim("id").asString();
        System.out.println(name);
        System.out.println(password);
        System.out.println(id);
    }

    //测试token是否有效
    @Test
    public void expired() {
        String token = "eyJ0eX11AiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzV29yZCI6IjY2NjY2IiwibmFtZSI6IuW8oOS4iSIsImlkIjoiMTIzNDU2IiwiZXhwIjoxNzEzNTEwNDA5LCJpYXQiOjE3MTM1MDg2MDl9.GXF8KSPI3yFIe60fh7bSlLgTZrogqOD-a5TCJwPsK40";
        System.out.println(verify(token));
    }

    //token有效时长
    private static final long EXPIRE = 2000L;
    //token的密钥 可自行定义
    private static final String SECRET = "jwt";

    /**
     * 获取token
     *
     * @param
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String createToken(User user) throws UnsupportedEncodingException {
        //token过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE);

        //jwt的header部分
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        //使用jwt的api生成token
        String token = JWT.create()
                .withHeader(map)
                .withClaim("name", user.getUserName())//私有声明
                .withClaim("passWord", user.getPassWord())//私有声明
                .withClaim("id", user.getId())//私有声明
                .withExpiresAt(date)//过期时间
                .withIssuedAt(new Date())//签发时间
                .sign(Algorithm.HMAC256(SECRET));//加密
        return token;
    }

    /**
     * 校验token的有效性
     * 1 token的header和payload是否没改过
     * 2 没有过期
     */
    public static boolean verify(String token) {
        try {
            //解密
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 解密token
     *
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token) {

        DecodedJWT jwt = null;
        try {
            jwt = JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token);
        } catch (JWTDecodeException e) {
            System.out.println("令牌错误");
        } catch (TokenExpiredException e) {
            System.out.println("令牌过期");
        }
        return jwt;
    }
}
