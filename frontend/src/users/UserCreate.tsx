import React from 'react';
import { Create, CreateProps } from 'react-admin';
import UserForm from './UserForm';

const UserCreate = (props: CreateProps) => {
	return (
		<Create {...props}>
			<UserForm />
		</Create>
	);
};

export default UserCreate;
