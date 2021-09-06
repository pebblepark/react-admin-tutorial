import React from 'react';
import { DateField, Show, ShowProps, SimpleForm, TextField, UrlField } from 'react-admin';
import MyUrlField from '../common/MyUrlField';

const UserShow = (props: ShowProps) => {
	return (
		<Show {...props}>
			<SimpleForm>
				<TextField source="name" label="이름" emptyText="-" />
				<TextField source="username" label="사용자 이름" emptyText="-" />
				<UrlField source="email" emptyText="-" />
				<TextField source="phone" label="전화번호" emptyText="-" />
				<DateField source="birth" label="생년월일" emptyText="-" />
				<MyUrlField source="website" label="사이트" addLabel emptyText="-" />
				<TextField source="address.street" label="주소" emptyText="-" />
				<TextField source="address.zipcode" label="우편번호" emptyText="-" />
				<TextField source="company.name" label="회사명" emptyText="-" />
			</SimpleForm>
		</Show>
	);
};

export default UserShow;
