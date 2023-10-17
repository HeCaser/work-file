/**
 * Handbook -> Basics -> Everyday Types
 * https://www.typescriptlang.org/docs/handbook/2/everyday-types.html
 */

/**
 * 基础类型 The primitives: string, number,boolean
 * TS 会自动配置类型
 */

function primitiveDemo() {
    let userName = "hepan" // string
    let age = 12 // number
}


/**
 * any 类型
 * 当一个类型是 any 时,可以任意使用
 */

function anyDemo() {
    let obj: any = { x: 0 }
    obj.foo()
    obj()
    obj.bar = 100
    const n: number = obj
}

/**
 * function , 可以标注数据类型
 */

function funTypeDemo(num: number) {
}
funTypeDemo(13)


/**
 * Object Types
 * 除了基本类型, 开发中用的最多的是 Object Types.
 * Object Types 定义方式: 参数名 + 参数类型
 */

function objectTypeDemo(px: { x: number; y: number }) {

}

objectTypeDemo({ x: 3, y: 8 })


/**
 * 可选参数: 利用 ? 来定义可选参数
 *
 */

function objectTypeDemo2(px: { x: number; y?: number }) {

}
objectTypeDemo2({ x: 3, y: 8 })
objectTypeDemo2({ x: 3 })


/**
 * Union Types: 利用 | 来定义组合类型.
 * 在使用组合类型的对象时,需要判断类型才能调用此类型相关方法
 */

function unionTypeDemo(param: number | string) {
    if (typeof param === 'string') {
        console.log(`param is ${param.toUpperCase()}`)
    } else {
        console.log(`param is ${param}`)
    }
}

// 字符串和数字均可以
unionTypeDemo('hepan')
unionTypeDemo(12)


/**
 * Type Aliases: 为类型定义别名,这样可以在任意处使用该类型
 */

type Point = { x: number, y: number }

function typeAliasDemo(point: Point) {
    console.log(`point = ${point.x}-${point.y}`)
}

typeAliasDemo({ x: 19, y: 20 })


/**
 * Differences Between Type Aliases and Interfaces
 * 
 * Type 别名与 Interface 的区别
 * 1. 大部分情况, Type 与 Interface 可以互换
 * 2. 两者最大的区别在于 Interface 可以任意扩展,  Type 则不行
 */

// interface 扩展
interface a {
    a: string
}

interface a {
    b: number
}

interface b extends a {

}

type a1 = {
    name: string
}
// type 伪扩展
type b1 = a1 & {
    b: boolean
}


/**
 * Type Assertions 类型声明, 当开发者可以明确对象类型时,可以进行类型转换
 */

function typeAssertDemo() {
    const a = document.getElementById("main_canvas") as HTMLCanvasElement
}


/**
 * Literal Types
 * 文字类型: TS 中可以定义文字类型, 在限制参数范围时很有用
 */

function literalTypeDemo(pos: "left" | "right") {

}

literalTypeDemo("left")

