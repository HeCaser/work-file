

/**
 * 可擦除类型
 */

interface Cat{
    name:string
}

interface Dog{
    color:string
}

function fCat(cat:Cat){

}

function fDog(dog:Dog){

}

const obj = {
    name: 'mimi',
    color: 'black'
}
// obj 可以作为 Cat 和 Dog 使用, 因为其参数符合相关定义
fCat(obj)
fDog(obj)

/**
 * Empty Types 
 */
class Empty { }

function fn(arg: Empty) { }

// {k:1} 被认为是 Empty 类型
fn({ k: 1 })



/**
 * 恒等性
 */
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



function dateFun(date:Date){

}

// 报错,因为 JS Date() 作为 string 返回. 传入 new Date() 解决
dateFun(Date())