import { Router } from 'express';
import authRouter from './auth/router';

const router = Router();

router.get('/', (req, res) => {
    res.send({ message: 'API is live!!' });
});

router.use('/auth', authRouter);

export default router;
