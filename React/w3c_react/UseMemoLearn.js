import { useState, useRef, useEffect, useMemo } from "react";
import { View, Text, Button, Pressable, TextInput } from "react-native";


function UseMemoLearn() {
    const [count, setCount] = useState(0);
    const [todos, setTodos] = useState(["todo 1", "todo 2"]);

    /**
     * calculate 是一个数值变量
     * 当调用 addTodo 时, calculate 代表的耗时计算也会被调用
     * 注意: 不使用 calculate 时,代码要删掉,否则 Render 时会重建,导致 expensiveCalculation1 执行
     */
    const calculate = expensiveCalculation1(count)

    /**
     * calculate2 是一个数值变量
     * 只有 count 改变时其所代表的复杂计算会执行,返回新值
     */
    const calculate2 = useMemo(() => expensiveCalculation2(count), [count])

    const increment = () => {
        setCount((c) => c + 1);
    };

    const addTodo = () => {
        setTodos([...todos, "Todo " + count])
    }

    return (
        <View>

            <View>
                <Text>count: {count}</Text>
                <View style={{ marginTop: 10 }}></View>
                {todos.map((todo, index) => {
                    return <Text key={index}>TODO NAME: {todo}</Text>
                })}
                <Button onPress={increment} title='++'></Button>
                <Text>依赖 count 的复杂计算: ={calculate2} </Text>
            </View>


            <View>
                <View style={{ marginTop: 10 }}></View>
                <Button onPress={addTodo} title='add todo'></Button>
            </View>
        </View>
    )

}

const expensiveCalculation1 = (num) => {
    console.log('hepan 复杂计算1')
    for (let i = 0; i < 100000; i++) {
        num += 1;
    }
    return num;
}

const expensiveCalculation2 = (num) => {
    console.log('hepan 复杂计算2')
    for (let i = 0; i < 100000; i++) {
        num += 1;
    }
    return num;
}

export default UseMemoLearn
