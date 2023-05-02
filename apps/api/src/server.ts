import cors from 'cors';
import dotenv from 'dotenv';
import express from 'express';

import './database';
import router from './router';

dotenv.config();

const app = express();
app.set('title', 'Emma API');
app.use(express.json());
app.use(cors());
app.use('/', router);

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => {
    console.log(`Listening on port ${PORT}!`);
});
