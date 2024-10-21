
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
数据范围：输入的正整数满足 
1
≤
n
≤
100
 
1≤n≤100 
 */
class JavaFile {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                int bottle = Integer.parseInt(line);
                if (bottle == 0) {
                    return;
                }
                int result = 0;
                while (bottle > 0) {
                    // 可以换取的数量
                    int replace = bottle / 3;
                    // 换取后剩余的数量
                    int last = bottle % 3;
                    // 已换取总数
                    result += replace;
                    // 剩余瓶子
                    bottle = replace + last;
                    if (bottle < 3) {
                        break;
                    }
                }
                // 特殊情况
                if (bottle == 2) {
                    result += 1;
                }
                System.out.println(result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
