import { Request, Response } from 'express';
import { generateJWT } from '../../lib/jwt';
import User from '../models/User';
import { viewUser } from '../utils';

export const authenticate = async (req: Request, res: Response) => {
    const { username, password } = req.body;

    const user = await User.findOne({ username: username });
    if (user === null) {
        return res.status(404).send({
            success: false,
            message: 'Username or password incorrect.',
            user: null
        });
    }

    if (user?.password !== password) {
        return res.status(200).send({
            success: false,
            message: 'Username or password incorrect.',
            user: null
        });
    }

    const token = generateJWT(user);

    return res.status(200).send({
        success: true,
        message: 'Authenticated Successfully!',
        user: viewUser(user),
        token: token
    });
};
