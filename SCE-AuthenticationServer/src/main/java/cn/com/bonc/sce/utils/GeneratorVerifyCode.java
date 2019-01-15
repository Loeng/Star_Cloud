package cn.com.bonc.sce.utils;

import lombok.extern.slf4j.Slf4j;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @Desc:  图形验证码生成器
 * @auther moukunlin
 * @date  2019/1/11
 */

public class GeneratorVerifyCode {

    final private char[] chars = "0123456789".toCharArray();
    private static String[] fontNames = new String[] { "Courier", "Arial", "Verdana", "Georgia", "Times", "Tahoma" };
    private static int[] fontStyle = new int[] { Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC };
    private int width = 140;
    private int height = 60;
    private int charCnt = 4;  // 验证码长度
    private int disturbLineNum = 50; // 产生随机干扰线条数量


    public  Map<String,Object> drawCode(OutputStream os) {
        Map<String,Object> map = new HashMap<>();
        BufferedImage bi = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setColor(new Color(245, 245, 245));
        g.fillRect(0, 0, this.width, this.height);
        drawDisturbLine(g);
        BufferedImage[] bis = new BufferedImage[charCnt];
        char[] codes = generateCode();
        for (int i = 0; i < charCnt; i++) {
            bis[i] = generateBuffImg(codes[i]);
            g.drawImage(bis[i], null, (int) (this.height * 0.6) * i, 0);
        }
        g.dispose();
        map.put("image",bi);
        map.put("code",new String(codes));
        return map;
    }

    private BufferedImage generateBuffImg(char c) {
        String tmp = Character.toString(c);
        Color forecolor = getRandomColor();
        Color backcolor = new Color(255, 255, 255, 0);
        String fontName = getRandomFontName();
        int fontStyle = getRandomStyle();
        int fontSize = getRandomSize();
        int strX = (this.height - fontSize) / 2;
        int strY = (this.height - fontSize) / 2 + fontSize;
        double arch = getRandomArch();

        BufferedImage ret = new BufferedImage(this.height, this.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = ret.createGraphics();
        g.setColor(backcolor);
        g.fillRect(0, 0, this.height, this.height);
        g.setColor(forecolor);
        g.setFont(new Font(fontName, fontStyle, fontSize));
        g.rotate(arch, this.height / 2, this.height / 2);
        g.drawString(tmp, strX, strY);

        g.dispose();
        return ret;
    }

    private double getRandomArch() {
        return ((int) (Math.random() * 1000) % 2 == 0 ? -1 : 1) * Math.random();
    }

    private Color getRandomColor() {
        int r = (int) (Math.random() * 10000) % 200;
        int g = (int) (Math.random() * 10000) % 200;
        int b = (int) (Math.random() * 10000) % 200;
        return new Color(r, g, b);
    }

    private String getRandomFontName() {
        int pos = (int) (Math.random() * 10000) % (fontNames.length);
        return fontNames[pos];
    }

    private int getRandomStyle() {
        int pos = (int) (Math.random() * 10000) % (fontStyle.length);
        return fontStyle[pos];
    }

    private int getRandomSize() {
        int max = (int) (this.height * 0.9);
        int min = (int) (this.height * 0.6);
        return (int) (Math.random() * 10000) % (max - min + 1) + min;
    }

    private char[] generateCode() {
        char[] ret = new char[charCnt];
        for (int i = 0; i < charCnt; i++) {
            int letterPos = (int) (Math.random() * 10000) % (chars.length);
            ret[i] = chars[letterPos];
        }
        return ret;
    }

    /**
     * 产生干扰线条
     * @param graphics
     */
    private void drawDisturbLine(Graphics2D graphics) {
        for (int i = 0; i < disturbLineNum; i++) {
            graphics.setColor(getRandomColor());
            int x = (int) (Math.random() * 10000) % (this.width + 1) + 1;
            int x1 = (int) (Math.random() * 10000) % (this.width + 1) + 1;
            int y = (int) (Math.random() * 10000) % (this.height + 1) + 1;
            int y1 = (int) (Math.random() * 10000) % (this.height + 1) + 1;
            graphics.drawLine(x, y, x1, y1);
        }

    }

}
