// server.js  
const WebSocket = require('ws');  
  
const wss = new WebSocket.Server({ port: 8080 });  
  
console.log('hepan ~~')
wss.on('connection', function connection(ws) {  
  ws.on('message', function incoming(message) {  
    console.log('received: %s', message);  
    wss.clients.forEach(function each(client) {  
      if (client !== ws && client.readyState === WebSocket.OPEN) {  
        client.send(message);  
      }  
    });  
  
    ws.send('Hello, client! time is ' +new Date().getTime());  
  });  
});