# 记录 JS 知识点

# 遍历 JsonObject 字段
[参考链接](https://stackoverflow.com/questions/957537/how-can-i-display-a-javascript-object/957652#957652)
```
// 主流浏览器支持
str = JSON.stringify(obj);
str = JSON.stringify(obj, null, 4); // (Optional) beautiful indented output.
console.log(str); // Logs output to dev tools console.
alert(str); // Displays output using window.alert()
```

```
// 可以在 RN 中使用
var output = '';
for (var property in object) {
  output += property + ': ' + object[property]+'; ';
}
alert(output);
```

---

# 时间相关

- 获取当前时间戳 

  `const mills = Date.now();` 

- 格式化

```
static formatMills(mills: number, format: string): string {
    if (!mills || !format) {
      return ''
    }
    try {
      return DateFormat(Number(mills), format)
    } catch (error) {
      return ''
    }
  }
  ```