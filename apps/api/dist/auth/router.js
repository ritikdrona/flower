'use strict';
Object.defineProperty(exports, '__esModule', { value: true });
const express_1 = require('express');
const validateSpecialAccessToken_1 = require('./middlewares/validateSpecialAccessToken');
const authenticate_1 = require('./routes/authenticate');
const createFirstUser_1 = require('./routes/createFirstUser');
const getUsers_1 = require('./routes/getUsers');
const validateUser_1 = require('./middlewares/validateUser');
const authRouter = (0, express_1.Router)();
authRouter.post('/authenticate', authenticate_1.authenticate);
authRouter.post(
    '/users/first',
    validateSpecialAccessToken_1.validateSpecialAccessToken,
    createFirstUser_1.createFirstUser
);
authRouter.get('/users', validateUser_1.validateUser, getUsers_1.getUsers);
exports.default = authRouter;
