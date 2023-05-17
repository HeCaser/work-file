import { createContext, useContext,useState } from "react";
import { View, Text, Button, Pressable } from "react-native";

const UserContext = createContext()

function Comp1() {
    return (
        <View>
            <Text>我是 Comp1</Text>
            <Comp2></Comp2>
        </View>
    )
}

function Comp2() {
    const user = useContext(UserContext)
    return (
        <View>
            <Text>我是 Comp2</Text>
            <Comp3></Comp3>
        </View>
    )
}

function Comp3() {
    const user = useContext(UserContext)
    return (
        <View>
            <Text>我是 Comp3, 获取 user = {user}</Text>
        </View>
    )
}

function UseContextLearn() {
    const [user, setUser] = useState("Jesse Hall");


    return (
        <View>
            {/* 利用 Context 传递 user */}
            <UserContext.Provider value={user}>

                <Text>user: {user}</Text>

                <View style={{ marginTop: 10 }}></View>
                <Comp1></Comp1>
                <View style={{ marginTop: 10 }}></View>
                <Button onPress={() => {
                    setUser("hepan")
                }} title='修改 user'></Button>
            </UserContext.Provider>

        </View>
    )

}

export default UseContextLearn
