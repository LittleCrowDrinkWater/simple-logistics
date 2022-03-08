package com.bolin.logistics.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {
    //加密算法
    private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    //私钥 / 生成签名的时候使用的秘钥secret，一般可以从本地配置文件中读取，切记这个秘钥不能外露，只在服务端使用，在任何场景都不应该流露出去。
    // 一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
    private final static String SECRET = "secretKey";

    // 过期时间
    private final static Long ACCESS_TOKEN_EXPIRATION = 28800L;

    //jwt签发者
    private final static String JWT_ISS = "admin";


    /**
     * 创建jwt
     *
     * @return 返回生成的jwt token
     */
    public static String generateJwtToken(long id, String password) {

        // 头部 map / Jwt的头部承载，第一部分
        // 可不设置 默认格式是{"alg":"HS256"}
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");


        //载荷 map / Jwt的载荷，第二部分
        Map<String, Object> claims = new HashMap<String, Object>();

        //私有声明 / 自定义数据，根据业务需要添加
        claims.put("id", id);
        claims.put("password", password);

        //标准中注册的声明 (建议但不强制使用)
        //一旦写标准声明赋值之后，就会覆盖了那些标准的声明
        claims.put("iss", JWT_ISS);
            /*	iss: jwt签发者
                sub: jwt所面向的用户
                aud: 接收jwt的一方
                exp: jwt的过期时间，这个过期时间必须要大于签发时间
                nbf: 定义在什么时间之前，该jwt都是不可用的.
                iat: jwt的签发时间
                jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
            */
        //下面就是在为payload添加各种标准声明和私有声明了
        return Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setHeader(map)         // 头部信息
                .setClaims(claims)      // 载荷信息
                .setId(UUID.randomUUID().toString()) // 设置jti(JWT ID)：是JWT的唯一标识，从而回避重放攻击。
                .setIssuedAt(new Date())       // 设置iat: jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION * 1000)) // 设置exp：jwt过期时间
                .signWith(SIGNATURE_ALGORITHM, SECRET)//设置签名：通过签名算法和秘钥生成签名
                .compact(); // 开始压缩为xxxxx.yyyyy.zzzzz 格式的jwt token
    }

    private static Claims getClaimsFromJwt(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    public static boolean checkJwt(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            return false;
        }
        if (claims == null)
            return false;
        long exp = 0;
        long id = 0;
        String password = "";
        exp = Long.parseLong(String.valueOf(claims.get("exp")));
        id = Long.parseLong(String.valueOf(claims.get("id")));
        password = (String) claims.get("password");
        boolean empty = StringUtils.isEmpty(password);
        return System.currentTimeMillis() / 1000 < exp && id > 0 && !empty;
    }

    public static long getId(String jwt) {
        Claims claims = getClaimsFromJwt(jwt);
        return Long.parseLong(String.valueOf(claims.get("id")));
    }

    public static String getPassword(String jwt) {
        Claims claims = getClaimsFromJwt(jwt);
        return (String) claims.get("password");
    }

    public static void main(String[] args) {
        String jwtToken = generateJwtToken(1, "123");
        System.out.println("JWT Token " + jwtToken);
        System.out.println("=======================================================");

        Claims claims = getClaimsFromJwt(jwtToken);
        System.out.println(claims);
        System.out.println(claims.get("id"));
        System.out.println(claims.get("password"));
        System.out.println(JwtUtil.checkJwt(jwtToken));
    }
}
