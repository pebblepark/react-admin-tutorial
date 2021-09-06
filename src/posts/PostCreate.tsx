import RichTextInput from 'ra-input-rich-text';
import React from 'react';
import {
	AutocompleteArrayInput,
	Create,
	CreateProps,
	FileField,
	FileInput,
	ImageField,
	ImageInput,
	ReferenceArrayInput,
	ReferenceInput,
	required,
	SelectInput,
	SimpleForm,
	TextInput,
} from 'react-admin';
import { Tag } from '../tags/types';

const PostCreate = (props: CreateProps) => {
	return (
		<Create {...props}>
			<SimpleForm warnWhenUnsavedChanges>
				<ReferenceInput source="user.id" reference="users" label="작성자" validate={required()}>
					<SelectInput optionText="username" />
				</ReferenceInput>
				<TextInput source="title" fullWidth label="제목" validate={required()} />
				<RichTextInput source="body" label="본문" addLabel={false} />
				<ImageInput multiple source="pictures" accept="image/*">
					<ImageField source="src" title="title" />
				</ImageInput>
				<FileInput source="file">
					<FileField source="src" title="title" />
				</FileInput>
				<ReferenceArrayInput
					source="tags"
					reference="tags"
					format={(v: Array<Tag>) => v && v.map(x => x.id)}
					parse={(v: Array<Tag>) => v && v.map(x => ({ id: x }))}
				>
					<AutocompleteArrayInput fullWidth />
				</ReferenceArrayInput>
			</SimpleForm>
		</Create>
	);
};

export default PostCreate;
