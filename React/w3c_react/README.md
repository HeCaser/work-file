# W3C React 基础学习

> 注意不是 React Native

https://www.w3schools.com/react/default.asp

2023-04-28
# React Tutorial
- React is a JavaScript library for building user interfaces.

- React is used to build single-page applications.

- React allows us to create reusable UI components.

----


# CarComponent
- 定义 Class Componet
- learn state
- chagne state to rerender

> 总结: Always use the setState() method to change the state object, it will ensure that the component knows its been updated and calls the render() method (and all the other lifecycle methods).

---

# Lifecycle of Component
> Each component in React has a lifecycle which you can monitor and manipulate during its three main phases. The three phases are: ``Mounting``, ``Updating``, and ``Unmounting``.

## Mounting

> Mounting means putting elements into the DOM.

React has four built-in methods that gets called, in this order, when mounting a component:

- constructor()
- getDerivedStateFromProps()
- render()
- componentDidMount()

## Updating

> A component is updated whenever there is a change in the component's state or props.

React has five built-in methods that gets called, in this order, when a component is updated:
- getDerivedStateFromProps()
- shouldComponentUpdate()
- render()
- getSnapshotBeforeUpdate()
- componentDidUpdate()

## Unmounting

> The next phase in the lifecycle is when a component is removed from the DOM, or unmounting as React likes to call it.

React has only one built-in method that gets called when a component is unmounted:
- componentWillUnmount()

---

# React Props

> Demo 见 [LearnProps](./LearnProps.js)

React Props are like function arguments in JavaScript and attributes in HTML.

To send props into a component, use the same syntax as HTML attributes:

```
<Car brand='BYD'></Car>
```

Note: React Props are ``read-only``! You will get an error if you try to change their value.

---
2023-05-04
# React Events
Just like HTML DOM events, React can perform actions based on user events.

React has the same events as HTML: click, change, mouseover etc.

React events are written in ``camelCase`` syntax:

`onClick` instead of `onclick`.

React event handlers are written inside curly braces:

`onClick={shoot}`  instead of `onClick="shoot()"`.

---
# React Conditional Rendering
> 条件渲染
In React, you can conditionally render components.

There are several ways to do this.

## if Statement
```
function Goal(props) {
  const isGoal = props.isGoal;
  if (isGoal) {
    return <MadeGoal/>;
  }
  return <MissedGoal/>;
}
```

## Logical && Operator
```
{cars.length > 0 &&
    <h2>
        You have {cars.length} cars in your garage.
    </h2>
}
```

## Ternary Operator

>三元运算 `condition ? true : false`

` { isGoal ? <MadeGoal/> : <MissedGoal/> }`

---
# React Lists
In React, you will render lists with some type of loop.

The JavaScript `map()` array method is generally the preferred method.

`{cars.map((car) => <Car brand={car} />)}`

## Keys
Keys allow React to keep track of elements. This way, if an item is updated or removed, only that item will be re-rendered instead of the entire list.

Keys need to be unique to each sibling. But they can be duplicated globally.

```
 {cars.map((car) => <Car key={car.id} brand={car.brand} />)}
```
---

# React Forms

> [Demo 查看](./FormLearn.js)

In HTML, form data is usually handled by the DOM.

In React, form data is usually handled by the components.

You can control changes by adding event handlers in the `onChange` attribute.

We can use the `useState` Hook to keep track of each inputs value and provide a "single source of truth" for the entire application.

> 总结: React 组件利用 `useState` 自己存储数据. 

---

# React Router

> 主要用于 Web 导航

---

# React Memo

> 使用 memo 可以优化不必要的刷新

Using `memo` will cause React to skip rendering a component if its props have not changed.

This can improve performance.

 [MemoLearn](./MemoLearn.js)

 [MemoTodo](./MemoTodo.js)

---

# Styling React Using CSS
There are many ways to style React with CSS, this tutorial will take a closer look at three common ways:

- Inline styling
- CSS stylesheets
- CSS Modules

## Inline Styling

```
 <h1 style={{color: "red"}}>Hello Style!</h1>
  
```

> ``Note``: In JSX, JavaScript expressions are written inside curly braces, and since JavaScript objects also use curly braces, the styling in the example above is written inside two sets of curly braces ``{{}}``.

### camelCased Property Names

> 驼峰命名

Since the inline CSS is written in a JavaScript object, properties with hyphen separators, like ``background-color``, must be written with camel case syntax:

```
   <h1 style={{backgroundColor: "lightblue"}}>Hello Style!</h1>
```

### JavaScript Object

> 定义对象

```
const Header = () => {
  const myStyle = {
    color: "white",
    backgroundColor: "DodgerBlue",
    padding: "10px",
    fontFamily: "Sans-Serif"
  };
  return (
    <>
      <h1 style={myStyle}>Hello Style!</h1>
      <p>Add a little style!</p>
    </>
  );
```

## CSS Stylesheet

> Web 开发使用

You can write your CSS styling in a separate file, just save the file with the .css file extension, and import it in your application.

Create a new file called "App.css" and insert some CSS code in it:

```
body {
  background-color: #282c34;
  color: white;
  padding: 40px;
  font-family: Sans-Serif;
  text-align: center;
}
```

## CSS Modules
Another way of adding styles to your application is to use CSS Modules.

CSS Modules are convenient for components that are placed in separate files.

Create the CSS module with the .module.css extension, example: my-style.module.css.

Create a new file called "my-style.module.css" and insert some CSS code in it:

```
.bigblue {
  color: DodgerBlue;
  padding: 40px;
  font-family: Sans-Serif;
  text-align: center;
}
```

