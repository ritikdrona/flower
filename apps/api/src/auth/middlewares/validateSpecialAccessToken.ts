import { NextFunction, Response } from 'express';
import { Request } from '../../types/request';

export const validateSpecialAccessToken = (
    req: Request,
    res: Response,
    next: NextFunction
) => {
    const header = req.headers['special-access-token'] as string;
    const specialAccessToken = process.env.SPECIAL_ACCESS_TOKEN;
    if (!header || header !== specialAccessToken) {
        return res.status(401).json({
            message: 'Unauthorized'
        });
    }

    next();
};
