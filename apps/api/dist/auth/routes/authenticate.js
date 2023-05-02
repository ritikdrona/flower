'use strict';
var __awaiter =
    (this && this.__awaiter) ||
    function (thisArg, _arguments, P, generator) {
        function adopt(value) {
            return value instanceof P
                ? value
                : new P(function (resolve) {
                      resolve(value);
                  });
        }
        return new (P || (P = Promise))(function (resolve, reject) {
            function fulfilled(value) {
                try {
                    step(generator.next(value));
                } catch (e) {
                    reject(e);
                }
            }
            function rejected(value) {
                try {
                    step(generator['throw'](value));
                } catch (e) {
                    reject(e);
                }
            }
            function step(result) {
                result.done
                    ? resolve(result.value)
                    : adopt(result.value).then(fulfilled, rejected);
            }
            step(
                (generator = generator.apply(thisArg, _arguments || [])).next()
            );
        });
    };
var __importDefault =
    (this && this.__importDefault) ||
    function (mod) {
        return mod && mod.__esModule ? mod : { default: mod };
    };
Object.defineProperty(exports, '__esModule', { value: true });
exports.authenticate = void 0;
const jwt_1 = require('../../lib/jwt');
const User_1 = __importDefault(require('../models/User'));
const utils_1 = require('../utils');
const authenticate = (req, res) =>
    __awaiter(void 0, void 0, void 0, function* () {
        const { username, password } = req.body;
        const user = yield User_1.default.findOne({ username: username });
        if (user === null) {
            return res.status(404).send({
                success: false,
                message: 'Username or password incorrect.',
                user: null
            });
        }
        if (
            (user === null || user === void 0 ? void 0 : user.password) !==
            password
        ) {
            return res.status(200).send({
                success: false,
                message: 'Username or password incorrect.',
                user: null
            });
        }
        const token = (0, jwt_1.generateJWT)(user);
        return res.status(200).send({
            success: true,
            message: 'Authenticated Successfully!',
            user: (0, utils_1.viewUser)(user),
            token: token
        });
    });
exports.authenticate = authenticate;
