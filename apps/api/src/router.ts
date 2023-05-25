import { Router } from 'express';
import authRouter from './auth/router';
import formsRouter from './forms/router';

const router = Router();

router.get('/', (req, res) => {
    res.send({ message: 'API is live!!' });
});

router.use('/auth', authRouter);
router.use('/forms', formsRouter);

export default router;
