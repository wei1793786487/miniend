package com.hqgml.small.utlis;

import cn.hutool.system.UserInfo;
import com.hqgml.small.pojo.MiniUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtUtils {


    private static final String JWT_KEY_ID = "id";
    private static final String JWT_KEY_PID = "pid";

    private static final String JWT_KEY_OPNE_ID = "openid";

    /**
     * 私钥加密token
     *
     * @param miniUser      载荷中的数据
     * @param privateKey    私钥
     * @param expireMinutes 过期时间，单位秒
     * @return
     * @throws Exception
     */
    public static String generateToken(MiniUser miniUser, PrivateKey privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim(JWT_KEY_ID, miniUser.getId())
                .claim(JWT_KEY_PID, miniUser.getPId())
                .claim(JWT_KEY_OPNE_ID, miniUser.getOpenid())
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 私钥加密token
     *
     * @param miniUser      载荷中的数据
     * @param privateKey    私钥字节数组
     * @param expireMinutes 过期时间，单位秒
     * @return
     * @throws Exception
     */
    public static String generateToken(MiniUser miniUser, byte[] privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim(JWT_KEY_ID, miniUser.getId())
                .claim(JWT_KEY_PID, miniUser.getPId())
                .claim(JWT_KEY_OPNE_ID, miniUser.getOpenid())
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥字节数组
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, byte[] publicKey) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey))
                .parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static MiniUser getInfoFromToken(String token, PublicKey publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();

        return new MiniUser(
                ObjectUtils.toInt(body.get(JWT_KEY_ID)),
                ObjectUtils.toInt(body.get(JWT_KEY_PID)),

                ObjectUtils.toString(body.get(JWT_KEY_OPNE_ID))

        );
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static MiniUser getInfoFromToken(String token, byte[] publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new MiniUser(
                ObjectUtils.toInt(body.get(JWT_KEY_ID)),
                ObjectUtils.toInt(body.get(JWT_KEY_PID)),

                ObjectUtils.toString(body.get(JWT_KEY_OPNE_ID))

        );
    }
}