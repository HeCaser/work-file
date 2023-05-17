
import React from "react";
import { View, Text, Button } from "react-native";

const logmsg = 'hepan lifecycle  '

// 用于学习 Component 的生命周期
class LifecycleComponent extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      brand: "Ford",
      model: "Mustang",
      color: "red",
      show_child: true
    };
    console.log(logmsg + "constructor() ")
  }

  // The getDerivedStateFromProps method is called right before the render method:
  static getDerivedStateFromProps(props, state) {
    // 修改 state 会触发
    console.log(logmsg + "getDerivedStateFromProps() ")

    // 可以根据 props state 做逻辑: returns an object with changes to the state
    return { favoritecolor: state.color };
  }


  render() {
    // 修改 state 会触发, 即使 state 内容没有变化
    console.log(logmsg + "render() ")
    return (
      <View>
        {
          this.state.show_child ? <Child></Child> : null
        }

        <Text>favoritecolor = {this.state.favoritecolor}</Text>
        <Button onPress={() => { this.setState({ brand: 'BYD', color: 'yellow' }) }} title={'点击修改属性'} />
        <View style={{height:20}}></View>
        <Button onPress={() => { this.setState({ show_child: false }) }} title={'点击隐藏 child'}  />
      </View>
    );
  }

  // The componentDidMount() method is called after the component is rendered.
  componentDidMount() {
    console.log(logmsg + "componentDidMount() ")
  }

  shouldComponentUpdate() {
    console.log(logmsg + "shouldComponentUpdate() ")
    // if return false, render not called when state changed
    return true;
  }

  componentDidUpdate() {
    console.log(logmsg + "componentDidUpdate() ")
  }

  componentWillUnmount() {
    // The componentWillUnmount method is called when the component is about to be removed from the DOM
    console.log(logmsg + " myself componentWillUnmount() ")
  }

}

class Child extends React.Component {
  componentWillUnmount() {
    console.log(logmsg + " child componentWillUnmount() ")
    alert("The component child is about to be unmounted.");
  }

  render() {
    return (
      <Text>Hello , I am Child!</Text>
    );
  }
}

export default LifecycleComponent