package com.atsun.coreapi.utils;

import com.atsun.coreapi.vo.ManagerVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author SH
 */
public class TokenUtils {

    private static final long serialVersionUID = -3L;
    /**
     * Token 有效时长 多少秒
     **/
    private static final Long EXPIRATION = 2 * 60L;

    public String createToken(ManagerVO managerVO) {
        try {
            // Token 的过期时间
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
            // 生成 Token
            String token = Jwts.builder()
                    .setId(managerVO.getId())
                    // 设置 Token 签发者 可选
                    .setIssuer("sh")
                    // 根据用户名设置 Token 的接受者
                    .setAudience(managerVO.getUsername())
                    // 设置过期时间
                    .setExpiration(expirationDate)
                    // 设置 Token 生成时间 可选
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "itheima")
                    // 通过 claim 方法设置一个 key = role，value = userRole 的值
                    // 设置加密密钥和加密算法，注意要用私钥加密且保证私钥不泄露
                    .compact();
            return token;
        } catch (Exception e) {
            return null;
        }
    }

    public ManagerVO validationToken(String token) {
        try {
            // 解密 Token，获取 Claims 主体
            Claims claims = Jwts.parser().setSigningKey("itheima")
                    .parseClaimsJws(token).getBody();

            ManagerVO managerVO = new ManagerVO();
            // 获得用户信息
            managerVO.setUsername(claims.getAudience());
            managerVO.setId(claims.getId());
            return managerVO;
        } catch (Exception e) {
            return null;
        }
    }
}
