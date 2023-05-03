import { Document, Model, ObjectId } from 'mongoose';

const findOne = async <T>(model: Model<Document>) => {
    return await model.findOne();
};

const findAll = async <T>(model: Model<T>) => {
    return await model.find();
};

const create = async <T>(model: Model<Document>, document: Document<T>) => {
    return await model.create(document);
};

const update = async <T>(model: Model<Document>, documentId: ObjectId) => {
    return await model.updateOne({ _id: documentId });
};

const patch = async <T>(model: Model<Document>, document: T) => {
    return null;
};

const deleteOne = async <T>(model: Model<Document>, documentId: ObjectId) => {
    return await model.deleteOne({ _id: documentId });
};

export const db = {
    findOne,
    findAll,
    create,
    update,
    patch,
    deleteOne
};
