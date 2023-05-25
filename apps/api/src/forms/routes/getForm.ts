import { Request, Response } from 'express';

export const getForm = async (req: Request, res: Response) => {
    res.json({ Forms: true });
};
