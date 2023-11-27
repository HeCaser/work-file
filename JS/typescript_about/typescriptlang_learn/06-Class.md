# Class 介绍

[链接](https://www.typescriptlang.org/docs/handbook/2/classes.html)

## 定义

```ts
  class Person {
        name = '2'  // 设置默认值, ts 自动推断类型
        age!: number // 没有默认值, 利用 ! 屏蔽错误提示
    }

```

## 实现接口

```ts
   interface Checkable {
        check(name: string): boolean;
    }

    // 实现接口, 必须实现与接口相同签名的方法
    class Name implements Checkable {
        check(name: string): boolean {
            console.log(`my name is ${name}`)
            return true
        }

        // 错误
        check(n:number){

        }
    }
```

## 继承类

```ts
    class Animal {
        eat(food: string) {
            console.log(`i am eat ${food}`)
        }
    }

    // 继承
    class Dog extends Animal {

        woof(times: number) {
            for (let i = 0; i < times; i++) {
                console.log("woof!");
            }
        }

        // Overriding Methods
        eat(food: string): void {
            console.log(`dog can overriding function eat()`)
            super.eat(food)
        }

        
    }

```

```ts
// 利用基类引用指向具体类似是常见的. 此时执行 eat() 方法, 与 Java 多态类似, 实际调用的是 Dog 的 eat() 方法
const dog: Animal = new Dog()
dog.eat('meat')
```


## Type-only Field Declarations

仅类型字段声明 `declare` 关键字

## Initialization Order

初始化顺序

1. 基类属性初始化
2. 基类构造方法执行
3. 子类属性初始化
4. 子类构造方法执行
   
```ts
class Base {
  name = "base";
  constructor() {
    console.log("My name is " + this.name);
  }
}
 
class Derived extends Base {
  name = "derived";
}
 
const d = new Derived();  // My name is base

```

## Member Visibility 成员可见性

`private` and `protected` 关键字


## Static Member 静态成员

```ts
class MyClass {
  static x = 0;
  static printX() {
    console.log(MyClass.x);
  }
}
```

静态成员不属于子对象, 而是通过 类名.成员 调用

