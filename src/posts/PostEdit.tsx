import { Card, CardContent, Typography } from '@material-ui/core';
import RichTextInput from 'ra-input-rich-text';
import React from 'react';
import {
	AutocompleteArrayInput,
	Edit,
	EditProps,
	FileField,
	FileInput,
	FormWithRedirect,
	ReferenceArrayInput,
	TextInput,
	Toolbar,
} from 'react-admin';
import { convertFileToBase64 } from '../api/addUploadFeature';
import FileCustomField from '../common/FileCustomField';
import { Tag } from '../tags/types';
import PostAside from './PostAside';
import { FileInfo } from './types';

const PostEdit = (props: EditProps) => {
	return (
		<Edit aside={<PostAside />} {...props}>
			<PostForm />
		</Edit>
	);
};

const PostForm = (props: any) => (
	<FormWithRedirect
		{...props}
		render={(formProps: any) => {
			return (
				<Card>
					<form>
						<CardContent>
							<Typography variant="h6" gutterBottom>
								Post
							</Typography>
							<TextInput label="제목" source="title" fullWidth />
							<RichTextInput label="본문" multiline source="body" fullWidth />
							<FileInput
								source="file"
								// parse={async (v: any) => {
								// 	if (!v) return;
								// 	const base64 = await convertFileToBase64(v);
								// 	console.log(base64);
								// 	return { base64, fileName: v.name, fileSize: v.size };
								// }}
							>
								<FileField
									source="base64"
									title="fileName"
									download={formProps.record.file.fileName}
								/>
							</FileInput>
							<ReferenceArrayInput
								source="tags"
								reference="tags"
								format={(v: Array<Tag>) => v && v.map(x => x.id)}
								parse={(v: Array<Tag>) => v && v.map(x => ({ id: x }))}
							>
								<AutocompleteArrayInput fullWidth />
							</ReferenceArrayInput>
						</CardContent>
					</form>
					<Toolbar
						record={formProps.record}
						basePath={formProps.basePath}
						undoable={true}
						invalid={formProps.invalid}
						handleSubmit={formProps.handleSubmit}
						saving={formProps.saving}
						resource="post"
					/>
				</Card>
			);
		}}
	/>
);

export default PostEdit;
