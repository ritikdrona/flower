import { Router } from 'express';
import entriesRouter from './entries/router';
import fieldsRouter from './fields/router';
import { getForms } from './routes/getForms';
import { createForm } from './routes/createForm';
import { patchForm } from './routes/patchForm';
import { deleteForm } from './routes/deleteForm';
import { getForm } from './routes/getForm';

const formsRouter = Router();

formsRouter.use('/:formId/entries', entriesRouter);
formsRouter.use('/:formId/fields', fieldsRouter);

formsRouter.get('/', getForms);
formsRouter.post('/', createForm);
formsRouter.get('/:id', getForm);
formsRouter.patch('/:id', patchForm);
formsRouter.delete('/:id', deleteForm);

export default formsRouter;
