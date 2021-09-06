import * as React from 'react';
import { Card, CardContent, CardHeader } from '@material-ui/core';
import { Title } from 'react-admin';

export default () => (
	<Card>
		<Title title="Dashboard" />
		<CardHeader title="Welcome to the administration" />
		<CardContent>Lorem ipsum sic dolor amet...</CardContent>
	</Card>
);
