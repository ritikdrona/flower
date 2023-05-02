import mongoose from 'mongoose';

const databaseUrl: string = process.env.MONGO_URL as string;
mongoose.connect(databaseUrl).catch((error) => console.log(error.message));

mongoose.connection.on('connected', () =>
    console.log('DB Connected Successfully!')
);
