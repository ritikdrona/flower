'use strict';
Object.defineProperty(exports, '__esModule', { value: true });
exports.viewUser = void 0;
const viewUser = (user) => {
    return {
        _id: user._id,
        name: user.name,
        username: user.username
    };
};
exports.viewUser = viewUser;
