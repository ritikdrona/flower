import { UserInterface } from '../auth/models/User';
import { sign, verify, Secret, VerifyOptions } from 'jsonwebtoken';
import config from '../config';

const JWT_SECRET_KEY = process.env.JWT_SECRET_KEY as Secret;

export const generateJWT = (user: UserInterface): string => {
    const token: string = sign(
        {
            sub: user.id
        },
        JWT_SECRET_KEY
    );
    return token;
};

export const verifyJWT = (token: string): any => {
    const verifyOptions: VerifyOptions = {
        maxAge: config.JWT_TOKEN_MAX_AGE
    };
    try {
        const payload = verify(token, JWT_SECRET_KEY, verifyOptions);
        return payload;
    } catch (error: any) {
        console.log(error.message);
        return null;
    }
};
