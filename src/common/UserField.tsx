import { FieldProps, linkToRecord, useRecordContext } from 'react-admin';
import { Link } from 'react-router-dom';

const UserField = (props: FieldProps) => {
	const record = useRecordContext(props);
	const userShowPage = linkToRecord('/users', record.user.id, 'show');
	return (
		<Link to={userShowPage} onClick={e => e.stopPropagation()} style={{ textDecoration: 'none' }}>
			{record.user.username}
		</Link>
	);
};

export default UserField;
