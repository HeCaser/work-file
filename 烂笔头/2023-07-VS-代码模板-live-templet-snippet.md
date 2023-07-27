
在 VS code 添加模板代码


[官网链接](https://code.visualstudio.com/docs/editor/userdefinedsnippets)

路径: Code > Preferences > Configure User Snippets 

选择好添加范围后会进入 **.json, eg: 给 tsx 文件添加代码片段

```
{
	// Place your snippets for typescriptreact here. Each snippet is defined under a snippet name and has a prefix, body and 
	// description. The prefix is what is used to trigger the snippet and the body will be expanded and inserted. Possible variables are:
	// $1, $2 for tab stops, $0 for the final cursor position, and ${1:label}, ${2:another} for placeholders. Placeholders with the 
	// same ids are connected.
	// Example:
	// "Print to console": {
	// 	"prefix": "log",
	// 	"body": [
	// 		"console.log('$1');",
	// 		"$2"
	// 	],
	// 	"description": "Log output to console"
	// }

	"Create Const function":{
		"prefix": ["hp-const-fun"],
		"body": ["const $1 = () => {\n}"],
		"description": "Create Const Function."
	},

	"Use Effect":{
		"prefix": ["hp-use-effect"],
		"body": ["useEffect(() => {\n\t$1\n}, [])"],
		"description": "UseEffect."
	}
}
```