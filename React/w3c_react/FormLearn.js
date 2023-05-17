import React, { useState } from 'react'
import { View, TextInput, Text, StyleSheet, Button} from "react-native";



function FormLearn() {
    // useState 不可以在 class *** extends React.Component 中使用
    const [name, setName] = useState('')

    const submit =()=>{
        alert(name)
    }
    return (
        <View>
            <Text>请输入姓名:</Text>
            <TextInput onChangeText={setName} style={styles.input}></TextInput>
            <Text style={styles.show}>{name}</Text>
            <Button onPress={submit} title='提交'></Button>
        </View>
    );

}

const styles = StyleSheet.create({
    input: {
        margin: 12,
        borderWidth: 1,
        padding: 10,
    },
    show: {
        margin: 12,
        padding: 10,
    },
});

export default FormLearn