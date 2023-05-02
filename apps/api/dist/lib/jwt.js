'use strict';
var __importDefault =
    (this && this.__importDefault) ||
    function (mod) {
        return mod && mod.__esModule ? mod : { default: mod };
    };
Object.defineProperty(exports, '__esModule', { value: true });
exports.verifyJWT = exports.generateJWT = void 0;
const jsonwebtoken_1 = require('jsonwebtoken');
const config_1 = __importDefault(require('../config'));
const JWT_SECRET_KEY = process.env.JWT_SECRET_KEY;
const generateJWT = (user) => {
    const token = (0, jsonwebtoken_1.sign)(
        {
            sub: user.id
        },
        JWT_SECRET_KEY
    );
    return token;
};
exports.generateJWT = generateJWT;
const verifyJWT = (token) => {
    const verifyOptions = {
        maxAge: config_1.default.JWT_TOKEN_MAX_AGE
    };
    try {
        const payload = (0, jsonwebtoken_1.verify)(
            token,
            JWT_SECRET_KEY,
            verifyOptions
        );
        return payload;
    } catch (error) {
        console.log(error.message);
        return null;
    }
};
exports.verifyJWT = verifyJWT;
