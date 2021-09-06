import * as React from 'react';
import {
	Create,
	SimpleForm,
	TextField,
	TextInput,
	required,
	TranslatableInputs,
	CreateProps,
	ReferenceInput,
	SelectInput,
} from 'react-admin';

const TagCreate = (props: CreateProps) => (
	<Create {...props}>
		<SimpleForm redirect="list">
			<TextField source="id" />
			<TextInput source="name" validate={[required()]} />
			<ReferenceInput source="parentId" reference="tags" label="상위태그">
				<SelectInput optionText="name" />
			</ReferenceInput>
		</SimpleForm>
	</Create>
);

export default TagCreate;
