# 记录 JS 基础学习

参考链接

- [W3C School](https://www.w3schools.com/js/default.asp)


[JS 基础](00JS.md)

- JS 基础概念
- JS 在 HTML 中的简单使用
    - 在 head 中
    - 在 body 中
    - 在外部文件中

[输出,关键字,语法语句,编写规则](01JS.md)

- JS 展示数据的四种方式
  - innerHTML
  - document.write()
  - widow.alert()
  - console.log()

- JS 语法语句
  - 包含: Values, Operators, Expressions, Keywords, and Comments.
  - **关键字**: var let const if switch for try ...
  - 常量,变量,操作符,命名规则
  - 大小写敏感

[变量 let const](02JS.md)

[JS 操作符,运算符](03JS.md)

  - 算数运算符 + - * % 等
  - 赋值运算符 = += 等
  - 比较运算符 == === 
  - 比较运算符 == === != 
  - 逻辑运算符 && || !
  - type 运算: typeof  instanceof
  - 位运算: & | ~ << 等

  - 数据类型 Data Types: 简介, 更详细的介绍在后续章节

[Function, Object, Events ](04JS.md)

[Strings, String Methods](05JS.md)
  - trim
  - padString
  - replace
  - charAt
  - split 
  - ...

[String Search, 模板字符串](06JS.md)

  - 字符串检索相关方法: indexof search match starsWith  等
  - `` 模板字符串


[JS Number 相关](07JS.md)

  - number 简介: 精度 NaN Infinity
  - 进制转换
  - BigInt

[JS Number 相关2](08JS.md)

  - Method: 
    - 指数转换 toExponential() 
    - 固定小数位 toFixed() 
    - 固定整长 toPrecision 
    - 转为为基础类型 valueOf()
    - 转换整型 parseInt()
    - 转换浮点 parseFloat()

  - Properites: 
    - EPSILON
    - MAX_VALUE
    - MAX_SAFE_INTEGER
    - POSITIVE_INFINITY

[JS array: basic](09JS.md)

  - 数组: 创建,获取元素,赋值,遍历,判断 array 类型
  
[JS arrary method](10JS.md)
  - method: length join() pop() push()
  - 拼接 concat() ,添加 splice(), 切割 slice()
  - 排序 sort() 

[JS Iteration](11JS.md)
  - forEach()
  - map()
  - flatMap()
  - reduce()
  - every(), some()
  - indexOf(),lastIndexOf()
  - find(), findIndex()
  - Array.from()
  - Keys(),entries()
  - includes()


[JS Date](12JS.md)
  - Date() 对象创建
  - 展示: toString(),toDateString(),toUTCString()
  - ISO UTC Time Zones

[JS Date Method](13JS.md)
  - getFullYear()
  - getMonth()
  - getDate() 1-31
  - getHours(), getMinutes(), getSeconds()
  - getDay() 星期几
  - getTime() 时间戳
  - Date.now() 时间戳
  - getUTC**() 返回 UTC 基准的年月日,时分秒
  - getTimezoneOffset() 以分钟单位返回当前时区与 UTC 时间差
  - get 都有对应的 set
  - setFullYear(), setMonth() ,setDate() 等
  - Compare Dates 日期比较 d1 > d2

[JS Math Method](14JS.md)
  - 方法: round(),ceil(),floor(),trunc()
  - pow(),sign()
  - sin(),cos()
  - min(),max()
  - random()
  - log() log2()
  - 其他 Math Method

  - Random() 生成不同范围内随机数


[JS Booleans Comparisons](15JS.md)
  - Booleans
  - Comparisons
  - 逻辑操作符 && || !
  - 三目运算 value = (condition)? v1:v2
  - ?? 操作符
  - ?. 操作符

  - if else

[JS Switch Loop](16JS.md)
  - switch
  - for() 循环, 变量作用域
  - for in
  - Array.forEach()
  - for of, 轮询迭代器 iterable
  - while loop

[JS Break Iterable](17JS.md)
  - break, continue
  - lable: 指定终止位置
  - Iterable

[JS Set Maps](18JS.md)
  - new Set(数组)
  - set.add, 遍历
  - new Map(二维数组)
  - map.set(), size, delete(), has()
  - map 遍历:forEach(), entries()

[JS 类型相关](19JS.md)
  - typeof
  - object.constructor : 返回对象的构造方法, 可以用来判断类型
  - instanceof
  - 类型转换

[JS 位运算](20JS.md)
  - & | ^ ~ 等运算
  - >> >>> << 左移,右移运算
  - 二进制/十进制转换

[JS 正则](21JS.md)
  - 正常语法
  - 正则修饰符  i g m
  - [a-z] (a|0)
  - \d \s \b
  - 数量: n+, n*, n?
  - RegExp: test() exec(), 分组查找替换


[JS Errors,Scope](22JS.md)
  - try catch finally
  - The Error Object
  - 作用域


[JS this](23JS.md)
  - this 关键字
  - Arrow function : let myFunction = (a, b) => a * b;
  