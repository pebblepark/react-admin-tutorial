import { Theme, useMediaQuery } from '@material-ui/core';
import * as React from 'react';
import {
	List,
	Datagrid,
	TextField,
	EmailField,
	UrlField,
	ListProps,
	ListContextProvider,
	Title,
	DateField,
} from 'react-admin';
import MyUrlField from '../common/MyUrlField';
import MobileGrid from './MobileGrid';

export const UserList = (props: ListProps) => {
	const isSmall = useMediaQuery((theme: Theme) => theme.breakpoints.down('sm'));

	return (
		<List {...props}>
			{isSmall ? (
				<MobileGrid />
			) : (
				<Datagrid rowClick="show">
					<TextField source="name" label="이름" />
					<TextField source="username" label="사용자이름" />
					<DateField source="birth" label="생년월일" />
					<EmailField source="email" />
					<TextField source="address.street" label="주소" />
					<TextField source="phone" label="전화번호" />
					<MyUrlField source="website" label="사이트" />
					<TextField source="company.name" label="회사명" />
				</Datagrid>
			)}
		</List>
	);
};
