
function arraysUse() {
    const names: string[] = []
    names.push('hepan')      // 末尾添加
    names.unshift("heshan")  // 开头添加
}

function readOnly() {
    const names: readonly string[] = ["9"]
    names.push('0')  // readonly 不可以修改
}

