'use strict';

const ynab = require("ynab");
const secret = require('./secret');
const api = new ynab.API(secret.ynab.accessToken);

module.exports.api = api;
