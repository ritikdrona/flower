import { Router } from 'express';
import { validateSpecialAccessToken } from './middlewares/validateSpecialAccessToken';
import { authenticate } from './routes/authenticate';
import { createFirstUser } from './routes/createFirstUser';
import { getUsers } from './routes/getUsers';
import { validateUser } from './middlewares/validateUser';

const authRouter = Router();

authRouter.post('/authenticate', authenticate);
authRouter.post('/users/first', validateSpecialAccessToken, createFirstUser);
authRouter.get('/users', validateUser, getUsers);

export default authRouter;
