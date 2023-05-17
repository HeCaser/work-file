import { useState, useRef, useEffect } from "react";
import { View, Text, Button, Pressable, TextInput } from "react-native";


function UseRefLearn() {
    const [count, setCount] = useState(0);

    const refCount = useRef(0)

    // useRef 作为对象的引用
    const refInput = useRef()
    const refInput2 = useRef()

    useEffect(() => {
        // 每次 render +1, 不会触发 render
        refCount.current += 1
    })

    return (
        <View>

            <Text>count: {count}</Text>
            <View style={{ marginTop: 10 }}></View>
            <Text>页面 Render 次数: {refCount.current}</Text>
            {/* 触发render */}
            <Button onPress={() => {
                setCount(count + 1)
            }} title='修改 count'></Button>


            <TextInput style={{ borderWidth: 1, margin: 10 }} ref={refInput}></TextInput>
            <TextInput style={{ borderWidth: 1, margin: 10 }} ref={refInput2}></TextInput>

            <View style={{ marginTop: 10 }}></View>
            <Button onPress={() => {
                refInput.current.focus()
            }} title='Input1 获取焦点'></Button>

            <View style={{ marginTop: 10 }}></View>
            <Button onPress={() => {
                refInput2.current.clear()
            }} title='Input2 清空'></Button>


        </View>
    )

}


export default UseRefLearn
