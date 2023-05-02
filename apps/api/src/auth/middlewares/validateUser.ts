import { NextFunction, Response } from 'express';
import { verifyJWT } from '../../lib/jwt';
import { Request } from '../../types/request';

export const validateUser = (
    req: Request,
    res: Response,
    next: NextFunction
) => {
    const header = req.headers['authorization'] as string;
    if (!header || !header.includes(' ')) {
        return res.status(401).json({
            message: 'Unauthorized'
        });
    }

    const token = header.split(' ').at(1) as string;
    const payload = verifyJWT(token);
    if (payload === null) {
        return res.status(401).json({
            message: 'Unauthorized'
        });
    }

    req.userId = payload.sub as string;
    next();
};
