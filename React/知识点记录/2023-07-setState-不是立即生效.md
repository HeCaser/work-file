## Q: setState 不是立即 '生效'
当使用 setState 改变对象值时，在后面紧接着使用该对象，其值并不是前面设置的
```ts
  const [isAddWeChat, setIsAddWeChat] = useState(true)
  
  if (result && result.code === 0) {
        setIsAddWeChat(flase)
        alert(isAddWeChat) // here is true
    }
```

## 原因
https://stackoverflow.com/questions/54069253/the-usestate-set-method-is-not-reflecting-a-change-immediately
- useState hook is also asynchronous： hook 是异步的

- Also, the main issue here is not just the asynchronous nature but the fact that state values are used by functions based on their current closures, and state updates will reflect in the next re-render by which the existing closures are not affected, but new ones are created.
      更重要的原因是 state value 在当前作用域是固定的，也就是你无论如何延时获取，在当前 {} 内得到的仍然是旧值。 
      
- 如果需要根据 value 的变动进行逻辑处理， 使用 useEffect

```
useEffect(() => {
    // action on update of movies
}, [isAddWeChat]);
```