## 

    /**
     * 抽象类
     * 子类要实现所有抽象方法 否则自身还得保持抽象类
     *
     * interface 是一个特殊类 编译后也是.class 里面所有方法都是未实现的抽象方法 只是不需要 abstract 标记
     * 说他特殊 是因为类只能单继承,接口可以多实现接口.
     * 接口之间可以继承多个接口,没有实现的方法会叠加
     *
     * 类转型 : 变量支持相互转化  int a = (int)3.5
     * 类型转化: 子类转化为父类, 也就是向上转型, 父类不能转化为子类. 特殊情况:父类对象本身就是子类转化过来的,可以强制转回为子类
     *
     * 继承和转型引出多态. 同名方法一子类为准. 这是由多态来保证.
     * 多态作用:1 控制不同对象的动态方法 2 解耦
     */

    /**
     * 静态 static
     * 静态变量: 类的共有成员. 可以类名.调用 也可以用具体对象调用. 不管通过对象还是类直接修改, 静态变量指向的都是同一个内存地址,效果相同.
     * 静态方法: 先于构造函数调用, 静态只能调用静态变量和方法.
     * 静态代码块: 先于对象的 构造方法运行, 只是在类第一次加载时调用. 也就是说在程序运行期间,这段代码只会运行一次
     *  引申: 匿名代码块, 也会先于构造方法运行,但是会晚于静态代码块. 并且每次new 对象都会执行
     *
     */

---
### 单例模式

    设计模式: 在软件开发中,经过验证的,用于处理特定环境下,重复出现的,特定的问题的解决方案.
    分类: 创建型,结构型,行为型.


单例: 保证一个类只有一个对象
   1. static 保证内存唯一
   2. private 修饰构造方法,防止在外部new对象
   3. 对外暴露获取对象的方法,这里利用静态,返回静态变量

### final 关键字
    类: 不能被继承
    变量: 不能再次赋值, 基本类型:不能修改具体值. 引用类型:不能修改指针(但是可以修改对象内部值)
    方法:不能改写(重写)
---
### 常量设计和常量池
    不能修改 final
    不会修改/只读/只要一份 static
    方便访问 public

    eg: public static final double PI = 3.141592653

    接口里面的变量默认常量: 缺省 public static final 修饰

    常量池: java 为很多基本类型的包装类/字符串都配置了常量池

    基本类型常量池:
    Boolean: true false
    Byte,Character 0 ~ 127
    Short,Integer, Long -128 ~ 127
    Float,Double 没有常量池

    所有字符串都会简历常量池缓存机制,相同的字符串在内从中只有一份.
    "ab"+"c"  == "abc" 结果为true 内从只有"abc" 不会有 "ab" 和 "c"

    注意: 常量化是建立在赋值创建基础上, eg Integer b = 19 ,会保存在栈内存里.
          new 对象创建 Integer c = new Integer(19) 是不会被常量化的,会放在堆内存中.
            此时 b==c 为 false. 但是 如果定义 int a = 19 由于拆箱操作 a==b 为true 
            a==c 为ture

```java
    /**
        * 常量池和拆箱验证代码
        */
        private void textChaiXinag(){
            int a = 1; // 基本变量 在常量池
            Integer b =  1;//引用变量,会被常量化到栈内存
            Integer c = new Integer(1);//引用变量, 不会被常量化.在堆内存

            System.out.println("a==b  "+(a==b)); // b 拆箱转换为基本类型比较 true
            System.out.println("a==c  "+(a==c)); // c 拆箱转换为基本类型比较 true
            System.out.println("b==c  "+(b==c)); // b c 比较地址, false
        }
```
  
栈内存空间小,速度快.  堆内存空间大,但是速到慢. 

---
### 不可变对象和字符串
 immutable对象是不可变的, 有改变,请clone/new一个对象进行修改

 所有属性都是 final private
 不提供 set
 类是final 或者所有方法是final
 如果类包含 mutable 类型, clone时需要深度克隆.

 String 类是一个典型的不可变对象
 字符串内容比较: equals 
 字符串内存比较: ==

 如果要大量字符串加法操作,如果用String, 会不断创建新的内存对象. 请使用 StringBuffer 或者 StringBuilder 来提高效率.

不可变对象提高读效率,但是操作(改变)效率会低.

---
### package 和 import

Java 支持多个目录放置Java,并通过 packge/import/classpath/jar机制相互调用
如果想使用不同包下的同名类,可以利用全路径 eg: a.b.ClassA  a.c.ClassA

---
### jar 文件导出和导入
jar 文件是一组 class 文件的压缩包
jar 文件是压缩的,利于传播
jar 只包含class,没有java,利于产权保护
多个 class 变为一个 jar 方便版本控制

---
### Java 访问权限
四种
 private 私有,只能本类
 default : 一个包内访问
 protected: 同一个包,子类(不同包)
 public: 公开的,所有类可访问

推荐: 成员变量全部private,成员方法全部public

---
### Java类库概述
1.8.0 已有 4k 多个类
JavaDoc 技术可以把特定规则注释生成 HTML 作为 API 文档查询

Java 数字类
整数 Short Int Long
浮点数 Float Double
大数 BigInteger BigDecimal
随机数 Random
工具类 Math

Java 字符串相关类
String 是一个不可变对象,加减操作性能差
常用方法 chatAt indexOf concat contains endsWith equals length trim
        replace replaceAll