Import the stylesheet in your component:
```
import styles from './my-style.module.css'; 

const Car = () => {
  return <h1 className={styles.bigblue}>Hello Car!</h1>;
}

export default Car;
```
---

# React Hooks 

Hooks were added to React in version 16.8.

Hooks allow function components to have access to state and other React features. Because of this, class components are generally no longer needed.

## What is a Hook?

> Hooks allow us to "hook" into React features such as state and lifecycle methods.

### Hook Rules
There are 3 rules for hooks:

- Hooks can only be called inside React function components.
- Hooks can only be called at the top level of a component.
- Hooks cannot be conditional

## React useState Hook

[Demo: UseEffectLearn.js](./UseStateLeanr.js)

> 总结: useState 可以 hook 的通常是数据,可以是基本类型也可以是对象, 每次更新 state 都会产生新的 Data

The React useState Hook allows us to track state in a function component.

State generally refers to data or properties that need to be tracking in an application.


### import
```
import { useState } from "react";
```

### Initialize

useState accepts an initial state and returns two values:

- The current state.
- A function that updates the state.

```
// we are destructuring the returned values from useState
 const [color, setColor] = useState(""); 
```

### Read State
```
<h1>My favorite color is {color}!</h1>
```

### Update State

```
const [color, setColor] = useState("red");

 <>
    <h1>My favorite color is {color}!</h1>
    <button
      type="button"
      onClick={() => setColor("blue")}
    >Blue</button>
  </>
```

### What Can State Hold

The useState Hook can be used to keep track of strings, numbers, booleans, arrays, objects, and any combination of these!

### Updating Objects and Arrays in State

When state is updated, the entire state gets overwritten.

If we only called setCar({color: "blue"}), this would remove the brand, model, and year from our state.

We can use the JavaScript spread operator to help us.

```
 const [car, setCar] = useState({
    brand: "Ford",
    model: "Mustang",
    year: "1964",
    color: "red"
  });

  const updateColor = () => {
    setCar(previousState => {
      return { ...previousState, color: "blue" }
    });
  }
```

### Effect Cleanup
Some effects require cleanup to reduce memory leaks.

Timeouts, subscriptions, event listeners, and other effects that are no longer needed should be disposed.

We do this by including a return function at the end of the useEffect Hook.

**总结: 通过在 useEffect 末尾 return function 来释放资源**

```
 useEffect(() => {
        console.log('hepan 执行 useEffect only once')
        let timer = setTimeout(() => {
            setCount((count) => count + 1)
        }, 1000);

        // 释放资源
        return () => clearTimeout(timer)
    }, [])
```

---

## React useContext Hook

React Context is a way to manage state globally.

It can be used together with the useState Hook to share state between deeply nested components more easily than with useState alone.

[Demo: UseContextLearn](./UseContextLearn.js)

**总结:在嵌套 components 中共享数据**

例如第一层有数据 user, 第五层需要使用 user, 如果用 useState ,需要通过第二层到第四层依次传递, 而 useContext 可以解决类似问题.

- 创建 Context, 并传入数据

```
import { createContext, useContext,useState } from "react";

const UserContext = createContext()

function UseContextLearn() {
    const [user, setUser] = useState("Jesse Hall");
    return (
        <View>
            {/* 利用 Context 传递 user */}
            <UserContext.Provider value={user}>
                <Text>user: {user}</Text>
                <View style={{ marginTop: 10 }}></View>
                <Comp1></Comp1>
              
            </UserContext.Provider>

        </View>
    )

}
```

- 子 View 获取 user
```
function Comp3() {
    const user = useContext(UserContext)
    return (
        <View>
            <Text>我是 Comp3, 获取 user = {user}</Text>
        </View>
    )
}
```

---

## React useRef Hook

The useRef Hook allows you to persist values between renders.

It can be used to store a mutable value that does not cause a re-render when updated.

It can be used to access a DOM element directly.

[Demo: UseRefLearn](./UseRefLearn.js)

**总结:**
1. useRef 可以保持 value, 其与 useState 不同在于数据变动时不会触发 render 
2. useRef 可以持有组件的引用

保持数据

```
  const refCount = useRef(0)

  refCount.current += 1
```

引用对象
```
 const refInput = useRef()

 <TextInput style={{ borderWidth: 1, margin: 10 }} ref={refInput}/>
```
---

## React useReducer Hook

The reducer function contains your custom state logic and the initialState can be a simple value but generally will contain an object.

The useReducer Hook returns the current state and a dispatch method.

Here is an example of useReducer in a counter app:

**总结: reducer 是更复杂的 state, 添加自定义逻辑, useReducer 接收自定义处理逻辑和初始化数据, 返回 current state 和 dispatch **

[Demo: UseReducerLean,一个 reducer 实现增删改](./UseReducerLean.js)

---

2023-05-11

## React useCallback Hook

The React useCallback Hook returns a memoized callback function.

**总结**
1. useMemo returns a memoized value, useCallback **returns a memoized function**
2. useCallback 只在其依赖更新时刷新, 而不是每次 Render 都刷新
3. useCallback 可以提高性能

[代码见: MemoTodoFunctionDemo](./MemoTodoFunctionDemo.js)

---

## React useMemo Hook

The React useMemo Hook returns a memoized value.

**总结**
1. The useMemo Hook only runs when one of its dependencies update.
2. The main difference is that useMemo returns a memoized value and useCallback returns a memoized functio

[Demo: UseMemoLearn](./UseMemoLearn.js)