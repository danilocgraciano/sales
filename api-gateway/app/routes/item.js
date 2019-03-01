const httpProxy = require('express-http-proxy');
const serviceProxy = httpProxy('http://localhost:8082');

let proxyFunction = (req, res, next) => {
    serviceProxy(req, res, next);
};

module.exports = function (app) {

    app.route('/items')
        .get(proxyFunction)
        .post(proxyFunction);

    app.route('/items/:sku')
        .get(proxyFunction)
        .put(proxyFunction)
        .delete(proxyFunction);

};