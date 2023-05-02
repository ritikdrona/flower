import dotenv from 'dotenv';
import mongoose, { ConnectOptions } from 'mongoose';

dotenv.config();

const databaseUrl: string = process.env.MONGO_URL as string;
mongoose.connect(databaseUrl);
