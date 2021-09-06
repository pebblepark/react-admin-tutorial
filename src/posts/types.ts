import { Record } from 'react-admin';
import { User } from '../users/types';

export interface Post extends Record {
	id: number;
	user: User;
	title: string;
	body: string;
	views: number;
	time: Date;
	file: FileInfo;
}

export interface FileInfo {
	base64: string;
	fileName: string;
	fileSize: number;
}
