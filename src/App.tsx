import * as React from 'react';
import { Admin, Resource, Title } from 'react-admin';
import jsonServerProvider from 'ra-data-json-server';
import users from './users';
import posts from './posts';
import { Layout } from './Layout';
import Dashboard from './Dashboard';
import Routes from './Layout/Routes';
import tags from './tags';
import dataProvider from './api/dataProvider';
import myDataProvider from './api/addUploadFeature';

//const dataProvider = jsonServerProvider('https://jsonplaceholder.typicode.com');
//const dataProvider = jsonServerProvider('http://localhost:8080');

const App = () => {
	return (
		<Admin
			layout={Layout}
			customRoutes={Routes}
			dashboard={Dashboard}
			dataProvider={myDataProvider}
		>
			<Resource name="users" {...users} />
			<Resource name="posts" {...posts} />
			<Resource name="tags" {...tags} />
			<Resource name="post-tags" />
		</Admin>
	);
};

export default App;
