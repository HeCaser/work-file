import { useState } from "react";
import { View, Text, Button, Pressable } from "react-native";

function UseStateLearn() {

    const [car, setCar] = useState({
        name: '宋DMI',
        brand: "BYD",
        color: "red"
    })

    const changeColor = () => {
        setCar((pre) => {
            return { ...pre, color: 'Yellow'}
        })
    }


    return (
        <View>
            <Text>我是 {car.brand} 品牌的 {car.name} ,颜色是 {car.color}</Text>

            <View style={{ marginTop: 10 }}></View>
            <Button onPress={changeColor} title='修改颜色'></Button>
        </View>
    )

}

export default UseStateLearn
