const express = require('express');
const consign = require('consign');
const helmet = require('helmet');
var logger = require('morgan');

const app = express();
app.use(logger('dev'));
app.use(helmet());
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

consign({ cwd: 'app' })
    .include('routes')
    .into(app);

module.exports = app;