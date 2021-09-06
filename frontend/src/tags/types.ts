import { Record } from 'react-admin';

export interface Tag extends Record {
	id: string;
	name: string;
	useYn: string;
	parentId: string;
}
