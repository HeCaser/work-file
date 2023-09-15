

function type(){
    // 显示的声明基础类型
    let name :string ="hepan"

    // 隐式声明基础类型, 让 TS 编译器自动识别
    let sex = "male"
}


function main() {
    let name = "ssss"
    name = 0 // TS 会报错, 类型不匹配
}

