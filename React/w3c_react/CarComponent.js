
import React  from "react";
import { View,Text, Button } from "react-native";

class CarComponent extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        brand: "Ford",
        model: "Mustang",
        color: "red",
        year: 1964
      };
    }
    render() {
      return (
        <View>
          <Text>My brand is {this.state.brand}, my color is {this.state.color}</Text>
          <Button onPress={()=>{this.setState({brand:'BYD',color:'yellow'})}} title={'点击修改属性'}/>
        </View>
      );
    }
  }

  export default CarComponent