const httpProxy = require('express-http-proxy');
const serviceProxy = httpProxy('http://localhost:8080');

let proxyFunction = (req, res, next) => {
    serviceProxy(req, res, next);
};

module.exports = function (app) {

    app.route('/orders')
        .get(proxyFunction)
        .post(proxyFunction);

    app.route('/orders/:id')
        .get(proxyFunction)
        .put(proxyFunction)
        .delete(proxyFunction);

};