import { Box, makeStyles, Theme, useMediaQuery } from '@material-ui/core';
import {
	ArrayField,
	AutocompleteInput,
	ChipField,
	Datagrid,
	DateInput,
	EditButton,
	FileField,
	List,
	ListProps,
	ReferenceArrayField,
	ReferenceInput,
	RichTextField,
	SimpleList,
	SingleFieldList,
	TextField,
	TextInput,
	useGetOne,
	useRecordContext,
} from 'react-admin';
import FileCustomField from '../common/FileCustomField';
import UserField from '../common/UserField';
import { User } from '../users/types';
import { Post } from './types';

const postFilters = [
	<TextInput source="all" label="title / body" alwaysOn />,
	<DateInput source="startTime" label="등록시작일" />,
	<DateInput source="endTime" label="등록종료일" />,
	<ReferenceInput source="userId" reference="users" label="작성자">
		<AutocompleteInput optionText={(choice: User) => (choice.id ? `${choice.username}` : '')} />
	</ReferenceInput>,
	// <ReferenceInput source="username" label="작성자" reference="users" allowEmpty>
	// 	<SelectInput optionText="username" />
	// </ReferenceInput>,
];

const useStyles = makeStyles({
	header: {
		textAlign: 'center',
		padding: '20px 0',
	},
	row: {
		textAlign: 'center',
	},
	leftRow: {
		textAlign: 'left',
	},
	no: {
		width: '5%',
	},
	user: {
		width: '15%',
	},
	title: {
		width: '45%',
	},
	tags: {
		width: '20%',
	},
	time: {
		width: '15%',
	},
});

const PostPanel = () => {
	const record = useRecordContext();
	const { data, loaded } = useGetOne<Post>('posts', record.id);

	return loaded ? (
		<>
			<FileCustomField
				record={data}
				source="file.base64"
				title="file.fileName"
				download={data?.file.fileName}
			/>
			<RichTextField record={data} source="body" />
		</>
	) : null;
};

export const PostList = (props: ListProps) => {
	const classes = useStyles();
	const isSmall = useMediaQuery((theme: Theme) => theme.breakpoints.down('sm'));
	return (
		<List {...props} filters={postFilters}>
			{isSmall ? (
				<SimpleList
					primaryText={record => record.title}
					secondaryText={record => `${record.views} views`}
					tertiaryText={record => new Date(record.time).toLocaleDateString()}
				/>
			) : (
				<Datagrid
					rowClick="edit"
					expand={<PostPanel />}
					classes={{ headerCell: classes.header, rowCell: classes.row }}
				>
					<TextField source="id" label="번호" headerClassName={classes.no} />
					<UserField source="user" label="작성자" headerClassName={classes.user} />
					<TextField
						source="title"
						label="제목"
						headerClassName={classes.title}
						cellClassName={classes.leftRow}
					/>
					<ArrayField
						label="Tags"
						source="tags"
						headerClassName={classes.tags}
						cellClassName={classes.leftRow}
					>
						<SingleFieldList>
							<ChipField source="name" size="small" />
						</SingleFieldList>
					</ArrayField>
					<TextField source="creTime" label="등록일" headerClassName={classes.time} />
					<EditButton />
				</Datagrid>
			)}
		</List>
	);
};
