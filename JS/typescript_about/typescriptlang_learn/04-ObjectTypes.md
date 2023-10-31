链接 https://www.typescriptlang.org/docs/handbook/2/objects.html

# Object Types

In JavaScript, the fundamental way that we group and pass around data is through objects. In TypeScript, we represent those through object types.

在 JS 中, 组织传递数据最基本的方式是利用 `objects`, 在 TS 中也定义了 `object` 类型

TS 的 `object` 可以是匿名的, 也可以通过 `interface` 和 `type` 定义

```ts
// 匿名
function greet(person: { name: string; age: number }) {
  return "Hello " + person.name;
}
// interface
interface Person {
  name: string;
  age: number;
}
// type
type Person = {
  name: string;
  age: number;
};

```

## Property Modifiers (属性修饰符)

### `?` : 可选操作符

```ts
interface PaintOptions {
  shape: Shape;
  xPos?: number;
  yPos?: number;
}
 
```
可选操作符代表当前参数可以不传, 此时其默认值为 `undefined`

### `解构参数`:  解构参数可以重命名, 但不能指定类型

```ts
function draw({ shape: Shape, xPos: number = 100 /*...*/ }) {
  render(shape);  // Cannot find name 'shape'. Did you mean 'Shape'?

  render(xPos); // Cannot find name 'xPos'.

}
```
上面代码总结: 接收传入的  `shape` 字段并赋值给 `Shape`, 并不是说入参是 `Shape` 类型


### `readonly` Properties : 只读参数

```ts
 type A = {
    readonly v: number
  }

 let a: A = { v: 10 }
 a.v = 10 // error , 只读参数不可修改
```

### Index Signatures 

没看明白, 不知属性名称时控制属性类型?

```ts
// 限制参数类型为 number
 interface NumberDictionary {
    [index: string]: number;
  }

let p1 : NumberDictionary ={a:1,b:2}   // ok
let p2 : NumberDictionary ={a:1,b:"3"} // error, stirng not number
```

### Excess Property Checks (额外属性检查)

总结: TS 的额外检查可以提前规避问题

例子:
```ts
 interface SquareConfig {
    color?: string;
    width?: number;
  }

// 会有错误提示, TS 认为你想输入 color 而实际是 colour
let p: SquareConfig = { colour: "1", width: 100 }
```

额外属性检查引起的问题: 当我们确实不传可选参数 `color` 时, 会接收到报错信息

```ts
// 不需要 color 参数,而是多传递了一个 `opacity` 仍然报错 
let p: SquareConfig = { opacity: 0.5, width: 100 }  // error 类型不匹配
```

解决办法:

```ts
// 方法1. 强制转换 as
let p: SquareConfig = { opacity: 0.5, width: 100 } as SquareConfig

// 方法2. 修改类型定义
  interface SquareConfig {
    color?: string;
    width?: number;
    [param:string]:any // 可由有任意数量,任意类型参数
  }
  
 let p: SquareConfig = { opacity: 0.5, width: 100 }  // ok

```

## Extending Types (扩展类型)

结论: 可以通过 `extends` 关键字扩展类型

```ts
 interface Person {
    name: string,
    age: number
  }

  interface Man extends Person {
    param: number
  }

```

结论: `extends` 可以多扩展:  `A extends B, C`

```ts
interface Colorful {
  color: string;
}
 
interface Circle {
  radius: number;
}
 
interface ColorfulCircle extends Colorful, Circle {}
 
const cc: ColorfulCircle = {
  color: "red",
  radius: 42,
};
```

## Intersection Types (交叉类型)

结论: 通过 `&` 操作符实现类型交叉

```ts
  interface Colorful {
    color: string;
  }
  interface Circle {
    radius: number;
  }
   
  type ColorfulCircle = Colorful & Circle;

  let p: ColorfulCircle = {
      color: 'red',
      radius: 10
    }
```

## Generic Object Types (泛型类型)

总结: 可以定义具有泛型的类型

```ts
  // 一个具备 content 字段的结构, 字段类型为 Type(占位,具体由使用时传入)
  interface Box<Type> {
    content: Type;
  }

 let stringBox: Box<string> = {
    content: "---"
 }
 let numberBox: Box<number> = {
    content: 100
 }
```