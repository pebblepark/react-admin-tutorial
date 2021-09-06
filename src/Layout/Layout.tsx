import React from 'react';
import { Layout, LayoutProps, Sidebar } from 'react-admin';
import MyAppBar from './Appbar';
import Menu from './Menu';

const MyLayout = (props: LayoutProps) => {
	return <Layout {...props} appBar={MyAppBar} menu={Menu} />;
};

export default MyLayout;