StringBuffer(字符串加减,同步,性能较String高很多)
StringBuilder(字符串加减,不同步,性能较StringBuffer高)

Java 时间类
java.util.Date(基本废弃)
java.sql.Date 和数据库对应时间类
Calendar 目前常用类,但是是抽象类
 Calendar gc = Calendar.getInstance(); 会返回一个子类 GregorianCalendar()
 
1.8后更新了一个 time 包, 设计的更安全,封装更好,并且可扩展.

Java 格式化相关类
java.text.Format
NumberFormat: 数字格式化 抽象类 具体使用的 DecimalFormat 类,典型工厂模式
MessageFormat: 字符串格式化
DateFormat: 日期格式化 抽象类

---
### Java 异常和异常处理
异常:程序不正常的行为或者状态
异常处理: 程序能够返回到安全状态,允许用户保存结果,并以适当方式关闭程序.

Throwable分为两种: Error:系统内部错误,资源耗尽. Exception:程序相关异常,重点关注

Exception分为两种:
RuntimeException 程序自身错误:  5/0 空指针 数组越界..
非 RuntimeException 外界相关错误: 打开不存在文件,加载不存在的类..


try-catch-finally 一种保护代码正常运行的机制
try 必须有 catch finally 至少有一个  catch可以有多个

方法存在可能异常,但不处理,可以使用throws来声明,别人调用的时候可以自行处理,或者继续上抛.

---
### 自定义异常
继承 Exception 就变成 CheckedException 编译器会检查
继承 RuntimeException 就变成 Unchecked Exception 编译时编译器不会辅助检查,需程序员自己注意


---
### Java数据结构
数组: 一个存放多个数据的容器

    1. 数据同类型
    2. 按照线性排列
    3. 可通过索引快速访问
    4. 需要明确容器长度,非动态

数组索引
1. 数组length标记数组长度
2. 0 ~ length-1 超出范围报错
   
多维数组: 规则数组 int a[][] = new int[2][3] 代表2行3列
不规则数组

```java
    int b[][];
    b = new int[3][];
    b[0] = new int[3];
    b[1] = new int[4];
    b[2] = new int[5];
```

JCF 
容器 能够存放数据的空间结构
容器框架: 为表示和操作容器而规定的标准的提现结构

Java1.2后 提出JCF集合框架
 1. 功能更强大
 2. 易于学习
 3. 接口和实现分离,多种设计模式设计更灵活
 4. 泛型设计

列表 List 
集合 Set 
映射 Map

算法类
Arrays Collections

---
### 列表List
 1. 有序的Collection
 2. 允许重复的元素
   
List 实现
1. ArrayList 非同步的,以数组实现的列表,不支持同步.与数组相比是大小可以动态扩充.适合变动不大,增删少的情况.
2. LinkedList 非同步的,以双向链表实现的列表,可以当做堆栈,队列等.增删都比较高效.
3. Vector 同步的

---
### 集合 Set

1. 确定性: 对任意对象都能判断其是否属于某一个集合
2. 互异性: 集合内每个元素都是不相同的, 注意是内容互异
3. 无序性: 集合内顺序无序
   
Set 实现
1. HashSet 基于散列函数的集合,无序.不支持同步,内部由HashMap 实现,可以容纳null
2. TreeSet 基于树结构的集合,可排序,不支持同步
3. LinkedHashset 基于散列函数和双向链表的集合,可排序的.不支持同步 也是基于HashMap实现的
   
HashSet 和 LinkedHashSet 判断元素是否重复的原则,其实就是HashMap判断原则
> 首先判断 hashCode 返回值是否相同,若不同,直接返回 false. 否则进行下一步
> 下一步调用 equals 返回 false 或者 true . 
>  hashCode 和 equals 是所有类都有的方法,因为Object有.

TreeSet 判断元素重复原则
需要元素继承 Comparable 接口
比较两个元素的 compareTo 方法

--- 
### 映射 Map
数学定义: 两个集合之间的元素对应关系,一个输入对应一个输出

Java 中的 Map
Hashtable 同步,慢,数据量小 K V 均不能为 null
HashMap 不支持同步,快,数据量大 K V 均可为 null
Properties 同步,文件形式,数据量小
LinkedHashMap 基于双向链表的维持插入顺序的 HashMap
TreeMap 基于红黑树的Map 可以根据 key 的自然排序或者 CompareTo 方法进行排序输出. key 不能为null

---
### JCF的工具类
不存储数据,只是在数据结构上做高效操作

Arrays: 处理对象为数组
1. 排序: 对数组排序 sort/parallelSort
2. 查找: 从数组查找 binarysearch
3. 批量拷贝: copyOf
4. 批量赋值: fill
5. 等价比较: 判断两个数组中内容是否相同 equals

Collections: 处理对象为 List Set Map 功能和使用与 Arrays 类似

---
### 文件系统
文件系统是由OS(操作系统)管理的
文件系统和Java进程是**平行**的,是两套系统
文件系统是由文件夹和文件递归组合而成

java.io.File是文件和目录的重要类
File 类与 OS 无关,但是受到OS的权限限制.File 类不涉及到文件内容,值涉及属性

Java 7 提出了 NIO 包,是对 File 包的补充,例如文件复制和移动,递归遍历和删除等.

Java io 包概述
Java 读写文件,只能以数据流的形式进行读写
-节点类 直接对文件读写
-包装类 转化类和装饰类

