import React from 'react'
import { View, TextInput, Text, StyleSheet, Button } from "react-native";
import { memo } from "react";

// 除了 value 类型参数, 还接收一个 function 类型参数
const MemoTodoFunction = ({ todos, addTodo }) => {
    console.log("MemoTodoFunction render");
    return (
        <>
            <Text>MemoTodoFunction</Text>
            {todos.map((todo, index) => {
                return <Text  key={index}>TODO NAME: {todo}</Text>
            })}
              <Button onPress={addTodo} title='添加todo'></Button>
        </>
    )
}


export default memo(MemoTodoFunction)