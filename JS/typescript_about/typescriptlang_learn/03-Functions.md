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

