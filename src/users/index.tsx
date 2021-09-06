import UserCreate from './UserCreate';
import UserEdit from './UserEdit';
import { UserList } from './UserList';
import UserShow from './UserShow';

export default {
	list: UserList,
	create: UserCreate,
	edit: UserEdit,
	show: UserShow,
	options: { label: '사용자 관리' },
};
