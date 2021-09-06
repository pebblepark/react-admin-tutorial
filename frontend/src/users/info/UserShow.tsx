import React from 'react';
import { DateField, Show, ShowProps, SimpleShowLayout, TextField, UrlField } from 'react-admin';
import MyUrlField from '../../common/MyUrlField';
import { User } from '../types';

interface UserTitleProps {
	record?: User;
}

const UserTitle = ({ record }: UserTitleProps) => {
	return <span>사용자 {record && `#${record.id}`}</span>;
};

const UserShow = (props: ShowProps) => {
	return (
		<Show {...props} title={<UserTitle />}>
			<SimpleShowLayout>
				<TextField source="name" label="이름" />
				<TextField source="username" label="사용자이름" />
				<UrlField source="email" />
				<DateField source="birth" label="생년월일" />
				<TextField source="address.street" label="주소" />
				<TextField source="phone" label="전화번호" />
				<MyUrlField source="website" label="사이트" addLabel />
				<TextField source="company.name" label="회사명" />
			</SimpleShowLayout>
		</Show>
	);
};
export default UserShow;
