import { ComponentNameToClassKey } from '@material-ui/core/styles/overrides';
import { Record } from 'react-admin';

export interface User extends Record {
	id: number;
	name: string;
	username: string;
	email: string;
	address: Address;
	phone: string;
	website: string;
	company: Company;
	birth: Date;
}

export interface Address {
	street: string;
	city: string;
	zipcode: string;
}

export interface Company {
	name: string;
	catchPhrase: string;
	bs: string;
}
