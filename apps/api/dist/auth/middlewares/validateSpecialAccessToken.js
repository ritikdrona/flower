'use strict';
Object.defineProperty(exports, '__esModule', { value: true });
exports.validateSpecialAccessToken = void 0;
const validateSpecialAccessToken = (req, res, next) => {
    const header = req.headers['special-access-token'];
    const specialAccessToken = process.env.SPECIAL_ACCESS_TOKEN;
    if (!header || header !== specialAccessToken) {
        return res.status(401).json({
            message: 'Unauthorized'
        });
    }
    next();
};
exports.validateSpecialAccessToken = validateSpecialAccessToken;
