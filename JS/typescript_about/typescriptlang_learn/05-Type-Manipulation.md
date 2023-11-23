# 类型 操作, 处理


[链接](https://www.typescriptlang.org/docs/handbook/2/conditional-types.html)

## keyof 操作符
根据 type 的 key 名称生成新的 type. 

注意 `keyof` 生成的对象类似是 `Type`

```ts
// a 是一个 type ,不是字符串
type a = 'a'
```

## typeof 操作符

返回对象的真实类型

## indexed access type

可以取出某个 Type 中的子 Type

## condition types

条件类型: 可以根据不同逻辑控制不同类型值

[详细用法见](https://www.typescriptlang.org/docs/handbook/2/conditional-types.html)

## Mapped Types

[详细用法见](https://www.typescriptlang.org/docs/handbook/2/mapped-types.html)

Demo1
```ts
// 定义类型, MapType 代表可以有任意多的属性, 属性规则为: key 是 string|number 类型,名称任意, 属性的值是 boolean 类型 
   type MapType = {
        [key: string]: boolean
    }

    // MapType 类型对象, 有一个属性, 名称为 1 , 值为 true
    const o1: MapType = {
        1: true,
    }

   // MapType 类型对象, 两个属性
    const o2: MapType = {
        1: true,
        name: true
    }
```


Demo2
```ts
    // will take all the properties from the type Type and change their values to be a boolean.
    // 接收泛型 Type , 属性名作为 key, 值转换为 boolean
   type OptionsFlags<Type> = {
        [Property in keyof Type]: boolean;
    };

    type Features = {
        darkMode: () => void;
        newUserProfile: () => void;
    };
    type FeatureOptions = OptionsFlags<Features>;

    const f1: FeatureOptions = {
        darkMode: true,
        newUserProfile: false
    }
```

## Template Literal Types

[详见](https://www.typescriptlang.org/docs/handbook/2/template-literal-types.html)

模板文字类型

```ts
type World = "world";
 
type Greeting = `hello ${World}`; // type Greeting = "hello world"
```

```ts
type EmailLocaleIDs = "welcome_email" | "email_heading";
type FooterLocaleIDs = "footer_title" | "footer_sendoff";

//type AllLocaleIDs = "welcome_email_id" | "email_heading_id" | "footer_title_id" | "footer_sendoff_id"
type AllLocaleIDs = `${EmailLocaleIDs | FooterLocaleIDs}_id`;
 
```