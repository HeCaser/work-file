import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
// 注意类名必须为 Main, 不要有任何 package xxx 信息
/**
 * 明明生成了
N
N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。

数据范围： 1≤n≤1000
 
输入的数字大小满足 1≤val≤500 
 */
public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int count = Integer.parseInt(br.readLine());
            // 创建数组， 因为输入可能重复，所以其长度会大于需要的长度
            int[] list = new int[count];
            HashSet<String> set = new HashSet<>();
            String num ;
            int index = 0;
            while ((num = br.readLine()) != null) {
                if (!set.contains(num)) {
                    // 添加非重复元素
                    list[index] = Integer.parseInt(num);
                    index++;
                }
                set.add(num);
            }
            // 排序，多余元素(0) 会排到开头
            Arrays.sort(list);
            for (int i = 0; i<list.length; i++) {
                // 避免输出错误的 0
                if(list[i]!=0){
                    System.out.println(list[i]);
                }
                
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}