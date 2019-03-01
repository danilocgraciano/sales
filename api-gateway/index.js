const http = require('http');

const app = require('./config/express.js');

http.createServer(app).listen(3000, function () {
    console.log('Server running on port 3000...');
});