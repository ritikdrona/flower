export const viewUser = (user: any) => {
    return {
        _id: user._id,
        name: user.name,
        username: user.username
    };
};
