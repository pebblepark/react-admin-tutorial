import { Box, Card, CardContent, makeStyles } from '@material-ui/core';
import { DateField, SimpleShowLayout, TextField } from 'react-admin';
import UserField from '../common/UserField';
import { Post } from './types';

const useAsideStyles = makeStyles(theme => ({
	root: {
		minWidth: 300,
		[theme.breakpoints.down('md')]: {
			display: 'none',
		},
	},
}));

const PostAside = ({ record }: { record?: Post }) => {
	const classes = useAsideStyles();
	return <div className={classes.root}>{record && <PostInfo record={record} />}</div>;
};

const PostInfo = ({ record }: { record: Post }) => (
	<Box ml={1}>
		<Card>
			<CardContent>
				<SimpleShowLayout>
					<TextField record={record} source="id" label="No" />
					<UserField record={record} source="user" addLabel label="작성자" />
					<TextField record={record} source="creTime" label="등록일시" />
					<TextField record={record} source="updTime" label="수정일시" />
					{/* <TextField record={record} source="views" label="조회수" /> */}
				</SimpleShowLayout>
			</CardContent>
		</Card>
	</Box>
);

export default PostAside;
