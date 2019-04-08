const httpProxy = require('express-http-proxy');
const serviceProxy = httpProxy('http://localhost:8080');

let proxyFunction = (req, res, next) => {
    serviceProxy(req, res, next);
};

module.exports = function (app) {

    app.route('/customers')
        .get(proxyFunction)
        .post(proxyFunction);

    app.route('/customers/:id')
        .get(proxyFunction)
        .put(proxyFunction)
        .delete(proxyFunction);

};