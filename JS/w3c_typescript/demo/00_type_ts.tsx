

function type() {
    // 显示的声明基础类型
    let name: string = "hepan"

    // 隐式声明基础类型, 让 TS 编译器自动识别
    let sex = "male"
}


function main() {
    let name = "ssss"
    name = 0 // TS 会报错, 类型不匹配
}

function anyTest() {
    let v: any = true
    v = "000" // any 类型, TS 不在报错
}

// unknown is a similar, but safer alternative to any.
function unknownTest() {
    let w: unknown = 1
    w = {
        runMethod: () => {
            console.log("run a method")
        }
    } as { runMethod: () => void }

    
    if (w !== null && typeof w === 'object') {
        // 类型转换后
        (w as { runMethod: Function }).runMethod()
    }
}
