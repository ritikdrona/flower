import { Request, Response } from 'express';
import User from '../models/User';
import { viewUser } from '../utils';

export const getUsers = async (req: Request, res: Response) => {
    const users = await User.find();
    return res.status(200).send({
        message: 'Users fetched successfully!',
        users: users.map((user) => viewUser(user))
    });
};
