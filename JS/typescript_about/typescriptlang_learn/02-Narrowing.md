
原文地址 https://www.typescriptlang.org/docs/handbook/2/narrowing.html

当 TS 现在的参数类型时, 我们需要先判断类型,才能正常使用相关方法

## 利用 typeof 缩窄范围

```ts
typeof a === 'string'
typeof b === 'number'
```

注意: null 的类型是 `object`

```ts
var a = null
if(typeof a === 'object'){
    // run here, a is null
}

function printAll(strs: string | string[] | null) {
  // 因为 null 代表 false, 通过 && 来排除 null 的情况
  if (strs && typeof strs === "object") {
    for (const s of strs) {
      console.log(s);
    }
  } else if (typeof strs === "string") {
    console.log(strs);
  }
}
```


## 运算符缩窄范围
> === !== && 等运算符

注意: `== null` 不仅判断 null , 也判断 undefined, 反之亦然, 但是 `===` 则要求类型一样

```ts
var a = null
a == undefined // true
a === undefined // false
``` 

## in 操作符缩窄

JavaScript has an operator for determining if an object or its prototype chain has a property with a name: the in operator. TypeScript takes this into account as a way to narrow down potential types.

JS 的的操作符可以判断对象是否包含某个名称的属性, TS 沿用这个 `in`

``` ts
type Fish = { swim: () => void };

const fish = {
    swim: function () {
        alert('执行了')
    }
}

if("swim" in animal) // true

```


## instanceof

instanceof 可以判断对象类型

```ts
 let d = new Date() // 注意必须是 new Date()
 d instanceof Date  // true
```

## The never type

When narrowing, you can reduce the options of a union to a point where you have removed all possibilities and have nothing left. In those cases, TypeScript will use a never type to represent a state which shouldn’t exist.

The never type is assignable to every type; however, no type is assignable to never (except never itself). This means you can use narrowing and rely on never turning up to do exhaustive checking in a switch statement.

> 只有 never 类型可以赋值给 never 类型

