import { Document, model, Schema } from 'mongoose';

export interface UserInterface extends Document {
    name: String;
    username: String;
    password: String;
}

const userSchema: Schema = new Schema({
    name: String,
    username: String,
    password: String
});

const User = model<UserInterface>('User', userSchema);
export default User;
