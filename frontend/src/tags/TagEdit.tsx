import * as React from 'react';
import {
	Edit,
	SimpleForm,
	TextField,
	TextInput,
	required,
	List,
	Datagrid,
	ResourceContextProvider,
	EditButton,
	TranslatableInputs,
	EditProps,
	BooleanInput,
	SelectInput,
	ReferenceInput,
	DateField,
	ShowButton,
	DeleteButton,
} from 'react-admin';
import FormattedBooleanInput from '../common/FormatterBooleanInput';

const TagEdit = (props: EditProps) => (
	<>
		<Edit {...props}>
			<SimpleForm redirect="list" warnWhenUnsavedChanges>
				<TextInput source="name" validate={[required()]} />
				<FormattedBooleanInput
					source="useYn"
					label="사용여부"
					format={(value: string) => value === 'Y'}
					parse={(value: string) => (value ? 'Y' : 'N')}
				/>
			</SimpleForm>
		</Edit>
		<ResourceContextProvider value="post">
			<List
				hasCreate={false}
				hasShow
				hasEdit
				hasList
				basePath="/post-tags"
				resource="post-tags"
				filter={{ tag: props.id }}
				title=" "
			>
				<Datagrid>
					<TextField source="id" />
					<TextField source="title" />
					<DateField source="creTime" label="등록일" />
					<DateField source="updTime" label="수정일" />
					<ShowButton basePath="/posts" />
					<EditButton basePath="/posts" />
				</Datagrid>
			</List>
		</ResourceContextProvider>
	</>
);
export default TagEdit;
