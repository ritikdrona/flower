import { Router } from 'express';
import { updateFields } from './routes/updateFields';

const fieldsRouter = Router();

fieldsRouter.put('/', updateFields);

export default fieldsRouter;
