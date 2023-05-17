import React, { useState, useCallback } from 'react'
import { View, TextInput, Text, StyleSheet, Button } from "react-native";

import MemoTodoFunction from './MemoTodoFunction'


function MemoTodoFunctionDemo() {
    const [todos, setTodos] = useState(["todo 1", "todo 2"]);
    const [count, setCount] = useState(0)

    /**
     * 当调用 increment,MemoTodoFunction 会 Render, 即使此时 todos 没有改变
     * 为什么 MemoTodoFunction 使用了 memo 还会出现这个现象?
     * This is because of something called "referential equality".
     * 因为上面的特性, 每次 component render , 其中的 function 会重建, 
     * 而这个 function 是被 MemoTodoFunction 持有的,所以 MemoTodoFunction 会 Render
     */
    
    const increment = () => {
        setCount((c) => c + 1);
    };

    // 每次 Component Render , 由于 `引用平等` 特性, function 会 recreated
    const addTodo = () => {
        setTodos([...todos, "Todo " + count])
    }

    // 使用 useCallback ,使得 function 在 component Render 时不重建, 只有在依赖项改变时才会重建
    const addTodo2 = useCallback(() => { setTodos([...todos, "Todo " + count]) }, [todos])


    return (
        <View>
            <Text>count = {count}</Text>
            <View style={{ marginTop: 10 }}></View>
            <Button onPress={increment} title='++'></Button>
            <View style={{ marginTop: 10 }}></View>
            <MemoTodoFunction todos={todos} addTodo={addTodo2}></MemoTodoFunction>
        </View>
    );
}


export default MemoTodoFunctionDemo