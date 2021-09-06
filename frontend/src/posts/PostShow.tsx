import { Box, Grid, TextField, Typography } from '@material-ui/core';
import React from 'react';
import {
	ArrayField,
	ChipField,
	DeleteButton,
	EditButton,
	FileField,
	FileInput,
	ListButton,
	Record,
	RichTextField,
	Show,
	ShowProps,
	SingleFieldList,
	TopToolbar,
} from 'react-admin';
import FileCustomField from '../common/FileCustomField';
import PostAside from './PostAside';
import { Post } from './types';

interface PostShowActionProps {
	basePath?: string;
	data?: Record;
	resource?: string;
}

const PostShowActions = ({ basePath, data, resource }: PostShowActionProps) => {
	return (
		<TopToolbar>
			<ListButton basePath={basePath} record={data} />
			<EditButton basePath={basePath} record={data} />
			<DeleteButton basePath={basePath} record={data} />
		</TopToolbar>
	);
};

const PostTitle = ({ record }: { record?: Post }) => {
	return record ? <span>{record.title}</span> : null;
};

const PostShow = (props: ShowProps) => {
	return (
		<Show aside={<PostAside />} actions={<PostShowActions />} title={<PostTitle />} {...props}>
			<PostForm />
		</Show>
	);
};

const PostForm = ({ record }: { record?: Post }) => {
	return (
		<Box m={3}>
			<Grid container spacing={2}>
				<Grid xs={12} item>
					<Typography variant="h5">{record?.title}</Typography>
					<Box mt={1} mb={1}>
						<ArrayField label="Tags" source="tags">
							<SingleFieldList>
								<ChipField source="name" size="small" />
							</SingleFieldList>
						</ArrayField>
					</Box>
				</Grid>
				<Grid xs={12} item>
					<FileCustomField
						source="file.base64"
						title="file.fileName"
						download={record?.file?.fileName}
					/>
					<RichTextField source="body" />
				</Grid>
			</Grid>
		</Box>
	);
};

export default PostShow;
