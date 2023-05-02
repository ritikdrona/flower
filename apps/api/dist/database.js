'use strict';
var __importDefault =
    (this && this.__importDefault) ||
    function (mod) {
        return mod && mod.__esModule ? mod : { default: mod };
    };
Object.defineProperty(exports, '__esModule', { value: true });
const dotenv_1 = __importDefault(require('dotenv'));
const mongoose_1 = __importDefault(require('mongoose'));
dotenv_1.default.config();
const databaseUrl = process.env.MONGO_URL;
mongoose_1.default.connect(databaseUrl);
