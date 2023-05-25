import { Router } from 'express';
import { getEntries } from './routes/getEntries';
import { createEntry } from './routes/createEntry';
import { patchEntry } from './routes/patchEntry';
import { deleteEntry } from './routes/deleteEntry';

const entriesRouter = Router();

entriesRouter.get('/', getEntries);
entriesRouter.post('/', createEntry);
entriesRouter.patch('/:id', patchEntry);
entriesRouter.delete('/:id', deleteEntry);

export default entriesRouter;
