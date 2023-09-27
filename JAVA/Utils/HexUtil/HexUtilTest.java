package com.example.xqtest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.example.xqtest.bouncycastle.HexUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author: hepan
 * @date: 2023/6/8
 * @desc: 测试类
 */
public class HexUtilTest {

    /**
     * 编码测试
     */
    @Test
    public void encode_test() {
        byte[] b = new byte[]{10, 16, 0, (byte) 255};
        String res = HexUtil.encodeHex(b);
        assertEquals(res.length(), b.length * 2);
        assertEquals(res, "0A1000FF");
    }

    /**
     * 解码测试
     */
    @Test
    public void decode_test() {
        byte[] b = new byte[]{10, 16, 0, (byte) 255};
        byte[] res = HexUtil.decodeHex("0A1000FF");
        final int l = res.length;
        assertEquals(l, b.length);
        for (int i = 0; i < l; i++) {
            assertEquals(b[i], res[i]);
        }
    }

    /**
     * 大小写解码后应该一致
     */
    @Test
    public void decode_case_test() {
        byte[] res = HexUtil.decodeHex("0A1000FF");
        byte[] res2 = HexUtil.decodeHex("0a1000ff");
        final int l = res.length;
        assertEquals(l, res2.length);
        for (int i = 0; i < l; i++) {
            assertEquals(res2[i], res[i]);
        }
    }

    /**
     * 有时需要 Hex 编码后返回 byte, 本工具返回的字符串, 调用者直接转 byte 即可, 编码选则 UTF_8
     */
    @Test
    public void string_2_byte(){
        String res ="CDE9EAFD9B94CFB46C03AF882E82127D";
        byte[] b1 = res.getBytes(StandardCharsets.UTF_8);
        byte[] b2 = res.getBytes(StandardCharsets.US_ASCII);

        assertEquals(res.length(),b1.length);
        assertEquals(b1.length, b2.length);

        final int l = b1.length;
        for (int i = 0; i < l; i++) {
            assertEquals(b1[i], b2[i]);
        }
    }
}
