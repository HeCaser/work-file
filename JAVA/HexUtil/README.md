# HexUtil 

本文件夹包含一个 Hex 编码/解密工具类和相关测试类


## Hex 编解码简介

16 进制转换是一种常见的字节数组 `byte[]` 编码方式, 类似的还有 `base64`

16 进制转换逻: 把 byte[] 的元素当做 (0-255) 范围内数字, 转换成 16 进制, 对应 0x00 - 0xFF

16 进制转换后, 所占空间会加倍

16 进制转换后, A-F 可能有大小写的区别. 例如 0a 和 0A, 两者进行 Hex 解码后其实是一样的, 都是十进制 10 


## 为什么要用到 16 进制转换

- 方便查看内容

对于字节数组 `byte[]` , 直接转换为 `String` 输出,大部分情况都是乱码, 进行 Hex 转换后可读性增强

- 进行网络传输
  
`byte[]` 直接传输不太方面, 转换为字符串后方便网络传输


## 是否可以直接用 Hex 转换后的字节数组处理逻辑

- 原则上不建议直接使用, 因为不同工具包实现 Hex 的逻辑可能不一致, 区别在于 a-f 的大小写(对应 byte 相差 32).

- 直接使用: 编码后的 `String` 统一转换为大写(或者小写),保证对应 byte 一致

- 建议进行解码后得到原始 `byte[]` 再使用,保证多端一致


##  Hex 编码后期望得到 `byte[]`

本文档实现的方法是转换为字符串, 可以通过字符串转换为 byte[], 建议选 UTF_8 编码

```
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
 ```