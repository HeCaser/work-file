import { useEffect, useState } from "react";
import { View, Text, Button, Pressable } from "react-native";

function UseEffectLearn() {

    const [count, setCount] = useState(0);
    const [prop, setProp] = useState('a');

    // 接收一个参数, 每次 render 均会调用
    useEffect(() => {
        console.log('hepan 执行 useEffect every render')
       let timer = setTimeout(() => {
            setCount((count) => count + 1)
        }, 1000);

         // 释放资源
         return () => clearTimeout(timer)
    })

    // 接收两个参数, 第二个传空数组 [],  Runs only on the first render
    useEffect(() => {
        console.log('hepan 执行 useEffect only once')
        let timer = setTimeout(() => {
            setCount((count) => count + 1)
        }, 1000);

        // 释放资源 
        return () => clearTimeout(timer)
    }, [])

    //Runs on the first render
    //And any time any dependency value changes
    useEffect(() => {
        // 首次刷新或者 prop 改变会触发
        console.log('hepan 执行 useEffect first render or data changed')
    }, [prop])

    const changeProp = () => {
        setProp((pre) => {
            return pre + '-a'
        })
    }

    return (
        <View>
            <Text>count: {count}</Text>

            <View style={{ marginTop: 10 }}></View>
            <Text>prop: {prop}</Text>
            <View style={{ marginTop: 10 }}></View>
            <Button onPress={changeProp} title='修改 prop'></Button>
        </View>
    )

}

export default UseEffectLearn
