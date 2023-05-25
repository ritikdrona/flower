import { Router } from 'express';
import { getFlows } from './routes/getFlows';
import { createFlow } from './routes/createFlow';
import { patchFlow } from './routes/patchFlow';
import { deleteFlow } from './routes/deleteFlow';

const flowsRouter = Router();

flowsRouter.get('/', getFlows);
flowsRouter.post('/', createFlow);
flowsRouter.patch('/:id', patchFlow);
flowsRouter.delete('/:id', deleteFlow);

export default flowsRouter;
