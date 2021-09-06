import { Record } from 'react-admin';
import PostCreate from './PostCreate';
import PostEdit from './PostEdit';
import { PostList } from './PostList';
import PostShow from './PostShow';

const PostTitle = ({ record }: Record) => {
	return <span>Post {record ? `#${record.id}` : ''}</span>;
};

export default {
	list: PostList,
	show: PostShow,
	edit: PostEdit,
	create: PostCreate,
};
