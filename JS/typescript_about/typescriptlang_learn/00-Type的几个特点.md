TS 与 JS 最大的区别就是 TS 就有 类型 (Type)

那么 TS 的 Type 到底是什么,有什么特性呢?

本文记录 Type 特性
文档链接:  https://www.typescriptlang.org/docs/handbook/typescript-in-5-minutes-oop.html

## Erased Structural Types

Demo Code

```ts
interface Pointlike {
  x: number;
  y: number;
}
interface Named {
  name: string;
}
 
function logPoint(point: Pointlike) {
  console.log("x = " + point.x + ", y = " + point.y);
}
 
function logName(x: Named) {
  console.log("Hello, " + x.name);
}
 
const obj = {
  x: 0,
  y: 0,
  name: "Origin",
};
 
logPoint(obj);
logName(obj);
```

关键点: `obj` 既可以作为 `Pointlike` , 也可以作为 `Named` 使用. 

解释: 
TypeScript’s type system is structural, not nominal: We can use obj as a Pointlike because it has x and y properties that are both numbers. The relationships between types are determined by the properties they contain, not whether they were declared with some particular relationship.

总结:
TS 的 Type 是结构化的,而不是标称的. 判断对象是否符合某个 Type, 是根据其参数确定的

## Empty Types
任意有值的对象, 都可以作为空类型 `{}` 使用


## Identical Types
> 恒等性, 等价性. 

```ts
class Car {
    drive() {
      // hit the gas
    }
  }
  class Golfer {
    drive() {
      // hit the ball far
    }
  }
  // No error?
  let w: Car = new Golfer();
```

总结: 因为 `Car` 与 `Golfer` 定义的结构相同, 所以 `let w: Car = new Golfer();` 是正确的.
