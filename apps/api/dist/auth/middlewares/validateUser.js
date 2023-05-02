'use strict';
Object.defineProperty(exports, '__esModule', { value: true });
exports.validateUser = void 0;
const jwt_1 = require('../../lib/jwt');
const validateUser = (req, res, next) => {
    const header = req.headers['authorization'];
    if (!header || !header.includes(' ')) {
        return res.status(401).json({
            message: 'Unauthorized'
        });
    }
    const token = header.split(' ').at(1);
    const payload = (0, jwt_1.verifyJWT)(token);
    if (payload === null) {
        return res.status(401).json({
            message: 'Unauthorized'
        });
    }
    req.userId = payload.sub;
    next();
};
exports.validateUser = validateUser;
