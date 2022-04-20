package com.travel.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.UUID;

@Service
public class VerificationImgeService {
    private int imageWidth = 70;
    private int imageHigh = 26;

    @Autowired
    private RedisService redisService;

    public void createImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage image = new BufferedImage(imageWidth, imageHigh, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        createBackground(g);
        String captchaString = createCharacter(g);
        String uuid = UUID.randomUUID().toString();
       // request.getSession().setAttribute("yzm_uuid",uuid);
        //将sessionId通过响应头传回客户端
        response.setHeader("Access-Control-Expose-Headers", "access_token");
        response.setHeader("access_token",uuid);
        //  redis缓存验证码
        redisService.setVal("mz:" + uuid, captchaString, 12000L);
        g.dispose();
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        out.close();
    }

    private void createBackground(Graphics g) {
        // 填充背景
        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, imageWidth, imageHigh);
        // 加入干扰线条
        for (int i = 0; i < 8; i++) {
            g.setColor(getRandColor(40, 150));
            Random random = new Random();
            int x = random.nextInt(imageWidth);
            int y = random.nextInt(imageHigh);
            int x1 = random.nextInt(imageWidth);
            int y1 = random.nextInt(imageHigh);
            g.drawLine(x, y, x1, y1);
        }
    }
//颜色
    private Color getRandColor(int fc, int bc) {
        int f = fc;
        int b = bc;
        Random random = new Random();
        if (f > 255) {
            f = 255;
        }
        if (b > 255) {
            b = 255;
        }
        return new Color(f + random.nextInt(b - f), f + random.nextInt(b - f), f + random.nextInt(b - f));
    }
    
    //获取随机字符
    private String createCharacter(Graphics g) {
        char[] codeSeq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
        String[] fontTypes = {"\u5b8b\u4f53", "\u65b0\u5b8b\u4f53", "\u9ed1\u4f53", "\u6977\u4f53", "\u96b6\u4e66"};
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);//random.nextInt(10));
            g.setColor(new Color(50 + random.nextInt(100), 50 + random.nextInt(100), 50 + random.nextInt(100)));
            g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)], Font.BOLD, 26));
            g.drawString(r, 15 * i + 5, 19 + random.nextInt(8));
            s.append(r);
        }
        return s.toString();
    }

    /**
     * 验证传入的验证码与缓存中的验证码是否一致
     * @param code
     * @return
     */
    public boolean verificationCode(HttpServletRequest request, String code,String token) {

        String key ="mz:" + token;
        String originCaptcha = redisService.getVal(key);
        if (originCaptcha != null) {
            if (code.equals(originCaptcha) == true) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
