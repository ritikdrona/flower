import { Request, Response } from 'express';
import User from '../models/User';
import { viewUser } from '../utils';

export const createFirstUser = async (req: Request, res: Response) => {
    const user = await User.create(req.body);
    return res.status(201).send({
        message: 'User created successfully!',
        user: viewUser(user)
    });
};
