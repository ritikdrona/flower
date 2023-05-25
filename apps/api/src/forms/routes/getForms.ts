import { Request, Response } from 'express';

export const getForms = async (req: Request, res: Response) => {
    res.json({ Forms: true });
};
