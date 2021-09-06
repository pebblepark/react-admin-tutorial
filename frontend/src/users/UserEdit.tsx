import { makeStyles } from '@material-ui/core';
import React from 'react';
import { Edit, EditProps } from 'react-admin';
import UserForm from './UserForm';

const UserEdit = (props: EditProps) => {
	return (
		<Edit {...props}>
			<UserForm />
		</Edit>
	);
};

export default UserEdit;
