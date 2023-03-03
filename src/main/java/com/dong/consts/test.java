package com.dong.consts;

import org.springframework.util.DigestUtils;

/**
 * @author 橙宝cc
 * @date 2023/3/3 - 6:20
 */
public class test {
    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("1234".getBytes()));
    }
}
