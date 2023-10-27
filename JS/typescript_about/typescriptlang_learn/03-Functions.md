链接 https://www.typescriptlang.org/docs/handbook/2/functions.html

介绍 `function` 相关知识

## Generic Functions (泛型函数)

```ts
function firstElement<Type>(arr: Type[]): Type | undefined {
  return arr[0];
}
```

通过添加 Type , 在入参与出参之间关联了具体类型

```ts
// s is of type 'string'
const s = firstElement(["a", "b", "c"]);
// n is of type 'number'
const n = firstElement([1, 2, 3]);
// u is of type undefined
const u = firstElement([]);
```

注意: 我们在方法中并没有规定具体类型, 运行时具体类型由 TS 判断

可以定义多种类型参数

```ts
function map<Input, Output>(arr: Input[], func: (arg: Input) => Output): Output[] {
  return arr.map(func);
}
 
// Parameter 'n' is of type 'string'
// 'parsed' is of type 'number[]'
const parsed = map(["1", "2", "3"], (n) => parseInt(n));
```

## Constraints (参数约束)

可以利用 `extends` 来约束参数类型或者具备某个属性

```ts
// 入参要求具备 .length 属性
  const getBigLength = <Type extends { length: number }>(a: Type, b: Type) => {
    if (a.length > b.length) {
      return a
    } else {
      return b
    }
  }

  getBigLength([1,2],[1,2,3]) // [1,2,3]
```

进一步: 规定返回类型为 `Type`
注意: 方法默认返回类型是 `Type`, 但返回 `{length:9}` 这样的数据是错误的, 必须与入参类型一致

```ts
  const getBigLength = <Type extends { length: number }>(a: Type, b: Type): Type => {
    if (a.length === b.length) {
      return { length: 9 }  // 错误, 不匹配 Type
    } else
      if (a.length > b.length) {
        return a
      } else {
        return b
      }
  }
```

虽然 `{ length: 9 }` 具备 `length` 属性, 但是其改变了入参的数据结构,所以不能作为结果返回

设想如果可以返回, 在使用时会造成如下错误:

```ts
  // 这里期待数组,但实际会是 { length: 9 }
  const arr = getBigLength([1, 2, 4], [1, 2, 3])

  arr.slice(0) // error
```

## 可选参数

结论: 可以用 `?` 实现可选参数

```ts
 const testParam = (n: number, fix?: number) => {
      return n.toFixed(fix)
  }
  
  const res = testParam(1)    // "1" 
  const res1 = testParam(1,3) // "1.000"
```
注意: `?` 标记的可选参数, 是可以接收 `undefined` 的


进阶: 可以给可选参数设置默认值

```ts
  // fix 是可选参数, 默认 = 4
  const testParam = (n: number, fix=4) => {
      return n.toFixed(fix)
  }

 const res = testParam(1)    // "1.0000"
 const res1 = testParam(1,3) // "1.000"

```

## Function Overloads (方法重载)

总结: 知道重载的写法和基本特性即可, 开发中少用

```ts
   // 重载函数, 下面写法只会重载入参为 1个 或者 3个的
  function makeDate(timestamp: number): Date;
  function makeDate(m: number, d: number, y: number): Date;
  function makeDate(mOrTimestamp: number, d?: number, y?: number): Date {
    if (d !== undefined && y !== undefined) {
      return new Date(y, mOrTimestamp, d);
    } else {
      return new Date(mOrTimestamp);
    }
  }


 const d1 = makeDate(12345678);
 const d2 = makeDate(5, 5, 5);
 const d3 = makeDate(1, 3); // error

```


## object 类型

`object` 代表除去 `(string, number, bigint, boolean, symbol, null, or undefined)` 之外的所有类型.

```ts
  const testParam = (param: object) => {
     console.log(`param is ${JSON.stringify(param)}`)
  }

  testParam([0,0])   // [0,0]
  testParam({name:"hepan"}) // {"name":"hepan"}

  testParam("字符串") // error string not object

```

## Rest Parameters (剩余参数)

结论: 利用 `...` 和 `T[]` 设置剩余参数

```ts
const testParam = (n: number,...m:number[]) => {
      console.log(`param is ${m.constructor.toString()} `) // m is array
  }

// 使用方式(注意入参不是 [])
testParam(0,1,2,3) 
```

## Parameter Destructuring (解构参数)

```ts
function sum({ a, b, c }) {
  console.log(a + b + c);
}
sum({ a: 10, b: 3, c: 9 });


// 复杂的数据可以通过 type 定义

type ABC = { a: number; b: number; c: number };
function sum({ a, b, c }: ABC) {
  console.log(a + b + c);
}
```

