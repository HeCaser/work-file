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


