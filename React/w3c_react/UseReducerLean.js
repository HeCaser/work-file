import { useReducer } from "react";
import { View, Text, Button, Pressable, TextInput } from "react-native";


function UseReducerLean() {

    const COMPLETE = 'COMPLETE'
    const ADD = 'ADD'
    const DELETE = 'Delete'

    const initialTodos = [
        {
            id: 1,
            title: "Todo 1",
            complete: false,
        },
        {
            id: 2,
            title: "Todo 2",
            complete: false,
        }
    ];

    // 处理自定义逻辑
    const reducer = (state, action) => {
        switch (action.type) {
            case COMPLETE:
                return state.map((todo) => {
                    if (todo.id === action.id) {
                        return { ...todo, complete: !todo.complete };
                    } else {
                        return todo;
                    }
                });
            case ADD:
                let l = state.length
                let newTodo = {
                    id: l + 1,
                    title: `Todo ${l + 1}`,
                    complete: false,
                }
                return [...state, newTodo]
            case DELETE:
                return state.slice(0,state.length-1)
            default:
                return state
        }
    }

    const [todos, dispatch] = useReducer(reducer, initialTodos)


    const handleComplete = (todo) => {
        dispatch({ type: COMPLETE, id: todo.id });
    };

    const addTodo = () => {
        dispatch({ type: ADD });
    }

    const deleteTodo = () => {
        dispatch({ type: DELETE });
    }

    return (
        <View>
            {
                todos.map((todo) => {
                    return <View key={todo.id}>
                        <Text >{todo.title} {todo.complete ? "已完成" : "未完成"}</Text>
                        <Button onPress={() => {
                            handleComplete(todo)
                        }} title='修改完成状态'></Button>
                    </View>

                })
            }
            <Button onPress={() => {
                addTodo()
            }} title='添加todo'></Button>

            <Button onPress={() => {
                deleteTodo()
            }} title='删除todo'></Button>

        </View>
    )

}

export default UseReducerLean
