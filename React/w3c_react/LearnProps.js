
import React from "react";
import { View, Text, Button } from "react-native";

function Car(props) {
    return <Text> i am a car, my brand is {props.brand}</Text>
}
function CarInfo(props) {
    return <Text> i am a car, my name is {props.info.name} my model is {props.info.model}</Text>
}


function LearnProps() {
    const myBrand = '大众'
    const carInfo = { name: "Ford", model: "Mustang" };
    return (<View>
        {/* 透过 HTML 直接传递 */}
        <Car brand='BYD'></Car>

        {/* 利用 {} 传递变量 */}
        <Car brand={myBrand}></Car>

        {/* 传递对象 */}
        <CarInfo info={carInfo}></CarInfo>

    </View>)
}

export default LearnProps